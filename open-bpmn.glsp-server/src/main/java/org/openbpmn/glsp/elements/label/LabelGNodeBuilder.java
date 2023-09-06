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

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.utils.BPMNGModelUtil;

/**
 * BPMN 2.0 Label Element.
 * <p>
 * The method builds a BPMNLabel used for Events, Gateways or Data elements. The
 * builder is called from the method createGModelFromProcess of the
 * BPMNGModelFactory.
 * <p>
 * Note: In case the LabelGNode did not provide bounds settings, or the
 * width/height does not match the Open BPMN default values, the builder
 * will set/update the bounds automatically to the Open BPMN default settings.
 * This will also reflect to the source model in case the user saves the model
 * later.
 * 
 * @author rsoika
 *
 */
public class LabelGNodeBuilder extends AbstractGNodeBuilder<LabelGNode, LabelGNodeBuilder> {

    private final String name;

    public LabelGNodeBuilder(final BPMNElementNode flowElement) {
        super(BPMNTypes.BPMNLABEL);
        this.name = flowElement.getName();// _name;
        this.id = flowElement.getId() + "_bpmnlabel";
        double width = BPMNLabel.DEFAULT_WIDTH;
        double height = BPMNLabel.DEFAULT_HEIGHT;
        double x = 0.0;
        double y = 0.0;

        // compute size
        try {
            if (flowElement.getLabel() != null && flowElement.getLabel().getBounds() != null) {
                x = flowElement.getLabel().getBounds().getPosition().getX();
                y = flowElement.getLabel().getBounds().getPosition().getY();
                width = flowElement.getLabel().getBounds().getDimension().getWidth();
                height = flowElement.getLabel().getBounds().getDimension().getHeight();

                // If no x/y is set we compute the default position. This will also reflect to
                // the source model, if the model will be saved later by the user...
                if (x == 0 || y == 0) {
                    x = flowElement.getBounds().getPosition().getX() + (Event.DEFAULT_WIDTH / 2)
                            - (BPMNLabel.DEFAULT_WIDTH / 2);
                    y = flowElement.getBounds().getPosition().getY() + Event.DEFAULT_HEIGHT + Event.LABEL_OFFSET;
                    // update source model with default width/height!
                    flowElement.getLabel().getBounds().setPosition(x, y);
                }
                // If no width/heigh is set or the width/height does not match
                // the open bpmn DEFAULT_WIDTH/HEIGHT, we adjust the dimensions
                // to the default settings. This will also reflect to the source model,
                // if the model will be saved later by the user...
                if (width != BPMNLabel.DEFAULT_WIDTH || height != BPMNLabel.DEFAULT_HEIGHT) {
                    if (width > 0) {
                        // adjust x postion .....
                        double xOffset = ((BPMNLabel.DEFAULT_WIDTH - width) / 2);
                        x = x - xOffset;
                        flowElement.getLabel().getBounds().setPosition(x, y);
                    }

                    width = BPMNLabel.DEFAULT_WIDTH;
                    height = BPMNLabel.DEFAULT_HEIGHT;
                    // update source model with default width/height!
                    flowElement.getLabel().getBounds().setDimension(width, height);
                }

            }
        } catch (BPMNMissingElementException e) {
            // failed to compute size
            e.printStackTrace();
        }
        this.size = GraphUtil.dimension(width, height);

        // set Layout options
        this.addCssClass(type);
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

    /**
     * The labelGNode element uses a HBOX layout with an embedded detail container
     * holding the multilabel.
     * This layout ensures that the element grows automatically with the size of the
     * multiLineNode.
     */
    @Override
    public void setProperties(final LabelGNode node) {
        super.setProperties(node);
        node.setName(name);
        node.setLayout(GConstants.Layout.HBOX);

        // Set absolute and min width/height for the Pool element
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, BPMNLabel.DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, BPMNLabel.DEFAULT_HEIGHT);
        if (size != null) {
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());
        }
        GCompartment detailsContainer = BPMNGModelUtil.createMultiLineContainer(node);
        node.getChildren().add(detailsContainer);

        // add a multiLine text block
        detailsContainer.getChildren()
                .add(BPMNGModelUtil.createMultiLineTextNode(node, name, BPMNGModelUtil.MULTILINETEXT_ALIGN_MIDDLE, 0));
    }


}
