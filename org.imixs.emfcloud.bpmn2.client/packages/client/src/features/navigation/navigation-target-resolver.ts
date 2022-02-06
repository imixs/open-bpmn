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
    isSetResolvedNavigationTargets,
    NavigationTarget,
    ResolveNavigationTargetAction,
    SetResolvedNavigationTargetAction
} from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { IActionDispatcher, ILogger, ResponseAction, TYPES } from 'sprotty';
import { EditorContextServiceProvider } from '../../base/editor-context-service';
import { GLSP_TYPES } from '../../base/types';

/**
 * Resolves `NavigationTargets` to element ids.
 *
 * If the `NavigationTarget` doesn't have element ids itself, this resolver queries the server via a
 * `ResolveNavigationTargetAction` for element ids.
 */
@injectable()
export class NavigationTargetResolver {
    @inject(GLSP_TYPES.IEditorContextServiceProvider) protected editorContextService: EditorContextServiceProvider;
    @inject(TYPES.IActionDispatcher) protected dispatcher: IActionDispatcher;
    @inject(TYPES.ILogger) protected readonly logger: ILogger;

    async resolve(navigationTarget: NavigationTarget): Promise<SetResolvedNavigationTargetAction | undefined> {
        const contextService = await this.editorContextService();
        const sourceUri = await contextService.getSourceUri();
        return this.resolveWithSourceUri(sourceUri, navigationTarget);
    }

    async resolveWithSourceUri(
        sourceUri: string | undefined,
        target: NavigationTarget
    ): Promise<SetResolvedNavigationTargetAction | undefined> {
        const targetUri = decodeURIComponent(target.uri);
        if (sourceUri && sourceUri !== targetUri && `file://${sourceUri}` !== targetUri) {
            // different URI, so we can't resolve it locally
            this.logger.info("Source and Target URI are different. Can't resolve locally.", sourceUri, targetUri);
            return undefined;
        }
        if (NavigationTarget.getElementIds(target).length > 0) {
            return new SetResolvedNavigationTargetAction(NavigationTarget.getElementIds(target));
        }
        const response = await this.requestResolution(target);
        if (isSetResolvedNavigationTargets(response)) {
            return response;
        }
        return undefined;
    }

    protected requestResolution(target: NavigationTarget): Promise<ResponseAction> {
        return this.dispatcher.request(new ResolveNavigationTargetAction(target));
    }
}
