package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.w3c.dom.Element;

/**
 * The BPMNNode is the abstract super class for most BPMN elements like Task,
 * Events, Gateways...
 * <p>
 * It provides a list of attributes with at least an id and a documentation.
 * Other element types can extend the attribute list.
 * <p>
 * A BPMNNode holds a reference to an optional bpmnShape element
 * 
 * @author rsoika
 */
public class Signal extends BPMNElement {

    protected String type = null;

    /**
     * Creates a new BPMN Signal element node
     * 
     * @param node
     * @param model
     */
    public Signal(BPMNModel model, Element node) {
        super(model, node, null);
    }
}
