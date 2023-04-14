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
package org.openbpmn.extension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Signal;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;

/**
 * The SignalEventDefinitionExtension is responsible to read and update optional
 * SignalEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Signals' shown a list of all
 * SignalEventDefinitions defined in an Event.
 * 
 * @author rsoika
 */
public class SignalEventDefinitionExtension extends AbstractBPMNElementExtension {

    private static Logger logger = LogManager.getLogger(DefaultBPMNSequenceFlowExtension.class);

    @Override
    public int getPriority() {
        return 103;
    }

    /**
     * This Extension is for BPMNEvents only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Event);
    }

    /**
     * Returns if this Extension can be applied to the given elementTypeID
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.BPMN_EVENTS.contains(elementTypeId);
    }

    /**
     * Adds the SignalEvent definitions
     * <p>
     * Note: Internally we need a mapping between the Signal name (Label) and the
     * Signal id (value). This can be done with the Imixs selectitem renderer using
     * using label|value pairs.
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        Event event = (Event) bpmnElement;
        Set<Element> signalEventDefinitions = event.getEventDefinitionsByType("signalEventDefinition");
        if (signalEventDefinitions.size() > 0) {
            Map<String, String> arrayDetailOption = new HashMap<>();
            arrayDetailOption.put("detail", "GENERATED");

            /***********
             * Data
             *
             * Now we can create the data structure - each signalEventDefinition is
             * represented as a separate object. We resolve the signalRef
             */
            dataBuilder.addArray("signals");
            for (Element definition : signalEventDefinitions) {
                dataBuilder.addObject();
                String signalRefID = definition.getAttribute("signalRef");
                if (!signalRefID.isEmpty()) {
                    dataBuilder.addData("signal", signalRefID);
                }
            }

            /***********
             * Schema
             * 
             * providing the label|value paris for each signal defined in the bpmn
             * definitions
             */
            schemaBuilder.addArray("signals");
            Set<Signal> bpmnSignals = modelState.getBpmnModel().getSignals();
            String[] signalOptions = new String[bpmnSignals.size() + 1];
            int i = 0;
            // add empty option (default option if no signal id is found or defined
            // issue #232
            signalOptions[i] = " - none - |";
            i++;
            for (Signal bpmnSignal : bpmnSignals) {
                // signalOptions[i] = bpmnSignal.getName();
                signalOptions[i] = bpmnSignal.getName() + "|" + bpmnSignal.getId();
                i++;
            }
            schemaBuilder.addProperty("signal", "string", "", signalOptions);

            /***********
             * UISchema
             *
             * for signal we use the custom renderer for imixs-selectitems
             */
            JsonObject comboBoxOption = Json.createObjectBuilder() //
                    .add("format", "selectitemcombo").build();
            uiSchemaBuilder. //
                    addCategory("Signals"). //
                    addLayout(Layout.VERTICAL);
            // create a detail control Layout....
            JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();
            controlsArrayBuilder //
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/signal")//
                            .add("options", comboBoxOption) //
                    );
            JsonObjectBuilder detailLayoutBuilder = Json.createObjectBuilder(). //
                    add("type", "VerticalLayout"). ///
                    add("elements", controlsArrayBuilder);
            JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                    add("detail", detailLayoutBuilder.build());
            uiSchemaBuilder.addDetailLayout("signals", "Signals", detailBuilder.build());

        }

    }

    /**
     * This method updates the signalEventDefinitions. With the help of
     * the imixs selectItem holding label|value pairs we can update the id directly.
     * 
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category signals
        if (!"Signals".equals(category)) {
            return;
        }

        Event bpmnEvent = (Event) bpmnElement;
        JsonArray dataList = json.getJsonArray("signals");
        // synchronize the definition list of the event element
        Set<Element> signalEventDefinitions = synchronizeEventDefinitions("signalEventDefinition", bpmnEvent, dataList);
        // now we can update the values
        Iterator<Element> iter = signalEventDefinitions.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Element eventDefinitionElement = iter.next();
            JsonObject jsonData = dataList.getJsonObject(i);
            if (jsonData != null) {
                eventDefinitionElement.setAttribute("signalRef", jsonData.getString("signal", ""));
            }
            i++;
            // update completed
        }

    }
}
