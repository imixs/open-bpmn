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
	Action,
	Args,
	CircularNode,
	DiamondNode,
	EditableLabel,
	GChildElement,
	GEdge,
	GModelElement,
	GShapeElement,
	LayoutContainer,
	Nameable,
	RectangularNode,
	WithEditableLabel,
	boundsFeature,
	connectableFeature, deletableFeature,
	fadeFeature,
	hoverFeedbackFeature,
	isEditableLabel,
	layoutContainerFeature,
	layoutableChildFeature,
	moveFeature,
	nameFeature,
	popupFeature,
	selectFeature,
	withEditLabelFeature
} from '@eclipse-glsp/client';

export interface BPMNFlowElement {
}

export class LabelNode extends RectangularNode {
	static override readonly DEFAULT_FEATURES = [
		selectFeature,
		moveFeature,
		layoutContainerFeature,
		hoverFeedbackFeature
	];
}

/* Task Node */
export class TaskNode extends RectangularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
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
	readonly args: Args;
	kind?: string;
	documentation: string;
}

/* CircularNode RectangularNode */
export class EventNode extends CircularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
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
	kind?: string;
	readonly args: Args;
	documentation: string;
}

// DiamondNode  //  Nameable, WithEditableLabel,
export class GatewayNode extends DiamondNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
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
	kind?: string;
	readonly args: Args;
	documentation: string;
}

export class DataObjectNode extends RectangularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
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
	readonly args: Args;
	documentation: string;
}

export class DataStoreNode extends RectangularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
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
	documentation: string;
}

export class MessageNode extends RectangularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
		connectableFeature,
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature
	];
	documentation: string;
}

export class TextAnnotationNode extends RectangularNode implements BPMNFlowElement {
	static override readonly DEFAULT_FEATURES = [
		connectableFeature,
		deletableFeature,
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature
	];
	documentation: string;
}

/*
 * This class provides a new Node displaying a multiline text block.
 * The text can be aligned 'left', 'middle' or 'right'.
 *
 */
export class MultiLineTextNode extends GShapeElement { // implements SArgumentable
	static readonly DEFAULT_FEATURES = [
		boundsFeature];
	layout: string;
	readonly args: Args;
	text = '';
	align = '';
}

export class BPMNEdge extends GEdge {
	kind?: string;
	documentation: string;
	override routerKind = 'manhattan';
}

export class Icon extends GShapeElement implements LayoutContainer {
	static readonly DEFAULT_FEATURES = [
		boundsFeature,
		layoutContainerFeature,
		layoutableChildFeature,
		fadeFeature];
	layout: string;
	override layoutOptions?: { [key: string]: string | number | boolean };
	override size = {
		width: 16,
		height: 16
	};
}

export class PoolNode extends RectangularNode implements Nameable, WithEditableLabel {
	static override readonly DEFAULT_FEATURES = [
		deletableFeature,
		selectFeature,
		boundsFeature,
		layoutContainerFeature,
		fadeFeature,
		hoverFeedbackFeature,
		popupFeature,
		nameFeature,
		withEditLabelFeature
	];

	name = '';

	get editableLabel(): (GChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}
}

export class LaneNode extends RectangularNode implements Nameable, WithEditableLabel {
	static override readonly DEFAULT_FEATURES = [
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

	get editableLabel(): (GChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}
}

/*
 * The model element for the lane divider. The model holds arguments
 * with the ymin and ymax values
 */
export class LaneDivider extends GShapeElement { // implements SArgumentable
	static readonly DEFAULT_FEATURES = [
		selectFeature,
		boundsFeature,
		moveFeature,
		layoutContainerFeature,
		hoverFeedbackFeature
	];
	readonly args: Args;
}

/*
 * Helper Methods to determine if a ModelElement is of a specific type
 * The methods return the corresponding node
 */
export function isTaskNode(element: GModelElement): element is TaskNode {
	return element instanceof TaskNode || false;
}

export function isPoolNode(element: GModelElement): element is PoolNode {
	return element instanceof PoolNode || false;
}

export function isContainerNode(element: GModelElement): element is LaneNode | PoolNode {
	return (element instanceof LaneNode || element instanceof PoolNode) || false;
}

export function isLaneNode(element: GModelElement): element is LaneNode {
	return element instanceof LaneNode || false;
}

export function isEventNode(element: GModelElement): element is EventNode {
	return element instanceof EventNode || false;
}

export function isGatewayNode(element: GModelElement): element is GatewayNode {
	return element instanceof GatewayNode || false;
}

/*
 * Indicates that the ModelElement has a independent BPMNLabel
 */
export function isBPMNLabelNode(element: GModelElement): element is GModelElement {
	return (element instanceof EventNode || element instanceof GatewayNode
		|| element instanceof DataObjectNode || element instanceof DataStoreNode
		|| element instanceof MessageNode) || false;
}

/*
* This method returns true if the element is a BPMN Node Element.
*/
export function isBPMNNode(element: GModelElement): element is TaskNode | EventNode | GatewayNode |
	DataObjectNode | DataStoreNode | TextAnnotationNode | MessageNode | PoolNode {
	return element instanceof TaskNode || element instanceof EventNode || element instanceof GatewayNode ||
		element instanceof DataObjectNode || element instanceof DataStoreNode || element instanceof TextAnnotationNode ||
		element instanceof MessageNode || element instanceof PoolNode;
}

/*
* This method returns true if the element is a BPMN Edge Element.
*/
export function isBPMNEdge(element: GModelElement): element is BPMNEdge {
	return element instanceof BPMNEdge;
}

/*
 * Returns true if the BPMN Node Element is a BoundaryEvent
 */
export function isBoundaryEvent(element: GModelElement): element is EventNode {
	return element instanceof EventNode && element.type === 'boundaryEvent';
}

/*
 * Returns true if the model Element is a LaneDivider
 */
export function isLaneDivider(element: GModelElement): boolean {
	return element.type === 'lane-divider';
}

/**
 * Open-BPMN Toggle SubProcess Action
 * This action holds the id of the expanded subProcess.
 * The property mode indicates if the sub process is being expanded or collapsed.
 */
export interface BPMNToggleSubProcessAction extends Action {
    kind: typeof BPMNToggleSubProcessAction.KIND;
    processId?: string;
	mode?: string;
}
export namespace BPMNToggleSubProcessAction {
    export const KIND = 'toggleSubProcess';
    export function is(object: any): object is BPMNToggleSubProcessAction {
        return Action.hasKind(object, KIND);
    }
    export function create(options: {processId?: string, mode?: string}): BPMNToggleSubProcessAction {
        return {
            kind: KIND,
            ...options
        };
    }
}
