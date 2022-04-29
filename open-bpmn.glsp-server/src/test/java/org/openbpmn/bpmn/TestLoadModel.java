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
package org.openbpmn.bpmn;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GGraph;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.elements.Process;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test loads a BPMN file and creates a GModel from the first process
 * element with the corresponding task, event and gateway elements.
 *
 * @author rsoika
 *
 */
public class TestLoadModel {
    private static Logger logger = Logger.getLogger(TestLoadModel.class.getName());

    @Test
    public void testReadFile() {
        BPMNModel model = BPMNModelFactory.read("/bpmn/process_1.bpmn");

        System.out.println("------");
        // we expect one child
        assertNotNull(model);

        List<Process> processList = model.getProcesList();
        if (processList != null && processList.size() > 0) {
            Process process = processList.get(0);

            GGraph newGModel = BPMNGModelUtil.createGModelFromProcess(process, null);

            assertNotNull(newGModel);
        }
    }

}
