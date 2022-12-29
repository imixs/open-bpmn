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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Signal;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;
import org.w3c.dom.Element;

import com.google.inject.Inject;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNEventExtension extends AbstractBPMNElementExtension {
    private static Logger logger = LogManager.getLogger(DefaultBPMNEventExtension.class);

    @Inject
    protected BPMNGModelState modelState;

    public DefaultBPMNEventExtension() {
        super();
    }

    /**
     * Returns if this Extension can be applied to the given elementTypeID
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNModel.BPMN_EVENTS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNEvents only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Event);
    }

    /**
     * This Helper Method generates a JSONForms Object with the BPMNElement
     * properties.
     * <p>
     * This JSON object is used on the GLSP Client to generate the EMF JsonForms
     * <p>
     * The method iterates over all eventDefinitions and adds a separate tab
     * including definitions of this type. Note: an event can have multiple
     * definitions of the same type.
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder. //
                addData("name", bpmnElement.getName()). //
                addData("documentation", bpmnElement.getDocumentation());

        schemaBuilder. //
                addProperty("name", "string", null). //
                addProperty("documentation", "string", null);

        Event bpmnEvent = (Event) bpmnElement;

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        Map<String, String> radioOption = new HashMap<>();
        radioOption.put("format", "radio");

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.VERTICAL). //
                addElements("name"). //
                addElement("documentation", "Documentation", multilineOption);

        Set<Element> eventDefinitions = bpmnEvent.getEventDefinitions();
        // Conditional
        List<Element> conditionalEventDefinitions = eventDefinitions.stream()
                .filter(c -> "conditionalEventDefinition".equals(c.getLocalName())).collect(Collectors.toList());
        addConditionalEventDefinitions(conditionalEventDefinitions, dataBuilder, schemaBuilder, uiSchemaBuilder);

        // Signal
        List<Element> signalEventDefinitions = eventDefinitions.stream()
                .filter(c -> "signalEventDefinition".equals(c.getLocalName())).collect(Collectors.toList());
        addSignalEventDefinitions(signalEventDefinitions, dataBuilder, schemaBuilder, uiSchemaBuilder);

        // Link
        List<Element> linkEventDefinitions = eventDefinitions.stream()
                .filter(c -> "linkEventDefinition".equals(c.getLocalName())).collect(Collectors.toList());
        addLinkEventDefinitions(linkEventDefinitions, dataBuilder, schemaBuilder, uiSchemaBuilder);

    }

    /**
     * Builds the different property tabs for the core attributes and the event
     * definitions
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        Event bpmnEvent = (Event) bpmnElement;
        Set<String> features = json.keySet();
        for (String feature : features) {

            if ("name".equals(feature)) {
                bpmnElement.setName(json.getString(feature));
                // Update Label...
                Optional<GModelElement> label = modelState.getIndex().get(gNodeElement.getId() + "_bpmnlabel");
                if (!label.isEmpty()) {
                    LabelGNode lgn = (LabelGNode) label.get();

                    GLabel glabel = BPMNBuilderHelper.findCompartmentHeader((lgn));
                    if (glabel != null) {
                        glabel.setText(json.getString(feature));
                    }
                }
                continue;
            }
            if ("documentation".equals(feature)) {
                bpmnElement.setDocumentation(json.getString(feature));
                continue;
            }

            // Update eventDefinitions...
            Set<Element> eventDefinitions = bpmnEvent.getEventDefinitions();
            if ("signals".equals(feature)) {
                JsonArray signalDataList = json.getJsonArray("signals");
                // find all Signal definitions of this event
                List<Element> signalEventDefinitions = eventDefinitions.stream()
                        .filter(c -> "signalEventDefinition".equals(c.getLocalName())).collect(Collectors.toList());
                updateSignalEventDefinitions(signalEventDefinitions, signalDataList);
            }
        }

    }

    private void updateSignalEventDefinitions(final List<Element> eventDefinitions, final JsonArray signalDataList) {
        // If the size of the singnalDataList is not equals the size of the known
        // eventSignalDefinitions we print a warning
        if (eventDefinitions.size() != signalDataList.size()) {
            logger.warn("signalDataList does not match the signalEventDefinition list!");
        }
        // just update the values one by one by reffering to the signalRef id by
        // comparing the name
        for (int i = 0; i < eventDefinitions.size(); i++) {
            Element eventDefinitionElement = eventDefinitions.get(i);
            JsonObject jsonData = signalDataList.getJsonObject(i); // .get(i);
            if (jsonData != null) {
                String signalName = jsonData.getString("signal");
                logger.info("signal=" + signalName);
                try {
                    Signal signal = modelState.getBpmnModel().findSignalByName(signalName);
                    eventDefinitionElement.setAttribute("signalRef", signal.getId());
                } catch (BPMNInvalidReferenceException | BPMNMissingElementException | BPMNInvalidTypeException e) {

                    e.printStackTrace();
                }
            }
            // update completed
        }
    }

    /**
     * Adds the ConditionalEvent definitions
     *
     * @param eventDefinitions
     * @param dataBuilder
     * @param schemaBuilder
     * @param uiSchemaBuilder
     */
    private void addConditionalEventDefinitions(final List<Element> eventDefinitions, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {
        if (eventDefinitions.size() > 0) {

            // build a custom detail UISchema to display fields like expression as a
            // Textarea
            uiSchemaBuilder. //
                    addCategory("Conditions"). //
                    addLayout(Layout.VERTICAL);

            JsonObject multilineOption = Json.createObjectBuilder() //
                    .add("multi", true).build();
            JsonObjectBuilder layoutBuilder = Json.createObjectBuilder().add("type", "VerticalLayout");
            JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();
            controlsArrayBuilder //
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/language"))//
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/expression") //
                            .add("label", "Expression") //
                            .add("options", multilineOption) //
                    );
            layoutBuilder.add("elements", controlsArrayBuilder);
            JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                    add("detail", layoutBuilder.build());

            uiSchemaBuilder.addElementWithOptions("conditions", "Conditions", detailBuilder.build());

            /*
             * Add the Schema ....
             */
            schemaBuilder.addArray("conditions");
            schemaBuilder.addProperty("language", "string", null, null);
            schemaBuilder.addProperty("expression", "string", null, null);

            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("conditions");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("language", "java");
                dataBuilder.addData("expression", "blue");
            }
        }
    }

    /**
     * Adds the SignalEvent definitions
     *
     * @param eventDefinitions
     * @param dataBuilder
     * @param schemaBuilder
     * @param uiSchemaBuilder
     */
    private void addSignalEventDefinitions(final List<Element> eventDefinitions, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {
        if (eventDefinitions.size() > 0) {
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
            // schemaBuilder.addPropertyOneOf("signal", "string", null, signalOptions);

            /*
             * Now we can create the data structure - each signalEventDefinition is
             * represented as a separate object. We resolve the signalRef
             */
            dataBuilder.addArray("signals");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                String signalRefID = definition.getAttribute("signalRef");
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

    /**
     * Adds the LinkEvent definitions
     *
     * @param eventDefinitions
     * @param dataBuilder
     * @param schemaBuilder
     * @param uiSchemaBuilder
     */
    private void addLinkEventDefinitions(final List<Element> eventDefinitions, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {
        if (eventDefinitions.size() > 0) {
            Map<String, String> arrayDetailOption = new HashMap<>();
            // GENERATED HorizontalLayout
            arrayDetailOption.put("detail", "GENERATED");

            uiSchemaBuilder. //
                    addCategory("Link"). //
                    addLayout(Layout.VERTICAL);
            uiSchemaBuilder.addElement("links", "Links", arrayDetailOption);

            schemaBuilder.addArray("links");
            schemaBuilder.addProperty("name", "string", null, null);
            schemaBuilder.addProperty("target", "string", null, null);

            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("links");
            logger.warn(" addLinkEventDefinitions not yet implemented");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("name", definition.getAttribute("name"));
                dataBuilder.addData("target", "some target");
            }
        }

    }

}
