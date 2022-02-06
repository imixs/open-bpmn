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
import { ContainerModule } from 'inversify';
import { configureCommand, configureLayout, VBoxLayouter } from 'sprotty';
import { FreeFormLayouter } from './freeform-layout';
import { AlignElementsCommand, ResizeElementsCommand } from './layout-commands';
import { VBoxLayouterExt } from './vbox-layout';

const layoutCommandsModule = new ContainerModule((bind, _unbind, isBound, rebind) => {
    configureCommand({ bind, isBound }, ResizeElementsCommand);
    configureCommand({ bind, isBound }, AlignElementsCommand);

    rebind(VBoxLayouter).to(VBoxLayouterExt);
    configureLayout({ bind, isBound }, FreeFormLayouter.KIND, FreeFormLayouter);
});

export default layoutCommandsModule;
