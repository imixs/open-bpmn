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
package org.openbpmn.glsp.elements.label;

import java.util.Arrays;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;

/**
 * BPMN 2.0 Label Element.
 * <p>
 * The method builds a GNode from a BPMNLabel element. The builder is called
 * from the method createGModelFromProcess of the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class LabelGNodeBuilder extends AbstractGNodeBuilder<LabelGNode, LabelGNodeBuilder> {

    public final static double DEFAULT_WIDTH = 100.0;
    public final static double DEFAULT_HEIGHT = 60.0;

    private final String name;

    public LabelGNodeBuilder(final String _name, final BPMNLabel bpmnLabel) {
        super("bpmn:" + BPMNTypes.LABEL);
        this.name = _name;
        if (bpmnLabel != null) {
            this.size = GraphUtil.dimension(bpmnLabel.getDimension().getWidth(), bpmnLabel.getDimension().getHeight());
        }
        // set Layout options
        this.addCssClasses(Arrays.asList(type.split(":")));
    }

    @Override
    protected LabelGNode instantiate() {
        return BpmnFactory.eINSTANCE.createLabelGNode();
    }

    @Override
    protected LabelGNodeBuilder self() {
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setProperties(final LabelGNode node) {
        super.setProperties(node);
        node.setName(name);

        node.setLayout(GConstants.Layout.HBOX);
        // Set min width/height
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, DEFAULT_HEIGHT);

        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
        node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());

        node.getLayoutOptions().put(GLayoutOptions.KEY_H_GAP, 10);
        node.getLayoutOptions().put(GLayoutOptions.KEY_V_ALIGN, "center");

        node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));

    }

}
