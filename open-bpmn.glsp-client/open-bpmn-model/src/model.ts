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
	withEditLabelFeature,
	ForeignObjectElement,SArgumentable,Args,Bounds,isBoundsAware,Dimension
} from '@eclipse-glsp/client';

export interface BPMNFlowElement {
}

export class LabelNode extends RectangularNode implements Nameable, WithEditableLabel{
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
		nameFeature,
		withEditLabelFeature
	];

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

export class TaskNode extends RectangularNode implements Nameable, WithEditableLabel, BPMNFlowElement {
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
		nameFeature,
		withEditLabelFeature
	];

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
		popupFeature,
		nameFeature
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
		popupFeature,
		nameFeature
	];
	documentation: string;
}

/*
 * This class provides a new Node displaying a multiline textblock.
 * The node also allows editing the text.
 * We are using this model object for BPMN TextAnnotations.
 *
 * See: https://www.eclipse.org/glsp/documentation/rendering/#default-views
 */
export class MultiLineTextNode extends ForeignObjectElement implements SArgumentable, EditableLabel {
  readonly isMultiLine = true;
  readonly args: Args;
  text = '';

  override set bounds(bounds: Bounds) {
    /* ignore set bounds, always use the parent's bounds */
  }

  override get bounds(): Bounds {
    if (isBoundsAware(this.parent)) {
      return {
        x: this.position.x,
        y: this.position.y,
        width: this.parent.bounds.width,
        height: this.parent.bounds.height
      };
    }
    return Bounds.EMPTY;
  }

  // @ts-expect-error Arguments are set in the element
  override get code(): string {
    if (this.text === '') {
      const textArg = this.args['text'];
      if (typeof textArg === 'string') {
        this.text = textArg;
      }
    }
    return `<pre>${this.text}</pre>`;
  }

  override namespace = 'http://www.w3.org/1999/xhtml';

  get editControlDimension(): Dimension {
    return {
      width: this.bounds.width - 4,
      height: this.bounds.height - 4
    };
  }
}

/*
 * Helper Methods to determind if a ModelElement is of a specific type
 * The methods return the corresponding node
 */
export function isTaskNode(element: SModelElement): element is TaskNode {
	return element instanceof TaskNode || false;
}

export function isPoolNode(element: SModelElement): element is PoolNode {
	return element instanceof PoolNode || false;
}

export function isContainerNode(element: SModelElement): element is LaneNode | PoolNode {
	return (element instanceof LaneNode || element instanceof PoolNode) || false;
}

export function isLaneNode(element: SModelElement): element is LaneNode {
	return element instanceof LaneNode || false;
}

export function isEventNode(element: SModelElement): element is EventNode {
	return element instanceof EventNode || false;
}

export function isGatewayNode(element: SModelElement): element is GatewayNode {
	return element instanceof GatewayNode || false;
}

/*
 * Indicates that the ModelElement has a independed BPNNLabel
 */
export function isBPMNLabelNode(element: SModelElement): element is SModelElement {
	return (element instanceof EventNode || element instanceof GatewayNode || element instanceof DataObjectNode
            || element instanceof MessageNode) || false;
}

/*
* This method returns the BPMN Node Element of a given SModelElement.
* The method detects if the given ModelElement is for example a SPort
* or label:heading. In this case the method returns the parent element
* which is a Task, Event or Gateway node
*/
export function isBPMNNode(element: SModelElement): element is TaskNode | EventNode | GatewayNode {
    return element instanceof TaskNode || element instanceof EventNode || element instanceof GatewayNode ||
           element instanceof DataObjectNode || element instanceof MessageNode || element instanceof PoolNode;
}

/*
 * Returns ture if the BPMN Node Elmeent is a BoundaryEvent
 */
export function isBoundaryEvent(element: SModelElement): element is EventNode {
    return element instanceof EventNode && element.type === 'boundaryEvent';
}

export class BPMNEdge extends SEdge {
	kind?: string;
	documentation: string;
}

export class Icon extends SShapeElement implements LayoutContainer {
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

	get editableLabel(): (SChildElement & EditableLabel) | undefined {
		const label = this.children.find(element => element.type === 'label:heading');
		if (label && isEditableLabel(label)) {
			return label;
		}
		return undefined;
	}
}
