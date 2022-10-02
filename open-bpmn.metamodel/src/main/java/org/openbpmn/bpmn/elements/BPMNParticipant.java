package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
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
public class BPMNParticipant extends BPMNBaseElement {

    public final static double DEFAULT_WIDTH = 500.0;
    public final static double DEFAULT_HEIGHT = 150.0;
    protected BPMNLabel label = null;
    private String processRef = null;

    public BPMNParticipant(BPMNModel model, Element node) throws BPMNModelException {
        super(model, node);

        // find the BPMNShape element. If not defined create a new one

        bpmnShape = (Element) model.findBPMNPlaneElement("BPMNShape", getId());

    }

    /**
     * This method creates a BPMNShape for this participant. The shape element
     * represents the Pool within the BPMNDiagram section.
     * 
     * @throws BPMNModelException
     */
    public void createPool() throws BPMNModelException {
        if (bpmnShape == null) {
            // create shape element
            bpmnShape = model.buildBPMNShape(this);
            this.setBounds(0.0, 0.0, getDefaultWidth(), getDefaultHeigth());
            // create BPMNLabel
            if (label != null) {
                // set default position
                // BPMNPoint pos = this.getBounds().getPosition();
                label.updateLocation(0.0, 0.0);
            }
        }
    }

    public boolean hasPool() {
        return (bpmnShape != null);
    }
    /**
     * Returns the initialized BPMNProcess of this participant or returns null if no
     * proces exists
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

    public void setProcessRef(String processRef) {
        this.processRef = processRef;
        this.getElementNode().setAttribute("processRef", processRef);
    }

    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }

    
    
  
}
