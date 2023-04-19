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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ReconnectEdgeOperation;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This OperationHandler reacts on reconnecting a BPMNSequenceFlow.
 * <p>
 *
 *
 * @author rsoika
 *
 */
public class BPMNReconnectEdgeOperationHandler extends AbstractOperationHandler<ReconnectEdgeOperation> {

    private static Logger logger = LogManager.getLogger(BPMNReconnectEdgeOperationHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void executeOperation(final ReconnectEdgeOperation operation) {

        String edgeID = operation.getEdgeElementId();
        String sourceElementID = operation.getSourceElementId();
        String targetElementID = operation.getTargetElementId();

        logger.debug("Reconnect: " + edgeID + "  " + sourceElementID + "<-->" + targetElementID);

        BPMNElementEdge bpmnElementEdge = modelState.getBpmnModel().findElementEdgeById(edgeID);

        BPMNElementNode targetElement = modelState.getBpmnModel().findElementNodeById(targetElementID);
        BPMNElementNode sourceElement = modelState.getBpmnModel().findElementNodeById(sourceElementID);

        if (bpmnElementEdge != null && targetElement != null && sourceElement != null) {

            // Test some edge-cases for SequenceFlows
            if (BPMNTypes.isSequenceFlow(bpmnElementEdge)) {
                // target and the source MUST be a BPMN FlowElement Node!
                if (!BPMNTypes.isFlowElementNode(sourceElement)
                        || !BPMNTypes.isFlowElementNode(targetElement)) {
                    logger.warn("SequenceFlows can only be reconnected with FlowElement Nodes! ");
                    return;
                }
                // Process must be the same for target and source!
                if (!targetElement.getProcessId().equals(sourceElement.getProcessId())) {
                    logger.warn("SequenceFlows can only be reconnected within the same Process! ");
                    return;
                }
            }

            bpmnElementEdge.setSourceRef(sourceElementID);
            bpmnElementEdge.setTargetRef(targetElementID);

            // remove routing points
            bpmnElementEdge.clearWayPoints();
            modelState.reset();
        }

        // no more action - the GModel is now up to date
    }

}
