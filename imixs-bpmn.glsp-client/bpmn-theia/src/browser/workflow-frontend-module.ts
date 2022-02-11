/********************************************************************************
 * Copyright (c) 2019-2020 EclipseSource and others.
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
    GLSPClientContribution,
    registerCopyPasteContextMenu,
    registerDiagramLayoutCommands,
    registerDiagramManager,
    registerMarkerNavigationCommands
} from '@eclipse-glsp/theia-integration/lib/browser';
import { CommandContribution, MenuContribution } from '@theia/core';
import { KeybindingContext, KeybindingContribution } from '@theia/core/lib/browser';
import { ContainerModule, interfaces } from 'inversify';
import { DiagramConfiguration } from 'sprotty-theia';

import { WorkflowDiagramConfiguration } from './diagram/workflow-diagram-configuration';
import { WorkflowDiagramManager } from './diagram/workflow-diagram-manager';
import { WorkflowDiagramReadonlyViewContribution } from './diagram/workflow-diagram-readonly-view';
import { WorkflowGLSPDiagramClient } from './diagram/workflow-glsp-diagram-client';
import { WorkflowDiagramKeybindingContext, WorkflowKeybindingContribution } from './diagram/workflow-keybinding-contribution';
import { WorkflowNavigationCommandContribution, WorkflowNavigationMenuContribution } from './diagram/workflow-navigation-context-menu';
import { WorkflowTaskEditCommandContribution, WorkflowTaskEditMenuContribution } from './diagram/workflow-task-editing-context-menu';
import { ExampleNavigationCommandContribution } from './external-navigation-example/external-navigation-example';
import { WorkflowGLSPClientContribution } from './language/workflow-glsp-client-contribution';

export default new ContainerModule((bind: interfaces.Bind) => {
    bind(WorkflowGLSPClientContribution).toSelf().inSingletonScope();
    bind(GLSPClientContribution).toService(WorkflowGLSPClientContribution);
    bind(DiagramConfiguration).to(WorkflowDiagramConfiguration).inSingletonScope();
    bind(WorkflowGLSPDiagramClient).toSelf().inSingletonScope();
    registerDiagramManager(bind, WorkflowDiagramManager);

    // Optional default commands and menus
    registerDiagramLayoutCommands(bind);
    registerCopyPasteContextMenu(bind);
    registerMarkerNavigationCommands(bind);

    // Custom workflow commands and menus
    bind(CommandContribution).to(WorkflowTaskEditCommandContribution).inSingletonScope();
    bind(MenuContribution).to(WorkflowTaskEditMenuContribution).inSingletonScope();
    bind(CommandContribution).to(WorkflowNavigationCommandContribution).inSingletonScope();
    bind(MenuContribution).to(WorkflowNavigationMenuContribution).inSingletonScope();
    bind(KeybindingContext).to(WorkflowDiagramKeybindingContext).inSingletonScope();
    bind(KeybindingContribution).to(WorkflowKeybindingContribution).inSingletonScope();

    // Example for a command that navigates to an element in a diagram with a query resolved by the server
    bind(CommandContribution).to(ExampleNavigationCommandContribution).inSingletonScope();

    // Readonly workflow diagram view
    bind(WorkflowDiagramReadonlyViewContribution).toSelf().inSingletonScope();
    bind(MenuContribution).toService(WorkflowDiagramReadonlyViewContribution);
    bind(CommandContribution).toService(WorkflowDiagramReadonlyViewContribution);
});
