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

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on ResetRouting actions send from the client and
 * clears the routing points for one selected sequence flow
 *
 * @author rsoika
 *
 */
public class BPMNResetRoutingOperationHandler extends GModelOperationHandler<BPMNResetRoutingOperation> {

    private static Logger logger = Logger.getLogger(BPMNResetRoutingOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public Optional<Command> createCommand(BPMNResetRoutingOperation operation) {

        return commandOf(() -> {

            logger.info("Reset edge id=" + operation.getElementIds());

            for (String id : operation.getElementIds()) {
                BPMNElement element = modelState.getBpmnModel().findElementById(id);

                // is it a BPMN Edge?
                if (BPMNTypes.isEdge(element)) {
                    ((BPMNElementEdge) element).clearWayPoints();
                }

                // If it is a BPMNFlowElement we search for all BPMNEdges assoicated with this
                // element
                if (BPMNTypes.isFlowElementNode(element)) {
                    BPMNElementNode flowElement = (BPMNElementNode) element;
                    BPMNProcess process = flowElement.getBpmnProcess();
                    Set<BPMNElementEdge> _edges = process.findAllBPMNEdgesByElementId(id);
                    for (BPMNElementEdge _edge : _edges) {
                        _edge.clearWayPoints();
                    }
                }
            }
            modelState.reset();
        });
    }

}