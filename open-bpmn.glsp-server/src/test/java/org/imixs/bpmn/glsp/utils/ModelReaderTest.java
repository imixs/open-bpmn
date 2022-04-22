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

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.imixs.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test Class to read a .bpmn model....
 *
 * @author rsoika
 *
 */
public class ModelReaderTest {
    private static Logger logger = Logger.getLogger(ModelReaderTest.class.getName());

    private Path workingDir;
    Resource resource = null;

    @Before
    public void init() {
        this.workingDir = Path.of("", "src/test/resources");

        URI uri = URI.createURI("src/test/resources/SampleProcess.bpmn");
        Bpmn2ResourceFactoryImpl resFactory = new Bpmn2ResourceFactoryImpl();
        resource = resFactory.createResource(uri);

    }

    @Test
    @Ignore
    public void testReadModel() {
        // We need this option because all object references in the file are "by ID"
        // instead of the document reference "URI#fragment" form.
        HashMap<Object, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);

        // Load the resource
        try {
            resource.load(options);
            Assert.assertNotNull(resource);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

}
