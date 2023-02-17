package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * A Message defines a message data object. A message can be referred by
 * MessageFlow
 * <p>
 * 
 * @author rsoika
 *
 */
public class Message extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 30.0;
    public final static double DEFAULT_HEIGHT = 20.0;
    public static final double LABEL_OFFSET = 5;

    public Message(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess)
            throws BPMNModelException {
        super(model, node, type, bpmnProcess);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}
