/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
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
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;

import com.google.inject.Inject;

public class BPMNCreateEventHandler extends CreateBPMNNodeOperationHandler { // CreateNodeOperationHandler

    private static Logger logger = Logger.getLogger(BPMNCreateEventHandler.class.getName());

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
    protected GNode createNode(final Optional<GPoint> point, final Map<String, String> args) {
        // no-op, because this class returns a command directly instead of executing the
        // command directly on a model
        return null;
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        elementTypeId = operation.getElementTypeId();
        // We can not call super.execute because of the missing createNode impl!
        super.executeOperation(operation);
        // See: https://github.com/eclipse-glsp/glsp/issues/648

        // now we add this task into the source model
        String eventID = "event-" + BPMNModel.generateShortID();
        logger.fine("===== > createNode eventnodeID=" + eventID);
        BPMNProcess process = modelState.getBpmnModel().getContext();
        BPMNEvent task = process.addEvent(eventID, getLabel(), operation.getElementTypeId());
        Optional<GPoint> point = operation.getLocation();
        if (point.isPresent()) {
            task.getBounds().updateBounds(point.get().getX(), point.get().getY(), 10.0, 10.0);
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(eventID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.EVENT_NODE, elementTypeId, modelState);
        return "Event-" + nodeCounter;
    }

}
