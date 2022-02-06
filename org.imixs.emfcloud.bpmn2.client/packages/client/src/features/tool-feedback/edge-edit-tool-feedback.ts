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
import { Action, Point } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { VNode } from 'snabbdom';
import {
    add,
    AnchorComputerRegistry,
    center,
    CommandExecutionContext,
    CommandReturn,
    EdgeRouterRegistry,
    ElementMove,
    euclideanDistance,
    findChildrenAtPosition,
    findParentByFeature,
    isBoundsAware,
    isConnectable,
    isSelected,
    isViewport,
    MouseListener,
    MoveAction,
    PolylineEdgeRouter,
    SConnectableElement,
    SModelElement,
    SModelRoot,
    SRoutingHandle,
    SwitchEditModeAction,
    SwitchEditModeCommand,
    TYPES
} from 'sprotty';
import { isNotUndefined, isRoutable, isRoutingHandle } from '../../utils/smodel-util';
import { getAbsolutePosition, toAbsoluteBounds } from '../../utils/viewpoint-util';
import { addReconnectHandles, removeReconnectHandles } from '../reconnect/model';
import { FeedbackEdgeEnd, feedbackEdgeEndId, FeedbackEdgeEndMovingMouseListener, feedbackEdgeId } from './creation-tool-feedback';
import { FeedbackCommand } from './model';

/**
 * RECONNECT HANDLES FEEDBACK
 */

export class ShowEdgeReconnectHandlesFeedbackAction implements Action {
    constructor(readonly elementId?: string, public readonly kind: string = ShowEdgeReconnectHandlesFeedbackCommand.KIND) {}
}

export class HideEdgeReconnectHandlesFeedbackAction implements Action {
    constructor(public readonly kind: string = HideEdgeReconnectHandlesFeedbackCommand.KIND) {}
}

@injectable()
export class ShowEdgeReconnectHandlesFeedbackCommand extends FeedbackCommand {
    static readonly KIND = 'showReconnectHandlesFeedback';

    constructor(@inject(TYPES.Action) protected action: ShowEdgeReconnectHandlesFeedbackAction) {
        super();
    }

    execute(context: CommandExecutionContext): CommandReturn {
        const index = context.root.index;
        index.all().filter(isRoutable).forEach(removeReconnectHandles);

        if (isNotUndefined(this.action.elementId)) {
            const routableElement = index.getById(this.action.elementId);
            if (isNotUndefined(routableElement) && isRoutable(routableElement)) {
                addReconnectHandles(routableElement);
            }
        }
        return context.root;
    }
}

@injectable()
export class HideEdgeReconnectHandlesFeedbackCommand extends FeedbackCommand {
    static readonly KIND = 'hideReconnectHandlesFeedback';

    constructor(@inject(TYPES.Action) protected action: HideEdgeReconnectHandlesFeedbackAction) {
        super();
    }

    execute(context: CommandExecutionContext): CommandReturn {
        const index = context.root.index;
        index.all().filter(isRoutable).forEach(removeReconnectHandles);
        return context.root;
    }
}
/**
 * ROUTING FEEDBACK
 */

export class SwitchRoutingModeAction extends SwitchEditModeAction {
    constructor(
        public readonly elementsToActivate: string[] = [],
        public readonly elementsToDeactivate: string[] = [],
        public readonly kind: string = SwitchRoutingModeCommand.KIND
    ) {
        super(elementsToActivate, elementsToDeactivate);
    }
}

@injectable()
export class SwitchRoutingModeCommand extends SwitchEditModeCommand {
    static KIND = 'switchRoutingMode';
    constructor(@inject(TYPES.Action) action: SwitchRoutingModeAction) {
        super(action);
    }
}

/**
 * SOURCE AND TARGET EDGE FEEDBACK
 */

export class DrawFeedbackEdgeSourceAction implements Action {
    constructor(
        readonly elementTypeId: string,
        readonly targetId: string,
        public readonly kind: string = DrawFeedbackEdgeSourceCommand.KIND
    ) {}
}

@injectable()
export class DrawFeedbackEdgeSourceCommand extends FeedbackCommand {
    static readonly KIND = 'drawFeedbackEdgeSource';

    constructor(@inject(TYPES.Action) protected action: DrawFeedbackEdgeSourceAction) {
        super();
    }

    execute(context: CommandExecutionContext): CommandReturn {
        drawFeedbackEdgeSource(context, this.action.targetId, this.action.elementTypeId);
        return context.root;
    }
}

/**
 * SOURCE AND TARGET MOUSE MOVE LISTENER
 */

export class FeedbackEdgeTargetMovingMouseListener extends FeedbackEdgeEndMovingMouseListener {
    constructor(protected anchorRegistry: AnchorComputerRegistry) {
        super(anchorRegistry);
    }
}

export class FeedbackEdgeSourceMovingMouseListener extends MouseListener {
    constructor(protected anchorRegistry: AnchorComputerRegistry) {
        super();
    }

    mouseMove(target: SModelElement, event: MouseEvent): Action[] {
        const root = target.root;
        const edgeEnd = root.index.getById(feedbackEdgeEndId(root));
        if (!(edgeEnd instanceof FeedbackEdgeEnd) || !edgeEnd.feedbackEdge) {
            return [];
        }

        const edge = edgeEnd.feedbackEdge;
        const position = getAbsolutePosition(edgeEnd, event);
        const endAtMousePosition = findChildrenAtPosition(target.root, position).find(
            e => isConnectable(e) && e.canConnect(edge, 'source')
        );

        if (endAtMousePosition instanceof SConnectableElement && edge.target && isBoundsAware(edge.target)) {
            const anchor = this.computeAbsoluteAnchor(endAtMousePosition, center(edge.target.bounds));
            if (euclideanDistance(anchor, edgeEnd.position) > 1) {
                return [new MoveAction([{ elementId: edgeEnd.id, toPosition: anchor }], false)];
            }
        } else {
            return [new MoveAction([{ elementId: edgeEnd.id, toPosition: position }], false)];
        }

        return [];
    }

    protected computeAbsoluteAnchor(element: SConnectableElement, referencePoint: Point, offset?: number): Point {
        const anchorComputer = this.anchorRegistry.get(PolylineEdgeRouter.KIND, element.anchorKind);
        let anchor = anchorComputer.getAnchor(element, referencePoint, offset);
        // The anchor is computed in the local coordinate system of the element.
        // If the element is a nested child element we have to add the absolute position of its parent to the anchor.
        if (element.parent !== element.root) {
            const parent = findParentByFeature(element.parent, isBoundsAware);
            if (parent) {
                const absoluteParentPosition = toAbsoluteBounds(parent);
                anchor = add(absoluteParentPosition, anchor);
            }
        }
        return anchor;
    }
}

export class FeedbackEdgeRouteMovingMouseListener extends MouseListener {
    protected hasDragged = false;
    protected lastDragPosition: Point | undefined;

    constructor(protected edgeRouterRegistry?: EdgeRouterRegistry) {
        super();
    }

    mouseDown(target: SModelElement, event: MouseEvent): Action[] {
        const result: Action[] = [];
        if (event.button === 0) {
            const routingHandle = findParentByFeature(target, isRoutingHandle);
            if (routingHandle !== undefined) {
                result.push(new SwitchRoutingModeAction([target.id], []));
                this.lastDragPosition = { x: event.pageX, y: event.pageY };
            } else {
                this.lastDragPosition = undefined;
            }
            this.hasDragged = false;
        }
        return result;
    }

    mouseMove(target: SModelElement, event: MouseEvent): Action[] {
        const result: Action[] = [];
        if (event.buttons === 0) {
            this.mouseUp(target, event);
        } else if (this.lastDragPosition) {
            const viewport = findParentByFeature(target, isViewport);
            this.hasDragged = true;
            const zoom = viewport ? viewport.zoom : 1;
            const dx = (event.pageX - this.lastDragPosition.x) / zoom;
            const dy = (event.pageY - this.lastDragPosition.y) / zoom;
            const handleMoves: ElementMove[] = [];
            target.root.index
                .all()
                .filter(element => isSelected(element))
                .forEach(element => {
                    if (isRoutingHandle(element)) {
                        const point = this.getHandlePosition(element);
                        if (point !== undefined) {
                            handleMoves.push({
                                elementId: element.id,
                                fromPosition: point,
                                toPosition: {
                                    x: point.x + dx,
                                    y: point.y + dy
                                }
                            });
                        }
                    }
                });
            this.lastDragPosition = { x: event.pageX, y: event.pageY };
            if (handleMoves.length > 0) {
                result.push(new MoveAction(handleMoves, false));
            }
        }
        return result;
    }

    protected getHandlePosition(handle: SRoutingHandle): Point | undefined {
        if (this.edgeRouterRegistry) {
            const parent = handle.parent;
            if (!isRoutable(parent)) {
                return undefined;
            }
            const router = this.edgeRouterRegistry.get(parent.routerKind);
            const route = router.route(parent);
            return router.getHandlePosition(parent, route, handle);
        }
        return undefined;
    }

    mouseEnter(target: SModelElement, event: MouseEvent): Action[] {
        if (target instanceof SModelRoot && event.buttons === 0) {
            this.mouseUp(target, event);
        }
        return [];
    }

    mouseUp(_target: SModelElement, event: MouseEvent): Action[] {
        this.hasDragged = false;
        this.lastDragPosition = undefined;
        return [];
    }

    decorate(vnode: VNode, _element: SModelElement): VNode {
        return vnode;
    }
}

/**
 * UTILITY FUNCTIONS
 */

function drawFeedbackEdgeSource(context: CommandExecutionContext, targetId: string, elementTypeId: string): void {
    const root = context.root;
    const targetChild = root.index.getById(targetId);
    if (!targetChild) {
        return;
    }

    const target = findParentByFeature(targetChild, isConnectable);
    if (!target || !isBoundsAware(target)) {
        return;
    }

    const edgeEnd = new FeedbackEdgeEnd(target.id, elementTypeId);
    edgeEnd.id = feedbackEdgeEndId(root);
    edgeEnd.position = { x: target.bounds.x, y: target.bounds.y };

    const feedbackEdgeSchema = {
        type: 'edge',
        id: feedbackEdgeId(root),
        sourceId: edgeEnd.id,
        targetId: target.id,
        opacity: 0.3
    };

    const feedbackEdge = context.modelFactory.createElement(feedbackEdgeSchema);
    if (isRoutable(feedbackEdge)) {
        edgeEnd.feedbackEdge = feedbackEdge;
        root.add(edgeEnd);
        root.add(feedbackEdge);
    }
}
