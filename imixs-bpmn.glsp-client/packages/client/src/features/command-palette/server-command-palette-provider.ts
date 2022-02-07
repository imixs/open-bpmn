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
import { Action, isSetContextActionsAction, LabeledAction, Point, RequestContextActions } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { ICommandPaletteActionProvider, SModelElement, TYPES } from 'sprotty';
import { GLSPActionDispatcher } from '../../base/action-dispatcher';
import { EditorContextService } from '../../base/editor-context-service';

export namespace ServerCommandPalette {
    export const CONTEXT_ID = 'command-palette';
    export const TEXT = 'text';
    export const INDEX = 'index';
}

@injectable()
export class ServerCommandPaletteActionProvider implements ICommandPaletteActionProvider {
    @inject(TYPES.IActionDispatcher) protected actionDispatcher: GLSPActionDispatcher;
    @inject(EditorContextService) protected editorContext: EditorContextService;

    getActions(_root: Readonly<SModelElement>, text: string, _lastMousePosition?: Point, index?: number): Promise<LabeledAction[]> {
        const requestAction = new RequestContextActions(
            ServerCommandPalette.CONTEXT_ID,
            this.editorContext.get({
                [ServerCommandPalette.TEXT]: text,
                [ServerCommandPalette.INDEX]: index ? index : 0
            })
        );
        return this.actionDispatcher.requestUntil(requestAction).then(response => this.getPaletteActionsFromResponse(response));
    }

    getPaletteActionsFromResponse(action: Action): LabeledAction[] {
        if (isSetContextActionsAction(action)) {
            return action.actions;
        }
        return [];
    }
}
