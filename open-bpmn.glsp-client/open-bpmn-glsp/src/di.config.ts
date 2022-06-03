/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
import {
    configureDefaultModelElements,
    configureModelElement,
    ConsoleLogger,
    createClientContainer,
    DeleteElementContextMenuItemProvider,
    editLabelFeature,moveFeature,selectFeature,
    //GridSnapper,
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
    configureModelElement(context, 'task', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'manualTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'userTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'sendTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'serviceTask', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'scriptTask', TaskNode, RoundedCornerNodeView);

    configureModelElement(context, 'startEvent', EventNode, CircularNodeView);
    // configureModelElement(context, 'startEvent', EventNode, EventNodeView);
    configureModelElement(context, 'endEvent', EventNode, CircularNodeView);

    // configureModelElement(context, DefaultTypes.NODE_CIRCLE, CircularNode, CircularNodeView);
    // configureModelElement(context, DefaultTypes.NODE_DIAMOND, DiamondNode, DiamondNodeView);

    configureModelElement(context, 'intermediateCatchEvent', EventNode, CircularNodeView);
    configureModelElement(context, 'intermediateThrowEvent', EventNode, CircularNodeView);
	// configureModelElement(context, 'event:port', BPMNPort, RectangularNodeView);

    configureModelElement(context, 'exclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'inclusiveGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'parallelGateway', GatewayNode, DiamondNodeView);
    configureModelElement(context, 'complexGateway', GatewayNode, DiamondNodeView);

    configureModelElement(context, 'label:heading', SLabel, SLabelView, { enable: [editLabelFeature] });
    configureModelElement(context, 'BPMNLabel', SLabel, SLabelView, { enable: [editLabelFeature,selectFeature,moveFeature] });

    configureModelElement(context, 'comp:comp', SCompartment, SCompartmentView);
    configureModelElement(context, 'comp:header', SCompartment, SCompartmentView);
    configureModelElement(context, 'icon', Icon, IconView);

    configureModelElement(context, 'pool', PoolNode, RoundedCornerNodeView);
    configureModelElement(context, 'struct', SCompartment, StructureCompartmentView);

    // configureModelElement(context, 'edge:sequenceflow', SequenceFlow, BPMNSequenceFlowView);
    configureModelElement(context, 'sequenceFlow', SequenceFlow, BPMNSequenceFlowView);

});

export default function createContainer(widgetId: string): Container {
    // Create the createClientContainer with the diagramModule and the BPMN bpmnPropertyModule...
    const container = createClientContainer(bpmnDiagramModule, bpmnPropertyModule);
    overrideViewerOptions(container, {
        baseDiv: widgetId,
        hiddenDiv: widgetId + '_hidden'
    });
    return container;
}
