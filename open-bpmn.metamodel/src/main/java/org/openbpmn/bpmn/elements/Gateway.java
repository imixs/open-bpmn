package org.openbpmn.bpmn.elements;

import java.util.List;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;
import org.w3c.dom.Element;

public class Gateway extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 50.0;
    public final static double DEFAULT_HEIGHT = 50.0;
    public static final double LABEL_OFFSET = 3;

    public Gateway(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess)
            throws BPMNModelException {
        super(model, node, _type, _bpmnProcess);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    /**
     * Validate Start, End, Catch and Throw event types
     */
    @Override
    public List<BPMNValidationMarker> validate() {
        resetValidation();

        if (this.getIngoingSequenceFlows().size() == 0
                || this.getOutgoingSequenceFlows().size() == 0) {
            this.addValidationMarker(new BPMNValidationMarker("Gateway",
                    "A Gateway must have at least one ingoing and one outgoing Sequence Flow!", this.getId(),
                    BPMNValidationMarker.ErrorType.ERROR));
        }

        return this.getValidationMarkers();
    }
}
