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
package org.openbpmn.glsp.elements.flow;

import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.SequenceFlowGNode;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * BPMN SequenceFlow
 *
 * @author rsoika
 *
 */
public class SequenceFlowGNodeBuilder extends AbstractGEdgeBuilder<SequenceFlowGNode, SequenceFlowGNodeBuilder> {

    private String condition;

    public SequenceFlowGNodeBuilder() {
        super(BPMNTypes.SEQUENCE_FLOW);
    }

    public SequenceFlowGNodeBuilder condition(final String condition) {
        this.condition = condition;
        return self();
    }

    @Override
    protected void setProperties(final SequenceFlowGNode edge) {
        super.setProperties(edge);
        edge.setCondition(condition);

        // set the custom BPMN Router Kind
        // edge.setRouterKind(GConstants.RouterKind.MANHATTAN);
        edge.setRouterKind(ModelTypes.BPMN_ROUTER_KIND);
    }

    @Override
    protected SequenceFlowGNode instantiate() {
        return BpmnFactory.eINSTANCE.createSequenceFlowGNode();
    }

    @Override
    protected SequenceFlowGNodeBuilder self() {
        return this;
    }

}
