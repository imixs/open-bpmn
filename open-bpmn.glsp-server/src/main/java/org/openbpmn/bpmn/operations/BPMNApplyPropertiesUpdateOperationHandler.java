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

import java.io.StringReader;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.glsp.bpmn.EventNode;

import com.google.inject.Inject;

/**
 * The BPMNApplyPropertiesUpdateOperationHandler is responsible for processing
 * the {@link BPMNApplyPropertiesUpdateOperation} send by the client for updates
 * of the element properties
 *
 */
public class BPMNApplyPropertiesUpdateOperationHandler
        extends AbstractOperationHandler<BPMNApplyPropertiesUpdateOperation> {
    private static Logger logger = Logger.getLogger(BPMNApplyPropertiesUpdateOperationHandler.class.getName());

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNExtension> extensions;

    /**
     *
     */
    @Override
    protected void executeOperation(final BPMNApplyPropertiesUpdateOperation operation) {
        long l = System.currentTimeMillis();
        logger.info("....execute UpdateEvent Operation id: " + operation.getId());
        String jsonData = operation.getJsonData();
        logger.info("....data= " + jsonData);

        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }

        // find the corresponding BPMN element
        BPMNBaseElement bpmnElement = modelState.getBpmnModel().getContext().findBaseElementById(operation.getId());
        if (bpmnElement == null) {
            throw new IllegalArgumentException(
                    "BPMN Element with id " + operation.getId() + " is not defined in current model!");
        }
        // parse json....
        JsonObject json = null;

        try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
            json = reader.readObject();
        } catch (JsonException e) {
            throw new RuntimeException("Cannot read json data : " + e.getMessage());
        }

        // The Name Feature for Events and Gateways is handled separately
        if (BPMNModel.isEvent(bpmnElement) || BPMNModel.isGateway(bpmnElement)) {
            // get name....
            String nameValue = json.getString("name");
            // did the name has changed?
            if (nameValue != null && !nameValue.equals(bpmnElement.getName())) {
                // now we update the GLabel associated with the Event directly.
                Optional<GLabel> label = modelState.getIndex().findElementByClass(operation.getId() + "_bpmnlabel",
                        GLabel.class);
                if (!label.isEmpty()) {
                    label.get().setText(nameValue);
                }

            }
        }

        // Now call the extensions to update the property data according to the BPMN
        // element
        if (extensions != null) {
            for (BPMNExtension extension : extensions) {
                // validate if the extension can handle this BPMN element
                if (extension.handles(bpmnElement)) {
                    extension.updateData(json, bpmnElement);
                }
            }
        }

        logger.info("....execute UpdateEvent took " + (System.currentTimeMillis() - l) + "ms");

        // we do not need to reset the modelState here!
        // modelState.reset();
    }

}
