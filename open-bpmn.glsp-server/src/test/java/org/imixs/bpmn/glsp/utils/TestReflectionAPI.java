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
package org.imixs.bpmn.glsp.utils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.imixs.bpmn.bpmngraph.Event;
import org.imixs.bpmn.glsp.elements.event.edit.ApplyEventUpdateOperationHandler;
import org.junit.Assert;
import org.junit.Test;

public class TestReflectionAPI {
    private static Logger logger = Logger.getLogger(TestReflectionAPI.class.getName());

    @Test
    public void testSettersOfEvent() {
        long l = System.currentTimeMillis();

        Method[] methods = Event.class.getMethods();// .getDeclaredMethods();

        // test if setDocumentation(String) exists
        Assert.assertNotNull(ApplyEventUpdateOperationHandler.findSetter(methods, "documentation"));

        logger.info("....testSettersOfEvent took " + (System.currentTimeMillis() - l) + "ms");

    }

}
