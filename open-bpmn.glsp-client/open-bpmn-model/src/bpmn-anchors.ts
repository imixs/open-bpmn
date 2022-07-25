/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
import {
    almostEquals,
    Bounds,
    IAnchorComputer,
    ManhattanEdgeRouter,
    Point,
    PolylineEdgeRouter,
    SConnectableElement
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';

export const BPMN_ELEMENT_ANCHOR_KIND = 'bpmn-element';

/**
 * This BPMNElementAnchor computes a centered anchor point of the BPMN FlowElements
 * This AnchorType is based on the ManhattanEdgeRouter which is used in Open BPMN.
 * Event and Gateway elements provide 4 anchor points (north, each, south, west)
 * The Task element provides a series of grid anchor points dependign on the bounds ofs
 * the element.
 */
@injectable()
export class BPMNElementAnchor implements IAnchorComputer {
    static KIND = ManhattanEdgeRouter.KIND + ':' + BPMN_ELEMENT_ANCHOR_KIND;

    get kind(): string {
        return BPMNElementAnchor.KIND;
    }

    getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number): Point {
        const b = connectable.bounds;
        if (b.width <= 0 || b.height <= 0) {
            return b;
        }
        const bounds: Bounds = {
            x: b.x - offset,
            y: b.y - offset,
            width: b.width + 2 * offset,
            height: b.height + 2 * offset
        };
        if (refPoint.x >= bounds.x && bounds.x + bounds.width >= refPoint.x) {
            if (refPoint.y < bounds.y + 0.5 * bounds.height) {
                return { x: refPoint.x, y: bounds.y };
            } else {
                return { x: refPoint.x, y: bounds.y + bounds.height };
            }
        }
        if (refPoint.y >= bounds.y && bounds.y + bounds.height >= refPoint.y) {
            if (refPoint.x < bounds.x + 0.5 * bounds.width) {
                return { x: bounds.x, y: refPoint.y };
            } else {
                return { x: bounds.x + bounds.width, y: refPoint.y };
            }
        }
        return Bounds.center(bounds);
    }
}

@injectable()
export class BPMNPolylineElementAnchor implements IAnchorComputer {
    get kind(): string {
        return PolylineEdgeRouter.KIND + ':' + BPMN_ELEMENT_ANCHOR_KIND;
    }

    getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number): Point {
        const bounds = connectable.bounds;
        const c = Bounds.center(bounds);
        const finder = new NearestPointFinder(c, refPoint);
        if (!almostEquals(c.y, refPoint.y)) {
            const xTop = this.getXIntersection(bounds.y, c, refPoint);
            if (xTop >= bounds.x && xTop <= bounds.x + bounds.width) {
                finder.addCandidate(xTop, bounds.y - offset);
            }
            const xBottom = this.getXIntersection(bounds.y + bounds.height, c, refPoint);
            if (xBottom >= bounds.x && xBottom <= bounds.x + bounds.width) {
                finder.addCandidate(xBottom, bounds.y + bounds.height + offset);
            }
        }
        if (!almostEquals(c.x, refPoint.x)) {
            const yLeft = this.getYIntersection(bounds.x, c, refPoint);
            if (yLeft >= bounds.y && yLeft <= bounds.y + bounds.height) {
                finder.addCandidate(bounds.x - offset, yLeft);
            }
            const yRight = this.getYIntersection(bounds.x + bounds.width, c, refPoint);
            if (yRight >= bounds.y && yRight <= bounds.y + bounds.height) {
                finder.addCandidate(bounds.x + bounds.width + offset, yRight);
            }
        }
        return finder.best;
    }

    protected getXIntersection(yIntersection: number, centerPoint: Point, point: Point): number {
        const t = (yIntersection - centerPoint.y) / (point.y - centerPoint.y);
        return (point.x - centerPoint.x) * t + centerPoint.x;
    }

    protected getYIntersection(xIntersection: number, centerPoint: Point, point: Point): number {
        const t = (xIntersection - centerPoint.x) / (point.x - centerPoint.x);
        return (point.y - centerPoint.y) * t + centerPoint.y;
    }
}

class NearestPointFinder {
    protected currentBest: Point | undefined;
    protected currentDist: number;

    constructor(protected centerPoint: Point, protected refPoint: Point) {}

    addCandidate(x: number, y: number): void {
        const dx = this.refPoint.x - x;
        const dy = this.refPoint.y - y;
        const dist = dx * dx + dy * dy;
        if (this.currentDist < 0 || dist < this.currentDist) {
            this.currentBest = {
                x: x,
                y: y
            };
            this.currentDist = dist;
        }
    }

    get best(): Point {
        if (this.currentBest === undefined) {
            return this.centerPoint;
        } else {
            return this.currentBest;
        }
    }
}
