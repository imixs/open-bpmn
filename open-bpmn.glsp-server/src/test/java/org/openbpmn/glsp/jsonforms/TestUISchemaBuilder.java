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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.eclipse.glsp.server.model.DefaultGModelState;
import org.junit.jupiter.api.Test;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * Test class to test the UISchemaBuilder used to generate JSONForms uiSchematas
 *
 * @author rsoika
 *
 */
public class TestUISchemaBuilder extends DefaultGModelState {

    private static Logger logger = Logger.getLogger(TestUISchemaBuilder.class.getName());

    @Test
    public void testHorizontalLayout() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.HORIZONTAL);
        builder.addElements(new String[] { "firstName", "lastName", "role" }, null);

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    @Test
    public void testHorizontalLayoutWithLabel() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.HORIZONTAL);
        builder.addElements(new String[] { "firstName", "lastName", "role" }, new String[] { "First Name" });

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    /**
     * Test a simple CATEGORIZATION layout with one tab
     */
    @Test
    public void testCategorization() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
                addLayout(Layout.HORIZONTAL). //
                addElements(new String[] { "firstName", "lastName", "role" });

        String json = builder.build();

        assertNotNull(json);
        // assertTrue(json.contains("#/properties/lastName"));
        logger.info(json);

    }

    @Test
    public void testCategorization2Sections() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
                addLayout(Layout.HORIZONTAL). //
                addElements(new String[] { "color", "rule" }, null). //
                addCategory("Cat-2"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("firstName", "lastName", "role");

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    /**
     * Test the creation of tabs with a missing layout. So we expect the default
     * VerticalLayout
     */
    @Test
    public void testCategorization2SectionsWithoutLayout() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
        // missing Layout
                addElements("color", "rule"). //
                addCategory("Cat-2"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("firstName", "lastName", "role");

        String json = builder.build();

        assertNotNull(json);
        logger.info(json);
        assertTrue(json.contains("VerticalLayout"));

    }
}
