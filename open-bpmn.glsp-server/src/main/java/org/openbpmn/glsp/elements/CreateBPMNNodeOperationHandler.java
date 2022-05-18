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

import java.util.List;
import java.util.Optional;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.glsp.utils.GridSnapper;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The CreateBPMNNodeOperationHandler is used to detect the node within
 * compartments
 *
 * @author rsoika
 *
 */
public abstract class CreateBPMNNodeOperationHandler extends AbstractCreateOperationHandler<CreateNodeOperation> {

    @Inject
    protected GModelState modelState;

    public CreateBPMNNodeOperationHandler(final String elementTypeId) {
        super(elementTypeId);
    }

    public CreateBPMNNodeOperationHandler(final String... elementTypeIds) {
        super(Lists.newArrayList(elementTypeIds));
    }

    public CreateBPMNNodeOperationHandler(final List<String> handledElementTypeIds) {
        super(handledElementTypeIds);
    }

    /**
     * Return the absolute location where the element should be created.
     * <p>
     * We use a GirdSnapper to snap into a predefined layout
     *
     * @param operation
     * @return the absolute location where the element should be created.
     */
    public Optional<GPoint> getLocation(final CreateNodeOperation operation) {
        return GridSnapper.snap(operation.getLocation());
    }

    /**
     * <p>
     * Return the GModelElement that will contain the newly created node. It is
     * usually the target element ({@link CreateNodeOperation#getContainerId()}),
     * but could also be e.g. an intermediate compartment, or even a different Node.
     * </p>
     *
     * @param operation
     * @return the GModelElement that will contain the newly created node.
     */
    protected Optional<GModelElement> getContainer(final CreateNodeOperation operation) {
        return modelState.getIndex().get(operation.getContainerId());
    }

}
