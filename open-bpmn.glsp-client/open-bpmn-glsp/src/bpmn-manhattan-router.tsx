/********************************************************************************
 * Copyright (c) 2019-2020 TypeFox and others.
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
    Bounds,
    GLSPManhattanEdgeRouter,
    GModelElement,
    GRoutableElement,
    isConnectable,
    MouseListener,
    Point,
    RoutedPoint,
    translatePoint
} from '@eclipse-glsp/client';
import { inject, injectable } from 'inversify';

/**
 * Enhanced Manhattan Edge Router for BPMN diagrams that preserves user-defined routing points
 * when moving connected elements. This router extends the default Manhattan router to handle
 * element movements while maintaining the Manhattan-style routing (right angles) and respecting
 * existing routing points.
 */
export class BPMNManhattanRouter extends GLSPManhattanEdgeRouter {
    private debug: boolean = false;

    /**
     * Stores the current movement information including the delta (x,y) and the ID of the moving element.
     * This is updated by the MouseListener and consumed in the next routing calculation.
     */
    private currentDelta?: { x: number; y: number; elementId: string };

    /**
     * Updates the current movement delta. Called by the MouseListener when an element is being dragged.
     * @param delta The x,y movement delta
     * @param elementId ID of the element being moved
     */
    public updateDelta(delta: { x: number; y: number }, elementId: string): void {
        this.currentDelta = { ...delta, elementId };
        if (this.debug) {
            console.log('update delta = ' + delta.x + "," + delta.y + ' for element ' + elementId);
        }
    }

    /**
     * Main routing method that calculates the path for an edge. This override handles the following cases:
     * 1. Default routing when no movement is happening
     * 2. Adjusting the route when the source element is moved
     * 3. Adjusting the route when the target element is moved
     * 
     * The method preserves the Manhattan-style routing by ensuring that segments remain either
     * horizontal or vertical after adjustments.
     * 
     * @param edge - the edge to be routed
     * @returns Array of points describing the complete route
     */
    override route(edge: GRoutableElement): RoutedPoint[] {
        if (!edge.source || !edge.target) {
            return [];
        }

        const routedCorners = this.createRoutedCorners(edge);
        const sourceRefPoint = routedCorners[0] ||
            translatePoint(Bounds.center(edge.target.bounds), edge.target.parent, edge.parent);
        const sourceAnchor = this.getTranslatedAnchor(
            edge.source,
            sourceRefPoint,
            edge.parent,
            edge,
            edge.sourceAnchorCorrection
        );

        const targetRefPoint = routedCorners[routedCorners.length - 1] ||
            translatePoint(Bounds.center(edge.source.bounds), edge.source.parent, edge.parent);
        const targetAnchor = this.getTranslatedAnchor(
            edge.target,
            targetRefPoint,
            edge.parent,
            edge,
            edge.targetAnchorCorrection
        );

        if (!sourceAnchor || !targetAnchor) {
            return [];
        }

        // Build complete route
        const completeRoute: RoutedPoint[] = [];
        completeRoute.push({ kind: 'source', ...sourceAnchor });
        routedCorners.forEach(corner => completeRoute.push(corner));
        completeRoute.push({ kind: 'target', ...targetAnchor });

        if (this.currentDelta && completeRoute.length >= 3) {
            if (this.debug) {
                this.printPoints('├── origin route:', completeRoute);
            }

            const delta = this.currentDelta;
            this.currentDelta = undefined;

            // Check if source or target is being moved
            const isSourceMoving = delta.elementId === edge.source.id;
            const isTargetMoving = delta.elementId === edge.target.id;

            if (isSourceMoving) {
                // Adjust source anchor point
                completeRoute[0] = {
                    kind: 'source',
                    x: completeRoute[0].x + delta.x,
                    y: completeRoute[0].y + delta.y
                };

                // Determine if the first segment is horizontal or vertical
                const firstRoutingPoint = completeRoute[1];
                const sourcePoint = completeRoute[0];

                const isHorizontalSegment = Math.abs(firstRoutingPoint.x - sourcePoint.x) >
                    Math.abs(firstRoutingPoint.y - sourcePoint.y);

                // Adjust the first routing point based on segment type
                if (isHorizontalSegment) {
                    completeRoute[1] = {
                        kind: 'linear',
                        x: firstRoutingPoint.x,
                        y: sourcePoint.y
                    };
                } else {
                    completeRoute[1] = {
                        kind: 'linear',
                        x: sourcePoint.x,
                        y: firstRoutingPoint.y
                    };
                }
            } else if (isTargetMoving) {
                // Adjust target anchor point
                completeRoute[completeRoute.length - 1] = {
                    kind: 'target',
                    x: completeRoute[completeRoute.length - 1].x + delta.x,
                    y: completeRoute[completeRoute.length - 1].y + delta.y
                };
                // Determine if the last segment is horizontal or vertical
                const lastRoutingPointIndex = completeRoute.length - 2;
                const lastRoutingPoint = completeRoute[lastRoutingPointIndex];
                const targetPoint = completeRoute[completeRoute.length - 1];

                const isHorizontalSegment = Math.abs(targetPoint.x - lastRoutingPoint.x) >
                    Math.abs(targetPoint.y - lastRoutingPoint.y);

                // Adjust the last routing point based on segment type
                if (isHorizontalSegment) {
                    completeRoute[lastRoutingPointIndex] = {
                        kind: 'linear',
                        x: lastRoutingPoint.x,
                        y: targetPoint.y
                    };
                } else {
                    completeRoute[lastRoutingPointIndex] = {
                        kind: 'linear',
                        x: targetPoint.x,
                        y: lastRoutingPoint.y
                    };
                }
            }

            // Update the edge's routing points
            const newRoutingPoints = completeRoute.slice(1, -1).map(point => ({
                x: point.x,
                y: point.y
            }));
            edge.routingPoints = newRoutingPoints;
            if (this.debug) {
                console.log(`Adjusted route after ${isSourceMoving ? 'source' : 'target'} movement:`, delta);
                this.printPoints('├── adjusted route:', completeRoute);
            }
            return completeRoute;
        }

        if (this.debug) {
            console.log('No adjustment needed, returning original route');
        }
        return completeRoute;
    }

    /**
     * Helper Debug Method
     * 
     * @param routedPoints
     */
    private printPoints(message: string, points: Point[]) {
        console.log(message);
        points.forEach(point => {
            console.log('│   ├── x=' + point.x + ' y=' + point.y);
        });
    }
}

/**
 * Mouse listener that tracks the movement of connectable elements (nodes that can have edges)
 * and communicates these movements to the BPMNManhattanRouter. This enables the router to
 * adjust edge routes in real-time as elements are being moved.
 */
@injectable()
export class BPMNRouterMoveListener extends MouseListener {
    protected isDragging = false;
    protected lastPosition?: Point;
    protected elementId?: string;

    @inject(BPMNManhattanRouter)
    protected router: BPMNManhattanRouter;

    /**
     * Handles mouse button press. Starts tracking if:
     * 1. It's a left mouse button press
     * 2. The target is a connectable element
     * 
     * @param target The element under the mouse
     * @param event Mouse event
     */
    override mouseDown(target: GModelElement, event: MouseEvent): Action[] {
        if (event.button === 0) { // left mouse button
            // Only track connectable elements
            if (isConnectable(target)) {
                this.isDragging = true;
                this.elementId = target.id;
                this.lastPosition = {
                    x: event.clientX,
                    y: event.clientY
                };
            }
        }
        return [];
    }

    /**
     * Handles mouse movement during drag operations. Calculates the movement delta
     * and informs the router about the movement.
     * 
     * @param target Current element under the mouse
     * @param event Mouse event
     */
    override mouseMove(target: GModelElement, event: MouseEvent): Action[] {
        if (this.isDragging && this.lastPosition && this.elementId) {
            const newPosition = {
                x: event.clientX,
                y: event.clientY
            };

            // Calculate movement delta
            const delta = {
                x: newPosition.x - this.lastPosition.x,
                y: newPosition.y - this.lastPosition.y
            };

            // Inform router about new move event with element ID
            this.router.updateDelta(delta, this.elementId);
            this.lastPosition = newPosition;
        }
        return [];
    }

    /**
     * Handles mouse button release. Cleans up tracking state.
     * 
     * @param target The element under the mouse
     * @param event Mouse event
     */
    override mouseUp(target: GModelElement, event: MouseEvent): Action[] {
        if (this.isDragging) {
            this.isDragging = false;
            this.lastPosition = undefined;
            this.elementId = undefined;
        }
        return [];
    }
}
