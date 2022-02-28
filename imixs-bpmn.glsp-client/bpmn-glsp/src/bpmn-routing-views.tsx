/********************************************************************************
 * Copyright (c) 2019-2022 EclipseSource and others.
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
	angleOfPoint,
	Point,
	PolylineEdgeViewWithGapsOnIntersections,
	RenderingContext,
	SEdge,
	IViewArgs,
	isIntersectingRoutedPoint,
	toDegrees
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { svg } from 'sprotty';

/****************************************************************************
 * This module provides BPMN Routings views for sequence flows
 *
 ****************************************************************************/

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/*
 * Layout for the bpmn sequence flow. The View extends the `PolylineEdgeView` that renders gaps on intersections,
 * and the `JumpingPolylineEdgeView` that renders jumps over intersections.
 *
 */
@injectable()
export class BPMNSequenceFlowView extends PolylineEdgeViewWithGapsOnIntersections {
	protected renderAdditionals(edge: SEdge, segments: Point[], context: RenderingContext): VNode[] {
		const additionals = super.renderAdditionals(edge, segments, context);
		const p1 = segments[segments.length - 2];
		const p2 = segments[segments.length - 1];

		const arrow = (
			<path
				class-sprotty-edge={true}
				class-arrow={true}
				d='M 1,0 L 14,-4 L 14,4 Z'
				transform={`rotate(${toDegrees(angleOfPoint({ x: p1.x - p2.x, y: p1.y - p2.y }))} ${p2.x} ${p2.y}) translate(${p2.x} ${p2.y
					})`}
			/>
		);
		additionals.push(arrow);
		return additionals;
	}

	/*
	 * The goal of this method is to render rounded corners. Therefore we compute always the next segment to decide
	 * the corner angle.
	 */
	protected renderLine(edge: SEdge, segments: Point[], context: RenderingContext, args?: IViewArgs): VNode {
		let path = '';
		const radius = 10;
		for (let i = 0; i < segments.length; i++) {
			const p = segments[i];
			// start point?
			if (i === 0) {
				path = `M ${p.x},${p.y}`;
			}
			if (isIntersectingRoutedPoint(p)) {
				path += this.intersectionPath(edge, segments, p, args);
			}
			// line...
			if (i > 0) {
				// compute the direction of the next line...
				if (i < segments.length - 1) {
					const plast = segments[i - 1];
					const pnext = segments[i + 1];
					// draw lines ending with rounded corners...
					// right-down  ↴
					if (plast.x < p.x && p.y < pnext.y) {
						path += ` L ${p.x - radius},${p.y}  Q ${p.x},${p.y} ${p.x},${p.y + radius}`;
						// down-right  ↳
					} else if (plast.y < p.y && p.x < pnext.x) {
						path += ` L ${p.x},${p.y - radius}  Q ${p.x},${p.y} ${p.x + radius},${p.y}`;
						// right-up  _↑
					} else if (plast.x < p.x && p.y > pnext.y) {
						path += ` L ${p.x - radius},${p.y}  Q ${p.x},${p.y} ${p.x},${p.y - radius}`;
						// up-right  ↱
					} else if (plast.y > p.y && p.x < pnext.x) {
						path += ` L ${p.x},${p.y + radius}  Q ${p.x},${p.y} ${p.x + radius},${p.y}`;
						// down-left  ↲
					} else if (plast.y < p.y && p.x > pnext.x) {
						path += ` L ${p.x},${p.y - radius}  Q ${p.x},${p.y} ${p.x - radius},${p.y}`;
						// left-down  ↓-
					} else if (plast.x > p.x && p.y < pnext.y) {
						path += ` L ${p.x+ radius},${p.y }  Q ${p.x},${p.y} ${p.x },${p.y + radius}`;
						// up-left  ↰
					} else if (plast.y > p.y && p.x > pnext.x) {
						path += ` L ${p.x},${p.y+ radius }  Q ${p.x},${p.y} ${p.x- radius },${p.y}`;
						// left-up ↑_
					} else if (plast.x > p.x && p.y > pnext.y) {
						path += ` L ${p.x + radius},${p.y }  Q ${p.x},${p.y} ${p.x},${p.y - radius }`;
					} else {
						// default
						path += ` L ${p.x},${p.y}`;
					}

				} else {
					// default behaviour 
					path += ` L ${p.x},${p.y}`;
				}
			}

		}
		return <path d={path} />;
	}

	/*
	 * compute corner segment for next point
	 */
	protected computeDirection(plast: Point, p: Point, pnext: Point) {
		console.log('compute direction.... plast=' + plast.x + ',' + plast.y + ' P=' + p.x + ',' + p.y + ' pnext=' + pnext.x + ',' + pnext.y);
		/*  plast  p       pnext
		 *  0,0    10,0    10,10  = NE  
		 *  0,0    0,10,   10,10  = SW
		 *  0,10   10,10,  10,0   = SE
		 *
		 *  0,10   0,0     10,0   = NW
		 */
		if (plast.x < p.x && p.x === pnext.x && plast.y === p.y && p.y < pnext.y) {
			return 'NE';
		}
		if (plast.x === p.x && p.x < pnext.x && plast.y < p.y && p.y === pnext.y) {
			return 'SW';
		}
		if (plast.x < p.x && p.x === pnext.x && plast.y === p.y && p.y > pnext.y) {
			return 'SE';
		}

		if (plast.x === p.x && p.x < pnext.x && plast.y > p.y && p.y === pnext.y) {
			return 'NW';
		}

		//M100,250 L100,100  L250 100


		return '';
	}
}


