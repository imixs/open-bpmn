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
package org.imixs.bpmn.glsp.elements.task;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.glsp.utils.BPMNBuilderHelper;
import org.imixs.bpmn2.Bpmn2Factory;
import org.imixs.bpmn2.Task;

public class TaskNodeBuilder extends AbstractGNodeBuilder<Task, TaskNodeBuilder> {

    private final String name;
    private final String taskType;

    public TaskNodeBuilder(final String type, final String name, final String taskType) {
        super(type);
        this.name = name;
        this.taskType = taskType;

    }

    @Override
    protected Task instantiate() {
        return Bpmn2Factory.eINSTANCE.createTask();
    }

    @Override
    protected TaskNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final Task node) {
        super.setProperties(node);
        node.setName(name);
        node.getCategory().add(taskType);
        node.setDocumentation("...some documentation....");

        node.setLayout(GConstants.Layout.HBOX);

        // Set min width/height
        node.getLayoutOptions().put("minWidth", 160);
        node.getLayoutOptions().put("minHeight", 60);

        node.getLayoutOptions().put("hGap", 10);
        node.getLayoutOptions().put("vAlign", "center");

        node.getChildren().add(BPMNBuilderHelper.createCompartmentIcon(node));
        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));

    }
}
