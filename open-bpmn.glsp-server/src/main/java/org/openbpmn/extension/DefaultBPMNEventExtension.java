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
import org.w3c.dom.NodeList;

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
            Node docElement = elementList.get(0);
            String value = docElement.getFirstChild().getNodeValue();
            dataBuilder.addData("documentation", value);
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
                    Node documentationElement = elementList.get(0);
                    // remove old
                    NodeList docChildList = documentationElement.getChildNodes();
                    for (int i = 0; i < docChildList.getLength(); i++) {
                        Node child = docChildList.item(i);
                        documentationElement.removeChild(child);
                    }
                    documentationElement.appendChild(cdata);
                    // elementList.get(0).set.setNodeValue(cdata);
                }

                continue;
            }

        }

    }
}
