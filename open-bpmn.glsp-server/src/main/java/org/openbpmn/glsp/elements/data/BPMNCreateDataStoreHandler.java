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
package org.openbpmn.glsp.elements.data;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.DataStoreReference;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The BPMNCreateDataStoreHandler is a GLSP CreateNodeOperation bound to the
 * DiagramModule and called when ever a BPMNDataStore is newly created within
 * the model.
 *
 * @author rsoika
 *
 */
public class BPMNCreateDataStoreHandler extends CreateBPMNNodeOperationHandler {

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
    public BPMNCreateDataStoreHandler() {
        super(BPMNTypes.DATASTORE);
    }

    @Override
    public Optional<Command> createCommand(final CreateNodeOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    // @Override
    protected void executeOperation(final CreateNodeOperation operation) {

        elementTypeId = operation.getElementTypeId();
        // now we add this element into the source model
        String dataStoreID = BPMNModel.generateShortID("dataStoreReference");
        logger.debug("createNode dataStoreID=" + dataStoreID);
        try {
            // find the process - either the default process for Root container or the
            // corresponding participant process
            BPMNProcess bpmnProcess = findProcessByCreateNodeOperation(operation);
            DataStoreReference dataStore = bpmnProcess.addDataStoreReference(dataStoreID, getLabel());
            Optional<GPoint> point = operation.getLocation();
            if (point.isPresent()) {
                double elementX = point.get().getX();
                double elementY = point.get().getY();
                // compute relative center position...
                elementX = elementX - (DataStoreReference.DEFAULT_WIDTH / 2);
                elementY = elementY - (DataStoreReference.DEFAULT_HEIGHT / 2);

                dataStore.setPosition(elementX, elementY);
                dataStore.setDimension(DataStoreReference.DEFAULT_WIDTH, DataStoreReference.DEFAULT_HEIGHT);
                // set label bounds

                // set label bounds
                double labelX = elementX + (DataStoreReference.DEFAULT_WIDTH / 2) - (BPMNLabel.DEFAULT_WIDTH / 2);
                double labelY = elementY + DataStoreReference.DEFAULT_HEIGHT + DataStoreReference.LABEL_OFFSET;
                logger.debug("new BPMNLabel Position = " + labelX + "," + labelY);
                dataStore.getLabel().updateLocation(labelX, labelY);
                dataStore.getLabel().updateDimension(BPMNLabel.DEFAULT_WIDTH, BPMNLabel.DEFAULT_HEIGHT);

            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(dataStoreID)));
    }

    @Override
    public String getLabel() {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.DATA_STORE_GNODE, elementTypeId, modelState);
        return "Data Store-" + (nodeCounter + 1);
    }

}
