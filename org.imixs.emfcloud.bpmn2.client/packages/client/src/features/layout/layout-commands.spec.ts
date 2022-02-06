/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { Action, Bounds, ChangeBoundsOperation, ElementAndBounds } from '@eclipse-glsp/protocol';
import { expect } from 'chai';
import { Container } from 'inversify';
import 'mocha';
import 'reflect-metadata';
import {
    AnimationFrameSyncer,
    CommandExecutionContext,
    ConsoleLogger,
    defaultModule,
    ElementMove,
    FeatureSet,
    IActionDispatcher,
    MoveAction,
    MoveCommand,
    RequestAction,
    ResponseAction,
    SChildElement,
    SetBoundsAction,
    SetBoundsCommand,
    SGraphFactory,
    SModelRoot,
    TYPES
} from 'sprotty';
import { GLSP_TYPES } from '../../base/types';
import { resizeFeature } from '../change-bounds/model';
import { SelectionService } from '../select/selection-service';
import { FeedbackActionDispatcher } from '../tool-feedback/feedback-action-dispatcher';
import {
    AlignElementsAction,
    AlignElementsCommand,
    Alignment,
    Reduce,
    ResizeDimension,
    ResizeElementsAction,
    ResizeElementsCommand
} from './layout-commands';

class MockActionDispatcher implements IActionDispatcher {
    constructor(public dispatchedActions: Action[] = []) {}
    dispatch(action: Action): Promise<void> {
        this.dispatchedActions.push(action);
        return Promise.resolve();
    }
    dispatchAll(actions: Action[]): Promise<void> {
        actions.forEach(action => this.dispatchedActions.push(action));
        return Promise.resolve();
    }
    request<Res extends ResponseAction>(action: RequestAction<Res>): Promise<Res> {
        throw new Error('Method not implemented.');
    }
}

const container = new Container();
container.load(defaultModule);
container.bind(GLSP_TYPES.IFeedbackActionDispatcher).to(FeedbackActionDispatcher).inSingletonScope();
container.bind(SelectionService).toSelf().inSingletonScope();
container.bind(GLSP_TYPES.SelectionService).toService(SelectionService);
container.rebind(TYPES.IModelFactory).to(SGraphFactory).inSingletonScope();
const graphFactory = container.get<SGraphFactory>(TYPES.IModelFactory);
const selectionService = container.get<SelectionService>(GLSP_TYPES.SelectionService);

const actionDispatcher = new MockActionDispatcher();

const node1 = {
    id: 'node1',
    type: 'node:circle',
    selected: true
};
const node2 = {
    id: 'node2',
    type: 'node:circle',
    selected: true
};
const node3 = {
    id: 'node3',
    type: 'node:circle',
    selected: true
};
const model = createModel();

function createModel(): SModelRoot {
    const root = graphFactory.createRoot({
        id: 'model1',
        type: 'graph',
        children: [node1, node2, node3]
    });
    root.children.forEach(child => applyFeature(child, resizeFeature));
    return root;
}

function applyFeature(element: SChildElement, feature: symbol): void {
    (element.features as FeatureSet & Set<symbol>).add(feature);
}

const context: CommandExecutionContext = {
    root: model,
    modelFactory: graphFactory,
    duration: 0,
    modelChanged: undefined!,
    logger: new ConsoleLogger(),
    syncer: new AnimationFrameSyncer()
};

const defaultSize = { height: 10, width: 10 };

describe('AlignElementsCommand', () => {
    it('should align all elements left', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Left);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 111, y: 111, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 111, y: 222, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 111, y: 333, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });

    it('should align all elements right', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Right);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 333, y: 111, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 333, y: 222, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 333, y: 333, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });

    it('should align all elements center', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Center);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 222, y: 111, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 222, y: 222, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 222, y: 333, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });

    it('should align all elements top', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Top);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 111, y: 111, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 222, y: 111, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 333, y: 111, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });

    it('should align all elements bottom', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Bottom);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 111, y: 333, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 222, y: 333, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 333, y: 333, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });

    it('should align all elements middle', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 111, y: 111 }, newSize: defaultSize },
            { elementId: 'node2', newPosition: { x: 222, y: 222 }, newSize: defaultSize },
            { elementId: 'node3', newPosition: { x: 333, y: 333 }, newSize: defaultSize }
        ]);
        const action = new AlignElementsAction(['node1', 'node2', 'node3'], Alignment.Middle);
        const command = new AlignElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        assertAllBounds(
            new Map([
                ['node1', { x: 111, y: 222, width: defaultSize.width, height: defaultSize.height }],
                ['node2', { x: 222, y: 222, width: defaultSize.width, height: defaultSize.height }],
                ['node3', { x: 333, y: 222, width: defaultSize.width, height: defaultSize.height }]
            ])
        );
    });
});

describe('ResizeElementsCommand', () => {
    it('should make same width as last', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 100, y: 100 }, newSize: { height: 10, width: 10 } },
            { elementId: 'node2', newPosition: { x: 100, y: 200 }, newSize: { height: 20, width: 20 } },
            { elementId: 'node3', newPosition: { x: 100, y: 300 }, newSize: { height: 30, width: 30 } }
        ]);
        const action = new ResizeElementsAction(['node1', 'node2', 'node3'], ResizeDimension.Width, Reduce.last);
        const command = new ResizeElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        // resize is keeping the center, so the X moves by diff / 2
        assertAllBoundsInChangeBounds(
            new Map([
                ['node1', { x: 90, y: 100, height: 10, width: 30 }],
                ['node2', { x: 95, y: 200, height: 20, width: 30 }],
                ['node3', { x: 100, y: 300, height: 30, width: 30 }]
            ])
        );
    });

    it('should make same height as last', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 100, y: 100 }, newSize: { height: 10, width: 10 } },
            { elementId: 'node2', newPosition: { x: 100, y: 200 }, newSize: { height: 20, width: 20 } },
            { elementId: 'node3', newPosition: { x: 100, y: 300 }, newSize: { height: 30, width: 30 } }
        ]);
        const action = new ResizeElementsAction(['node1', 'node2', 'node3'], ResizeDimension.Height, Reduce.last);
        const command = new ResizeElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        // resize is keeping the center, so the Y moves by diff / 2
        assertAllBoundsInChangeBounds(
            new Map([
                ['node1', { x: 100, y: 90, height: 30, width: 10 }],
                ['node2', { x: 100, y: 195, height: 30, width: 20 }],
                ['node3', { x: 100, y: 300, height: 30, width: 30 }]
            ])
        );
    });

    it('should make same width and height as last', () => {
        actionDispatcher.dispatchedActions = [];
        const newModel = initModel([
            { elementId: 'node1', newPosition: { x: 100, y: 100 }, newSize: { height: 10, width: 10 } },
            { elementId: 'node2', newPosition: { x: 100, y: 200 }, newSize: { height: 20, width: 20 } },
            { elementId: 'node3', newPosition: { x: 100, y: 300 }, newSize: { height: 30, width: 30 } }
        ]);
        const action = new ResizeElementsAction(['node1', 'node2', 'node3'], ResizeDimension.Width_And_Height, Reduce.last);
        const command = new ResizeElementsCommand(action, actionDispatcher, selectionService);
        command.execute(newContext(newModel));

        // resize is keeping the center, so the Y moves by diff / 2
        assertAllBoundsInChangeBounds(
            new Map([
                ['node1', { x: 90, y: 90, height: 30, width: 30 }],
                ['node2', { x: 95, y: 195, height: 30, width: 30 }],
                ['node3', { x: 100, y: 300, height: 30, width: 30 }]
            ])
        );
    });
});

function initModel(elementAndBounds: ElementAndBounds[]): SModelRoot {
    const mySetBoundsAction = new SetBoundsAction(elementAndBounds);
    const setBoundsCommand = new SetBoundsCommand(mySetBoundsAction);
    return setBoundsCommand.execute(context) as SModelRoot;
}

function newContext(root: SModelRoot): CommandExecutionContext {
    return {
        root: root,
        modelFactory: graphFactory,
        duration: 0,
        modelChanged: undefined!,
        logger: new ConsoleLogger(),
        syncer: new AnimationFrameSyncer()
    };
}

function assertAllBounds(allBounds: Map<string, Bounds>): void {
    allBounds.forEach((bounds, nodeId) => assertBounds(nodeId, bounds));
}

function assertAllBoundsInChangeBounds(allBounds: Map<string, Bounds>): void {
    allBounds.forEach((bounds, nodeId) => assertBoundsInChangeBoundsActions(nodeId, bounds));
}

function assertBounds(nodeId: string, bounds: Bounds): void {
    assertBoundsInMoves(nodeId, bounds);
    assertBoundsInChangeBoundsActions(nodeId, bounds);
}

function assertBoundsInMoves(nodeId: string, bounds: Bounds): void {
    const moves = dispatchedElementMoves();
    const move = getMoveById(nodeId, moves);
    expect(move.toPosition.x).to.be.equal(bounds.x);
    expect(move.toPosition.y).to.be.equal(bounds.y);
}

function assertBoundsInChangeBoundsActions(nodeId: string, bounds: Bounds): void {
    const allChangeBounds = dispatchedChangeBounds();
    const changeBounds = getElementAndBoundsById(nodeId, allChangeBounds);
    expect(changeBounds.newPosition!.x).to.be.equal(bounds.x);
    expect(changeBounds.newPosition!.y).to.be.equal(bounds.y);
    expect(changeBounds.newSize!.height).to.be.equal(bounds.height);
    expect(changeBounds.newSize!.width).to.be.equal(bounds.width);
}

function getMoveById(id: string, moves: ElementMove[]): ElementMove {
    return moves.filter(m => m.elementId === id)[0];
}

function getElementAndBoundsById(id: string, elementAndBounds: ElementAndBounds[]): ElementAndBounds {
    return elementAndBounds.filter(m => m.elementId === id)[0];
}

function dispatchedElementMoves(): ElementMove[] {
    return actionDispatcher.dispatchedActions
        .filter(isMoveAction)
        .map(a => a.moves)
        .reduce((acc, val) => acc.concat(val), []);
}

function dispatchedChangeBounds(): ElementAndBounds[] {
    return actionDispatcher.dispatchedActions
        .filter(isChangeBounds)
        .map(a => a.newBounds)
        .reduce((acc, val) => acc.concat(val), []);
}

function isMoveAction(action: Action): action is MoveAction {
    return action.kind === MoveCommand.KIND;
}

function isChangeBounds(action: Action): action is ChangeBoundsOperation {
    return action.kind === ChangeBoundsOperation.KIND;
}
