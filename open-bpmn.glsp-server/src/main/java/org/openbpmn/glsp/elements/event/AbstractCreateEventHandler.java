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
package org.openbpmn.glsp.elements.event;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.gmodel.CreateNodeOperationHandler;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.utils.ModelTypes;

public abstract class AbstractCreateEventHandler extends CreateBPMNNodeOperationHandler {

    private final Function<Integer, String> labelProvider;
    private final String elementTypeId;

    public AbstractCreateEventHandler(final String elementTypeId, final Function<Integer, String> labelProvider) {
        super(elementTypeId);
        this.elementTypeId = elementTypeId;
        this.labelProvider = labelProvider;
    }

    protected String getElementTypeId() {
        return elementTypeId;
    }

    /**
     * Create and return the new GNode at the specified (optional) location. The
     * location is given in coordinates relative to the
     * {@link CreateNodeOperationHandler#getContainer(CreateNodeOperation)}
     * container.
     *
     * @param relativeLocation
     * @param args
     * @return The created {@link GNode Node}.
     */
    @Override
    protected GNode createNode(final Optional<GPoint> point, final Map<String, String> args) {
        return builder(point, modelState).build();
    }

    protected EventNodeBuilder builder(final Optional<GPoint> point, final GModelState modelState) {
        int nodeCounter = GModelUtil.generateId(BpmnPackage.Literals.EVENT_NODE, "event", modelState);
        String name = labelProvider.apply(nodeCounter);
        String eventType = ModelTypes.toNodeType(getElementTypeId());
        return new EventNodeBuilder(getElementTypeId(), name, eventType) //
                .position(point.orElse(null)).addArguments(GArguments.cornerRadius(5)).addCssClass("event");
    }

}
