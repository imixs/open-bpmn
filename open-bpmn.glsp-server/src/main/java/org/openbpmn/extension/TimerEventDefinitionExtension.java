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
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.w3c.dom.Element;

import com.google.inject.Inject;

/**
 * The TimerEventDefinitionExtension is responsible to read and update optional
 * TimerEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Timers' shown a list of all TimerEventDefinitions
 * define in a Event.
 * 
 * @author rsoika
 */
public class TimerEventDefinitionExtension extends AbstractBPMNElementExtension {

    @Inject
    protected BPMNGModelState modelState;

    public TimerEventDefinitionExtension() {
        super();
    }

    @Override
    public int getPriority() {
        return 104; // below default settings from Edge element
    }

    /**
     * Returns if this Extension can be applied to the given elementTypeID
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.BPMN_EVENTS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNEvents only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Event);
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

        // Link
        Set<Element> timerEventDefinitions = event.getEventDefinitionsByType("timerEventDefinition");

        if (timerEventDefinitions.size() > 0) {
            /******************************
             * Create UISchema
             ******************************/
            final JsonObject radioOption = Json.createObjectBuilder() //
                    .add("format", "radio").build();
            uiSchemaBuilder. //
                    addCategory("Timer Definitions"). //
                    addLayout(Layout.VERTICAL);
            // create a detail control Layout....
            JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();
            controlsArrayBuilder //
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/type") //
                            .add("options", radioOption) //
                    ) //
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/value") //
                            .add("label", "Value") //
                    );
            JsonObjectBuilder detailLayoutBuilder = Json.createObjectBuilder(). //
                    add("type", "HorizontalLayout"). ///
                    add("elements", controlsArrayBuilder);
            JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                    add("detail", detailLayoutBuilder.build());
            uiSchemaBuilder.addDetailLayout("timers", "Timer Definition", detailBuilder.build());

            /***************************
             * Create schemaBuilder
             ***************************/
            String[] timerTypes = { "Time/Date", "Interval", "Duration" };
            schemaBuilder.addArray("timers");
            schemaBuilder.addProperty("type", "string", null, timerTypes);
            schemaBuilder.addProperty("value", "string", null, null);

            /**************************
             * Create DataBuilder
             * 
             * Each timerDefinition is represented as a separate object
             **************************/
            dataBuilder.addArray("timers");

            for (Element timerDefinition : timerEventDefinitions) {
                dataBuilder.addObject();
                // test the type of the timer object....
                Element timeDuration = event.getModel().findChildNodeByName(timerDefinition, BPMNNS.BPMN2,
                        "timeDuration");
                Element timeCycle = event.getModel().findChildNodeByName(timerDefinition, BPMNNS.BPMN2, "timeCycle");
                Element timeDate = event.getModel().findChildNodeByName(timerDefinition, BPMNNS.BPMN2, "timeDate");

                String timerValue = "";
                String timerType = "Time/Date"; // default
                if (timeDate != null) {
                    timerType = "Time/Date";
                    timerValue = timeDate.getTextContent();
                }
                if (timeDuration != null) {
                    timerType = "Duration";
                    timerValue = timeDuration.getTextContent();
                }
                if (timeCycle != null) {
                    timerType = "Interval";
                    timerValue = timeCycle.getTextContent();
                }
                dataBuilder.addData("type", timerType);
                dataBuilder.addData("value", timerValue);
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

        // we are only interested in category 'Timer Definitions'
        if (!"Timer Definitions".equals(category)) {
            return;
        }

        Event bpmnEvent = (Event) bpmnElement;
        JsonArray dataList = json.getJsonArray("timers");

        // synchronize the definition list of the event element
        Set<Element> timerEventDefinitions = synchronizeEventDefinitions("timerEventDefinition", bpmnEvent, dataList);

        // now we can update the values one by one
        // NOTE: the id can change within the definitionList if an element was deleted
        // or moved! but we do not care about this issue.
        Iterator<Element> iter = timerEventDefinitions.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Element eventDefinitionElement = iter.next();
            JsonObject jsonData = dataList.getJsonObject(i); // .get(i);
            if (jsonData != null) {
                // remove the old child elements
                while (eventDefinitionElement.hasChildNodes()) {
                    eventDefinitionElement.removeChild(eventDefinitionElement.getFirstChild());
                }
                // depending on the type we need to create a new child element
                Element timerObject = null;
                // { "Time/Date", "Interval", "Duration" };
                String timerType = jsonData.getString("type", "Time/Date");
                if ("Time/Date".equals(timerType)) {
                    timerObject = bpmnEvent.getModel().createElement(BPMNNS.BPMN2, "timeDate");
                }
                if ("Duration".equals(timerType)) {
                    timerObject = bpmnEvent.getModel().createElement(BPMNNS.BPMN2, "timeDuration");
                }
                if ("Interval".equals(timerType)) {
                    timerObject = bpmnEvent.getModel().createElement(BPMNNS.BPMN2, "timeCycle");

                }

                // xsi:type="bpmn2:tFormalExpression" id="FormalExpression_1"
                timerObject.setAttribute("xsi:type", "bpmn2:tFormalExpression");
                timerObject.setAttribute("id", "FormalExpression_" + i);
                timerObject.setTextContent(jsonData.getString("value", ""));

                // finally add the new updated timerObject
                eventDefinitionElement.appendChild(timerObject);

            }
            i++;
            // update completed
        }
    }
}
