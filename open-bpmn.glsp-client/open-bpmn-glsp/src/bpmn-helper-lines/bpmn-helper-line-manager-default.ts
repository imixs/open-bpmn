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
    DisposableCollection,
    GModelElement,
    GModelRoot,
    IActionHandler,
    IFeedbackActionDispatcher,
    ISelectionListener,
    MoveAction,
    MoveFinishedEventAction, MoveInitializedEventAction,
    Point,
    SelectionService,
    SetBoundsAction,
    SetBoundsFeedbackAction,
    TYPES
} from '@eclipse-glsp/client';


import { inject, injectable, optional, postConstruct } from 'inversify';

import {
    AlignmentElementFilter,
    DEFAULT_ALIGNABLE_ELEMENT_FILTER,
    DEFAULT_DEBUG,
    DEFAULT_ELEMENT_LINES,
    DEFAULT_EPSILON,
    DEFAULT_VIEWPORT_LINES,
    DrawHelperLinesFeedbackAction,
    RemoveHelperLinesFeedbackAction,
    ViewportLineType
} from './bpmn-helper-line-feedback';
import { IHelperLineManager } from './bpmn-helper-line-manager';
import { Direction, HelperLineType } from './model';

export interface IHelperLineOptions {
    /**
     * A list of helper line types that should be rendered when elements are aligned.
     * Defaults to all possible alignments.
     */
    elementLines?: HelperLineType[];
    /**
     * A list of helper line types that should be rendered when an element is aligned with the viewport.
     * Defaults to middle and center alignment.
     */
    viewportLines?: ViewportLineType[];
    /**
     * The minimum difference between two coordinates
     * Defaults to 1.
     */
    alignmentEpsilon?: number;
    /**
     * A filter that is applied to determine on which elements the alignment calculation is performed.
     * By default all top-level bounds-aware, non-routable elements that are visible on the canvas are considered.
     */
    alignmentElementFilter?: AlignmentElementFilter;
    /**
     * The minimum move delta that is necessary for an element to break through a helper line.
     * Defaults to { x: 1, y: 1 } whereas the x represents the horizontal distance and y represents the vertical distance.
     */
    minimumMoveDelta?: Point;

    /**
     * Produces debug output.
     * Defaults to false.
     */
    debug?: boolean;
}

export const DEFAULT_MOVE_DELTA = { x: 1, y: 1 };

export const DEFAULT_HELPER_LINE_OPTIONS: Required<IHelperLineOptions> = {
    elementLines: DEFAULT_ELEMENT_LINES,
    viewportLines: DEFAULT_VIEWPORT_LINES,
    alignmentEpsilon: DEFAULT_EPSILON,
    alignmentElementFilter: DEFAULT_ALIGNABLE_ELEMENT_FILTER,
    minimumMoveDelta: DEFAULT_MOVE_DELTA,
    debug: DEFAULT_DEBUG
};

@injectable()
export class HelperLineManager implements IActionHandler, ISelectionListener, IHelperLineManager {
    @inject(TYPES.IFeedbackActionDispatcher) protected feedbackDispatcher: IFeedbackActionDispatcher;
    @inject(SelectionService) protected selectionService: SelectionService;
    @optional() @inject(TYPES.IHelperLineOptions) protected userOptions?: IHelperLineOptions;

    protected options: Required<IHelperLineOptions>;
    protected feedback: DisposableCollection = new DisposableCollection();

    @postConstruct()
    protected init(): void {
        this.options = { ...DEFAULT_HELPER_LINE_OPTIONS, ...this.userOptions };
        this.selectionService.onSelectionChanged(change =>
            this.selectionChanged(change.root, change.selectedElements, change.deselectedElements)
        );
    }

    handle(action: Action): void {
        if (MoveInitializedEventAction.is(action)) {
            this.handleMoveInitializedAction(action);
        } else if (MoveAction.is(action)) {
            this.handleMoveAction(action);
        } else if (MoveFinishedEventAction.is(action)) {
            this.handleMoveFinishedAction(action);
        } else if (SetBoundsAction.is(action) || SetBoundsFeedbackAction.is(action)) {
            this.handleSetBoundsAction(action);
        }
    }

    protected handleMoveInitializedAction(_action: MoveInitializedEventAction): void {
        this.feedback.dispose();
        const feedback = this.createHelperLineFeedback(this.selectionService.getSelectedElementIDs());
        this.feedback.push(this.feedbackDispatcher.registerFeedback(this, [feedback], [RemoveHelperLinesFeedbackAction.create()]));
    }

    protected handleMoveFinishedAction(_action: MoveFinishedEventAction): void {
        this.feedback.dispose();
    }

    protected handleMoveAction(action: MoveAction): void {
        if (!action.finished) {
            const elementIds = action.moves.map(move => move.elementId);
            const feedback = this.createHelperLineFeedback(elementIds);
            this.feedback.push(this.feedbackDispatcher.registerFeedback(this, [feedback], [RemoveHelperLinesFeedbackAction.create()]));
        } else {
            this.feedback.dispose();
        }
    }

    protected createHelperLineFeedback(elementIds: string[]): DrawHelperLinesFeedbackAction {
        return DrawHelperLinesFeedbackAction.create({ elementIds, ...this.options });
    }

    protected handleSetBoundsAction(action: SetBoundsAction | SetBoundsFeedbackAction): void {
        const elementIds = action.bounds.map(bound => bound.elementId);
        const feedback = this.createHelperLineFeedback(elementIds);
        this.feedback.push(this.feedbackDispatcher.registerFeedback(this, [feedback], [RemoveHelperLinesFeedbackAction.create()]));
    }

    selectionChanged(root: Readonly<GModelRoot>, selectedElements: string[], deselectedElements?: string[] | undefined): void {
        this.feedback.dispose();
    }

    getMinimumMoveDelta(element: GModelElement, isSnap: boolean, direction: Direction): number {
        if (!isSnap) {
            return 0;
        }
        return direction === Direction.Left || direction === Direction.Right
            ? this.options.minimumMoveDelta.x
            : this.options.minimumMoveDelta.y;
    }
}
