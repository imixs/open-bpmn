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
package org.openbpmn.glsp.elements;

import org.eclipse.glsp.graph.builder.AbstractGCompartmentBuilder;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.Icon;
import org.openbpmn.glsp.elements.gateway.GatewayNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskNodeBuilder;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * The IconBuilder is used to add icons into different elements like Tasks or
 * Gateways
 *
 * @See {@link TaskNodeBuilder}, {@link GatewayNodeBuilder}
 * @author rsoika
 *
 */
public class IconBuilder extends AbstractGCompartmentBuilder<Icon, IconBuilder> {

    public IconBuilder() {
        super(ModelTypes.ICON);
    }

    @Override
    protected Icon instantiate() {
        return BpmnFactory.eINSTANCE.createIcon();
    }

    @Override
    protected IconBuilder self() {
        return this;
    }

}
