package org.openbpmn.bpmn.elements.core;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.DataObject;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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

    protected static Logger logger = Logger.getLogger(BPMNElementNode.class.getName());

    protected String type = null;
    protected BPMNProcess bpmnProcess = null;
    protected BPMNLabel label = null;
    protected Element bpmnShape = null;
    protected BPMNBounds bounds = null;

    /**
     * Create a new BPMN Base Element. The constructor expects a model instance and
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
            this.setBounds(0.0, 0.0, getDefaultWidth(), getDefaultHeight());
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
        // update bounds
        setDimension(width, height);
        setPosition(x, y);
        return bounds;
    }

    /**
     * Update the absolute position of a BPMNElementNode.
     * 
     * When BPMNElementNode is part of a participant with lanes, this method
     * automatically updates the containing participant and/or the bpmn2:flowNodeRef
     * of the containing lane.
     * 
     * 
     * 
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        try {
            this.getBounds().setPosition(x, y);
            // update containment only if position is not 0,0
            if (x != 0 && y != 0) {
                updateContainment();
            }
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '" + this.getId() + "'");
        }
    }

    /**
     * Update the absolute position of a BPMNElementNode.
     * 
     * When BPMNElementNode is part of a participant with lanes, this method
     * automatically updates the containing participant and/or the bpmn2:flowNodeRef
     * of the containing lane.
     * 
     * 
     * 
     * @param point - a BPMN point
     * @param y
     */
    public void setPosition(BPMNPoint point) {
        try {
            this.getBounds().setPosition(point);
            // update containment only if position is not 0,0
            if (point.getX() != 0 && point.getY() != 0) {
                // updateContainment(point.getX(), point.getY());
                updateContainment();
            }
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '" + this.getId() + "'");
        }
    }

    /**
     * This method update the containment regarding the
     * participant and/or lane
     * 
     */
    // public void updateContainment() {
    // try {

    // // compute center of element
    // BPMNPoint centerPoint = this.getBounds().getCenter();

    // updateContainment(this.getBounds().getPosition().getX(),
    // this.getBounds().getPosition().getY());
    // } catch (BPMNMissingElementException e) {
    // logger.warning("Failed to recompute containment of elemnet " + this.getId());
    // }
    // }

    /**
     * This helper method verifies the current containment and updates the
     * participant and/or lane in case the containment has changed.
     * 
     * The method first verifies if the BPMNElementNode is still contained by the
     * current Participant or is moved from the default process into a pool. In this
     * cases the method updates the process ref of the element.
     * 
     */
    public void updateContainment() {
        // update is only needed for collaboration diagrams
        if (!this.model.isCollaborationDiagram()) {
            return;
        }
        // update is only possible for flow elements and data objects.
        if (!BPMNTypes.isFlowElementNode(this) && !BPMNTypes.isDataObjectNode(this)
                && !BPMNTypes.isTextAnnotationNode(this)) {
            return;
        }

        try {
            // compute the center of element
            BPMNPoint centerPoint = this.getBounds().getCenter();

            // first test if the participant need to be updated...
            Set<Participant> participants = this.model.getParticipants();
            BPMNProcess newProcess = this.model.openDefaultProces(); // default process
            for (Participant participant : participants) {
                if (participant.bounds == null) {
                    continue; // no bounds!
                }
                BPMNBounds participantBounds = participant.getBounds();
                if (participantBounds.containsPoint(new BPMNPoint(centerPoint.getX(), centerPoint.getY()))) {
                    newProcess = participant.bpmnProcess;
                    break;
                }
            }
            if (!this.getProcessId().equals(newProcess.getId())) {
                // change process!
                updateBPMNProcess(newProcess);
            }

            // In any case we finally update the lane flowNodeRef if the element is or was
            // part of a pool with lanes....
            // For this we iterate over all lanes and test if a lane is the containing lane
            // according to the position.
            Set<Lane> lanes = this.getBpmnProcess().getLanes();
            for (Lane lane : lanes) {
                BPMNBounds laneBounds = lane.getBounds();
                if (laneBounds.containsPoint(new BPMNPoint(centerPoint.getX(), centerPoint.getY()))) {
                    // found containing lane!
                    lane.insert(this);
                } else {
                    // remove if listed in this lane....
                    lane.remove(this);
                }
            }

        } catch (BPMNMissingElementException | BPMNInvalidTypeException e) {
            BPMNModel.error("Failed to update bounds position for element '" + this.getId() + "' - " + e.getMessage());
        }
    }

    /**
     * This method updates the process assignment of a FlowElement Node. The element
     * will be removed form the current process and added to the new process. All
     * edges will be removed.
     * 
     * Also the element will be removed from an optional laneSet of the old process.
     * 
     * @param newProcess
     * @throws BPMNInvalidTypeException
     */
    public void updateBPMNProcess(BPMNProcess newProcess) throws BPMNInvalidTypeException {

        if (!BPMNTypes.isFlowElementNode(this) && !BPMNTypes.isDataObjectNode(this)
                && !BPMNTypes.isTextAnnotationNode(this)) {
            logger.finest(
                    "updateBPMNProcess can only be applied for BPMN FlowElements (Event, Gateway, Activity, DataObjects)");
            return;
        }

        // remove element from an optional laneSet
        Set<Lane> lanes = this.bpmnProcess.getLanes();
        for (Lane lane : lanes) {
            lane.remove(this);
        }

        // remove all flows...
        this.bpmnProcess.removeAllEdgesFromElement(this.getId());

        // ...remove the element from the corresponding element list
        // and add it to the new process
        if (this instanceof Activity) {
            this.bpmnProcess.getActivities().remove(this);
            newProcess.getActivities().add((Activity) this);
        }
        if (this instanceof Gateway) {
            this.bpmnProcess.getGateways().remove(this);
            newProcess.getGateways().add((Gateway) this);
        }
        if (this instanceof Event) {
            this.bpmnProcess.getEvents().remove(this);
            newProcess.getEvents().add((Event) this);
        }
        if (this instanceof DataObject) {
            this.bpmnProcess.getDataObjects().remove(this);
            newProcess.getDataObjects().add((DataObject) this);
        }
        if (this instanceof TextAnnotation) {
            this.bpmnProcess.getTextAnnotations().remove(this);
            newProcess.getTextAnnotations().add((TextAnnotation) this);
        }

        // remove element from old process and assign it ot the new
        this.bpmnProcess.elementNode.removeChild(this.elementNode);
        this.bpmnProcess = newProcess;
        this.bpmnProcess.elementNode.appendChild(this.elementNode);
    }

    /**
     * This method updates the bpmn2:incoming and bpmn2:outgoing references of a
     * BPMNElement.
     * During cloning a element it may happen that invalid references are copied as
     * child elements. This method can be called to clean up such references.
     * 
     */
    public void updateSequenceFlowReferences() {
        // only possible if a bpmnProcess is defined
        if (bpmnProcess == null) {
            return; // no op!
        }
        // remove incoming childs...
        NodeList incomingSequenceFlows = getElementNode()
                .getElementsByTagName(bpmnProcess.getModel().getPrefix(BPMNNS.BPMN2) + ":incoming");
        for (int i = 0; i < incomingSequenceFlows.getLength(); i++) {
            Element item = (Element) incomingSequenceFlows.item(i);
            // test if the sequence flow exists...
            BPMNElementEdge flow = bpmnProcess.findElementEdgeById(item.getTextContent());
            if (flow != null &&
                    (!getId().equals(flow.getElementNode().getAttribute("sourceRef"))
                            && !getId().equals(flow.getElementNode().getAttribute("targetRef")))) {
                // invalid element
                getElementNode().removeChild(item);
            }

        }
        // remove outgoing childs...
        NodeList outgoingSequenceFlows = getElementNode()
                .getElementsByTagName(bpmnProcess.getModel().getPrefix(BPMNNS.BPMN2) + ":outgoing");
        for (int i = 0; i < outgoingSequenceFlows.getLength(); i++) {
            Element item = (Element) outgoingSequenceFlows.item(i);
            // test if the sequence flow exists...
            BPMNElementEdge flow = bpmnProcess.findElementEdgeById(item.getTextContent());
            if (flow != null &&
                    (!getId().equals(flow.getElementNode().getAttribute("sourceRef"))
                            && !getId().equals(flow.getElementNode().getAttribute("targetRef")))) {
                // invalid element
                getElementNode().removeChild(item);
            }
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
    public abstract double getDefaultHeight();

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

    /**
     * Returns a List of all outgoing SequenceFlows associated with this element
     * 
     * @return
     */
    public Set<SequenceFlow> getOutgoingSequenceFlows() {
        // filter all sequenceFlows with a sourceRef to this elementNode
        Set<SequenceFlow> result = this.bpmnProcess.getSequenceFlows()
                .stream()
                .filter(c -> c.sourceRef.equals(this.getId()))
                .collect(Collectors.toSet());
        return result;

    }

    /**
     * Returns a List of all ingoing SequenceFlows associated with this element
     * 
     * @return
     */
    public Set<SequenceFlow> getIngoingSequenceFlows() {
        // filter all sequenceFlows with a sourceRef to this elementNode
        Set<SequenceFlow> result = this.bpmnProcess.getSequenceFlows()
                .stream()
                .filter(c -> c.targetRef.equals(this.getId()))
                .collect(Collectors.toSet());
        return result;

    }

    /**
     * Returns a List of all Associations associated with this element
     * 
     * @return
     */
    public Set<Association> getAssociations() {
        // filter all Associations with a sourceRef or targetRef to this elementNode
        Set<Association> result = this.bpmnProcess.getAssociations()
                .stream()
                .filter(c -> c.getSourceRef().equals(this.getId()) || c.getTargetRef().equals(this.getId()))
                .collect(Collectors.toSet());
        return result;

    }
}
