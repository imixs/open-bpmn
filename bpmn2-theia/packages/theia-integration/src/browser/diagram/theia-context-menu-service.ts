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
import { GLSP_TYPES, IActionDispatcher, TYPES } from '@eclipse-glsp/client';
import { Container } from '@theia/core/shared/inversify';
import { TheiaContextMenuService, TheiaSprottyContextMenu } from 'sprotty-theia/lib/sprotty/theia-sprotty-context-menu-service';

export const TheiaContextMenuServiceFactory = Symbol('TheiaContextMenuServiceFactory');

export namespace GLSPContextMenu {
    export const MENU_PATH = TheiaSprottyContextMenu.CONTEXT_MENU;
}

export function connectTheiaContextMenuService(container: Container, contextMenuServiceFactory: () => TheiaContextMenuService): void {
    const contextMenuService = contextMenuServiceFactory();
    container.bind(GLSP_TYPES.IContextMenuService).toConstantValue(contextMenuService);
    if (contextMenuService instanceof TheiaContextMenuService) {
        contextMenuService.connect(container.get<IActionDispatcher>(TYPES.IActionDispatcher));
    }
}
