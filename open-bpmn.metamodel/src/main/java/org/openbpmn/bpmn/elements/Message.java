package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * A Message defines a message data object. A message can be referred by MessageFlow
 * <p>
 *
 * 
 * 
 * @author rsoika
 *
 */
public class Message extends BPMNElementNode {

    public Message(BPMNModel model, Element node) {
        super(model, node);
    }

    public final static double DEFAULT_WIDTH = 35.0;
    public final static double DEFAULT_HEIGHT = 50.0;
    public static final double LABEL_OFFSET = 0;
    

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }
}
