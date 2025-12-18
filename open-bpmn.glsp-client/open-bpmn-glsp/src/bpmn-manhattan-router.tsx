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
            minimalPointDistance:3, // 3  | 25
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
        // Validate edge structure completely
        if (!edge.source || !edge.target) {
            this.debug('No source or target - returning empty array');
            return [];
        }
        // Validate bounds existence and validity
        if (!edge.source.bounds || !edge.target.bounds) {
            this.debug('Invalid bounds on source or target - returning empty array');
            return [];
        }
        if (edge.source.bounds.width === undefined || edge.source.bounds.height === undefined ||
            edge.target.bounds.width === undefined || edge.target.bounds.height === undefined) {
            this.debug('Incomplete bounds data - returning empty array');
            return [];
        }

        const currentWayPointData = this.originWayPointData?.find(origin => origin.edgeId === edge.id);
        if (!currentWayPointData) {
            return super.route(edge);
        }

        this.debug('start routing '+edge.id);
        let completeRoute;
        try {
            completeRoute = super.route(edge);
        } catch (error) {
            this.debug('Error in super.route(): ' + error);
            return [];
        }

        // validate route
        if (!completeRoute || completeRoute.length < 2) {
            this.debug('Invalid route returned from super - length: ' + completeRoute?.length);
            return super.route(edge);
        }

        this.debugFine(`Origin element pos=${edge.source.bounds.x},${edge.source.bounds.y}`);

        // if ('1'!==currentWayPointData.elementId) {
        //     console.log('Ich mache nix!!');
        //     // return super.route(edge);
        //      //return [];
        //       this.resetWayPointData();
        //             return super.route(edge);
        // }

        if (!currentWayPointData.originRoute) {
            currentWayPointData.originRoute = [...completeRoute];
            this.debugPoints('Set origin Route for '+edge.id, currentWayPointData.originRoute);
        } else {
            if (currentWayPointData.originRoute.length === 2) {
                currentWayPointData.originRoute = [...completeRoute];
                return super.route(edge);
            } else {
                // VALIDIERUNG: Prüfe ob originRoute gültig ist
                if (!currentWayPointData.originRoute || currentWayPointData.originRoute.length < 2) {
                    this.debug('Invalid originRoute - resetting');
                    this.resetWayPointData();
                    return super.route(edge);
                }
                this.debugPoints('Restore origin Route.....', currentWayPointData.originRoute);
                completeRoute = [...currentWayPointData.originRoute];

                // validate all points
                const allPointsValid = completeRoute.every(point =>
                    point && typeof point.x === 'number' && typeof point.y === 'number'
                );

                if (!allPointsValid) {
                    this.debug('Invalid points in route - resetting');
                    this.resetWayPointData();
                    return super.route(edge);
                }
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
                    y: completeRoute[startIndex].y + verticalOffset
                };
                completeRoute[routeIndex] = {
                    ...completeRoute[routeIndex],
                    x: isHorizontalSegment ? completeRoute[routeIndex].x : completeRoute[routeIndex].x + horizontalOffset,
                    y: isHorizontalSegment ? completeRoute[routeIndex].y + verticalOffset : completeRoute[routeIndex].y
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

        // optimize route be removing clusters
        completeRoute=this.collapseRoute(completeRoute);
        // Update the edge's routing points and return the complete route
        edge.routingPoints = completeRoute;
        return completeRoute;
    }

        /**
     * Collapses a Manhattan route by removing unnecessary intermediate points
     * that form a local "cluster" (short segments) within the given threshold.
     *
     * @param points - The route points (first and last are fixed anchor points)
     * @param threshold - Maximum length of a segment to collapse (default: 20px)
     * @returns Optimized route with minimal points
     */
    public collapseRoute(points: RoutedPoint[], threshold = 20): RoutedPoint[] {
        // Need at least 4 points to have a collapsible segment
        // (3 points = already optimal L-path)
        if (points.length <= 3) {
            return points;
        }

        let result = [...points];
        let changed = true;

        // Repeat until no more changes are made
        // (collapsing one segment might create another short segment)
        while (changed) {
            changed = false;

            // Iterate through inner segments only (skip first and last segment)
            // First segment (0→1) connects to fixed start point
            // Last segment (n-2→n-1) connects to fixed end point
            for (let i = 1; i < result.length - 2; i++) {
                const p1 = result[i];
                const p2 = result[i + 1];

                // Calculate segment length
                // For Manhattan routing, segments are either horizontal or vertical
                const dx = Math.abs(p2.x - p1.x);
                const dy = Math.abs(p2.y - p1.y);
                const segmentLength = dx + dy;

                // Skip diagonal segments (should not occur in Manhattan routing)
                // These require different handling and might indicate a data issue
                if (dx > 0 && dy > 0) {
                    continue;
                }

                if (segmentLength < threshold) {
                    // Found a short segment → collapse it
                    const before = result[i - 1];
                    const after = result[i + 2];

                    // Safety check: ensure 'before' and 'after' exist
                    if (!before || !after) {
                        continue;
                    }

                    // Determine segment direction to calculate the new L-point
                    // Horizontal segment: same Y values (dy === 0)
                    // Vertical segment: same X values (dx === 0)
                    const isHorizontal = dy === 0;

                    // The new L-point connects 'before' to 'after':
                    // - Horizontal segment → take X from 'after', Y from 'before'
                    // - Vertical segment   → take X from 'before', Y from 'after'
                    const lPoint: RoutedPoint = isHorizontal
                        ? { x: after.x, y: before.y, kind: 'linear' }
                        : { x: before.x, y: after.y, kind: 'linear' };

                    // Validate: new L-point should create valid Manhattan segments
                    // Skip if the new point would create a diagonal connection
                    const createsValidRoute =
                        (before.x === lPoint.x || before.y === lPoint.y) &&
                        (after.x === lPoint.x || after.y === lPoint.y);

                    if (!createsValidRoute) {
                        continue;
                    }

                    // Replace points[i] and points[i+1] with the new L-point
                    result = [
                        ...result.slice(0, i),
                        lPoint,
                        ...result.slice(i + 2)
                    ];

                    changed = true;
                    break; // Restart from beginning (indices have changed)
                }
            }
        }

        return result;
    }

    /**
     * Collapses a Manhattan route by removing unnecessary intermediate points
     * that form a local "cluster" (short segments) within the given threshold.
     *
     * @param points - The route points (first and last are fixed anchor points)
     * @param threshold - Maximum length of a segment to collapse (default: 20px)
     * @returns Optimized route with minimal points
     */
    public collapseRouteFirstDraft(points: RoutedPoint[], threshold = 10): RoutedPoint[] {
        // Need at least 4 points to have a collapsible segment
        // (3 points = already optimal L-path)
        if (points.length <= 3) {
            return points;
        }

        let result = [...points];
        let changed = true;

        // Repeat until no more changes are made
        // (collapsing one segment might create another short segment)
        while (changed) {
            changed = false;

            // Iterate through inner segments only (skip first and last segment)
            // First segment (0→1) connects to fixed start point
            // Last segment (n-2→n-1) connects to fixed end point
            for (let i = 1; i < result.length - 2; i++) {
                const p1 = result[i];
                const p2 = result[i + 1];

                // Calculate segment length
                // For Manhattan routing, segments are either horizontal or vertical
                const segmentLength = Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);

                if (segmentLength < threshold) {
                    // Found a short segment → collapse it
                    const before = result[i - 1];
                    const after = result[i + 2];

                    // Determine segment direction to calculate the new L-point
                    // Horizontal segment: same Y values
                    // Vertical segment: same X values
                    const isHorizontal = p1.y === p2.y;

                    // The new L-point connects 'before' to 'after':
                    // - Horizontal segment → take X from 'after', Y from 'before'
                    // - Vertical segment   → take X from 'before', Y from 'after'
                    const lPoint: RoutedPoint = isHorizontal
                        ? { x: after.x, y: before.y, kind: 'linear' }
                        : { x: before.x, y: after.y, kind: 'linear' };

                    // Replace points[i] and points[i+1] with the new L-point
                    result = [
                        ...result.slice(0, i),
                        lPoint,
                        ...result.slice(i + 2)
                    ];
                    changed = true;
                    break; // Restart from beginning (indices have changed)
                }
            }
        }
        return result;
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
