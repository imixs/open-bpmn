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
import {
    configureModelElement,
    ConsoleLogger,
    createClientContainer,
    GLSPGraph,
    LogLevel,
    overrideViewerOptions,
    RectangularNode,
    RectangularNodeView,
    RoundedCornerNodeView,
    SGraphView,
    TYPES
} from '@eclipse-glsp/client';
import { DefaultTypes } from '@eclipse-glsp/protocol';
import 'balloon-css/balloon.min.css';
import { Container, ContainerModule } from 'inversify';
import '../css/diagram.css';

// import model elements ActivityNode, Icon
import {  TaskNode} from './model';

// import views IconView, WorkflowEdgeView
//import { IconView } from './workflow-views';

const minimalDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    // set loglevel warn | info
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.info);
    const context = { bind, unbind, isBound, rebind };
    configureModelElement(context, DefaultTypes.GRAPH, GLSPGraph, SGraphView);
    configureModelElement(context, DefaultTypes.NODE, RectangularNode, RectangularNodeView);

    // Task
    configureModelElement(context, 'task:manual', TaskNode, RoundedCornerNodeView);
   // configureModelElement(context, 'icon', Icon, IconView);

});

export default function createContainer(widgetId: string): Container {
    const container = createClientContainer(minimalDiagramModule);

    overrideViewerOptions(container, {
        baseDiv: widgetId,
        hiddenDiv: widgetId + '_hidden',
        needsClientLayout: true
    });

    return container;
}
