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
import {
    configureDefaultModelElements,
    configureModelElement,
    ConsoleLogger,
    createClientContainer,
    DeleteElementContextMenuItemProvider,
    editLabelFeature,
    GridSnapper,
    LogLevel,
    overrideViewerOptions,
    RevealNamedElementActionProvider,
    RoundedCornerNodeView,
    RectangularNodeView,
    SCompartment,
    SCompartmentView,
    SLabel,
    SLabelView,
    StructureCompartmentView,
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
	BPMNElementAnchor,
	BPMNPolylineElementAnchor,
	BPMNPort
} from '@open-bpmn/open-bpmn-model';
import { IconView, GatewayNodeView, EventNodeView } from './bpmn-element-views';
import { BPMNSequenceFlowView } from './bpmn-routing-views';
import { BPMNHelperLineTool } from './bpmn-helperlines';

import {bpmnPropertyModule} from '@open-bpmn/open-bpmn-properties';

const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);
    bind(TYPES.ISnapper).to(GridSnapper);
    bind(TYPES.ICommandPaletteActionProvider).to(RevealNamedElementActionProvider);
    bind(TYPES.IContextMenuItemProvider).to(DeleteElementContextMenuItemProvider);

	// bpmn helper lines
	// bind(BPMNHelperLine).toSelf().inSingletonScope();
    // bind(TYPES.IUIExtension).toService(BPMNHelperLine);
    bind(TYPES.MouseListener).to(BPMNHelperLineTool);

    // bind the BPMN AnchorComputer
    bind(TYPES.IAnchorComputer).to(BPMNElementAnchor).inSingletonScope();
    bind(TYPES.IAnchorComputer).to(BPMNPolylineElementAnchor).inSingletonScope();

    // bind(TYPES.IAnchorComputer).to(BPMNSequenceFlowAnchor).inSingletonScope();
    // bind(TYPES.IAnchorComputer).to(BPMNEventElementAnchor).inSingletonScope();

    const context = { bind, unbind, isBound, rebind };

    configureDefaultModelElements(context);
    configureModelElement(context, 'task:manual', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'task:user', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'task:send', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'task:service', TaskNode, RoundedCornerNodeView);
    configureModelElement(context, 'task:script', TaskNode, RoundedCornerNodeView);

    configureModelElement(context, 'event:start', EventNode, EventNodeView);
    configureModelElement(context, 'event:end', EventNode, EventNodeView);
	configureModelElement(context, 'event:port', BPMNPort, RectangularNodeView);
	// configureModelElement(context, 'event:port', SPort, RectangularNodeView, { disable: [selectFeature] });

    configureModelElement(context, 'gateway:exclusive', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'gateway:inclusive', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'gateway:parallel', GatewayNode, GatewayNodeView);
    configureModelElement(context, 'gateway:complex', GatewayNode, GatewayNodeView);

    configureModelElement(context, 'label:heading', SLabel, SLabelView, { enable: [editLabelFeature] });
    configureModelElement(context, 'comp:comp', SCompartment, SCompartmentView);
    configureModelElement(context, 'comp:header', SCompartment, SCompartmentView);
    configureModelElement(context, 'icon', Icon, IconView);

    configureModelElement(context, 'pool', PoolNode, RoundedCornerNodeView);
    configureModelElement(context, 'struct', SCompartment, StructureCompartmentView);

    configureModelElement(context, 'edge:sequenceflow', SequenceFlow, BPMNSequenceFlowView);

    // configureModelElement(context, 'helpline', HelpLineNode, HelpLineView);

    
    /*this.bpmnMouseListener = new BPMNMouseListener();
		this.mouseTool.register(this.bpmnMouseListener);*/

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
