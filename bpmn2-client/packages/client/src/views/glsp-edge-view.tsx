/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { Point } from '@eclipse-glsp/protocol';
import { injectable } from 'inversify';
import { Classes, VNode } from 'snabbdom';
import { PolylineEdgeView, RenderingContext, SEdge, svg } from 'sprotty';
import { EdgePadding } from '../utils/argument-utils';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class GEdgeView extends PolylineEdgeView {
    render(edge: Readonly<SEdge>, context: RenderingContext): VNode {
        const router = this.edgeRouterRegistry.get(edge.routerKind);
        const route = router.route(edge);
        if (route.length === 0) {
            return this.renderDanglingEdge('Cannot compute route', edge, context);
        }

        return (
            <g class-sprotty-edge={true} class-mouseover={edge.hoverFeedback} {...this.addtionalClasses(edge, context)}>
                {this.renderLine(edge, route, context)}
                {this.renderAdditionals(edge, route, context)}
                {context.renderChildren(edge, { route })}
            </g>
        );
    }

    protected addtionalClasses(_edge: Readonly<SEdge>, _context: RenderingContext): Classes {
        return {};
    }

    protected renderLine(_edge: SEdge, segments: Point[], _context: RenderingContext): VNode {
        return <path d={this.createPathForSegments(segments)} />;
    }

    protected renderAdditionals(edge: SEdge, segments: Point[], _context: RenderingContext): VNode[] {
        // for additional padding we draw another transparent path with larger stroke width
        const edgePadding = EdgePadding.from(edge);
        return edgePadding ? [this.renderMouseHandle(segments, edgePadding)] : [];
    }

    protected renderMouseHandle(segments: Point[], padding: number): VNode {
        return (
            <path
                class-mouse-handle
                d={this.createPathForSegments(segments)}
                style-stroke-width={padding * 2}
                style-stroke='transparent'
                style-stroke-dasharray='none'
                style-stroke-dashoffset='0'
            />
        );
    }

    protected createPathForSegments(segments: Point[]): string {
        const firstPoint = segments[0];
        let path = `M ${firstPoint.x},${firstPoint.y}`;
        for (let i = 1; i < segments.length; i++) {
            const p = segments[i];
            path += ` L ${p.x},${p.y}`;
        }
        return path;
    }
}
