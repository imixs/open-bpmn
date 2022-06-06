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
