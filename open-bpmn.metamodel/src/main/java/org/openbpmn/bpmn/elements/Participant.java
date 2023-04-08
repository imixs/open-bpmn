package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
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
    public final static double DEFAULT_HEIGHT = 200.0;
    public final static double POOL_OFFSET = 30.0;

    protected BPMNLabel label = null;
    private String processRef = null;

    public Participant(BPMNModel model, Element node) throws BPMNModelException {
        super(model, node);
        // find the BPMNShape element.
        bpmnShape = (Element) model.findBPMNPlaneElement("BPMNShape", getId());
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
     * Returns the initialized BPMNProcess of this participant or returns null if no
     * process exists
     * 
     * @return BPMNProcess
     */
    public BPMNProcess openProcess() {
        try {
            return model.openProcess(processRef);
        } catch (BPMNModelException e) {
            BPMNModel.getLogger().severe("Participant process '" + processRef + "' not fund!");
        }
        return null;
    }

    public String getProcessRef() {
        return processRef;
    }

    /**
     * This method updates the processRef attribute of the participant.
     * If the id is empty or null the attribute will be removed.
     * 
     * @param processRef
     */
    public void setProcessRef(String processRef) {
        this.processRef = processRef;
        if (processRef != null && !processRef.isEmpty()) {
            this.getElementNode().setAttribute("processRef", processRef);
        } else {
            this.getElementNode().removeAttribute("processRef");
        }
    }

    /**
     * This method returns the corresponding BPMNProcess ID for this Element.
     * 
     * @param bpmynElement
     * @return
     */
    @Override
    public String getProcessId() {
        return getProcessRef();
    }

    /**
     * Updates the BPMNProcessRef of the Participant
     * 
     * @return
     */
    public void setBpmnProcess(BPMNProcess _process) {
        this.bpmnProcess = _process;
        this.setProcessRef(_process.getId());
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
