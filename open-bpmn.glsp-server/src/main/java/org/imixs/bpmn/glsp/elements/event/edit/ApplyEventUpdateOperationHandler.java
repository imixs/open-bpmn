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
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.imixs.bpmn.bpmngraph.EventNode;
import org.imixs.bpmn.glsp.utils.ModelTypes;

import com.google.inject.Inject;

/**
 * The ApplyEventEditOperationHandler is responsible for processing the
 * ApplyEventEditOperation send by the client and updates the model
 * representation accordingly.
 * <p>
 * Finally the OperationHandler sends out a EventEditOperation
 *
 */
public class ApplyEventUpdateOperationHandler extends AbstractOperationHandler<ApplyEventUpdateOperation> {
    private static Logger logger = Logger.getLogger(ApplyEventUpdateOperationHandler.class.getName());

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected GModelState modelState;

    /**
     *
     */
    @Override
    protected void executeOperation(final ApplyEventUpdateOperation operation) {
        logger.fine("....execute UpdateEvent Operation id: " + operation.getId());
        String expression = operation.getExpression();
        logger.fine("....expression= " + expression);

        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }

        String feature = expression.substring(0, expression.indexOf(':'));
        String value = expression.substring(expression.indexOf(':') + 1);
        logger.fine("...feature = " + feature + " - value = " + value);
        switch (feature) {
        case "name":
            // in case the name was updated, we update the LABEL_HEADING
            // from the element. This will automatically update the name
            // via the EventEditValidator
            // element.get().setName(value);
            logger.fine("....update LABLE_HEANDING..");
            // Update also the text of the GLabel element
            EList<GModelElement> childs = element.get().getChildren();
            for (GModelElement child : childs) {
                if (ModelTypes.LABEL_HEADING.equals(child.getType())) {
                    GLabel gLabel = (GLabel) child;
                    gLabel.setText(value);
                    break;
                }
            }

            break;

        case "documentation":
            element.get().setDocumentation(value);
            break;
        default:
            throw new GLSPServerException("Cannot edit element at feature '" + feature + "'");
        }

    }

}
