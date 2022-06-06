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
package org.openbpmn.glsp.elements.flow;

import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.SequenceFlow;

/**
 * BPMN SequenceFlow
 *
 * @author rsoika
 *
 */
public class SequenceFlowBuilder extends AbstractGEdgeBuilder<SequenceFlow, SequenceFlowBuilder> {

    private String condition;

    public SequenceFlowBuilder() {
        super(BPMNTypes.SEQUENCE_FLOW);
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
