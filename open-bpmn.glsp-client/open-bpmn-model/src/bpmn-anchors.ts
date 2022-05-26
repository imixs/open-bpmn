/********************************************************************************
 *  Open-BPMN
 *  
 *  Copyright (C) 2022 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 3 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *      https://github.com/imixs/open-bpmn
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 ********************************************************************************/

/**
 * This module provides Anchor Computers for the BPMN Manhattan Router.
 * There are different IAnchorComputer for each kind of core BPMN element
 */
import { injectable } from 'inversify';
import {
	ManhattanEdgeRouter,IAnchorComputer,SConnectableElement,PolylineEdgeRouter,
	Bounds,almostEquals,
	Point
} from '@eclipse-glsp/client';

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
            if (refPoint.y < bounds.y + 0.5 * bounds.height)
            return { x: refPoint.x, y: bounds.y };
            else
            return { x: refPoint.x, y: bounds.y + bounds.height };
        }
        if (refPoint.y >= bounds.y && bounds.y + bounds.height >= refPoint.y) {
            if (refPoint.x < bounds.x + 0.5 * bounds.width)
            return { x: bounds.x, y: refPoint.y };
            else
            return { x: bounds.x + bounds.width, y: refPoint.y };
        }
        return Bounds.center(bounds);
    }
}


@injectable()
export class BPMNPolylineElementAnchor implements IAnchorComputer {


    get kind(): string {
        return PolylineEdgeRouter.KIND + ':' + BPMN_ELEMENT_ANCHOR_KIND;
    }

    getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number ): Point {
        const bounds = connectable.bounds;
        const c = Bounds.center(bounds);
        const finder = new NearestPointFinder(c, refPoint);
        if (!almostEquals(c.y, refPoint.y)) {
            const xTop = this.getXIntersection(bounds.y, c, refPoint);
            if (xTop >= bounds.x && xTop <= bounds.x + bounds.width)
                finder.addCandidate(xTop, bounds.y - offset);
            const xBottom = this.getXIntersection(bounds.y + bounds.height, c, refPoint);
            if (xBottom >= bounds.x && xBottom <= bounds.x + bounds.width)
                finder.addCandidate(xBottom, bounds.y + bounds.height + offset);
        }
        if (!almostEquals(c.x, refPoint.x)) {
            const yLeft = this.getYIntersection(bounds.x, c, refPoint);
            if (yLeft >= bounds.y && yLeft <= bounds.y + bounds.height)
                finder.addCandidate(bounds.x - offset, yLeft);
            const yRight = this.getYIntersection(bounds.x + bounds.width, c, refPoint);
            if (yRight >= bounds.y && yRight <= bounds.y + bounds.height)
                finder.addCandidate(bounds.x + bounds.width + offset, yRight);
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

    constructor(protected centerPoint: Point, protected refPoint: Point) {
    }

    addCandidate(x: number, y: number) {
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
        if (this.currentBest === undefined)
            return this.centerPoint;
        else
            return this.currentBest;
    }
}

