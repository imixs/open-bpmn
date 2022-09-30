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
package org.openbpmn.glsp.elements.gateway;

import java.util.Arrays;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

/**
 * BPMN 2.0 Gateway Element.
 * <p>
 * The method builds a GNode from a BPMNGateway element. The builder is called
 * from the method createGModelFromProcess of the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class GatewayGNodeBuilder extends AbstractGNodeBuilder<GatewayGNode, GatewayGNodeBuilder> {

    private final String name;

    public GatewayGNodeBuilder(final BPMNGateway gateway) {
        super("bpmn:" + gateway.getType());
        this.name = gateway.getName();
        this.id = gateway.getId();

        // set Layout options
        this.addCssClasses(Arrays.asList(type.split(":")));
        this.addCssClass("gateway");
    }

    @Override
    protected GatewayGNode instantiate() {
        return BpmnFactory.eINSTANCE.createGatewayGNode();
    }

    @Override
    protected GatewayGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final GatewayGNode node) {
        super.setProperties(node);
        node.setName(name);
        node.setLayout(GConstants.Layout.FREEFORM);
        size = GraphUtil.dimension(BPMNGateway.DEFAULT_WIDTH, BPMNGateway.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", BPMNGateway.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", BPMNGateway.DEFAULT_HEIGHT);

        node.getChildren().add(BPMNBuilderHelper.createCompartmentIcon(node));

    }

}
