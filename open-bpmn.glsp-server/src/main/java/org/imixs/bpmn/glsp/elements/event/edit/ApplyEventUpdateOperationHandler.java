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

import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.types.GLSPServerException;

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
        logger.info("....execute UpdateEvent Operation id: " + operation.getId());
        String expression = operation.getExpression();

        logger.info("....expression= " + expression);
        if (expression.startsWith(ApplyEventUpdateOperation.DOCUMENTATION_PREFIX)) {
            String value = expression.substring(ApplyEventUpdateOperation.DOCUMENTATION_PREFIX.length());
            actionDispatcher.dispatch(new EditEventOperation(operation.getId(), "documentation", value));
        } else if (expression.startsWith(ApplyEventUpdateOperation.NAME_PREFIX)) {
            String value = expression.substring(ApplyEventUpdateOperation.NAME_PREFIX.length());
            logger.info("...create EventEditOperation name=" + value);
            actionDispatcher.dispatch(new EditEventOperation(operation.getId(), "name", value));
        } else {
            throw new GLSPServerException(
                    "Cannot process 'UpdateEventOperation' expression unnown: " + operation.getExpression());
        }
    }

}
