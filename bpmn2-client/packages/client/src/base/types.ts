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
export const GLSP_TYPES = {
    IAsyncClipboardService: Symbol.for('IAsyncClipboardService'),
    ICommandPaletteActionProviderRegistry: Symbol.for('ICommandPaletteActionProviderRegistry'),
    IEditorContextServiceProvider: Symbol.for('IEditorContextProvider'),
    IFeedbackActionDispatcher: Symbol.for('IFeedbackActionDispatcher'),
    IToolFactory: Symbol.for('Factory<Tool>'),
    ITypeHintProvider: Symbol.for('ITypeHintProvider'),
    IMovementRestrictor: Symbol.for('IMovmementRestrictor'),
    SelectionService: Symbol.for('SelectionService'),
    SelectionListener: Symbol.for('SelectionListener'),
    SModelRootListener: Symbol.for('SModelRootListener'),
    MouseTool: Symbol.for('MouseTool'),
    IContextMenuService: Symbol.for('IContextMenuService'),
    IContextMenuServiceProvider: Symbol.for('IContextMenuServiceProvider'),
    IContextMenuProviderRegistry: Symbol.for('IContextMenuProviderRegistry'),
    IContextMenuProvider: Symbol.for('IContextMenuProvider'),
    ICopyPasteHandler: Symbol.for('ICopyPasteHandler'),
    IGLSPToolManager: Symbol.for('IGLSPToolManager'),
    ITool: Symbol.for('ITool'),
    IDefaultTool: Symbol.for('IDefaultTool'),
    IEditModeListener: Symbol.for('IEditModeListener'),
    LayoutRegistration: Symbol.for('LayoutRegistration'),
    IMarqueeBehavior: Symbol.for('IMarqueeBehavior')
};
