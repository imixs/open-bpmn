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
package org.openbpmn.glsp.elements.event;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.BPMNDiagramConfiguration;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to add a new EventDefinition to a BPMN Event node.
 *
 * @author rsoika
 *
 */
public class BPMNCreateEventDefinitionHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(BPMNCreateEventDefinitionHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateEventDefinitionHandler() {
        super(BPMNTypes.BPMN_EVENT_DEFINITIONS);
    }

    /**
     * We expect that the EventDefintion was dropped on a Event. See
     * {@link BPMNDiagramConfiguration} method getShapeTypeHints
     */
    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        String eventID = null;
        String elementTypeId = operation.getElementTypeId();
        // now we add this definition directly into the BPMN Event element of the source
        // model
        Optional<GModelElement> container = this.getContainer(operation);
        if (container.isPresent()) {
            Optional<GModelElement> eventElement = modelState.getIndex().get(container.get().getId());
            if (eventElement.isPresent()) {
                eventID = eventElement.get().getId();
                logger.debug("===== > event id: " + eventID);
                try {
                    // open bpmn event element
                    BPMNElementNode bpmnEvent = modelState.getBpmnModel().findElementNodeById(eventID);
                    if (bpmnEvent != null && bpmnEvent instanceof Event) {
                        // add the new definition
                        ((Event) bpmnEvent).addEventDefinition(elementTypeId);
                    } else {
                        logger.warn("Event " + eventID + " does not exist in current source model!");
                    }
                } catch (BPMNModelException e) {
                    logger.warn("Failed to add new Event Definition: " + e.getMessage());
                }
            }
        }
        modelState.reset();
        if (eventID != null) {
            // select event
            actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(eventID)));
        }
    }

}
