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
