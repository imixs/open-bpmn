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

import java.util.logging.Logger;

import org.eclipse.glsp.server.model.DefaultGModelState;
import org.junit.jupiter.api.Test;

/**
 * Test class to test the UISchemaBuilder used to generate JSONForms uiSchematas
 *
 * @author rsoika
 *
 */
public class TestSchemaBuilder extends DefaultGModelState {

    private static Logger logger = Logger.getLogger(TestSchemaBuilder.class.getName());

    @Test
    public void testSimple() {

        SchemaBuilder builder = new SchemaBuilder(). //
                addProperty("name", "string", "Please enter your name"). //
                addProperty("description", "string", null);

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

}
