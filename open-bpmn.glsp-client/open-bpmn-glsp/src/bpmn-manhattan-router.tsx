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
    findParentByFeature,
    GLSPManhattanEdgeRouter,
    GModelElement,
    GRoutableElement,
    isRoutable,
    ManhattanRouterOptions,
    MouseListener,
    Point,
    RoutedPoint
} from '@eclipse-glsp/client';
import { isBPMNNode } from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';

/**
 * Interface defining the structure of movement information
 */
interface WayPointData {
    kind: string;
    elementId: string;
    elementPoint: Point;
    edgeId: string;
    originRoute?: RoutedPoint[];
}

/**
 * Enhanced Manhattan Edge Router for BPMN diagrams that preserves user-defined routing points
 * when moving connected elements. This router extends the default Manhattan router to handle
 * element movements while maintaining the Manhattan-style routing (right angles) and respecting
 * existing routing points.
 */
export class BPMNManhattanRouter extends GLSPManhattanEdgeRouter {
    private debugMode: boolean = false;
    private originWayPointData: WayPointData[];

      /**
       * Updates the current movement delta. Called by the MouseListener when an element is being dragged.
       * @param delta The x,y movement delta
       * @param elementId ID of the element being moved
       */
      public addWayPointData(origin: WayPointData): void {
        this.originWayPointData?.push(origin);
        this.debug('addWayPointData - kind=' + origin.kind 
            + ' dockingPoint=' +origin.elementPoint.x + ','+origin.elementPoint.y
            + ' id=' + origin.edgeId );
      }
      public resetWayPointData(): void {
        this.originWayPointData = [];
        this.debug('reset WayPointData');
    }

    protected override getOptions(edge: GRoutableElement): ManhattanRouterOptions {
        return {
            standardDistance: 20, // 20
            minimalPointDistance: 3, // 3
            selfEdgeOffset: 0.25  // 0.25
        };
    }

    /**
     * The route method overwrite the default behavior of the GLSPManhattanEdgeRouter.
     * The method restore the default routing and is just adjusting the anchor point
     * and its next waypoint.
     * @param edge
     * @returns
     */
    override route(edge: GRoutableElement): RoutedPoint[] {
        // Early return if source or target is missing
        if (!edge.source || !edge.target) {
            this.debug('No source or target - returning empty array');
            return [];
        }
        // Check if we know the waypointData for the current edge
        const currentWayPointData = this.originWayPointData?.find(origin => origin.edgeId === edge.id);
        if (!currentWayPointData) {
            // Default routing if no waypointData is found
            return super.route(edge);
        }
        this.debug('start routing '+edge.id);
        let completeRoute = super.route(edge);

        // Log origin element position and offset
        this.debugFine(`Origin element pos=${edge.source.bounds.x},${edge.source.bounds.y}`);
        if (!currentWayPointData.originRoute) {
            // Store the original route if it doesn't exist
            currentWayPointData.originRoute = [...completeRoute];
            this.debugPoints('Set origin Route for '+edge.id, currentWayPointData.originRoute);
        } else {

            this.debug(' new-rout.length='+completeRoute.length + '  origin-route.length='+ currentWayPointData.originRoute.length);

            if (currentWayPointData.originRoute.length<=2
                && completeRoute.length>2 ) {
                // Restore the original route and adjust it
                currentWayPointData.originRoute = [...completeRoute];
                this.debugPoints('Reset! origin Route '+edge.id, currentWayPointData.originRoute);
                // completeRoute = [...currentWayPointData.originRoute];
            } else {
                this.debugPoints('Restore origin Route.....', currentWayPointData.originRoute);
                completeRoute = [...currentWayPointData.originRoute];
                this.debugFine('Adjusting routing points...');

                // Determine if the source or target is being moved
                const isSourceMoving = currentWayPointData.kind === 'source';
                const element = isSourceMoving ? edge.source : edge.target;

                // Calculate offsets
                const verticalOffset = element.bounds.y - currentWayPointData.elementPoint.y;
                const horizontalOffset = element.bounds.x - currentWayPointData.elementPoint.x;
                this.debugFinest(`offset=${horizontalOffset},${verticalOffset}`);

                // Determine the index to adjust routing points (first point for source, last point for target)
                const startIndex = isSourceMoving ? 0 : completeRoute.length - 1;
                const routeIndex = startIndex + (isSourceMoving ? 1 : -1);

                // Check if this segment is horizontal or vertical
                const isHorizontalSegment = Math.abs(completeRoute[startIndex].x - completeRoute[routeIndex].x) >
                    Math.abs(completeRoute[startIndex].y - completeRoute[routeIndex].y);
                this.debugFinest(isHorizontalSegment ? 'horizontal segment!' : 'vertical segment!');
                // Adjust the routing points based on the segment type
                completeRoute[startIndex] = {
                    ...completeRoute[startIndex],
                    x: completeRoute[startIndex].x + horizontalOffset,
                    y: completeRoute[startIndex].y + verticalOffset,
                };
                completeRoute[routeIndex] = {
                    ...completeRoute[routeIndex],
                    x: isHorizontalSegment ? completeRoute[routeIndex].x : completeRoute[routeIndex].x + horizontalOffset,
                    y: isHorizontalSegment ? completeRoute[routeIndex].y + verticalOffset : completeRoute[routeIndex].y,
                };

                // Collision-Check
                // if the first routingPoint is inside the element, reset WayPointData!
                if (Bounds.includes(element.bounds, completeRoute[routeIndex])) {
                    this.debug('Collision with nearest routing point detected - reset waypoint data!');
                    this.resetWayPointData();
                    edge.routingPoints = completeRoute;
                    return completeRoute;
                }
            }
        }

        // Update the edge's routing points and return the complete route
        edge.routingPoints = completeRoute;
        return completeRoute;
    }

    /**
     * Helper Debug Methods
     *
     * @param routedPoints
     */
    public debugPoints(message: string, points: Point[]): void {
        if (this.debugMode) {
            console.log('├── '+message);
            points.forEach(point => {
                console.log('│   ├── x=' + point.x + ' y=' + point.y);
            });
        }
    }
    public debug(message: string): void {
        if (this.debugMode) {
               console.log('├── '+message);
        }
    }
    public debugFine(message: string): void {
        if (this.debugMode) {
               console.log('│   ├── '+message);
        }
    }
    public debugFinest(message: string): void {
        if (this.debugMode) {
               console.log('│   │   ├── '+message);
        }
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
            let targetElement = target;
            if (!isBPMNNode(targetElement)) {
                const bpmnNode = findParentByFeature(targetElement, isBPMNNode);
                if (bpmnNode) {
                    targetElement = bpmnNode;
                }
            }
            // do we have a BPMN node?
            if (isBPMNNode(targetElement)) {
                // reset origins!
                this.router.resetWayPointData();
                this.router.debug('├── Mouse Down - Element: '+target.id + ' pos= x:'
                     + targetElement.bounds.x + ' y:'+targetElement.bounds.y);
                this.isDragging = true;

                // Collect all affected edges
                if (targetElement.incomingEdges) {
                    targetElement.incomingEdges.forEach(edge => {
                        if (isRoutable(edge)) {
                            this.router.addWayPointData({
                                kind: 'target',
                                elementId: targetElement.id,
                                elementPoint: {x: targetElement.bounds.x, y:  targetElement.bounds.y },
                                edgeId: edge.id
                            });
                        }
                    });
                }
                if (targetElement.outgoingEdges) {
                    targetElement.outgoingEdges.forEach(edge => {
                        this.router.debugFine('  routing point count='+ edge.routingPoints.length);
                        if (isRoutable(edge)) {
                            this.router.addWayPointData({
                                kind: 'source',
                                elementId: targetElement.id,
                                elementPoint: {x: targetElement.bounds.x, y:  targetElement.bounds.y },
                                edgeId:edge.id
                            });
                        }
                    });
                }
            }
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
            this.router.resetWayPointData();
        }
        return [];
    }
}
