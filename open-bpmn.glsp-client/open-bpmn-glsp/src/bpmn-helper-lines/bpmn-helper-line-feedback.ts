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
    BoundsAwareModelElement,
    CommandExecutionContext,
    CommandReturn,
    FeedbackCommand,
    GChildElement,
    GLabel,
    GModelElement,
    GModelRoot,
    ILogger,
    Point,
    TYPES,
    Viewport,
    bottom,
    bottomCenter,
    bottomLeft,
    bottomRight,
    center,
    findParentByFeature,
    findTopLevelElementByFeature,
    forEachElement,
    getMatchingElements,
    getViewportBounds,
    isAbove,
    isBefore,
    isBoundsAware,
    isDecoration,
    isRoutable,
    isViewport,
    isVisibleOnCanvas,
    left,
    middle,
    middleLeft,
    middleRight,
    right,
    sortBy,
    toAbsoluteBounds,
    top,
    topCenter,
    topLeft,
    topRight
} from '@eclipse-glsp/client';


import { inject, injectable } from 'inversify';
import { partition } from 'lodash';
//import './helper-lines.css';
import '../../css/diagram.css';
import { HelperLine, HelperLineType, SelectionBounds, isHelperLine, isSelectionBounds } from './model';

export type ViewportLineType = typeof HelperLineType.Center | typeof HelperLineType.Middle | string;

export type AlignmentElementFilter = (element: BoundsAwareModelElement, referenceElementIds: string[]) => boolean;

export const isTopLevelBoundsAwareElement: AlignmentElementFilter = element =>
    findTopLevelElementByFeature(element, isBoundsAware, isViewport) === element;

export interface DrawHelperLinesFeedbackAction extends Action {
    kind: typeof DrawHelperLinesFeedbackAction.KIND;
    elementIds: string[];
    elementLines?: HelperLineType[];
    viewportLines?: ViewportLineType[];
    alignmentEpsilon?: number;
    alignmentElementFilter?: AlignmentElementFilter;
    debug?: boolean;
}

export const ALL_ELEMENT_LINE_TYPES = Object.values(HelperLineType);
export const ALL_VIEWPORT_LINE_TYPES = [HelperLineType.Center, HelperLineType.Middle];

export const DEFAULT_ELEMENT_LINES = ALL_ELEMENT_LINE_TYPES;
export const DEFAULT_VIEWPORT_LINES = ALL_VIEWPORT_LINE_TYPES;
export const DEFAULT_EPSILON = 1;
export const DEFAULT_ALIGNABLE_ELEMENT_FILTER = (element: BoundsAwareModelElement): boolean =>
    isVisibleOnCanvas(element) && !isRoutable(element) && !(element instanceof GLabel) && !isDecoration(element);
export const DEFAULT_DEBUG = true;

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

    protected elementIds: string[];
    protected elementLines: HelperLineType[];
    protected viewportLines: ViewportLineType[];
    protected alignmentEpsilon: number;
    protected alignableElementFilter: AlignmentElementFilter;
    protected isAlignableElementPredicate: (element: GModelElement) => element is BoundsAwareModelElement;
    protected debug: boolean;

    constructor(
        @inject(TYPES.Action) action: DrawHelperLinesFeedbackAction,
        @inject(TYPES.ILogger) protected logger: ILogger
    ) {
        super();
        this.elementIds = action.elementIds;
        this.elementLines = action.elementLines ?? DEFAULT_ELEMENT_LINES;
        this.viewportLines = action.viewportLines ?? DEFAULT_VIEWPORT_LINES;
        this.alignmentEpsilon = action.alignmentEpsilon ?? DEFAULT_EPSILON;
        this.alignableElementFilter = action.alignmentElementFilter ?? DEFAULT_ALIGNABLE_ELEMENT_FILTER;
        this.isAlignableElementPredicate = this.isAlignableElement.bind(this);
        this.debug = action.debug ?? DEFAULT_DEBUG;
    }

    execute(context: CommandExecutionContext): CommandReturn {
        removeHelperLines(context.root);
        removeSelectionBounds(context.root);
        const alignableElements = getMatchingElements(context.root.index, this.isAlignableElementPredicate);


        console.log('Halllo Welt folgende Elementw sind allignambe :');
        for (const element of alignableElements) {
            console.log('....... Element : ' + element.id + ' type=' + element.type);
        }


        //console.log('All alignable elements: ', alignableElements);
        const [referenceElements, elements] = partition(alignableElements, element => this.elementIds.includes(element.id));
        // console.log('Split alignable elements into reference elements and other elements: ', referenceElements, elements);
        if (referenceElements.length === 0) {
            this.log('--> No helper lines as we do not have any reference elements.');
            return context.root;
        }
        const referenceBounds = this.calcReferenceBounds(referenceElements);
        //  console.log('Bounds encompasing all reference elements: ', referenceBounds);
        const helperLines = this.calcHelperLines(elements, referenceBounds, context);
        if (referenceElements.length > 1) {
            context.root.add(new SelectionBounds(referenceBounds));
            this.log('Render selection bounds for more than one reference element:', referenceBounds);
        }
        helperLines.forEach(helperLine => context.root.add(helperLine));
        if (helperLines.length > 0) {
            this.log(`--> Add ${helperLines.length} helper lines to root:`, helperLines);
        } else {
            this.log('--> Add no helper lines to root.');
        }
        return context.root;
    }

    protected isAlignableElement(element: GModelElement): element is BoundsAwareModelElement {
        return isBoundsAware(element) && this.alignableElementFilter(element, this.elementIds);
    }

    protected calcReferenceBounds(referenceElements: BoundsAwareModelElement[]): Bounds {
        return referenceElements.map(element => this.calcBounds(element)).reduce(Bounds.combine, Bounds.EMPTY);
    }

    protected calcBounds(element: BoundsAwareModelElement): Bounds {
        return toAbsoluteBounds(element);
    }

    protected calcHelperLines(elements: BoundsAwareModelElement[], bounds: Bounds, context: CommandExecutionContext): HelperLine[] {
        const helperLines: HelperLine[] = [];
        const viewport = findParentByFeature(context.root, isViewport);
        if (viewport) {
            helperLines.push(...this.calcHelperLinesForViewport(viewport, bounds, this.viewportLines));
        }
        elements
            .flatMap(element => this.calcHelperLinesForElement(element, bounds, this.elementLines))
            .forEach(line => helperLines.push(line));
        return helperLines;
    }

    protected calcHelperLinesForViewport(root: Viewport & GModelRoot, bounds: Bounds, lineTypes: HelperLineType[]): HelperLine[] {
        const helperLines: HelperLine[] = [];
        this.log('Find helperlines for viewport:', root);
        const viewportBounds = getViewportBounds(root, root.canvasBounds);
        if (lineTypes.includes(HelperLineType.Center) && this.isAligned(center, viewportBounds, bounds, 2)) {
            helperLines.push(new HelperLine(topCenter(viewportBounds), bottomCenter(viewportBounds), HelperLineType.Center));
            this.log('- Reference bounds center align with viewport.', viewportBounds);
        }
        if (lineTypes.includes(HelperLineType.Middle) && this.isAligned(middle, viewportBounds, bounds, 2)) {
            helperLines.push(new HelperLine(middleLeft(viewportBounds), middleRight(viewportBounds), HelperLineType.Middle));
            this.log('- Reference bounds middle align with viewport.', viewportBounds);
        }
        if (helperLines.length > 0) {
            this.log(`--> Add ${helperLines.length} helperlines for viewport:`, helperLines);
        }
        return helperLines;
    }

    protected calcHelperLinesForElement(element: BoundsAwareModelElement, bounds: Bounds, lineTypes: HelperLineType[]): HelperLine[] {
        this.log('Find helperlines for element:', element);
        return this.calcHelperLinesForBounds(this.calcBounds(element), bounds, lineTypes);
    }

    protected calcHelperLinesForBounds(elementBounds: Bounds, bounds: Bounds, lineTypes: HelperLineType[]): HelperLine[] {
        const helperLines: HelperLine[] = [];

        if (lineTypes.includes(HelperLineType.Left) && this.isAligned(left, elementBounds, bounds, this.alignmentEpsilon)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(bottomLeft(below), topLeft(above), HelperLineType.Left));
            this.log('- Reference bounds left align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.Center) && this.isAligned(center, elementBounds, bounds, this.alignmentEpsilon)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(topCenter(above), bottomCenter(below), HelperLineType.Center));
            this.log('- Reference bounds center align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.Right) && this.isAligned(right, elementBounds, bounds, this.alignmentEpsilon)) {
            const [above, below] = sortBy(top, elementBounds, bounds); // higher top-value ==> lower
            helperLines.push(new HelperLine(bottomRight(below), topRight(above), HelperLineType.Right));
            this.log('- Reference bounds right align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.Bottom) && this.isAligned(bottom, elementBounds, bounds, this.alignmentEpsilon)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(bottomLeft(before), bottomRight(after), HelperLineType.Bottom));
            this.log('- Reference bounds bottom align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.Middle) && this.isAligned(middle, elementBounds, bounds, this.alignmentEpsilon)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(middleLeft(before), middleRight(after), HelperLineType.Middle));
            this.log('- Reference bounds middle align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.Top) && this.isAligned(top, elementBounds, bounds, this.alignmentEpsilon)) {
            const [before, after] = sortBy(left, elementBounds, bounds); // higher left-value ==> more to the right
            helperLines.push(new HelperLine(topLeft(before), topRight(after), HelperLineType.Top));
            this.log('- Reference bounds top align with element', elementBounds);
        }

        if (lineTypes.includes(HelperLineType.LeftRight) && this.isMatch(left(elementBounds), right(bounds), this.alignmentEpsilon)) {
            if (isAbove(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomLeft(elementBounds), topRight(bounds), HelperLineType.RightLeft));
                this.log('- Reference bounds right aligns with element left', elementBounds);
            } else {
                helperLines.push(new HelperLine(topLeft(elementBounds), bottomRight(bounds), HelperLineType.RightLeft));
                this.log('- Reference bounds right aligns with element left', elementBounds);
            }
        }

        if (lineTypes.includes(HelperLineType.LeftRight) && this.isMatch(right(elementBounds), left(bounds), this.alignmentEpsilon)) {
            if (isAbove(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomRight(elementBounds), topLeft(bounds), HelperLineType.LeftRight));
                this.log('- Reference bounds left aligns with element right', elementBounds);
            } else {
                helperLines.push(new HelperLine(topRight(elementBounds), bottomLeft(bounds), HelperLineType.LeftRight));
                this.log('- Reference bounds left aligns with element right', elementBounds);
            }
        }

        if (lineTypes.includes(HelperLineType.TopBottom) && this.isMatch(top(elementBounds), bottom(bounds), this.alignmentEpsilon)) {
            if (isBefore(bounds, elementBounds)) {
                helperLines.push(new HelperLine(topRight(elementBounds), bottomLeft(bounds), HelperLineType.BottomTop));
                this.log('- Reference bounds bottom aligns with element top', elementBounds);
            } else {
                helperLines.push(new HelperLine(topLeft(elementBounds), bottomRight(bounds), HelperLineType.BottomTop));
                this.log('- Reference bounds bottom aligns with element top', elementBounds);
            }
        }

        if (lineTypes.includes(HelperLineType.TopBottom) && this.isMatch(bottom(elementBounds), top(bounds), this.alignmentEpsilon)) {
            if (isBefore(bounds, elementBounds)) {
                helperLines.push(new HelperLine(bottomRight(elementBounds), topLeft(bounds), HelperLineType.TopBottom));
                this.log('- Reference bounds top aligns with element bottom', elementBounds);
            } else {
                helperLines.push(new HelperLine(bottomLeft(elementBounds), topRight(bounds), HelperLineType.TopBottom));
                this.log('- Reference bounds top aligns with element bottom', elementBounds);
            }
        }
        if (helperLines.length > 0) {
            this.log(`--> Add ${helperLines.length} helperlines for element:`, helperLines);
        }

        return helperLines;
    }

    protected isAligned(coordinate: (elem: Bounds) => number, leftBounds: Bounds, rightBounds: Bounds, epsilon: number): boolean {
        return this.isMatch(coordinate(leftBounds), coordinate(rightBounds), epsilon);
    }

    protected isMatch(leftCoordinate: number, rightCoordinate: number, epsilon: number): boolean {
        return Math.abs(leftCoordinate - rightCoordinate) < epsilon;
    }

    protected log(message: string, ...params: any[]): void {
        if (this.debug) {
            this.logger.log(this, message, params);
        }
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

export function boundsInViewport(element: GModelElement, bounds: Bounds | Point): Bounds | Point {
    if (element instanceof GChildElement && !isViewport(element.parent)) {
        return boundsInViewport(element.parent, element.parent.localToParent(bounds) as Bounds);
    } else {
        return bounds;
    }
}
