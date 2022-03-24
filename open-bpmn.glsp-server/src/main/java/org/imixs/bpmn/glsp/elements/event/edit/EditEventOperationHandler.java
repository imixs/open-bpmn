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
package org.imixs.bpmn.glsp.elements.event.edit;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.imixs.bpmn.bpmngraph.EventNode;
import org.imixs.bpmn.glsp.utils.ModelTypes;

import com.google.inject.Inject;

public class EditEventOperationHandler extends AbstractOperationHandler<EditEventOperation> {
    private static Logger logger = Logger.getLogger(EditEventOperationHandler.class.getName());

    @Inject
    protected GModelState modelState;

    @Override
    protected void executeOperation(final EditEventOperation operation) {

        logger.info("...execute EditEvent Operation - ID=" + operation.getId());

        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }
        switch (operation.getFeature()) {
        case "name":
            logger.info("...feature = name - value = " + operation.getValue());
            element.get().setName(operation.getValue());

            // Update also the text of the GLabel element
            EList<GModelElement> childs = element.get().getChildren();
            for (GModelElement child : childs) {
                if (ModelTypes.LABEL_HEADING.equals(child.getType())) {
                    GLabel gLabel = (GLabel) child;
                    gLabel.setText(operation.getValue());
                    break;
                }
            }

            break;
        case "documentation":
            element.get().setDocumentation(operation.getValue());
            break;
        default:
            throw new GLSPServerException("Cannot edit element at feature '" + operation.getFeature() + "'");
        }

        logger.info("...execute EditEvent Operation completed");
    }

}
