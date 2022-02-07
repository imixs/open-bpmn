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
import { GLSPDiagramWidget } from '@eclipse-glsp/theia-integration';
import { ApplicationShell, KeybindingContext, KeybindingContribution, KeybindingRegistry } from '@theia/core/lib/browser';
import { inject, injectable } from '@theia/core/shared/inversify';

import { WorkflowLanguage } from '../../common/workflow-language';
import { WorkflowNavigationCommands } from './workflow-navigation-context-menu';

@injectable()
export class WorkflowDiagramKeybindingContext implements KeybindingContext {
    static readonly ID = 'workflow-diagram-keybinding-context';
    id = WorkflowDiagramKeybindingContext.ID;
    @inject(ApplicationShell) protected readonly shell: ApplicationShell;
    isEnabled(): boolean {
        return this.shell.activeWidget instanceof GLSPDiagramWidget && this.shell.activeWidget.diagramType === WorkflowLanguage.diagramType;
    }
}

@injectable()
export class WorkflowKeybindingContribution implements KeybindingContribution {
    registerKeybindings(registry: KeybindingRegistry): void {
        registry.registerKeybinding({
            command: WorkflowNavigationCommands.NEXT_NODE,
            context: WorkflowDiagramKeybindingContext.ID,
            keybinding: 'ctrl-4',
            when: 'glspEditorHasSelectionOfType == task:automated || glspEditorHasSelectionOfType == task:manual'
        });
        registry.registerKeybinding({
            command: WorkflowNavigationCommands.PREVIOUS_NODE,
            context: WorkflowDiagramKeybindingContext.ID,
            keybinding: 'ctrl-3',
            when: 'glspEditorHasSelectionOfType == task:automated || glspEditorHasSelectionOfType == task:manual'
        });
    }
}
