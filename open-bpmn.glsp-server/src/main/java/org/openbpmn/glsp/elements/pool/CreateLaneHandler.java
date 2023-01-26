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
package org.openbpmn.glsp.elements.pool;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to create a new BPMNLane within the BPMN Pool.
 *
 * @author rsoika
 *
 */
public class CreateLaneHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(CreateLaneHandler.class);

    private String elementTypeId;

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    public CreateLaneHandler() {
        super(BPMNTypes.LANE);
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        logger.debug("===== > created lane....");
        elementTypeId = operation.getElementTypeId();
        // now we add a new lane into the source model
        String laneID = BPMNModel.generateShortID("lane");
        try {
            // find the process - should be the corresponding participant process
            BPMNProcess bpmnProcess = findProcessByCreateNodeOperation(operation);
            if (bpmnProcess != null) {
                Lane bpmnLane = bpmnProcess.addLane("Lane " + (bpmnProcess.getLanes().size() + 1));
                logger.debug("===== > created lane=" + bpmnLane.getId());

            } else {
                logger.error("Failed do find corresponding process to add lane " + laneID);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(laneID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.LANE_GNODE, elementTypeId, modelState);
        nodeCounter++; // start with 1
        return "Lane-" + nodeCounter;
    }

}
