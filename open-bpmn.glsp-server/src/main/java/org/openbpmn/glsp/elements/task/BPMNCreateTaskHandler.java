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
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.model.BPMNGModelState;

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

    private static Logger logger = Logger.getLogger(BPMNCreateTaskHandler.class.getName());

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
        super(BPMNModel.BPMN_TASKS);
    }

    @Override
    protected void executeOperation(final CreateNodeOperation operation) {

        GModelElement container = getContainer(operation).orElseGet(modelState::getRoot);

        logger.info(" ==> Wir setzten das Task Element in den Container : " + container.getId());

        elementTypeId = operation.getElementTypeId();
        // now we add this task into the source model
        String taskID = "task-" + BPMNModel.generateShortID();
        logger.fine("===== > createNode tasknodeID=" + taskID);
        try {
            // find the process - either the default process for Root container or the
            // corresponding participant process
            BPMNProcess bpmnProcess = null;
            // is it the root?
            if (modelState.getRoot().getId().equals(container.getId())) {
                bpmnProcess = modelState.getBpmnModel().openDefaultProcess();
            } else {
                // it should be a participant container
                if (container.getId().startsWith("participant_")) {
                    // compute participant
                    String participantID = container.getId().substring(0, container.getId().lastIndexOf("_"));
                    BPMNParticipant bpmnParticipant = modelState.getBpmnModel().findBPMNParticipantById(participantID);
                    if (bpmnParticipant != null) {
                        bpmnProcess = bpmnParticipant.openProcess();
                    }
                }
            }

            if (bpmnProcess != null) {
                BPMNActivity task = bpmnProcess.addTask(taskID, getLabel(), operation.getElementTypeId());
                Optional<GPoint> point = operation.getLocation();
                if (point.isPresent()) {
                    task.getBounds().updateLocation(point.get().getX(), point.get().getY());
                    task.getBounds().updateDimension(BPMNActivity.DEFAULT_WIDTH, BPMNActivity.DEFAULT_HEIGHT);

                    logger.info("....Drop Position = " + point.get().getX() + " " + point.get().getY());
                }
            } else {
                // should not happen
                logger.severe("Unable to find a vaild BPMNElement to place the new node: " + elementTypeId);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(taskID)));
    }

//    @Override
//    public void executeOperation(final CreateNodeOperation operation) {
//       GModelElement container = getContainer(operation).orElseGet(modelState::getRoot);
//       Optional<GPoint> absoluteLocation = getLocation(operation);
//       Optional<GPoint> relativeLocation = getRelativeLocation(operation, absoluteLocation, container);
//       GModelElement element = createNode(relativeLocation, operation.getArgs());
//       container.getChildren().add(element);
//       actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(element.getId())));
//    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.TASK_NODE, elementTypeId, modelState);
        return "Task-" + nodeCounter;
    }

}
