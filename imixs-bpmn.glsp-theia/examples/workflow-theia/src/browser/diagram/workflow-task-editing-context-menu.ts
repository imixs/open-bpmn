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
import { TaskEditor } from '@imixs-bpmn/bpmn-glsp/lib/direct-task-editing/direct-task-editor';
import { isTaskNode } from '@imixs-bpmn/bpmn-glsp/lib/model';
import { SetUIExtensionVisibilityAction } from '@eclipse-glsp/client';
import { GLSPCommandHandler, GLSPContextMenu } from '@eclipse-glsp/theia-integration';
import { CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry } from '@theia/core';
import { ApplicationShell } from '@theia/core/lib/browser';
import { inject, injectable } from '@theia/core/shared/inversify';

export namespace WorkflowTaskEditingCommands {
    export const EDIT_TASK = 'glsp-workflow-edit-task';
}

@injectable()
export class WorkflowTaskEditCommandContribution implements CommandContribution {
    @inject(ApplicationShell) protected readonly shell: ApplicationShell;
    registerCommands(commands: CommandRegistry): void {
        commands.registerCommand(
            { id: WorkflowTaskEditingCommands.EDIT_TASK, label: 'Direct Edit Task' },
            new GLSPCommandHandler(this.shell, {
                actions: context => [new SetUIExtensionVisibilityAction(TaskEditor.ID, true, [context.selectedElements[0].id])],
                isEnabled: context => !context.isReadonly && context.selectedElements.filter(isTaskNode).length === 1
            })
        );
    }
}

@injectable()
export class WorkflowTaskEditMenuContribution implements MenuContribution {
    static readonly EDIT = GLSPContextMenu.MENU_PATH.concat('edit');
    registerMenus(menus: MenuModelRegistry): void {
        menus.registerMenuAction(WorkflowTaskEditMenuContribution.EDIT, { commandId: WorkflowTaskEditingCommands.EDIT_TASK });
    }
}
