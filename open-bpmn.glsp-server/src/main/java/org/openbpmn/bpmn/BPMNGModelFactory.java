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

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.elements.BPMNProcess;

/**
 * The BPMNGModelFactory is responsible to produce a graph model from the BPMN
 * Meta model.
 * <p>
 * The BPMNGModelState holds an instance of the BPMN Meta model which is created
 * by the {@link BPMNSourceModelStorage}
 * <p>
 * The graph model factory is invoked after initial load of the source model and
 * after each operation that is applied to the source model by an
 * {@link OperationHandler} in order to update the graph model before sending it
 * to the client for rendering.
 * </p>
 **/
public class BPMNGModelFactory implements GModelFactory {
    private static Logger logger = Logger.getLogger(BPMNGModelFactory.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void createGModel() {

        if (!modelState.isInitalized()) {

            logger.info("Creating new GModel from BPMN metha model...");
            long l = System.currentTimeMillis();
            GGraph newGModel = null;
            BPMNModel model = modelState.getBpmnModel();
            if (model != null) {
                BPMNProcess process = model.openContext(null);
                if (process != null) {
                    newGModel = BPMNGModelUtil.createGModelFromProcess(process, modelState);
                    // modelState.setRoot(newGModel);
                    // updateRoot can be removed somtime in the future - see
                    // https://github.com/eclipse-glsp/glsp/discussions/615
                    modelState.updateRoot(newGModel);
                    modelState.getRoot().setRevision(-1);
                }
            }
            if (newGModel == null) {
                logger.info("Unable to create model - no processes found - creating an empty model");
                BPMNGModelUtil.createNewEmptyRoot("process_0");
            }

            modelState.setInitalized(true);
            logger.info("===> createGModel took " + (System.currentTimeMillis() - l) + "ms");
        } else {
            logger.info("===> createGModel skipped!");
        }
    }
}
