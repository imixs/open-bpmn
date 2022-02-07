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
import { InitializeResult, SetEditModeAction } from '@eclipse-glsp/protocol';
import '@vscode/codicons/dist/codicon.css';
import { Container, ContainerModule } from 'inversify';
import { ActionHandlerRegistry, configureActionHandler, configureCommand, ModelSource, SetModelCommand, TYPES } from 'sprotty';
import '../../css/glsp-sprotty.css';
import { GLSPActionDispatcher } from './action-dispatcher';
import { FocusStateChangedAction } from './actions/focus-change-action';
import { GLSPCommandStack } from './command-stack';
import { EditorContextService } from './editor-context-service';
import { FocusTracker } from './focus-tracker';
import { DefaultModelInitializationConstraint, ModelInitializationConstraint } from './model-initialization-constraint';
import { GLSPModelRegistry } from './model/model-registry';
import { FeedbackAwareUpdateModelCommand, SetModelActionHandler } from './model/update-model-command';
import { SelectionClearingMouseListener } from './selection-clearing-mouse-listener';
import { GLSPToolManager } from './tool-manager/glsp-tool-manager';
import { GLSP_TYPES } from './types';
import { GLSPViewRegistry } from './view/view-registry';

const defaultGLSPModule = new ContainerModule((bind, _unbind, isBound, rebind) => {
    const context = { bind, _unbind, isBound, rebind };
    bind(EditorContextService).toSelf().inSingletonScope();
    bind(GLSP_TYPES.IEditorContextServiceProvider).toProvider<EditorContextService>(
        ctx => () =>
            new Promise<EditorContextService>((resolve, reject) => {
                if (ctx.container.isBound(EditorContextService)) {
                    resolve(ctx.container.get<EditorContextService>(EditorContextService));
                } else {
                    reject();
                }
            })
    );

    configureActionHandler(context, SetEditModeAction.KIND, EditorContextService);

    bind(FocusTracker).toSelf().inSingletonScope();
    configureActionHandler(context, FocusStateChangedAction.KIND, FocusTracker);

    // Model update initialization ------------------------------------
    configureCommand(context, FeedbackAwareUpdateModelCommand);
    configureActionHandler(context, SetModelCommand.KIND, SetModelActionHandler);

    bind(TYPES.MouseListener).to(SelectionClearingMouseListener);

    rebind(TYPES.ICommandStack).to(GLSPCommandStack);
    bind(GLSPToolManager).toSelf().inSingletonScope();
    bind(GLSP_TYPES.IGLSPToolManager).toService(GLSPToolManager);
    rebind(TYPES.IToolManager).toService(GLSPToolManager);
    bind(GLSPActionDispatcher).toSelf().inSingletonScope();
    rebind(TYPES.IActionDispatcher).toService(GLSPActionDispatcher);

    bind(ModelInitializationConstraint).to(DefaultModelInitializationConstraint).inSingletonScope();

    // support re-registration of model elements and views
    rebind(TYPES.SModelRegistry).to(GLSPModelRegistry).inSingletonScope();
    rebind(TYPES.ViewRegistry).to(GLSPViewRegistry).inSingletonScope();
});

export default defaultGLSPModule;

/**
 * Utility function to configure the {@link ModelSource}, i.e. the `DiagramServer`, as action handler for all server actions for the given
 * diagramType.
 * @param result A promise that resolves after all server actions have been registered.
 * @param diagramType The diagram type.
 * @param container The di container.
 */
export async function configureServerActions(result: InitializeResult, diagramType: string, container: Container): Promise<void> {
    const modelSource = container.get<ModelSource>(TYPES.ModelSource);
    const actionHandlerRegistry = container.get<ActionHandlerRegistry>(ActionHandlerRegistry);
    const serverActions = result.serverActions[diagramType];
    if (serverActions.length === 0) {
        throw new Error(`No server-handled actions could be derived from the initialize result for diagramType: ${diagramType}!`);
    }
    serverActions.forEach(actionKind => actionHandlerRegistry.register(actionKind, modelSource));
}
