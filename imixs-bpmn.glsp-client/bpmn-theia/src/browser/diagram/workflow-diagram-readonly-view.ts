/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
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
import { EditMode } from '@eclipse-glsp/client';
import { GLSPWidgetOpenerOptions } from '@eclipse-glsp/theia-integration/lib/browser';
import { Command, CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry, SelectionService } from '@theia/core';
import { OpenerService } from '@theia/core/lib/browser';
import URI from '@theia/core/lib/common/uri';
import { UriAwareCommandHandler } from '@theia/core/lib/common/uri-command-handler';
import { NavigatorContextMenu } from '@theia/navigator/lib/browser/navigator-contribution';
import { inject, injectable } from 'inversify';

export const OPEN_READONLY_DIAGRAM_VIEW: Command = {
    id: 'workflow.open.readonly',
    label: 'Open in Workflow Diagram Readonly View',
    category: '2_additional',
    iconClass: 'fa fa-project-diagram'
};
@injectable()
export class WorkflowDiagramReadonlyViewContribution implements CommandContribution, MenuContribution {
    @inject(OpenerService) protected readonly openerService: OpenerService;
    @inject(SelectionService) protected readonly selectionService: SelectionService;
    registerMenus(registry: MenuModelRegistry): void {
        registry.registerMenuAction(NavigatorContextMenu.OPEN_WITH, {
            commandId: OPEN_READONLY_DIAGRAM_VIEW.id,
            label: 'Workflow Diagram Readonly View',
            icon: OPEN_READONLY_DIAGRAM_VIEW.iconClass
        });
    }

    registerCommands(registry: CommandRegistry): void {
        registry.registerCommand(
            OPEN_READONLY_DIAGRAM_VIEW,
            new UriAwareCommandHandler(this.selectionService, {
                execute: async (uri: URI) => {
                    const openerOptions: GLSPWidgetOpenerOptions = { editMode: EditMode.READONLY };
                    const opener = await this.openerService.getOpener(uri, openerOptions);
                    await opener.open(uri, openerOptions);
                },
                isVisible: uri => uri.toString().endsWith('.wf'),
                isEnabled: uri => uri.toString().endsWith('.wf')
            })
        );
    }
}
