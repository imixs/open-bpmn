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
import { isArray, isObject, isString } from '../utils/typeguard-util';
import { Action, generateRequestId, isActionKind, RequestAction, ResponseAction } from './base-protocol';
import { Args, EditorContext } from './types';

/**
 *  A NavigationTarget identifies the object we want to navigate to via its uri and may further provide a label to display for the client.
 */
export interface NavigationTarget {
    uri: string;
    label?: string;
    args?: Args;
}

export namespace NavigationTarget {
    export const ELEMENT_IDS = 'elementIds';
    export const ELEMENT_IDS_SEPARATOR = '&';
    export const TEXT_LINE = 'line';
    export const TEXT_COLUMN = 'column';

    export interface TextPosition {
        line: number;
        character: number;
    }

    export function hasArguments(target: NavigationTarget): boolean {
        return target.args !== undefined && Object.keys(target.args).length > 0;
    }

    export function addArgument(target: NavigationTarget, key: string, value: string | number | boolean): void {
        if (target.args === undefined) {
            target.args = {};
        }
        target.args[key] = value;
    }

    export function getElementIds(target: NavigationTarget): string[] {
        if (target.args === undefined || target.args[ELEMENT_IDS] === undefined) {
            return [];
        }
        const elementIdsValue = target.args[ELEMENT_IDS].toString();
        return elementIdsValue.split(ELEMENT_IDS_SEPARATOR);
    }

    export function setElementIds(target: NavigationTarget, elementIds: string[]): string {
        if (target.args === undefined) {
            target.args = {};
        }
        return (target.args[ELEMENT_IDS] = elementIds.join(ELEMENT_IDS_SEPARATOR));
    }

    export function setTextPosition(target: NavigationTarget, position: TextPosition | undefined): void {
        if (position) {
            if (target.args === undefined) {
                target.args = {};
            }
            target.args[TEXT_LINE] = position.line;
            target.args[TEXT_COLUMN] = position.character;
        }
    }

    export function getTextPosition(target: NavigationTarget): TextPosition | undefined {
        if (target.args === undefined || target.args[TEXT_LINE] === undefined || target.args[TEXT_COLUMN] === undefined) {
            return undefined;
        }
        return {
            line: Number(target.args[TEXT_LINE]),
            character: Number(target.args[TEXT_COLUMN])
        };
    }
}

/**
 * Action that is usually sent from the client to the server to request navigation targets for a specific navigation type such as
 * documentation or implementation in the given editor context.
 */
export class RequestNavigationTargetsAction implements RequestAction<SetNavigationTargetsAction> {
    static readonly KIND = 'requestNavigationTargets';
    kind = RequestNavigationTargetsAction.KIND;
    constructor(readonly targetTypeId: string, readonly editorContext: EditorContext, readonly requestId: string = generateRequestId()) {}
}

export function isRequestNavigationTargetsAction(action: any): action is RequestNavigationTargetsAction {
    return (
        isActionKind(action, RequestNavigationTargetsAction.KIND) &&
        isString(action, 'targetTypeId') &&
        isObject(action, 'editorContext') &&
        isString(action, 'requestId')
    );
}

/**
 * Response action from the server following a {@link RequestNavigationTargetsAction}. It contains all available navigation targets for the
 * queried target type in the provided editor context. The server may also provide additional information using the arguments, e.g.,
 *  warnings, that can be interpreted by the client.
 */
export class SetNavigationTargetsAction implements ResponseAction {
    static readonly KIND = 'setNavigationTargets';
    kind = SetNavigationTargetsAction.KIND;
    constructor(readonly targets: NavigationTarget[], readonly responseId: string = '', readonly args?: Args) {}
}

export function isSetNavigationTargetsAction(action: any): action is SetNavigationTargetsAction {
    return isActionKind(action, SetNavigationTargetsAction.KIND) && isArray(action, 'targets') && isString(action, 'responseId');
}

/** Action that triggers the navigation to a particular navigation target, such as element IDs, queries, etc.. */
export class NavigateToTargetAction implements Action {
    static readonly KIND = 'navigateToTarget';
    readonly kind = NavigateToTargetAction.KIND;
    constructor(readonly target: NavigationTarget) {}
}

export function isNavigateToTargetAction(action: any): action is NavigateToTargetAction {
    return isActionKind(action, NavigateToTargetAction.KIND) && isObject(action, 'target');
}

/**
 * If a client cannot navigate to a target directly, a {@link ResolveNavigationTargetAction} may be sent to the server to resolve the
 * navigation target to one or more model elements. This may be useful in cases where the resolution of each target is expensive or the
 * client architecture requires an indirection.
 */
export class ResolveNavigationTargetAction implements RequestAction<SetResolvedNavigationTargetAction> {
    static readonly KIND = 'resolveNavigationTarget';
    kind = ResolveNavigationTargetAction.KIND;
    constructor(readonly navigationTarget: NavigationTarget, public readonly requestId: string = generateRequestId()) {}
}

export function isResolveNavigationTargetAction(action: any): action is ResolveNavigationTargetAction {
    return (
        isActionKind(action, ResolveNavigationTargetAction.KIND) && isObject(action, 'navigationTarget') && isString(action, 'requestId')
    );
}

/**
 * An action sent from the server in response to a {@link ResolveNavigationTargetAction}. The response contains the resolved element ids
 * for the given target and may contain additional information in the args property.
 */
export class SetResolvedNavigationTargetAction implements ResponseAction {
    static readonly KIND = 'setResolvedNavigationTarget';
    kind = SetResolvedNavigationTargetAction.KIND;
    constructor(readonly elementIds: string[] = [], readonly args?: Args, readonly responseId: string = '') {}
}

export function isSetResolvedNavigationTargets(action: any): action is SetResolvedNavigationTargetAction {
    return isActionKind(action, SetResolvedNavigationTargetAction.KIND) && isArray(action, 'elementIds') && isString(action, 'responseId');
}

/**
 * If a {@link NavigationTarget} cannot be resolved or the resolved target is something that is not part of our model source, e.g.,
 * a separate documentation file, a {@link NavigateToExternalTargetAction} may be sent. Since the target it outside of the model scope such
 * an action would be typically handled by an integration layer (such as the surrounding IDE).
 */
export class NavigateToExternalTargetAction implements Action {
    static readonly KIND = 'navigateToExternalTarget';
    readonly kind = NavigateToExternalTargetAction.KIND;
    constructor(readonly target: NavigationTarget) {}
}

export function isNavigateToExternalTargetAction(action: any): action is NavigateToExternalTargetAction {
    return isActionKind(action, NavigateToExternalTargetAction.KIND) && isObject(action, 'target');
}
