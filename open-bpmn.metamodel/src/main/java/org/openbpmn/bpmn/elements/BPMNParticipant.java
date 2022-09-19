package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BPMNParticipant extends BPMNFlowElement {

    public final static double DEFAULT_WIDTH = 300.0;
    public final static double DEFAULT_HEIGHT = 100.0;

    public BPMNParticipant(BPMNModel model, Element node, String type) throws BPMNModelException {
        super(model, node, type);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }
}
