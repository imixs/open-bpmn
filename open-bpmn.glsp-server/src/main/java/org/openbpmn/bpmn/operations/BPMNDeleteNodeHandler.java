/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.bpmn.operations;

import java.util.List;

import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.DeleteOperation;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;

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