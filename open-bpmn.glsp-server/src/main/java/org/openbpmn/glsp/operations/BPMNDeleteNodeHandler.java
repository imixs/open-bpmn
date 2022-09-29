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

import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.DeleteOperation;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.model.BPMNGModelState;

import com.google.inject.Inject;

public class BPMNDeleteNodeHandler extends AbstractOperationHandler<DeleteOperation> {

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void executeOperation(final DeleteOperation operation) {

        List<String> elementIds = operation.getElementIds();
        if (elementIds == null || elementIds.size() == 0) {
            System.out.println("Elements to delete are not specified");
        }
        for (String id : elementIds) {
            // Update the source model
            System.out.println("...delete Element " + id);
            // test if the element is a participant or a FlowElement
            BPMNParticipant participant = modelState.getBpmnModel().findBPMNParticipantById(id);
            if (participant != null) {
                // delete participant with the pool and all contained elements
                modelState.getBpmnModel().deleteBPMNParticipant(participant);
            } else {
                // find the bpmnFlowElement
                BPMNBaseElement baseElement = modelState.getBpmnModel().findBPMNBaseElementById(id);
                if (baseElement != null && baseElement instanceof BPMNFlowElement) {
                    // open the corresponding process
                    BPMNProcess process = ((BPMNFlowElement) baseElement).getBpmnProcess();
                    process.deleteBPMNBaseElement(id);
                } else {
                    System.out.println("...no BPMN elmenet with id: " + id + " found!");
                }
            }
        }

        // reset model state
        modelState.reset();

    }

}