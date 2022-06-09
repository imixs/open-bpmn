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
import org.openbpmn.glsp.bpmn.BaseElement;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

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
        String jsonData = operation.getJsonData();

        // validate GModel id
        Optional<BaseElement> element = modelState.getIndex().findElementByClass(operation.getId(), BaseElement.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find BaseElement with id '" + operation.getId() + "'");
        }

        // validate BPMN element
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

        // The Name Feature for Tasks, Events and Gateways is handled separately to
        // avoid the need to recompute the full GModel
        String nameValue = json.getString("name");
        // did the name has changed?
        if (nameValue != null && !nameValue.equals(bpmnElement.getName())) {
            // Event or Gateway?
            if (BPMNModel.isEvent(bpmnElement) || BPMNModel.isGateway(bpmnElement)) {
                // update the GLabel associated with the Event directly.
                Optional<GLabel> label = modelState.getIndex().findElementByClass(operation.getId() + "_bpmnlabel",
                        GLabel.class);
                if (!label.isEmpty()) {
                    label.get().setText(nameValue);
                }

            } else if (BPMNModel.isActivity(bpmnElement)) {
                // update the task CompartmentHeader (GLabel)
                GLabel label = BPMNBuilderHelper.findCompartmentHeader(element.get());
                if (label != null) {
                    label.setText(json.getString("name"));
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

        logger.info("....execute Update " + operation.getId() + " in " + (System.currentTimeMillis() - l) + "ms");

        // we do not need to reset the modelState here!
        // modelState.reset();
    }

}
