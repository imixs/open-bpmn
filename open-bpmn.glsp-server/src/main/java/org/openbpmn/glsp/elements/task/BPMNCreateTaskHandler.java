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

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGridSnapper;

import com.google.inject.Inject;

/**
 * The BPMNCreateTaskHandler is a GLSP CreateNodeOperation bound to the
 * DiagramModule and called when ever a BPMNTask is newly created within the
 * model.
 *
 * @author rsoika
 *
 */
public class BPMNCreateTaskHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(BPMNCreateTaskHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    private String elementTypeId;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateTaskHandler() {
        super(BPMNTypes.BPMN_TASKS);
    }

    @Override
    public Optional<Command> createCommand(final CreateNodeOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    protected void executeOperation(final CreateNodeOperation operation) {

        elementTypeId = operation.getElementTypeId();
        // now we add this task into the source model
        String taskID = BPMNModel.generateShortID("task");
        logger.debug("createNode taskNodeID=" + taskID);
        try {
            // find the process - either the default process for Root container or the
            // corresponding participant process
            BPMNProcess bpmnProcess = findProcessByCreateNodeOperation(operation);
            if (bpmnProcess != null) {
                Activity task = bpmnProcess.addTask(taskID, getLabel(), operation.getElementTypeId());
                Optional<GPoint> point = operation.getLocation();
                if (point.isPresent()) {
                    BPMNPoint targetPosition = BPMNGridSnapper.snap(task, point.get());
                    task.setPosition(targetPosition);
                    task.setDimension(Activity.DEFAULT_WIDTH, Activity.DEFAULT_HEIGHT);
                    logger.debug("new BPMNActivity Position = " + targetPosition.getX() + "," + targetPosition.getY());
                }
            } else {
                // should not happen
                logger.fatal("Unable to find a valid BPMNElement to place the new node: " + elementTypeId);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(taskID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.TASK_GNODE, elementTypeId, modelState);
        return "Task-" + (nodeCounter + 1);
    }

}
