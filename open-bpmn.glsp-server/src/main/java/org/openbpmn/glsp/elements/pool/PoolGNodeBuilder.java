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
package org.openbpmn.glsp.elements.pool;

import java.util.logging.Logger;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.utils.BPMNGraphUtil;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * BPMN 2.0 Pool Element.
 * <p>
 * The method builds a GNode Pool element from a BPMNParticipant element. The
 * builder is called from the method createGModelFromProcess of the
 * BPMNGModelFactory.
 *
 * @author rsoika
 */
public class PoolGNodeBuilder extends AbstractGNodeBuilder<PoolGNode, PoolGNodeBuilder> {

    private static Logger logger = Logger.getLogger(PoolGNodeBuilder.class.getName());

    private String name;

    public PoolGNodeBuilder(final Participant participant) {
        super(BPMNTypes.POOL);
        this.name = participant.getName();
        this.id = participant.getId();
        try {
            BPMNBounds bpmnPoolBounds = participant.getBounds();
            this.position = GraphUtil.point(bpmnPoolBounds.getPosition().getX(), bpmnPoolBounds.getPosition().getY());
            this.size = GraphUtil.dimension(bpmnPoolBounds.getDimension().getWidth(),
                    bpmnPoolBounds.getDimension().getHeight());
        } catch (BPMNMissingElementException e) {
            // should not happen
            logger.severe("BPMNParticipant does not support a BPMNBounds object!");
        }

        // set css style
        this.addCssClass(type);
    }

    @Override
    protected PoolGNode instantiate() {
        return BpmnFactory.eINSTANCE.createPoolGNode();
    }

    @Override
    protected PoolGNodeBuilder self() {
        return this;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setProperties(final PoolGNode node) {
        super.setProperties(node);
        node.setName(name);

        node.setLayout(GConstants.Layout.HBOX);
        // Set min width/height for the Pool element
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, Participant.MIN_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, Participant.MIN_HEIGHT);

        node.getChildren().add(BPMNGraphUtil.createBPMNContainerHeader(node));
        node.getChildren().add(createContainerCompartment(node));

    }

    /**
     * Creates the Container compartment
     *
     * @param node
     * @return
     */
    private GCompartment createContainerCompartment(final PoolGNode node) {
        // DefaultTypes.NODE ModelTypes.CONTAINER
        return new GCompartmentBuilder(ModelTypes.CONTAINER) //
                .id(node.getId() + "_container") //
                .layout(GConstants.Layout.FREEFORM) //
                .build();
    }
}
