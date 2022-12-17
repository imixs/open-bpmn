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
package org.openbpmn.glsp.elements;

import org.eclipse.glsp.graph.builder.AbstractGCompartmentBuilder;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.elements.gateway.GatewayGNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskGNodeBuilder;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * The IconGCompartmentBuilder is used to add icons into various elements like
 * Tasks or Gateways
 *
 * @See {@link TaskGNodeBuilder}, {@link GatewayGNodeBuilder}
 * @author rsoika
 *
 */
public class IconGCompartmentBuilder extends AbstractGCompartmentBuilder<IconGCompartment, IconGCompartmentBuilder> {

    public IconGCompartmentBuilder() {
        super(ModelTypes.ICON);
    }

    @Override
    protected IconGCompartment instantiate() {
        return BpmnFactory.eINSTANCE.createIconGCompartment();
    }

    @Override
    protected IconGCompartmentBuilder self() {
        return this;
    }

}
