/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
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
/* eslint-disable @typescript-eslint/no-unused-vars */
/** @jsx svg */
import {
    angleOfPoint,
    IView,
    Point,
    PolylineEdgeView,
    RectangularNodeView,
    RenderingContext,
    SEdge,
    SShapeElement,
    toDegrees
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { svg } from 'snabbdom-jsx';
import { VNode } from 'snabbdom/vnode';
import { ActivityNode, Icon, TaskNode, WeightedEdge } from './model';

@injectable()
export class TaskNodeView extends RectangularNodeView {
    render(node: TaskNode, context: RenderingContext): VNode {
        const rcr = this.getRoundedCornerRadius(node);
        const graph = (
            <g>
                <rect
                    class-sprotty-node={true}
                    class-task={true}
                    class-automated={node.taskType === 'automated'}
                    class-manual={node.taskType === 'manual'}
                    class-mouseover={node.hoverFeedback}
                    class-selected={node.selected}
                    x={0}
                    y={0}
                    rx={rcr}
                    ry={rcr}
                    width={Math.max(0, node.bounds.width)}
                    height={Math.max(0, node.bounds.height)}
                ></rect>
                {context.renderChildren(node)}
            </g>
        );
        return graph;
    }

    protected getRoundedCornerRadius(node: SShapeElement): number {
        return 5;
    }
}

@injectable()
export class ForkOrJoinNodeView extends RectangularNodeView {
    render(node: ActivityNode, context: RenderingContext): VNode {
        const graph = (
            <g>
                <rect
                    class-sprotty-node={true}
                    class-forkOrJoin={true}
                    class-mouseover={node.hoverFeedback}
                    class-selected={node.selected}
                    width={10}
                    height={Math.max(50, node.bounds.height)}
                ></rect>
            </g>
        );
        return graph;
    }
}

@injectable()
export class WorkflowEdgeView extends PolylineEdgeView {
    protected renderAdditionals(edge: SEdge, segments: Point[], context: RenderingContext): VNode[] {
        const p1 = segments[segments.length - 2];
        const p2 = segments[segments.length - 1];
        return [
            <path
                class-sprotty-edge={true}
                class-arrow={true}
                d='M 1.5,0 L 10,-4 L 10,4 Z'
                transform={`rotate(${toDegrees(angleOfPoint({ x: p1.x - p2.x, y: p1.y - p2.y }))} ${p2.x} ${p2.y}) translate(${p2.x} ${
                    p2.y
                })`}
            />
        ];
    }
}

@injectable()
export class WeightedEdgeView extends WorkflowEdgeView {
    render(edge: Readonly<WeightedEdge>, context: RenderingContext): VNode {
        const router = this.edgeRouterRegistry.get(edge.routerKind);
        const route = router.route(edge);
        if (route.length === 0) {
            return this.renderDanglingEdge('Cannot compute route', edge, context);
        }

        return (
            <g
                class-sprotty-edge={true}
                class-weighted={true}
                class-low={edge.probability === 'low'}
                class-medium={edge.probability === 'medium'}
                class-high={edge.probability === 'high'}
                class-mouseover={edge.hoverFeedback}
            >
                {this.renderLine(edge, route, context)}
                {this.renderAdditionals(edge, route, context)}
                {context.renderChildren(edge, { route })}
            </g>
        );
    }
}

@injectable()
export class IconView implements IView {
    render(element: Icon, context: RenderingContext): VNode {
        const radius = this.getRadius();
        return (
            <g>
                <circle class-sprotty-icon={true} r={radius} cx={radius} cy={radius}></circle>
                {context.renderChildren(element)}
            </g>
        );
    }

    getRadius(): number {
        return 16;
    }
}
