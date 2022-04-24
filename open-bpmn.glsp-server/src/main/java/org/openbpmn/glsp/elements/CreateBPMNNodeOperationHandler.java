/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
package org.openbpmn.glsp.elements;

import java.util.Optional;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.gmodel.CreateNodeOperationHandler;
import org.openbpmn.glsp.graph.Pool;
import org.openbpmn.glsp.utils.GridSnapper;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * The CreateBPMNNodeOperationHandler is used to detect the node within
 * compartments
 *
 * @author rsoika
 *
 */
public abstract class CreateBPMNNodeOperationHandler extends CreateNodeOperationHandler {

    public CreateBPMNNodeOperationHandler(final String elementTypeId) {
        super(elementTypeId);
    }

    /**
     * Return the absolute location where the element should be created.
     * <p>
     * We use a GirdSnapper to snap into a predefined layout
     *
     * @param operation
     * @return the absolute location where the element should be created.
     */
    @Override
    protected Optional<GPoint> getLocation(final CreateNodeOperation operation) {
        return GridSnapper.snap(operation.getLocation());
    }

    /**
     * Find the optional container element the node is part of.
     */
    @Override
    protected Optional<GModelElement> getContainer(final CreateNodeOperation operation) {
        Optional<GModelElement> container = super.getContainer(operation);
        // If the container is a Pool node, find its structure compartment
        Optional<GModelElement> structCompt = container.filter(Pool.class::isInstance).map(Pool.class::cast)
                .flatMap(this::getPoolCompartment);
        return structCompt.isPresent() ? structCompt : container;
    }

    protected Optional<GCompartment> getPoolCompartment(final Pool pool) {
        return pool.getChildren().stream().filter(GCompartment.class::isInstance).map(GCompartment.class::cast)
                .filter(comp -> ModelTypes.STRUCTURE.equals(comp.getType())).findFirst();
    }

}
