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
package org.openbpmn.glsp.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.AbstractActionHandler;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SetDirtyStateAction;
import org.eclipse.glsp.server.features.core.model.UpdateModelAction;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.model.BPMNGModelFactory;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on ResetRouting actions send from the client and
 * clears the routing points for one selected sequence flow
 *
 * @author rsoika
 *
 */
public class BPMNResetRoutingActionHandler extends AbstractActionHandler<BPMNResetRoutingAction> {

    private static Logger logger = Logger.getLogger(BPMNResetRoutingActionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected BPMNGModelFactory bpmnGModelFactory;

    @Override
    protected List<Action> executeAction(final BPMNResetRoutingAction actualAction) {

        logger.info("Reset edge id=" + actualAction.getElementIds());

        for (String id : actualAction.getElementIds()) {
            BPMNElement element = modelState.getBpmnModel().findElementById(id);

            // is it a BPMN Edge?
            if (BPMNTypes.isEdge(element)) {
                ((BPMNElementEdge) element).clearWayPoints();
            }

            // If it is a BPMNFlowElement we search for all BPMNEdges assoicated with this
            // element
            if (BPMNTypes.isFlowElementNode(element)) {
                BPMNElementNode flowElement = (BPMNElementNode) element;
                BPMNProcess process = flowElement.getBpmnProcess();
                Set<BPMNElementEdge> _edges = process.findAllBPMNEdgesByElementId(id);
                for (BPMNElementEdge _edge : _edges) {
                    _edge.clearWayPoints();
                }
            }
        }

        modelState.reset();

        // Force a complete refresh of the GModel state....
        modelState.refreshGModelState();
        List<Action> resultAction = new ArrayList<>();
        resultAction.add(new SetDirtyStateAction(true,
                SetDirtyStateAction.Reason.OPERATION));
        resultAction.add(new UpdateModelAction(modelState.getRoot()));
        return resultAction;
    }

}