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
package org.openbpmn.glsp.elements.gateway;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to create a new gateway.
 *
 * @author rsoika
 *
 */
public class BPMNCreateGatewayHandler extends CreateBPMNNodeOperationHandler { // CreateNodeOperationHandler

    private static Logger logger = LogManager.getLogger(BPMNCreateGatewayHandler.class);

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
    public BPMNCreateGatewayHandler() {
        super(BPMNTypes.BPMN_GATEWAYS);
    }

    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        elementTypeId = operation.getElementTypeId();
        // now we add a new gateway into the source model
        String gatewayID = BPMNModel.generateShortID("gateway");// "gateway-" + BPMNModel.generateShortID();
        logger.debug("createNode gatewaynodeID=" + gatewayID);
        try {
            // find the process - either the default process for Root container or the
            // corresponding participant process
            BPMNProcess bpmnProcess = findProcessByCreateNodeOperation(operation);
            Gateway gateway = bpmnProcess.addGateway(gatewayID, getLabel(), operation.getElementTypeId());
            Optional<GPoint> point = operation.getLocation();
            if (point.isPresent()) {

                double elementX = point.get().getX();
                double elementY = point.get().getY();

                // compute relative center position...
                elementX = elementX - (Gateway.DEFAULT_WIDTH / 2);
                elementY = elementY - (Gateway.DEFAULT_HEIGHT / 2);

                gateway.setPosition(elementX, elementY);
                gateway.setDimension(Gateway.DEFAULT_WIDTH, Gateway.DEFAULT_HEIGHT);
                // set label bounds
                double labelX = elementX + (Gateway.DEFAULT_WIDTH / 2) - (BPMNLabel.DEFAULT_WIDTH / 2);
                double labelY = elementY + Gateway.DEFAULT_HEIGHT + Gateway.LABEL_OFFSET;
                logger.debug("new BPMNLabel Position = " + labelX + "," + labelY);
                gateway.getLabel().updateLocation(labelX, labelY);
                gateway.getLabel().updateDimension(BPMNLabel.DEFAULT_WIDTH, BPMNLabel.DEFAULT_HEIGHT);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(gatewayID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.GATEWAY_GNODE, elementTypeId, modelState);
        nodeCounter++; // start with 1
        return "Gateway-" + nodeCounter;
    }

}
