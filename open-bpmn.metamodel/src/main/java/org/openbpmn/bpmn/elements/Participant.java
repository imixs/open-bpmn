package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * A BPMNParticipant is a container for a BPMNProcess. A BPMNParticipant can
 * contain a bpmnShape element which is in this case a BPMN Pool. If a
 * BPMNParticipant does not contain a bpmnShape (Pool) this indicates that this
 * is the public default process
 * 
 * @author rsoika
 *
 */
public class Participant extends BPMNElementNode {

    public final static double MIN_WIDTH = 300.0;
    public final static double MIN_HEIGHT = 100.0;
    public final static double DEFAULT_WIDTH = 800.0;
    public final static double DEFAULT_HEIGHT = 210.0;
    public final static double POOL_OFFSET = 30.0;
    protected BPMNLabel label = null;

    /**
     * Creates a new participant from a element node. The process is mandatory and
     * defines the reference to an existing process within the current model.
     * 
     * @param model
     * @param node
     * @param processRef
     * @throws BPMNModelException
     */
    public Participant(BPMNModel model, Element node, BPMNProcess process) throws BPMNModelException {
        super(model, node, process);
        // update processRef
        if (process.getId().isEmpty()) {
            throw new BPMNMissingElementException(BPMNMissingElementException.MISSING_ELEMENT,
                    "Missing id element of BPMNProcess");
        }
        node.setAttribute("processRef", process.getId());
    }

    /**
     * Updates the name of the participant and also the corresponding process name.
     */
    @Override
    public void setName(String name) {
        super.setName(name);
        // update name of corresponding process
        this.bpmnProcess.setName(name);
    }

    public boolean hasPool() {
        return (bpmnShape != null);
    }

    /**
     * Returns the BPMNProcess for this participant. The method ensures that the
     * BpmnProcess is initialized.
     * 
     * @return
     */
    @Override
    public BPMNProcess getBpmnProcess() {
        if (bpmnProcess != null) {
            try {
                bpmnProcess.init();
            } catch (BPMNModelException e) {
                logger.severe("Unable to initialized BPMNProcess '" + bpmnProcess.getId() + "' for Participant '"
                        + this.getId() + "'!");
            }
        }
        return bpmnProcess;
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
     * Remove any embedded bpmndi:BPMNLabel element within the bpmndi:BPMNShape
     * 
     * Positioning of the label is part of the client. Any position update should
     * ignore these settings in Open-BPMN.
     */
    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);

        // remove optional BPMNLabel
        Element bpmnLabel = getModel().findChildNodeByName(this.bpmnShape, BPMNNS.BPMNDI, "BPMNLabel");
        if (bpmnLabel != null) {
            this.bpmnShape.removeChild(bpmnLabel);
        }
    }
}
