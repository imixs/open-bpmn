/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
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
import { injectable } from 'inversify';
import { MouseListener, SModelElement } from 'sprotty';

/**
 * A mouse listener that clears the document selection on click
 *
 * Clicking on SVG elements doesn't update the document selection. In the context of diagramming
 * with GLSP, this is unnatural, as the user would expect that clicking in the diagram (e.g. to
 * select a diagram element) would clear any other selection state in the DOM. From a technical
 * point of view, an active selection in the document after clicking into the diagram may also
 * lead to bogus target elements in certain browser events, such as clipboard events, etc.
 *
 * This listener helps to overcome this problem by actively clearing any existing selection in
 * the document, if the user clicks into the diagram.
 */
@injectable()
export class SelectionClearingMouseListener extends MouseListener {
    mouseDown(_target: SModelElement, event: MouseEvent): never[] {
        const selection = document.getSelection();
        // eslint-disable-next-line no-null/no-null
        if (selection === null) {
            return [];
        }
        selection.removeAllRanges();
        selection.addRange(document.createRange());
        return [];
    }
}
