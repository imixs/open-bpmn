/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import { Action, CreateNodeOperation, isTriggerNodeCreationAction, TriggerNodeCreationAction } from '@eclipse-glsp/protocol';
import { inject, injectable, optional } from 'inversify';
import {
    EnableDefaultToolsAction,
    EnableToolsAction,
    findParentByFeature,
    IActionHandler,
    isCtrlOrCmd,
    ISnapper,
    SModelElement,
    SNode,
    TYPES
} from 'sprotty';
import { DragAwareMouseListener } from '../../base/drag-aware-mouse-listener';
import { getAbsolutePosition } from '../../utils/viewpoint-util';
import { Containable, isContainable } from '../hints/model';
import { CursorCSS, cursorFeedbackAction } from '../tool-feedback/css-feedback';
import { BaseGLSPTool } from './base-glsp-tool';

@injectable()
export class NodeCreationTool extends BaseGLSPTool implements IActionHandler {
    static ID = 'tool_create_node';

    @inject(TYPES.ISnapper) @optional() readonly snapper?: ISnapper;

    protected creationToolMouseListener: NodeCreationToolMouseListener;
    protected triggerAction: TriggerNodeCreationAction;

    get id(): string {
        return NodeCreationTool.ID;
    }

    enable(): void {
        if (this.triggerAction === undefined) {
            throw new TypeError(`Could not enable tool ${this.id}.The triggerAction cannot be undefined.`);
        }
        this.creationToolMouseListener = new NodeCreationToolMouseListener(this.triggerAction, this);
        this.mouseTool.register(this.creationToolMouseListener);
        this.dispatchFeedback([cursorFeedbackAction(CursorCSS.NODE_CREATION)]);
    }

    disable(): void {
        this.mouseTool.deregister(this.creationToolMouseListener);
        this.deregisterFeedback([cursorFeedbackAction()]);
    }

    handle(action: Action): Action | void {
        if (isTriggerNodeCreationAction(action)) {
            this.triggerAction = action;
            return new EnableToolsAction([this.id]);
        }
    }
}

@injectable()
export class NodeCreationToolMouseListener extends DragAwareMouseListener {
    protected container?: SModelElement & Containable;

    constructor(protected triggerAction: TriggerNodeCreationAction, protected tool: NodeCreationTool) {
        super();
    }

    protected creationAllowed(elementTypeId: string): boolean | undefined {
        return this.container && this.container.isContainableElement(elementTypeId);
    }

    get elementTypeId(): string {
        return this.triggerAction.elementTypeId;
    }

    nonDraggingMouseUp(target: SModelElement, event: MouseEvent): Action[] {
        const result: Action[] = [];
        if (this.creationAllowed(this.elementTypeId)) {
            const containerId = this.container ? this.container.id : undefined;
            let location = getAbsolutePosition(target, event);
            if (this.tool.snapper) {
                // Create a 0-bounds proxy element for snapping
                const elementProxy = new SNode();
                elementProxy.size = { width: 0, height: 0 };
                location = this.tool.snapper.snap(location, elementProxy);
            }
            result.push(new CreateNodeOperation(this.elementTypeId, location, containerId, this.triggerAction.args));
            if (!isCtrlOrCmd(event)) {
                result.push(new EnableDefaultToolsAction());
            }
        }
        return result;
    }

    mouseOver(target: SModelElement, event: MouseEvent): Action[] {
        const currentContainer = findParentByFeature(target, isContainable);
        if (!this.container || currentContainer !== this.container) {
            this.container = currentContainer;
            const feedback = this.creationAllowed(this.elementTypeId)
                ? cursorFeedbackAction(CursorCSS.NODE_CREATION)
                : cursorFeedbackAction(CursorCSS.OPERATION_NOT_ALLOWED);
            this.tool.dispatchFeedback([feedback]);
        }
        return [];
    }
}
