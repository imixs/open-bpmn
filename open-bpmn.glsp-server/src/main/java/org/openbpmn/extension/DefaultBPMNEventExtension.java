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
package org.openbpmn.extension;

import java.util.HashMap;
import java.util.Map;

import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNEventExtension implements BPMNExtension {

    public DefaultBPMNEventExtension() {
        super();
    }

    @Override
    public String getUISchema(final BPMNBaseElement element) {
        System.out.println(" ..................OOOHHH wir sind in einer Imixs Extennsion");
        return null;
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void addFormsData(final DataBuilder dataBuilder, final BPMNEvent bpmnEvent) {

        dataBuilder //
                .addData("name", bpmnEvent.getName()) //
                .addData("category", "some cati") //
                .addData("documentation", "some test docu");
    }

    /**
     * Adds the default JSONForms schema for a BPMNEvent.
     *
     */
    @Override
    public void addSchema(final SchemaBuilder schemaBuilder, final BPMNEvent event) {

        schemaBuilder. //
                addProperty("name", "string", "Please enter your name :-)"). //
                addProperty("category", "string", null). //
                addProperty("documentation", "string", null);

    }

    /**
     * This Helper Method generates the default UISchema for a BPMNEvent
     *
     * @see UISchemaBuilder
     */
    @Override
    public void addCategories(final UISchemaBuilder uiSchemaBuilder, final BPMNEvent event) {

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
                addCategory("Attributes"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption). //
                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL);

    }

}
