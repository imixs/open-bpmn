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
package org.openbpmn.glsp.elements.data;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The BPMNCreateTextAnnotationHandler is a GLSP CreateNodeOperation bound to
 * the DiagramModule and called when ever a BPMN TextAnnotation is newly created
 * within the model.
 *
 * @author rsoika
 *
 */
public class BPMNCreateTextAnnotationHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(BPMNCreateGatewayHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds
     */
    public BPMNCreateTextAnnotationHandler() {
        super(BPMNTypes.TEXTANNOTATION);
    }

    @Override
    protected void executeOperation(final CreateNodeOperation operation) {

        // now we add this task into the source model
        String textAnnotationID = BPMNModel.generateShortID("textAnnotation");
        logger.debug("createNode textAnnotationID=" + textAnnotationID);
        try {
            // find the process - either the default process for Root container or the
            // corresponding participant process
            BPMNProcess bpmnProcess = findProcessByCreateNodeOperation(operation);
            TextAnnotation textAnnotation = bpmnProcess.addTextAnnotation(textAnnotationID);
            Optional<GPoint> point = operation.getLocation();
            if (point.isPresent()) {
                double elementX = point.get().getX();
                double elementY = point.get().getY();
                // compute relative center position...
                elementX = elementX - (TextAnnotation.DEFAULT_WIDTH / 2);
                elementY = elementY - (TextAnnotation.DEFAULT_HEIGHT / 2);
                textAnnotation.setPosition(elementX, elementY);
                textAnnotation.setDimension(TextAnnotation.DEFAULT_WIDTH, TextAnnotation.DEFAULT_HEIGHT);
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(textAnnotationID)));
    }

    /**
     * TextAnnotation does not have a label. Return a default name.
     */
    @Override
    public String getLabel() {
        return "Text Annotation";
    }

}
