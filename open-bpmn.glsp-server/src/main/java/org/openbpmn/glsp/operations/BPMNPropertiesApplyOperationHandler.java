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

import java.io.StringReader;
import java.util.Optional;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.extensions.BPMNElementExtension;
import org.openbpmn.glsp.bpmn.BPMNGEdge;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.model.BPMNGModelFactory;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The BPMNApplyPropertiesUpdateOperationHandler is responsible for processing
 * the {@link BPMNPropertiesApplyOperation} send by the client for updates
 * of the element properties.
 * 
 * The method delegates the new values to the registered Extensions to update
 * the
 * BPMN model and probably update additional values of the data structure.
 *
 */
public class BPMNPropertiesApplyOperationHandler
        extends GModelOperationHandler<BPMNPropertiesApplyOperation> {
    private static Logger logger = LogManager.getLogger(BPMNPropertiesApplyOperationHandler.class);

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNElementExtension> extensions;

    @Inject
    protected BPMNGModelFactory bpmnGModelFactory;

    @Override
    public Optional<Command> createCommand(BPMNPropertiesApplyOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    /**
     * The BPMNApplyPropertiesUpdateOperation provides the ID of the selected GNode
     * and the new data value as a json structure.
     * 
     * The method delegates the data to the registered BPMNElementExtension to
     * update the BPMN model.
     * 
     * If one extension signals a client update the method will send a
     * BPMNPropertiesUpdateAction
     */
    private void executeOperation(final BPMNPropertiesApplyOperation operation) {
        long l = System.currentTimeMillis();
        boolean clientUpdate = false;
        String jsonData = operation.getJsonData();
        String category = operation.getCategory();

        // reset undo/redo stack!
        modelState.resetRevisions();

        // validate GModel id
        String elementID = operation.getId();
        GModelElement gModelElement = null;
        BPMNElement bpmnElement = null;
        // is the root element selected?
        if (modelState.getRoot().getId().equals(elementID)) {
            gModelElement = modelState.getRoot();
            bpmnElement = modelState.getBpmnModel().openDefaultProcess();
        } else {
            // find the corresponding gModelElement and bpmnElement....
            gModelElement = modelState.getIndex().get(elementID).orElse(null);
            if (gModelElement != null) {
                if (gModelElement instanceof BPMNGNode) {
                    bpmnElement = modelState.getBpmnModel().findElementNodeById(elementID);
                }
                if (gModelElement instanceof BPMNGEdge) {
                    bpmnElement = modelState.getBpmnModel().findElementEdgeById(elementID);
                }
            }
        }

        // validate BPMN element
        if (bpmnElement == null || gModelElement == null) {
            // should never happen
            throw new IllegalArgumentException(
                    "BPMN Element with id " + operation.getId() + " is not defined in current model!");
        }

        // parse json....
        JsonObject json = null;
        String jsonDataClone = String.valueOf(jsonData);
        try (JsonReader reader = Json.createReader(new StringReader(jsonDataClone))) {
            json = reader.readObject();
            // update the JSONFormsData property of the selected element
            // See also issue #164
            gModelElement.getArgs().put("JSONFormsData", json.toString());
        } catch (JsonException e) {
            throw new RuntimeException("Cannot read json data : " + e.getMessage());
        }

        // Now call the extensions to update the BPMN Model according to the data

        if (extensions != null) {
            for (BPMNElementExtension extension : extensions) {
                // validate if the extension can handle this BPMN element
                if (extension.handlesBPMNElement(bpmnElement)) {
                    boolean _update = extension.updatePropertiesData(json, category, bpmnElement, gModelElement);
                    if (clientUpdate == false && _update == true) {
                        clientUpdate = true;
                    }
                }
            }
        }

        // Finally dispatch an BPMNPropertiesUpdateAction event to refresh the
        // property panel. This action is only dispatched in case at least one extension
        // has signaled an update.
        if (clientUpdate) {
            modelState.refreshSelection(gModelElement.getId());
        }
        logger.debug("....execute Update " + operation.getId() + " in " +
                (System.currentTimeMillis() - l) + "ms");

    }

}
