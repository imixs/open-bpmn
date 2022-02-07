/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
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
import { ContainerModule } from 'inversify';
import { configureCommand, TYPES } from 'sprotty';

import { GLSP_TYPES } from '../../base/types';
import {
    LeftToRightTopToBottomComparator,
    MarkerNavigator,
    MarkerNavigatorContextMenuItemProvider,
    MarkerNavigatorKeyListener,
    NavigateToMarkerCommand,
    SModelElementComparator
} from './marker-navigator';
import { ApplyMarkersCommand, DeleteMarkersCommand, SetMarkersCommand, ValidationFeedbackEmitter } from './validate';

export const validationModule = new ContainerModule((bind, _unbind, isBound) => {
    configureCommand({ bind, isBound }, SetMarkersCommand);
    configureCommand({ bind, isBound }, ApplyMarkersCommand);
    configureCommand({ bind, isBound }, DeleteMarkersCommand);
    bind(ValidationFeedbackEmitter).toSelf().inSingletonScope();
});

export const markerNavigatorModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(SModelElementComparator).to(LeftToRightTopToBottomComparator).inSingletonScope();
    bind(MarkerNavigator).toSelf().inSingletonScope();
    configureCommand({ bind, isBound }, NavigateToMarkerCommand);
});

/**
 * This module is not required if the diagram is deployed in Theia but only intended to be used
 * in a standalone deployment of GLSP. If the GLSP diagram is in Theia use the Theia-native
 * `registerMarkerNavigationCommands()` in `glsp-theia-integration` instead.
 */
export const markerNavigatorContextMenuModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(GLSP_TYPES.IContextMenuProvider).to(MarkerNavigatorContextMenuItemProvider).inSingletonScope();
    bind(TYPES.KeyListener).to(MarkerNavigatorKeyListener).inSingletonScope();
});
