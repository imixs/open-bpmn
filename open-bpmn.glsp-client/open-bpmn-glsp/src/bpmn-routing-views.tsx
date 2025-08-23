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
    EdgePadding,
    GEdge,
    IViewArgs,
    Point,
    PolylineEdgeViewWithGapsOnIntersections,
    RenderingContext,
    angleOfPoint, hasArgs,
    isIntersectingRoutedPoint,
    svg, toDegrees
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

    /*
     * The goal of this method is to render a BPMN edge supporting rounded corners.
     */
    protected override renderLine(edge: GEdge, segments: Point[], context: RenderingContext, args?: IViewArgs): VNode {
        const path = this.createPathForSegments(edge, segments, args, true);
        const vnode: any = <path class-sprotty-edge={true} class-line={true} d={path} />;
        return vnode;
    }

    /**
     * This method adds the additionals for a BPMN Edge.
     *
     * For sequenceFlow and messageFlow edges we render an arrow at the end of the edge.
     * For conditional sequenceFlows we also render the 'default' conditional symbol (/)
     *
     * Finally the method adds a padding that makes it easier to grab the line with the mouse.
     * The 'edgePadding' is an optional argument for GEdge and is added by the BPMNGModelFactory
     * from the Server.
     *
     * (See: https://github.com/eclipse-glsp/glsp-client/blob/e7dec9bd52b9688a7a23005c
     * 3f7de652d0e85923/packages/client/src/views/gedge-view.tsx#L52
     *
     * @param edge
     * @param segments
     * @param context
     * @returns
     */
    protected override renderAdditionals(edge: GEdge, segments: Point[], context: RenderingContext): VNode[] {
        const additionals = super.renderAdditionals(edge, segments, context);
        const endP1 = segments[segments.length - 2];
        const endP2 = segments[segments.length - 1];
        const startP1 = segments[0];
        const startP2 = segments[1];

        // arrow depends on the type of the BPMNEdge
        if ('sequenceFlow' === edge.type || 'messageFlow' === edge.type) {
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
            if (hasArgs(edge)) {
                if (edge.args.default && 'true' === edge.args.default) {
                    additionals.push(defaultSymbol);
                }
            }
            additionals.push(arrow);
        }

        // Add the edge padding (added by the BPMNGModelFactory)
        const edgePadding = EdgePadding.from(edge);
        if (edgePadding) {
            additionals.push(this.renderEdgePadding(edge, segments, edgePadding));
        }
        return additionals;
    }

    /**
     * Additional path with transparent border to support edge padding feature.
     *
     * @param segments
     * @param padding
     * @returns
     */
    protected renderEdgePadding(edge: GEdge, segments: Point[], padding: number, args?: IViewArgs): VNode {
        return (
            <path
                class-mouse-handle
                d={this.createPathForSegments(edge, segments, args, false)}
                style-stroke-width={padding * 2}
                style-stroke='transparent'
                style-stroke-dasharray='none'
                style-stroke-dashoffset='0'
            />
        );
    }

    /**
     * This helper method renders the SVG path for an BPMN Edge. An edge can have multiple routing points.
     * In addition this method renders 'rounded' corners typical for BPMN.
     *
     * To render rounded corners we compute always the next segment to decide the corner angle.
     *
     * The method also supports the Sprotty Intersection feature. This renders an intersection if the edge
     * crosses another edge.
     *
     * @param edge
     * @param segments
     * @param args
     * @returns
     */
    protected createPathForSegments(edge: GEdge, segments: Point[], args?: IViewArgs, addIntersectionPoints?: boolean): string {
        let path = '';
        // let radius = 10;
        let radius = 10;
        for (let i = 0; i < segments.length; i++) {
            const p = segments[i];
            // start point?
            if (i === 0) {
                path = `M ${p.x},${p.y}`;
            }
            // Optional render the Intersection route point
            if (addIntersectionPoints && isIntersectingRoutedPoint(p)) {
                path += this.intersectionPath(edge, segments, p, args);
            }
            // render a line with rounded corners...
            if (i > 0) {
                // compute the direction of the next line...
                if (i < segments.length - 1) {
                    const plast = segments[i - 1];
                    const pnext = segments[i + 1];
                    // render lines ending with rounded corners...
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
        return path;
    }

    /**
     * Helper method to compute the maximum possible radius.
     * If two points are very close the radius need to be reduced
     */
    protected computeMaxRadius(pCurrent: Point, pLast: Point, pNext: Point): number {
        const defaultRadius = 10;
        const minRadius = 2;     // Minimum radius for visibility
        const maxRadiusFactor = 0.45;  // Use up to 45% of the shortest segment

        // Calculate segment lengths
        const segmentLengthBefore = Math.max(
            Math.abs(pCurrent.x - pLast.x),
            Math.abs(pCurrent.y - pLast.y)
        );

        const segmentLengthAfter = Math.max(
            Math.abs(pNext.x - pCurrent.x),
            Math.abs(pNext.y - pCurrent.y)
        );

        // Skip radius calculation if either segment is 0 (same points)
        if (segmentLengthBefore === 0 || segmentLengthAfter === 0) {
            return minRadius;
        }

        // Use the shorter segment to determine maximum possible radius
        const shortestSegment = Math.min(segmentLengthBefore, segmentLengthAfter);

        // Calculate radius based on shortest segment
        let calculatedRadius = shortestSegment * maxRadiusFactor;

        // Apply constraints
        calculatedRadius = Math.max(minRadius, calculatedRadius);  // Not smaller than minimum
        calculatedRadius = Math.min(defaultRadius, calculatedRadius);  // Not larger than default

        // For very short segments (< 5px), use an even smaller radius
        if (shortestSegment < 5) {
            calculatedRadius = Math.min(calculatedRadius, shortestSegment * 0.3);
        }

        // Ensure radius is never more than half of the shortest segment
        // This prevents visual artifacts
        calculatedRadius = Math.min(calculatedRadius, shortestSegment / 2);

        return Math.round(calculatedRadius * 10) / 10;  // Round to 1 decimal place
    }
}
