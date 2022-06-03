/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
import {
	boundsFeature,
	connectableFeature,
	CircularNode,DiamondNode,
	deletableFeature,
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
	withEditLabelFeature
} from '@eclipse-glsp/client';
import { Bounds } from 'sprotty-protocol';
// import { BPMN_ELEMENT_ANCHOR_KIND } from './bpmn-anchors';

export interface BPMNFlowElement {
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

}

/* CircularNode RectangularNode */
export class EventNode extends CircularNode implements BPMNFlowElement {
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
		nameFeature
	];
	symbol?: string;
	name?: string;

}

// DiamondNode
export class GatewayNode extends DiamondNode implements Nameable, WithEditableLabel, BPMNFlowElement {
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

export function isBPMNLabelNode(element: SModelElement): element is SModelElement {
	return (element instanceof EventNode || element instanceof GatewayNode) || false;
}

/*
* This method returns the BPMN Node Element of a given SModelElement.
* The method detects if the given ModelElement is for example a SPort
* or label:heading. In this case the method returns the parent element
* which is a Task, Event or Gateway node
*/
export function isBPMNNode(element: SModelElement): element is TaskNode | EventNode | GatewayNode {
    return element instanceof TaskNode || element instanceof EventNode || element instanceof GatewayNode;
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
