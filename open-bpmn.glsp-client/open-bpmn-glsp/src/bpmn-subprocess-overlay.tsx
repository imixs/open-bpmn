/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
  EditorContextService,
  IActionDispatcher,
  ICommand,
  IDiagramStartup,
  MaybePromise,
  TYPES,
  codiconCSSClasses
} from '@eclipse-glsp/client';
import { BPMNToggleSubProcessAction } from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';
/****************************************************************************
 * This module provides an overlay control with a button to close an expended subProcess
 *
 ****************************************************************************/

/**
 * This SubProcessOverlay displays a close subProcess button on the left upper corner of the diagram panel
 */
@injectable()
export class SubProcessOverlay extends AbstractUIExtension implements IDiagramStartup {
    @inject(TYPES.IActionDispatcher)
    protected readonly actionDispatcher: IActionDispatcher;

    @inject(EditorContextService)
    protected editorContext: EditorContextService;

    static readonly ID = 'grid-button-overlay';

    id(): string {
        return SubProcessOverlay.ID;
    }

    containerClass(): string {
        return SubProcessOverlay.ID;
    }

    /**
     * icon is taken form https://microsoft.github.io/vscode-codicons/dist/codicon.html
     * collapse-all, screen-normal, diff-removed, combine
     *
     * @param containerElement
     */
    protected initializeContents(containerElement: HTMLElement): void {
        containerElement.appendChild(this.createButton('btn_center_diagram', 'Close SubProcess',
           'combine', BPMNToggleSubProcessAction.create({mode:'collapse'})));
    }

    protected createButton(id: string, label: string, codiconId: string, action: Action): HTMLElement {
        const baseDiv = document.getElementById(this.options.baseDiv);
        if (baseDiv) {
            const button = document.createElement('div');
            const insertedDiv = baseDiv.insertBefore(button, baseDiv.firstChild);
            button.id = id;
            button.classList.add('overlay-button');
            const icon = this.createIcon(codiconId);
            insertedDiv.appendChild(icon);
            insertedDiv.onclick = () => this.actionDispatcher.dispatch(action);
            insertedDiv.insertAdjacentText('beforeend', label);
            return button;
        }
        return document.createElement('div');
    }

    protected createIcon(codiconId: string): HTMLElement {
        const icon = document.createElement('i');
        icon.classList.add(...codiconCSSClasses(codiconId), 'overlay-icon');
        return icon;
    }

    postModelInitialization(): MaybePromise<void> {
      // no action
    }

    /*
     * We react on the toggleSubProcess Action events to display the
     * ui control after a subProcess was initialized
     */
    handle(action: Action): ICommand | Action | void {
        // Toggle the property panel. Action is triggered by context menu
        if (action.kind === BPMNToggleSubProcessAction.KIND) {
            if (BPMNToggleSubProcessAction.is(action)) {
               // test mode
               const mode=action.mode;
               if ('expand'===mode) {
                  this.show(this.editorContext.modelRoot);
               } else {
                  this.hide();
               }
            }

        }
    }
}
