package org.openbpmn.bpmn.elements.core;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * The BPMNNode is the abstract super class for most BPMN elements like Task,
 * Events, Gateways...
 * <p>
 * It provides a list of attributes with at least an id and a documentation.
 * Other element types can extend the attribute list.
 * <p>
 * A BPMNNode holds a reference to an optional bpmnShape element
 * 
 * @author rsoika
 */
public abstract class BPMNElementNode extends BPMNElement {
    protected String type = null;
    protected BPMNProcess bpmnProcess = null;
    protected BPMNLabel label = null;
    protected Element bpmnShape = null;
    protected BPMNBounds bounds = null;

    /**
     * Create a new BPMN Base Element. The constructor expects a model instnace and
     * a node.
     * 
     * @param node
     * @param model
     */
    public BPMNElementNode(BPMNModel model, Element node) {
        super(model, node);
    }

    /**
     * Creates a new BPMNFlowElement
     * 
     * @param _type
     * @param node
     * @param model
     * @throws BPMNModelException
     */
    public BPMNElementNode(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess)
            throws BPMNModelException {
        super(model, node);
        this.type = _type;
        this.bpmnProcess = _bpmnProcess;
        // find the BPMNShape element. If not defined create a new one
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

    /**
     * Returns the BPMNShape bounds.
     * 
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNBounds getBounds() throws BPMNMissingElementException {
        if (bounds == null) {
            // lazy loading of bounds from a given bpmnShape
            bounds = new BPMNBounds(this.bpmnShape, model);
        }
        return bounds;
    }

    public void setPosition(double x, double y) {
        try {
            this.getBounds().setPosition(x, y);
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '" + this.getId() + "'");
        }
    }

    public void setDimension(double width, double height) {
        try {
            this.getBounds().setDimension(width, height);
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '" + this.getId() + "'");
        }
    }

    /**
     * Updates the BPMN Shape bounds.
     * 
     * @param x
     * @param y
     * @param height
     * @param width
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNBounds setBounds(double x, double y, double width, double height) throws BPMNMissingElementException {
        // init bound if not yet loaded
        getBounds();

        // update bounds
        bounds.setDimension(width, height);
        bounds.setPosition(x, y);

        return bounds;
    }

    /**
     * Returns the BPMNShape element
     * 
     * @return
     */
    public Element getBpmnShape() {
        return bpmnShape;
    }

    /**
     * Set the BPMNShape element
     * 
     * @param bpmnShape
     */
    public void setBpmnShape(Element bpmnShape) {
        this.bpmnShape = bpmnShape;
    }

    /**
     * Returns the BPMNProcess this element belongs to.
     * 
     * @return
     */
    public BPMNProcess getBpmnProcess() {
        return bpmnProcess;
    }

    /**
     * This method updates the participant reference of a BPMNElementNode. In a
     * collaboration diagram each BPMNElementNode is contained in a participant.
     * This can be either a Pool (private process) or the default Participant
     * (public process).
     * <p>
     * This method can be used to change the participant (pool) of an element, which
     * means that the BPMNElementNode is moved from one participant to another, or
     * it is moved out of or into a pool.
     * <p>
     * Note that this method does not validate the absolute position of the
     * BPMNElementNode within the Diagram plane.
     * <p>
     * If the current model is not a collaboration diagram, the method throws a
     * {@link BPMNInvalidTypeException}
     * 
     * @throws BPMNInvalidReferenceException - thrown if participant is null
     * @throws BPMNInvalidTypeException
     */
    public void updateParticipant(Participant participant)
            throws BPMNInvalidReferenceException, BPMNInvalidTypeException {
        if (!this.model.isCollaborationDiagram()) {
            throw new BPMNInvalidTypeException(BPMNInvalidTypeException.INVALID_TYPE,
                    "Participant can only be set in a collaboration model.");
        }
        if (participant == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Participant can not be null!");
        }

        // remove element from old participant
        this.bpmnProcess.elementNode.removeChild(this.elementNode);

        // add element to new participant
        participant.bpmnProcess.elementNode.appendChild(this.elementNode);

        // update bpmn Process
        bpmnProcess = participant.bpmnProcess;

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
        if (hasBPMNLabel()) {
            if (label == null) {
                // lazy loading of bounds from a given bpmnShape
                label = new BPMNLabel(model, this.bpmnShape);
            }
        }
        return label;
    }

    /**
     * Returns true if the element has a separate BPMN label like a Event or
     * Gateway.
     * 
     * @return
     */
    public boolean hasBPMNLabel() {

        if (BPMNTypes.BPMN_GATEWAYS.contains(type)) {
            return true;
        }

        if (BPMNTypes.BPMN_EVENTS.contains(type)) {
            return true;
        }

        if (BPMNTypes.DATAOBJECT.equals(type)) {
            return true;
        }
        if (BPMNTypes.MESSAGE.equals(type)) {
            return true;
        }

        return false;
    }

}