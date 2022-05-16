/********************************************************************************
 * Copyright (c) 2021-2022 EclipseSource and others.
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
package org.openbpmn.glsp.elements.pool;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.utils.ModelTypes;

public class CreatePoolHandler extends CreateBPMNNodeOperationHandler {
    private static Logger logger = Logger.getLogger(CreatePoolHandler.class.getName());

    public CreatePoolHandler() {
        super(ModelTypes.POOL);
    }

    protected PoolNodeBuilder builder(final Optional<GPoint> point, final GModelState modelState) {

        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.POOL, "pool", modelState);
        String name = "Pool " + nodeCounter;

        return new PoolNodeBuilder(name) //
                .position(point.orElse(null)).addArguments(GArguments.cornerRadius(5));
    }

    @Override
    public String getLabel() {
        return "Pool";
    }

    @Override
    protected void executeOperation(final CreateNodeOperation operation) {
        // TODO Auto-generated method stub
        logger.warning("NOT YET IMPLEMENTED: CreatePoolHandler.executeOperation");
    }
}
