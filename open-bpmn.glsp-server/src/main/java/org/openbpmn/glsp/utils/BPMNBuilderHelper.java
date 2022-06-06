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
package org.openbpmn.glsp.utils;

import java.util.logging.Logger;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.BaseElement;
import org.openbpmn.glsp.bpmn.Icon;
import org.openbpmn.glsp.elements.IconBuilder;

/**
 * The BPMNBuilderHelper provides helper methods to create GNode Elements
 *
 * @author rsoika
 *
 */
public class BPMNBuilderHelper {
    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(BPMNBuilderHelper.class.getName());

    public static Icon createCompartmentIcon(final BaseElement node) {
        return new IconBuilder(). //
                id(node.getId() + "_icon"). //
                build();
    }

    /**
     * Creates a GLabel for the name of a BaseElement
     *
     * @param node
     * @return GPort
     */
    public static GLabel createCompartmentHeader(final BaseElement node) {
        return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
                .id(node.getId() + "_header") //
                .text(node.getName()).build();
    }

    /**
     * Creates a moveable BPMNLabel. This element is used for Event and Gateway
     * Nodes
     *
     */
    public static GLabel createBPMNLabel(final String id, final String name, final Double x, final Double y) {
        return new GLabelBuilder(BPMNTypes.BPMN_LABEL) //
                .id(id + "_bpmnlabel") //
                .position(x, y) //
                .text(name) //
                .build();
    }

}
