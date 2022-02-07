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
import { NavigateToExternalTargetAction, NavigateToTargetAction } from '@eclipse-glsp/protocol';
import { ContainerModule } from 'inversify';
import { configureActionHandler } from 'sprotty';
import { NavigateAction, NavigationActionHandler, ProcessNavigationArgumentsAction } from './navigation-action-handler';
import { NavigationTargetResolver } from './navigation-target-resolver';

export const navigationModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(NavigationTargetResolver).toSelf().inSingletonScope();
    bind(NavigationActionHandler).toSelf().inSingletonScope();
    configureActionHandler({ bind, isBound }, NavigateAction.KIND, NavigationActionHandler);
    configureActionHandler({ bind, isBound }, NavigateToTargetAction.KIND, NavigationActionHandler);
    configureActionHandler({ bind, isBound }, ProcessNavigationArgumentsAction.KIND, NavigationActionHandler);
    configureActionHandler({ bind, isBound }, NavigateToExternalTargetAction.KIND, NavigationActionHandler);
});
