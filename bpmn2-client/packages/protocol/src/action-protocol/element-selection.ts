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
import { isArray, isBoolean } from '../utils/typeguard-util';
import { Action, isActionKind } from './base-protocol';

/**
 * Triggered when the user changes the selection, e.g. by clicking on a selectable element. The action should trigger a change in the
 * selected state accordingly, so the elements can be rendered differently. The server can send such an action to the client in order to
 *  change the selection remotely.
 */
export class SelectAction implements Action {
    static readonly KIND = 'elementSelected';
    kind = SelectAction.KIND;

    constructor(public readonly selectedElementsIDs: string[] = [], public readonly deselectedElementsIDs: string[] = []) {}
}

export function isSelectAction(action: any): action is SelectAction {
    return isActionKind(action, SelectAction.KIND) && isArray(action, 'selectedElementsIDs') && isArray(action, 'deselectedElementsIDs');
}

/**
 * Programmatic action for selecting or deselecting all elements.
 */
export class SelectAllAction implements Action {
    static readonly KIND = 'allSelected';
    kind = SelectAllAction.KIND;

    /**
     * If `select` is true, all elements are selected, otherwise they are deselected.
     */
    constructor(public readonly select: boolean = true) {}
}

export function isSelectAllAction(action: any): action is SelectAllAction {
    return isActionKind(action, SelectAllAction.KIND) && isBoolean(action, 'select');
}
