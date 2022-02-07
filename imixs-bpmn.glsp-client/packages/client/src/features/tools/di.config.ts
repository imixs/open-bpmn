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
import { TriggerEdgeCreationAction, TriggerNodeCreationAction } from '@eclipse-glsp/protocol';
import { ContainerModule, interfaces } from 'inversify';
import { configureActionHandler, configureModelElement } from 'sprotty';
import { GLSP_TYPES } from '../../base/types';
import { MARQUEE } from '../tool-feedback/marquee-tool-feedback';
import { ChangeBoundsTool } from './change-bounds-tool';
import { DelKeyDeleteTool, MouseDeleteTool } from './delete-tool';
import { EdgeCreationTool } from './edge-creation-tool';
import { EdgeEditTool } from './edge-edit-tool';
import { MarqueeMouseTool } from './marquee-mouse-tool';
import { MarqueeTool } from './marquee-tool';
import { MarqueeNode } from './model';
import { NodeCreationTool } from './node-creation-tool';
import { MarqueeView } from './view';

const toolsModule = new ContainerModule((bind, _unbind, isBound) => {
    // Register default tools
    bind(GLSP_TYPES.IDefaultTool).to(ChangeBoundsTool);
    bind(GLSP_TYPES.IDefaultTool).to(EdgeEditTool);
    bind(GLSP_TYPES.IDefaultTool).to(DelKeyDeleteTool);

    // Register  tools
    bind(GLSP_TYPES.ITool).to(MouseDeleteTool);
    bind(NodeCreationTool).toSelf().inSingletonScope();
    bind(EdgeCreationTool).toSelf().inSingletonScope();
    bind(GLSP_TYPES.ITool).toService(EdgeCreationTool);
    bind(GLSP_TYPES.ITool).toService(NodeCreationTool);

    configureMarqueeTool({ bind, isBound });
    configureActionHandler({ bind, isBound }, TriggerNodeCreationAction.KIND, NodeCreationTool);
    configureActionHandler({ bind, isBound }, TriggerEdgeCreationAction.KIND, EdgeCreationTool);
});

export function configureMarqueeTool(context: { bind: interfaces.Bind; isBound: interfaces.IsBound }): void {
    configureModelElement(context, MARQUEE, MarqueeNode, MarqueeView);
    context.bind(GLSP_TYPES.IDefaultTool).to(MarqueeTool);
    context.bind(GLSP_TYPES.ITool).to(MarqueeMouseTool);
}

export default toolsModule;
