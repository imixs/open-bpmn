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
    angleOfPoint, hasArguments, isIntersectingRoutedPoint,
    IViewArgs,
    Point,
    PolylineEdgeViewWithGapsOnIntersections,
    RenderingContext, SEdge, svg, toDegrees
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';

/****************************************************************************
 * This module provides BPMN Routings views for sequence flows
 *
 * Layout for the bpmn sequence flow. The View extends the `PolylineEdgeView`,
 * that renders gaps on intersections, and the `JumpingPolylineEdgeView` that
 * renders jumps over intersections.
 *
 * In addition the view render rounded corners for a manhattan routing and an
 * arrow on the edge end point
 *
 * For conditional SequenceFlows the view also adds the BPMN default symbol
 *
 ****************************************************************************/

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class BPMNEdgeView extends PolylineEdgeViewWithGapsOnIntersections {
    protected override renderAdditionals(edge: SEdge, segments: Point[], context: RenderingContext): VNode[] {
        const additionals = super.renderAdditionals(edge, segments, context);
        const endP1 = segments[segments.length - 2];
        const endP2 = segments[segments.length - 1];
        const startP1=segments[0];
        const startP2=segments[1];

        // arrow depends on the type of the BPMNEdge
        if ('sequenceFlow'===edge.type || 'messageFlow'===edge.type) {
          const arrow: any = (
            <path
                class-sprotty-edge={true}
                class-arrow={true}
                d='M 1,0 L 14,-4 L 14,4 Z'
                transform={`rotate(${toDegrees(angleOfPoint({ x: endP1.x - endP2.x, y: endP1.y - endP2.y }))}
                    ${endP2.x} ${endP2.y}) translate(${endP2.x} ${endP2.y}
                )`}
            />
          );

        // Conditional default Sequence flow?
        const defaultSymbol: any = (
            <path
                class-sprotty-edge={true}
                class-default-symbol={true}
                d='M 5,-4 L 10,4 Z'
                transform={`rotate(${toDegrees(angleOfPoint({ x: startP2.x - startP1.x, y: startP2.y - startP1.y }))} 
                    ${startP1.x} ${startP1.y}) translate(${startP1.x} ${startP1.y}
                )`}
            />
          );

          // if the edge is a sequence flow with the default property than add the default symbol
          if (hasArguments(edge)) {
            if (edge.args.default && 'true'===edge.args.default) {
                additionals.push(defaultSymbol);
             }
          }

          additionals.push(arrow);
        }
        return additionals;
    }

    /*
     * The goal of this method is to render rounded corners. Therefore we compute always the next segment to decide
     * the corner angle.
     */
    protected override renderLine(edge: SEdge, segments: Point[], context: RenderingContext, args?: IViewArgs): VNode {
        let path = '';
        // let radius = 10;
        let radius = 10;
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
                    radius = this.computeMaxRadius(p, plast, pnext);
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
                        path += ` L ${p.x + radius},${p.y}  Q ${p.x},${p.y} ${p.x},${p.y + radius}`;
                        // up-left  ↰
                    } else if (plast.y > p.y && p.x > pnext.x) {
                        path += ` L ${p.x},${p.y + radius}  Q ${p.x},${p.y} ${p.x - radius},${p.y}`;
                        // left-up ↑_
                    } else if (plast.x > p.x && p.y > pnext.y) {
                        path += ` L ${p.x + radius},${p.y}  Q ${p.x},${p.y} ${p.x},${p.y - radius}`;
                    } else {
                        // default
                        path += ` L ${p.x},${p.y}`;
                    }
                } else {
                    // default behavior
                    path += ` L ${p.x},${p.y}`;
                }
            }
        }

        const vnode: any = <path class-sprotty-edge={true} class-line={true} d={path} />;
        return vnode;
    }

    /**
     * Helper method to compute the maximum possible radius.
     * If two points are very close the radius need to be reduced
     */
    protected computeMaxRadius(pCurrent: Point, pLast: Point, pNext: Point): number {
        let radius = 10;
        const dRef = 0.5;
        // verify last point
        let xDif = Math.abs(pCurrent.x - pLast.x);
        let yDif = Math.abs(pCurrent.y - pLast.y);
        if (xDif > 0 && xDif < 10) {
            radius = xDif * dRef;
            return radius;
        }
        if (yDif > 0 && yDif < 10) {
            radius = yDif * dRef;
            return radius;
        }
        // verify next point
        xDif = Math.abs(pCurrent.x - pNext.x);
        yDif = Math.abs(pCurrent.y - pNext.y);
        if (xDif > 0 && xDif < 10) {
            radius = xDif * dRef;
            return radius;
        }
        if (yDif > 0 && yDif < 10) {
            radius = yDif * dRef;
            return radius;
        }
        // default
        return radius;
    }
}
