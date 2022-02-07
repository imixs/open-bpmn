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
 * Centers the viewport on the elements with the given identifiers. It changes the scroll setting of the viewport accordingly and resets
 * the zoom to its default. This action can also be created on the client but it can also be sent by the server in order to perform such
 *  a viewport change remotely.
 */
export class CenterAction implements Action {
    static readonly KIND = 'center';
    readonly kind = CenterAction.KIND;

    constructor(
        public readonly elementIds: string[],
        public readonly animate: boolean = true,
        public readonly retainZoom: boolean = false
    ) {}
}

export function isCenterAction(action: any): action is CenterAction {
    return isActionKind(action, CenterAction.KIND) && isBoolean(action, 'animate') && isBoolean(action, 'retainZoom');
}

export class FitToScreenAction implements Action {
    static readonly KIND = 'fit';
    readonly kind = FitToScreenAction.KIND;

    constructor(
        public readonly elementIds: string[],
        public readonly padding?: number,
        public readonly maxZoom?: number,
        public readonly animate: boolean = true
    ) {}
}

export function isFitToScreenAction(action: any): action is FitToScreenAction {
    return isActionKind(action, FitToScreenAction.KIND) && isArray(action, 'elementIds') && isBoolean(action, 'animate');
}
