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
import { Action, isStringArray } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { CommandExecutionContext, SModelElement, SModelRoot, TYPES } from 'sprotty';
import { addCssClasses, removeCssClasses } from '../../utils/smodel-util';
import { FeedbackCommand } from './model';

export class ModifyCSSFeedbackAction implements Action {
    readonly elementIds?: string[];

    constructor(
        public readonly input?: string[] | SModelElement[],
        public readonly addClasses?: string[],
        public readonly removeClasses?: string[],
        public kind = ModifyCssFeedbackCommand.KIND
    ) {
        if (input) {
            this.elementIds = isStringArray(input) ? input : input.map(element => element.id);
        }
    }
}

@injectable()
export class ModifyCssFeedbackCommand extends FeedbackCommand {
    static readonly KIND = 'modifyCSSFeedback';

    constructor(@inject(TYPES.Action) readonly action: ModifyCSSFeedbackAction) {
        super();
    }

    execute(context: CommandExecutionContext): SModelRoot {
        const elements: SModelElement[] = [];
        if (this.action.elementIds) {
            elements.push(...this.action.elementIds.map(elementId => context.root.index.getById(elementId)).filter(exists));
        } else {
            elements.push(context.root);
        }

        elements.forEach(e => {
            if (this.action.removeClasses) {
                removeCssClasses(e, this.action.removeClasses);
            }

            if (this.action.addClasses) {
                addCssClasses(e, this.action.addClasses);
            }
        });

        return context.root;
    }
}

function exists(elt?: SModelElement): elt is SModelElement {
    return elt !== undefined;
}

// eslint-disable-next-line no-shadow
export enum CursorCSS {
    DEFAULT = 'default-mode',
    OVERLAP_FORBIDDEN = 'overlap-forbidden-mode',
    NODE_CREATION = 'node-creation-mode',
    EDGE_CREATION_SOURCE = 'edge-creation-select-source-mode',
    EDGE_CREATION_TARGET = 'edge-creation-select-target-mode',
    EDGE_RECONNECT = 'edge-reconnect-select-target-mode',
    OPERATION_NOT_ALLOWED = 'edge-modification-not-allowed-mode',
    ELEMENT_DELETION = 'element-deletion-mode',
    RESIZE_NESW = 'resize-nesw-mode',
    RESIZE_NWSE = 'resize-nwse-mode',
    MOVE = 'move-mode',
    MARQUEE = 'marquee-mode'
}

export function cursorFeedbackAction(cursorCss?: CursorCSS): ModifyCSSFeedbackAction {
    const addCss = [];
    if (cursorCss) {
        addCss.push(cursorCss);
    }
    return new ModifyCSSFeedbackAction(undefined, addCss, Object.values(CursorCSS));
}

export function applyCssClasses(element: SModelElement, ...classes: string[]): ModifyCSSFeedbackAction {
    return new ModifyCSSFeedbackAction([element], classes, []);
}

export function deleteCssClasses(element: SModelElement, ...classes: string[]): ModifyCSSFeedbackAction {
    return new ModifyCSSFeedbackAction([element], [], classes);
}
