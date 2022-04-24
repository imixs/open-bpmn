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
package org.openbpmn.glsp.elements.flow;

import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.glsp.graph.BpmnFactory;
import org.openbpmn.glsp.graph.SequenceFlow;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * BPMN SequenceFlow
 *
 * @author rsoika
 *
 */
public class SequenceFlowBuilder extends AbstractGEdgeBuilder<SequenceFlow, SequenceFlowBuilder> {

    private String condition;

    public SequenceFlowBuilder() {
        super(ModelTypes.SEQUENCE_FLOW);
    }

    public SequenceFlowBuilder condition(final String condition) {
        this.condition = condition;
        return self();
    }

    @Override
    protected void setProperties(final SequenceFlow edge) {
        super.setProperties(edge);
        edge.setCondition(condition);

        edge.setRouterKind(GConstants.RouterKind.MANHATTAN);
        // edge.setRouterKind("bpmn");
    }

    @Override
    protected SequenceFlow instantiate() {
        return BpmnFactory.eINSTANCE.createSequenceFlow();
    }

    @Override
    protected SequenceFlowBuilder self() {
        return this;
    }

}
