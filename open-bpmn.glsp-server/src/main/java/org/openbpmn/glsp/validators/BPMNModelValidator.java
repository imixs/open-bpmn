/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
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