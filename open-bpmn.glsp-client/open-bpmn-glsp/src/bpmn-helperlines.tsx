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
	Action,
	MouseListener, SModelElement, SModelRoot
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { getAbsoluteBounds, isBoundsAware } from 'sprotty';
import { Bounds, Point } from 'sprotty-protocol';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
// const JSX = { createElement: svg };
/****************************************************************************
 * This module extends the mouseListener to support helper lines. 
 * HelperLines are displayed when a element is selected and draged on the diagram pane.
 * 
 * If the moved element is alligned to the center of another elemen ton the diagram
 * the helper line is shown. There are two helperlines - north-south and west-east
 ****************************************************************************/
@injectable()
export class BPMNHelperLineTool extends MouseListener {
	private _isMouseDown = false;
	private helperLineWestEast: any;
	private helperLineNorthSouth: any;

	override mouseDown(target: SModelElement, event: MouseEvent): Action[] {
		this._isMouseDown = true;
		return [];
	}
	mouseUp(target: SModelElement, event: MouseEvent): (Action | Promise<Action>)[] {
		this._isMouseDown = false;
		// remove old helper lines
		this.deleteHelperLines();
		return [];
	}

	/**
	 * We are only interested in DragMode - mouseDown and element selected!
	 **/
	override mouseMove(target: SModelElement, event: MouseEvent): Action[] {
		if (this.isMouseDown && target) {

			// do we have a node element selected...?
			// TODO this question is not as exactly as it should be!
			const targetBounds = getAbsoluteBounds(target);
			if (targetBounds && targetBounds.x > 0) {
				console.log('.... bounds target: x=' + targetBounds.x + " y=" + targetBounds.y + '  id=' + target.id);
				const targetElement = document.querySelector('[id$="' + target.id + '"]')
				// const targetElement = event.target instanceof SVGElement ? event.target : undefined;
				const svgParentElement = targetElement?.closest('svg');
				if (svgParentElement) {
					// console.log('... svg Element found id=' + svgParentElement.id);

					const allignedCenterPoint: Point | undefined = this.findFittingElement(target);
					if (allignedCenterPoint) {
						this.initHelperLines(svgParentElement);
						const modelElementCenter = Bounds.center(targetBounds);
						console.log(' model center point x='+modelElementCenter.x + ' y='+modelElementCenter.y);	
						console.log(' allignment   point x='+allignedCenterPoint.x + ' y='+allignedCenterPoint.y);	
						this.helperLineWestEast.setAttribute('id', svgParentElement.id + '_bpmn_helperline_west-east');
						this.helperLineWestEast.setAttribute('x1', '' + modelElementCenter.x);
						this.helperLineWestEast.setAttribute('y1', '' + modelElementCenter.y);
						this.helperLineWestEast.setAttribute('x2', '' + allignedCenterPoint.x);
						this.helperLineWestEast.setAttribute('y2', '' + allignedCenterPoint.y);
						this.helperLineWestEast.setAttribute("stroke", "green");
					} else {
						this.deleteHelperLines();
					}

					console.log('line completed');
				} else {
					console.log('problem no svg element found');
				}
			}
		}
		return [];
	}

	get isMouseDown(): boolean {
		return this._isMouseDown;
	}


	/*
	 * This helper method searches the model for a model element
	 * fitting the alligment of the given modelElement.
	 * 
	 * A ModelElement is a alligned to another element if its center point
	 * on the same x or y axis. 
	 * If more than one elment fits the alligment of the given Element
	 * the method returns the neares one. 
	 */
	private findFittingElement(modelElement: SModelElement): Point | undefined {
		const root: SModelRoot = modelElement.root;
		if (root && isBoundsAware(modelElement)) {

			//const modelElementCenter = Bounds.center(modelElement.bounds);
			const modelElementCenter = Bounds.center(getAbsoluteBounds(modelElement));
			
			const childs = root.children;
			// iterate over all model elements

			for (const element of childs) {
				if (element.id !== modelElement.id) {
					if (isBoundsAware(element)) {
						//const elementCenter = Bounds.center(element.bounds);
						const elementCenter = Bounds.center(getAbsoluteBounds(element));
						
						// test vertical alligment...
						if (elementCenter.y === modelElementCenter.y) {
							console.log('found y-center point x=' + elementCenter.x + ' y=' + elementCenter.y + ' (element x=' + element.bounds.x+' y='+element.bounds.y+')');
							console.log('        modelElement x=' + modelElement.bounds.x + ' y=' + modelElement.bounds.y);
							return elementCenter;
						}
						// test horizontal alligment...
						if (elementCenter.x === modelElementCenter.x) {
							console.log('found x-center point x=' + elementCenter.x + ' y=' + elementCenter.y+ ' (element x=' + element.bounds.x+' y='+element.bounds.y+')');
							return elementCenter;
						}
					}
				}
			}
		}
		return undefined;
	}

	/**
	 * This is a helper method that initalizes the helper lines and 
	 * adds the lines into the diagram panel
	 */
	private initHelperLines(svgParentElement: any) {
		if (!this.helperLineWestEast) {
			this.helperLineWestEast = document.createElementNS('http://www.w3.org/2000/svg', 'line');
			svgParentElement.appendChild(this.helperLineWestEast);
		}
		if (!this.helperLineNorthSouth) {
			this.helperLineNorthSouth = document.createElementNS('http://www.w3.org/2000/svg', 'line');
			svgParentElement.appendChild(this.helperLineNorthSouth);
		}
	}

	/**
	 * This is a helper method that removes the helper lines from the diagram panel
	 */
	private deleteHelperLines() {
		if (this.helperLineWestEast) {
			document.removeChild(this.helperLineWestEast);
			this.helperLineWestEast = undefined;
		}
		if (this.helperLineNorthSouth) {
			document.removeChild(this.helperLineNorthSouth);
			this.helperLineNorthSouth = undefined;
		}
	}
}
