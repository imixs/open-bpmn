/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { VNode } from 'snabbdom';
import { IView, ORIGIN_POINT, RenderingContext, setAttr, SModelElement, svg } from 'sprotty';
import { isResizable, ResizeHandleLocation, SResizeHandle } from '../change-bounds/model';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/**
 * This view is used for the invisible end of the feedback edge.
 * A feedback edge is shown as a visual feedback when creating edges.
 */
@injectable()
export class FeedbackEdgeEndView implements IView {
    render(model: Readonly<SModelElement>, context: RenderingContext): VNode {
        const position: Point = (model as any).position || ORIGIN_POINT;
        return <g x={position.x} y={position.y} />;
    }
}

@injectable()
export class SResizeHandleView implements IView {
    render(handle: SResizeHandle, context: RenderingContext): VNode {
        const position = this.getPosition(handle);
        if (position !== undefined) {
            const node = (
                <circle
                    class-sprotty-resize-handle={true}
                    class-mouseover={handle.hoverFeedback}
                    cx={position.x}
                    cy={position.y}
                    r={this.getRadius()}
                />
            );
            setAttr(node, 'data-kind', handle.location);
            return node;
        }
        // Fallback: Create an empty group
        return <g />;
    }

    protected getPosition(handle: SResizeHandle): Point | undefined {
        const parent = handle.parent;
        if (isResizable(parent)) {
            if (handle.location === ResizeHandleLocation.TopLeft) {
                return { x: 0, y: 0 };
            } else if (handle.location === ResizeHandleLocation.TopRight) {
                return { x: parent.bounds.width, y: 0 };
            } else if (handle.location === ResizeHandleLocation.BottomLeft) {
                return { x: 0, y: parent.bounds.height };
            } else if (handle.location === ResizeHandleLocation.BottomRight) {
                return { x: parent.bounds.width, y: parent.bounds.height };
            }
        }
        return undefined;
    }

    getRadius(): number {
        return 7;
    }
}
