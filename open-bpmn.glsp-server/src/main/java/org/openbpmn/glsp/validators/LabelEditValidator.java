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

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.directediting.RequestEditValidationAction;
import org.eclipse.glsp.server.features.directediting.ValidationStatus;
import org.eclipse.glsp.server.model.GModelState;
import org.openbpmn.glsp.bpmn.BaseElement;

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
        Optional<BaseElement> element = modelState.getIndex().findElementByClass(id, BaseElement.class);
        if (!element.isEmpty()) {
            // We have a BaseElemtn, update the name attribute
            element.get().setName(text);
        }
        // no further validation needed
        return ValidationStatus.ok();
    }

}
