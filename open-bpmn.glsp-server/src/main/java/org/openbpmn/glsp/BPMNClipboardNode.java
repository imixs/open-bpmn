package org.openbpmn.glsp;

import org.w3c.dom.Element;

/**
 * Holds a detached, cloned pair of a BPMN semantic element (e.g. bpmn2:task)
 * and its corresponding bpmndi:BPMNShape element (which contains the dc:Bounds
 * with x/y/width/height). Both are plain, detached DOM Elements, not attached
 * to any BPMNProcess or BPMNPlane.
 */
public class BPMNClipboardNode {

    private final Element semanticElement;
    private final Element shapeElement;

    public BPMNClipboardNode(final Element semanticElement, final Element shapeElement) {
        this.semanticElement = semanticElement;
        this.shapeElement = shapeElement;
    }

    public Element getSemanticElement() {
        return semanticElement;
    }

    public Element getShapeElement() {
        return shapeElement;
    }
}