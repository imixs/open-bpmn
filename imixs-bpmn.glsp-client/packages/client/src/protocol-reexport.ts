/********************************************************************************
 * Copyright (c) 2021 STMicroelectronics and others.
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

/**
 * Workaround for now to solve ambiguous exports in glsp-client. This module provides all glsp-only components.
 * i.e. typings that are not defined in sprotty
 */

export {
    CompoundOperation,
    isActionKind,
    isCompoundOperation,
    isRejectAction,
    Operation
} from '@eclipse-glsp/protocol/lib/action-protocol/base-protocol';
export * from '@eclipse-glsp/protocol/lib/action-protocol/client-notification';
export * from '@eclipse-glsp/protocol/lib/action-protocol/clipboard';
export * from '@eclipse-glsp/protocol/lib/action-protocol/contexts';
export * from '@eclipse-glsp/protocol/lib/action-protocol/edge-modification';
export * from '@eclipse-glsp/protocol/lib/action-protocol/element-creation';
export { isRequestPopupModelAction, isSetPopupModelAction } from '@eclipse-glsp/protocol/lib/action-protocol/element-hover';
export * from '@eclipse-glsp/protocol/lib/action-protocol/element-navigation';
export { isSelectAction, isSelectAllAction } from '@eclipse-glsp/protocol/lib/action-protocol/element-selection';
export * from '@eclipse-glsp/protocol/lib/action-protocol/element-text-editing';
export * from '@eclipse-glsp/protocol/lib/action-protocol/element-type-hints';
export * from '@eclipse-glsp/protocol/lib/action-protocol/element-validation';
export { LayoutOperation } from '@eclipse-glsp/protocol/lib/action-protocol/mode-layout';
export {
    isModelSourceChangedAction,
    isRequestModelAction,
    isSetModelAction,
    ModelSourceChangedAction
} from '@eclipse-glsp/protocol/lib/action-protocol/model-data';
export * from '@eclipse-glsp/protocol/lib/action-protocol/model-edit-mode';
export {
    DirtyStateChangeReason,
    isExportSvgAction,
    isSaveModelAction,
    isSetDirtyStateAction,
    SaveModelAction,
    SetDirtyStateAction
} from '@eclipse-glsp/protocol/lib/action-protocol/model-saving';
export * from '@eclipse-glsp/protocol/lib/action-protocol/node-modification';
export * from '@eclipse-glsp/protocol/lib/action-protocol/tool-palette';
export { Args, EditorContext, ElementAndRoutingPoints } from '@eclipse-glsp/protocol/lib/action-protocol/types';
export * from '@eclipse-glsp/protocol/lib/action-protocol/undo-redo';
export * from '@eclipse-glsp/protocol/lib/glsp-client';
export * from '@eclipse-glsp/protocol/lib/jsonrpc/base-jsonrpc-glsp-client';
export * from '@eclipse-glsp/protocol/lib/jsonrpc/glsp-jsonrpc-client';
export * from '@eclipse-glsp/protocol/lib/utils/array-util';
export * from '@eclipse-glsp/protocol/lib/utils/launch-util';
