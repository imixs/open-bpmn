/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
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
import { IViewArgs, RenderingContext, ShapeView, svg } from '@eclipse-glsp/sprotty';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { HelperLine, SelectionBounds } from './model';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class HelperLineView extends ShapeView {
    override render(model: HelperLine, _context: RenderingContext): VNode | undefined {
        return (
            <g>
                <line
                    data-alignment={model.lineType}
                    x1={model.startPoint.x}
                    y1={model.startPoint.y}
                    x2={model.endPoint.x}
                    y2={model.endPoint.y}
                    class-helper-line={true}
                />
            </g>
        );
    }
}

@injectable()
export class SelectionBoundsView extends ShapeView {
    override render(model: SelectionBounds, context: RenderingContext, args?: IViewArgs | undefined): VNode | undefined {
        if (!this.isVisible(model, context)) {
            return undefined;
        }
        return (
            <g>
                <rect
                    class-selection-bounds={true}
                    x={0}
                    y={0}
                    width={Math.max(model.size.width, 0)}
                    height={Math.max(model.size.height, 0)}
                />
            </g>
        );
    }
}
