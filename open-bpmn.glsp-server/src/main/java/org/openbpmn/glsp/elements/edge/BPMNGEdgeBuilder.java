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
package org.openbpmn.glsp.elements.edge;

import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.bpmn.elements.BPMNFlowEdge;
import org.openbpmn.glsp.bpmn.BPMNGEdge;
import org.openbpmn.glsp.bpmn.BpmnFactory;

/**
 * BPMN SequenceFlow
 *
 * @author rsoika
 *
 */
public class BPMNGEdgeBuilder extends AbstractGEdgeBuilder<BPMNGEdge, BPMNGEdgeBuilder> {

    private final String name;

    public BPMNGEdgeBuilder(final BPMNFlowEdge edge) {
        super(edge.getType());
        this.name = edge.getName();
        this.id = edge.getId();

        this.addCssClass(type);
        this.addCssClass("bpmnedge");
    }

    @Override
    protected void setProperties(final BPMNGEdge edge) {
        super.setProperties(edge);
        edge.setName(name);
        // set the custom BPMN Router Kind
        edge.setRouterKind(GConstants.RouterKind.MANHATTAN);
        // edge.setRouterKind(ModelTypes.BPMN_ROUTER_KIND);
    }

    @Override
    protected BPMNGEdge instantiate() {
        return BpmnFactory.eINSTANCE.createBPMNGEdge();
    }

    @Override
    protected BPMNGEdgeBuilder self() {
        return this;
    }

}
