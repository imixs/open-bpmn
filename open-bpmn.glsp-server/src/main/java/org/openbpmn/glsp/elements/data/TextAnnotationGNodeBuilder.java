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

import java.util.logging.Logger;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.TextAnnotationGNode;

/**
 * BPMN 2.0 Data Object Element.
 * <p>
 * The method builds a GNode from a BPMN TextAnnotation element. The builder is
 * called from the method createGModelFromProcess of the BPMNGModelFactory.
 *
 * @author rsoika
 *
 */
public class TextAnnotationGNodeBuilder extends AbstractGNodeBuilder<TextAnnotationGNode, TextAnnotationGNodeBuilder> {
    private static Logger logger = Logger.getLogger(TextAnnotationGNodeBuilder.class.getName());

    private final String text;

    public TextAnnotationGNodeBuilder(final TextAnnotation textAnnotation) {
        super(textAnnotation.getType());
        this.text = textAnnotation.getText();
        this.id = textAnnotation.getId();

        try {
            BPMNBounds bpmnBounds = textAnnotation.getBounds();
            this.size = GraphUtil.dimension(bpmnBounds.getDimension().getWidth(),
                    bpmnBounds.getDimension().getHeight());
        } catch (BPMNMissingElementException e) {
            // should not happen
            logger.severe("textAnnotation " + id + " does not support a BPMNBounds object!");
        }
        // set Layout options
        this.addCssClass(type);
    }

    @Override
    protected TextAnnotationGNode instantiate() {
        return BpmnFactory.eINSTANCE.createTextAnnotationGNode();
    }

    @Override
    protected TextAnnotationGNodeBuilder self() {
        return this;
    }

    @Override
    public void setProperties(final TextAnnotationGNode node) {
        super.setProperties(node);
        node.setLayout(GConstants.Layout.FREEFORM);

        // Set min width/height
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_WIDTH, TextAnnotation.DEFAULT_WIDTH);
        node.getLayoutOptions().put(GLayoutOptions.KEY_MIN_HEIGHT, TextAnnotation.DEFAULT_HEIGHT);

        if (size != null) {
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, size.getWidth());
            node.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, size.getHeight());
        }
        // add a mulitLine text block to show and edit long text blocks
        this.id = node.getId() + "_bpmntext";
        node.getChildren().add(new GNodeBuilder("bpmn-text-node"). //
                id(id). //
                addArgument("text", text). //
                build());
    }

}
