/********************************************************************************
 * Copyright (c) 2019 TypeFox and others.
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

/**
 * This module provides Anchor Computers for the BPMN Manhattan Router.
 * There are different IAnchorComputer for each kind of core BPMN element
 */
import { injectable } from 'inversify';
import {
	Bounds,
	Point,
	center,
	RectangleAnchor,
	ManhattanEdgeRouter,
	SConnectableElement,
	PolylineEdgeRouter,
	IAnchorComputer
} from 'sprotty';

export const BPMN_ELEMENT_ANCHOR_KIND = 'bpmn-element';
// export const BPMN_FLOW_ANCHOR_KIND = 'bpmn-flow';
export const BPMN_EVENT_ANCHOR_KIND = 'bpmn-event';

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
		console.log('...refPoint x=' + refPoint.x + ' y=' + refPoint.y);
		console.log('...initial-bounds x=' + bounds.x + ' y=' + bounds.y + ' w=' + bounds.width + ' h=' + bounds.height);

		/*
		 * The refPoint is between west and east
		 */
		if (refPoint.x >= bounds.x && bounds.x + bounds.width >= refPoint.x) {
			if (refPoint.y < bounds.y + 0.5 * bounds.height) {
				// north
				return { x: bounds.x + 0.5 * bounds.width, y: bounds.y };
			}
			else {
				// south
				return { x: bounds.x + 0.5 * bounds.width, y: bounds.y + bounds.height };
			}
		}
		/*
		 * The refPoint is between north and south
		 */
		if (refPoint.y >= bounds.y && bounds.y + bounds.height >= refPoint.y) {
			if (refPoint.x < bounds.x + 0.5 * bounds.width) {
				// west
				return { x: bounds.x, y: bounds.y + 0.5 * bounds.height };
			}
			else {
				// east
				return { x: bounds.x + bounds.width, y: bounds.y + 0.5 * bounds.height };
			}
		}
		// default....
		console.log('..WARNING - default to center!');
		return center(bounds);
	}
}

/*
 * This class provides a BPMN PolylineEdgeRouter by overwriten
 * the Sprotty RectangleAnchor
 */
@injectable()
export class BPMNSequenceFlowAnchor extends RectangleAnchor {

	get kind(): string {
		return PolylineEdgeRouter.KIND + ':' + BPMN_ELEMENT_ANCHOR_KIND;
	}
}

/*
 * Special anchorComputer for Event Elements
 */
@injectable()
export class BPMNEventElementAnchor implements IAnchorComputer {

	static KIND = ManhattanEdgeRouter.KIND + ':' + BPMN_EVENT_ANCHOR_KIND;

	get kind(): string {
		return BPMNEventElementAnchor.KIND;
	}

	getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number): Point {
		const b = connectable.bounds;
		if (b.width <= 0 || b.height <= 0) {
			return b;
		}

		console.log(' .... BPMNEventElementAnchor....');
		/*const bounds = {
			x: b.x - 20,
			y: b.y - 20,
			width: 40,
			height: 40
		};*/
		const bounds=b;

		const c = center(bounds);
		const dx = c.x - refPoint.x;
		const dy = c.y - refPoint.y;
		const distance = Math.sqrt(dx * dx + dy * dy);
		const normX = (dx / distance) || 0;
		const normY = (dy / distance) || 0;
		return {
			x: c.x - normX * (0.5 * bounds.width + offset),
			y: c.y - normY * (0.5 * bounds.height + offset)
		};
	}
}

@injectable()
export class BPMNPolylineEventAnchor implements IAnchorComputer {

	static readonly KIND = 'polyline';

    get kind(): string {
        return BPMNPolylineEventAnchor.KIND + ':' + BPMN_EVENT_ANCHOR_KIND;
    }

    getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number): Point {
        // const bounds = connectable.bounds;
		const b = connectable.bounds;
        console.log(' .... BPMNPolylineEventAnchor....');
		/*const bounds = {
			x: b.x - 20,
			y: b.y - 20,
			width: 40,
			height: 40
		};*/
		const bounds=b;
        const c = center(bounds);
        const dx = c.x - refPoint.x;
        const dy = c.y - refPoint.y;
        const distance = Math.sqrt(dx * dx + dy * dy);
        const normX = (dx / distance) || 0;
        const normY = (dy / distance) || 0;
        return {
            x: c.x - normX * (0.5 * bounds.width + offset),
            y: c.y - normY * (0.5 * bounds.height + offset)
        };
    }
}
