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
import { isObject, isString } from '../utils/typeguard-util';
import { Action, generateRequestId, isActionKind, isRequestAction, Operation, RequestAction, ResponseAction } from './base-protocol';
import { Args } from './types';

/**
 * Requests the validation of the given text in the context of the provided model element. Typically sent from the client to the server.
 */
export class RequestEditValidationAction implements RequestAction<SetEditValidationResultAction> {
    static readonly KIND = 'requestEditValidation';
    constructor(
        public readonly contextId: string,
        public readonly modelElementId: string,
        public readonly text: string,
        public readonly requestId: string = generateRequestId(),
        public readonly kind: string = RequestEditValidationAction.KIND
    ) {}
}

export function isRequestEditValidationAction(action: any): action is RequestEditValidationAction {
    return (
        isRequestAction(action) &&
        action.kind === RequestEditValidationAction.KIND &&
        isString(action, 'contextId') &&
        isString(action, 'modelElementId') &&
        isString(action, 'text')
    );
}

/**
 * Response to a {@link RequestEditValidationAction} containing the validation result for applying a text on a certain model element.
 */
export class SetEditValidationResultAction implements ResponseAction {
    static readonly KIND = 'setEditValidationResult';
    constructor(
        public readonly status: ValidationStatus,
        public readonly responseId: string = '',
        public readonly args?: Args,
        public readonly kind: string = SetEditValidationResultAction.KIND
    ) {}
}

export function isSetEditValidationResultAction(action: Action): action is SetEditValidationResultAction {
    return isActionKind(action, SetEditValidationResultAction.KIND) && isObject(action, 'status') && isString(action, 'responseId');
}

export class ApplyLabelEditOperation implements Operation {
    static KIND = 'applyLabelEdit';
    kind = ApplyLabelEditOperation.KIND;
    constructor(readonly labelId: string, readonly text: string) {}
}

export function isApplyLabelEditOperation(action: any): action is ApplyLabelEditOperation {
    return isActionKind(action, ApplyLabelEditOperation.KIND) && isString(action, 'labelId') && isString(action, 'text');
}

export interface ValidationStatus {
    /**
     * The severity of the validation returned by the server.
     */
    readonly severity: ValidationStatus.Severity;

    /**
     * The validation status message which may be rendered in the view.
     */
    readonly message?: string;

    /**
     * A potential error that encodes more details.
     */
    readonly error?: ResponseError;
}

export interface ResponseError {
    /**
     * Code identifying the error kind.
     */
    readonly code: number;

    /**
     * Error message.
     */
    readonly message: string;

    /**
     * Additional custom data, e.g., a serialized stacktrace.
     */
    readonly data: Record<string, any>;
}

export namespace ValidationStatus {
    // eslint-disable-next-line no-shadow
    export enum Severity {
        FATAL,
        ERROR,
        WARNING,
        INFO,
        OK,
        // eslint-disable-next-line no-shadow
        NONE
    }

    export const NONE: ValidationStatus = {
        severity: Severity.NONE,
        message: '',
        error: { code: -1, message: '', data: {} }
    };

    export function isOk(validationStatus: ValidationStatus): boolean {
        return (
            validationStatus.severity === Severity.OK ||
            validationStatus.severity === Severity.INFO ||
            validationStatus.severity === Severity.NONE
        );
    }

    export function isWarning(validationStatus: ValidationStatus): boolean {
        return validationStatus.severity === Severity.WARNING;
    }

    export function isError(validationStatus: ValidationStatus): boolean {
        return validationStatus.severity === Severity.ERROR || validationStatus.severity === Severity.FATAL;
    }
}
