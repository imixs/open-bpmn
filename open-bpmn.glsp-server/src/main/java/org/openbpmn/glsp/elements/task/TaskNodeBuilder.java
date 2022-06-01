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
package org.openbpmn.glsp.elements.task;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

public class TaskNodeBuilder extends AbstractGNodeBuilder<TaskNode, TaskNodeBuilder> {

    private final String name;

    public TaskNodeBuilder(final String type, final String name) {
        super(type);
        this.name = name;
        this.addCssClass("task");
        this.addCssClass(type);
        this.addArguments(GArguments.cornerRadius(5));

    }

    @Override
    protected TaskNode instantiate() {
        return BpmnFactory.eINSTANCE.createTaskNode();
    }

    @Override
    protected TaskNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final TaskNode node) {
        super.setProperties(node);
        node.setName(name);
        // node.getCategory().add(taskType);
        // node.setDocumentation("...some documentation....");

        node.getArgs().put("JSONFormsData", buildJSONFormsData());
        node.getArgs().put("JSONFormsUISchema", buildJSONFormsUISchemaTabs());
        node.getArgs().put("JSONFormsSchema", buildSchema());

        node.setLayout(GConstants.Layout.HBOX);

        // Set min width/height
        node.getLayoutOptions().put("minWidth", BPMNActivity.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", BPMNActivity.DEFAULT_HEIGHT);

        node.getLayoutOptions().put("hGap", 10);
        node.getLayoutOptions().put("vAlign", "center");

        node.getChildren().add(BPMNBuilderHelper.createCompartmentIcon(node));
        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));

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
     * Returns the Data Schema for a event
     *
     * @return
     */
    private String buildSchema() {

        return new SchemaBuilder(). //
                addProperty("name", "string", "Please enter your name :-)"). //
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
                addLayout(Layout.VERTICAL). //
                addElement("name", null, null). //
                addElement("documentation", "Documentation", multilineOption). //
                build();

        return result;

    }
}
