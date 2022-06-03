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
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

/**
 * The EventNodeBuilder defines the layout and properties for all types of BPMN
 * Event elements
 * <p>
 * The radius of the circle symbol is 36. The label is below the symbol.
 * <p>
 * The EventNodeBuilder also creates JSONForms data and schema information.
 *
 * @author rsoika
 *
 */
public class EventNodeBuilder extends AbstractGNodeBuilder<EventNode, EventNodeBuilder> {

    private final String name;
    private final int SYMBOL_OFFSET = 10;

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
        node.getArgs().put("JSONFormsData", buildJSONFormsData());
        // node.getArgs().put("JSONFormsUISchema", buildJSONFormsUISchemaSimple());
        node.getArgs().put("JSONFormsUISchema", buildJSONFormsUISchemaTabs());
        node.getArgs().put("JSONFormsSchema", buildSchema());

        node.setLayout(GConstants.Layout.HBOX);
        size = GraphUtil.dimension(BPMNEvent.DEFAULT_WIDTH, BPMNEvent.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", BPMNEvent.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", BPMNEvent.DEFAULT_HEIGHT);

        node.getChildren().add(BPMNBuilderHelper.createCompartmentIcon(node));

        node.setSymbol("message");
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
                addElements("name", "category", "documentation"). //
                build();

    }

    /**
     * Returns the Data Schema for a event
     *
     * @return
     */
    private String buildSchema() {

        return new SchemaBuilder(). //
                addProperty("name", "string", "Please enter your name :-)"). //
                addProperty("category", "string", null). //
                addProperty("documentation", "string", null). //
                build();

    }

    /**
     * This Helper Method generates a UISchema JSON Object used on the GLSP Client
     * to generate the EMF JsonForms
     * <p>
     * The UI schema, which is passed to JSON Forms, describes the general layout of
     * a form and is just a regular JSON object. It describes the form by means of
     * different UI schema elements, which can often be categorized into either
     * Controls or Layouts.
     *
     * @return
     */
    private String buildJSONFormsUISchemaTabs() {
        String result = "{}";

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        result = new UISchemaBuilder(Layout.CATEGORIZATION). //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
                addCategory("Attributes"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption). //
                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL). //
                build();

        return result;

    }

}
