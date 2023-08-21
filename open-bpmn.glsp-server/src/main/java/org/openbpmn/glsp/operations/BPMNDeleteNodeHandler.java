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
package org.openbpmn.glsp.operations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.server.operations.DeleteOperation;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.MessageFlow;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * Default Delete handler for all BPMN elements including all BPMN Node, Edges,
 * Lanes, Processes and
 * Messages.
 */
public class BPMNDeleteNodeHandler extends GModelOperationHandler<DeleteOperation> {
    private static Logger logger = Logger.getLogger(BPMNDeleteNodeHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public Optional<Command> createCommand(DeleteOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    private void executeOperation(final DeleteOperation operation) {

        List<String> elementIds = operation.getElementIds();
        if (elementIds == null || elementIds.size() == 0) {
            System.out.println("Elements to delete are not specified");
        }
        for (String id : elementIds) {
            // test if the element is a participant
            Participant participant = modelState.getBpmnModel().findParticipantById(id);
            if (participant != null) {
                // delete participant with the pool and all contained elements
                modelState.getBpmnModel().deleteParticipant(participant);
                continue;
            }

            // find the bpmnBaseElement
            BPMNElement bpmnElement = modelState.getBpmnModel().findElementById(id);
            if (bpmnElement == null) {
                // This situation can happen with BPMN Labels of Events and Gateways when the
                // main element was already removed
                logger.fine("...no BPMN element with id: " + id + " found!");
                continue;
            }

            // Message nodes and edges are handled by the BPMNModel
            if (bpmnElement instanceof Message) {
                bpmnElement.getModel().deleteMessage(id);
                continue;
            }
            if (bpmnElement instanceof MessageFlow) {
                bpmnElement.getModel().deleteMessageFlow(id);
                continue;
            }

            // Check if the element is a BPMNElementNode...
            if (bpmnElement instanceof BPMNElementNode) {
                // open the corresponding process
                BPMNProcess process = ((BPMNElementNode) bpmnElement).getBpmnProcess();
                process.deleteElementNode(id);
                continue;
            }

            if (bpmnElement instanceof SequenceFlow) {
                BPMNProcess process = ((SequenceFlow) bpmnElement).getProcess();
                if (process != null) {
                    process.deleteSequenceFlow(id);
                }
                continue;
            }
            if (bpmnElement instanceof Association) {
                BPMNProcess process = ((Association) bpmnElement).getProcess();
                if (process != null) {
                    process.deleteAssociation(id);
                }
                continue;
            }

        }

        // reset model state
        modelState.reset();

    }

}