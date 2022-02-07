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
import {
    Action,
    GLSPServerStatusAction,
    isModelSourceChangedAction,
    ModelSourceChangedAction,
    ServerMessageAction
} from '@eclipse-glsp/protocol';
import { inject, injectable, optional } from 'inversify';
import { IActionDispatcher, IActionHandler, TYPES, ViewerOptions } from 'sprotty';

/**
 * An external handler of the model source change event.
 *
 * This allows external applications to react specifically to this event, e.g. by bringing up the diagram,
 * check their dirty state, show a dialog, etc.
 */
@injectable()
export abstract class ExternalModelSourceChangedHandler {
    /**
     * Notifies about a change of the model source.
     * @returns actions to be dispatched after the notification.
     */
    abstract notifyModelSourceChange(modelSourceName: string, options: ViewerOptions): Promise<Action[]>;
}

@injectable()
export class ModelSourceChangedActionHandler implements IActionHandler {
    @inject(TYPES.IActionDispatcher)
    protected dispatcher: IActionDispatcher;

    @inject(TYPES.ViewerOptions)
    protected options: ViewerOptions;

    @inject(ExternalModelSourceChangedHandler)
    @optional()
    protected externalModelSourceChangedHandler?: ExternalModelSourceChangedHandler;

    handle(action: Action): void {
        if (isModelSourceChangedAction(action)) {
            if (this.externalModelSourceChangedHandler) {
                this.externalModelSourceChangedHandler
                    .notifyModelSourceChange(action.modelSourceName, this.options)
                    .then(actions => this.dispatcher.dispatchAll(actions));
                return;
            }
            this.showSimpleNotification(action);
        }
    }

    protected showSimpleNotification(action: ModelSourceChangedAction): void {
        const message = `The model source ${action.modelSourceName} has changed. You might want to consider reloading.`;
        const timeout = 0;
        const severity = 'WARNING';
        this.dispatcher.dispatchAll([
            { kind: GLSPServerStatusAction.KIND, severity, timeout, message } as GLSPServerStatusAction,
            { kind: ServerMessageAction.KIND, severity, timeout, message } as ServerMessageAction
        ]);
    }
}
