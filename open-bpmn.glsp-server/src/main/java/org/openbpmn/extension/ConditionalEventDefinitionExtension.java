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

import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
 * The TimerEventDefinitionExtension is responsible to read and update optional
 * TimerEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Timers' shown a list of all TimerEventDefinitions
 * define in a Event.
 * 
 * @author rsoika
 */
public class ConditionalEventDefinitionExtension extends AbstractBPMNElementExtension {

    @Override
    public int getPriority() {
        return 101;
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

        Set<Element> conditionalEventDefinition = event.getEventDefinitionsByType("conditionalEventDefinition");

        if (conditionalEventDefinition.size() > 0) {
            JsonObject multilineOption = Json.createObjectBuilder() //
                    .add("multi", true).build();
            uiSchemaBuilder. //
                    addCategory("Conditions"). //
                    addLayout(Layout.VERTICAL);

            // create a detail control Layout....
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

            JsonObjectBuilder detailLayoutBuilder = Json.createObjectBuilder(). //
                    add("type", "VerticalLayout"). ///
                    add("elements", controlsArrayBuilder);

            JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                    add("detail", detailLayoutBuilder.build());
            uiSchemaBuilder.addDetailLayout("conditions", "Conditions", detailBuilder.build());

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
            for (Element definition : conditionalEventDefinition) {
                dataBuilder.addObject();
                dataBuilder.addData("language", definition.getAttribute("language"));
                dataBuilder.addData("expression", definition.getAttribute("expression"));
            }

        }
    }

    /**
     * Update the timers definitions
     * 
     * This method updates all timerEventDefinitions. The method expects a
     * dataList containing all timer definitions with its values.
     * The method simply overwrites all timerEventDefinitions.
     * <p>
     * Example:
     * 
     * <pre>
     * {@code
     *  <bpmn2:startEvent id="event_n8bj0g" name="Event-1">
     *    <bpmn2:documentation id="documentation_4XhEKA"/>
     *     <bpmn2:timerEventDefinition id="timerEventDefinition_hElKhw">
     *      <bpmn2:timeDuration id="FormalExpression_0" xsi:type=
    "bpmn2:tFormalExpression">3cc</bpmn2:timeDuration>
     *       </bpmn2:timerEventDefinition>
     *   </bpmn2:startEvent>
     * }
    * </pre>
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category conditions
        if (!"Conditions".equals(category)) {
            return;
        }

        Event bpmnEvent = (Event) bpmnElement;
        JsonArray dataList = json.getJsonArray("conditions");

        // synchronize the definition list of the event element
        Set<Element> conditionalEventDefinitions = synchronizeEventDefinitions("conditionalEventDefinition", bpmnEvent,
                dataList);

        // now we can update the values one by one
        // NOTE: the id can change within the definitionList if an element was deleted
        // or moved!
        // but we do not care about this issue.
        Iterator<Element> iter = conditionalEventDefinitions.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Element eventDefinitionElement = iter.next();
            JsonObject jsonData = dataList.getJsonObject(i); // .get(i);
            if (jsonData != null) {
                eventDefinitionElement.setAttribute("language", jsonData.getString("language", ""));
                eventDefinitionElement.setAttribute("expression", jsonData.getString("expression", ""));
            }
            i++;
            // update completed
        }

    }
}
