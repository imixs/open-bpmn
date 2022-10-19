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
package org.openbpmn.glsp.elements.dataobject;

import java.util.logging.Logger;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNDataObject;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;
import org.openbpmn.model.BPMNGModelFactory;

/**
 * BPMN 2.0 Data Object Element.
 * <p>
 * The method builds a GNode from a BPMNDataObject element. The builder is
 * called from the method createGModelFromProcess of the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class DataObjectGNodeBuilder extends AbstractGNodeBuilder<DataObjectGNode, DataObjectGNodeBuilder> {

    private static Logger logger = Logger.getLogger(BPMNGModelFactory.class.getName());

    private final String name;

    public DataObjectGNodeBuilder(final BPMNDataObject dataObject) {
        super(dataObject.getType());
        this.name = dataObject.getName();
        this.id = dataObject.getId();

        try {
            BPMNBounds bpmnBounds = dataObject.getBounds();
            this.size = GraphUtil.dimension(bpmnBounds.getDimension().getWidth(),
                    bpmnBounds.getDimension().getHeight());
        } catch (BPMNMissingElementException e) {
            // should not happen
            logger.severe("BPMNDataObject does not support a BPMNBounds object!");
        }
        // set Layout options
        this.addCssClass(type);
    }

    @Override
    protected DataObjectGNode instantiate() {
        return BpmnFactory.eINSTANCE.createDataObjectGNode();
    }

    @Override
    protected DataObjectGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final DataObjectGNode node) {
        super.setProperties(node);
        node.setName(name);

        node.setLayout(GConstants.Layout.VBOX);
        node.getLayoutOptions().put(GLayoutOptions.KEY_H_ALIGN, GConstants.HAlign.CENTER);
        node.getLayoutOptions().put(GLayoutOptions.KEY_V_ALIGN, GConstants.VAlign.CENTER);
        // Set min width/height
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, BPMNActivity.DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, BPMNActivity.DEFAULT_HEIGHT);

        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());

        node.getLayoutOptions().put(GLayoutOptions.KEY_V_GAP, 1);
        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));

    }

}
