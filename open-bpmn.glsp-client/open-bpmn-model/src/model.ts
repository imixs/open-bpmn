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
import {
	boundsFeature,
	connectableFeature,
	CircularNode,
	deletableFeature,
	SNode,
	EditableLabel,
	fadeFeature,
	hoverFeedbackFeature,
	isEditableLabel,
	layoutableChildFeature,
	LayoutContainer,
	layoutContainerFeature,
	moveFeature,
	Nameable,
	nameFeature,
	popupFeature,
	RectangularNode,
	SChildElement,
	SEdge,
	selectFeature,
	SModelElement,
	SShapeElement,
	WithEditableLabel,
	withEditLabelFeature,
	Fadeable, Hoverable,SPort
} from '@eclipse-glsp/client';
import { Bounds,Point } from 'sprotty-protocol';
// import { BPMN_ELEMENT_ANCHOR_KIND } from './bpmn-anchors';

export class TaskNode extends RectangularNode implements Nameable, WithEditableLabel {
	static readonly DEFAULT_FEATURES = [
		connectableFeature,
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature,
		nameFeature,
		withEditLabelFeature
	];
	category?: string;
	documentation?: string;

	get editableLabel(): (SChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}

	get name(): string {
		const labelText = this.editableLabel?.text;
		return labelText ? labelText : '<unknown>';
	}

	/*
	 * Method that returns a json data structure including all
	 * editable element properties
	 */
	get propetriesData(): any {
		return {
			'name': this.name,
			'category': this.category,
			'documentation': this.documentation ? this.documentation : ''
		};
	}

	/*
	 * Returns the BPMN anchorCompute Kind for GatewayNodes
	 */
	/*get anchorKind(): string {
		return BPMN_ELEMENT_ANCHOR_KIND;
	}*/
}

/* CircularNode RectangularNode */
export class EventNode extends CircularNode implements Nameable, WithEditableLabel {
	static readonly DEFAULT_FEATURES = [
		connectableFeature,
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature,
		nameFeature,
		withEditLabelFeature
	];
	category?: string;
	documentation?: string;

	get editableLabel(): (SChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}

	get name(): string {
		const labelText = this.editableLabel?.text;
		return labelText ? labelText : '<unknown>';
	}

	/*
	 * Method that returns a json data structure including all
	 * editable element properties
	 */
	get propetriesData(): any {
		return {
			'name': this.name,
			'category': this.category,
			'documentation': this.documentation ? this.documentation : ''
		};
	}
}

// DiamondNode
export class GatewayNode extends SNode implements Nameable, WithEditableLabel {
	static readonly DEFAULT_FEATURES = [
		connectableFeature,
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature,
		nameFeature,
		withEditLabelFeature
	];
	category?: string;
	documentation: string;

	get editableLabel(): (SChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}

	get name(): string {
		const labelText = this.editableLabel?.text;
		return labelText ? labelText : '<unknown>';
	}

	/*
	 * Method that returns a json data structure including all
	 * editable element properties
	 */
	get propetriesData(): any {
		return {
			'name': this.name,
			'category': this.category,
			'documentation': this.documentation ? this.documentation : ''
		};
	}
}

export namespace GatewayNode {
	export namespace Type {
		export const EXLUSIVE = 'exclusiveNode';
		export const INCLUSIVE = 'inclusiveNode';
		export const PARALLEL = 'parallelNode';
		export const UNDEFINED = 'undefined';
	}
}

/*
 * Helper Methods to determind if a ModelElement is of a specific type
 * The methods return the corresponding node
 */
export function isTaskNode(element: SModelElement): element is TaskNode {
	return element instanceof TaskNode || false;
}

export function isEventNode(element: SModelElement): element is EventNode {
	return element instanceof EventNode || false;
}

export function isGatewayNode(element: SModelElement): element is GatewayNode {
	return element instanceof GatewayNode || false;
}

/*
* This method returns the BPMN Node Element of a given SModelElement.
* The method detects if the given ModelElement is for example a SPort
* or label:heading. In this case the method returns the parent element
* which is a Task, Event or Gateway node
*/
export function getBPMNNode(modelElement: SModelElement): SModelElement | undefined {
	if (isTaskNode(modelElement)) {
		// we found a direct task node
		return modelElement;
	}
	if (isEventNode(modelElement)) {
		// we found a direct event node
		return modelElement;
	}
	if (isGatewayNode(modelElement)) {
		// we found a direct gateway node
		return modelElement;
	}

	if (modelElement instanceof BPMNPort) {
		// we have a BPMNPOrt - so return the parent
		if (modelElement instanceof SChildElement) {
			return modelElement.parent;
		}
	}
	if (modelElement instanceof SChildElement) {
		// try a recusrive call
		return getBPMNNode(modelElement.parent);
	}

	// no result!
	return undefined;
}

/*
 * This method returns the center point of a BPMNNode element.
 * The method is needed because of the untypical implementation
 * of the EventView and GatewayView
 */
export function getBPMNNodeCenter(modelElement: SModelElement): Point | undefined {
	if (isTaskNode(modelElement)) {
		console.log('we found a task node ');
		// we can use the default method of GLSP here
		const point = Bounds.center(modelElement.bounds);
		console.log(' center bounds y='+point.y);
		return point;
	}
	if (isEventNode(modelElement)) {
		console.log('we found a event node');
		let point = Bounds.center(modelElement.bounds);
		console.log(' center bounds y='+point.y);
		// we fix the center point offset here
		point = { x:point.x, y:modelElement.bounds.y+(36*0.5)};
		return point;
	}
	return undefined;
}

/**
 * The BPMNPort extends teh SPort and disables the selected feature and adds the
 * BPMN_ELEMENT_ANCHOR_KIND.
 * A Port is a connection point for edges. It should always be contained in an SNode.
 */
export class BPMNPort extends SPort implements Fadeable, Hoverable {
    static readonly DEFAULT_FEATURES = [connectableFeature, boundsFeature, fadeFeature,
        hoverFeedbackFeature];

    // selected: boolean = false;
    hoverFeedback = false;
    opacity = 1;

	/*
	* Returns the BPMN anchorCompute Kind for BPMN Elements
	*/
	/*get anchorKind(): string {
		return BPMN_ELEMENT_ANCHOR_KIND;
	}*/
}

export class SequenceFlow extends SEdge {
	condition?: string;

	/*
	* Returns the BPMN anchorCompute Kind for BPMN Elements
	*/
	/*get anchorKind(): string {
		return BPMN_ELEMENT_ANCHOR_KIND;
	}*/
}

export class Icon extends SShapeElement implements LayoutContainer {
	static readonly DEFAULT_FEATURES = [boundsFeature, layoutContainerFeature, layoutableChildFeature, fadeFeature];

	layout: string;
	layoutOptions?: { [key: string]: string | number | boolean };
	size = {
		width: 32,
		height: 32
	};
}

export class PoolNode extends RectangularNode implements Nameable, WithEditableLabel {
	static readonly DEFAULT_FEATURES = [
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature,
		nameFeature,
		withEditLabelFeature
	];

	name = '';

	get editableLabel(): (SChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}
}
