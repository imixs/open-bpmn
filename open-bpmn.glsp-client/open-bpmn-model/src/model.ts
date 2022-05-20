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
	withEditLabelFeature,isBoundsAware,
	Fadeable, Hoverable,SPort
} from '@eclipse-glsp/client';
import { Bounds,Point } from 'sprotty-protocol';
// import { BPMN_ELEMENT_ANCHOR_KIND } from './bpmn-anchors';

export interface BPMNFlowElement {
    symbolBounds: Bounds
}

/* a weak teas if an SModelElement is a BPMNFlowElement */
export function isBPMNFlowElement(obj: any): obj is BPMNFlowElement {
  return ('symbolBounds' in obj) && (obj instanceof SModelElement );
}

export class TaskNode extends RectangularNode implements Nameable, WithEditableLabel, BPMNFlowElement {
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

	// return the default bounds
	get symbolBounds(): Bounds {
		return this.bounds;
	}

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

/* CircularNode RectangularNode */
export class EventNode extends CircularNode implements Nameable, WithEditableLabel, BPMNFlowElement {
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

	// return the offset bounds
	get symbolBounds(): Bounds {
		return {
			x: this.bounds.x-20,
			y: this.bounds.y,
			width: 40.0,
			height: 40.0
        };
	}

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
export class GatewayNode extends SNode implements Nameable, WithEditableLabel, BPMNFlowElement {
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

	// return the default bounds
	get symbolBounds(): Bounds {
		return {
			x: this.bounds.x-25,
			y: this.bounds.y,
			width: 50.0,
			height: 50.0
        };
	}

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

	if (isBPMNFlowElement(modelElement)) {
		// console.log('isBPMNFlowELement=true');
		return modelElement;
	}

	if (modelElement instanceof BPMNPort) {
		// we have a BPMNPOrt - recusrive call with parent
		if (modelElement instanceof SChildElement) {
			return getBPMNNode(modelElement.parent);
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
 * This method returns the center point of a BPMNFlowElement.
 * A BPMNFlowElement holds an extra bounds element for the symbol.
 * The method is needed because of the untypical implementation
 * of the EventView and GatewayView
 */
export function getBPMNNodeCenter(modelElement: SModelElement): Point | undefined {

	if (isBPMNFlowElement(modelElement) && isBoundsAware(modelElement)) {
		console.log('Bounds  : '+modelElement.type );
		console.log('  outer -> x='+modelElement.bounds.x +  ' y='+modelElement.bounds.y
		+  ' w='+modelElement.bounds.width
		+  ' h='+modelElement.bounds.height);
		console.log('   symbol -> x='+modelElement.symbolBounds.x +  ' y='+modelElement.symbolBounds.y
		+  ' w='+modelElement.symbolBounds.width
		+  ' h='+modelElement.symbolBounds.height);
		const result = Bounds.center(modelElement.symbolBounds);
		console.log('  center -> x='+result.x + ' y='+result.y);
		return result;
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

}

export class SequenceFlow extends SEdge {
	condition?: string;

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
