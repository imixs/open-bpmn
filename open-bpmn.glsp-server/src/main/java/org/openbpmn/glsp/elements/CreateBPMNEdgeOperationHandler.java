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

import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The CreateBPMNEdgeOperationHandler is used for generating sequence flows
 *
 * @author rsoika
 *
 */
public abstract class CreateBPMNEdgeOperationHandler extends AbstractCreateOperationHandler<CreateEdgeOperation> {

    @Inject
    protected GModelState modelState;

    public CreateBPMNEdgeOperationHandler(final String elementTypeId) {
        super(elementTypeId);
    }

    public CreateBPMNEdgeOperationHandler(final String... elementTypeIds) {
        super(Lists.newArrayList(elementTypeIds));
    }

    public CreateBPMNEdgeOperationHandler(final List<String> handledElementTypeIds) {
        super(handledElementTypeIds);
    }

}
