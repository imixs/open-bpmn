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
package org.openbpmn.glsp.elements.event;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.AbstractGLabelBuilder;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNEvent;

/**
 * BPMN 2.0 Event Element.
 * <p>
 * The method builds a GNode from a BPMNEvent element. The builder is called
 * from the method createGModelFromProcess of the BPMNGModelFactory.
 * <p>
 * The EventNodeBuilder defines the layout and properties for all types of BPMN
 * Event elements. The radius of the circle symbol is 36. The label is below the
 * symbol.
 *
 * @author rsoika
 *
 */

//public class GLabelBuilder extends AbstractGLabelBuilder<GLabel, GLabelBuilder> {
//
//   public GLabelBuilder() {
//      this(DefaultTypes.LABEL);
//   }
//
//   public GLabelBuilder(String type) {
//      super(type);
//   }
//
//   @Override
//   protected GLabel instantiate() {
//      return GraphFactory.eINSTANCE.createGLabel();
//   }
//
//   @Override
//   protected GLabelBuilder self() {
//      return this;
//   }
//
//}

public class EventLabelBuilder extends AbstractGLabelBuilder<GLabel, EventLabelBuilder> {

    private final String name;

    public EventLabelBuilder(final BPMNEvent event) {
        super(BPMNTypes.BPMN_LABEL);
        this.name = event.getName();
        this.id = event.getId() + "_bpmnlabel";

    }

    @Override
    protected GLabel instantiate() {
        return GraphFactory.eINSTANCE.createGLabel();
    }

    @Override
    protected EventLabelBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final GLabel node) {
        super.setProperties(node);

    }

}
