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
    editLabelFeature,
    LogLevel,
    overrideViewerOptions,
    RevealNamedElementActionProvider,
    RoundedCornerNodeView,
    SCompartment,
    SCompartmentView,
    SLabel,CircularNodeView,DiamondNodeView,
    SLabelView,configureView,
    StructureCompartmentView,configureCommand,
    TYPES
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
	Icon,
	TaskNode,
	EventNode,
	SequenceFlow,
	BPMNElementAnchor
} from '@open-bpmn/open-bpmn-model';
import { IconView,BPMNLabelNodeSelectionListener } from './bpmn-element-views';
import { BPMNSequenceFlowView } from './bpmn-routing-views';
import { HelperLineListener,DrawHelperLinesCommand,RemoveHelperLinesCommand,HelperLineView,BPMNElementSnapper } from './bpmn-helperlines';

import {bpmnPropertyModule} from '@open-bpmn/open-bpmn-properties';

const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };

    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);
    // bind(TYPES.ISnapper).to(GridSnapper);
    // bind(TYPES.ISnapper).to(BPMNGridSnapper);
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

    // bind the BPMN AnchorComputer
    bind(TYPES.IAnchorComputer).to(BPMNElementAnchor).inSingletonScope();
   // bind(TYPES.IAnchorComputer).to(BPMNPolylineElementAnchor).inSingletonScope();

    // bind(TYPES.IAnchorComputer).to(BPMNSequenceFlowAnchor).inSingletonScope();
    // bind(TYPES.IAnchorComputer).to(BPMNEventElementAnchor).inSingletonScope();

    configureDefaultModelElements(context);

    //configureModelElement(context, 'label', LabelNode, RoundedCornerNodeView);
    //configureModelElement(context, 'BPMNLabel', SLabel, SLabelView, { enable: [editLabelFeature,selectFeature,moveFeature] });
    configureModelElement(context, 'bpmn:label', LabelNode, RoundedCornerNodeView);

    configureModelElement(context, 'bpmn:task', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'bpmn:manualTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'bpmn:userTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'bpmn:sendTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'bpmn:serviceTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'bpmn:scriptTask', TaskNode, RoundedCornerNodeView);

    configureModelElement(context, 'bpmn:startEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'bpmn:endEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'bpmn:intermediateCatchEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'bpmn:intermediateThrowEvent', EventNode, CircularNodeView);

    configureModelElement(context, 'bpmn:exclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'bpmn:inclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'bpmn:parallelGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'bpmn:complexGateway', GatewayNode, DiamondNodeView);

    configureModelElement(context, 'label:heading', SLabel, SLabelView, { enable: [editLabelFeature] });

    configureModelElement(context, 'comp:comp', SCompartment, SCompartmentView);
    configureModelElement(context, 'comp:header', SCompartment, SCompartmentView);
    configureModelElement(context, 'icon', Icon, IconView);

    configureModelElement(context, 'bpmn:pool', PoolNode, RoundedCornerNodeView);
    configureModelElement(context, 'container', SCompartment, StructureCompartmentView);

    // configureModelElement(context, 'edge:sequenceflow', SequenceFlow, BPMNSequenceFlowView);
    configureModelElement(context, 'bpmn:sequenceFlow', SequenceFlow, BPMNSequenceFlowView);

});

// createBPMNDiagramContainer
// createContainer
export default function createBPMNDiagramContainer(widgetId: string): Container {
    // Create the createClientContainer with the diagramModule and the BPMN bpmnPropertyModule...
    const container = createClientContainer(bpmnDiagramModule, bpmnPropertyModule);
    overrideViewerOptions(container, {
        baseDiv: widgetId,
        hiddenDiv: widgetId + '_hidden'
    });
    return container;
}
