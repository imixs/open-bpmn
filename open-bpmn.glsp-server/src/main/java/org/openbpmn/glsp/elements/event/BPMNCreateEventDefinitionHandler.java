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
package org.openbpmn.glsp.elements.event;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.BPMNDiagramConfiguration;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;

import com.google.inject.Inject;

/**
 * OperationHandler to add a new EventDefinition.
 *
 * @author rsoika
 *
 */
public class BPMNCreateEventDefinitionHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = Logger.getLogger(BPMNCreateEventDefinitionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    private String elementTypeId;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateEventDefinitionHandler() {
        super(BPMNModel.BPMN_EVENT_DEFINITIONS);
    }

    /**
     * We expect that the EventDefintion was dropped on a Event. See
     * {@link BPMNDiagramConfiguration} method getShapeTypeHints
     */
    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        String eventID = null;
        elementTypeId = operation.getElementTypeId();
        // now we add this definition directly into the BPMN Event element of the source
        // model
        Optional<GModelElement> container = this.getContainer(operation);

        if (container.isPresent()) {
            Optional<GModelElement> eventElement = modelState.getIndex().get(container.get().getId());
            if (eventElement.isPresent()) {
                eventID = eventElement.get().getId();
                logger.info("===== > event id: " + eventID);
                try {
                    BPMNProcess process = modelState.getBpmnModel().getContext();
                    BPMNEvent bpmnEvent = (BPMNEvent) process.findBaseElementById(eventID);
                    if (bpmnEvent != null) {
                        // add the new definition
                        bpmnEvent.addEventDefinition(elementTypeId);
                    } else {
                        logger.warning("Event " + eventID + " does not exist in current source model!");
                    }
                } catch (BPMNModelException e) {
                    logger.warning("Failed to add new Event Definition: " + e.getMessage());
                }
            }

        }

        modelState.reset();
        if (eventID != null) {
            // select event
            actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(eventID)));
        }
    }

}
