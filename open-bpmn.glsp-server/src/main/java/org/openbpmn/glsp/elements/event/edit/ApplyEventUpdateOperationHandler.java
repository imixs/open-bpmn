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
package org.openbpmn.glsp.elements.event.edit;

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
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.bpmn.EventNode;

import com.google.inject.Inject;

/**
 * The ApplyEventEditOperationHandler is responsible for processing the
 * ApplyEventEditOperation send by the client and updates the model
 * representation accordingly.
 * <p>
 * Finally the OperationHandler sends out a EventEditOperation
 *
 */
public class ApplyEventUpdateOperationHandler extends AbstractOperationHandler<ApplyEventUpdateOperation> {
    private static Logger logger = Logger.getLogger(ApplyEventUpdateOperationHandler.class.getName());

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected BPMNGModelState modelState;

    /**
     *
     */
    @Override
    protected void executeOperation(final ApplyEventUpdateOperation operation) {
        long l = System.currentTimeMillis();
        logger.fine("....execute UpdateEvent Operation id: " + operation.getId());
        String jsonData = operation.getJsonData();
        logger.fine("....expression= " + jsonData);

        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }

        // find the corresponding BPMN element
        BPMNEvent bpmnEvent = (BPMNEvent) modelState.getBpmnModel().getContext().findBaseElementById(operation.getId());
        if (bpmnEvent == null) {
            throw new IllegalArgumentException(
                    "BPMN Event with id " + operation.getId() + " is not defined in current model!");
        }
        // parse json....
        JsonObject json = null;

        try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
            json = reader.readObject();
        } catch (JsonException e) {
            throw new RuntimeException("Cannot read json data : " + e.getMessage());
        }

        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {

            logger.fine("...update feature = " + feature);
            if ("name".equals(feature)) {
                value = json.getString(feature);
                bpmnEvent.setName(value);

                // now we update the GLabel associated with the Event directly.
                Optional<GLabel> label = modelState.getIndex().findElementByClass(operation.getId() + "_bpmnlabel",
                        GLabel.class);
                if (!label.isEmpty()) {
                    label.get().setText(value);
                }

                continue;
            }

        }

        logger.info("....execute UpdateEvent took " + (System.currentTimeMillis() - l) + "ms");

        // we do not need to reset the modelState here!
        // modelState.reset();
    }

}
