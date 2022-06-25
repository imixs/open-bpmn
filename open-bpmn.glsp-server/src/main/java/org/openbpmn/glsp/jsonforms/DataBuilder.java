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
import java.util.logging.Logger;

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
public class DataBuilder {

    private static Logger logger = Logger.getLogger(DataBuilder.class.getName());

    JsonObjectBuilder jsonObjectBuilder;
    // arrays
    JsonArrayBuilder objectArrayBuilder;
    JsonObjectBuilder objectArrayItemBuilder;
    String objectName = null;

    public DataBuilder() {

        jsonObjectBuilder = Json.createObjectBuilder();
    }

    /**
     * Adds a new property
     *
     * @param name  - name of the data property
     * @param value - value of the data property
     *
     */
    public DataBuilder addData(final String name, final String value) {

        if (objectArrayBuilder != null) {
            if (objectArrayItemBuilder == null) {
                objectArrayItemBuilder = Json.createObjectBuilder();
            }
            objectArrayItemBuilder.add(name, value);

        } else {
            jsonObjectBuilder.add(name, value);
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
        objectArrayBuilder = Json.createArrayBuilder();
        objectName = name;

        return this;
    }

    /**
     * Adds a new object to an existing objectArrayBuilder
     *
     * @return this
     */
    public DataBuilder addObject() {

        if (objectArrayBuilder != null) {
            if (objectArrayItemBuilder != null) {
                objectArrayBuilder.add(objectArrayItemBuilder);
            }

            objectArrayItemBuilder = Json.createObjectBuilder();
        } else {
            logger.severe("You can not add an object to an array if no array was defined before!");
        }

        return this;
    }

    /**
     * Helper Method to close an open array builder
     */
    private void closeArrayBuilder() {
        if (objectArrayItemBuilder != null) {

            if (objectArrayItemBuilder != null) {
                objectArrayBuilder.add(objectArrayItemBuilder);
            }

            jsonObjectBuilder.add(objectName, objectArrayBuilder);

        }

    }

    /**
     * Returns a String with the JSON Data Schema
     */
    public String build() {
        closeArrayBuilder();
        // write result
        JsonObject jsonObject = jsonObjectBuilder.build();
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
