/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.extension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNEventExtension implements BPMNExtension {
    private static Logger logger = Logger.getLogger(DefaultBPMNEventExtension.class.getName());

    public DefaultBPMNEventExtension() {
        super();
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void addFormsData(final DataBuilder dataBuilder, final BPMNBaseElement bpmnEvent) {

        dataBuilder //
                .addData("name", bpmnEvent.getName()) //
                .addData("category", "some cati");

        // do we have a docu?
        List<Node> elementList = BPMNModel.findChildNodesByName(bpmnEvent.getElementNode(),
                BPMNNS.BPMN2.prefix + ":documentation");
        if (elementList.size() > 0) {
            // get the first one and update the value only
            dataBuilder.addData("documentation", elementList.get(0).getNodeValue());
        }
    }

    /**
     * Adds the default JSONForms schema for a BPMNEvent.
     *
     */
    @Override
    public void addSchema(final SchemaBuilder schemaBuilder, final BPMNBaseElement bpmnEvent) {

        schemaBuilder. //
                addProperty("name", "string", "Please enter your name :-)"). //
                addProperty("category", "string", null). //
                addProperty("documentation", "string", null);

    }

    /**
     * This Helper Method generates the default UISchema for a BPMNEvent
     *
     * @see UISchemaBuilder
     */
    @Override
    public void addCategories(final UISchemaBuilder uiSchemaBuilder, final BPMNBaseElement bpmnEvent) {

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
                addCategory("Attributes"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption). //
                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL);

    }

    /**
     * This Extension is for BPMNEvents only
     */
    @Override
    public boolean handles(final BPMNBaseElement bpmnElement) {
        return (bpmnElement instanceof BPMNEvent);
    }

    @Override
    public void updateData(final JsonObject json, final BPMNBaseElement bpmnElement) {

        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);
            if ("name".equals(feature)) {
                bpmnElement.setName(value);

                continue;
            }

            if ("documentation".equals(feature)) {
                // <bpmn2:documentation
                // id="Documentation_3"><![CDATA[...]]></bpmn2:documentation>

                List<Node> elementList = BPMNModel.findChildNodesByName(bpmnElement.getElementNode(),
                        BPMNNS.BPMN2.prefix + ":documentation");
                if (elementList.size() == 0) {
                    // create new node
                    Element documentation = bpmnElement.getModel().createElement(BPMNNS.BPMN2, "documentation");
                    documentation.setAttribute("id", BPMNModel.generateShortID("documentation"));

                    CDATASection cdata = bpmnElement.getDoc().createCDATASection(value);
                    documentation.appendChild(cdata);
                    // .setTextContent(value);
                    bpmnElement.getElementNode().appendChild(documentation);
                } else {
                    // get the first one and update the value only
                    CDATASection cdata = bpmnElement.getDoc().createCDATASection(value);
                    elementList.get(0).appendChild(cdata);
                    // elementList.get(0).set.setNodeValue(cdata);
                }

                continue;
            }

        }

    }
}
