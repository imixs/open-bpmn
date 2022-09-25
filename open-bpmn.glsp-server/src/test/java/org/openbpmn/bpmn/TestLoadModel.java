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
package org.openbpmn.bpmn;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.glsp.graph.GGraph;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.model.BPMNGModelFactory;

/**
 * This test loads a BPMN file and creates a GModel from the first process
 * element with the corresponding task, event and gateway elements.
 *
 * @author rsoika
 *
 */
public class TestLoadModel {

    @Test
    public void testReadFile() throws BPMNModelException {
        BPMNModel model = BPMNModelFactory.read("/bpmn/process_1.bpmn");

        System.out.println("------");
        assertNotNull(model);

        BPMNGModelFactory modelFactory = new BPMNGModelFactory();
        modelFactory.setBpmnModel(model);
        GGraph newGModel = modelFactory.buildGGraph(model);
        assertNotNull(newGModel);
    }

}
