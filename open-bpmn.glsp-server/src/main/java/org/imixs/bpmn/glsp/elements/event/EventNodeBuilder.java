/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.imixs.bpmn.glsp.elements.event;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.EventNode;
import org.imixs.bpmn.glsp.utils.BPMNBuilderHelper;

public class EventNodeBuilder extends AbstractGNodeBuilder<EventNode, EventNodeBuilder> {

    private final String name;
    private final String eventType;

    public EventNodeBuilder(final String type, final String name, String eventType) {
        super(type);
        this.name = name;
        if (eventType == null || eventType.isEmpty()) {
            eventType = "";
        }
        this.eventType = eventType;

    }

    @Override
    protected EventNode instantiate() {
        return BpmngraphFactory.eINSTANCE.createEventNode();
    }

    @Override
    protected EventNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final EventNode node) {
        super.setProperties(node);
        node.setName(name);
        node.getCategory().add(eventType);
        node.setLayout(GConstants.Layout.FREEFORM);

        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));
        node.getChildren().add(BPMNBuilderHelper.createPort(node, -5.0, -25.0, "_north"));
        node.getChildren().add(BPMNBuilderHelper.createPort(node, -25.0, -5.0, "_west"));
        node.getChildren().add(BPMNBuilderHelper.createPort(node, 15.0, -5.0, "_east"));
        node.getChildren().add(BPMNBuilderHelper.createPort(node, -5.0, 15.0, "_south"));
    }

}
