/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
import { Action, Bounds, RequestPopupModelAction, SetPopupModelAction } from '@eclipse-glsp/protocol';
import { injectable } from 'inversify';
import {
    EMPTY_ROOT,
    HoverFeedbackAction,
    HoverMouseListener,
    IActionHandler,
    ICommand,
    PreRenderedElementSchema,
    SIssueMarker,
    SIssueSeverity,
    SModelElement,
    SModelElementSchema,
    SModelRootSchema
} from 'sprotty';
import { isFocusStateChangedAction } from '../../base/actions/focus-change-action';
import { GIssueMarker } from '../validation/issue-marker';

@injectable()
export class GlspHoverMouseListener extends HoverMouseListener implements IActionHandler {
    /**
     * Stops mouse over timer and remove hover feedback, if focus is lost.
     *
     * This fixes strange effects that appear if the mouse left the element via e.g. a context menu,
     * which explicitly removes the focus of the diagram.
     * @see SelectionServiceAwareContextMenuMouseListener
     * @param action should be a `FocusStateChangedAction`
     * @returns a `HoverFeedbackAction` resetting the state, if the specified action indicates lost focus
     */
    handle(action: Action): void | Action | ICommand {
        if (isFocusStateChangedAction(action) && !action.hasFocus) {
            this.stopMouseOverTimer();
            if (this.lastHoverFeedbackElementId) {
                const previousTargetId = this.lastHoverFeedbackElementId;
                this.lastHoverFeedbackElementId = undefined;
                return new HoverFeedbackAction(previousTargetId, false);
            }
        }
    }

    protected startMouseOverTimer(target: SModelElement, event: MouseEvent): Promise<Action> {
        this.stopMouseOverTimer();
        return new Promise(resolve => {
            this.state.mouseOverTimer = window.setTimeout(() => {
                const popupBounds = this.computePopupBounds(target, { x: event.pageX, y: event.pageY });
                if (target instanceof GIssueMarker) {
                    resolve(new SetPopupModelAction(this.createPopupModel(target as GIssueMarker, popupBounds)));
                } else {
                    resolve(new RequestPopupModelAction(target.id, popupBounds));
                }

                this.state.popupOpen = true;
                this.state.previousPopupElement = target;
            }, this.options.popupOpenDelay);
        });
    }

    protected createPopupModel(marker: GIssueMarker, bounds: Bounds): SModelRootSchema {
        if (marker.issues !== undefined && marker.issues.length > 0) {
            return {
                type: 'html',
                id: 'sprotty-popup',
                children: [this.createMarkerIssuePopup(marker)],
                canvasBounds: this.modifyBounds(bounds)
            };
        }
        return { type: EMPTY_ROOT.type, id: EMPTY_ROOT.id };
    }

    protected createMarkerIssuePopup(marker: GIssueMarker): SModelElementSchema {
        const message = this.createIssueMessage(marker);
        return {
            type: 'pre-rendered',
            id: 'popup-title',
            code: `<div class="${getSeverity(marker)}"><div class="sprotty-popup-title">${message}</div></div>`
        } as PreRenderedElementSchema;
    }

    protected createIssueMessage(marker: GIssueMarker): string {
        return '<ul>' + marker.issues.map(i => '<li>' + i.severity.toUpperCase() + ': ' + i.message + '</li>').join('') + '</ul>';
    }

    protected modifyBounds(bounds: Bounds): Bounds {
        return bounds;
    }
}

export function getSeverity(marker: SIssueMarker): SIssueSeverity {
    let currentSeverity: SIssueSeverity = 'info';
    for (const severity of marker.issues.map(s => s.severity)) {
        if (severity === 'error') {
            return severity;
        }
        if (severity === 'warning' && currentSeverity === 'info') {
            currentSeverity = severity;
        }
    }
    return currentSeverity;
}
