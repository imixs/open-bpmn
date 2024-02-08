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
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.model.BPMNGModelFactory;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGridSnapper;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on AutoAlign actions send from the client
 *
 * @author rsoika
 *
 */
public class BPMNAutoAlignActionHandler extends AbstractActionHandler<BPMNAutoAlignAction> {

    private static Logger logger = Logger.getLogger(BPMNAutoAlignActionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected BPMNGModelFactory bpmnGModelFactory;

    @Override
    protected List<Action> executeAction(final BPMNAutoAlignAction actualAction) {
        logger.finest("Auto align all elements....");
        Set<Participant> participants = modelState.getBpmnModel().getParticipants();
        for (Participant participant : participants) {
            try {
                BPMNGridSnapper.snap(participant);
            } catch (BPMNMissingElementException e) {
                logger.severe("Unable to update bounds for " + participant.getId() + " : " + e.getMessage());
            }
        }

        Set<BPMNProcess> processList = modelState.getBpmnModel().getProcesses();
        for (BPMNProcess process : processList) {
            try {
                // snap all elements
                Set<BPMNElementNode> allNodes = process.getAllFlowElementNodes();
                for (BPMNElementNode _node : allNodes) {
                    BPMNGridSnapper.snap(_node);
                }
            } catch (BPMNMissingElementException e) {
                logger.severe("Unable to update bounds for " + process.getId() + " : " + e.getMessage());
            }
        }

        // Force a complete refresh of the GModel state....
        modelState.refreshGModelState();
        List<Action> resultAction = new ArrayList<>();
        resultAction.add(new SetDirtyStateAction(true,
                SetDirtyStateAction.Reason.OPERATION));
        resultAction.add(new UpdateModelAction(modelState.getRoot()));
        return resultAction;
    }

}