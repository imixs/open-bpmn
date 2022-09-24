package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * The FlowElement is the abstract super class for all elements that can appear
 * in a Process flow, which are FlowNodes consisting of Activities ,
 * Choreography Activities, Gateways , and Events , Data Objects , Data
 * Associations , and Sequence Flows.
 * <p>
 * A BPMNFlowElement holds a reference to the Bounds of the element
 * 
 * @author rsoika
 *
 */
public abstract class BPMNFlowElement extends BPMNBaseElement {

    protected String type = null;
    protected BPMNProcess bpmnProcess = null;
    protected BPMNLabel label = null;

    /**
     * Creates a new BPMNFlowElement
     * 
     * @param _type
     * @param node
     * @param model
     * @throws BPMNModelException
     */
    public BPMNFlowElement(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess)
            throws BPMNModelException {
        super(model, node);
        this.type = _type;
        this.bpmnProcess = _bpmnProcess;
        // find the BPMNShape element. If not defined create a new one
        if (bpmnProcess != null) {
            bpmnShape = (Element) model.findBPMNPlaneElement("BPMNShape", getId());
            if (bpmnShape == null) {
                // create shape element
                bpmnShape = model.buildBPMNShape(this); 
                this.setBounds(0.0, 0.0, getDefaultWidth(), getDefaultHeigth());
                // create BPMNLabel
                label = this.getLabel();
                if (label != null) {
                    // set default position
                    // BPMNPoint pos = this.getBounds().getPosition();
                    label.updateLocation(0.0, 0.0);
                }
            }
        }
    }

    /**
     * Defines the default width of an BPMN element. Must be overwritten by
     * subclasses.
     * 
     * @return
     */
    public abstract double getDefaultWidth();

    /**
     * Defines the default height of an BPMN element. Must be overwritten by
     * subclasses.
     * 
     * @return
     */
    public abstract double getDefaultHeigth();

    /**
     * Returns the BPMN sub type
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set the BPMN sub type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the BPMN Label
     * 
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNLabel getLabel() throws BPMNMissingElementException {

        if (isEvent() || isGateway()) {
            if (label == null) {
                // lazy loading of bounds from a given bpmnShape
                label = new BPMNLabel(model, this.bpmnShape);
            }
        }
        return label;
    }

    /**
     * Returns true if the element is an BPMN Event
     * 
     * @return
     */
    public boolean isEvent() {
        return (BPMNTypes.EVENT.equals(type)//
                || BPMNTypes.START_EVENT.equals(type) //
                || BPMNTypes.END_EVENT.equals(type) //
                || BPMNTypes.CATCH_EVENT.equals(type) //
                || BPMNTypes.THROW_EVENT.equals(type) //
        );
    }

    /**
     * Returns true if the element is an BPMN Gateway
     * 
     * @return
     */
    public boolean isGateway() {
        return (BPMNTypes.GATEWAY.equals(type) //
                || BPMNTypes.EXCLUSIVE_GATEWAY.equals(type)//
                || BPMNTypes.INCLUSIVE_GATEWAY.equals(type)//
                || BPMNTypes.COMPLEX_GATEWAY.equals(type)//
                || BPMNTypes.PARALLEL_GATEWAY.equals(type)//
        );
    }

}
