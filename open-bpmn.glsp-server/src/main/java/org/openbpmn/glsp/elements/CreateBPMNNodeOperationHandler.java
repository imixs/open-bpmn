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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.operations.AbstractCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.CreateNodeOperationHandler;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
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
    private static Logger logger = LogManager.getLogger(CreateBPMNNodeOperationHandler.class);

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
     * This method is a helper method to compute the container element. E.g. when a
     * Task is placed into a Pool. This information is needed by BPMN Model Factory
     * to create the GNode in the correct hierarchy. (see also
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
        // super.getContainer(operation);
        // If the container is a Category node, find its structure compartment
        Optional<GModelElement> containerCompt = container.filter(PoolGNode.class::isInstance)
                .map(PoolGNode.class::cast).flatMap(this::getPoolCompartment);
        return containerCompt.isPresent() ? containerCompt : container;
    }

    protected Optional<GCompartment> getPoolCompartment(final PoolGNode category) {
        return category.getChildren().stream().filter(GCompartment.class::isInstance).map(GCompartment.class::cast)
                .filter(comp -> ModelTypes.CONTAINER.equals(comp.getType())).findFirst();
    }

    /**
     * Helper method computes the container BPMNProcess for a CreateNodeOperation.
     * The method first computes the GModel container Element. Than the method tests
     * if the container is the Model root or a BPMNPool. In the later case the
     * method computes the BPMNProcess from the corresponding BPMNParticipant.
     *
     * @param operation - a CreateNodeOperation
     * @return the corresponding BPMNProcess
     */
    public BPMNProcess findProcessByCreateNodeOperation(final CreateNodeOperation operation) {
        GModelElement container = getContainer(operation).orElseGet(modelState::getRoot);
        String containerId = container.getId();
        logger.debug("containerId = " + container.getId());

        BPMNProcess bpmnProcess = null;
        // is it the root?
        if (modelState.getRoot().getId().equals(containerId)) {
            bpmnProcess = modelState.getBpmnModel().openDefaultProcess();
        } else {
            // it should be a participant container
            if (containerId.startsWith("participant_")) {
                // compute participant
                String participantID = containerId.substring(0, containerId.lastIndexOf("_"));
                BPMNParticipant bpmnParticipant = modelState.getBpmnModel().findBPMNParticipantById(participantID);
                if (bpmnParticipant != null) {
                    bpmnProcess = bpmnParticipant.openProcess();
                }
            }
        }
        return bpmnProcess;
    }

}
