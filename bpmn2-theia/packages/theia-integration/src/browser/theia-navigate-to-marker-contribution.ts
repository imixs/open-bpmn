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
import { collectIssueMarkers, NavigateToMarkerAction } from '@eclipse-glsp/client/lib';
import { CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry } from '@theia/core';
import { ApplicationShell, KeybindingContribution, KeybindingRegistry } from '@theia/core/lib/browser';
import { inject, injectable, interfaces } from '@theia/core/shared/inversify';
import { DiagramKeybindingContext } from 'sprotty-theia';

import { GLSPCommandHandler, GLSPContextMenu } from './diagram';

export function registerMarkerNavigationCommands(bind: interfaces.Bind): void {
    bind(CommandContribution).to(NavigateToMarkerCommandContribution);
    bind(MenuContribution).to(NavigateToMarkerMenuContribution);
    bind(KeybindingContribution).to(NavigateToMarkerKeybindingContribution);
}

export namespace NavigateToMarkerCommand {
    export const NEXT_MARKER = 'next-marker';
    export const PREVIOUS_MARKER = 'previous-marker';
}

@injectable()
export class NavigateToMarkerCommandContribution implements CommandContribution {
    @inject(ApplicationShell) protected readonly shell: ApplicationShell;

    registerCommands(commands: CommandRegistry): void {
        commands.registerCommand(
            { id: NavigateToMarkerCommand.NEXT_MARKER, label: 'Go to Next Marker', category: 'Diagram' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [new NavigateToMarkerAction('next')],
                isEnabled: context => collectIssueMarkers(context.modelRoot).length > 0
            })
        );
        commands.registerCommand(
            { id: NavigateToMarkerCommand.PREVIOUS_MARKER, label: 'Go to Previous Marker', category: 'Diagram' },
            new GLSPCommandHandler(this.shell, {
                actions: () => [new NavigateToMarkerAction('previous')],
                isEnabled: context => collectIssueMarkers(context.modelRoot).length > 0
            })
        );
    }
}

@injectable()
export class NavigateToMarkerMenuContribution implements MenuContribution {
    static readonly NAVIGATION = GLSPContextMenu.MENU_PATH.concat('navigate');

    registerMenus(menus: MenuModelRegistry): void {
        menus.registerSubmenu(NavigateToMarkerMenuContribution.NAVIGATION, 'Go to');
        menus.registerMenuAction(NavigateToMarkerMenuContribution.NAVIGATION.concat('m'), {
            commandId: NavigateToMarkerCommand.NEXT_MARKER,
            label: 'Next Marker'
        });
        menus.registerMenuAction(NavigateToMarkerMenuContribution.NAVIGATION.concat('m'), {
            commandId: NavigateToMarkerCommand.PREVIOUS_MARKER,
            label: 'Previous Marker'
        });
    }
}

@injectable()
export class NavigateToMarkerKeybindingContribution implements KeybindingContribution {
    @inject(DiagramKeybindingContext) protected readonly diagramKeybindingContext: DiagramKeybindingContext;

    registerKeybindings(keybindings: KeybindingRegistry): void {
        keybindings.registerKeybinding({
            command: NavigateToMarkerCommand.NEXT_MARKER,
            context: this.diagramKeybindingContext.id,
            keybinding: 'f8'
        });
        keybindings.registerKeybinding({
            command: NavigateToMarkerCommand.PREVIOUS_MARKER,
            context: this.diagramKeybindingContext.id,
            keybinding: 'shift+f8'
        });
    }
}
