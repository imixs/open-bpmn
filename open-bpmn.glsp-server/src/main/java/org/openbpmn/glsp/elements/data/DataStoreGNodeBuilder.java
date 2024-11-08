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
package org.openbpmn.glsp.elements.data;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.DataStoreReference;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.DataStoreGNode;
import org.openbpmn.glsp.utils.BPMNGModelUtil;

/**
 * BPMN 2.0 Data Store Element.
 * <p>
 * The method builds a GNode from a BPMNDataStoreReference element. The builder
 * is
 * called from the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class DataStoreGNodeBuilder extends AbstractGNodeBuilder<DataStoreGNode, DataStoreGNodeBuilder> {

    private final String name;

    public DataStoreGNodeBuilder(final DataStoreReference dataStore) {
        super(dataStore.getType());
        this.name = dataStore.getName();
        this.id = dataStore.getId();

        // set Layout options
        this.addCssClass(type);
    }

    @Override
    protected DataStoreGNode instantiate() {
        return BpmnFactory.eINSTANCE.createDataStoreGNode();
    }

    @Override
    protected DataStoreGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final DataStoreGNode node) {
        super.setProperties(node);
        node.setName(name);
        node.setLayout(GConstants.Layout.FREEFORM);
        size = GraphUtil.dimension(DataStoreReference.DEFAULT_WIDTH, DataStoreReference.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", DataStoreReference.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", DataStoreReference.DEFAULT_HEIGHT);

        node.getChildren().add(BPMNGModelUtil.createCompartmentIcon(node));

    }

}
