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
public class BPMNParticipant extends BPMNElementNode {

    public final static double MIN_WIDTH = 300.0;
    public final static double MIN_HEIGHT = 100.0;
    public final static double DEFAULT_WIDTH = 800.0;
    public final static double DEFAULT_HEIGHT = 200.0;
    public final static double POOL_OFFSET = 30.0;
    
    protected BPMNLabel label = null;
    private String processRef = null;

    public BPMNParticipant(BPMNModel model, Element node) throws BPMNModelException {
        super(model, node);
        
        // find the BPMNShape element. 
        bpmnShape = (Element) model.findBPMNPlaneElement("BPMNShape", getId());
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


    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }


    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }
  
}
