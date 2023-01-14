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
package org.openbpmn.glsp.elements.event;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.utils.BPMNGraphUtil;

/**
 * BPMN 2.0 Event Element.
 * <p>
 * The method builds a GNode from a BPMNEvent element. The builder is called
 * from the method createGModelFromProcess of the BPMNGModelFactory.
 * <p>
 * The EventNodeBuilder defines the layout and properties for all types of BPMN
 * Event elements. The radius of the circle symbol is 36. The label is below the
 * symbol.
 *
 * @author rsoika
 *
 */
public class EventGNodeBuilder extends AbstractGNodeBuilder<EventGNode, EventGNodeBuilder> {

    private final String name;

    public EventGNodeBuilder(final Event event) {
        super(event.getType());
        this.name = event.getName();
        this.id = event.getId();

        // set Layout options
        this.addCssClass(type);
        this.addCssClass("event");
    }

    @Override
    protected EventGNode instantiate() {
        return BpmnFactory.eINSTANCE.createEventGNode();
    }

    @Override
    protected EventGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final EventGNode node) {
        super.setProperties(node);
        node.setName(name);
        node.setLayout(GConstants.Layout.FREEFORM);
        size = GraphUtil.dimension(Event.DEFAULT_WIDTH, Event.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", Event.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", Event.DEFAULT_HEIGHT);

        node.getChildren().add(BPMNGraphUtil.createCompartmentIcon(node));

    }

}
