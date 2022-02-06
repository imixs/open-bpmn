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
import { Action, isResponseAction, RequestAction, ResponseAction } from '@eclipse-glsp/protocol';
import { inject } from 'inversify';
import { ActionDispatcher } from 'sprotty';
import { ModelInitializationConstraint } from './model-initialization-constraint';

export class GLSPActionDispatcher extends ActionDispatcher {
    protected readonly timeouts: Map<string, NodeJS.Timeout> = new Map();
    protected initializedConstraint = false;
    @inject(ModelInitializationConstraint) protected initializationConstraint: ModelInitializationConstraint;

    initialize(): Promise<void> {
        return super.initialize().then(() => this.startModelInitialization());
    }

    startModelInitialization(): void {
        if (!this.initializedConstraint) {
            this.logger.log(this, 'Starting model initialization mode');
            this.initializationConstraint.onInitialized().then(() => this.logger.log(this, 'Model initialization completed'));
            this.initializedConstraint = true;
        }
    }

    onceModelInitialized(): Promise<void> {
        return this.initializationConstraint.onInitialized();
    }

    dispatch(action: Action): Promise<void> {
        const result = super.dispatch(action);
        this.initializationConstraint.notifyDispatched(action);
        return result;
    }

    protected handleAction(action: Action): Promise<void> {
        if (isResponseAction(action)) {
            // clear timeout
            const timeout = this.timeouts.get(action.responseId);
            if (timeout !== undefined) {
                clearTimeout(timeout);
                this.timeouts.delete(action.responseId);
            }

            // we might have reached a timeout, so we simply drop the response
            const deferred = this.requests.get(action.responseId);
            if (deferred === undefined) {
                this.logger.log(this, 'No matching request for response', action);
                return Promise.resolve();
            }
        }
        return super.handleAction(action);
    }

    /**
     * Dispatch a request and waits for a response until the timeout given in `timeoutMs` has
     * been reached. The returned promise is resolved when a response with matching identifier
     * is dispatched or when the timeout has been reached. That response is _not_ passed to the
     * registered action handlers. Instead, it is the responsibility of the caller of this method
     * to handle the response properly. For example, it can be sent to the registered handlers by
     * passing it again to the `dispatch` method.
     * If `rejectOnTimeout` is set to false (default) the returned promise will be resolved with
     * no value, otherwise it will be rejected.
     */
    requestUntil<Res extends ResponseAction>(action: RequestAction<Res>, timeoutMs = 2000, rejectOnTimeout = false): Promise<Res> {
        if (!action.requestId) {
            return Promise.reject(new Error('Request without requestId'));
        }

        const requestId = action.requestId;
        const timeout = setTimeout(() => {
            const deferred = this.requests.get(requestId);
            if (deferred !== undefined) {
                // cleanup
                clearTimeout(timeout);
                this.requests.delete(requestId);

                const notification = 'Request ' + requestId + ' (' + action + ') time out after ' + timeoutMs + 'ms.';
                if (rejectOnTimeout) {
                    deferred.reject(notification);
                } else {
                    this.logger.info(this, notification);
                    deferred.resolve();
                }
            }
        }, timeoutMs);
        this.timeouts.set(requestId, timeout);

        return super.request(action);
    }
}
