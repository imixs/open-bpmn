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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on AutoAlign actions send from the client
 *
 * @author rsoika
 *
 */
public class BPMNAutoAlignOperationHandler extends GModelOperationHandler<BPMNAutoAlignOperation> {

    private static Logger logger = Logger.getLogger(BPMNAutoAlignOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public Optional<Command> createCommand(BPMNAutoAlignOperation operation) {

        return commandOf(() -> {

            logger.finest("Auto align all elements....");
            modelState.setAutoAlign(true);
            Set<Participant> participants = modelState.getBpmnModel().getParticipants();
            for (Participant participant : participants) {
                modelState.getBpmnGridSnapper().snap(participant);
            }

            List<BPMNProcess> processList = modelState.getBpmnModel().getBpmnProcessList();
            for (BPMNProcess process : processList) {
                // snap all elements
                Set<BPMNElementNode> allNodes = process.getAllFlowElementNodes();
                for (BPMNElementNode _node : allNodes) {
                    modelState.getBpmnGridSnapper().snap(_node);
                }
            }

            modelState.reset();
        });
    }

}