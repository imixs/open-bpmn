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

    public SchemaBuilder() {

        rootSchemaBuilder = Json.createObjectBuilder();

        propertiesBuilder = Json.createObjectBuilder();
    }

    /**
     * Adds a new property
     *
     * @param name - name of the data property
     * @return this
     */
    public SchemaBuilder addProperty(final String name, final String type, final String description) {

        JsonObjectBuilder typeBuilder = Json.createObjectBuilder(). //
                add("type", type);
        if (description != null && !description.isBlank()) {
            typeBuilder.add("description", description);
        }

        propertiesBuilder.add(name, typeBuilder);

        return this;
    }

    /**
     * Returns a String with the JSON UISchema
     */
    public String build() {

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
