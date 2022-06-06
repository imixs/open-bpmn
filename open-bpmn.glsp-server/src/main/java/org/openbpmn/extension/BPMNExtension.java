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

import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;

public interface BPMNExtension {

    /**
     * Executes the Extension handler for the given {@link BPMNBaseElement} and
     * returns a JSONForms UIScheam
     *
     * @param element The BPMNBaseElement that should be processed.
     * @return JSONForms UISchema
     */
    String getUISchema(BPMNBaseElement element);

    /**
     * Returns the priority of this action handler. The priority is used to derive
     * the execution order if multiple action handlers should execute the same
     * {@link BPMNBaseElement}. The default priority is `0` and the priority is
     * sorted descending. This means handlers with a priority &gt; 0 are executed
     * before handlers with a default priority and handlers with a priority &lt; 0
     * are executed afterwards.
     *
     * @return the priority as integer.
     */
    default int getPriority() {
        return 0;
    }

    /**
     * This Helper Method generates a JSON Object by adding the corresponding
     * BPMNElement properties.
     * <p>
     * You can add a new data field by calling
     * <p>
     * {@code builder.add("myField","someData")}
     * <p>
     * This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the
     * JsonForms
     *
     */
    void addFormsData(DataBuilder dataBuilder, BPMNEvent bpmnEvent);

    /**
     * This Helper Method adds categories to a JSONForms UISchema
     * <p>
     * You can add a new data field by calling
     *
     * <pre>
     * {@code
    uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
    }
     * </pre>
     * <p>
     * This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the
     * JsonForms
     *
     */
    void addCategories(UISchemaBuilder uiSchemaBuilder, BPMNEvent event);

    /**
     * This Helper Method adds new schema information to a JSONForms Schema
     * <p>
     * You can add a new data field by calling
     *
     * <pre>
     * {@code
    schemaBuilder. //
                addProperty("name", "string", "Please enter your name"). //
     *
       }
     * </pre>
     *
     *
     * <p>
     * This SchemaBuilder is used on the BPMNGmodelFactory to generate the JsonForms
     *
     */
    void addSchema(SchemaBuilder schemaBuilder, BPMNEvent event);

}
