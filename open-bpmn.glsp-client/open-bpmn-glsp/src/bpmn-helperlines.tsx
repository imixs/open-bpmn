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
import { inject, injectable, postConstruct } from 'inversify';
import {
	AbstractUIExtension, EditModeListener,
	EditorContextService, ActionDispatcher, SConnectableElement,
	SetUIExtensionVisibilityAction,
	SModelRoot, Action,
	MouseListener, SModelElement, IMouseTool,
	TYPES
} from '@eclipse-glsp/client';
import { VNode } from 'snabbdom';
import {
	SelectionListener,
	SelectionService
} from '@eclipse-glsp/client/lib/features/select/selection-service';
import { svg, RenderingContext, SShapeElement, ShapeView,getAbsoluteBounds} from 'sprotty';

import { Point } from '@eclipse-glsp/protocol';

// import { isBPMNFlowElement, isConnectableElement } from '@open-bpmn/open-bpmn-model';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };
/****************************************************************************
 * This module provides UIExtension to support BPMN helper lines. 
 * HelperLines are displayed when a element is selected dand draged on the diagram pane.
 ****************************************************************************/


export class HelpLineNode extends SShapeElement {
	//static override readonly DEFAULT_FEATURES = [boundsFeature];
	startPoint: Point;
	endPoint: Point;
}

@injectable()
export class HelpLineView extends ShapeView {

	render(element: HelpLineNode, context: RenderingContext): VNode | undefined {
		const graph: any = (
			<g>
				<rect
					class-sprotty-node={true}
					class-marquee={true}
					x={10}
					y={20}
					rx={0}
					ry={0}
					width={250}
					height={120}
				></rect>
			</g>
		);
		return graph;
	}
}

@injectable()
export class BPMNHelperLine extends AbstractUIExtension implements EditModeListener, SelectionListener {

	protected bpmnMouseListener: BPMNMouseListener;

	// protected selectedElement: SModelElement & Connectable;
	// protected selectedElement?: SModelElement;

	static readonly ID = 'bpmn-helper-line';

	@inject(EditorContextService)
	protected readonly editorContext: EditorContextService;

	@inject(TYPES.IActionDispatcher)
	protected readonly actionDispatcher: ActionDispatcher;

	@inject(TYPES.MouseTool)
	protected mouseTool: IMouseTool;

	@inject(SelectionService)
	protected selectionService: SelectionService;

	protected bodyDiv: HTMLElement;
	modelRootId: string;

	/*
	 * We register a selectionService to react on element selctions
	 * and a mouseListener to react on daggign selected elements
	 */
	@postConstruct()
	postConstruct(): void {
		this.selectionService.register(this);
		this.editorContext.register(this);
		this.bpmnMouseListener = new BPMNMouseListener();
		this.mouseTool.register(this.bpmnMouseListener);
	}

	id(): string {
		return BPMNHelperLine.ID;
	}

	containerClass(): string {
		return BPMNHelperLine.ID;
	}

	/*
	 * dispatch editMode events
	 * (for what ever...?)
	 */
	editModeChanged(_oldValue: string, _newValue: string): void {
		console.log('======== > BPMNHelperLine ... editModeChanged: ' + _newValue);
		this.actionDispatcher.dispatch(SetUIExtensionVisibilityAction.create(
			{
				extensionId: BPMNHelperLine.ID,
				visible: !this.editorContext.isReadonly
			}
		));
	}

	protected onBeforeShow(_containerElement: HTMLElement, _root: Readonly<SModelRoot>): void {
		console.log('...--------BPMNHelperLine bin in onBeforeShow: ');
		this.bpmnMouseListener.containerElement = _containerElement;
		this.bpmnMouseListener.root = _root;
		console.log(' HtmlContainer ID = ' + _containerElement.id);
	}

	/*
	 * Initalize the helperLine html element
	 */
	protected initializeContents(_containerElement: HTMLElement): void {
		console.log('======== > BPMNHelperLine initializeContents');
	}

	/*
	 * On selectionChange we set the current nodeElement into 
	 * the BPMNMouseListener
	 */
	selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
		console.log('======== BPMNHelperLine > selectionChanged...');
		if (this.selectionService.isSingleSelection()) {

			const selectedElement = root.index.getById(selectedElements[0]);
			if (selectedElement instanceof SConnectableElement) {
				console.log(' wir haben eine connectable ');
				const connectableElement: SConnectableElement = selectedElement;
				if (connectableElement) {
					console.log(' jett setuem word doe  connectable ');
					this.bpmnMouseListener.selectedElement = connectableElement;
				}
			}
		}
		// disable selection
		this.bpmnMouseListener.selectedElement = undefined;
	}
}

export class BPMNMouseListener extends MouseListener {
	private _isMouseDown = false;
	// private lastPosition: Point | undefined;
	protected _selectedElement: SConnectableElement | undefined;
	private _containerElement: HTMLElement | undefined;
	private _root: Readonly<SModelRoot>

	@inject(HelpLineView)
	protected readonly helperLIne: HelpLineView;

	override mouseDown(target: SModelElement, event: MouseEvent): Action[] {
		this._isMouseDown = true;
		console.log('======== > BPMNMouseListener mouseDown... targetID=' + target.id);
		return [];
	}
	mouseUp(target: SModelElement, event: MouseEvent): (Action | Promise<Action>)[] {
		return [];
	}

	/**
	 * We are only interested in DragMode - mouseDown and element selected!
	 **/
	override mouseMove(target: SModelElement, event: MouseEvent): Action[] {
		if ( this.isMouseDown &&  target) {
			
			// do we have a node element selected...?
			const b=getAbsoluteBounds(target);
			
			if (b && b.x>0) {
				console.log('.... bounds target: x='+b.x + " y="+b.y + '  id='+target.id);
				//const targetElement = document.getElementById(target.id);
				// [id$='someId']
				const targetElement = document.querySelector('[id$="' + target.id + '"]')
				
				// const targetElement = event.target instanceof SVGElement ? event.target : undefined;
				const svgParentElement = targetElement?.closest('svg');
				if (svgParentElement) {
					// svgParentElement.focus();
					console.log('... svg Element found id=' + svgParentElement.id);
					const newLine = document.createElementNS('http://www.w3.org/2000/svg', 'line');
					newLine.setAttribute('id', 'line2');
					newLine.setAttribute('x1', '0');
					newLine.setAttribute('y1', '' + b.y);
					newLine.setAttribute('x2', '200');
					newLine.setAttribute('y2', '' + b.y);
					newLine.setAttribute("stroke", "green");
					svgParentElement.appendChild(newLine);
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

	set selectedElement(element: SConnectableElement | undefined) {
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
	}
}
