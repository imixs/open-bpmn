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
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This is the OperationHandler to create all kinds of BPMNEvents. This
 * operationHandler is called when the user adds a new Element from the
 * ToolPalette.
 * <p>
 * The Handler also is aware of BoundaryEvents which are contained in a Task
 * element.
 * <p>
 * Finally the Handler simply extends the SourceModel and reset the state.
 *
 *
 * @author rsoika
 *
 */
public class BPMNCreateEventHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(BPMNCreateEventHandler.class);

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
    public BPMNCreateEventHandler() {
        super(BPMNModel.BPMN_EVENTS);
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        BPMNBaseElement containerElement = null;
        elementTypeId = operation.getElementTypeId();
        // now we add this task into the source model
        String eventID = BPMNModel.generateShortID("event"); // "event-" + BPMNModel.generateShortID();
        logger.debug("createNode eventnodeID=" + eventID);
        try {
            GModelElement container = getContainer(operation).orElseGet(modelState::getRoot);
            String containerId = container.getId();
            logger.debug("containerId = " + container.getId());
            BPMNProcess bpmnProcess = null;
            String attachedToRef = null;
            // Do we have a BoundaryEvent? Than we need to compute the Tasks Process
            if (BPMNTypes.BOUNDARY_EVENT.equals(elementTypeId)) {
                // we assume that the containerId is the Task Element...
                containerElement = modelState.getBpmnModel().findBPMNBaseElementById(containerId);
                if (containerElement != null && containerElement instanceof BPMNActivity) {
                    // it is a BPMNActivity
                    bpmnProcess = ((BPMNActivity) containerElement).getBpmnProcess();
                    attachedToRef = containerElement.getId();
                }
            }
            // did we have yet a process ?
            if (bpmnProcess == null) {
                // find the process - either the default process for Root container or the
                // corresponding participant process
                bpmnProcess = findProcessByCreateNodeOperation(operation);
            }

            BPMNEvent event = bpmnProcess.addEvent(eventID, getLabel(), operation.getElementTypeId());
            // do we have a attachedToRef?
            if (attachedToRef != null && !attachedToRef.isEmpty()) {
                event.setAttribute("attachedToRef", attachedToRef);
            }
            Optional<GPoint> point = operation.getLocation();

            if (point.isPresent()) {
                double elementX = point.get().getX();
                double elementY = point.get().getY();
                // compute relative center position...
                elementX = elementX - (BPMNEvent.DEFAULT_WIDTH / 2);
                elementY = elementY - (BPMNEvent.DEFAULT_HEIGHT / 2);
                // compute default label position
                double labelX = elementX + (BPMNEvent.DEFAULT_WIDTH / 2) - (BPMNLabel.DEFAULT_WIDTH / 2);
                double labelY = elementY + BPMNEvent.DEFAULT_HEIGHT + BPMNEvent.LABEL_OFFSET;

                // in case of a BoundaryEvent we adjust the position to the TaskEdge...
                if (BPMNTypes.BOUNDARY_EVENT.equals(elementTypeId)
                        && (containerElement != null && containerElement instanceof BPMNActivity)) {
                    double taskY = containerElement.getBounds().getPosition().getY();
                    BPMNPoint taskCenterPoint = containerElement.getBounds().getCenter();
                    // Upper bounds?
                    if (elementY + (BPMNEvent.DEFAULT_HEIGHT / 2) < taskCenterPoint.getY()) {
                        elementY = taskY - (BPMNEvent.DEFAULT_HEIGHT / 2);
                        labelY = elementY - BPMNLabel.DEFAULT_HEIGHT + BPMNEvent.LABEL_OFFSET;
                    } else {
                        // lower bounds
                        elementY = taskY + (containerElement.getBounds().getDimension().getHeight()
                                - (BPMNEvent.DEFAULT_HEIGHT / 2));
                        labelY = elementY + BPMNEvent.DEFAULT_HEIGHT + BPMNEvent.LABEL_OFFSET;
                    }
                }
                // set event bounds
                event.getBounds().setPosition(elementX, elementY);
                event.getBounds().setDimension(BPMNEvent.DEFAULT_WIDTH, BPMNEvent.DEFAULT_HEIGHT);
                // set label bounds
                event.getLabel().updateLocation(labelX, labelY);
                event.getLabel().updateDimension(BPMNLabel.DEFAULT_WIDTH, BPMNLabel.DEFAULT_HEIGHT);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(eventID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.EVENT_GNODE, elementTypeId, modelState);
        return "Event-" + (nodeCounter + 1);
    }

}
