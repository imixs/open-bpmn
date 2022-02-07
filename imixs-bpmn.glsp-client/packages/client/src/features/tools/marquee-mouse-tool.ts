/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { Action, SelectAction } from '@eclipse-glsp/protocol';
import { inject, injectable, optional } from 'inversify';
import {
    BoundsAware,
    EnableDefaultToolsAction,
    isSelectable,
    isSelected,
    KeyListener,
    SEdge,
    SModelElement,
    SModelRoot,
    SNode,
    TYPES
} from 'sprotty';
import { DOMHelper } from 'sprotty/lib/base/views/dom-helper';
import { GLSP_TYPES } from '../../base/types';
import { DragAwareMouseListener } from '../../base/drag-aware-mouse-listener';
import { getAbsolutePosition, toAbsoluteBounds } from '../../utils/viewpoint-util';
import { CursorCSS, cursorFeedbackAction } from '../tool-feedback/css-feedback';
import { RemoveMarqueeAction } from '../tool-feedback/marquee-tool-feedback';
import { BaseGLSPTool } from './base-glsp-tool';
import { IMarqueeBehavior, MarqueeUtil } from './marquee-behavior';

@injectable()
export class MarqueeMouseTool extends BaseGLSPTool {
    static ID = 'glsp.marquee-mouse-tool';

    @inject(TYPES.DOMHelper) protected domHelper: DOMHelper;
    @inject(GLSP_TYPES.IMarqueeBehavior) @optional() protected marqueeBehavior: IMarqueeBehavior;

    protected marqueeMouseListener: MarqueeMouseListener;
    protected shiftKeyListener: ShiftKeyListener = new ShiftKeyListener();

    get id(): string {
        return MarqueeMouseTool.ID;
    }

    enable(): void {
        this.marqueeMouseListener = new MarqueeMouseListener(this.domHelper, this.editorContext.modelRoot, this.marqueeBehavior);
        this.mouseTool.register(this.marqueeMouseListener);
        this.keyTool.register(this.shiftKeyListener);
        this.dispatchFeedback([cursorFeedbackAction(CursorCSS.MARQUEE)]);
    }

    disable(): void {
        this.mouseTool.deregister(this.marqueeMouseListener);
        this.keyTool.deregister(this.shiftKeyListener);
        this.deregisterFeedback([cursorFeedbackAction()]);
    }
}

export class MarqueeMouseListener extends DragAwareMouseListener {
    protected domHelper: DOMHelper;
    protected marqueeUtil: MarqueeUtil;
    protected nodes: (SModelElement & BoundsAware)[];
    protected edges: SVGGElement[];
    protected previouslySelected: string[];
    protected isActive = false;

    constructor(domHelper: DOMHelper, root: SModelRoot, marqueeBehavior: IMarqueeBehavior | undefined) {
        super();
        this.domHelper = domHelper;
        this.marqueeUtil = new MarqueeUtil(marqueeBehavior);
        this.nodes = Array.from(
            root.index
                .all()
                .map(e => e as SModelElement & BoundsAware)
                .filter(e => isSelectable(e))
                .filter(e => e instanceof SNode)
        );
        const sEdges = Array.from(
            root.index
                .all()
                .filter(e => e instanceof SEdge)
                .filter(e => isSelectable(e))
                .map(e => e.id)
        );
        this.edges = Array.from(document.querySelectorAll('g')).filter(e => sEdges.includes(this.domHelper.findSModelIdByDOMElement(e)));
    }

    mouseDown(target: SModelElement, event: MouseEvent): Action[] {
        this.isActive = true;
        this.marqueeUtil.updateStartPoint(getAbsolutePosition(target, event));
        if (event.ctrlKey) {
            this.previouslySelected = Array.from(
                target.root.index
                    .all()
                    .map(e => e as SModelElement & BoundsAware)
                    .filter(e => isSelected(e))
                    .map(e => e.id)
            );
        }
        return [];
    }

    mouseMove(target: SModelElement, event: MouseEvent): Action[] {
        this.marqueeUtil.updateCurrentPoint(getAbsolutePosition(target, event));
        if (this.isActive) {
            const nodeIdsSelected = this.nodes.filter(e => this.marqueeUtil.isNodeMarked(toAbsoluteBounds(e))).map(e => e.id);
            const edgeIdsSelected = this.edges.filter(e => this.isEdgeMarked(e)).map(e => this.domHelper.findSModelIdByDOMElement(e));
            const selected = nodeIdsSelected.concat(edgeIdsSelected);
            return [
                new SelectAction([], Array.from(target.root.index.all().map(e => e.id))),
                new SelectAction(selected.concat(this.previouslySelected), []),
                this.marqueeUtil.drawMarqueeAction()
            ];
        }
        return [];
    }

    mouseUp(target: SModelElement, event: MouseEvent): Action[] {
        this.isActive = false;
        if (event.shiftKey) {
            return [new RemoveMarqueeAction()];
        }
        return [new RemoveMarqueeAction(), new EnableDefaultToolsAction()];
    }

    isEdgeMarked(element: SVGElement): boolean {
        if (!element.getAttribute('transform')) {
            if (element.children[0]) {
                const path = element.children[0].getAttribute('d');
                return this.marqueeUtil.isEdgePathMarked(path);
            }
        }
        return false;
    }
}

@injectable()
export class ShiftKeyListener extends KeyListener {
    keyUp(element: SModelElement, event: KeyboardEvent): Action[] {
        if (event.shiftKey) {
            return [];
        }
        return [new RemoveMarqueeAction(), new EnableDefaultToolsAction()];
    }
}
