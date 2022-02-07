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
import { BoundsAware, RenderingContext, SChildElement, SCompartment, ShapeView, svg } from 'sprotty/lib';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class StructureCompartmentView extends ShapeView {
    render(model: Readonly<SCompartment & SChildElement & BoundsAware>, context: RenderingContext): VNode | undefined {
        if (!this.isVisible(model, context)) {
            return undefined;
        }

        return (
            <g>
                <rect class-sprotty-comp={true} class-debug={false} x='0' y='0' width={model.size.width} height={model.size.height}></rect>
                {context.renderChildren(model)}
            </g>
        );
    }
}
