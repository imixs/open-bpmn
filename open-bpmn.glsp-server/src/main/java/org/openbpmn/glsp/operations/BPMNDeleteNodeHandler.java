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
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
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
            System.out.println("...delete Element " + id);

            // Update the source model
            BPMNBaseElement baseElement = modelState.getBpmnModel().getContext().findBaseElementById(id);
            if (baseElement == null) {
                System.out.println("...no BPMN elmenet with id: " + id + " found!");
            }
            if (baseElement instanceof BPMNActivity) {
                modelState.getBpmnModel().getContext().deleteTask(id);
            }
            if (baseElement instanceof BPMNEvent) {
                modelState.getBpmnModel().getContext().deleteEvent(id);
            }
            if (baseElement instanceof BPMNGateway) {
                modelState.getBpmnModel().getContext().deleteGateway(id);
            }
            if (baseElement instanceof BPMNSequenceFlow) {
                modelState.getBpmnModel().getContext().deleteSequenceFlow(id);
            }
        }

        // reset model state
        modelState.reset();

    }

}