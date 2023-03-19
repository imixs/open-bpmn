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
import { CommandContribution, MenuContribution } from '@theia/core';
import { DiagramConfiguration } from 'sprotty-theia';
import { BPMNLanguage } from '../common/bpmn-language';
import { BPMNGLSPClientContribution } from './bpmn-glsp-client-contribution';
import { BPMNPropertiesCommandContribution, BPMNPropertiesMenuContribution } from './bpmn-property-commands';
import { BPMNDiagramConfiguration } from './diagram/bpmn-diagram-configuration';

export class BPMNTheiaFrontendModule extends GLSPTheiaFrontendModule {
    protected override enableCopyPaste = true;
    readonly diagramLanguage = BPMNLanguage;

    bindDiagramConfiguration(context: ContainerContext): void {
        context.bind(DiagramConfiguration).to(BPMNDiagramConfiguration);
    }

    override configure(context: ContainerContext): void {
        // Custom workflow commands and menus
        context.bind(CommandContribution).to(BPMNPropertiesCommandContribution);
        context.bind(MenuContribution).to(BPMNPropertiesMenuContribution);

    }

    override bindGLSPClientContribution(context: ContainerContext): void {
        context.bind(GLSPClientContribution).to(BPMNGLSPClientContribution);
    }
}

export default new BPMNTheiaFrontendModule();

