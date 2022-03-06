/********************************************************************************
 * Copyright (C) 2019 Ericsson and others.
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

import { ContainerModule, interfaces } from 'inversify';
import { CustomGettingStartedWidget } from './custom-getting-started-widget';
import { WidgetFactory } from '@theia/core/lib/browser/widget-manager';
import { GettingStartedWidget } from '@theia/getting-started/lib/browser/getting-started-widget';

/**
 * DI decouples a component from creating its dependencies. Instead, it gets them injected on creation (as parameters of a constructor).
 * A DI container does the creation for you, based on some configuration you provide on startup through so-called container modules.
 */
export default new ContainerModule((bind: interfaces.Bind) => {
    // Bind the `CustomGettingStartedWidget` to itself.
    bind(CustomGettingStartedWidget).toSelf();
    bind(WidgetFactory).toDynamicValue(context => ({
        id: GettingStartedWidget.ID, // Re-use the pre-existing `GettingStartedWidget` ID.
        // On creation, create the `CustomGettingStartedWidget` instead.
        createWidget: () => context.container.get<CustomGettingStartedWidget>(CustomGettingStartedWidget),
    })).inSingletonScope();
});