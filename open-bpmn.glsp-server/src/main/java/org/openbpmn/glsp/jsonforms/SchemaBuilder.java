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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * The SchemaBuilder can be used to define a JSONFOrms Schema used by JSONForms
 * <p>
 * The schema defines the data type for each data element. If no schema
 * definition is available, JSONForms will treat the property as a String.
 * <p>
 * The mandatory scope property, which expects a JSON schema reference value,
 * defines to which property of the data the control should be bound to.
 *
 * @author rsoika
 *
 */
public class SchemaBuilder {

    JsonObjectBuilder rootSchemaBuilder;
    JsonObjectBuilder propertiesBuilder;
    // arrays
    JsonObjectBuilder arrayBuilder;
    JsonObjectBuilder arrayItemsBuilder;
    JsonObjectBuilder arrayItemsPropertyBuilder;
    // JsonObjectBuilder propertyArrayItemSubBuilder;
    String arrayName = null;

    public SchemaBuilder() {

        rootSchemaBuilder = Json.createObjectBuilder();

        propertiesBuilder = Json.createObjectBuilder();
    }

    /**
     * Adds a new property
     *
     * @param name        - name of the data property
     * @param type        - field type
     * @param description - optional description
     * @return this
     */
    public SchemaBuilder addProperty(final String name, final String type, final String description) {

        addProperty(name, type, description, null);
        // JsonObjectBuilder typeBuilder = Json.createObjectBuilder(). //
        // add("type", type);
        // if (description != null && !description.isBlank()) {
        // typeBuilder.add("description", description);
        // }

        // propertiesBuilder.add(name, typeBuilder);

        return this;
    }

    /**
     * Adds a new property
     *
     * @param name        - name of the data property
     * @param type        - field type
     * @param description - optional description
     * @param options     - optional option list
     * @return this
     */
    public SchemaBuilder addProperty(final String name, final String type, final String description,
            final String[] options) {

        JsonObjectBuilder typeBuilder = Json.createObjectBuilder(). //
                add("type", type);
        if (description != null && !description.isBlank()) {
            typeBuilder.add("description", description);
        }
        /*
         * Add a enum? <pre> "enum": [ "One", "Two", "Three" ] </pre>
         *
         */
        if (options != null && options.length > 0) {
            JsonArrayBuilder enumArrayBuilder = Json.createArrayBuilder();
            for (String v : options) {
                enumArrayBuilder.add(v);
            }
            typeBuilder.add("enum", enumArrayBuilder.build());
        }

        if (arrayItemsPropertyBuilder != null) {
            arrayItemsPropertyBuilder.add(name, typeBuilder);
        } else {
            propertiesBuilder.add(name, typeBuilder);
        }
        return this;
    }

    /**
     * Adds a new property array type
     *
     * @param name - name of the array property
     * @return this
     */
    public SchemaBuilder addArray(final String name) {

        // close old array builder if exits
        closeArray();

        // create new array builder
        arrayBuilder = Json.createObjectBuilder();
        arrayBuilder.add("type", "array");

        // create new property Item builder
        arrayItemsBuilder = Json.createObjectBuilder();
        arrayItemsBuilder.add("type", "object");

        arrayName = name;
        arrayItemsPropertyBuilder = Json.createObjectBuilder();

        return this;
    }

    /**
     * Helper Method to close an open array builder
     */
    public void closeArray() {
        if (arrayBuilder != null) {
            // arrayObjectBuilder.add("type", "object");
            if (arrayItemsBuilder != null) {

                if (arrayItemsPropertyBuilder != null) {
                    arrayItemsBuilder.add("properties", arrayItemsPropertyBuilder.build());
                    arrayItemsPropertyBuilder = null;
                }
                arrayBuilder.add("items", arrayItemsBuilder.build());
                arrayItemsBuilder = null;
            }
            propertiesBuilder.add(arrayName, arrayBuilder.build());
            arrayBuilder = null;
        }

    }

    // public void closeArray() {
    // if (arrayObjectBuilder != null) {
    // arrayBuilder.add(arrayObjectBuilder.build());
    // arrayObjectBuilder = null;
    // rootBuilder.add(arrayName, arrayBuilder.build());
    // arrayBuilder = null;
    // }
    // }

    /**
     * Returns a String with the JSON UISchema
     */
    public String build() {
        closeArray();

        rootSchemaBuilder.add("type", "object");
        rootSchemaBuilder.add("properties", propertiesBuilder);

        // write result
        JsonObject jsonObject = rootSchemaBuilder.build();
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
