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
