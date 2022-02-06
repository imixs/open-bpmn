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
import { VNode } from 'snabbdom';
import { RectangularNodeView, RenderingContext, svg } from 'sprotty';

import { MarqueeNode } from './model';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class MarqueeView extends RectangularNodeView {
    render(node: MarqueeNode, context: RenderingContext): VNode {
        const graph = (
            <g>
                <rect
                    class-sprotty-node={true}
                    class-marquee={true}
                    x={node.startPoint.x - node.endPoint.x <= 0 ? node.startPoint.x : node.endPoint.x}
                    y={node.startPoint.y - node.endPoint.y <= 0 ? node.startPoint.y : node.endPoint.y}
                    rx={0}
                    ry={0}
                    width={Math.abs(node.startPoint.x - node.endPoint.x)}
                    height={Math.abs(node.startPoint.y - node.endPoint.y)}
                ></rect>
            </g>
        );
        return graph;
    }
}
