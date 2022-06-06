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
package org.openbpmn.glsp.elements;

import java.util.List;

import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.CreateEdgeOperationHandler;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The CreateBPMNEdgeOperationHandler is used for generating sequence flows
 *
 * @author rsoika
 *
 */
public abstract class CreateBPMNEdgeOperationHandler extends AbstractCreateOperationHandler<CreateEdgeOperation>
        implements CreateEdgeOperationHandler {

//public abstract class AbstractGModelCreateEdgeOperationHandler extends AbstractCreateOperationHandler<CreateEdgeOperation>
//    implements CreateEdgeOperationHandler {

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
