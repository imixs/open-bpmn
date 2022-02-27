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

    protected renderLine(edge: SEdge, segments: Point[], context: RenderingContext, args?: IViewArgs): VNode {
        let path = '';
        console.log('*** === ich bin in ner irren methode !');
        for (let i = 0; i < segments.length; i++) {
            const p = segments[i];
            if (i === 0) {
                path = `M ${p.x},${p.y}`;
            }
            if (isIntersectingRoutedPoint(p)) {
                path += this.intersectionPath(edge, segments, p, args);
            }
            if (i !== 0) {
                path += ` L ${p.x},${p.y}`;
            }

            console.log( p.x + ',' + p.y);
        }
        console.log('*** ende');
        return <path d={path} />;
    }

}


