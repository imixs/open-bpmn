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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.elements.CreateBPMNEdgeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * Handler to create a BPMN SequenceFlow, MessageFlow or Association.
 */
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
        super(BPMNTypes.BPMN_EDGE_ELEMENTS);
        this.label = "Sequence Flow";
    }

    @Override
    public Optional<Command> createCommand(final CreateEdgeOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    /**
     * Adds a new BPMNEdge to the diagram. Depending on the type a SequenceFlow,
     * MessageFlow or Association is created.
     */
    protected void executeOperation(final CreateEdgeOperation operation) {
        if (operation.getSourceElementId() == null || operation.getTargetElementId() == null) {
            throw new IllegalArgumentException("Incomplete create connection action");
        }
        String edgeId = null;
        BPMNGNode sourceGNode = null;
        BPMNGNode targetGNode = null;
        BPMNElementNode sourceElementNode = null;
        BPMNElementNode targetElementNode = null;

        String edgeType = operation.getElementTypeId();
        try {
            String targetId = operation.getTargetElementId();
            // find GNode
            targetGNode = (BPMNGNode) modelState.getIndex().get(targetId).orElse(null);

            String sourceId = operation.getSourceElementId();
            // find GNode
            sourceGNode = (BPMNGNode) modelState.getIndex().get(sourceId).orElse(null);

            // Depending on the edgeType we use here different method to create the BPMN
            // edge
            sourceElementNode = (BPMNElementNode) modelState.getBpmnModel()
                    .findElementById(sourceId);
            targetElementNode = (BPMNElementNode) modelState.getBpmnModel()
                    .findElementById(targetId);

            if (BPMNTypes.SEQUENCE_FLOW.equals(edgeType)) {

                // Verify that both Elements are members of the same process...
                String sourceProcessId = modelState.getBpmnModel().findElementNodeById(sourceId).getProcessId();
                String targetProcessId = modelState.getBpmnModel().findElementNodeById(targetId).getProcessId();
                if (sourceProcessId == null || !sourceProcessId.equals(targetProcessId)) {
                    throw new IllegalArgumentException(
                            "Target and Source Element are not members of the same process!");
                }
                // open the process and create the sequence flow...
                edgeId = BPMNModel.generateShortID("sequenceFlow");
                BPMNProcess bpmnProcess = modelState.getBpmnModel().openProcess(targetProcessId);
                bpmnProcess.addSequenceFlow(edgeId, sourceId, targetId);
            }

            if (BPMNTypes.ASSOCIATION.equals(edgeType)) {
                // if one of the element nodes is assigned to the default process, than we
                // assign the association also to the default process
                BPMNProcess sourceProcess = sourceElementNode.getBpmnProcess();
                BPMNProcess targetProcess = targetElementNode.getBpmnProcess();
                edgeId = BPMNModel.generateShortID("association");
                if (sourceProcess.isPublicProcess()) {
                    sourceProcess.addAssociation(edgeId, sourceId, targetId);
                } else {
                    // in any case this is the best match now
                    targetProcess.addAssociation(edgeId, sourceId, targetId);
                }
            }

            if (BPMNTypes.MESSAGE_FLOW.equals(edgeType)) {
                edgeId = BPMNModel.generateShortID("messageFlow");
                modelState.getBpmnModel().addMessageFlow(edgeId, sourceId, targetId);
            }

            // invalidate source/target elements
            sourceElementNode.resetValidation();
            targetElementNode.resetValidation();

            // finally update he current selection
            updateSelection(sourceGNode, targetGNode, edgeId);
            modelState.reset();

        } catch (BPMNModelException e) {
            logger.severe(e.getMessage());
        }

    }

    @Override
    public String getLabel() {
        return label;
    }

    /*
     * This helper method updates the current selection by sending a new
     * SelectAction. The source and target element are deselected and the new enge
     * is selected.
     * 
     */
    private void updateSelection(BPMNGNode sourceNode, BPMNGNode targetNode, String edgeId) {
        // finally deselect the initial elements and select the new edge instead.
        ArrayList<String> deselectedElementsIDs = new ArrayList<String>();
        deselectedElementsIDs.add(sourceNode.getId());
        // if the source element is has bpmn label , than we need to deselect the label
        // too
        if (BPMNTypes.BPMN_EVENTS.contains(sourceNode.getType())
                || BPMNTypes.BPMN_GATEWAYS.contains(sourceNode.getType())) {
            deselectedElementsIDs.add(sourceNode.getId() + "_bpmnlabel");
        }
        deselectedElementsIDs.add(targetNode.getId());
        // if the target element is has bpmn label , than we need to deselect the label
        // too
        if (BPMNTypes.BPMN_EVENTS.contains(targetNode.getType())
                || BPMNTypes.BPMN_GATEWAYS.contains(targetNode.getType())) {
            deselectedElementsIDs.add(targetNode.getId() + "_bpmnlabel");
        }
        actionDispatcher.dispatch(new SelectAction(List.of(edgeId),
                deselectedElementsIDs));
    }
}
