/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { inject, injectable, postConstruct } from 'inversify';
import {
    AbstractUIExtension,
    SetUIExtensionVisibilityAction,
    ActionDispatcher,
    SModelRoot,
    TYPES
} from 'sprotty';
import {
    Action,
	EditModeListener,
	EditorContextService
} from '@eclipse-glsp/client';
import {
	SelectionListener,
	SelectionService
} from '@eclipse-glsp/client/lib/features/select/selection-service';

@injectable()
export class EnableBPMNPropertyPanelAction implements Action {
    static readonly KIND = 'enableBPMNPropertyPanel';
    readonly kind = EnableBPMNPropertyPanelAction.KIND;
}

@injectable()
export class BPMNPropertyPanel extends AbstractUIExtension implements  EditModeListener, SelectionListener {
    static readonly ID = 'bpmn-property-panel';

    @inject(TYPES.IActionDispatcher)
    protected readonly actionDispatcher: ActionDispatcher;

    @inject(EditorContextService)
    protected readonly editorContext: EditorContextService;

    @inject(SelectionService)
    protected selectionService: SelectionService;

    protected bodyDiv?: HTMLElement;
    modelRootId: string;

    @postConstruct()
    postConstruct(): void {
        this.editorContext.register(this);
        this.selectionService.register(this);
    }

    id(): string {
        return BPMNPropertyPanel.ID;
    }
    containerClass(): string {
        return BPMNPropertyPanel.ID;
    }

    initialize(): boolean {
        return super.initialize();
    }

	/*
	 * Initalize the elemnts of property panel
	 */
    protected initializeContents(containerElement: HTMLElement): void {
        const div = document.createElement('div');
        div.innerHTML = 'hello world';
        containerElement.appendChild(div);
    }

    editModeChanged(_oldValue: string, _newValue: string): void {
		console.log('...bin in editModeChanged: '+_newValue);
        this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(BPMNPropertyPanel.ID, !this.editorContext.isReadonly));
    }

    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        console.log('selection change  received:', root, selectedElements);
    }

}

