/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
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
    CommandExecutionContext,
    CommandReturn,
    GModelElement,
    GModelRoot,
    TYPES,
    Viewport,
    findParentByFeature,
    isBoundsAware,
    isViewport
} from '@eclipse-glsp/sprotty';
import { inject, injectable } from 'inversify';
import { partition } from 'lodash';
//import '../../../css/helper-lines.css';

import {
    BoundsAwareModelElement,
    FeedbackCommand,
    bottom,
    bottomCenter,
    bottomLeft,
    bottomRight,
    center,
    findTopLevelElementByFeature, forEachElement, getMatchingElements,
    getViewportBounds,
    isAbove,
    isBefore,
    left,
    middle,
    middleLeft,
    middleRight,
    right,
    sortBy,
    top,
    topCenter,
    topLeft,
    topRight
} from '@eclipse-glsp/client';
// import { BoundsAwareModelElement, findTopLevelElementByFeature, forEachElement, getMatchingElements } from '../../utils/gmodel-util';
//import { getViewportBounds } from '../../utils/viewpoint-util';
import { HelperLine, HelperLineType, SelectionBounds, isHelperLine, isSelectionBounds } from './model';

export type ViewportLineType = typeof HelperLineType.Center | typeof HelperLineType.Middle | string;

export const ALL_ELEMENT_LINE_TYPES = Object.values(HelperLineType);
export const ALL_VIEWPORT_LINE_TYPES = [HelperLineType.Center, HelperLineType.Middle];

export interface DrawHelperLinesFeedbackAction extends Action {
    kind: typeof DrawHelperLinesFeedbackAction.KIND;
    elementIds: string[];
    elementLines?: HelperLineType[];
    viewportLines?: ViewportLineType[];
    alignmentEpsilon?: number;
}

export namespace DrawHelperLinesFeedbackAction {
    export const KIND = 'drawHelperLines';

    export function create(options: Omit<DrawHelperLinesFeedbackAction, 'kind'>): DrawHelperLinesFeedbackAction {
        return {
            kind: KIND,
            ...options
        };
    }
}

@injectable()
export class DrawHelperLinesFeedbackCommand extends FeedbackCommand {
    static readonly KIND = DrawHelperLinesFeedbackAction.KIND;

    constructor(@inject(TYPES.Action) public action: DrawHelperLinesFeedbackAction) {
        super();
    }

    execute(context: CommandExecutionContext): CommandReturn {
        removeHelperLines(context.root);
        removeSelectionBounds(context.root);
        const boundsAwareElements = getMatchingElements(context.root.index, this.isCompareElement);


        // for (const element of boundsAwareElements) {
        //     console.log(' BEFORE boundsAwareElements : ' + element.type);
        // }

        // Add a filter to exclude elements with specified types
        const filteredMatchElements = boundsAwareElements.filter(element =>
            element.type !== 'BPMNLabel' &&
            element.type !== 'sequenceFlow' &&
            element.type !== 'messageFlow' &&
            element.type !== 'association' &&
            element.type !== 'lane-divider'
        );

        // for (const element of filteredMatchElements) {
        //     console.log(' AFTER boundsAwareElements : ' + element.type);
        // }



        const [referenceElements, elements] = partition(filteredMatchElements, element => this.action.elementIds.includes(element.id));
        if (referenceElements.length === 0) {
            return context.root;
        }
        const referenceBounds = this.calcReferenceBounds(referenceElements);
        const helperLines = this.calcHelperLines(elements, referenceBounds, context);
        if (referenceElements.length > 1) {
            context.root.add(new SelectionBounds(referenceBounds));
        }
        helperLines.forEach(helperLine => context.root.add(helperLine));
        return context.root;
    }

    protected isCompareElement(element: GModelElement): element is BoundsAwareModelElement {
        return isBoundsAware(element) && findTopLevelElementByFeature(element, isBoundsAware, isViewport) === element;
    }

    protected calcReferenceBounds(referenceElements: BoundsAwareModelElement[]): Bounds {


        //return referenceElements.map(element => element.bounds).reduce((combined, next) => Bounds.combine(combined, next), Bounds.EMPTY);
        // for (const element of referenceElements) {
        //     console.log(' BEFORE: included element : ' + element.type);
        // }

        // Add a filter to exclude elements with specified types
        const filteredElements = referenceElements.filter(element =>
            element.type !== 'BPMNLabel' &&
            element.type !== 'sequenceFlow' &&
            element.type !== 'messageFlow' &&
            element.type !== 'association' &&
            element.type !== 'lane-divider'
        );

        // for (const element of filteredElements) {
        //     console.log(' AFTER: included element : ' + element.type);
        // }



        // Use map and reduce on the filtered elements
        return filteredElements.map(element => element.bounds).reduce((combined, next) => Bounds.combine(combined, next), Bounds.EMPTY);


    }

    protected calcHelperLines(elements: BoundsAwareModelElement[], bounds: Bounds, context: CommandExecutionContext): HelperLine[] {
        const helperLines: HelperLine[] = [];
        const viewport = findParentByFeature(context.root, isViewport);
        if (viewport) {
            helperLines.push(...this.calcHelperLinesForViewport(viewport, bounds));
        }
        elements.flatMap(element => this.calcHelperLinesForElement(element, bounds)).forEach(line => helperLines.push(line));
        return helperLines;
    }

    protected calcHelperLinesForViewport(
        root: Viewport & GModelRoot,
        bounds: Bounds,
        lineTypes: HelperLineType[] = this.action.viewportLines ?? ALL_VIEWPORT_LINE_TYPES
    ): HelperLine[] {
        const helperLines: HelperLine[] = [];
        const viewportBounds = getViewportBounds(root, root.canvasBounds);
        if (lineTypes.includes(HelperLineType.Center) && this.isAligned(center, viewportBounds, bounds, 2)) {
            helperLines.push(new HelperLine(topCenter(viewportBounds), bottomCenter(viewportBounds), HelperLineType.Center));
        }
        if (lineTypes.includes(HelperLineType.Middle) && this.isAligned(middle, viewportBounds, bounds, 2)) {
            helperLines.push(new HelperLine(middleLeft(viewportBounds), middleRight(viewportBounds), HelperLineType.Middle));
        }
        return helperLines;
    }

    protected calcHelperLinesForElement(
        element: BoundsAwareModelElement,
        bounds: Bounds,
        lineTypes: HelperLineType[] = this.action.elementLines ?? ALL_ELEMENT_LINE_TYPES
    ): HelperLine[] {
        return this.calcHelperLinesForBounds(element.bounds, bounds, lineTypes);
    }

    protected calcHelperLinesForBounds(
        elementBounds: Bounds,
        bounds: Bounds,
        lineTypes: HelperLineType[] = this.action.elementLines ?? ALL_ELEMENT_LINE_TYPES
    ): HelperLine[] {
        const helperLines: HelperLine[] = [];

        if (lineTypes.includes(HelperLineType.Left) && this.isAligned(left, elementBounds, bounds)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(bottomLeft(below), topLeft(above), HelperLineType.Left));
        }

        if (lineTypes.includes(HelperLineType.Center) && this.isAligned(center, elementBounds, bounds)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(topCenter(above), bottomCenter(below), HelperLineType.Center));
        }

        if (lineTypes.includes(HelperLineType.Right) && this.isAligned(right, elementBounds, bounds)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(bottomRight(below), topRight(above), HelperLineType.Right));
        }

        if (lineTypes.includes(HelperLineType.Bottom) && this.isAligned(bottom, elementBounds, bounds)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(bottomLeft(before), bottomRight(after), HelperLineType.Bottom));
        }

        if (lineTypes.includes(HelperLineType.Middle) && this.isAligned(middle, elementBounds, bounds)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(middleLeft(before), middleRight(after), HelperLineType.Middle));
        }

        if (lineTypes.includes(HelperLineType.Top) && this.isAligned(top, elementBounds, bounds)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(topLeft(before), topRight(after), HelperLineType.Top));
        }

        if (lineTypes.includes(HelperLineType.LeftRight) && this.isMatch(left(elementBounds), right(bounds), 2)) {
            if (isAbove(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomLeft(elementBounds), topRight(bounds), HelperLineType.RightLeft));
            } else {
                helperLines.push(new HelperLine(topLeft(elementBounds), bottomRight(bounds), HelperLineType.RightLeft));
            }
        }

        if (lineTypes.includes(HelperLineType.LeftRight) && this.isMatch(right(elementBounds), left(bounds), 2)) {
            if (isAbove(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomRight(elementBounds), topLeft(bounds), HelperLineType.LeftRight));
            } else {
                helperLines.push(new HelperLine(topRight(elementBounds), bottomLeft(bounds), HelperLineType.LeftRight));
            }
        }

        if (lineTypes.includes(HelperLineType.TopBottom) && this.isMatch(top(elementBounds), bottom(bounds), 2)) {
            if (isBefore(bounds, elementBounds)) {
                helperLines.push(new HelperLine(topRight(elementBounds), bottomLeft(bounds), HelperLineType.BottomTop));
            } else {
                helperLines.push(new HelperLine(topLeft(elementBounds), bottomRight(bounds), HelperLineType.BottomTop));
            }
        }

        if (lineTypes.includes(HelperLineType.TopBottom) && this.isMatch(bottom(elementBounds), top(bounds), 2)) {
            if (isBefore(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomRight(elementBounds), topLeft(bounds), HelperLineType.TopBottom));
            } else {
                helperLines.push(new HelperLine(bottomLeft(elementBounds), topRight(bounds), HelperLineType.TopBottom));
            }
        }

        return helperLines;
    }

    protected isAligned(
        coordinate: (elem: Bounds) => number,
        leftBounds: Bounds,
        rightBounds: Bounds,
        epsilon = this.action.alignmentEpsilon ?? 1
    ): boolean {
        return this.isMatch(coordinate(leftBounds), coordinate(rightBounds), epsilon);
    }

    protected isMatch(leftCoordinate: number, rightCoordinate: number, epsilon = this.action.alignmentEpsilon ?? 1): boolean {
        return Math.abs(leftCoordinate - rightCoordinate) < epsilon;
    }
}

export interface RemoveHelperLinesFeedbackAction extends Action {
    kind: typeof RemoveHelperLinesFeedbackAction.KIND;
}

export namespace RemoveHelperLinesFeedbackAction {
    export const KIND = 'removeHelperLines';

    export function create(options: Omit<RemoveHelperLinesFeedbackAction, 'kind'> = {}): RemoveHelperLinesFeedbackAction {
        return {
            kind: KIND,
            ...options
        };
    }
}

@injectable()
export class RemoveHelperLinesFeedbackCommand extends FeedbackCommand {
    static readonly KIND = RemoveHelperLinesFeedbackAction.KIND;

    constructor(@inject(TYPES.Action) public action: RemoveHelperLinesFeedbackAction) {
        super();
    }
    override execute(context: CommandExecutionContext): CommandReturn {
        removeHelperLines(context.root);
        removeSelectionBounds(context.root);
        return context.root;
    }
}

export function removeHelperLines(root: GModelRoot): void {
    forEachElement(root.index, isHelperLine, line => root.remove(line));
}

export function removeSelectionBounds(root: GModelRoot): void {
    forEachElement(root.index, isSelectionBounds, line => root.remove(line));
}
