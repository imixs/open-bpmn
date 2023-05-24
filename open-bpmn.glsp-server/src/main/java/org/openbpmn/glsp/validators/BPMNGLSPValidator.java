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
package org.openbpmn.glsp.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.MarkerKind;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.openbpmn.bpmn.validation.BPMNValidationError;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The BPMNGLSPValidator is used to validate the complete model. It is
 * triggered when the GLSP client starts 'Validate model' action.
 * <p>
 * The Result of a validation can be verified in the 'Problems View' from Theia.
 * <p>
 * The BPMNModelValidator simply calls the BPMNModel.validate() method which
 * returns a List of all found BPMNValidationError objects.
 * This List is translated into GLSP Marker list.
 * 
 *
 * @see: https://www.eclipse.org/glsp/documentation/validation/
 * @author rsoika
 *
 */
public class BPMNGLSPValidator implements ModelValidator {
    private static Logger logger = Logger.getLogger(BPMNGLSPValidator.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public List<Marker> validate(final GModelElement... elements) {
        logger.fine("...starting validating model...");
        List<Marker> markers = new ArrayList<>();

        // Meta Model validation...
        List<BPMNValidationError> errorList = modelState.getBpmnModel().validate();

        // Convert ErrorList into a GLSP Marker List
        for (BPMNValidationError _error : errorList) {
            if (BPMNValidationError.ErrorType.ERROR.equals(_error.getErrorType())) {
                markers.add(new Marker(_error.getLabel(), _error.getDescription(), _error.getElementId(),
                        MarkerKind.ERROR));

            }
        }

        return markers;
    }

}