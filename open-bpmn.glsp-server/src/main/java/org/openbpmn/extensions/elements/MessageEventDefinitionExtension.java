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
package org.openbpmn.extensions.elements;

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
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.extensions.AbstractBPMNElementExtension;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;

/**
 * The MessageEventDefinitionExtension is responsible to read and update
 * optional
 * MessageEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Messages' shown a list of all
 * MessageEventDefinitions defined in an Event.
 * 
 * @author rsoika
 */
public class MessageEventDefinitionExtension extends AbstractBPMNElementExtension {

    private static Logger logger = LogManager.getLogger(MessageEventDefinitionExtension.class);

    @Override
    public int getPriority() {
        return 105;
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
     * Adds the MessageEvent definitions
     * <p>
     * Note: Internally we need a mapping between the Message name (Label) and the
     * Message id (value). This can be done with the Imixs selectitem renderer using
     * using label|value pairs.
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        Event event = (Event) bpmnElement;
        Set<Element> messageEventDefinitions = event.getEventDefinitionsByType("messageEventDefinition");
        if (messageEventDefinitions.size() > 0) {
            Map<String, String> arrayDetailOption = new HashMap<>();
            arrayDetailOption.put("detail", "GENERATED");

            /***********
             * Data
             *
             * Now we can create the data structure - each messageEventDefinition is
             * represented as a separate object. We resolve the messageRef
             */
            dataBuilder.addArray("messageRefs");
            for (Element definition : messageEventDefinitions) {
                dataBuilder.addObject();
                String messageRefID = definition.getAttribute("messageRef");
                if (!messageRefID.isEmpty()) {
                    dataBuilder.addData("messageRef", messageRefID);
                }
            }

            /***********
             * Schema
             * 
             * providing the label|value pairs for each message defined in the bpmn
             * definitions
             */
            schemaBuilder.addArray("messageRefs");
            Set<Message> bpmnMessages = modelState.getBpmnModel().getMessages();
            String[] messageOptions = new String[bpmnMessages.size() + 1];
            int i = 0;
            // add empty option (default option if no message id is found or defined
            // issue #232
            messageOptions[i] = " - none - |";
            i++;
            for (Message bpmnMessage : bpmnMessages) {
                messageOptions[i] = bpmnMessage.getName() + "|" + bpmnMessage.getId();
                i++;
            }
            schemaBuilder.addProperty("messageRef", "string", "", messageOptions);

            /***********
             * UISchema
             *
             * for message we use the custom renderer for imixs-selectitems
             */
            JsonObject comboBoxOption = Json.createObjectBuilder() //
                    .add("format", "selectitemcombo").build();
            uiSchemaBuilder. //
                    addCategory("Message Objects"). //
                    addLayout(Layout.VERTICAL);
            // create a detail control Layout....
            JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();
            controlsArrayBuilder //
                    .add(Json.createObjectBuilder() //
                            .add("type", "Control") //
                            .add("scope", "#/properties/messageRef")//
                            .add("options", comboBoxOption) //
                    );
            JsonObjectBuilder detailLayoutBuilder = Json.createObjectBuilder(). //
                    add("type", "VerticalLayout"). ///
                    add("elements", controlsArrayBuilder);
            JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                    add("detail", detailLayoutBuilder.build());
            uiSchemaBuilder.addDetailLayout("messageRefs", "Message Object", detailBuilder.build());

        }

    }

    /**
     * This method updates the messageEventDefinitions. With the help of
     * the imixs selectItem holding label|value pairs we can update the id directly.
     * 
     */
    @Override
    public boolean updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        boolean updateClient = false;
        // we are only interested in category messages
        if ("Message Objects".equals(category)) {

            Event bpmnEvent = (Event) bpmnElement;
            Set<Element> oldDefinitionList = bpmnEvent.getEventDefinitionsByType("messageEventDefinition");

            JsonArray dataList = json.getJsonArray("messageRefs");
            // synchronize the definition list of the event element
            Set<Element> messageEventDefinitions = synchronizeEventDefinitions("messageEventDefinition", bpmnEvent,
                    dataList);
            if (oldDefinitionList.size() != messageEventDefinitions.size()) {
                updateClient = true;
            }
            // now we can update the values
            Iterator<Element> iter = messageEventDefinitions.iterator();
            int i = 0;
            while (iter.hasNext()) {
                Element eventDefinitionElement = iter.next();
                JsonObject jsonData = dataList.getJsonObject(i);
                if (jsonData != null) {
                    String oldRef = eventDefinitionElement.getAttribute("messageRef");
                    String newRef = jsonData.getString("messageRef", "");
                    eventDefinitionElement.setAttribute("messageRef", newRef);
                    if (!newRef.equals(oldRef)) {
                        // reset validation and update the client view
                        updateClient = true;
                    }
                }
                i++;
                // update completed
            }

        }

        if (updateClient) {
            bpmnElement.resetValidation();
            modelState.refreshGModelState();

        }
        return updateClient;

    }
}
