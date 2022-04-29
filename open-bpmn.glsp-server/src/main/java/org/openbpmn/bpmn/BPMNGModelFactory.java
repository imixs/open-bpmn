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

import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.glsp.BPMNDiagramModule;

/**
 * A graph model factory produces a graph model from the model state; typically
 * its contained source model.
 * <p>
 * The responsibility of a {@link GModelFactory} implementation is to define how
 * a {@link GModelState} is to be translated into a {@link GModelRoot} that is
 * sent to the client for rendering. Before a {@link GModelFactory} is invoked,
 * the {@link ModelSourceLoader} has already been executed for loading the
 * source model into the {@link GModelState}. The {@link GModelFactory} then
 * produces the {@link GModelRoot} from the source model in the
 * {@link GModelState}. Implementations of {@link GModelFactory} are usually
 * specific to the type of source model, as they need to understand the source
 * model in order to translate it into a graph model.
 * </p>
 * <p>
 * The graph model factory is invoked after initial load of the source model and
 * after each operation that is applied to the source model by an
 * {@link OperationHandler} in order to update the graph model before sending it
 * to the client for rendering.
 * </p>
 **/
public class BPMNGModelFactory implements GModelFactory {
    private static Logger logger = Logger.getLogger(BPMNDiagramModule.class.getName());

    @Override
    public void createGModel() {
        logger.info("create GModel is called....");
        // TODO Auto-generated method stub

    }

}
