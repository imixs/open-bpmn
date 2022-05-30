/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.openbpmn.glsp.elements.event;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * The EventNodeBuilder defines the layout for all types of BPMN Event elements
 * <p>
 * The radius of the circle symbol is 36. The label is below the symbol.
 *
 * @author rsoika
 *
 */
public class EventNodeBuilder extends AbstractGNodeBuilder<EventNode, EventNodeBuilder> {

    private final String name;

    public EventNodeBuilder(final String type, final String name) {
        super(type);
        this.name = name;
        this.addCssClass("event");
        this.addCssClass(type);
    }

    @Override
    protected EventNode instantiate() {
        return BpmnFactory.eINSTANCE.createEventNode();
    }

    @Override
    protected EventNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final EventNode node) {
        super.setProperties(node);
        node.setName(name);
        node.setDocumentation("...some documentation....");
        node.getArgs().put("JSONFormsData", buildJSONFormsData());
        // node.getArgs().put("JSONFormsUISchema", buildJSONFormsUISchemaSimple());
        node.getArgs().put("JSONFormsUISchema", buildJSONFormsUISchemaTabs());
        node.getArgs().put("JSONFormsSchema", buildSchema());

        node.setLayout(GConstants.Layout.HBOX);
        size = GraphUtil.dimension(BPMNEvent.DEFAULT_WIDTH, BPMNEvent.DEFAULT_HEIGHT);
        node.setSize(size);
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     *
     * @return
     */
    private String buildJSONFormsData() {
        String result = "{}";
        JsonObject jsonObject = Json.createObjectBuilder() //
                .add("name", name) //
                .add("category", "some cati") //
                .add("documentation", "some test docu") //
                .build();

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            result = writer.toString();
        } catch (IOException e) {
            result = "{}";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This Helper Method generates a UISchema JSON Object used on the GLSP Client
     * to generate the EMF JsonForms
     *
     * @return
     */
    private String buildJSONFormsUISchemaSimple() {

        return new UISchemaBuilder(Layout.VERTICAL). //
                addElements(new String[] { "name", "category", "documentation" },
                        new String[] { "", "", "please enter a description" })
                . //
                build();

    }

    private String buildSchema() {
        String result = "{\n" + "  \"type\": \"object\",\n" + "  \"properties\": {\n" + "    \"name\": {\n"
                + "      \"type\": \"string\",\n" + "      \"minLength\": 3,\n"
                + "      \"description\": \"Please enter your name\"\n" + "    },\n" + "    \"category\": {\n"
                + "      \"type\": \"string\"\n" + "    },\n" + "    \"documentation\": {\n"
                + "      \"type\": \"string\"\n" + "    }\n" + "  }\n" + "}";

        return result;
    }

    /**
     * This Helper Method generates a UISchema JSON Object used on the GLSP Client
     * to generate the EMF JsonForms
     *
     * @return
     */
    private String buildJSONFormsUISchemaTabs() {
        String result = "{}";

        result = new UISchemaBuilder(Layout.CATEGORIZATION). //
                addCategory("Basic Information"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL). //
                addElements(new String[] { "documentation" }, new String[] { "please enter a description" }). //
                build();

        return result;

    }

    /**
     * <pre>
     * {@code
     *    "elements": [
     *        { "type": "Control",
     *          "scope": "#/properties/firstName"
     *        },
     *        { "type": "Control",
     *          "scope": "#/properties/secondName"
     *        }
     *       ]
     * }
     * </pre>
     */
    private JsonArray buildControlElements(final String... items) {

        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (String item : items) {
            builder.add(Json.createObjectBuilder(). //
                    add("type", "Control"). //
                    add("scope", "#/properties/" + item));
        }

        JsonArray value = builder.build();

        return value;

    }
}
