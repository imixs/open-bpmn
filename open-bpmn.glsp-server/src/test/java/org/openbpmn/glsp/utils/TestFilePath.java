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
package org.openbpmn.glsp.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.eclipse.glsp.server.model.DefaultGModelState;
import org.junit.jupiter.api.Test;

/**
 * Some file path tests
 */
public class TestFilePath extends DefaultGModelState {

    protected static Logger logger = Logger.getLogger(TestFilePath.class.getName());

    /**
     * This test just demonstrates that a file path with spaces can not easily
     * converted into a URI object
     */
    @Test
    public void testURI() {

        String filePath = "file:///My - Data/text.bpmn";
        URI uri = null;
        try {
            uri = new URI(filePath);// throws java.net.URISyntaxException: Illegal character
            assertNotNull(uri);
            logger.info(uri.toString());

        } catch (URISyntaxException e) {
            // e.printStackTrace();
            // fail();
        }

    }

    /**
     * Also this test results in a wrong uri because the file path does not start
     * with a /
     * It is simply spoken not a valid file path!
     */
    @Test
    public void testURI2() {

        String filePath = "file:///My - Data/text.bpmn";
        URI uri = null;

        File file = new File(filePath);
        uri = file.toPath().toUri();

        assertNotNull(uri);
        logger.info(uri.toString());

    }

}
