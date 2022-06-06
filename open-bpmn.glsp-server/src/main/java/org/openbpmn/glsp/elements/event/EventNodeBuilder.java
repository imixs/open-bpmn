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
package org.openbpmn.glsp.elements.event;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

/**
 * The EventNodeBuilder defines the layout and properties for all types of BPMN
 * Event elements
 * <p>
 * The radius of the circle symbol is 36. The label is below the symbol.
 * <p>
 * The EventNodeBuilder also creates JSONForms data and schema information.
 *
 * @author rsoika
 *
 */
public class EventNodeBuilder extends AbstractGNodeBuilder<EventNode, EventNodeBuilder> {

    private final String name;

    public EventNodeBuilder(final String type, final String name) {
        super(type);
        this.name = name;
        this.addCssClass("event");
        this.addCssClass(type);
    }

    @Override
    protected EventNode instantiate() {
        return BpmnFactory.eINSTANCE.createEventNode();
    }

    @Override
    protected EventNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final EventNode node) {
        super.setProperties(node);
        node.setName(name);

        node.setLayout(GConstants.Layout.FREEFORM);
        size = GraphUtil.dimension(BPMNEvent.DEFAULT_WIDTH, BPMNEvent.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", BPMNEvent.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", BPMNEvent.DEFAULT_HEIGHT);

        node.getChildren().add(BPMNBuilderHelper.createCompartmentIcon(node));

    }

}
