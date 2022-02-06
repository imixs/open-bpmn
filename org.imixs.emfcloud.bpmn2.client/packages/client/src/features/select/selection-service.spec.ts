/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
/* eslint-disable import/no-deprecated,no-unused-expressions */
import { Action } from '@eclipse-glsp/protocol';
import { AssertionError, expect } from 'chai';
import { Container, injectable } from 'inversify';
import { defaultModule, SGraphFactory, SModelElementSchema, SModelRoot, TYPES } from 'sprotty';
import { GLSP_TYPES } from '../../base/types';
import { IFeedbackActionDispatcher, IFeedbackEmitter } from '../tool-feedback/feedback-action-dispatcher';
import { SelectFeedbackAction } from './select-feedback-action';
import { SelectionListener, SelectionService } from './selection-service';

@injectable()
class MockFeedbackActionDispatcher implements IFeedbackActionDispatcher {
    protected feedbackEmitters: Map<IFeedbackEmitter, Action[]> = new Map();

    registerFeedback(feedbackEmitter: IFeedbackEmitter, actions: Action[]): void {
        this.feedbackEmitters.set(feedbackEmitter, actions);
    }

    deregisterFeedback(feedbackEmitter: IFeedbackEmitter, actions: Action[]): void {
        this.feedbackEmitters.delete(feedbackEmitter);
    }

    getRegisteredFeedback(): Action[] {
        const result: Action[] = [];
        this.feedbackEmitters.forEach((value, key) => result.push(...value));
        return result;
    }

    getSingleFeedbackAction(): SelectFeedbackAction | undefined {
        const actions = this.getRegisteredFeedback();
        return actions.length === 1 ? (actions[0] as SelectFeedbackAction) : undefined;
    }
}

class MockSelectionListener implements SelectionListener {
    private called = 0;
    private root: SModelRoot;
    private selectedElements: string[] = [];

    selectionChanged(newRoot: Readonly<SModelRoot>, selectedElements: string[]): void {
        this.called++;
        this.root = newRoot;
        this.selectedElements = selectedElements;
    }

    getRoot(): SModelRoot {
        return this.root;
    }

    getSelectedElements(): string[] {
        return this.selectedElements;
    }

    getCalled(): number {
        return this.called;
    }
}

function createContainer(): Container {
    const container = new Container();
    container.load(defaultModule);
    container.rebind(TYPES.IModelFactory).to(SGraphFactory).inSingletonScope();
    container.bind(GLSP_TYPES.IFeedbackActionDispatcher).to(MockFeedbackActionDispatcher).inSingletonScope();
    container.bind(SelectionService).toSelf().inSingletonScope();
    container.bind(GLSP_TYPES.SelectionService).toService(SelectionService);
    return container;
}

describe('SelectionService', () => {
    let graphFactory: SGraphFactory;
    let root: SModelRoot;
    let selectionService: SelectionService;
    let feedbackDispatcher: MockFeedbackActionDispatcher;

    beforeEach(() => {
        const container = createContainer();
        graphFactory = container.get<SGraphFactory>(TYPES.IModelFactory);
        root = createRoot('node1', 'node2', 'node3', 'node4', 'node5');
        selectionService = container.get<SelectionService>(GLSP_TYPES.SelectionService);
        feedbackDispatcher = container.get<MockFeedbackActionDispatcher>(GLSP_TYPES.IFeedbackActionDispatcher);
    });

    describe('Initial State', () => {
        it('On creation nothing should be selected and no feedback should be dispatched.', () => {
            assertSelectionAndFeedback([], []);
        });
    });
    describe('Single Selection', () => {
        it('Selecting a single element should be tracked correctly and trigger feedback.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1'], []);
            assertSelectionAndFeedback(['node1'], []);
        });
        it('Selecting the same element twice in one operation should not make a difference.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node1'], []);
            assertSelectionAndFeedback(['node1'], []);
        });
        it('Selecting and then deselecting the same element should result in an empty selection.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1'], []);
            selectionService.updateSelection(root, [], ['node1']);
            assertSelectionAndFeedback([], ['node1']);
        });
        it('Selecting and deselecting the same element in the same operation should have no effect.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1'], ['node1']);
            assertSelectionAndFeedback([], []);
        });
        it('Selecting and deselecting not-existing nodes should have no effect.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['not-existing'], []);
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, [], ['not-existing']);
            assertSelectionAndFeedback([], []);
        });
    });
    describe('Multi Selection', () => {
        it('Selecting multiple elements should be tracked correctly and trigger feedback.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2'], []);
            assertSelectionAndFeedback(['node1', 'node2'], []);
        });
        it('Selecting multiple elements should have the selection order in the dispatched feedback.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node2', 'node1'], []);
            expect(() => assertSelectionAndFeedback(['node1', 'node2'], [])).to.throw(AssertionError, 'ordered members');
        });
        it('Selecting the same elements twice in one operation should not make a difference.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node1', 'node2', 'node2'], []);
            assertSelectionAndFeedback(['node1', 'node2'], []);
        });
        it('Selecting and then deselecting the same elements should result in an empty selection.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2'], []);
            selectionService.updateSelection(root, [], ['node1', 'node2']);
            assertSelectionAndFeedback([], ['node1', 'node2']);
        });
        it('Selecting and deselecting the same elements in one operation should have no effect.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2'], ['node1', 'node2']);
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2', 'node3'], ['node1', 'node2']);
            assertSelectionAndFeedback(['node3'], []);
        });
        it('Selecting three elements and deselecting one should result in two selected and one deselected element.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2', 'node3'], []);
            assertSelectionAndFeedback(['node1', 'node2', 'node3'], []);
            selectionService.updateSelection(root, [], ['node2']);
            assertSelectionAndFeedback(['node1', 'node3'], ['node2']);
        });
        it('A series of selection and deselection operations should be tracked correctly.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2', 'node3'], []);
            assertSelectionAndFeedback(['node1', 'node2', 'node3'], []);
            selectionService.updateSelection(root, ['node4'], ['node2']);
            assertSelectionAndFeedback(['node1', 'node3', 'node4'], ['node2']);
            selectionService.updateSelection(root, ['node3', 'node1'], ['node2', 'node4']);
            assertSelectionAndFeedback(['node1', 'node3'], ['node4']);
            selectionService.updateSelection(root, ['node3'], ['node3']);
            assertSelectionAndFeedback(['node1', 'node3'], ['node4']);
        });
    });
    describe('Changing Root', () => {
        it('Changing root deselects all selected elements if there are no matching elements.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1'], []);
            assertSelectionAndFeedback(['node1'], []);

            const newRoot = createRoot('newNode1', 'newNode2', 'newNode3');
            selectionService.modelRootChanged(newRoot);
            assertSelectionAndFeedback([], ['node1']);
        });
        it('Changing root keeps selected elements if there are matching elements.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2'], []);
            assertSelectionAndFeedback(['node1', 'node2'], []);

            const newRoot = createRoot('node1', 'newNode2', 'newNode3');
            selectionService.modelRootChanged(newRoot);
            assertSelectionAndFeedback(['node1'], ['node2']);
        });
        it('Changing root with new selection correctly selects matchting elements and deselects not matching elements.', () => {
            assertSelectionAndFeedback([], []);
            selectionService.updateSelection(root, ['node1', 'node2'], []);
            assertSelectionAndFeedback(['node1', 'node2'], []);

            const newRoot = createRoot('newNode1', 'newNode2', 'newNode3');
            selectionService.updateSelection(newRoot, ['newNode1'], []);
            assertSelectionAndFeedback(['newNode1'], ['node1', 'node2']);
        });
    });
    describe('Listeners', () => {
        it('A registered listener should be notified of a single selection change.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1', 'node1'], []);
            assertListener(listener, root, ['node1'], 1);
        });
        it('A registered listener should be notified of a multi-selection change.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1', 'node2', 'node3'], []);
            assertListener(listener, root, ['node1', 'node2', 'node3'], 1);
        });
        it('A registered listener should be notified of series of selection changes.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
            selectionService.updateSelection(root, ['node2'], ['node1']);
            assertListener(listener, root, ['node2'], 2);
            selectionService.updateSelection(root, ['node3', 'node4'], []);
            assertListener(listener, root, ['node2', 'node3', 'node4'], 3);
            selectionService.updateSelection(root, [], ['node4']);
            assertListener(listener, root, ['node2', 'node3'], 4);
        });
        it('A registered listener should receive the selected elements in the right order.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node2', 'node1'], []);
            expect(() => assertListener(listener, root, ['node1', 'node2'], 1)).to.throw(AssertionError, 'ordered members');
        });
        it('A registered listener should be notified of root changes.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, [], []);
            assertListener(listener, root, [], 1);

            const newRoot = createRoot('node1', 'newNode2', 'newNode3');
            selectionService.updateSelection(newRoot, [], []);
            assertListener(listener, newRoot, [], 2);
        });
        it('Registering an already registered listener should have no effect.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            selectionService.register(listener);
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1', 'node2', 'node3'], []);
            assertListener(listener, root, ['node1', 'node2', 'node3'], 1);
            selectionService.register(listener);
            selectionService.updateSelection(root, [], ['node2']);
            assertListener(listener, root, ['node1', 'node3'], 2);
        });
        it('Selecting the same elements consecutively should not trigger a listener update.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
        });
        it('Selecting a not-existing elements should not trigger a listener update.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
            selectionService.updateSelection(root, ['not-existing'], []);
            assertListener(listener, root, ['node1'], 1);
        });
        it('All registered listeners should be called on selection changes.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            const listener2: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            selectionService.register(listener2);
            assertListener(listener, undefined, [], 0);
            assertListener(listener2, undefined, [], 0);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
            assertListener(listener2, root, ['node1'], 1);
            selectionService.updateSelection(root, ['node2'], ['node1']);
            assertListener(listener, root, ['node2'], 2);
            assertListener(listener2, root, ['node2'], 2);
            selectionService.updateSelection(root, ['node3', 'node4'], []);
            assertListener(listener, root, ['node2', 'node3', 'node4'], 3);
            assertListener(listener2, root, ['node2', 'node3', 'node4'], 3);
            selectionService.updateSelection(root, [], ['node4']);
            assertListener(listener, root, ['node2', 'node3'], 4);
            assertListener(listener2, root, ['node2', 'node3'], 4);
        });
        it('A deregistered listener should not receive further updates.', () => {
            const listener: MockSelectionListener = new MockSelectionListener();
            selectionService.register(listener);
            assertListener(listener, undefined, [], 0);
            selectionService.updateSelection(root, ['node1'], []);
            assertListener(listener, root, ['node1'], 1);
            selectionService.deregister(listener);
            selectionService.updateSelection(root, ['node2'], ['node1']);
            assertListener(listener, root, ['node1'], 1);
            selectionService.updateSelection(root, ['node3', 'node4'], []);
            assertListener(listener, root, ['node1'], 1);
            selectionService.updateSelection(root, [], ['node4']);
            assertListener(listener, root, ['node1'], 1);

            const newRoot = createRoot('node1', 'newNode2', 'newNode3');
            selectionService.updateSelection(newRoot, [], []);
            assertListener(listener, root, ['node1'], 1);
        });
    });

    function createRoot(...nodes: string[]): SModelRoot {
        const children: SModelElementSchema[] = [];
        nodes.forEach(node => children.push({ id: node, type: 'node:circle' }));
        return graphFactory.createRoot({
            id: 'selection-service-spec',
            type: 'graph',
            children: children
        });
    }

    function assertSelectionAndFeedback(expectedSelection: string[], expectedDeselection: string[]): void {
        assertSelectionService(expectedSelection);
        assertDispatchedFeedback(expectedSelection, expectedDeselection);
    }

    function assertSelectionService(expectedSelection: string[]): void {
        expect(selectionService.isSingleSelection()).to.equal(expectedSelection.length === 1);
        expect(selectionService.isMultiSelection()).to.equal(expectedSelection.length > 1);
        expect(selectionService.hasSelectedElements()).to.equal(expectedSelection.length > 0);
        expect(selectionService.getSelectedElementIDs()).to.have.lengthOf(expectedSelection.length);
        if (expectedSelection.length > 0) {
            expect(selectionService.getSelectedElementIDs()).to.have.all.keys(...expectedSelection);
        }
    }

    function assertDispatchedFeedback(expectedSelection: string[], expectedDeselection: string[]): void {
        // a single feedback action reflects aggregated selection/deselection
        const hasFeedback = expectedSelection.length > 0 || expectedDeselection.length > 0;
        if (hasFeedback) {
            expect(feedbackDispatcher.getRegisteredFeedback()).to.have.lengthOf(1);
            expect(feedbackDispatcher.getSingleFeedbackAction()!.selectedElementsIDs).to.have.lengthOf(expectedSelection.length);
            expect(feedbackDispatcher.getSingleFeedbackAction()!.selectedElementsIDs).to.have.ordered.members(expectedSelection);
            expect(feedbackDispatcher.getSingleFeedbackAction()!.deselectedElementsIDs).to.have.lengthOf(expectedDeselection.length);
            expect(feedbackDispatcher.getSingleFeedbackAction()!.deselectedElementsIDs).to.have.ordered.members(expectedDeselection);
        } else {
            expect(feedbackDispatcher.getRegisteredFeedback()).to.have.lengthOf(0);
            expect(feedbackDispatcher.getSingleFeedbackAction()).to.be.undefined;
        }
    }

    function assertListener(
        listener: MockSelectionListener,
        expectedRoot: SModelRoot | undefined,
        expectedSelection: string[],
        expectedCalled: number
    ): void {
        expect(listener.getRoot()).to.equal(expectedRoot);
        expect(listener.getSelectedElements()).to.have.lengthOf(expectedSelection.length);
        expect(listener.getSelectedElements()).to.have.ordered.members(expectedSelection);
        expect(listener.getCalled()).to.equal(expectedCalled);
    }
});
