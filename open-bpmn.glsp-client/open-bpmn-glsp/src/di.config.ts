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
    configureCommand,
    ManhattanEdgeRouter,
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
	LaneNode,
	Icon,
	TaskNode,
	EventNode,
	SequenceFlow,
	BPMNElementAnchor
} from '@open-bpmn/open-bpmn-model';
import { IconView,ContainerHeaderView,BPMNLabelNodeSelectionListener } from './bpmn-element-views';
import { BPMNSequenceFlowView } from './bpmn-routing-views';
import { BPMNManhattanEdgeRouter } from './bpmn-edge-router';
// import { BPMNEllipticAnchor,BPMNRectangularAnchor,BPMNDiamondAnchor } from './bpmn-anchors';
import { HelperLineListener,DrawHelperLinesCommand,RemoveHelperLinesCommand,HelperLineView,BPMNElementSnapper } from './bpmn-helperlines';

import {bpmnPropertyModule} from '@open-bpmn/open-bpmn-properties';

const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };

    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);
    // bind(TYPES.ISnapper).to(GridSnapper);
    // bind(TYPES.ISnapper).to(BPMNGridSnapper);
    bind(TYPES.ISnapper).to(BPMNElementSnapper);

    // BPMN Router
    bind(BPMNManhattanEdgeRouter).toSelf().inSingletonScope();
    rebind(ManhattanEdgeRouter).toService(BPMNManhattanEdgeRouter);

   // bind(TYPES.IAnchorComputer).to(BPMNEllipticAnchor).inSingletonScope();
   // bind(TYPES.IAnchorComputer).to(BPMNRectangularAnchor).inSingletonScope();
   // bind(TYPES.IAnchorComputer).to(BPMNDiamondAnchor).inSingletonScope();

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

    configureModelElement(context, 'BPMNLabel', LabelNode, RoundedCornerNodeView);

    configureModelElement(context, 'task', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'manualTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'userTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'sendTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'serviceTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'scriptTask', TaskNode, RoundedCornerNodeView);

    configureModelElement(context, 'startEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'endEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'intermediateCatchEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'intermediateThrowEvent', EventNode, CircularNodeView);

    configureModelElement(context, 'exclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'inclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'parallelGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'complexGateway', GatewayNode, DiamondNodeView);

    configureModelElement(context, 'label:heading', SLabel, SLabelView, { enable: [editLabelFeature] });

    configureModelElement(context, 'comp:comp', SCompartment, SCompartmentView);
    configureModelElement(context, 'comp:header', SCompartment, SCompartmentView);
    configureModelElement(context, 'icon', Icon, IconView);

    configureModelElement(context, 'comp:header', SCompartment, ContainerHeaderView);

    configureModelElement(context, 'pool', PoolNode, RoundedCornerNodeView);
    configureModelElement(context, 'lane', LaneNode, RoundedCornerNodeView);
    // configureModelElement(context, 'container', SCompartment, StructureCompartmentView);
    configureModelElement(context, 'container', SCompartment, SCompartmentView);

    // configureModelElement(context, 'edge:sequenceflow', SequenceFlow, BPMNSequenceFlowView);
    configureModelElement(context, 'sequenceFlow', SequenceFlow, BPMNSequenceFlowView);

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
