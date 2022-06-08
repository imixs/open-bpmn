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
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.MarkerKind;
import org.eclipse.glsp.server.features.validation.ModelValidator;

/**
 * The BPMNModelValidator is used to validate the complete model. It is
 * triggered when the client starts 'Validate model' action.
 * <p>
 * The Result of a validaten can be verfied in the 'Problems View' from Theia.
 *
 * <p>
 * Currently we do not validate any model logic here.
 *
 * @see: https://www.eclipse.org/glsp/documentation/validation/
 * @author rsoika
 *
 */
public class BPMNModelValidator implements ModelValidator {
    private static Logger logger = Logger.getLogger(BPMNModelValidator.class.getName());

    @Override
    public List<Marker> validate(final GModelElement... elements) {
        logger.fine("...starting validating model...");
        List<Marker> markers = new ArrayList<>();
        for (GModelElement element : elements) {
            if (element instanceof GNode) {
                markers.add(validateGNode((GNode) element));
            }
            element.getChildren().forEach(child -> markers.addAll(validate(child)));
        }
        return markers;
    }

    // create a dummy marker
    protected Marker validateGNode(final GNode element) {
        return new Marker("Node", "This graphical element is a node", element.getId(), MarkerKind.INFO);
    }

}