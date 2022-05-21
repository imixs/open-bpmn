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
package org.openbpmn.bpmn.operations;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeRoutingPointsOperation;
import org.eclipse.glsp.server.types.ElementAndRoutingPoints;
import org.openbpmn.bpmn.BPMNGModelState;

import com.google.inject.Inject;

public class BPMNChangeRoutingPointsOperationHandler extends AbstractOperationHandler<ChangeRoutingPointsOperation> {
    private static Logger logger = Logger.getLogger(BPMNChangeRoutingPointsOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void executeOperation(final ChangeRoutingPointsOperation operation) {

        List<ElementAndRoutingPoints> routingPoints = operation.getNewRoutingPoints();
        for (ElementAndRoutingPoints routingPoint : routingPoints) {
            logger.info("...hangeRoutingPoint id: " + routingPoint.getElementId());
        }

    }

}