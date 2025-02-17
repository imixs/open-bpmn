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
    wayPoint: Point;
    offsetY: number;
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
    private originWaypoints: WayPointData[];

      /**
       * Updates the current movement delta. Called by the MouseListener when an element is being dragged.
       * @param delta The x,y movement delta
       * @param elementId ID of the element being moved
       */
      public addOriginWaypoint(origin: WayPointData): void {
        this.originWaypoints?.push(origin);
        this.debug('setOriginWaypoint - kind=' + origin.kind 
            + ' dockingPoint=' +origin.elementPoint.x + ','+origin.elementPoint.y
            + ' wayPoint=' +origin.wayPoint.x +',' +origin.wayPoint.y
            + ' offsetY='+origin.offsetY
            + ' id=' + origin.edgeId );
      }
      public initOriginWaypoint(): void {
        this.originWaypoints = [];
        this.debug('init WayPointOrigins');
    }

    protected override getOptions(edge: GRoutableElement): ManhattanRouterOptions {
        return {
            standardDistance: 20, // 20
            minimalPointDistance: 3, // 3
            selfEdgeOffset: 0.25  // 0.25
        };
    }

    override route(edge: GRoutableElement): RoutedPoint[] {

        if (!edge.source || !edge.target) {
            this.debug('No source or target - returning empty array');
            return [];
        }

        if (this.originWaypoints
            &&  this.originWaypoints.find(origin => origin.edgeId === edge.id)  ) {

            this.debug('start routing....');
            // first compute default route....
            let completeRoute= super.route(edge);

            // Test if we have a wayPointOrigin....
            const wayPointOrigin = this.originWaypoints.find(origin => origin.edgeId === edge.id);
            if (wayPointOrigin) {
                this.debugFine('Origin element pos='+edge.source.bounds.x + ','+edge.source.bounds.y + ' yOffset='+wayPointOrigin.offsetY);
                // Store origin Route?
                if (!wayPointOrigin.originRoute) {
                    wayPointOrigin.originRoute=[...completeRoute];
                    this.debugPoints('Set origin Route.....',wayPointOrigin.originRoute);
                } else {
                    this.debugPoints('Restore origin Route.....',wayPointOrigin.originRoute);
                    completeRoute=[...wayPointOrigin.originRoute];
                    this.debugFine('Adjusting routing points...');

                    // Check if source or target is being moved
                    if (wayPointOrigin.kind === 'source') {
                        // ========= SOURCE Moving ==========
                        this.debugFine('processing source moving....');
                        const verticalOffSet=edge.source.bounds.y - wayPointOrigin.elementPoint.y;
                        const horizontalOffSet=edge.source.bounds.x - wayPointOrigin.elementPoint.x;
                        this.debugFinest('offset=' + horizontalOffSet + ',' + verticalOffSet);
                        const isHorizontalSegment = Math.abs(completeRoute[1].x -  completeRoute[0].x) >
                                Math.abs(completeRoute[1].y - completeRoute[0].y);

                        if (isHorizontalSegment) {
                            this.debugFinest('horizontal segment!');
                            completeRoute[0] = {
                                kind: completeRoute[0].kind,
                                pointIndex: completeRoute[0].pointIndex,
                                isJunction: completeRoute[0].isJunction,
                                x: completeRoute[0].x + horizontalOffSet,
                                y: completeRoute[0].y + verticalOffSet
                            };
                            completeRoute[1] = {
                                kind: completeRoute[1].kind,
                                pointIndex: completeRoute[1].pointIndex,
                                isJunction: completeRoute[1].isJunction,
                                x: completeRoute[1].x,
                                y: completeRoute[1].y + verticalOffSet
                            };
                        } else {
                            // vertial
                            this.debugFinest('vertical segment!');
                            completeRoute[0] = {
                                kind: completeRoute[0].kind,
                                pointIndex: completeRoute[0].pointIndex,
                                isJunction: completeRoute[0].isJunction,
                                x: completeRoute[0].x + horizontalOffSet,
                                y: completeRoute[0].y + verticalOffSet
                            };
                            completeRoute[1] = {
                                kind: completeRoute[1].kind,
                                pointIndex: completeRoute[1].pointIndex,
                                isJunction: completeRoute[1].isJunction,
                                x: completeRoute[1].x + horizontalOffSet,
                                y: completeRoute[1].y
                            };
                        }
                    } else {
                        // ========= TARGET Moving ==========
                        this.debugFine('processing target moving....');
                        const verticalOffSet=edge.target.bounds.y - wayPointOrigin.elementPoint.y;
                        const horizontalOffSet=edge.target.bounds.x - wayPointOrigin.elementPoint.x;
                        this.debugFinest('offset=' + horizontalOffSet + ',' + verticalOffSet);
                        const pIndex=completeRoute.length-1;
                        const isHorizontalSegment = Math.abs(completeRoute[pIndex].x - completeRoute[pIndex-1].x) >
                                Math.abs(completeRoute[pIndex].y - completeRoute[pIndex-1].y);                                

                        if (isHorizontalSegment) {
                            this.debugFinest('horizontal segment!');
                            completeRoute[pIndex] = {
                                kind: completeRoute[pIndex].kind,
                                pointIndex: completeRoute[pIndex].pointIndex,
                                isJunction: completeRoute[pIndex].isJunction,
                                x: completeRoute[pIndex].x + horizontalOffSet,
                                y: completeRoute[pIndex].y + verticalOffSet
                            };
                            completeRoute[pIndex-1] = {
                                kind: completeRoute[1].kind,
                                pointIndex: completeRoute[pIndex-1].pointIndex,
                                isJunction: completeRoute[pIndex-1].isJunction,
                                x: completeRoute[pIndex-1].x,
                                y: completeRoute[pIndex-1].y + verticalOffSet
                            };
                        } else {
                            // vertial
                            this.debugFinest('vertical segment!');
                            completeRoute[pIndex] = {
                                kind: completeRoute[pIndex].kind,
                                pointIndex: completeRoute[pIndex].pointIndex,
                                isJunction: completeRoute[pIndex].isJunction,
                                x: completeRoute[pIndex].x + horizontalOffSet,
                                y: completeRoute[pIndex].y + verticalOffSet
                            };
                            completeRoute[pIndex-1] = {
                                kind: completeRoute[pIndex-1].kind,
                                pointIndex: completeRoute[pIndex-1].pointIndex,
                                isJunction: completeRoute[pIndex-1].isJunction,
                                x: completeRoute[pIndex-1].x + horizontalOffSet,
                                y: completeRoute[pIndex-1].y
                            };
                        }
                    }
                }

                edge.routingPoints=completeRoute;
            }

            // return full route
            return completeRoute;
        } else {
        // default routing!
        return super.route(edge);
        }
    }

    /**
     * Helper Debug Methods
     *
     * @param routedPoints
     */
    private debugPoints(message: string, points: Point[]): void {
        if (this.debugMode) {
            console.log('├── '+message);
            points.forEach(point => {
                console.log('│   ├── x=' + point.x + ' y=' + point.y);
            });
        }
    }
    private debug(message: string): void {
        if (this.debugMode) {
               console.log('├── '+message);
        }
    }
    private debugFine(message: string): void {
        if (this.debugMode) {
               console.log('│   ├── '+message);
        }
    }
    private debugFinest(message: string): void {
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
                this.router.initOriginWaypoint();
                console.log('├── Mouse Down - Element: '+target.id + ' pos= x:'+targetElement.bounds.x + ' y:'+targetElement.bounds.y);
                this.isDragging = true;

                // Collect all affected edges
                if (targetElement.incomingEdges) {
                    targetElement.incomingEdges.forEach(edge => {
                        if (isRoutable(edge) && edge.routingPoints.length>0) {
                            const _offsetY=edge.routingPoints[0].y-targetElement.bounds.y;
                            this.router.addOriginWaypoint({
                                kind: 'target',
                                elementId: targetElement.id,
                                elementPoint: {x: targetElement.bounds.x, y:  targetElement.bounds.y },
                                edgeId: edge.id,
                                wayPoint: (edge.routingPoints[edge.routingPoints.length-1]),
                                offsetY: _offsetY
                            });
                        }
                    });
                }
                if (targetElement.outgoingEdges) {
                    targetElement.outgoingEdges.forEach(edge => {
                        if (isRoutable(edge) && edge.routingPoints.length>0) {
                            const _offsetY=edge.routingPoints[0].y-targetElement.bounds.y;
                            this.router.addOriginWaypoint({
                                kind: 'source',
                                elementId: targetElement.id,
                                elementPoint: {x: targetElement.bounds.x, y:  targetElement.bounds.y },
                                edgeId:edge.id,
                                wayPoint: edge.routingPoints[0],
                                offsetY:_offsetY
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
            this.router.initOriginWaypoint();
        }
        return [];
    }
}
