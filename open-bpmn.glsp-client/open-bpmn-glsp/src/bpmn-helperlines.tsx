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
	Action,SModelElement, SModelRoot, MouseListener, FeedbackCommand, hasObjectProp,toAbsoluteBounds
} from '@eclipse-glsp/client';
import { inject, injectable } from 'inversify';
import {
	isBoundsAware, isSelectable,
	SNode,SChildElement,
	CommandExecutionContext,
	CommandReturn,
	svg, IView, RenderingContext,getAbsoluteBounds,
	TYPES
} from 'sprotty';
import { VNode } from 'snabbdom';
import { Bounds, Point } from 'sprotty-protocol';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/****************************************************************************
 * This module extends the mouseListener to support helper lines. 
 * HelperLines are displayed when a element is selected and draged on the diagram pane.
 * 
 * If the moved element is alligned to the center of another element on the diagram
 * the helper line is shown. There are can be up to 4 helperlines be displayed
 * - north -south -west -east
 ****************************************************************************/
 
 export interface Line {
    readonly x1: number
    readonly y1: number
    readonly x2: number
    readonly y2: number
}

@injectable()
export class HelperLineListener extends MouseListener {
	protected previouslySelected: string[];
	protected isActive = false;
	protected dreck: Point;

	override mouseDown(target: SModelElement, event: MouseEvent): Action[] {
		// check if target is relevant....
		if (isSelectable(target) && target instanceof SNode) {
			// switch into active mode
			this.isActive = true;
		} else {
			this.isActive = false;
		}
		return [];
	}

	/**
	 * This method acts on a mouseMove if a element is selected (isActive==true).
	 * The method computes alligned element in the diagram and fires the corresponding
	 * command to show the helper lines.
	 */
	override mouseMove(target: SModelElement, event: MouseEvent): Action[] {
		//this.marqueeUtil.updateCurrentPoint(getAbsolutePosition(target, event));
		if (this.isActive && isBoundsAware(target)) {
			// const nodeIdsSelected = this.nodes.filter(e => this.marqueeUtil.isNodeMarked(toAbsoluteBounds(e))).map(e => e.id);
			// const selected = nodeIdsSelected.concat(edgeIdsSelected);

			// const targetBounds = getAbsoluteBounds(target);
			// const elementCenter = Bounds.center(getAbsoluteBounds(target));
			const elementCenter = Bounds.center(target.bounds);
			console.log(' ===> mouseMove  target center bounds : x=' + elementCenter.x + " y=" + elementCenter.y + '  id=' + target.id);
			const superLine: Line | undefined = this.findFittingElement(target);
			if (superLine) {
				console.log(' ===> found match x=' + superLine.x1 + ' y='+superLine.y1);
				return [DrawHelperLinesAction.create({ horizontalLine: superLine, verticalLine: superLine })];
			} else {
				// now match! remove helper lines...
				console.log('...create RemoveHelperLinesAction .....');
				return [RemoveHelperLinesAction.create()];
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
	 * This helper method searches the model for a model element
	 * fitting the alligment of the given modelElement.
	 *
	 * A ModelElement is a alligned to another element if its center point
	 * on the same x or y axis.
	 *
	 * If more than one elment fits the alligment of the given Element
	 * the method returns the the farthest one.
	 */
	private findFittingElement(modelElement: SModelElement): Line | undefined {
		const root: SModelRoot = modelElement.root;
		if (root && isBoundsAware(modelElement)) {
			const childs = root.children;
			
			// absolute.....
			const absolute=toAbsoluteBounds(modelElement);
			if (absolute) {
				console.log(' ===> findFitting dragedElement origin  x='+modelElement.bounds.x + 'y='+modelElement.bounds.y);
				console.log(' ===> findFitting dragedElement absolut x='+absolute.x + 'y='+absolute.y);
				
				
			}
			const absolutSprottyCenterBounds=Bounds.center(getAbsoluteBounds(modelElement));
			if (absolutSprottyCenterBounds) {
				console.log(' ===> findFitting sprotty absolut centr x='+absolutSprottyCenterBounds.x + 'y='+absolutSprottyCenterBounds.y);
				
			}
			
			const modelElementCenter = Bounds.center(modelElement.bounds);
			console.log(' ===> findFitting dragedElement center x='+modelElementCenter.x + 'y='+modelElementCenter.y);
			
			
			
			const canvasBounds=root.canvasBounds;
			console.log('===> findFitting canvasBounds x='+canvasBounds.x + ' width='+canvasBounds.width + ' y='+canvasBounds.y + ' height='+canvasBounds.height);
			
			// In the following we iterate over all model elements
			// and compare the x and y axis of the center points 
			for (const element of childs) {
				if (element.id !== modelElement.id) {
					if (isBoundsAware(element)) {
						const elementCenter = Bounds.center(element.bounds);
						console.log(' ===> findFitting check absolut ID=' + element.id + ' x='+elementCenter.x + " y="+elementCenter.y);
						// test vertical alligment...
						if (elementCenter.y === modelElementCenter.y) {
							console.log(' ===> findFitting found y-center point x=' + elementCenter.x + ' y=' + elementCenter.y + ' (element x=' + element.bounds.x + ' y=' + element.bounds.y + ')');
							
							// berechne den Superpunkt
							
							const superEndPoint: Point = {
								x:modelElementCenter.x-absolutSprottyCenterBounds.x+canvasBounds.width,
								y:elementCenter.y
							}
							if (superEndPoint) {
								console.log('==> das ideale ende wäre '+superEndPoint.x);
							}
							
							const horizontalLine: Line = {
								x1:modelElementCenter.x-absolutSprottyCenterBounds.x,
								y1:elementCenter.y,
								x2:modelElementCenter.x-absolutSprottyCenterBounds.x+canvasBounds.width,
								y2:elementCenter.y
							}
							
							
							//return superPoint;
							return horizontalLine;
							
							// return elementCenter;
						}
						// test horizontal alligment...
						if (elementCenter.x === modelElementCenter.x) {
							console.log(' ===> findFitting found y-center point x=' + elementCenter.x + ' y=' + elementCenter.y + ' (element x=' + element.bounds.x + ' y=' + element.bounds.y + ')');
							
							const verticalLine: Line = {
								y1:modelElementCenter.y-absolutSprottyCenterBounds.y,
								x1:elementCenter.x,
								y2:modelElementCenter.y-absolutSprottyCenterBounds.y+canvasBounds.height,
								x2:elementCenter.x
							}
							
							return verticalLine;
							
							//return elementCenter;
						}
					}
				}
			}
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
 * 
 */
export interface DrawHelperLinesAction extends Action {
	kind: typeof DrawHelperLinesAction.KIND;
	horizontalLine: Line;
	verticalLine: Line;
}
export namespace DrawHelperLinesAction {
	export const KIND = 'drawHelperLines';

	export function is(object: any): object is DrawHelperLinesAction {
		return Action.hasKind(object, KIND) && hasObjectProp(object, 'startPoint') && hasObjectProp(object, 'endPoint');
	}

	export function create(options: { horizontalLine: Line; verticalLine: Line }): DrawHelperLinesAction {
		return {
			kind: KIND,
			...options
		};
	}
}

/*
 * The HelperLinesCommand creates the HelperLine schema element 
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
		// create new model schema
		const helperLinesSchema = {
			type: HELPLINE,
			id: helpLineId(root),
			horizontalLine: this.action.horizontalLine,
			verticalLine: this.action.verticalLine
		};
		const helperLines = context.modelFactory.createElement(helperLinesSchema);
		root.add(helperLines);
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
 * HelperLine Command to remove the helperline form the model
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
 * The HelperLineView shows the helper lines
 */
@injectable()
export class HelperLineView implements IView {
	render(model: Readonly<SModelElement>, context: RenderingContext): VNode {
		/*const startPoint: Point = (model as any).startPoint ?? Point.ORIGIN;
		const endPoint: Point = (model as any).endPoint ?? Point.ORIGIN;*/
		
		const horizontalLine: Line = (model as any).horizontalLine ?? {x1:0,y1:0,x2:0,y2:0};
		/*const root=model.root;
		const canvasBounds=root.canvasBounds;
		console.log('==> canvasBounds x='+canvasBounds.x + ' width='+canvasBounds.width + ' y='+canvasBounds.y + ' height='+canvasBounds.height);*/
		const vnode: any = (			
			<line x1={horizontalLine.x1} y1={horizontalLine.y1} x2={horizontalLine.x2} y2={horizontalLine.y2} class-helper-line />
		);
		return vnode;
	}
}
