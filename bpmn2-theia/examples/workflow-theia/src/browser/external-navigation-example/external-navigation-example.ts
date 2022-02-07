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
import { CommandContribution, CommandRegistry } from '@theia/core';
import { open, OpenerService } from '@theia/core/lib/browser/opener-service';
import URI from '@theia/core/lib/common/uri';
import { inject, injectable } from '@theia/core/shared/inversify';
import { WorkspaceService } from '@theia/workspace/lib/browser/workspace-service';

@injectable()
export class ExampleNavigationCommandContribution implements CommandContribution {
    @inject(OpenerService) protected readonly openerService: OpenerService;
    @inject(WorkspaceService) protected readonly workspaceService: WorkspaceService;
    registerCommands(commands: CommandRegistry): void {
        commands.registerCommand(
            {
                id: 'navigate-to-element-via-query-example-command',
                label: 'Open example1.wf task "Push"'
            },
            {
                execute: args => {
                    if (this.workspaceService.workspace) {
                        const uri = new URI(this.workspaceService.workspace.resource + '/example1.wf');
                        open(this.openerService, uri, {
                            selection: { name: 'Push' }
                        });
                    }
                }
            }
        );
    }
}
