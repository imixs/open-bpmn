/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import { isTaskNode } from '@imixs-bpmn/bpmn-glsp/lib/model';
import { NavigateAction } from '@eclipse-glsp/client';
import { GLSPCommandHandler, GLSPContextMenu } from '@eclipse-glsp/theia-integration';
import { CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry } from '@theia/core';
import { ApplicationShell } from '@theia/core/lib/browser';
import { inject, injectable } from '@theia/core/shared/inversify';

export namespace WorkflowNavigationCommands {
    export const NEXT_NODE = 'glsp-workflow-next-node';
    export const PREVIOUS_NODE = 'glsp-workflow-previous-node';
    export const DOCUMENTATION = 'glsp-workflow-documentation';
}

@injectable()
export class WorkflowNavigationCommandContribution implements CommandContribution {
    @inject(ApplicationShell) protected readonly shell: ApplicationShell;
    registerCommands(commands: CommandRegistry): void {
        commands.registerCommand(
            { id: WorkflowNavigationCommands.NEXT_NODE, label: 'Go to Next Node' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [new NavigateAction('next')],
                isEnabled: context => context.selectedElements.filter(isTaskNode).length === 1
            })
        );
        commands.registerCommand(
            { id: WorkflowNavigationCommands.PREVIOUS_NODE, label: 'Go to Previous Node' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [new NavigateAction('previous')],
                isEnabled: context => context.selectedElements.filter(isTaskNode).length === 1
            })
        );
        commands.registerCommand(
            { id: WorkflowNavigationCommands.DOCUMENTATION, label: 'Go to Documentation' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [new NavigateAction('documentation')],
                isEnabled: context => context.selectedElements.filter(isTaskNode).length === 1
            })
        );
    }
}

@injectable()
export class WorkflowNavigationMenuContribution implements MenuContribution {
    static readonly NAVIGATION = GLSPContextMenu.MENU_PATH.concat('navigate');
    registerMenus(menus: MenuModelRegistry): void {
        menus.registerMenuAction(WorkflowNavigationMenuContribution.NAVIGATION.concat('n'), {
            commandId: WorkflowNavigationCommands.NEXT_NODE,
            label: 'Next node'
        });
        menus.registerMenuAction(WorkflowNavigationMenuContribution.NAVIGATION.concat('n'), {
            commandId: WorkflowNavigationCommands.PREVIOUS_NODE,
            label: 'Previous node'
        });
        menus.registerMenuAction(WorkflowNavigationMenuContribution.NAVIGATION.concat('z'), {
            commandId: WorkflowNavigationCommands.DOCUMENTATION,
            label: 'Documentation'
        });
    }
}
