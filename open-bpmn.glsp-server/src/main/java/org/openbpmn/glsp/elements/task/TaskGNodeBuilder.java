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

import java.util.logging.Logger;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.TaskGNode;
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
        this.addArgument("info", "");
    }


    @Override
    protected TaskGNode instantiate() {
        return BpmnFactory.eINSTANCE.createTaskGNode();
    }

    @Override
    protected TaskGNodeBuilder self() {
        return this;
    }

    /**
     * The taskGNode element uses a HBOX layout with an multiLabel element.
     * The client implements a custom micro-view component to display the
     * Icon and the Extension text.
     * 
     * @see bpmn-element-views.tsx
     */
    @Override
    public void setProperties(final TaskGNode node) {
        super.setProperties(node);
        node.setLayout(GConstants.Layout.HBOX);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, Activity.DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, Activity.DEFAULT_HEIGHT);
        if (size != null) {
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());
        }
        // add a multiLine text block to show long text blocks
        node.getChildren()
                .add(BPMNGModelUtil.createMultiLineTextNode(node, name, BPMNGModelUtil.MULTILINETEXT_ALIGN_MIDDLE, 8));
    }

}
