/********************************************************************************
 * Copyright (c) 2019-2022 EclipseSource and others.
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
	Action, SModelElement, SModelRoot, MouseListener, FeedbackCommand, hasObjectProp,GridSnapper
} from '@eclipse-glsp/client';
import { inject, injectable } from 'inversify';
import {
	SChildElement,
	CommandExecutionContext,
	CommandReturn,
	svg, IView, RenderingContext, getAbsoluteBounds,
	TYPES
} from 'sprotty';
import { VNode } from 'snabbdom';
import { Bounds, Point } from 'sprotty-protocol';
import { getBPMNNode,getBPMNNodeCenter} from '@open-bpmn/open-bpmn-model';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/****************************************************************************
 * This module extends the mouseListener to support helper lines.
 * HelperLines are displayed when a element is selected and draged on the diagram pane.
 *
 * If the moved element is alligned to the center of another element on the diagram
 * the helper line is shown. There are can be up to two helperlines be displayed
 * - horizontal and vertical
 *
 * The method 'findHelperLines' can be extended to proivde more helper lines
 * to show a different behavior.
 ****************************************************************************/

/*
 * The HelperLinesElement defines the SModelElement
 * to containing a list of helperLines.
 */
export class HelperLinesElement extends SChildElement {
	type: string;
	id: string;
	readonly helperLines: ReadonlyArray<HelperLine> = [];
	constructor(type: string, id: string, helperLines: HelperLine[]) {
		super();
		this.type = type;
		this.id = id;
		this.helperLines = helperLines;
	}
}
export interface HelperLine {
	readonly x1: number
	readonly y1: number
	readonly x2: number
	readonly y2: number
}


/*
 * This is a custom Implementation of a GridSnapper
 * for BPMN
 */
@injectable()
export class BPMNGridSnapper extends GridSnapper {
    override get gridX(): number {
        return 1;
    }

    override get gridY(): number {
        return 1;
    }
}


/*
 * The HelperLineListener reacts on mouseDown and mouseMove and searches for
 * matching elements acording to the current  possition of the draged element.
 */
@injectable()
export class HelperLineListener extends MouseListener {
	protected previouslySelected: string[];
	protected isActive = false;
	protected dreck: Point;

	override mouseDown(target: SModelElement, event: MouseEvent): Action[] {
		// check if target is relevant....
		let bpmnNode = getBPMNNode(target);
		if (bpmnNode) {
			// switch into active mode
			this.isActive = true;
		} else {
			this.isActive = false;
		}

		return [];
	}

	/**
	 * This method acts on a mouseMove event if a BPMN element is selected (isActive==true).
	 * The method computes a list of helperLines to alligned elements in the diagram.
	 * If helper lines where found, the method fires the corresponding
	 * command to draw the helper lines.
	 */
	override mouseMove(target: SModelElement, event: MouseEvent): Action[] {
		if (this.isActive) {
			// first test if we have a mouseMove on a BPMNNode
			let bpmnNode = getBPMNNode(target);
			if (bpmnNode) {
				const helperLines: HelperLine[] | undefined = this.findHelperLines(bpmnNode);
				if (helperLines) {
					return [DrawHelperLinesAction.create({ helperLines: helperLines })];
				} else {
					// now match! remove helper lines...
					return [RemoveHelperLinesAction.create()];
				}
			}
		}
		return [];
	}

	/*
	 * On the mouseUp event we end the active mode and
	 * remove the HelperLines from the model
	 */
	override mouseUp(target: SModelElement, event: MouseEvent): Action[] {
		this.isActive = false;
		return [RemoveHelperLinesAction.create()]; //  EnableDefaultToolsAction.create()
	}

	/*
	 * This helper method searches the model for model elements
	 * matching the horizontal and/or vertical alligment of the given modelElement.
	 *
	 * A ModelElement is a alligned to another element if its center point matches
	 * the same x or y axis. The method retuns up to two helper lines. The helper lines
	 * go through the full dimensions of the canvas.
	 *
	 * The method can be extended to find  more helper lines with a differnt algorythm.
	 */
	private findHelperLines(modelElement: SModelElement): HelperLine[] | undefined {
		const root: SModelRoot = modelElement.root;
		const helperLines: HelperLine[] = [];
		if (root) {
			const childs = root.children;
			const canvasBounds = root.canvasBounds;
			// compute absolute center bounds of the model element. This is needed to compute the
			// dimensions of the drawing canvas.
			const absoluteCenterBounds = Bounds.center(getAbsoluteBounds(modelElement));
			// const modelElementCenter = Bounds.center(modelElement.bounds);
			const modelElementCenter = getBPMNNodeCenter(modelElement);
			// In the following we iterate over all model elements
			// and compare the x and y axis of the center points
			let foundHorizontal = false;
			let foundVertical = false;
			for (const element of childs) {
				if (element.id !== modelElement.id) {
					// const elementCenter = Bounds.center(element.bounds);
					const elementCenter = getBPMNNodeCenter(element);
					if (elementCenter && modelElementCenter) {
						// test vertical alligment...
						if (!foundHorizontal && elementCenter.y === modelElementCenter.y) {
							const horizontalLine: HelperLine = {
								x1: modelElementCenter.x - absoluteCenterBounds.x,
								y1: elementCenter.y,
								x2: modelElementCenter.x - absoluteCenterBounds.x + canvasBounds.width,
								y2: elementCenter.y
							};
							foundHorizontal = true;
							helperLines.push(horizontalLine);
						}
						// test horizontal alligment...
						if (!foundVertical && elementCenter.x === modelElementCenter.x) {
							const verticalLine: HelperLine = {
								y1: modelElementCenter.y - absoluteCenterBounds.y,
								x1: elementCenter.x,
								y2: modelElementCenter.y - absoluteCenterBounds.y + canvasBounds.height,
								x2: elementCenter.x
							};
							foundVertical = true;
							helperLines.push(verticalLine);
						}
					}
				}
				if (foundHorizontal === true && foundVertical === true) {
					// we can break here as we found already two possible matches.
					break;
				}
			}
		}
		if (helperLines.length > 0) {
			return helperLines;
		}
		return undefined;
	}
		
}

export const HELPLINE = 'helpline';

export function helpLineId(root: SModelRoot): string {
	return root.id + '_' + HELPLINE;
}

/**
 * DrawHelperLines Action
 */
export interface DrawHelperLinesAction extends Action {
	kind: typeof DrawHelperLinesAction.KIND;
	helperLines: HelperLine[];
}
export namespace DrawHelperLinesAction {
	export const KIND = 'drawHelperLines';
	export function is(object: any): object is DrawHelperLinesAction {
		return Action.hasKind(object, KIND) && hasObjectProp(object, 'helperLines');
	}
	export function create(options: { helperLines: HelperLine[]; }): DrawHelperLinesAction {
		return {
			kind: KIND,
			...options
		};
	}
}

/*
 * The HelperLinesCommand creates the HelperLinesElement model element
 * and adds it to the model.
 */
@injectable()
export class DrawHelperLinesCommand extends FeedbackCommand {
	static readonly KIND = DrawHelperLinesAction.KIND;

	constructor(@inject(TYPES.Action) protected action: DrawHelperLinesAction) {
		super();
	}
	execute(context: CommandExecutionContext): CommandReturn {
		const root = context.root;
		removeHelperLines(root);
		// create new helperLineElement....
		const helperLineElement = new HelperLinesElement(HELPLINE, helpLineId(root), this.action.helperLines);
		root.add(helperLineElement);
		return context.root;
	}
}

export interface RemoveHelperLinesAction extends Action {
	kind: typeof RemoveHelperLinesAction.KIND;
}

export namespace RemoveHelperLinesAction {
	export const KIND = 'removeHelperLines';
	export function is(object: any): object is RemoveHelperLinesAction {
		return Action.hasKind(object, KIND);
	}
	export function create(): RemoveHelperLinesAction {
		return { kind: KIND };
	}
}

/*
 * HelperLine Command to remove the helperlines form the model
 * called when mouse move event ended
 */
@injectable()
export class RemoveHelperLinesCommand extends FeedbackCommand {
	static readonly KIND = RemoveHelperLinesAction.KIND;
	execute(context: CommandExecutionContext): CommandReturn {
		removeHelperLines(context.root);
		return context.root;
	}
}

/*
 * Helper method to remove the HelperLine element
 * from the model.
 */
export function removeHelperLines(root: SModelRoot): void {
	const helperLines = root.index.getById(helpLineId(root));
	if (helperLines instanceof SChildElement) {
		root.remove(helperLines);
	}
}

/*
 * The HelperLineView shows the helper lines.
 * The mothod draws either one line (horizontal|vertical) or both if two matching points are available.
 *
 * This View can be extended to display more helper lines in a differnt layout if needed.
 */
@injectable()
export class HelperLineView implements IView {
	render(model: Readonly<SModelElement>, context: RenderingContext): VNode {
		const helperLines: ReadonlyArray<HelperLine> = (model as HelperLinesElement).helperLines;
		let vnode: any;
		// draw only one helper line?
		if (helperLines.length === 1) {
			vnode = (
				<line x1={helperLines[0].x1} y1={helperLines[0].y1} x2={helperLines[0].x2} y2={helperLines[0].y2} class-helper-line />
			);
		}
		// draw two helper lines (horizontal|vertical)
		if (helperLines.length === 2) {
			vnode = (
				<g>
					<line x1={helperLines[0].x1} y1={helperLines[0].y1} x2={helperLines[0].x2} y2={helperLines[0].y2} class-helper-line />
					<line x1={helperLines[1].x1} y1={helperLines[1].y1} x2={helperLines[1].x2} y2={helperLines[1].y2} class-helper-line />
				</g>
			);
		}
		return vnode;
	}
}
