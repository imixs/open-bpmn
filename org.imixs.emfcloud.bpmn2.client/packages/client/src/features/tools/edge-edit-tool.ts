/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { Action, ChangeRoutingPointsOperation, ReconnectEdgeOperation } from '@eclipse-glsp/protocol';
import { inject, injectable, optional } from 'inversify';
import {
    AnchorComputerRegistry,
    canEditRouting,
    Connectable,
    EdgeRouterRegistry,
    findParentByFeature,
    isConnectable,
    isSelected,
    SModelElement,
    SModelRoot,
    SRoutableElement,
    SRoutingHandle
} from 'sprotty';
import { DragAwareMouseListener } from '../../base/drag-aware-mouse-listener';
import { GLSP_TYPES } from '../../base/types';
import { isRoutable, isRoutingHandle } from '../../utils/smodel-util';
import { isReconnectable, isReconnectHandle, isSourceRoutingHandle, isTargetRoutingHandle, SReconnectHandle } from '../reconnect/model';
import { SelectionListener, SelectionService } from '../select/selection-service';
import { DrawFeedbackEdgeAction, feedbackEdgeId, RemoveFeedbackEdgeAction } from '../tool-feedback/creation-tool-feedback';
import { CursorCSS, cursorFeedbackAction } from '../tool-feedback/css-feedback';
import {
    DrawFeedbackEdgeSourceAction,
    FeedbackEdgeRouteMovingMouseListener,
    FeedbackEdgeSourceMovingMouseListener,
    FeedbackEdgeTargetMovingMouseListener,
    HideEdgeReconnectHandlesFeedbackAction,
    ShowEdgeReconnectHandlesFeedbackAction,
    SwitchRoutingModeAction
} from '../tool-feedback/edge-edit-tool-feedback';
import { BaseGLSPTool } from './base-glsp-tool';

@injectable()
export class EdgeEditTool extends BaseGLSPTool {
    static ID = 'glsp.edge-edit-tool';

    @inject(GLSP_TYPES.SelectionService) protected selectionService: SelectionService;
    @inject(AnchorComputerRegistry) protected anchorRegistry: AnchorComputerRegistry;
    @inject(EdgeRouterRegistry) @optional() protected edgeRouterRegistry?: EdgeRouterRegistry;

    protected feedbackEdgeSourceMovingListener: FeedbackEdgeSourceMovingMouseListener;
    protected feedbackEdgeTargetMovingListener: FeedbackEdgeTargetMovingMouseListener;
    protected feedbackMovingListener: FeedbackEdgeRouteMovingMouseListener;
    protected edgeEditListener: EdgeEditListener;

    get id(): string {
        return EdgeEditTool.ID;
    }

    enable(): void {
        this.edgeEditListener = new EdgeEditListener(this);
        this.mouseTool.register(this.edgeEditListener);
        this.selectionService.register(this.edgeEditListener);

        // install feedback move mouse listener for client-side move updates
        this.feedbackEdgeSourceMovingListener = new FeedbackEdgeSourceMovingMouseListener(this.anchorRegistry);
        this.feedbackEdgeTargetMovingListener = new FeedbackEdgeTargetMovingMouseListener(this.anchorRegistry);
        this.feedbackMovingListener = new FeedbackEdgeRouteMovingMouseListener(this.edgeRouterRegistry);
    }

    registerFeedbackListeners(): void {
        this.mouseTool.register(this.feedbackMovingListener);
        this.mouseTool.register(this.feedbackEdgeSourceMovingListener);
        this.mouseTool.register(this.feedbackEdgeTargetMovingListener);
    }

    deregisterFeedbackListeners(): void {
        this.mouseTool.deregister(this.feedbackEdgeSourceMovingListener);
        this.mouseTool.deregister(this.feedbackEdgeTargetMovingListener);
        this.mouseTool.deregister(this.feedbackMovingListener);
    }

    disable(): void {
        this.edgeEditListener.reset();
        this.selectionService.deregister(this.edgeEditListener);
        this.deregisterFeedbackListeners();
        this.mouseTool.deregister(this.edgeEditListener);
    }
}

class EdgeEditListener extends DragAwareMouseListener implements SelectionListener {
    // active selection data
    protected edge?: SRoutableElement;
    protected routingHandle?: SRoutingHandle;

    // new connectable (source or target) for edge
    protected newConnectable?: SModelElement & Connectable;

    // active reconnect handle data
    protected reconnectMode?: 'NEW_SOURCE' | 'NEW_TARGET';

    constructor(protected tool: EdgeEditTool) {
        super();
    }

    protected isValidEdge(edge?: SRoutableElement): edge is SRoutableElement {
        return edge !== undefined && edge.id !== feedbackEdgeId(edge.root) && isSelected(edge);
    }

    protected setEdgeSelected(edge: SRoutableElement): void {
        if (this.edge && this.edge.id !== edge.id) {
            // reset from a previously selected edge
            this.reset();
        }

        this.edge = edge;
        // note: order is important here as we want the reconnect handles to cover the routing handles
        const feedbackActions = [];
        if (canEditRouting(edge)) {
            feedbackActions.push(new SwitchRoutingModeAction([this.edge.id], []));
        }
        if (isReconnectable(edge)) {
            feedbackActions.push(new ShowEdgeReconnectHandlesFeedbackAction(this.edge.id));
        }
        this.tool.dispatchFeedback(feedbackActions);
    }

    protected isEdgeSelected(): boolean {
        return this.edge !== undefined && isSelected(this.edge);
    }

    protected setReconnectHandleSelected(edge: SRoutableElement, reconnectHandle: SReconnectHandle): void {
        if (this.edge && this.edge.target && this.edge.source) {
            if (isSourceRoutingHandle(edge, reconnectHandle)) {
                this.tool.dispatchFeedback([
                    new HideEdgeReconnectHandlesFeedbackAction(),
                    cursorFeedbackAction(CursorCSS.EDGE_RECONNECT),
                    new DrawFeedbackEdgeSourceAction(this.edge.type, this.edge.targetId)
                ]);
                this.reconnectMode = 'NEW_SOURCE';
            } else if (isTargetRoutingHandle(edge, reconnectHandle)) {
                this.tool.dispatchFeedback([
                    new HideEdgeReconnectHandlesFeedbackAction(),
                    cursorFeedbackAction(CursorCSS.EDGE_CREATION_TARGET),
                    new DrawFeedbackEdgeAction(this.edge.type, this.edge.sourceId)
                ]);
                this.reconnectMode = 'NEW_TARGET';
            }
        }
    }

    protected isReconnecting(): boolean {
        return this.reconnectMode !== undefined;
    }

    protected isReconnectingNewSource(): boolean {
        return this.reconnectMode === 'NEW_SOURCE';
    }

    protected setRoutingHandleSelected(edge: SRoutableElement, routingHandle: SRoutingHandle): void {
        if (this.edge && this.edge.target && this.edge.source) {
            this.routingHandle = routingHandle;
        }
    }

    protected requiresReconnect(sourceId: string, targetId: string): boolean {
        return this.edge !== undefined && (this.edge.sourceId !== sourceId || this.edge.targetId !== targetId);
    }

    protected setNewConnectable(connectable?: SModelElement & Connectable): void {
        this.newConnectable = connectable;
    }

    protected isReadyToReconnect(): boolean | undefined {
        return this.edge && this.isReconnecting() && this.newConnectable !== undefined;
    }

    protected isReadyToReroute(): boolean {
        return this.routingHandle !== undefined;
    }

    mouseDown(target: SModelElement, event: MouseEvent): Action[] {
        const result: Action[] = super.mouseDown(target, event);
        if (event.button === 0) {
            const reconnectHandle = findParentByFeature(target, isReconnectHandle);
            const routingHandle = !reconnectHandle ? findParentByFeature(target, isRoutingHandle) : undefined;
            const edge = findParentByFeature(target, isRoutable);
            if (this.isEdgeSelected() && edge && reconnectHandle) {
                // PHASE 2 Reconnect: Select reconnect handle on selected edge
                this.setReconnectHandleSelected(edge, reconnectHandle);
            } else if (this.isEdgeSelected() && edge && routingHandle) {
                // PHASE 2 Reroute: Select routing handle on selected edge
                this.setRoutingHandleSelected(edge, routingHandle);
            } else if (this.isValidEdge(edge)) {
                // PHASE 1: Select edge
                this.tool.registerFeedbackListeners();
                this.setEdgeSelected(edge);
            }
        } else if (event.button === 2) {
            this.reset();
        }
        return result;
    }

    mouseMove(target: SModelElement, event: MouseEvent): Action[] {
        const result = super.mouseMove(target, event);
        if (this.isMouseDrag) {
            // reset any selected connectables when we are dragging, maybe the user is just panning
            this.setNewConnectable(undefined);
        }
        return result;
    }

    mouseUp(target: SModelElement, event: MouseEvent): Action[] {
        const result = super.mouseUp(target, event);
        if (!this.isReadyToReconnect() && !this.isReadyToReroute()) {
            return result;
        }

        if (this.edge && this.newConnectable) {
            const sourceId = this.isReconnectingNewSource() ? this.newConnectable.id : this.edge.sourceId;
            const targetId = this.isReconnectingNewSource() ? this.edge.targetId : this.newConnectable.id;
            if (this.requiresReconnect(sourceId, targetId)) {
                result.push(new ReconnectEdgeOperation(this.edge.id, sourceId, targetId));
            }
            this.reset();
        } else if (this.edge && this.routingHandle) {
            // we need to re-retrieve the edge as it might have changed due to a server update since we do not reset the state between
            // reroute actions
            const latestEdge = target.index.getById(this.edge.id);
            if (latestEdge && isRoutable(latestEdge)) {
                result.push(new ChangeRoutingPointsOperation([{ elementId: latestEdge.id, newRoutingPoints: latestEdge.routingPoints }]));
                this.routingHandle = undefined;
            }
        }
        return result;
    }

    mouseOver(target: SModelElement, event: MouseEvent): Action[] {
        if (this.edge && this.isReconnecting()) {
            const currentTarget = findParentByFeature(target, isConnectable);
            if (!this.newConnectable || currentTarget !== this.newConnectable) {
                this.setNewConnectable(currentTarget);
                if (currentTarget) {
                    if (
                        (this.reconnectMode === 'NEW_SOURCE' && currentTarget.canConnect(this.edge, 'source')) ||
                        (this.reconnectMode === 'NEW_TARGET' && currentTarget.canConnect(this.edge, 'target'))
                    ) {
                        this.tool.dispatchFeedback([cursorFeedbackAction(CursorCSS.EDGE_RECONNECT)]);
                        return [];
                    }
                }
                this.tool.dispatchFeedback([cursorFeedbackAction(CursorCSS.OPERATION_NOT_ALLOWED)]);
            }
        }
        return [];
    }

    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        if (this.edge) {
            if (selectedElements.indexOf(this.edge.id) > -1) {
                // our active edge is still selected, nothing to do
                return;
            }

            if (this.isReconnecting()) {
                // we are reconnecting, so we may have clicked on a potential target
                return;
            }

            // try to find some other selected element and mark that active
            for (const elementId of selectedElements.reverse()) {
                const element = root.index.getById(elementId);
                if (element) {
                    const edge = findParentByFeature(element, isRoutable);
                    if (this.isValidEdge(edge)) {
                        // PHASE 1: Select edge
                        this.setEdgeSelected(edge);
                        return;
                    }
                }
            }

            this.reset();
        }
    }

    public reset(): void {
        this.resetFeedback();
        this.resetData();
    }

    protected resetData(): void {
        this.edge = undefined;
        this.reconnectMode = undefined;
        this.newConnectable = undefined;
        this.routingHandle = undefined;
    }

    protected resetFeedback(): void {
        const result: Action[] = [];
        if (this.edge) {
            result.push(new SwitchRoutingModeAction([], [this.edge.id]));
        }
        result.push(...[new HideEdgeReconnectHandlesFeedbackAction(), cursorFeedbackAction(), new RemoveFeedbackEdgeAction()]);
        this.tool.deregisterFeedback(result);
        this.tool.deregisterFeedbackListeners();
    }
}
