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
import { ContainerContext, GLSPClientContribution, GLSPTheiaFrontendModule } from '@eclipse-glsp/theia-integration';
import { CommandContribution, MenuContribution } from '@theia/core';
import { KeybindingContext, KeybindingContribution } from '@theia/core/lib/browser';
import { DiagramConfiguration } from 'sprotty-theia';

import { WorkflowLanguage } from '../common/workflow-language';
import { WorkflowDiagramConfiguration } from './diagram/workflow-diagram-configuration';
import { WorkflowDiagramReadonlyViewContribution } from './diagram/workflow-diagram-readonly-view';
import { WorkflowDiagramKeybindingContext, WorkflowKeybindingContribution } from './diagram/workflow-keybinding-contribution';
import { WorkflowNavigationCommandContribution, WorkflowNavigationMenuContribution } from './diagram/workflow-navigation-context-menu';
import { WorkflowTaskEditCommandContribution, WorkflowTaskEditMenuContribution } from './diagram/workflow-task-editing-context-menu';
import { ExampleNavigationCommandContribution } from './external-navigation-example/external-navigation-example';
import { WorkflowGLSPClientContribution } from './workflow-glsp-client-contribution';

export class WorkflowTheiaFrontendModule extends GLSPTheiaFrontendModule {
    protected enableCopyPaste = true;

    bindDiagramConfiguration(context: ContainerContext): void {
        context.bind(DiagramConfiguration).to(WorkflowDiagramConfiguration);
    }
    readonly diagramLanguage = WorkflowLanguage;

    configure(context: ContainerContext): void {
        // Custom workflow commands and menus
        context.bind(CommandContribution).to(WorkflowTaskEditCommandContribution);
        context.bind(MenuContribution).to(WorkflowTaskEditMenuContribution);
        context.bind(CommandContribution).to(WorkflowNavigationCommandContribution);
        context.bind(MenuContribution).to(WorkflowNavigationMenuContribution);
        context.bind(KeybindingContext).to(WorkflowDiagramKeybindingContext);
        context.bind(KeybindingContribution).to(WorkflowKeybindingContribution);

        // Example for a command that navigates to an element in a diagram with a query resolved by the server
        context.bind(CommandContribution).to(ExampleNavigationCommandContribution).inSingletonScope();

        // Readonly workflow diagram view
        context.bind(WorkflowDiagramReadonlyViewContribution).toSelf().inSingletonScope();
        context.bind(MenuContribution).toService(WorkflowDiagramReadonlyViewContribution);
        context.bind(CommandContribution).toService(WorkflowDiagramReadonlyViewContribution);
    }

    bindGLSPClientContribution(context: ContainerContext): void {
        context.bind(GLSPClientContribution).to(WorkflowGLSPClientContribution);
    }
}

export default new WorkflowTheiaFrontendModule();
