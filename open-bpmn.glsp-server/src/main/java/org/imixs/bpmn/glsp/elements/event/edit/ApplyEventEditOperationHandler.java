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
public class ApplyEventEditOperationHandler extends AbstractOperationHandler<ApplyEventEditOperation> {

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected GModelState modelState;

    /**
     *
     */
    @Override
    protected void executeOperation(final ApplyEventEditOperation operation) {
        String expression = operation.getExpression();
        if (expression.startsWith(ApplyEventEditOperation.DOCUMENTATION_PREFIX)) {
            String value = expression.substring(ApplyEventEditOperation.DOCUMENTATION_PREFIX.length());
            actionDispatcher.dispatch(new EventEditOperation(operation.getId(), "documentation", value));
        } else if (expression.startsWith(ApplyEventEditOperation.NAME_PREFIX)) {
            String value = expression.substring(ApplyEventEditOperation.NAME_PREFIX.length());
            actionDispatcher.dispatch(new EventEditOperation(operation.getId(), "name", value));
        } else {
            throw new GLSPServerException(
                    "Cannot process 'ApplyEventEditOperation' expression unnown: " + operation.getExpression());
        }
    }

}
