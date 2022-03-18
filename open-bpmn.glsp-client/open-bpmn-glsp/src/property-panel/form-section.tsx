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
import { isLabeledAction, isTriggerElementTypeCreationAction, LabeledAction, TriggerElementCreationAction } from '@eclipse-glsp/protocol';
// import * as React from 'react';
/*import * as ReactDOM from 'react-dom';
// import { svg } from 'sprotty';
import {JsonForms} from '@jsonforms/react';
import {
    vanillaCells,
    vanillaRenderers
} from '@jsonforms/vanilla-renderers';*/

/*
 * Defines a single form section containing input fields and buttons
 */
export interface FormSection extends LabeledAction {
    readonly id: string;
    readonly sortString: string;
    readonly children?: FormSection[];
}

export function isFormSection(object?: any): object is FormSection {
    return isLabeledAction(object) && 'id' in object && 'sortString' in object;
}

export namespace FormSection {
    export function getTriggerAction(item?: FormSection): TriggerElementCreationAction | undefined {
        if (item) {
            const initiAction = item.actions
                .filter(a => isTriggerElementTypeCreationAction(a))
                .map(action => action as TriggerElementCreationAction);
            return initiAction.length > 0 ? initiAction[0] : undefined;
        }
        return undefined;
    }
}
/*
export function renderEmptyForm(node: HTMLElement): void {
	console.log('Debug 1');
	const elementDataAsJson={};

	ReactDOM.render(
    <JsonForms
        data={elementDataAsJson}
        cells={vanillaCells}
        renderers={vanillaRenderers}
        readonly
    />,
    node
);
console.log('Debug END');
}
*/
/*
export function renderEmptyForm2(): void {
	console.log('Debug 1');
	ReactDOM.render(<div/>, document.getElementById('bpmn-diagram_0_bpmn-property-panel'));
	console.log('Ich bin fertig');
}
*/


