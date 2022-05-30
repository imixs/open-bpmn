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
package org.openbpmn.glsp.jsonforms;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * The UISchemaBuilder can be used to define a UISchema used by JSONForms
 * <p>
 *
 *
 * @author rsoika
 *
 */
public class UISchemaBuilder {

    public enum Layout {
        HORIZONTAL, VERTICAL, GROUP, CATEGORIZATION
    }

    JsonObjectBuilder rootLayoutBuilder;

    JsonArrayBuilder groupArrayBuilder = null;
    JsonArrayBuilder layoutsArrayBuilder = null;
    JsonObjectBuilder layoutContext = null;
    JsonObjectBuilder groupContext = null;

    public UISchemaBuilder(final Layout layoutType) {

        switch (layoutType) {
        case HORIZONTAL:
            rootLayoutBuilder = Json.createObjectBuilder() //
                    .add("type", "HorizontalLayout");
            layoutContext = rootLayoutBuilder;
            break;
        case CATEGORIZATION:
            rootLayoutBuilder = Json.createObjectBuilder() //
                    .add("type", "Categorization");
            this.groupArrayBuilder = Json.createArrayBuilder();

            break;
        default:
            rootLayoutBuilder = Json.createObjectBuilder() //
                    .add("type", "VerticalLayout");
            layoutContext = rootLayoutBuilder;
        }
    }

    /**
     * Adds a new GroupContext
     *
     * @param label
     * @return this
     */
    public UISchemaBuilder addCategory(final String label) {

        if (groupArrayBuilder == null) {
            throw new IllegalArgumentException("Invalid context - current layout does not support Categories!");
        }

        // close current layoutContext....
        if (layoutsArrayBuilder != null && layoutContext != null) {
            this.layoutsArrayBuilder.add(layoutContext);
        }

        // close current groupContext....
        if (groupContext != null) {
            if (layoutsArrayBuilder != null) {
                groupContext.add("elements", layoutsArrayBuilder.build());
            }
            groupArrayBuilder.add(groupContext);
        }

        if (layoutsArrayBuilder == null) {
            this.layoutsArrayBuilder = Json.createArrayBuilder();
        }

        // create new groupContext
        groupContext = Json.createObjectBuilder(). //
                add("type", "Category"). //
                add("label", label);

        return this;
    }

    public UISchemaBuilder addLayout(final Layout layoutType) {

        // layout context
        switch (layoutType) {
        case HORIZONTAL:
            layoutContext = Json.createObjectBuilder() //
                    .add("type", "HorizontalLayout");
            break;
        default:
            layoutContext = Json.createObjectBuilder() //
                    .add("type", "VerticalLayout");
        }
        return this;
    }

    /**
     * Adds new controles into the current layoutContext.
     *
     * @param controls
     * @param labels   - optional description text
     * @return this
     */
    public UISchemaBuilder addElements(final String[] controls, final String[] labels) {

        if (layoutContext == null) {
            this.addLayout(Layout.VERTICAL);
        }

        layoutContext.add("elements", buildControlElements(controls, labels));

        return this;
    }

    public UISchemaBuilder addElements(final String... controls) {
        return this.addElements(controls, null);
    }

    /**
     * Returns a String with the JSON UISchema
     */
    public String build() {

        if (groupArrayBuilder != null) {

            // close current layoutContext....
            if (layoutsArrayBuilder != null && layoutContext != null) {
                this.layoutsArrayBuilder.add(layoutContext);
            }

            // close current gourpContext
            if (groupContext != null) {
                groupContext.add("elements", layoutsArrayBuilder.build());
                groupArrayBuilder.add(groupContext);
            }

            rootLayoutBuilder.add("elements", groupArrayBuilder.build());
        }

        // write result
        JsonObject jsonObject = rootLayoutBuilder.build();
        String result = null;
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
     *
     * @param descriptions
     */
    private JsonArray buildControlElements(final String[] items, final String[] labels) {

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            JsonObjectBuilder eBuilder = Json.createObjectBuilder(). //
                    add("type", "Control"). //
                    add("scope", "#/properties/" + item);

            // add optional description
            if (labels != null && labels.length > i && !labels[i].isBlank()) {
                eBuilder.add("label", labels[i]);
            }

            arrayBuilder.add(eBuilder);
        }

        JsonArray jsonArray = arrayBuilder.build();
        return jsonArray;

    }

}
