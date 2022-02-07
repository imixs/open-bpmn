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
import { Action, isAction, isLabeledAction, LabeledAction, ValidationStatus } from '@eclipse-glsp/protocol';
import { AutocompleteResult, AutocompleteSettings } from 'autocompleter';
import { codiconCSSClasses, ILogger, SModelRoot } from 'sprotty';
import { toArray } from 'sprotty/lib/utils/iterable';
import { matchesKeystroke } from 'sprotty/lib/utils/keyboard';
import { isSetAutoCompleteValueAction } from './auto-complete-actions';
import { IValidationDecorator } from './validation-decorator';

export interface AutoCompleteSettings {
    readonly noSuggestionsMessage?: string;
    readonly suggestionsClass?: string;
    readonly debounceWaitMs?: number;
    readonly showOnFocus?: boolean;
}

export interface InputValidator {
    validate(input: string): Promise<ValidationStatus>;
}

export interface SuggestionProvider {
    provideSuggestions(input: string): Promise<LabeledAction[]>;
}

export interface InputValueInitializer {
    initializeValue(containerElement: HTMLElement, root: Readonly<SModelRoot>, ...contextElementIds: string[]): string;
}

export interface SuggestionSubmitHandler {
    executeFromSuggestion(input: LabeledAction | Action | Action[]): void;
}

export interface TextSubmitHandler {
    executeFromTextOnlyInput(input: string): void;
}

// eslint-disable-next-line @typescript-eslint/no-var-requires
const configureAutocomplete: (settings: AutocompleteSettings<LabeledAction>) => AutocompleteResult = require('autocompleter');

export class AutoCompleteWidget {
    loadingIndicatorClasses = codiconCSSClasses('loading', false, true, ['loading']);

    protected containerElement: HTMLElement;
    protected inputElement: HTMLInputElement;
    protected loadingIndicator: HTMLSpanElement;
    protected autoCompleteResult: AutocompleteResult;
    protected contextActions?: LabeledAction[];
    protected previousContent?: string;

    protected inputValidator?: InputValidator;
    protected validationDecorator: IValidationDecorator = IValidationDecorator.NO_DECORATION;

    protected textSubmitHandler?: TextSubmitHandler;

    constructor(
        public autoSuggestionSettings: AutoCompleteSettings,
        public suggestionProvider: SuggestionProvider,
        public suggestionSubmitHandler: SuggestionSubmitHandler,
        // eslint-disable-next-line @typescript-eslint/no-empty-function
        protected notifyClose: () => void = () => {},
        protected logger?: ILogger
    ) {}

    configureValidation(
        inputValidator: InputValidator,
        validationDecorator: IValidationDecorator = IValidationDecorator.NO_DECORATION
    ): void {
        this.inputValidator = inputValidator;
        this.validationDecorator = validationDecorator;
    }

    configureTextSubmitHandler(textSubmitHandler: TextSubmitHandler): void {
        this.textSubmitHandler = textSubmitHandler;
    }

    initialize(containerElement: HTMLElement): void {
        this.containerElement = containerElement;
        this.inputElement = this.createInputElement();
        this.containerElement.appendChild(this.inputElement);
        this.containerElement.style.position = 'absolute';
    }

    protected createInputElement(): HTMLInputElement {
        const inputElement = document.createElement('input');
        inputElement.style.position = 'absolute';
        inputElement.spellcheck = false;
        inputElement.autocapitalize = 'false';
        inputElement.autocomplete = 'false';
        inputElement.style.width = '100%';
        inputElement.addEventListener('keydown', event => this.handleKeyDown(event));
        inputElement.addEventListener('blur', () => window.setTimeout(() => this.notifyClose(), 200));
        return inputElement;
    }

    protected handleKeyDown(event: KeyboardEvent): void {
        if (matchesKeystroke(event, 'Escape')) {
            this.notifyClose();
            return;
        }
        if (matchesKeystroke(event, 'Enter') && !this.isInputElementChanged() && this.isSuggestionAvailable()) {
            return;
        }
        if (this.isInputElementChanged()) {
            this.invalidateValidationResultAndContextActions();
        }
        if (!matchesKeystroke(event, 'Enter') || this.isSuggestionAvailable()) {
            return;
        }
        if (!this.validationDecorator.isValidatedOk()) {
            event.stopImmediatePropagation();
            return;
        }
        if (this.textSubmitHandler) {
            this.executeFromTextOnlyInput();
            this.notifyClose();
        }
    }

    protected isInputElementChanged(): boolean {
        return this.inputElement.value !== this.previousContent;
    }

    protected invalidateValidationResultAndContextActions(): void {
        this.contextActions = undefined;
        this.validationDecorator.invalidate();
    }

    open(root: Readonly<SModelRoot>, ...contextElementIds: string[]): void {
        this.contextActions = undefined;
        this.autoCompleteResult = configureAutocomplete(this.autocompleteSettings(root));
        this.previousContent = this.inputElement.value;
        this.inputElement.setSelectionRange(0, this.inputElement.value.length);
        this.inputElement.focus();
    }

    protected autocompleteSettings(root: Readonly<SModelRoot>): AutocompleteSettings<LabeledAction> {
        return {
            input: this.inputElement,
            emptyMsg: this.autoSuggestionSettings.noSuggestionsMessage,
            className: this.autoSuggestionSettings.suggestionsClass,
            showOnFocus: this.autoSuggestionSettings.showOnFocus,
            debounceWaitMs: this.autoSuggestionSettings.debounceWaitMs,
            minLength: -1,
            fetch: (text: string, update: (items: LabeledAction[]) => void) => this.updateSuggestions(update, text, root),
            onSelect: (item: LabeledAction) => this.onSelect(item),
            render: (item: LabeledAction, currentValue: string): HTMLDivElement | undefined => this.renderSuggestions(item, currentValue),
            customize: (input: HTMLInputElement, inputRect: ClientRect | DOMRect, container: HTMLDivElement, maxHeight: number) => {
                this.customizeInputElement(input, inputRect, container, maxHeight);
            }
        };
    }

    protected customizeInputElement(
        input: HTMLInputElement,
        inputRect: ClientRect | DOMRect,
        container: HTMLDivElement,
        maxHeight: number
    ): void {
        // move container into our UIExtension container as this is already positioned correctly
        if (this.containerElement) {
            this.containerElement.appendChild(container);
        }
    }

    protected updateSuggestions(
        update: (items: LabeledAction[]) => void,
        text: string,
        root: Readonly<SModelRoot>,
        ...contextElementIds: string[]
    ): void {
        this.onLoading();
        this.doUpdateSuggestions(text, root)
            .then(actions => {
                this.contextActions = this.filterActions(text, actions);
                update(this.contextActions);
                this.onLoaded('success');
            })
            .catch(reason => {
                if (this.logger) {
                    this.logger.error(this, 'Failed to obtain suggestions', reason);
                }
                this.onLoaded('error');
            });
    }

    protected onLoading(): void {
        if (this.loadingIndicator && this.containerElement.contains(this.loadingIndicator)) {
            return;
        }
        this.loadingIndicator = document.createElement('span');
        this.loadingIndicator.classList.add(...this.loadingIndicatorClasses);
        this.containerElement.appendChild(this.loadingIndicator);
    }

    protected doUpdateSuggestions(text: string, root: Readonly<SModelRoot>, ...contextElementIds: string[]): Promise<LabeledAction[]> {
        return this.suggestionProvider.provideSuggestions(text);
    }

    protected onLoaded(_success: 'success' | 'error'): void {
        if (this.containerElement.contains(this.loadingIndicator)) {
            this.containerElement.removeChild(this.loadingIndicator);
        }
        this.validationDecorator.invalidate();
        this.validateInputIfNoContextActions();
        this.previousContent = this.inputElement.value;
    }

    protected renderSuggestions(item: LabeledAction, value: string): HTMLDivElement {
        const itemElement = document.createElement('div');
        const wordMatcher = this.espaceForRegExp(value).split(' ').join('|');
        const regex = new RegExp(wordMatcher, 'gi');
        if (item.icon) {
            this.renderIcon(itemElement, item.icon);
        }
        itemElement.innerHTML += item.label.replace(regex, match => '<em>' + match + '</em>');
        return itemElement;
    }

    protected espaceForRegExp(value: string): string {
        return value.replace(/([.?*+^$[\]\\(){}|-])/g, '\\$1');
    }

    protected renderIcon(itemElement: HTMLDivElement, icon: string): void {
        itemElement.innerHTML += `<span class="icon ${icon}"></span>`;
    }

    protected filterActions(filterText: string, actions: LabeledAction[]): LabeledAction[] {
        return toArray(
            actions.filter(action => {
                const label = action.label.toLowerCase();
                const searchWords = filterText.split(' ');
                return searchWords.every(word => label.indexOf(word.toLowerCase()) !== -1);
            })
        );
    }

    protected onSelect(item: LabeledAction): void {
        if (isSetAutoCompleteValueAction(item)) {
            this.inputElement.value = item.text;
            // trigger update of suggestions with an keyup event
            window.setTimeout(() => this.inputElement.dispatchEvent(new Event('keyup')));
        } else {
            this.executeFromSuggestion(item);
            this.notifyClose();
        }
    }

    protected validateInputIfNoContextActions(): void {
        if (this.isNoOrExactlyOneMatchingContextAction()) {
            this.validateInput();
        } else {
            this.validationDecorator.dispose();
        }
    }

    private isNoOrExactlyOneMatchingContextAction(): boolean | undefined {
        return (
            !this.isSuggestionAvailable() ||
            (this.contextActions && this.contextActions.length === 1 && this.inputElement.value.endsWith(this.contextActions[0].label))
        );
    }

    protected isSuggestionAvailable(): boolean | undefined {
        return this.contextActions && this.contextActions.length > 0;
    }

    validateInput(): void {
        if (this.inputValidator) {
            this.inputValidator
                .validate(this.inputElement.value)
                .then(result => this.validationDecorator.decorateValidationResult(result))
                .catch(error => this.handleErrorDuringValidation(error));
        }
    }

    protected handleErrorDuringValidation(error: Error): void {
        if (this.logger) {
            this.logger.error(this, 'Failed to validate input', error);
        }
        this.validationDecorator.dispose();
    }

    protected executeFromSuggestion(input: LabeledAction | Action[] | Action): void {
        this.suggestionSubmitHandler.executeFromSuggestion(input);
    }

    protected executeFromTextOnlyInput(): void {
        if (this.textSubmitHandler) {
            this.textSubmitHandler.executeFromTextOnlyInput(this.inputElement.value);
        }
    }

    get inputField(): HTMLInputElement {
        return this.inputElement;
    }

    dispose(): void {
        this.validationDecorator.dispose();
        if (this.autoCompleteResult) {
            this.autoCompleteResult.destroy();
        }
    }
}

export function toActionArray(input: LabeledAction | Action[] | Action): Action[] {
    if (isLabeledAction(input)) {
        return input.actions;
    } else if (isAction(input)) {
        return [input];
    }
    return [];
}
