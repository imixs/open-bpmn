/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
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

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.AbstractCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.CreateNodeOperationHandler;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGraphUtil;
import org.openbpmn.glsp.utils.ModelTypes;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The CreateBPMNNodeOperationHandler is used to detect the node within
 * compartments
 *
 * @author rsoika AbstractGModelCreateNodeOperationHandler
 *         AbstractCreateOperationHandler
 */
public abstract class CreateBPMNNodeOperationHandler extends AbstractCreateOperationHandler<CreateNodeOperation>
        implements CreateNodeOperationHandler {

    @Inject
    protected BPMNGModelState modelState;

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
     * Helper method resolves the containing BPMNProcess for a CreateNodeOperation.
     * This can be either the default process or the process of a Participant
     * (Pool).
     * <p>
     * The method computes the matching process by testing the DropPoint with the
     * dimensions of the existing BPMN Pools (Participants). In case the model is
     * not a collaboration diagram, the method always returns the default process.
     * <p>
     * The reason why we can't use the getContainer method here is because a
     * Participant has the CSS attribute <code>pointer-events: none</code>. So the
     * <code>operation.getContainerId()</code> always returns the root element.
     *
     * @param operation - a CreateNodeOperation
     * @return the corresponding BPMNProcess
     */
    protected BPMNProcess findProcessByCreateNodeOperation(final CreateNodeOperation operation)
            throws BPMNInvalidTypeException {
        if (!modelState.getBpmnModel().isCollaborationDiagram()) {
            return modelState.getBpmnModel().openDefaultProces();
        }
        GPoint dropPoint = operation.getLocation().orElse(null);

        Participant participant = modelState.getBpmnModel()
                .findParticipantByPoint(BPMNGraphUtil.createBPMNPoint(dropPoint));

        if (participant != null) {
            return participant.getBpmnProcess();
        }
        return null;
    }

    /**
     * This method is a helper method to compute the container element. E.g. when a
     * EventDefinition is placed into a Event or when a Extension is placed into a
     * BPMNElementNode. This information is needed by BPMN Model Factory to create
     * the GNode in the correct hierarchy. (see also
     * https://github.com/eclipse-glsp/glsp/discussions/692)
     * <p>
     * The method returns the GModelElement that will contain the newly created
     * node. It is usually the target element
     * ({@link CreateNodeOperation#getContainerId()}), but could also be e.g. an
     * intermediate compartment, or even a different Node.
     * </p>
     *
     * @param operation
     * @return the GModelElement that will contain the newly created node.
     */
    protected Optional<GModelElement> getContainer(final CreateNodeOperation operation) {
        Optional<GModelElement> container = modelState.getIndex().get(operation.getContainerId());
        // If the container is a PoolGNode, find its structure compartment
        Optional<GModelElement> containerCompt = container.filter(PoolGNode.class::isInstance)
                .map(PoolGNode.class::cast).flatMap(this::getPoolCompartment);
        return containerCompt.isPresent() ? containerCompt : container;
    }

    protected Optional<GCompartment> getPoolCompartment(final PoolGNode category) {
        return category.getChildren().stream().filter(GCompartment.class::isInstance).map(GCompartment.class::cast)
                .filter(comp -> ModelTypes.CONTAINER.equals(comp.getType())).findFirst();
    }

}
