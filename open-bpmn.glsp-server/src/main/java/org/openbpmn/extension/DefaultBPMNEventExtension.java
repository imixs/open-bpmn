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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNEventExtension extends AbstractBPMNElementExtension {
    private static Logger logger = Logger.getLogger(DefaultBPMNEventExtension.class.getName());

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
    public boolean handlesBPMNElement(final BPMNBaseElement bpmnElement) {
        return (bpmnElement instanceof BPMNEvent);
    }

    @Override
    public int getPriority() {
        return 999999;
    }

    /**
     * This Helper Method generates a JSONForms Object with the BPMNElement
     * properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNBaseElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder. //
                addData("name", bpmnElement.getName()). //
                addData("rating", "3"). //
                addData("documentation", bpmnElement.getDocumentation());

        schemaBuilder. //
                addProperty("name", "string", null). //
                addProperty("rating", "integer", null). //
                addProperty("documentation", "string", null);

        BPMNEvent bpmnEvent = (BPMNEvent) bpmnElement;

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.VERTICAL). //
                addElements("name"). //
                addElements("rating"). //
                addElement("documentation", "Documentation", multilineOption);

        // check Event Definitions
        Map<String, String> arrayDetailOption = new HashMap<>();
        // GENERATED HorizontalLayout
        arrayDetailOption.put("detail", "GENERATED");

        try {
            List<Element> eventDefinitions = bpmnEvent.getEventDefinitions();
            if (eventDefinitions.size() > 0) {
                uiSchemaBuilder. //
                        addCategory("Event"). //
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

                    if ("conditionalEventDefinition".equals(definition.getLocalName())) {
                        dataBuilder.addObject();

                        dataBuilder.addData("formalExpression", "...some expression...");
                        dataBuilder.addData("language", "java");
                        dataBuilder.addData("condition", "blue");
                    }

                }
            }

        } catch (BPMNModelException e) {
            logger.warning("Failed to compute EventDefinitions: " + e.getMessage());
            if (logger.isLoggable(Level.FINE)) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNBaseElement bpmnElement) {

        // default update of name and documentation
        super.updatePropertiesData(json, bpmnElement);

        // check custom features
        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);

            // TODO implement Event features
        }

    }

}
