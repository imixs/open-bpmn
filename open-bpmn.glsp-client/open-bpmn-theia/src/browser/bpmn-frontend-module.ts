/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
import { DiagramConfiguration } from 'sprotty-theia';
import { BPMNLanguage } from '../common/bpmn-language';
import { BPMNDiagramConfiguration } from './diagram/bpmn-diagram-configuration';
import { BPMNGLSPClientContribution } from './bpmn-glsp-client-contribution';

export class BPMNTheiaFrontendModule extends GLSPTheiaFrontendModule {
    protected override enableCopyPaste = true;
    readonly diagramLanguage = BPMNLanguage;

    bindDiagramConfiguration(context: ContainerContext): void {
        context.bind(DiagramConfiguration).to(BPMNDiagramConfiguration);
    }
    override bindGLSPClientContribution(context: ContainerContext): void {
        context.bind(GLSPClientContribution).to(BPMNGLSPClientContribution);
    }
}

export default new BPMNTheiaFrontendModule();

// --
/*
export class WorkflowTheiaFrontendModule extends GLSPTheiaFrontendModule {
    protected override enableCopyPaste = true;

    bindDiagramConfiguration(context: ContainerContext): void {
        context.bind(DiagramConfiguration).to(WorkflowDiagramConfiguration);
    }
    readonly diagramLanguage = WorkflowLanguage;

    override configure(context: ContainerContext): void {
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

    override bindGLSPClientContribution(context: ContainerContext): void {
        context.bind(GLSPClientContribution).to(WorkflowGLSPClientContribution);
    }
}
*/

