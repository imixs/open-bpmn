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
package org.openbpmn.glsp.elements.flow;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.server.gmodel.AbstractGModelCreateEdgeOperationHandler;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.openbpmn.glsp.utils.ModelTypes;

public class CreateSequenceFlowHandler extends AbstractGModelCreateEdgeOperationHandler {
    private static Logger logger = Logger.getLogger(CreateSequenceFlowHandler.class.getName());

    public CreateSequenceFlowHandler() {
        super(ModelTypes.SEQUENCE_FLOW, "Sequence Flow");
    }

    @Override
    public void executeOperation(final CreateEdgeOperation operation) {

        super.executeOperation(operation);
        logger.warning("NOT YET IMPLEMENTED: BPMNCreateSequenceFlowHandler.executeOperation()");
    }

    @Override
    protected Optional<GEdge> createEdge(final GModelElement source, final GModelElement target,
            final GModelState modelState) {
        return Optional.of(new SequenceFlowBuilder() //
                .source(source) //
                .target(target) //
                .addArgument(GArguments.edgePadding(10)) //
                .addCssClass("sequenceflow") //
                .routerKind(GConstants.RouterKind.MANHATTAN) //
                .build());
    }
}
