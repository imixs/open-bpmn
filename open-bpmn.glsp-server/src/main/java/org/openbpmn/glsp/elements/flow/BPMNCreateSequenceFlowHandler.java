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
package org.openbpmn.glsp.elements.flow;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BaseElement;
import org.openbpmn.glsp.elements.CreateBPMNEdgeOperationHandler;

import com.google.inject.Inject;

public class BPMNCreateSequenceFlowHandler extends CreateBPMNEdgeOperationHandler {

    protected final String label;
    private static Logger logger = Logger.getLogger(BPMNCreateSequenceFlowHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateSequenceFlowHandler() {
        super(BPMNTypes.SEQUENCE_FLOW);
        this.label = "Sequence Flow";
    }

    @Override
    protected void executeOperation(final CreateEdgeOperation operation) {
        if (operation.getSourceElementId() == null || operation.getTargetElementId() == null) {
            throw new IllegalArgumentException("Incomplete create connection action");
        }
        BPMNProcess process = modelState.getBpmnModel().getContext();
        try {
            Optional<BaseElement> element = null;
            String targetId = operation.getTargetElementId();
            // find GNode
            element = modelState.getIndex().findElementByClass(targetId, BaseElement.class);
            if (element.isPresent()) {
                targetId = element.get().getId();
            }

            String sourceId = operation.getSourceElementId();
            // find GNode
            element = modelState.getIndex().findElementByClass(sourceId, BaseElement.class);
            if (element.isPresent()) {
                sourceId = element.get().getId();
            }
            process.buildSequenceFlow(BPMNModel.generateShortID("SequenceFlow"), sourceId, targetId);

            modelState.reset();
        } catch (BPMNModelException e) {
            logger.severe(e.getMessage());
        }

    }

    @Override
    public String getLabel() {
        return label;
    }
}
