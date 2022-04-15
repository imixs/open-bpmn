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
	MouseListener, SModelElement
} from '@eclipse-glsp/client';

import { getAbsoluteBounds} from 'sprotty';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
// const JSX = { createElement: svg };
/****************************************************************************
 * This module provides UIExtension to support BPMN helper lines. 
 * HelperLines are displayed when a element is selected dand draged on the diagram pane.
 ****************************************************************************/

export class BPMNMouseListener extends MouseListener {
	private _isMouseDown = false;
	// private lastPosition: Point | undefined;
/*	protected _selectedElement: SConnectableElement | undefined;
	private _containerElement: HTMLElement | undefined;
	private _root: Readonly<SModelRoot>;*/
	private helperLine: any; 

	override mouseDown(target: SModelElement, event: MouseEvent): Action[] {
		this._isMouseDown = true;
		console.log('======== > BPMNMouseListener mouseDown... targetID=' + target.id);
		return [];
	}
	mouseUp(target: SModelElement, event: MouseEvent): (Action | Promise<Action>)[] {
		// remove old helper line
		if (this.helperLine) {
			console.log('remove old helper line');
			document.removeChild(this.helperLine);
			this.helperLine=undefined;
		}
		return [];
		
	}

	/**
	 * We are only interested in DragMode - mouseDown and element selected!
	 **/
	override mouseMove(target: SModelElement, event: MouseEvent): Action[] {
		if ( this.isMouseDown &&  target) {
			
			// remove old helper line
			/*if (this.helperLine) {
				console.log('remove old helper line');
				document.removeChild(this.helperLine);
				this.helperLine=undefined;
			}*/

			// do we have a node element selected...?
			const b=getAbsoluteBounds(target);
			if (b && b.x>0) {
				console.log('.... bounds target: x='+b.x + " y="+b.y + '  id='+target.id);
				const targetElement = document.querySelector('[id$="' + target.id + '"]')
				// const targetElement = event.target instanceof SVGElement ? event.target : undefined;
				const svgParentElement = targetElement?.closest('svg');
				if (svgParentElement) {
					// console.log('... svg Element found id=' + svgParentElement.id);
					
					if (!this.helperLine) {
						this.helperLine = document.createElementNS('http://www.w3.org/2000/svg', 'line');
						svgParentElement.appendChild(this.helperLine);
					}
					
					this.helperLine.setAttribute('id', svgParentElement.id + '_bpmn_helperline_west');
					this.helperLine.setAttribute('x1', '0');
					this.helperLine.setAttribute('y1', '' + b.y);
					this.helperLine.setAttribute('x2', '' + b.x);
					this.helperLine.setAttribute('y2', '' + b.y);
					this.helperLine.setAttribute("stroke", "green");
					
					
					
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

/*	set selectedElement(element: SConnectableElement | undefined) {
		this._selectedElement = element;
	}

	get selectedElement(): SConnectableElement | undefined {
		return this._selectedElement;
	}

	set containerElement(element: HTMLElement | undefined) {
		this._containerElement = element;
	}

	get containerElement(): HTMLElement | undefined {
		return this._containerElement;
	}

	set root(element: SModelRoot) {
		this._root = element;
	}

	get root(): SModelRoot {
		return this._root;
	}*/
}
