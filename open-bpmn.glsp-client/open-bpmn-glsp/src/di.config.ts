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
    configureDefaultModelElements,
    configureModelElement,
    ConsoleLogger,
    createClientContainer,
    DeleteElementContextMenuItemProvider,
    editLabelFeature, moveFeature, selectFeature,
    LogLevel,
    overrideViewerOptions,
    RevealNamedElementActionProvider,
    RoundedCornerNodeView,RectangularNodeView,
    SCompartment,
    SCompartmentView,
    SLabel,CircularNodeView,DiamondNodeView,
    SLabelView,configureView,
    configureCommand,
    TYPES,ForeignObjectView, boundsFeature
} from '@eclipse-glsp/client';
// import { DefaultTypes } from '@eclipse-glsp/protocol';
import 'balloon-css/balloon.min.css';
import { Container, ContainerModule } from 'inversify';
import 'sprotty/css/edit-label.css';
import '../css/diagram.css';
import {
	LabelNode,
	GatewayNode,
	PoolNode,
	LaneNode,
	DataObjectNode,
	MessageNode,
	TextAnnotationNode,
	Icon,
	TaskNode,
	EventNode,
	BPMNEdge,
	MultiLineTextNode
} from '@open-bpmn/open-bpmn-model';
import { IconView,ContainerHeaderView,
         BPMNLabelNodeSelectionListener,
         DataObjectNodeView,
         MessageNodeView,
         TextAnnotationNodeView
} from './bpmn-element-views';
import { BPMNEdgeView } from './bpmn-routing-views';
import { HelperLineListener,
         DrawHelperLinesCommand,
         RemoveHelperLinesCommand,
         HelperLineView,
         BPMNElementSnapper
       } from './bpmn-helperlines';

import {bpmnPropertyModule} from '@open-bpmn/open-bpmn-properties';

const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };

    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);
    bind(TYPES.ISnapper).to(BPMNElementSnapper);

    bind(TYPES.ICommandPaletteActionProvider).to(RevealNamedElementActionProvider);
    bind(TYPES.IContextMenuItemProvider).to(DeleteElementContextMenuItemProvider);

	// neuer SelectionListener for Event BPMNLabels
	bind(TYPES.SelectionListener).to(BPMNLabelNodeSelectionListener);

	// bpmn helper lines
    bind(TYPES.MouseListener).to(HelperLineListener);
    configureCommand({ bind, isBound }, DrawHelperLinesCommand);
    configureCommand({ bind, isBound }, RemoveHelperLinesCommand);
    configureView({ bind, isBound }, 'helpline', HelperLineView);
    configureDefaultModelElements(context);

 
    configureModelElement(context, 'task', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'manualTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'userTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'scriptTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'businessRuleTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'serviceTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'sendTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'receiveTask', TaskNode, RoundedCornerNodeView);

    configureModelElement(context, 'startEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'endEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'intermediateCatchEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'intermediateThrowEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'boundaryEvent', EventNode, CircularNodeView);

    configureModelElement(context, 'exclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'inclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'parallelGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'eventBasedGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'complexGateway', GatewayNode, DiamondNodeView);

    configureModelElement(context, 'label:heading', SLabel, SLabelView, { enable: [editLabelFeature] });

    configureModelElement(context, 'comp:comp', SCompartment, SCompartmentView);
    // configureModelElement(context, 'comp:header', SCompartment, SCompartmentView);
    configureModelElement(context, 'icon', Icon, IconView);

    configureModelElement(context, 'comp:header', SCompartment, ContainerHeaderView);

    configureModelElement(context, 'pool', PoolNode, RoundedCornerNodeView, { disable: [moveFeature] } );
    configureModelElement(context, 'lane', LaneNode, RoundedCornerNodeView);
    configureModelElement(context, 'dataObject', DataObjectNode, DataObjectNodeView);
    configureModelElement(context, 'message', MessageNode, MessageNodeView);
    configureModelElement(context, 'textAnnotation', TextAnnotationNode, TextAnnotationNodeView);

    configureModelElement(context, 'BPMNLabel', LabelNode, RectangularNodeView);

    // textNode of BPMNLable, TextAnnotation...
    configureModelElement(context, 'bpmn-text-node', MultiLineTextNode, ForeignObjectView, {
         disable: [moveFeature, selectFeature,boundsFeature],
         enable: [editLabelFeature]}
    );

    configureModelElement(context, 'container', SCompartment, SCompartmentView);

    // Sequence flows
    configureModelElement(context, 'sequenceFlow', BPMNEdge, BPMNEdgeView);
    configureModelElement(context, 'messageFlow', BPMNEdge, BPMNEdgeView);
    configureModelElement(context, 'association', BPMNEdge, BPMNEdgeView);

});

export default function createBPMNDiagramContainer(widgetId: string): Container {
    // Create the createClientContainer with the diagramModule and the BPMN bpmnPropertyModule...
    const container = createClientContainer(bpmnDiagramModule, bpmnPropertyModule);
    overrideViewerOptions(container, {
        baseDiv: widgetId,
        hiddenDiv: widgetId + '_hidden'
    });
    return container;
}
