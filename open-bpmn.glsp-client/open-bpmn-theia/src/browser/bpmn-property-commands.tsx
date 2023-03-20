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

import { GLSPCommandHandler, GLSPContextMenu } from '@eclipse-glsp/theia-integration';
import { BPMNPropertyPanelToggleAction } from '@open-bpmn/open-bpmn-properties';
import { CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry } from '@theia/core';
import { ApplicationShell } from '@theia/core/lib/browser';
import { inject, injectable } from '@theia/core/shared/inversify';

/**
 * This module defines different commands and menu actions that fire a specific action.
 * These actions can be handled by the server or client part.
 *
 * Note! the command contributions must be registered in the BPMNTheiaFrontendModule
 *
 */
export namespace PropertyPanelCommands {
    export const PROPERTIES_TOGGLE = 'glsp-bpmn-properties-toggle';
}

@injectable()
export class BPMNPropertiesCommandContribution implements CommandContribution {
    @inject(ApplicationShell) protected readonly shell: ApplicationShell;
    registerCommands(commands: CommandRegistry): void {
        // register commands...
        commands.registerCommand(
            { id: PropertyPanelCommands.PROPERTIES_TOGGLE, label: 'BPMN Properties' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [BPMNPropertyPanelToggleAction.create()],
                isEnabled: context => context.selectedElements.length === 1
            })
        );
    }
}

@injectable()
export class BPMNPropertiesMenuContribution implements MenuContribution {
    // static readonly NAVIGATION = GLSPContextMenu.MENU_PATH.concat('navigate');
    // static readonly NIX = GLSPContextMenu.MENU_PATH;
    registerMenus(menus: MenuModelRegistry): void {

        menus.registerMenuAction(GLSPContextMenu.MENU_PATH.concat('z'), {
            commandId: PropertyPanelCommands.PROPERTIES_TOGGLE,
            label: 'BPMN Properties'
        });

    }
}
