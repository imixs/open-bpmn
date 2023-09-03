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
package org.openbpmn.glsp.elements.task;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.bpmn.TaskGNode;
import org.openbpmn.glsp.elements.IconGCompartmentBuilder;
import org.openbpmn.glsp.utils.BPMNGModelUtil;

/**
 * BPMN 2.0 Task Element.
 * <p>
 * The method builds a GNode from a BPMNTask element. The builder is called from
 * the method createGModelFromProcess of the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class TaskGNodeBuilder extends AbstractGNodeBuilder<TaskGNode, TaskGNodeBuilder> {

    private static Logger logger = Logger.getLogger(TaskGNodeBuilder.class.getName());
    private static final String V_GRAB = "vGrab";
    private static final String H_GRAB = "hGrab";
    private final String name;

    public TaskGNodeBuilder(final Activity activity) {
        super(activity.getType());
        this.name = activity.getName();
        this.id = activity.getId();

        try {
            BPMNBounds bpmnBounds = activity.getBounds();
            this.size = GraphUtil.dimension(bpmnBounds.getDimension().getWidth(),
                    bpmnBounds.getDimension().getHeight());
        } catch (BPMNMissingElementException e) {
            // should not happen
            logger.severe("BPMNActivity does not support a BPMNBounds object!");
        }
        // set Layout options
        this.addCssClass(type);
        this.addCssClass("task");

        this.addArguments(GArguments.cornerRadius(5));
    }

    @Override
    protected TaskGNode instantiate() {
        return BpmnFactory.eINSTANCE.createTaskGNode();
    }

    @Override
    protected TaskGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final TaskGNode node) {
        super.setProperties(node);
        node.setName(name);

        node.setLayout(GConstants.Layout.HBOX);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, Activity.DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, Activity.DEFAULT_HEIGHT);
        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());

        GCompartment freeformContainer = createContainerCompartment(node);
        node.getChildren().add(freeformContainer);

        // add Icon
        GPoint iconPosition = GraphUtil.point(3, 3);
        IconGCompartment taskIcon = new IconGCompartmentBuilder() //
                .id(node.getId() + "_icon") //
                .position(iconPosition) //
                .build();
        freeformContainer.getChildren().add(taskIcon);

        // add Text Input
        freeformContainer.getChildren().add(BPMNGModelUtil.createMultiLineTextNode(id + "_name", name));

        // add extension label
        String extensionLabelString = (String) node.getArgs().get("extensionLabel");
        GPoint extensionPosition = GraphUtil.point(3, size.getHeight() - 12);
        GCompartment extensionLabel = new GCompartmentBuilder()
                .type(DefaultTypes.COMPARTMENT)
                .position(extensionPosition) //
                // .layoutOptions(extensionLabelLayoutOptions) //
                .add(new GLabelBuilder()
                        .text(extensionLabelString)
                        .build())
                .addCssClass("extension")
                .build();
        freeformContainer.getChildren().add(extensionLabel);

    }

    private GCompartment createContainerCompartment(final TaskGNode node) {

        Map<String, Object> superLayoutOptions = new HashMap<>();
        superLayoutOptions.put(V_GRAB, true);
        superLayoutOptions.put(H_GRAB, true);
        superLayoutOptions.put(GLayoutOptions.KEY_H_GAP, 0);
        superLayoutOptions.put(GLayoutOptions.KEY_V_GAP, 0);
        superLayoutOptions.put(GLayoutOptions.KEY_RESIZE_CONTAINER, false);

        return new GCompartmentBuilder()
                .type(DefaultTypes.COMPARTMENT)
                .position(1, 1) //
                .size(node.getSize().getWidth() - 2, node.getSize().getHeight() - 2)
                .layoutOptions(superLayoutOptions)
                .addCssClass("label-container")
                .build();
    }


}
