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
import {
    AbstractUIExtension,
    Action,
    AutoCompleteWidget,
    EditorContextService,
    getAbsoluteClientBounds,
    GLSPActionDispatcher,
    ILogger,
    isSetContextActionsAction,
    isSetEditValidationResultAction,
    LabeledAction,
    RequestContextActions,
    RequestEditValidationAction,
    SModelElement,
    SModelRoot,
    toActionArray,
    ValidationDecorator,
    ValidationStatus,
    ViewerOptions
} from '@eclipse-glsp/client';
import { inject, injectable } from 'inversify';
import { TYPES } from 'sprotty/lib';
import { DOMHelper } from 'sprotty/lib/base/views/dom-helper';

import { isTaskNode, TaskNode } from '../model';

export class ApplyTaskEditOperation implements Action {
    static readonly KIND = 'applyTaskEdit';
    readonly kind = ApplyTaskEditOperation.KIND;
    constructor(readonly taskId: string, readonly expression: string) {}
}

@injectable()
export class TaskEditor extends AbstractUIExtension {
    static readonly ID = 'task-editor';
    readonly autoSuggestionSettings = {
        noSuggestionsMessage: 'No suggestions available',
        suggestionsClass: 'command-palette-suggestions',
        debounceWaitMs: 50,
        showOnFocus: true
    };

    @inject(TYPES.IActionDispatcher)
    protected actionDispatcher: GLSPActionDispatcher;

    @inject(EditorContextService)
    protected editorContextService: EditorContextService;

    @inject(TYPES.ViewerOptions)
    protected viewerOptions: ViewerOptions;

    @inject(TYPES.DOMHelper)
    protected domHelper: DOMHelper;

    @inject(TYPES.ILogger)
    protected logger: ILogger;

    protected task: TaskNode;
    protected autoSuggestion: AutoCompleteWidget;

    id(): string {
        return TaskEditor.ID;
    }
    containerClass(): string {
        return 'command-palette';
    }

    protected initializeContents(containerElement: HTMLElement): void {
        this.autoSuggestion = new AutoCompleteWidget(
            this.autoSuggestionSettings,
            { provideSuggestions: input => this.retrieveSuggestions(input) },
            { executeFromSuggestion: input => this.executeFromSuggestion(input) },
            () => this.hide(),
            this.logger
        );
        this.autoSuggestion.configureValidation(
            { validate: input => this.validateInput(input) },
            new ValidationDecorator(containerElement)
        );
        this.autoSuggestion.configureTextSubmitHandler({
            executeFromTextOnlyInput: (input: string) => this.executeFromTextOnlyInput(input)
        });
        this.autoSuggestion.initialize(containerElement);
    }

    show(root: Readonly<SModelRoot>, ...contextElementIds: string[]): void {
        super.show(root, ...contextElementIds);
        this.autoSuggestion.open(root);
    }

    protected onBeforeShow(containerElement: HTMLElement, root: Readonly<SModelRoot>, ...contextElementIds: string[]): void {
        this.task = getTask(contextElementIds, root)[0];
        this.autoSuggestion.inputField.value = '';
        this.setPosition(containerElement);
    }

    protected setPosition(containerElement: HTMLElement): void {
        let x = 0;
        let y = 0;

        if (this.task) {
            const bounds = getAbsoluteClientBounds(this.task, this.domHelper, this.viewerOptions);
            x = bounds.x + 5;
            y = bounds.y + 5;
        }

        containerElement.style.left = `${x}px`;
        containerElement.style.top = `${y}px`;
        containerElement.style.width = '200px';
    }

    protected async retrieveSuggestions(input: string): Promise<LabeledAction[]> {
        const response = await this.actionDispatcher.request(
            new RequestContextActions(TaskEditor.ID, this.editorContextService.get({ ['text']: input }))
        );
        if (isSetContextActionsAction(response)) {
            return response.actions;
        }
        return Promise.reject();
    }

    protected async validateInput(input: string): Promise<ValidationStatus> {
        const response = await this.actionDispatcher.request(new RequestEditValidationAction(TaskEditor.ID, this.task.id, input));
        if (isSetEditValidationResultAction(response)) {
            return response.status;
        }
        return Promise.reject();
    }

    protected executeFromSuggestion(input: LabeledAction | Action[] | Action): void {
        this.actionDispatcher.dispatchAll(toActionArray(input));
    }

    protected executeFromTextOnlyInput(input: string): void {
        const action = new ApplyTaskEditOperation(this.task.id, input);
        this.actionDispatcher.dispatch(action);
    }

    hide(): void {
        this.autoSuggestion.dispose();
        super.hide();
    }
}

function getTask(ids: string[], element: SModelElement): TaskNode[] {
    return ids.map(id => element.index.getById(id)).filter(isTaskNode);
}
