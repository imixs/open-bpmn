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
package org.openbpmn.glsp.elements.gateway;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

/**
 * The GatewayNodeBuilder defines the layout of BPMN gateways
 *
 * @author rsoika
 *
 */
public class GatewayNodeBuilder extends AbstractGNodeBuilder<GatewayNode, GatewayNodeBuilder> {

    private final String name;
//    private final String category;

    public GatewayNodeBuilder(final String type, final String name) {
        super(type);
        this.name = name;
//        if (gatewayType == null || gatewayType.isEmpty()) {
//            gatewayType = "";
//        }
        // this.category = category;
        // split the typeId into two parts and add the corresponding classes
        String[] cssClasses = type.split(":");
        for (String cssClass : cssClasses) {
            this.addCssClass(cssClass);
        }
    }

    @Override
    protected GatewayNode instantiate() {
        return BpmnFactory.eINSTANCE.createGatewayNode();
    }

    @Override
    protected GatewayNodeBuilder self() {
        return this;
    }

    /**
     * Define layout properties
     */
    @Override
    protected void setProperties(final GatewayNode node) {
        super.setProperties(node);
        node.setName(name);
//        if (category == null || category.isEmpty()) {
//            node.getCategory().add(category);
//        }
        node.setLayout(GConstants.Layout.FREEFORM);

        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));
        node.getChildren().add(BPMNBuilderHelper.createBPMNPort(node, -30.0, 0.0, 60.0, 60.0));
    }

}
