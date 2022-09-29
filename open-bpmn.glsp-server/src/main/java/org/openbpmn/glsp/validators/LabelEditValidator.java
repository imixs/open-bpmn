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

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.directediting.RequestEditValidationAction;
import org.eclipse.glsp.server.features.directediting.ValidationStatus;
import org.eclipse.glsp.server.model.GModelState;
import org.openbpmn.glsp.bpmn.BaseElementGNode;

import com.google.inject.Inject;

/**
 * This ContextEditValidator reacts on label-edits within the diagram pane and
 * updates the corresponding 'name' property from the parent BaseElement node.
 * <p>
 * This means if the user edits the label of a ElementNode he indirectly edit
 * the 'name' property.
 *
 * @author rsoika
 *
 */
public class LabelEditValidator implements ContextEditValidator {
    private static Logger logger = Logger.getLogger(LabelEditValidator.class.getName());

    @Override
    public String getContextId() {
        return "label-edit";
    }

    @Inject
    protected GModelState modelState;

    @SuppressWarnings("checkstyle:cyclomaticComplexity")
    @Override
    public ValidationStatus validate(final RequestEditValidationAction action) {
        String text = action.getText();
        String id = action.getModelElementId();
        logger.fine("...validate Element: " + id);
        // test if this element is a BaseElement with the property 'name'
        Optional<BaseElementGNode> element = modelState.getIndex().findElementByClass(id, BaseElementGNode.class);
        if (!element.isEmpty()) {
            // We have a BaseElemtn, update the name attribute
            element.get().setName(text);
        }
        // no further validation needed
        return ValidationStatus.ok();
    }

}
