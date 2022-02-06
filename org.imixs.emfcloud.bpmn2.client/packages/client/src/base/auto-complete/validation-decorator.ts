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
import { codiconCSSString } from 'sprotty/lib/utils/codicon';
import { ValidationStatus } from '@eclipse-glsp/protocol';

export interface IValidationDecorator {
    decorateValidationResult(status: ValidationStatus): void;
    isValidatedOk(): boolean;
    invalidate(): void;
    dispose(): void;
}

export namespace IValidationDecorator {
    export const NO_DECORATION: IValidationDecorator = {
        // eslint-disable-next-line @typescript-eslint/no-empty-function
        decorateValidationResult(_status: ValidationStatus) {},
        isValidatedOk(): boolean {
            return false;
        },
        // eslint-disable-next-line @typescript-eslint/no-empty-function
        invalidate() {},
        // eslint-disable-next-line @typescript-eslint/no-empty-function
        dispose() {}
    };
}

export class ValidationDecorator implements IValidationDecorator {
    warningClasses = ['warning'];
    warningIconClasses = codiconCSSString('warning');
    errorClasses = ['error'];
    errorIconClasses = codiconCSSString('error');

    isValidated = false;
    hasValidationError = false;

    protected decorationDiv?: HTMLDivElement;

    constructor(protected containerElement: HTMLElement) {}

    decorateValidationResult(status: ValidationStatus): void {
        if (ValidationStatus.isError(status)) {
            this.hasValidationError = true;
            this.decorateError(status.message ? status.message : 'Error');
        } else if (ValidationStatus.isWarning(status)) {
            this.hasValidationError = false;
            this.decorateWarning(status.message ? status.message : 'Warning');
        } else {
            this.hasValidationError = false;
            this.dispose();
        }
        this.isValidated = true;
    }

    protected decorateError(message: string): void {
        this.switchCssClasses(this.containerElement, this.errorClasses);
        const div = this.createDecorationDiv();
        this.switchCssClasses(div, this.errorClasses);
        div.innerHTML = `<span class="${this.errorIconClasses}"></span> ${message}`;
        this.adjustPosition();
    }

    protected decorateWarning(message: string): void {
        this.switchCssClasses(this.containerElement, this.warningClasses);
        const div = this.createDecorationDiv();
        this.switchCssClasses(div, this.warningClasses);
        div.innerHTML = `<span class="${this.warningIconClasses}"></span> ${message}`;
        this.adjustPosition();
    }

    protected switchCssClasses(element: HTMLElement, cssClasses: string[]): void {
        element.classList.remove(...this.errorClasses, ...this.warningClasses);
        element.classList.add(...cssClasses);
    }

    protected createDecorationDiv(): HTMLDivElement {
        if (!this.decorationDiv) {
            this.containerElement.classList.add('validation');
            this.decorationDiv = document.createElement('div');
            this.decorationDiv.style.width = `${this.decorationContainerWidth()}px`;
            this.decorationDiv.classList.add('validation-decorator');
            this.containerElement.appendChild(this.decorationDiv);
        }
        return this.decorationDiv;
    }

    protected decorationContainerWidth(): number {
        return this.containerElement.clientWidth - 5;
    }

    protected adjustPosition(): void {
        if (this.decorationDiv) {
            const height = this.decorationDiv.clientHeight + 2;
            this.decorationDiv.style.top = `-${height}px`;
        }
    }

    isValidatedOk(): boolean {
        return this.isValidated && !this.hasValidationError;
    }

    invalidate(): void {
        this.isValidated = false;
    }

    dispose(): void {
        this.hasValidationError = false;
        this.isValidated = false;
        if (this.decorationDiv && this.containerElement && this.containerElement.contains(this.decorationDiv)) {
            this.containerElement.removeChild(this.decorationDiv);
            this.switchCssClasses(this.containerElement, []);
            this.decorationDiv = undefined;
        }
    }
}
