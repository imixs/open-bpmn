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
    GridSnapper,
    IFeedbackActionDispatcher,
    ISelectionListener,
    MoveFinishedEventAction, MoveInitializedEventAction,
    SelectionService,
    SetBoundsFeedbackAction
} from '@eclipse-glsp/client';
import {
    Action,
    DisposableCollection,
    GModelElement,
    GModelRoot,
    IActionHandler,
    ISnapper,
    MoveAction,
    SetBoundsAction,
    TYPES
} from '@eclipse-glsp/sprotty';
import { inject, injectable, optional, postConstruct } from 'inversify';
// import { IFeedbackActionDispatcher } from '../../base/feedback/feedback-action-dispatcher';
// import { ISelectionListener, SelectionService } from '../../base/selection-service';
// import { SetBoundsFeedbackAction } from '../bounds/set-bounds-feedback-command';
// import { GridSnapper } from '../change-bounds/snap';
// import { MoveFinishedEventAction, MoveInitializedEventAction } from '../tools/change-bounds/change-bounds-tool-feedback';
import { DrawHelperLinesFeedbackAction, RemoveHelperLinesFeedbackAction, ViewportLineType } from './bpmn-helper-line-feedback';
import { IHelperLineManager } from './bpmn-helper-line-manager';
import { Direction, HelperLineType } from './model';

@injectable()
export class HelperLineManager implements IActionHandler, ISelectionListener, IHelperLineManager {
    @inject(TYPES.IFeedbackActionDispatcher) protected feedbackDispatcher: IFeedbackActionDispatcher;
    @inject(SelectionService) protected selectionService: SelectionService;
    @optional() @inject(TYPES.ISnapper) protected snapper?: ISnapper;

    protected snapSize = { x: 10, y: 10 };
    protected feedback: DisposableCollection = new DisposableCollection();

    protected elementLines?: HelperLineType[];
    protected viewportLines?: ViewportLineType[];
    protected alignmentEpsilon?: number;

    @postConstruct()
    protected init(): void {
        this.selectionService.onSelectionChanged(change =>
            this.selectionChanged(change.root, change.selectedElements, change.deselectedElements)
        );
        if (this.snapper instanceof GridSnapper) {
            console.log('--------__> NOT helloe ich setzte irgendwas mit snap');
            //this.snapSize = { x: this.snapper.grid.x * 2, y: this.snapper.grid.y * 2 };
        }
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
        return DrawHelperLinesFeedbackAction.create({
            elementIds,
            elementLines: this.elementLines,
            viewportLines: this.viewportLines,
            alignmentEpsilon: this.alignmentEpsilon
        });
    }

    protected handleSetBoundsAction(action: SetBoundsAction | SetBoundsFeedbackAction): void {
        console.log('set bounds action');
        const elementIds = action.bounds.map(bound => bound.elementId);
        const feedback = this.createHelperLineFeedback(elementIds);
        this.feedback.push(this.feedbackDispatcher.registerFeedback(this, [feedback], [RemoveHelperLinesFeedbackAction.create()]));
    }

    selectionChanged(root: Readonly<GModelRoot>, selectedElements: string[], deselectedElements?: string[] | undefined): void {
        this.feedback.dispose();
    }

    getHelperLineSnapping(target: GModelElement, isSnap: boolean, direction: Direction): number {
        return direction === Direction.Left || direction === Direction.Right ? this.snapSize.x : this.snapSize.y;
    }
}
