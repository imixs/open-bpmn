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
import { isBoolean, isString } from '../utils/typeguard-util';
import { Action, isActionKind, ResponseAction } from './base-protocol';

export class SaveModelAction implements Action {
    static readonly KIND = 'saveModel';
    constructor(public readonly fileUri?: string, public readonly kind: string = SaveModelAction.KIND) {}
}

export function isSaveModelAction(action?: any): action is SaveModelAction {
    return isActionKind(action, SaveModelAction.KIND);
}

export class SetDirtyStateAction implements Action {
    static readonly KIND = 'setDirtyState';
    constructor(public readonly isDirty: boolean, public readonly reason?: string, public readonly kind = SetDirtyStateAction.KIND) {}
}

export namespace DirtyStateChangeReason {
    export const OPERATION = 'operation';
    export const UNDO = 'undo';
    export const REDO = 'redo';
    export const SAVE = 'save';
    export const EXTERNAL = 'external';
}

export function isSetDirtyStateAction(action?: any): action is SetDirtyStateAction {
    return isActionKind(action, SetDirtyStateAction.KIND) && isBoolean(action, 'isDirty');
}

export class ExportSvgAction implements ResponseAction {
    static KIND = 'exportSvg';
    kind = ExportSvgAction.KIND;

    constructor(public readonly svg: string, public readonly responseId: string = '') {}
}

export function isExportSvgAction(action?: any): action is ExportSvgAction {
    return isActionKind(action, ExportSvgAction.KIND) && isString(action, 'svg');
}
