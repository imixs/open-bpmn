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
import { configureActionHandler } from 'sprotty';
import { GLSP_TYPES } from '../../base/types';
import {
    CopyPasteContextMenuItemProvider,
    InvokeCopyAction,
    InvokeCopyPasteActionHandler,
    InvokeCutAction,
    InvokePasteAction
} from './copy-paste-context-menu';
import { LocalClipboardService, ServerCopyPasteHandler } from './copy-paste-handler';

export const glspServerCopyPasteModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(GLSP_TYPES.ICopyPasteHandler).to(ServerCopyPasteHandler);
    bind(GLSP_TYPES.IAsyncClipboardService).to(LocalClipboardService).inSingletonScope();
});

/**
 * This module is not required if the diagram is deployed in Theia but only intended to be used
 * in a standalone deployment of GLSP. If the GLSP diagram in Theia use the Theia-native
 * `CopyPasteMenuContribution` in `glsp-theia-integration` instead.
 */
export const copyPasteContextMenuModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(GLSP_TYPES.IContextMenuProvider).to(CopyPasteContextMenuItemProvider).inSingletonScope();
    bind(InvokeCopyPasteActionHandler).toSelf().inSingletonScope();
    configureActionHandler({ bind, isBound }, InvokeCopyAction.KIND, InvokeCopyPasteActionHandler);
    configureActionHandler({ bind, isBound }, InvokeCutAction.KIND, InvokeCopyPasteActionHandler);
    configureActionHandler({ bind, isBound }, InvokePasteAction.KIND, InvokeCopyPasteActionHandler);
});
