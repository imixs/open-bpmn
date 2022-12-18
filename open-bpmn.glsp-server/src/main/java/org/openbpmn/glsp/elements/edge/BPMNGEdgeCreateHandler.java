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
package org.openbpmn.glsp.elements.edge;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.elements.CreateBPMNEdgeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

public class BPMNGEdgeCreateHandler extends CreateBPMNEdgeOperationHandler {

    protected final String label;
    private static Logger logger = Logger.getLogger(BPMNGEdgeCreateHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNGEdgeCreateHandler() {
        super(BPMNModel.BPMN_EDGES);
        this.label = "Sequence Flow";
    }

    /**
     * Adds a new BPMNEdge to the diagram. Depending on the type a SequenceFlow,
     * MessageFlow or Association is crated.
     */
    @Override
    protected void executeOperation(final CreateEdgeOperation operation) {
        if (operation.getSourceElementId() == null || operation.getTargetElementId() == null) {
            throw new IllegalArgumentException("Incomplete create connection action");
        }

        String edgeType = operation.getElementTypeId();
        try {
            Optional<BPMNGNode> element = null;
            String targetId = operation.getTargetElementId();
            // find GNode
            element = modelState.getIndex().findElementByClass(targetId, BPMNGNode.class);
            if (element.isPresent()) {
                targetId = element.get().getId();
            }

            String sourceId = operation.getSourceElementId();
            // find GNode
            element = modelState.getIndex().findElementByClass(sourceId, BPMNGNode.class);
            if (element.isPresent()) {
                sourceId = element.get().getId();
            }

            // Verify that both Elements are members of the same process...
            String sourceProcessId = modelState.getBpmnModel().findBPMNNodeById(sourceId).getProcessId();
            String targetProcessId = modelState.getBpmnModel().findBPMNNodeById(targetId).getProcessId();
            if (sourceProcessId == null || !sourceProcessId.equals(targetProcessId)) {
                throw new IllegalArgumentException("Target and Source Element are not members of the same process!");
            }
            // open the process and create the sequence flow...
            BPMNProcess bpmnProcess = modelState.getBpmnModel().openProcess(targetProcessId);

            switch (edgeType) {
            case BPMNTypes.SEQUENCE_FLOW:
                bpmnProcess.addSequenceFlow(BPMNModel.generateShortID("SequenceFlow"), sourceId, targetId);
                break;
            case BPMNTypes.MESSAGE_FLOW:
                bpmnProcess.addMessageFlow(BPMNModel.generateShortID("MessageFlow"), sourceId, targetId);
                break;
            case BPMNTypes.ASSOCIATION:
                bpmnProcess.addAssociation(BPMNModel.generateShortID("Association"), sourceId, targetId);
                break;
            default:
                throw new IllegalArgumentException("Invalid BPMNEdge type: " + edgeType);
            }
            modelState.reset();
        } catch (BPMNModelException e) {
            logger.severe(e.getMessage());
        }

    }

    @Override
    public String getLabel() {
        return label;
    }
}
