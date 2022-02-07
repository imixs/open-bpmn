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
import { injectable } from 'inversify';
import { Classes, VNode } from 'snabbdom';
import { Hoverable, RectangularNodeView, RenderingContext, Selectable, SNode, SPort, SShapeElement, svg } from 'sprotty';

import { CornerRadius } from '../utils/argument-utils';
import { RoundedCornerWrapper } from './rounded-corner';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class RoundedCornerNodeView extends RectangularNodeView {
    render(node: Readonly<SShapeElement & Hoverable & Selectable>, context: RenderingContext): VNode | undefined {
        const cornerRadius = CornerRadius.from(node);
        if (!cornerRadius) {
            return this.renderWithoutRadius(node, context);
        }

        const wrapper = new RoundedCornerWrapper(node, cornerRadius);
        return (
            <g class-node={true}>
                <defs>
                    <clipPath id={toClipPathId(node)}>
                        <path d={this.renderPath(wrapper, context, this.getClipPathInsets() || 0)}></path>
                    </clipPath>
                </defs>
                {this.renderPathNode(wrapper, context)}
                {context.renderChildren(node)}
            </g>
        );
    }

    protected renderWithoutRadius(node: Readonly<SShapeElement & Hoverable & Selectable>, context: RenderingContext): VNode | undefined {
        return super.render(node, context);
    }

    protected getClipPathInsets(): number | undefined {
        return 2;
    }

    protected renderPathNode(wrapper: Readonly<RoundedCornerWrapper>, context: RenderingContext): VNode {
        return (
            <path
                d={this.renderPath(wrapper, context, 0)}
                class-sprotty-node={wrapper.element instanceof SNode}
                class-sprotty-port={wrapper.element instanceof SPort}
                class-mouseover={wrapper.element.hoverFeedback}
                class-selected={wrapper.element.selected}
                {...this.additionalClasses(wrapper.element, context)}
            />
        );
    }

    protected additionalClasses(_node: Readonly<SShapeElement & Hoverable & Selectable>, _context: RenderingContext): Classes {
        return {};
    }

    protected renderPath(wrapper: Readonly<RoundedCornerWrapper>, _context: RenderingContext, inset: number): string {
        // Calcualte length of straight line segments
        const topLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.topLeft - wrapper.cornerRadius.topRight);
        const rightLineLength = Math.max(0, wrapper.size.height - wrapper.cornerRadius.topRight - wrapper.cornerRadius.bottomRight);
        const bottomLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.bottomLeft - wrapper.cornerRadius.bottomRight);

        const path =
            `M${0 + inset},${0 + wrapper.topLeftCorner.radiusY}` +
            `q${0},${-(wrapper.topLeftCorner.radiusY - inset)} ${wrapper.topLeftCorner.radiusX - inset},${-(
                wrapper.topLeftCorner.radiusY - inset
            )}` +
            `h${topLineLength}` +
            `q${wrapper.topRightCorner.radiusX - inset},0 ${wrapper.topRightCorner.radiusX - inset},${
                wrapper.topRightCorner.radiusY - inset
            }` +
            `v${rightLineLength}` +
            `q0,${wrapper.bottomRightCorner.radiusY - inset} ${-(wrapper.bottomRightCorner.radiusX - inset)},${
                wrapper.bottomRightCorner.radiusY - inset
            }` +
            `h${-bottomLineLength}` +
            `q${-(wrapper.bottomLeftCorner.radiusX - inset)},0 ${-(wrapper.bottomLeftCorner.radiusX - inset)},${-(
                wrapper.bottomLeftCorner.radiusY - inset
            )}` +
            'z ';
        return path;
    }
}

export function toClipPathId(node: Readonly<SShapeElement & Hoverable & Selectable>): string {
    return `${node.id}_clip_path`;
}
