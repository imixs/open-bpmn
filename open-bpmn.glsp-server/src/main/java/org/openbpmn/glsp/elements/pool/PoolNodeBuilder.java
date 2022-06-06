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
package org.openbpmn.glsp.elements.pool;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.Pool;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * BPMN 2.0 Pool Element
 *
 * @author rsoika
 *
 */
public class PoolNodeBuilder extends AbstractGNodeBuilder<Pool, PoolNodeBuilder> {
    private String name;

    private static final String V_GRAB = "vGrab";
    private static final String H_GRAB = "hGrab";
    private static final String H_ALIGN = "hAlign";

    public PoolNodeBuilder(final String name) {
        super(ModelTypes.POOL);
        this.name = name;
    }

    @Override
    protected Pool instantiate() {
        return BpmnFactory.eINSTANCE.createPool();
    }

    @Override
    protected PoolNodeBuilder self() {
        return this;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    protected void setProperties(final Pool node) {
        super.setProperties(node);
        node.setName(name);
        node.setLayout(GConstants.Layout.VBOX);
        node.getLayoutOptions().put(H_ALIGN, "center");
        node.getLayoutOptions().put(H_GRAB, false);
        node.getLayoutOptions().put(V_GRAB, false);
        node.getCssClasses().add("pool");
        node.getChildren().add(createLabelCompartment(node));
        node.getChildren().add(createStructCompartment(node));
    }

    private GCompartment createLabelCompartment(final Pool node) {
        Map<String, Object> layoutOptions = new HashMap<>();

        return new GCompartmentBuilder(ModelTypes.COMP_HEADER) //
                .id(node.getId() + "_header") //
                .layout(GConstants.Layout.HBOX) //
                .layoutOptions(layoutOptions) //
                .add(createCompartmentHeader(node)) //
                .build();
    }

    private GLabel createCompartmentHeader(final Pool node) {
        return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
                .id(node.getId() + "_header_label") //
                .text(node.getName()) //
                .build();
    }

    private GCompartment createStructCompartment(final Pool node) {
        Map<String, Object> layoutOptions = new HashMap<>();
        layoutOptions.put(H_ALIGN, "left");
        layoutOptions.put(H_GRAB, true);
        layoutOptions.put(V_GRAB, true);
        GCompartmentBuilder structCompartmentBuilder = new GCompartmentBuilder(ModelTypes.STRUCTURE) //
                .id(node.getId() + "_struct") //
                .layout(GConstants.Layout.FREEFORM) //
                .layoutOptions(layoutOptions);
        return structCompartmentBuilder //
                .build();
    }
}
