/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
import {
    AbstractEdgeRouter,
    Bounds,
    DefaultAnchors,
    GRoutableElement,
    GRoutingHandle,
    LinearRouteOptions,
    Point,
    ResolvedHandleMove,
    RoutedPoint,
    Side,
    translateBounds
} from '@eclipse-glsp/client';
import { injectable } from 'inversify';

export interface BPMNRouterOptions extends LinearRouteOptions {
    standardDistance: number;
}

/**
 * BPMN2ManhattanRouter - A custom Manhattan Edge Router for BPMN diagrams.
 *
 * Architecture (based on Sprotty ManhattanEdgeRouter):
 *
 * 1. applyInnerHandleMoves() only aligns routing points along their constrained
 *    axis — no cleanup, no addAdditionalCorner. Identical to Sprotty.
 *
 * 2. cleanupRoutingPoints() is called by createRoutedCorners() on a COPY of
 *    edge.routingPoints (updateHandles=false, addRoutingPoints=true).
 *    It handles BPMN anchor removal, addAdditionalCorner with handle index
 *    shifting, and manhattanify. Handle index shifting is applied when
 *    called with updateHandles=true (e.g. after mouse-up via createRoutingHandles).
 *
 * 3. Element movement detection (BPMN extension):
 *    edgeElementPositions stores the last known source/target bounds per edge.
 *    If bounds changed → elementMoved=true → applyFollowLogic() adjusts nearest
 *    corners to follow the moved element. Write-back to edge.routingPoints
 *    happens only in this case.
 *    If bounds unchanged → standard Sprotty cleanup path.
 *
 * 4. BPMN files store source/target anchors as first/last waypoint.
 *    cleanupRoutingPoints() removes them via the standard Bounds.includes() check.
 */
@injectable()
export class BPMNManhattanRouter extends AbstractEdgeRouter {

    static readonly KIND = 'manhattan';

    // Set to true to enable verbose logging
    private debugMode: boolean = false;

    // Stores the last known source/target positions per edge.
    // Used to detect element movement vs. handle drags.
    private edgeElementPositions: Map<string, {
        sourceX: number, sourceY: number,
        targetX: number, targetY: number
    }> = new Map();

    get kind(): string {
        return BPMNManhattanRouter.KIND;
    }

    protected getOptions(edge: GRoutableElement): BPMNRouterOptions {
        return {
            standardDistance: 20,
            minimalPointDistance: 3,
            selfEdgeOffset: 0.25
        };
    }

    // ──────────────────────────────────────────────
    // Core routing (Sprotty pattern)
    // ──────────────────────────────────────────────

    /**
     * Main entry point called by the framework on every render cycle.
     * Pure rendering function — never writes to edge.routingPoints directly.
     * Write-back happens only via commitRoute() or the elementMoved path
     * in createRoutedCorners().
     */
    override route(edge: GRoutableElement): RoutedPoint[] {
        if (!edge.source || !edge.target) {
            return [];
        }
        // Guard against reconnect state where bounds are not yet initialized.
        if (!edge.source.bounds || !edge.target.bounds) {
            return [];
        }

        const routedCorners = this.createRoutedCorners(edge);
        const sourceRefPoint = routedCorners[0] ?? Bounds.center(edge.target.bounds);
        const targetRefPoint = routedCorners[routedCorners.length - 1] ?? Bounds.center(edge.source.bounds);

        const sourceAnchor = this.getTranslatedAnchor(
            edge.source, sourceRefPoint, edge.parent, edge, edge.sourceAnchorCorrection
        );
        const targetAnchor = this.getTranslatedAnchor(
            edge.target, targetRefPoint, edge.parent, edge, edge.targetAnchorCorrection
        );

        if (!sourceAnchor || !targetAnchor) {
            return [];
        }

        const result: RoutedPoint[] = [];
        result.push({ kind: 'source', ...sourceAnchor });
        routedCorners.forEach(corner => result.push(corner));
        result.push({ kind: 'target', ...targetAnchor });

        // Guard: filter out any points with NaN coordinates before handing the
        // result to the framework renderer. NaN values in segments cause
        // "Cannot determine direction of line" in createPathForSegments.
        const sanitized = result.filter(p => !isNaN(p.x) && !isNaN(p.y));

        // Guard: renderAdditionals requires at least source + target point.
        // Return empty array if sanitizing removed too many points.
        if (sanitized.length < 2) {
            return [];
        }

        this.debug(`route() edge=${edge.id} corners=${routedCorners.length} existingPoints=${edge.routingPoints.length}`);
        this.debugPoints('  result', sanitized);

        return sanitized;
    }

    /**
     * Computes intermediate corner points.
     *
     * Follows Sprotty pattern: works on a COPY of edge.routingPoints,
     * calls cleanupRoutingPoints(false, true) on the copy.
     * edge.routingPoints is only written back in the elementMoved case.
     *
     * BPMN extension: detects element movement via edgeElementPositions.
     * If moved → applyFollowLogic() adjusts nearest corners, then
     * cleanupRoutingPoints(false, false) for basic cleanup without
     * re-inserting corners. Write-back ensures subsequent route() calls
     * in the same frame see the updated positions.
     */
    protected createRoutedCorners(edge: GRoutableElement): RoutedPoint[] {

        // Guard against reconnect state where source/target exist but bounds
        // are not yet fully initialized.
        if (!edge.source?.bounds || !edge.target?.bounds) {
            return [];
        }

        if (edge.routingPoints && edge.routingPoints.length > 0) {

            // Step 1: Detect element movement (BPMN extension).
            const srcBounds = edge.source!.bounds;
            const tgtBounds = edge.target!.bounds;
            const lastPos = this.edgeElementPositions.get(edge.id);
            const elementMoved = lastPos !== undefined
                && (lastPos.sourceX !== srcBounds.x
                || lastPos.sourceY !== srcBounds.y
                || lastPos.targetX !== tgtBounds.x
                || lastPos.targetY !== tgtBounds.y);

            this.edgeElementPositions.set(edge.id, {
                sourceX: srcBounds.x, sourceY: srcBounds.y,
                targetX: tgtBounds.x, targetY: tgtBounds.y
            });

            this.debug(`createRoutedCorners edge=${edge.id} elementMoved=${elementMoved}`);

            // Step 2: Work on a copy (Sprotty pattern).
            const points = edge.routingPoints.slice();

            if (elementMoved) {
                // BPMN extension: adjust nearest corners to follow the moved element.
                // Then basic cleanup (anchor removal only, no corner re-insertion)
                // to keep the preserved route intact.
                this.applyFollowLogic(points, edge);
                this.cleanupRoutingPoints(edge, points, false, false);
                // Write-back so subsequent route() calls in the same frame
                // see the updated corner positions.
                edge.routingPoints = points.map(p => ({
                    x: Math.round(p.x),
                    y: Math.round(p.y)
                }));
            } else {
                // Standard Sprotty path: full cleanup including addAdditionalCorner
                // and manhattanify. No write-back — edge.routingPoints stays unchanged.
                this.cleanupRoutingPoints(edge, points, false, true);
            }

            if (points.length > 0) {
                return points.map((rp, index) => ({
                    kind: 'linear' as const,
                    pointIndex: index,
                    x: Math.round(rp.x),
                    y: Math.round(rp.y)
                }));
            }
        }

        // No routing points → calculate default corners.
        const sourceAnchors = new DefaultAnchors(edge.source!, edge.parent, 'source');
        const targetAnchors = new DefaultAnchors(edge.target!, edge.parent, 'target');
        const options = this.getOptions(edge);
        const bestAnchors = this.getBestConnectionAnchors(sourceAnchors, targetAnchors, options);
        this.debug(`  createRoutedCorners: default source=${bestAnchors.source} target=${bestAnchors.target}`);
        const corners = this.calculateCorners(sourceAnchors, targetAnchors, bestAnchors, options);

        return corners.map((corner, index) => ({
            kind: 'linear' as const,
            pointIndex: index,
            x: Math.round(corner.x),
            y: Math.round(corner.y)
        }));
    }

    /**
     * Adjusts the nearest corner points when source or target element has moved.
     * The corner adjacent to the moved element slides along its axis to keep
     * the route orthogonal.
     *
     * This is the BPMN-specific "follow" logic that replaces Sprotty's default
     * behavior of recalculating the entire route on element movement.
     *
     * IMPORTANT - Coordinate space:
     * edge.source.bounds and edge.target.bounds are LOCAL to each element's
     * direct parent (e.g. a Pool or Lane), not relative to edge.parent.
     * As long as source and target live in the same container, this doesn't
     * matter (or cancels out). But for MessageFlow/Association edges that cross
     * container boundaries - e.g. source outside any Pool, target inside a Pool -
     * the two bounds live in different coordinate systems and must NOT be
     * compared or averaged directly.
     *
     * Both sourceBounds and targetBounds are therefore translated into
     * edge.parent's coordinate system first (via translateBounds), mirroring
     * what DefaultAnchors and getTranslatedAnchor() already do internally.
     * Skipping this translation was the root cause of a bug where moving an
     * element connected via a cross-Pool MessageFlow/Association caused the
     * router to compute corner points at seemingly random positions on the
     * diagram - the offset roughly matched the Pool's own position relative
     * to the diagram root.
     */
    protected applyFollowLogic(points: Point[], edge: GRoutableElement): void {
        const sourceAnchor = this.getTranslatedAnchor(
            edge.source!, points[0], edge.parent, edge, edge.sourceAnchorCorrection
        );
        const targetAnchor = this.getTranslatedAnchor(
            edge.target!, points[points.length - 1], edge.parent, edge, edge.targetAnchorCorrection
        );

        if (!sourceAnchor || !targetAnchor) {
            return;
        }

        // Translate source/target bounds into edge.parent's coordinate system,
        // exactly as DefaultAnchors does internally. Without this, elements
        // inside a Pool/Lane (whose bounds are relative to that container)
        // are compared against elements outside it in the wrong coordinate space.
        const sourceBounds = translateBounds(edge.source!.bounds, edge.source!.parent, edge.parent);
        const targetBounds = translateBounds(edge.target!.bounds, edge.target!.parent, edge.parent);

        if (points.length === 1) {
            const isVertical = Math.abs(points[0].x - sourceAnchor.x) < 5;
            if (isVertical) {
                points[0] = {
                    x: sourceAnchor.x,
                    y: Math.round(Bounds.center(targetBounds).y)
                };
            } else {
                points[0] = {
                    x: Math.round(Bounds.center(targetBounds).x),
                    y: sourceAnchor.y
                };
            }
        } else {
            // Adjust first corner to follow source element.
            const isFirstVertical = Math.abs(points[0].x - points[1].x) < 1;
            if (isFirstVertical) {
                points[0] = {
                    x: points[0].x,
                    y: Math.round(Bounds.center(sourceBounds).y)
                };
            } else {
                points[0] = {
                    x: Math.round(Bounds.center(sourceBounds).x),
                    y: points[0].y
                };
            }
            // Adjust last corner to follow target element.
            const last = points.length - 1;
            const isLastVertical = Math.abs(points[last].x - points[last - 1].x) < 1;
            if (isLastVertical) {
                points[last] = {
                    x: points[last].x,
                    y: Math.round(Bounds.center(targetBounds).y)
                };
            } else {
                points[last] = {
                    x: Math.round(Bounds.center(targetBounds).x),
                    y: points[last].y
                };
            }
        }
    }

    // ──────────────────────────────────────────────
    // Handle management (Sprotty pattern)
    // ──────────────────────────────────────────────

    /**
     * Called when the user selects an edge to show routing handles.
     * Delegates to commitRoute() then creates all handles with fresh indices.
     */
    override createRoutingHandles(edge: GRoutableElement): void {
        const routedPoints = this.route(edge);
        this.debug(`createRoutingHandles edge=${edge.id} (${routedPoints.length} points)`);
        this.debugPoints('  routedPoints', routedPoints);
        this.commitRoute(edge, routedPoints);
        if (routedPoints.length > 0) {
            this.addHandle(edge, 'source', 'routing-point', -2);
            for (let i = 0; i < routedPoints.length - 1; i++) {
                this.addHandle(edge, 'manhattan-50%', 'volatile-routing-point', i - 1);
            }
            this.addHandle(edge, 'target', 'routing-point', routedPoints.length - 2);
        }
    }

    /**
     * Returns the visual position of an inner routing handle (midpoint of segment).
     */
    override getInnerHandlePosition(edge: GRoutableElement, route: RoutedPoint[], handle: GRoutingHandle): Point | undefined {
        if (handle.kind === 'manhattan-50%') {
            const { start, end } = this.findRouteSegment(edge, route, handle.pointIndex);
            if (start !== undefined && end !== undefined) {
                return Point.linear(start, end, 0.5);
            }
        }
        return undefined;
    }

    /**
     * Called while the user drags a routing handle.
     *
     * Follows Sprotty exactly: only aligns the affected routing point(s)
     * along their constrained axis. No addAdditionalCorner here.
     * Orthogonality cleanup happens on the next render cycle via
     * createRoutedCorners() → cleanupRoutingPoints(false, true).
     *
     * correctX/correctY snap to adjacent points when within minimalPointDistance
     * to avoid degenerate zero-length segments.
     */
    override applyInnerHandleMoves(edge: GRoutableElement, moves: ResolvedHandleMove[]): void {
        this.debug(`applyInnerHandleMoves edge=${edge.id} moves=${moves.length}`);

        const route = this.route(edge);
        const routingPoints = edge.routingPoints;
        const minimalPointDistance = this.getOptions(edge).minimalPointDistance;

        moves.forEach(move => {
            const handle = move.handle;
            const index = handle.pointIndex;

            this.debug(`  handle kind=${handle.kind} index=${index} to=(${move.toPosition.x},${move.toPosition.y})`);

            if (handle.kind === 'manhattan-50%') {
                const correctedX = this.correctX(routingPoints, index, move.toPosition.x, minimalPointDistance);
                const correctedY = this.correctY(routingPoints, index, move.toPosition.y, minimalPointDistance);

                if (index < 0) {
                    // First segment: source anchor → first corner
                    if (routingPoints.length === 0) {
                        routingPoints.push({ x: correctedX, y: correctedY });
                        handle.pointIndex = 0;
                    } else if (this.almostEquals(route[0].x, route[1].x)) {
                        this.alignX(routingPoints, 0, correctedX);
                    } else {
                        this.alignY(routingPoints, 0, correctedY);
                    }
                } else if (index < routingPoints.length - 1) {
                    // Inner segment: move both endpoints of the segment
                    if (this.almostEquals(routingPoints[index].x, routingPoints[index + 1].x)) {
                        this.alignX(routingPoints, index, correctedX);
                        this.alignX(routingPoints, index + 1, correctedX);
                    } else {
                        this.alignY(routingPoints, index, correctedY);
                        this.alignY(routingPoints, index + 1, correctedY);
                    }
                } else {
                    // Last segment: last corner → target anchor
                    if (routingPoints.length === 0) {
                        routingPoints.push({ x: correctedX, y: correctedY });
                        handle.pointIndex = 0;
                    } else if (this.almostEquals(route[route.length - 2].x, route[route.length - 1].x)) {
                        this.alignX(routingPoints, routingPoints.length - 1, correctedX);
                    } else {
                        this.alignY(routingPoints, routingPoints.length - 1, correctedY);
                    }
                }

                this.debugPoints('  routingPoints after move', routingPoints);
            }
        });
    }

    // ──────────────────────────────────────────────
    // Routing point cleanup (Sprotty pattern)
    // ──────────────────────────────────────────────

    /**
     * Cleans up routing points after a route change.
     *
     * Ported from Sprotty ManhattanEdgeRouter.cleanupRoutingPoints():
     * 1. Remove leading RPs inside source bounds (BPMN anchor removal included)
     * 2. Remove trailing RPs inside target bounds
     * 3. Remove degenerate segments shorter than minimalPointDistance
     * 4. If addRoutingPoints=true: addAdditionalCorner + manhattanify
     *
     * updateHandles=true: also shifts handle pointIndices and adds/removes
     * handle elements on the edge (used by addAdditionalCorner).
     * updateHandles=false: only modifies the routingPoints array (used during
     * rendering in createRoutedCorners).
     */
    override cleanupRoutingPoints(edge: GRoutableElement,routingPoints: Point[], updateHandles: boolean, addRoutingPoints: boolean ): void {

        // Guard against reconnect state where bounds are not yet available.
        if (!edge.source?.bounds || !edge.target?.bounds) {
            return;
        }
        const sourceAnchors = new DefaultAnchors(edge.source!, edge.parent, 'source');
        const targetAnchors = new DefaultAnchors(edge.target!, edge.parent, 'target');

        if (this.resetRoutingPointsOnReconnect(edge, routingPoints, updateHandles, sourceAnchors, targetAnchors)) {
            return;
        }

        // Remove leading routing points inside source bounds.
        // Also removes BPMN source anchor waypoints stored in the BPMN file.
        for (let i = 0; i < routingPoints.length; i++) {
            if (Bounds.includes(sourceAnchors.bounds, routingPoints[i])) {
                routingPoints.splice(0, 1);
                if (updateHandles) {
                    this.removeHandle(edge, -1);
                }
            } else {
                break;
            }
        }

        // Remove trailing routing points inside target bounds.
        // Also removes BPMN target anchor waypoints stored in the BPMN file.
        for (let i = routingPoints.length - 1; i >= 0; i--) {
            if (Bounds.includes(targetAnchors.bounds, routingPoints[i])) {
                routingPoints.splice(i, 1);
                if (updateHandles) {
                    this.removeHandle(edge, i);
                }
            } else {
                break;
            }
        }

        // Remove degenerate segments shorter than minimalPointDistance.
        if (routingPoints.length >= 2) {
            const options = this.getOptions(edge);
            for (let i = routingPoints.length - 2; i >= 0; i--) {
                if (Point.manhattanDistance(routingPoints[i], routingPoints[i + 1]) < options.minimalPointDistance) {
                    routingPoints.splice(i, 2);
                    i--;
                    if (updateHandles) {
                        this.removeHandle(edge, i - 1);
                        this.removeHandle(edge, i);
                    }
                }
            }
        }

        if (addRoutingPoints) {
            this.addAdditionalCorner(edge, routingPoints, sourceAnchors, targetAnchors, updateHandles);
            this.addAdditionalCorner(edge, routingPoints, targetAnchors, sourceAnchors, updateHandles);
            this.manhattanify(routingPoints);
        }
    }

    /**
     * Removes a routing handle at the given pointIndex from the edge's children.
     * All handles with a higher pointIndex are shifted down by 1.
     * Ported from Sprotty ManhattanEdgeRouter.removeHandle().
     */
    protected removeHandle(edge: GRoutableElement, pointIndex: number): void {
        const toBeRemoved: GRoutingHandle[] = [];
        edge.children.forEach(child => {
            if (child instanceof GRoutingHandle) {
                if (child.pointIndex > pointIndex) {
                    child.pointIndex--;
                } else if (child.pointIndex === pointIndex) {
                    toBeRemoved.push(child);
                }
            }
        });
        toBeRemoved.forEach(child => edge.remove(child));
    }

    /**
     * Inserts an additional corner point if the first/last routing point lies
     * outside the element bounds in the direction of the adjacent segment.
     *
     * When updateHandles=true, also shifts all affected handle pointIndices
     * and adds a new volatile handle for the new segment (Sprotty pattern).
     * This ensures handles always reference the correct segments after insertion.
     *
     * Ported from Sprotty ManhattanEdgeRouter.addAdditionalCorner().
     */
    protected addAdditionalCorner(edge: GRoutableElement, points: Point[],
        currentAnchors: DefaultAnchors, otherAnchors: DefaultAnchors, updateHandles: boolean): void {
        if (points.length === 0) {
            return;
        }

        const isSource = currentAnchors.kind === 'source';
        const refPoint = isSource ? points[0] : points[points.length - 1];
        const insertIndex = isSource ? 0 : points.length;
        // shiftIndex: for source insertion at 0 → -1 (shifts all handles)
        //             for target insertion at end → points.length-1 (shifts none)
        const shiftIndex = insertIndex - (isSource ? 1 : 0);

        let isHorizontal: boolean;
        if (points.length > 1) {
            isHorizontal = isSource
                ? this.almostEquals(points[0].x, points[1].x)
                : this.almostEquals(points[points.length - 1].x, points[points.length - 2].x);
        } else {
            const nearestSide = otherAnchors.getNearestSide(refPoint);
            isHorizontal = nearestSide === Side.TOP || nearestSide === Side.BOTTOM;
        }

        if (isHorizontal) {
            const topY = currentAnchors.get(Side.TOP).y;
            const bottomY = currentAnchors.get(Side.BOTTOM).y;
            if (refPoint.y < topY || refPoint.y > bottomY) {
                const newPoint: Point = {
                    x: currentAnchors.get(Side.TOP).x,
                    y: refPoint.y
                };
                this.debug(`addAdditionalCorner (${currentAnchors.kind}): inserting at x=${newPoint.x} y=${newPoint.y}`);
                points.splice(insertIndex, 0, newPoint);
                if (updateHandles) {
                    edge.children.forEach(child => {
                        if (child instanceof GRoutingHandle && child.pointIndex >= shiftIndex) {
                            child.pointIndex++;
                        }
                    });
                    this.addHandle(edge, 'manhattan-50%', 'volatile-routing-point', shiftIndex);
                }
            }
        } else {
            const leftX = currentAnchors.get(Side.LEFT).x;
            const rightX = currentAnchors.get(Side.RIGHT).x;
            if (refPoint.x < leftX || refPoint.x > rightX) {
                const newPoint: Point = {
                    x: refPoint.x,
                    y: currentAnchors.get(Side.LEFT).y
                };
                this.debug(`addAdditionalCorner (${currentAnchors.kind}): inserting at x=${newPoint.x} y=${newPoint.y}`);
                points.splice(insertIndex, 0, newPoint);
                if (updateHandles) {
                    edge.children.forEach(child => {
                        if (child instanceof GRoutingHandle && child.pointIndex >= shiftIndex) {
                            child.pointIndex++;
                        }
                    });
                    this.addHandle(edge, 'manhattan-50%', 'volatile-routing-point', shiftIndex);
                }
            }
        }
    }

    /**
     * Ensures all segments are strictly orthogonal.
     * Inserts intermediate corner points for any diagonal segments.
     */
    protected manhattanify(points: Point[]): void {
        for (let i = 1; i < points.length; i++) {
            const isVertical = Math.abs(points[i - 1].x - points[i].x) < 1;
            const isHorizontal = Math.abs(points[i - 1].y - points[i].y) < 1;
            if (!isVertical && !isHorizontal) {
                this.debug(`manhattanify: fixing diagonal at index=${i}`);
                points.splice(i, 0, { x: points[i - 1].x, y: points[i].y });
                i++;
            }
        }
    }

    // ──────────────────────────────────────────────
    // Default corner calculation
    // ──────────────────────────────────────────────

    /**
     * Determines the best sides (TOP/BOTTOM/LEFT/RIGHT) on source and target
     * for a new default route. Evaluated in priority order:
     *   1. Direct connections (0 corners)
     *   2. One-corner connections
     *   3. Two-corner connections (fallback)
     */
    protected getBestConnectionAnchors(sourceAnchors: DefaultAnchors, targetAnchors: DefaultAnchors,
        options: BPMNRouterOptions): { source: Side, target: Side } {
        const sd = options.standardDistance;

        // Direct connections (0 corners) ─────────────────────────────────
        if (targetAnchors.get(Side.LEFT).x - sourceAnchors.get(Side.RIGHT).x > sd) {
            return { source: Side.RIGHT, target: Side.LEFT };
        }
        if (sourceAnchors.get(Side.LEFT).x - targetAnchors.get(Side.RIGHT).x > sd) {
            return { source: Side.LEFT, target: Side.RIGHT };
        }
        if (sourceAnchors.get(Side.TOP).y - targetAnchors.get(Side.BOTTOM).y > sd) {
            return { source: Side.TOP, target: Side.BOTTOM };
        }
        if (targetAnchors.get(Side.TOP).y - sourceAnchors.get(Side.BOTTOM).y > sd) {
            return { source: Side.BOTTOM, target: Side.TOP };
        }

        // One-corner connections ──────────────────────────────────────────
        if (targetAnchors.get(Side.TOP).x - sourceAnchors.get(Side.RIGHT).x > 0.5 * sd
            && targetAnchors.get(Side.TOP).y - sourceAnchors.get(Side.RIGHT).y > sd) {
            return { source: Side.RIGHT, target: Side.TOP };
        }
        if (targetAnchors.get(Side.BOTTOM).x - sourceAnchors.get(Side.RIGHT).x > 0.5 * sd
            && sourceAnchors.get(Side.RIGHT).y - targetAnchors.get(Side.BOTTOM).y > sd) {
            return { source: Side.RIGHT, target: Side.BOTTOM };
        }
        if (sourceAnchors.get(Side.LEFT).x - targetAnchors.get(Side.BOTTOM).x > 0.5 * sd
            && sourceAnchors.get(Side.LEFT).y - targetAnchors.get(Side.BOTTOM).y > sd) {
            return { source: Side.LEFT, target: Side.BOTTOM };
        }
        if (sourceAnchors.get(Side.LEFT).x - targetAnchors.get(Side.TOP).x > 0.5 * sd
            && targetAnchors.get(Side.TOP).y - sourceAnchors.get(Side.LEFT).y > sd) {
            return { source: Side.LEFT, target: Side.TOP };
        }
        if (targetAnchors.get(Side.RIGHT).y - sourceAnchors.get(Side.BOTTOM).y > 0.5 * sd
            && sourceAnchors.get(Side.BOTTOM).x - targetAnchors.get(Side.RIGHT).x > sd) {
            return { source: Side.BOTTOM, target: Side.RIGHT };
        }
        if (targetAnchors.get(Side.LEFT).y - sourceAnchors.get(Side.BOTTOM).y > 0.5 * sd
            && targetAnchors.get(Side.LEFT).x - sourceAnchors.get(Side.BOTTOM).x > sd) {
            return { source: Side.BOTTOM, target: Side.LEFT };
        }

        // Two-corner connections (fallback) ───────────────────────────────
        const srcTop = sourceAnchors.get(Side.TOP);
        const tgtTop = targetAnchors.get(Side.TOP);
        if (!Bounds.includes(targetAnchors.bounds, srcTop)
            && !Bounds.includes(sourceAnchors.bounds, tgtTop)) {
            return { source: Side.TOP, target: Side.TOP };
        }
        const srcRight = sourceAnchors.get(Side.RIGHT);
        const tgtRight = targetAnchors.get(Side.RIGHT);
        if (!Bounds.includes(targetAnchors.bounds, srcRight)
            && !Bounds.includes(sourceAnchors.bounds, tgtRight)) {
            return { source: Side.RIGHT, target: Side.RIGHT };
        }

        return { source: Side.RIGHT, target: Side.LEFT };
    }

    /**
     * Calculates corner points for a new default route between two sides.
     * Returns 0, 1, or 2 corner points depending on relative geometry.
     */
    protected calculateCorners(sourceAnchors: DefaultAnchors, targetAnchors: DefaultAnchors,
        sides: { source: Side, target: Side }, options: BPMNRouterOptions ): Point[] {
        const src = sourceAnchors.get(sides.source);
        const tgt = targetAnchors.get(sides.target);
        const sd = options.standardDistance;

        switch (sides.source) {
            case Side.RIGHT:
                switch (sides.target) {
                    case Side.LEFT: {
                        if (src.y !== tgt.y) {
                            const midX = Math.round((src.x + tgt.x) / 2);
                            return [{ x: midX, y: src.y }, { x: midX, y: tgt.y }];
                        }
                        return [];
                    }
                    case Side.TOP:
                    case Side.BOTTOM:
                        return [{ x: tgt.x, y: src.y }];
                    case Side.RIGHT: {
                        const maxX = Math.round(Math.max(src.x, tgt.x) + 1.5 * sd);
                        return [{ x: maxX, y: src.y }, { x: maxX, y: tgt.y }];
                    }
                }
                break;

            case Side.LEFT:
                switch (sides.target) {
                    case Side.RIGHT: {
                        if (src.y !== tgt.y) {
                            const midX = Math.round((src.x + tgt.x) / 2);
                            return [{ x: midX, y: src.y }, { x: midX, y: tgt.y }];
                        }
                        return [];
                    }
                    case Side.TOP:
                    case Side.BOTTOM:
                        return [{ x: tgt.x, y: src.y }];
                    default: {
                        const minX = Math.round(Math.min(src.x, tgt.x) - 1.5 * sd);
                        return [{ x: minX, y: src.y }, { x: minX, y: tgt.y }];
                    }
                }

            case Side.TOP:
                switch (sides.target) {
                    case Side.BOTTOM: {
                        if (src.x !== tgt.x) {
                            const midY = Math.round((src.y + tgt.y) / 2);
                            return [{ x: src.x, y: midY }, { x: tgt.x, y: midY }];
                        }
                        return [];
                    }
                    case Side.TOP: {
                        const minY = Math.round(Math.min(src.y, tgt.y) - 1.5 * sd);
                        return [{ x: src.x, y: minY }, { x: tgt.x, y: minY }];
                    }
                    case Side.RIGHT:
                    case Side.LEFT:
                        return [{ x: src.x, y: tgt.y }];
                }
                break;

            case Side.BOTTOM:
                switch (sides.target) {
                    case Side.TOP: {
                        if (src.x !== tgt.x) {
                            const midY = Math.round((src.y + tgt.y) / 2);
                            return [{ x: src.x, y: midY }, { x: tgt.x, y: midY }];
                        }
                        return [];
                    }
                    case Side.BOTTOM: {
                        const maxY = Math.round(Math.max(src.y, tgt.y) + 1.5 * sd);
                        return [{ x: src.x, y: maxY }, { x: tgt.x, y: maxY }];
                    }
                    case Side.RIGHT:
                    case Side.LEFT:
                        return [{ x: src.x, y: tgt.y }];
                }
                break;
        }

        const midX = Math.round((src.x + tgt.x) / 2);
        return [{ x: midX, y: src.y }, { x: midX, y: tgt.y }];
    }

    // ──────────────────────────────────────────────
    // Helper methods (Sprotty pattern)
    // ──────────────────────────────────────────────

    /**
     * Snaps x to the adjacent routing point's x if within minimalPointDistance.
     * Prevents degenerate zero-length segments during handle drags.
     */
    protected correctX(routingPoints: Point[], index: number, x: number, minimalPointDistance: number): number {
        if (index > 0 && Math.abs(x - routingPoints[index - 1].x) < minimalPointDistance) {
            return routingPoints[index - 1].x;
        } else if (index < routingPoints.length - 2
            && Math.abs(x - routingPoints[index + 2].x) < minimalPointDistance) {
            return routingPoints[index + 2].x;
        }
        return x;
    }

    /**
     * Snaps y to the adjacent routing point's y if within minimalPointDistance.
     */
    protected correctY(routingPoints: Point[], index: number, y: number, minimalPointDistance: number): number {
        if (index > 0 && Math.abs(y - routingPoints[index - 1].y) < minimalPointDistance) {
            return routingPoints[index - 1].y;
        } else if (index < routingPoints.length - 2
            && Math.abs(y - routingPoints[index + 2].y) < minimalPointDistance) {
            return routingPoints[index + 2].y;
        }
        return y;
    }

    protected almostEquals(a: number, b: number): boolean {
        return Math.abs(a - b) < 1;
    }

    protected alignX(points: Point[], index: number, x: number): void {
        if (index >= 0 && index < points.length) {
            points[index] = { x, y: points[index].y };
        }
    }

    protected alignY(points: Point[], index: number, y: number): void {
        if (index >= 0 && index < points.length) {
            points[index] = { x: points[index].x, y };
        }
    }

    // ──────────────────────────────────────────────
    // Debug methods
    // ──────────────────────────────────────────────

    protected debug(message: string): void {
        if (this.debugMode) {
            console.log('├── ' + message);
        }
    }

    protected debugPoints(label: string, points: { x: number; y: number; kind?: string }[]): void {
        if (this.debugMode) {
            console.log('│   ├── ' + label + ' (' + points.length + ')');
            points.forEach((p, i) => {
                const kind = 'kind' in p ? ` kind=${p.kind}` : '';
                console.log(`│   │   ├── [${i}]${kind} x=${p.x} y=${p.y}`);
            });
        }
    }
}
