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
import java.util.List;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * The DataBuilder can be used to define a data structure for JSONForms
 * <p>
 * The schema of the data is defined by the SchemaBuilder class.
 * <p>
 * The DataBuilder can add simple key/value pairs as also complex data arrays
 *
 * @author rsoika
 *
 */
public class DataBuilder {

    private static Logger logger = Logger.getLogger(DataBuilder.class.getName());

    JsonObjectBuilder rootBuilder;
    // arrays
    JsonArrayBuilder arrayBuilder;
    JsonObjectBuilder arrayObjectBuilder;
    String arrayName = null;

    /**
     * Initialize the root json object builder
     */
    public DataBuilder() {
        rootBuilder = Json.createObjectBuilder();
    }

    /**
     * Adds a new property to the root object.
     * <p>
     * In case an arrayBuilder was created before, the key/value pair is added into
     * the arrayObjectBuilder
     *
     * @param name  - name of the data property
     * @param value - value of the data property
     *
     */
    public DataBuilder addData(final String name, final String value) {
        if (arrayBuilder != null) {
            if (arrayObjectBuilder == null) {
                arrayObjectBuilder = Json.createObjectBuilder();
            }
            arrayObjectBuilder.add(name, value);
        } else {
            rootBuilder.add(name, value);
        }
        return this;
    }

    public DataBuilder addData(final String name, final boolean value) {
        if (arrayBuilder != null) {
            if (arrayObjectBuilder == null) {
                arrayObjectBuilder = Json.createObjectBuilder();
            }
            arrayObjectBuilder.add(name, value);
        } else {
            rootBuilder.add(name, value);
        }
        return this;
    }

    public DataBuilder addDataList(final String name, final List<String> values) {
        if (arrayBuilder != null) {
            if (arrayObjectBuilder == null) {
                arrayObjectBuilder = Json.createObjectBuilder();
            }

            JsonArrayBuilder jsonValueArray = Json.createArrayBuilder(values);
            arrayObjectBuilder.add(name, jsonValueArray);

        } else {
            JsonArrayBuilder jsonValueArray = Json.createArrayBuilder(values);
            rootBuilder.add(name, jsonValueArray);
        }
        return this;
    }

    /**
     * Adds a new property array type
     *
     * @param name - name of the array property
     * @return this
     */
    public DataBuilder addArray(final String name) {
        // close old array builder if exits
        closeArrayBuilder();
        // create new array builder
        arrayBuilder = Json.createArrayBuilder();
        arrayName = name;
        return this;
    }

    /**
     * Adds a new object to the arrayObjectBuilder
     *
     * @return this
     */
    public DataBuilder addObject() {
        if (arrayBuilder != null) {
            // add an existing arrayItem into the arrayObject
            if (arrayObjectBuilder != null) {
                arrayBuilder.add(arrayObjectBuilder);
            }
            arrayObjectBuilder = Json.createObjectBuilder();
        } else {
            logger.severe("You can not add an object to an array if no array was defined before!");
        }

        return this;
    }

    /**
     * Helper Method to close an open array builder. The method adds an existing
     * arrayObjectBuilder.
     */
    public void closeArrayBuilder() {
        if (arrayObjectBuilder != null) {
            if (arrayObjectBuilder != null) {
                arrayBuilder.add(arrayObjectBuilder.build());
                arrayObjectBuilder = null;

            }
            rootBuilder.add(arrayName, arrayBuilder.build());
            arrayBuilder = null;
        }
    }

    /**
     * Returns a String with the JSON Data Schema
     */
    public String build() {
        closeArrayBuilder();
        // write result
        JsonObject jsonObject = rootBuilder.build();
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
