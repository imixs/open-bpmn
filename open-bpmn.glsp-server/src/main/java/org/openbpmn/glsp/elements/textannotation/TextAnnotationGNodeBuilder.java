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
package org.openbpmn.glsp.elements.textannotation;

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.TextAnnotation;
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

    private final String text;

    public TextAnnotationGNodeBuilder(final TextAnnotation textAnnotation) {
        super(textAnnotation.getType());
        this.text = textAnnotation.getText();
        this.id = textAnnotation.getId();

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
        // node.setName(name);
        node.setLayout(GConstants.Layout.FREEFORM);
        size = GraphUtil.dimension(TextAnnotation.DEFAULT_WIDTH, TextAnnotation.DEFAULT_HEIGHT);
        node.setSize(size);

        node.getLayoutOptions().put("minWidth", TextAnnotation.DEFAULT_WIDTH);
        node.getLayoutOptions().put("minHeight", TextAnnotation.DEFAULT_HEIGHT);

        // add a mulitLine text block to show and edit long text blocks
        this.id = node.getId() + "_bpmntext";
        node.getChildren().add(new GNodeBuilder("bpmn-text-node"). //
                id(id). //
                addArgument("text", text). //
                build());
    }

}
