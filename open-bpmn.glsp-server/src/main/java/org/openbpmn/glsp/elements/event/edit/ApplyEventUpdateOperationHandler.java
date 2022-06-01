/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
        // Method[] methods = EventNode.class.getMethods();
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
