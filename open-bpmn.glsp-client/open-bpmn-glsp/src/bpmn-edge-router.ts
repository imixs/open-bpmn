/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
import { ManhattanEdgeRouter,
         ResolvedHandleMove,
         SRoutableElement
        } from 'sprotty';
import { almostEquals,
         Action,
         ElementAndRoutingPoints
       } from '@eclipse-glsp/client';
// import { inject } from 'inversify';
/****************************************************************************
 * The BPMNEdgeRouter is a custom implementation of the Sprotty
 * Manhantten Router Implementation (manhattan-edge-router.ts)
 ****************************************************************************/

export class BPMNCreateRoutingPointsAction implements Action {
  static readonly KIND = 'BPMNCreateRoutingPoints';
  kind = BPMNCreateRoutingPointsAction.KIND;
  constructor(public readonly newRoutingPoints: ElementAndRoutingPoints[]) {}
}
// newRoutingPoints: ElementAndRoutingPoints[]
export class BPMNManhattanEdgeRouter extends ManhattanEdgeRouter {

    /*
    override route(edge: SRoutableElement): RoutedPoint[] {

        const routedPoints: RoutedPoint[] = super.route(edge);
        console.log('....route...');
		let r: Point;
        for (r of routedPoints) {
          console.log('......... point : '+r.x + ',' + r.y);
        }

        return routedPoints;
    }*/

    protected override applyInnerHandleMoves(edge: SRoutableElement, moves: ResolvedHandleMove[]): void {
        // console.log('....applyInnerHandleMoves - START edge.routing.length='+edge.routingPoints.length);
        const route = this.route(edge);
        const routingPoints = edge.routingPoints;
        const minimalPointDistance = this.getOptions(edge).minimalPointDistance;
        moves.forEach(move => {
            const handle = move.handle;
            const index = handle.pointIndex;
            const correctedX = this.correctX(routingPoints, index, move.toPosition.x, minimalPointDistance);
            const correctedY = this.correctY(routingPoints, index, move.toPosition.y, minimalPointDistance);
            switch (handle.kind) {
                case 'manhattan-50%':
                    if (index < 0) {
                        if (routingPoints.length === 0) {
                            routingPoints.push({ x: correctedX, y: correctedY });
                            move.handle.pointIndex = 0;
                        }
                        else if (almostEquals(route[0].x, route[1].x)) { this.alignX(routingPoints, 0, correctedX); }
                        else { this.alignY(routingPoints, 0, correctedY); }
                    } else if (index < routingPoints.length - 1) {
                        if (almostEquals(routingPoints[index].x, routingPoints[index + 1].x)) {
                            this.alignX(routingPoints, index, correctedX);
                            this.alignX(routingPoints, index + 1, correctedX);
                        } else {
                            this.alignY(routingPoints, index, correctedY);
                            this.alignY(routingPoints, index + 1, correctedY);
                        }
                    } else {
                        if (almostEquals(route[route.length - 2].x, route[route.length - 1].x)) {
                            this.alignX(routingPoints, routingPoints.length - 1, correctedX);
                        }
                        else { this.alignY(routingPoints, routingPoints.length - 1, correctedY); }
                    }
                    break;
            }
        });
        // console.log('....applyInnerHandleMoves - END edge.routing.length='+edge.routingPoints.length);
    }



}
