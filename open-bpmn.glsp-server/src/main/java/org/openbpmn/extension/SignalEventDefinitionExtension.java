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

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Signal;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;

/**
 * The LinkEventDefinitionExtension is responsible to read and update optional
 * LinkEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Link Definitions' shown a list of all
 * LinkEventDefinitions
 * define in a Event.
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
     * Signal id (value). As
     * discussed here
     * (https://jsonforms.discourse.group/t/how-to-separate-value-and-label-in-a-combobox/1200)
     * we do not have this feature yet. Currently the efforts seems to be to high to
     * implement a new
     * renderer for JsonForms.
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        Event event = (Event) bpmnElement;

        // Conditional

        Set<Element> signalEventDefinitions = event.getEventDefinitionsByType("signalEventDefinition");

        if (signalEventDefinitions.size() > 0) {
            Map<String, String> arrayDetailOption = new HashMap<>();
            // GENERATED HorizontalLayout
            arrayDetailOption.put("detail", "GENERATED");

            uiSchemaBuilder. //
                    addCategory("Signals"). //
                    addLayout(Layout.VERTICAL);
            uiSchemaBuilder.addElement("signals", "Signals", arrayDetailOption);
            // uiSchemaBuilder.addElement("formalExpression", "Script", multilineOption);

            schemaBuilder.addArray("signals");

            // find all signals in the current model and build an array...
            Set<Signal> bpmnSignals = modelState.getBpmnModel().getSignals();
            String[] signalOptions = new String[bpmnSignals.size()];
            int i = 0;
            for (Signal bpmnSignal : bpmnSignals) {
                signalOptions[i] = bpmnSignal.getName();
                i++;
                // signalOptions[i] = bpmnSignal.getId() + "|" + bpmnSignal.getName();
            }
            schemaBuilder.addProperty("signal", "string", null, signalOptions);

            /*
             * Now we can create the data structure - each signalEventDefinition is
             * represented as a separate object. We resolve the signalRef
             */
            dataBuilder.addArray("signals");
            for (Element definition : signalEventDefinitions) {
                dataBuilder.addObject();
                String signalRefID = definition.getAttribute("signalRef");
                if (!signalRefID.isEmpty()) {
                    // fetch the corresponding Signal
                    Signal bpmnSignal = (Signal) modelState.getBpmnModel().findElementById(signalRefID);
                    if (bpmnSignal != null) {
                        dataBuilder.addData("signal", bpmnSignal.getName());
                    } else {
                        logger.warn("invalid signalRefID found: " + signalRefID);
                    }
                }

            }

        }

    }

    /**
     * This method updates the signalEventDefinitions. The method expects a
     * dataList containing all conditions with its values (including the id).
     * The method simply overwrites all signalEventDefinitions.
     * 
     * @See addSignalEventDefinitions how we map between the signal name and its id.
     * 
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {
        Event bpmnEvent = (Event) bpmnElement;
        JsonArray dataList = json.getJsonArray("signals");

        // synchronize the definition list of the event element
        Set<Element> signalEventDefinitions = synchronizeEventDefinitions("signalEventDefinition", bpmnEvent, dataList);

        // now we can update the values one by referring to the signalRef id by
        // comparing the name
        Iterator<Element> iter = signalEventDefinitions.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Element eventDefinitionElement = iter.next();
            JsonObject jsonData = dataList.getJsonObject(i); // .get(i);
            if (jsonData != null) {

                String signalName = "";

                try {
                    signalName = jsonData.getString("signal");
                } catch (NullPointerException en) {
                    // no name defined!
                }
                logger.debug("signal=" + signalName);
                try {
                    // fetch the signal from teh Model Signal list by name...
                    Signal signal = modelState.getBpmnModel().findSignalByName(signalName);
                    if (signal != null) {
                        eventDefinitionElement.setAttribute("signalRef", signal.getId());
                    } else {
                        // no signal definition found - delete signalRef...
                        eventDefinitionElement.setAttribute("signalRef", "");
                    }
                } catch (BPMNInvalidReferenceException | BPMNMissingElementException | BPMNInvalidTypeException e) {
                    e.printStackTrace();
                }
            }
            i++;
            // update completed
        }

    }
}
