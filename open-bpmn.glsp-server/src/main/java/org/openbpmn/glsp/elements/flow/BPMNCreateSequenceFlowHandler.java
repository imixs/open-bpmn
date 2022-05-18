/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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

import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.glsp.elements.CreateBPMNEdgeOperationHandler;

import com.google.inject.Inject;

public class BPMNCreateSequenceFlowHandler extends CreateBPMNEdgeOperationHandler {

    // public class BPMNDeleteNodeHandler extends
    // AbstractOperationHandler<DeleteOperation> {

    private static Logger logger = Logger.getLogger(BPMNCreateSequenceFlowHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateSequenceFlowHandler() {
        super(BPMNModel.BPMN_SQUENCEFLOWS);
    }

    @Override
    protected void executeOperation(final CreateEdgeOperation operation) {
        // TODO Auto-generated method stub
        logger.warning("NOT YET IMPLEMENTED: BPMNCreateSequenceFlowHandler.executeOperation()");
    }

    /**
     * Default constructor
     * <p>
     */
//    public BPMNCreateSequenceFlowHandler() {
//        super(ModelTypes.SEQUENCE_FLOW, "Sequence Flow");
//    }
//
//    @Override
//    protected Optional<GEdge> createEdge(final GModelElement source, final GModelElement target,
//            final GModelState modelState) {
//        return Optional.of(new SequenceFlowBuilder() //
//                .source(source) //
//                .target(target) //
//                .addArgument(GArguments.edgePadding(10)) //
//                .addCssClass("sequenceflow") //
//
//                .build());
//    }

}
