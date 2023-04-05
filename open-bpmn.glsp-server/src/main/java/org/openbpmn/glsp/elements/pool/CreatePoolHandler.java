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
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to create a new BPMNParticipant within the BPMN Model.
 *
 * @author rsoika
 *
 */
public class CreatePoolHandler extends CreateBPMNNodeOperationHandler {
    private static Logger logger = Logger.getLogger(CreatePoolHandler.class.getName());
    private String elementTypeId;

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    public CreatePoolHandler() {
        super(BPMNTypes.POOL);
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        elementTypeId = operation.getElementTypeId();
        // now we add a new participant into the source model
        String participantID = BPMNModel.generateShortID("participant");
        logger.fine("===== > createNode poolID=" + participantID);
        try {
            // add a new BPMNParticipant to BPMN model
            Participant participant = modelState.getBpmnModel().addParticipant(getLabel());
            Optional<GPoint> point = operation.getLocation();
            if (point.isPresent()) {
                // set the bounds
                participant.setPosition(point.get().getX(), point.get().getY());
                participant.setDimension(Participant.DEFAULT_WIDTH, Participant.DEFAULT_HEIGHT);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(participantID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.POOL_GNODE, elementTypeId, modelState);
        nodeCounter++; // start with 1
        return "Pool-" + nodeCounter;
    }

}
