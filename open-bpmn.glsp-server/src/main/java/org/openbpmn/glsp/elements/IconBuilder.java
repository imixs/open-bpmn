/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
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
