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
package org.openbpmn.glsp.jsonforms;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * The UISchemaBuilder can be used to define a UISchema used by JSONForms
 * <p>
 * The UI schema, which is passed to JSON Forms, describes the general layout of
 * a form and is just a regular JSON object. It describes the form by means of
 * different UI schema elements, which can often be categorized into either
 * Controls or Layouts.
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
    JsonArrayBuilder controlsArrayBuilder = null;
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

        // close current controllsBuilder....
        if (controlsArrayBuilder != null && layoutContext != null) {
            layoutContext.add("elements", controlsArrayBuilder.build());
            controlsArrayBuilder = null;
        }

        // close current layoutContext....
        if (layoutsArrayBuilder != null && layoutContext != null) {
            this.layoutsArrayBuilder.add(layoutContext.build());
            layoutContext = null;
        }

        // close current groupContext....
        if (groupContext != null) {
            if (layoutsArrayBuilder != null) {
                groupContext.add("elements", layoutsArrayBuilder.build());
            }
            groupArrayBuilder.add(groupContext.build());
        }

        // create new layoutsArrayBuilder
        this.layoutsArrayBuilder = Json.createArrayBuilder();

        // create new groupContext
        groupContext = Json.createObjectBuilder(). //
                add("type", "Category"). //
                add("label", label);

        return this;
    }

    public UISchemaBuilder addLayout(final Layout layoutType) {

        // close current controllsBuilder....
        if (controlsArrayBuilder != null && layoutContext != null) {
            layoutContext.add("elements", controlsArrayBuilder.build());
            controlsArrayBuilder = null;
        }

        // close current layoutContext....
        if (layoutsArrayBuilder != null && layoutContext != null) {
            this.layoutsArrayBuilder.add(layoutContext.build());
            layoutContext = null;
        }

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
     * Adds a control into the current layout context
     *
     * The mandatory scope property of a control expects a JSON schema reference
     * value, that defines to which property of the data the control should be bound
     * to.
     *
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
     * @param item  - item name
     * @param label - optional label
     * @return this
     */
    public UISchemaBuilder addElement(final String item, final String label) {
        return this.addElement(item, label, null);
    }

    /**
     * Adds a control into the current layout context
     *
     * @param item    - item name
     * @param label   - optional label
     * @param options - optional options
     * @return this
     */
    public UISchemaBuilder addElement(final String item, final String label, final Map<String, String> options) {

        if (layoutContext == null) {
            // add a default layout context
            this.addLayout(Layout.VERTICAL);
        }

        // do we already have a controlArray builder?
        if (controlsArrayBuilder == null) {
            // create a new one
            controlsArrayBuilder = Json.createArrayBuilder();
        }

        JsonObjectBuilder controlBuilder = Json.createObjectBuilder(). //
                add("type", "Control"). //
                add("scope", "#/properties/" + item);

        // add optional description?
        if (label != null && !label.isBlank()) {
            controlBuilder.add("label", label);

            controlBuilder.add("classNames", "test-abc");
        }

        // add optional options?
        if (options != null && options.size() > 0) {
            JsonObjectBuilder optionsBuilder = Json.createObjectBuilder();

            for (Map.Entry<String, String> entry : options.entrySet()) {
                String sKey = entry.getKey();
                String sVal = entry.getValue();

                // is boolean?
                if ("true".equals(sVal) || "false".equals(sVal)) {
                    optionsBuilder.add(sKey, Boolean.parseBoolean(sVal));
                } else {
                    // treat as string
                    optionsBuilder.add(sKey, sVal);
                }
            }

            controlBuilder.add("options", optionsBuilder.build());
        }

        controlsArrayBuilder.add(controlBuilder);

        return this;

    }

    /**
     * Adds a 'detail' layout section into the current layout context. A detail
     * layout can be used for object arrays with complex data structures.
     * <p>
     * The method expects a JsonObject containing the layout details to be embedded.
     * The scope of the referred elements is relative to the object array
     * <p>
     * The result is embedded as a element:
     *
     * <pre>
     * {@code
     * ....
     *  "elements": [
        {
            "type": "Control",
            "scope": "#/properties/conditions",
            "label": "Conditions",
            "options": {
                "detail": {
                    "type": "VerticalLayout",
                    "elements": [
                        {
                            "type": "Control",
                            "scope": "#/properties/language"
                        },
                        {
                            "type": "Control",
                            "scope": "#/properties/expression",
                            "label": "Expression",
                            "options": {
                                "multi": true
                            }
                        }
                    ]
                }
            }
        }
     *  ...
     *
     * }</pre>
     *
     *
     *
     * @param item    - item name
     * @param label   - optional label
     * @param details - JsonObject for details
     * @return this
     */
    public UISchemaBuilder addDetailLayout(final String item, final String label, final JsonObject details) {

        if (layoutContext == null) {
            // add a default layout context
            this.addLayout(Layout.VERTICAL);
        }

        // do we already have a controlArray builder?
        if (controlsArrayBuilder == null) {
            // create a new one
            controlsArrayBuilder = Json.createArrayBuilder();
        }

        JsonObjectBuilder controlBuilder = Json.createObjectBuilder(). //
                add("type", "Control"). //
                add("scope", "#/properties/" + item);

        // add optional description?
        if (label != null && !label.isBlank()) {
            controlBuilder.add("label", label);
        }

        // add optional options?
        if (details != null) {
            controlBuilder.add("options", details);
        }

        controlsArrayBuilder.add(controlBuilder);

        return this;

    }

    /**
     * Adds a list of control elements
     *
     * @param controls
     * @return this
     */
    public UISchemaBuilder addElements(final String... controls) {
        for (String control : controls) {
            this.addElement(control, null, null);
        }
        return this;
    }

    /**
     * Returns a String with the JSON UISchema
     */
    public String build() {

        // close current controllsBuilder....
        if (controlsArrayBuilder != null && layoutContext != null) {
            layoutContext.add("elements", controlsArrayBuilder.build());
        }

        if (groupArrayBuilder != null) {

            // close current layoutContext....
            if (layoutsArrayBuilder != null && layoutContext != null) {
                this.layoutsArrayBuilder.add(layoutContext.build());
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

}
