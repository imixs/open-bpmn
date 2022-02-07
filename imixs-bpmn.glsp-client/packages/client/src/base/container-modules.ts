/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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

import { Container, ContainerModule } from 'inversify';
import {
    boundsModule,
    buttonModule,
    defaultModule,
    edgeIntersectionModule,
    edgeLayoutModule,
    expandModule,
    exportModule,
    fadeModule,
    labelEditUiModule,
    modelSourceModule,
    openModule,
    routingModule,
    zorderModule
} from 'sprotty';
import glspCommandPaletteModule from '../features/command-palette/di.config';
import glspContextMenuModule from '../features/context-menu/di.config';
import { glspServerCopyPasteModule } from '../features/copy-paste/di.config';
import glspDecorationModule from '../features/decoration/di.config';
import glspEditLabelModule from '../features/edit-label/di.config';
import modelHintsModule from '../features/hints/di.config';
import glspHoverModule from '../features/hover/di.config';
import layoutCommandsModule from '../features/layout/di.config';
import modelSourceWatcherModule from '../features/model-source-watcher/di.config';
import glspMouseToolModule from '../features/mouse-tool/di.config';
import { navigationModule } from '../features/navigation/di.config';
import glspSelectModule from '../features/select/di.config';
import toolFeedbackModule from '../features/tool-feedback/di.config';
import toolPaletteModule from '../features/tool-palette/di.config';
import toolsModule from '../features/tools/di.config';
import { markerNavigatorModule, validationModule } from '../features/validation/di.config';
import glspViewportModule from '../features/viewport/di.config';
import defaultGLSPModule from './di.config';

export const DEFAULT_MODULES = [
    defaultModule,
    defaultGLSPModule,
    boundsModule,
    buttonModule,
    edgeIntersectionModule,
    edgeLayoutModule,
    expandModule,
    exportModule,
    fadeModule,
    glspCommandPaletteModule,
    glspContextMenuModule,
    glspDecorationModule,
    glspEditLabelModule,
    glspHoverModule,
    glspMouseToolModule,
    glspSelectModule,
    glspServerCopyPasteModule,
    glspViewportModule,
    labelEditUiModule,
    layoutCommandsModule,
    markerNavigatorModule,
    modelHintsModule,
    modelSourceModule,
    modelSourceWatcherModule,
    navigationModule,
    openModule,
    toolPaletteModule,
    routingModule,
    toolFeedbackModule,
    toolsModule,
    validationModule,
    zorderModule
];

/**
 * Creates a GLSP Client container with the GLSP default modules and the specified custom `modules`.
 *
 * You can still customize the default modules in two ways.
 *
 * First, you can unload default modules and load them again with your custom code.
 *
 * ```typescript
 * const container = createClientContainer(myModule1, myModule2);
 * container.unload(modelSourceWatcherModule);
 * container.load(myModelSourceWatcherModule);
 * ```
 *
 * Second, you can unbind or rebind implementations that are originally bound in one of the default modules.
 *
 * ```typescript
 * rebind(NavigationTargetResolver).to(MyNavigationTargetResolver);
 * ```
 * @param modules Custom modules to be loaded in addition to the default modules.
 * @returns The created container.
 */
export function createClientContainer(...modules: ContainerModule[]): Container {
    const container = new Container();
    container.load(...DEFAULT_MODULES, ...modules);
    return container;
}
