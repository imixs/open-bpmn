package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

public class BPMNParticipant extends BPMNBaseElement {

    public final static double DEFAULT_WIDTH = 500.0;
    public final static double DEFAULT_HEIGHT = 150.0;
    protected BPMNLabel label = null;
    private String processRef=null;

    public BPMNParticipant(BPMNModel model, Element node) throws BPMNModelException {
        super(model, node);
        
        
        // find the BPMNShape element. If not defined create a new one
       
            bpmnShape = (Element) model.findBPMNPlaneElement(
                   "BPMNShape", getId());
            if (bpmnShape == null) {
                // create shape element
                bpmnShape = model.buildBPMNShape(this);
                this.setBounds(0.0, 0.0, getDefaultWidth(), getDefaultHeigth());
                // create BPMNLabel 
               
                if (label!=null) {
                    // set default position
                    //BPMNPoint pos = this.getBounds().getPosition();
                    label.updateLocation(0.0,0.0);
                }
            }
        
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
