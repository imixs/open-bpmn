/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
package org.openbpmn.glsp.elements.task;

import java.util.Map;
import java.util.Optional;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNTaskType;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;

import com.google.inject.Inject;

public abstract class CreateTaskHandler extends CreateBPMNNodeOperationHandler {

    @Inject
    protected BPMNGModelState modelState;

    /**
     * Default constructor
     */
    public CreateTaskHandler() {
        super(BPMNTaskType.TASK);
    }

    public CreateTaskHandler(final String elementTypeId) {
        super(elementTypeId);
    }

    protected String getElementTypeId() {
        return getHandledElementTypeIds().get(0);
    }

    @Override
    protected GNode createNode(final Optional<GPoint> point, final Map<String, String> args) {
        System.out.println("===== > createNode");
        TaskNode taskNode = builder(point, modelState).build();

        // now we add this task into the source model
        System.out.println("===== > createNode tasknodeID=" + taskNode.getId());
        BPMNProcess process = modelState.getBpmnModel().getContext();
        process.addTask(taskNode.getId(), taskNode.getName(), taskNode.getType());

        return taskNode;
    }

    protected TaskNodeBuilder builder(final Optional<GPoint> point, final GModelState modelState) {

        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.TASK_NODE, getElementTypeId(), modelState);
        String name = getLabel() + " " + nodeCounter;

        return new TaskNodeBuilder(getElementTypeId(), name) //
                .position(point.orElse(null));
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        super.executeOperation(operation);

        System.out.println("===== > executeOperation " + operation.getElementTypeId() + " kind=" + operation.getKind());
        System.out.println("===== > Modelstate we have now " + modelState.getIndex().allIds().size() + " elements");

        // How should we generate a new element in the SourceModel if we do not know the
        // ID???

    }

}
