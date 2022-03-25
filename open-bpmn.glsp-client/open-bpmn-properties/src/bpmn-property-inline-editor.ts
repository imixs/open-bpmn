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
    AutoCompleteWidget,
    EditorContextService,
    getAbsoluteClientBounds,
    GLSPActionDispatcher,
    ILogger,
    SModelElement,
    SModelRoot,
    ViewerOptions
} from '@eclipse-glsp/client';
import { inject, injectable } from 'inversify';
import { TYPES } from 'sprotty/lib';
import { DOMHelper } from 'sprotty/lib/base/views/dom-helper';

import { TaskNode, isTaskNode } from '@open-bpmn/open-bpmn-glsp';



@injectable()
export class BPMNPropertyEditor extends AbstractUIExtension {
    static readonly ID = 'bpmn-property-editor';


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
        return BPMNPropertyEditor.ID;
    }
    containerClass(): string {
        return 'bpmn-property-editor';
    }

    protected initializeContents(containerElement: HTMLElement): void {
		console.log('.... BPMNPropertyEditor - initializeContents')

    }

    show(root: Readonly<SModelRoot>, ...contextElementIds: string[]): void {
        super.show(root, ...contextElementIds);
        console.log('...show');
    }

    protected onBeforeShow(containerElement: HTMLElement, root: Readonly<SModelRoot>, ...contextElementIds: string[]): void {
        this.task = getTask(contextElementIds, root)[0];
        console.log('...onBeforeShow');
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

    hide(): void {
        console.log('...hide');
        super.hide();
    }
}

function getTask(ids: string[], element: SModelElement): TaskNode[] {
    return ids.map(id => element.index.getById(id)).filter(isTaskNode);
}
