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
import org.openbpmn.bpmn.elements.core.BPMNElement;
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
public class LinkEventDefinitionExtension extends AbstractBPMNElementExtension {

    private static Logger logger = LogManager.getLogger(DefaultBPMNSequenceFlowExtension.class);

    @Override
    public int getPriority() {
        return 102;
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
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        Event event = (Event) bpmnElement;

        // Conditional

        Set<Element> linkEventDefinitions = event.getEventDefinitionsByType("linkEventDefinition");

        if (linkEventDefinitions.size() > 0) {

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
            schemaBuilder.closeArray();
            /*
             * Now we can create the data structure - each conditionalEventDefinition is
             * represented as a separate object
             */
            dataBuilder.addArray("links");
            logger.warn(" addLinkEventDefinitions - no full support!");
            for (Element definition : linkEventDefinitions) {
                dataBuilder.addObject();
                dataBuilder.addData("name", definition.getAttribute("name"));
                dataBuilder.addData("target", definition.getAttribute("target"));
            }

        }
    }

    /**
     * Update the link definitions
     * 
     * This method updates all linkEventDefinitions. The method expects a
     * dataList containing all link definitions with its values.
     * The method simply overwrites all linkEventDefinitions.
     * <p>
     * 
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category link
        if (!"Link".equals(category)) {
            return;
        }

        Event bpmnEvent = (Event) bpmnElement;
        JsonArray dataList = json.getJsonArray("links");

        // synchronize the definition list of the event element
        Set<Element> linkEventDefinitions = synchronizeEventDefinitions("linkEventDefinition", bpmnEvent,
                dataList);

        // just update the values one by one by referring to the signalRef id by
        // comparing the name
        Iterator<Element> iter = linkEventDefinitions.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Element eventDefinitionElement = iter.next();
            JsonObject jsonData = dataList.getJsonObject(i); // .get(i);
            if (jsonData != null) {
                eventDefinitionElement.setAttribute("name", jsonData.getString("name", ""));
                eventDefinitionElement.setAttribute("target", jsonData.getString("target", ""));
            }
            i++;
            // update completed
        }

    }
}
