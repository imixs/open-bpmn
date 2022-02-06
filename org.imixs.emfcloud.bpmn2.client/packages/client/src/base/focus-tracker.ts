/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { Action } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { IActionHandler, ICommand, TYPES, ViewerOptions } from 'sprotty';
import { isFocusStateChangedAction } from './actions/focus-change-action';

@injectable()
export class FocusTracker implements IActionHandler {
    protected inActiveCssClass = 'inactive';
    protected _hasFocus = true;

    @inject(TYPES.ViewerOptions) protected options: ViewerOptions;

    get hasFocus(): boolean {
        return this._hasFocus;
    }

    handle(action: Action): void | Action | ICommand {
        if (isFocusStateChangedAction(action)) {
            this._hasFocus = action.hasFocus;
            const placeholder = document.getElementById(this.options.baseDiv);
            if (!placeholder) {
                return;
            }
            if (this.hasFocus) {
                if (placeholder.classList.contains(this.inActiveCssClass)) {
                    placeholder.classList.remove(this.inActiveCssClass);
                }
            } else {
                placeholder.classList.add(this.inActiveCssClass);
            }
        }
    }
}
