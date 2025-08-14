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
import {
    ConsoleLogger,
    ContainerConfiguration,
    DefaultTypes,
    DeleteElementContextMenuItemProvider,
    GCompartment,
    GCompartmentView,
    GGraph,
    GLabel,
    GLabelView,
    HelperLineType,
    IHelperLineOptions,
    LogLevel,
    RectangularNodeView,
    RoundedCornerNodeView,
    TYPES,
    configureDefaultModelElements,
    configureModelElement,
    editLabelFeature,
    helperLineModule,
    initializeDiagramContainer,
    moveFeature,
    overrideModelElement,
    selectFeature
} from '@eclipse-glsp/client';
import {
    BPMNEdge,
    DataObjectNode,
    DataStoreNode,
    EventNode,
    GatewayNode,
    Icon,
    LabelNode,
    LaneDivider,
    LaneNode,
    MessageNode,
    MultiLineTextNode,
    PoolNode, TaskNode,
    TextAnnotationNode,
    isBPMNNode,
    isBoundaryEvent
} from '@open-bpmn/open-bpmn-model';
import {
    BPMNPropertiesMouseListener,
    BPMNPropertyModule
} from '@open-bpmn/open-bpmn-properties';
import 'balloon-css/balloon.min.css';
import { Container, ContainerModule } from 'inversify';
import 'sprotty/css/edit-label.css';

import '../css/diagram.css';
import {
    BPMNGridView,
    DataObjectNodeView,
    DataStoreNodeView,
    EventNodeView,
    GatewayNodeView,
    IconView,
    LaneDividerView,
    LaneHeaderView,
    MessageNodeView,
    MultiLineTextNodeView,
    PoolHeaderView,
    SubTaskNodeView,
    TaskNodeView,
    TextAnnotationNodeView
} from './bpmn-element-views';
import { BPMNRouterModule } from './bpmn-router-module';
import { BPMNEdgeView } from './bpmn-routing-views';
import {
    BPMNMultiNodeSelectionListener,
    BPMNSelectionHelper
} from './bpmn-select-listeners';
const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);

    // beaks launch
    bind<IHelperLineOptions>(TYPES.IHelperLineOptions).toConstantValue({
        elementLines: [HelperLineType.Center, HelperLineType.Middle], // only show center and middle lines
        viewportLines: [], // do not show alignment lines for viewport
        alignmentElementFilter: element =>
            isBPMNNode(element) && !isBoundaryEvent(element),
        minimumMoveDelta: { x: 10, y: 10 },
        alignmentEpsilon: 0.5
    });
    // bind new SelectionListener for BPMNLabels and BoundaryEvents
    bind(TYPES.ISelectionListener).to(BPMNSelectionHelper);
    bind(TYPES.ISelectionListener).to(BPMNMultiNodeSelectionListener);
    bind(TYPES.MouseListener).to(BPMNPropertiesMouseListener);
    bind(TYPES.IContextMenuItemProvider).to(DeleteElementContextMenuItemProvider);

    // Configure BMW View Elements
    configureDefaultModelElements(context);
    overrideModelElement(context, DefaultTypes.GRAPH, GGraph, BPMNGridView);
    // Tasks
    configureModelElement(context, 'task', TaskNode, TaskNodeView);
    configureModelElement(context, 'manualTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'userTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'scriptTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'businessRuleTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'serviceTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'sendTask', TaskNode, TaskNodeView);
    configureModelElement(context, 'receiveTask', TaskNode, TaskNodeView);
    // Events
    configureModelElement(context, 'startEvent', EventNode, EventNodeView);
    configureModelElement(context, 'endEvent', EventNode, EventNodeView);
    configureModelElement(context, 'intermediateCatchEvent', EventNode, EventNodeView);
    configureModelElement(context, 'intermediateThrowEvent', EventNode, EventNodeView);
    configureModelElement(context, 'boundaryEvent', EventNode, EventNodeView);
    // Gateways
    configureModelElement(context, 'exclusiveGateway', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'inclusiveGateway', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'parallelGateway', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'eventBasedGateway', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'complexGateway', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'label:heading', GLabel, GLabelView, { enable: [editLabelFeature] });
    configureModelElement(context, 'comp:comp', GCompartment, GCompartmentView);
    // Lanes
    configureModelElement(context, 'icon', Icon, IconView); // no longer used
    configureModelElement(context, 'pool_header', GCompartment, PoolHeaderView);
    configureModelElement(context, 'lane_header', GCompartment, LaneHeaderView);
    configureModelElement(context, 'pool', PoolNode, RoundedCornerNodeView, { disable: [moveFeature] });
    configureModelElement(context, 'lane', LaneNode, RoundedCornerNodeView, {
        disable: [moveFeature, selectFeature]
    });
    // Data objects
    configureModelElement(context, 'dataObject', DataObjectNode, DataObjectNodeView);
    configureModelElement(context, 'dataStore', DataStoreNode, DataStoreNodeView);
    configureModelElement(context, 'dataStoreReference', DataStoreNode, DataStoreNodeView);
    configureModelElement(context, 'message', MessageNode, MessageNodeView);
    configureModelElement(context, 'textAnnotation', TextAnnotationNode, TextAnnotationNodeView);
    configureModelElement(context, 'BPMNLabel', LabelNode, RectangularNodeView);
    configureModelElement(context, 'bpmn-text-node', MultiLineTextNode, MultiLineTextNodeView);
    configureModelElement(context, 'lane-divider', LaneDivider, LaneDividerView);
    configureModelElement(context, 'container', GCompartment, GCompartmentView);
    // Sequence flows
    configureModelElement(context, 'sequenceFlow', BPMNEdge, BPMNEdgeView);
    configureModelElement(context, 'messageFlow', BPMNEdge, BPMNEdgeView);
    configureModelElement(context, 'association', BPMNEdge, BPMNEdgeView);
    // Currently unsupported Task Types
    configureModelElement(context, 'subProcess', TaskNode, SubTaskNodeView);
    configureModelElement(context, 'adHocSubProcess', TaskNode, TaskNodeView);
    configureModelElement(context, 'transaction', TaskNode, TaskNodeView);
    configureModelElement(context, 'callActivity', TaskNode, TaskNodeView);

});

export function createBPMNDiagramContainer(...containerConfiguration: ContainerConfiguration): Container {
    return initializeBPMNDiagramContainer(new Container(), ...containerConfiguration);
}

export function initializeBPMNDiagramContainer(container: Container,
    ...containerConfiguration: ContainerConfiguration): Container {
    return initializeDiagramContainer(
        container,
        bpmnDiagramModule,
        helperLineModule,
        BPMNPropertyModule,
        BPMNRouterModule,
        ...containerConfiguration);
}
