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
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
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
    private static Logger logger = Logger.getLogger(DefaultBPMNEventExtension.class.getName());
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
    public boolean handlesBPMNElement(final BPMNElementNode bpmnElement) {
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
    public void buildPropertiesForm(final BPMNElementNode bpmnElement, final DataBuilder dataBuilder,
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
            Map<String, String> arrayDetailOption = new HashMap<>();
            // GENERATED HorizontalLayout
            arrayDetailOption.put("detail", "GENERATED");

            uiSchemaBuilder. //
                    addCategory("Conditions"). //
                    addLayout(Layout.VERTICAL);
            uiSchemaBuilder.addElement("conditions", "Conditions", arrayDetailOption);
            // uiSchemaBuilder.addElement("formalExpression", "Script", multilineOption);

            schemaBuilder.addArray("conditions");
            schemaBuilder.addProperty("formalExpression", "string", null, null);
            schemaBuilder.addProperty("language", "string", null, null);
            schemaBuilder.addProperty("condition", "string", null, null);

            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("conditions");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("formalExpression", "...some expression...");
                dataBuilder.addData("language", "java");
                dataBuilder.addData("condition", "blue");
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
            schemaBuilder.addProperty("Signal", "string", null, null);

            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("signals");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("signal", "org.imixs.foo...");
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
            // uiSchemaBuilder.addElement("formalExpression", "Script", multilineOption);

            schemaBuilder.addArray("links");
            schemaBuilder.addProperty("Name", "string", null, null);
            schemaBuilder.addProperty("Target", "string", null, null);

            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("links");
            logger.warning(" addLinkEventDefinitions not yet implemented");
            for (Element definition : eventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("name", "MY_LINK");
                dataBuilder.addData("target", "some target");
            }
        }

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElementNode bpmnElement,
            final GModelElement gNodeElement) {

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

            logger.fine("...update feature = " + feature);

            // TODO implement Event features
        }

    }

}
