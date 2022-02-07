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
import { injectable } from 'inversify';
import { RequestAction, ResponseAction } from '.';
import { isArray } from '../utils/typeguard-util';
import { Action, isActionKind } from './base-protocol';

/**
 * Validation in GLSP is performed by using validation markers. A marker represents the validation result for a single model element
 */
export interface Marker {
    /**
     * Short label describing this marker message, e.g., short validation message
     */
    readonly label: string;
    /**
     * Full description of this marker, e.g., full validation message
     */
    readonly description: string;
    /**
     * Id of the model element this marker refers to
     */
    readonly elementId: string;
    /**
     * Marker kind, e.g., info, warning, error or custom kind
     */
    readonly kind: string;
}

export namespace MarkerKind {
    export const INFO = 'info';
    export const WARNING = 'warning';
    export const ERROR = 'error';
}

/**
 * Action to retrieve markers for the specified model elements. Sent from the client to the server.
 */
export class RequestMarkersAction implements RequestAction<SetMarkersAction> {
    static readonly KIND = 'requestMarkers';
    constructor(
        public readonly elementsIDs: string[] = [],
        public readonly requestId = '',
        public readonly kind = RequestMarkersAction.KIND
    ) {}
}

export function isRequestMarkersAction(action: any): action is RequestMarkersAction {
    return isActionKind(action, RequestMarkersAction.KIND) && isArray(action, 'elementsIDs');
}

/**
 * Response to the {@link RequestMarkersAction} containing all validation markers. Sent from the server to the client.
 */
export class SetMarkersAction implements ResponseAction {
    static readonly KIND = 'setMarkers';
    constructor(public readonly markers: Marker[], public readonly responseId = '', public readonly kind = SetMarkersAction.KIND) {}
}

export function isSetMarkersAction(action: any): action is SetMarkersAction {
    return isActionKind(action, SetMarkersAction.KIND) && isArray(action, 'markers');
}

/**
 * Action for clearing makers of a model
 */
@injectable()
export class DeleteMarkersAction implements Action {
    static readonly KIND = 'deleteMarkers';
    constructor(public readonly markers: Marker[], public readonly kind = DeleteMarkersAction.KIND) {}
}

export function isDeleteMarkersAction(action: any): action is DeleteMarkersAction {
    return isActionKind(action, DeleteMarkersAction.KIND) && isArray(action, 'markers');
}
