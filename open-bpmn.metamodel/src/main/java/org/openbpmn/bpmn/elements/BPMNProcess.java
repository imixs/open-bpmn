package org.openbpmn.bpmn.elements;

import java.util.HashSet;
import java.util.Set;

import org.openbpmn.bpmn.BPMNEventType;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTaskType;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A Process describes a sequence or flow of Activities in an organization with
 * the objective of carrying out work. In BPMN a Process is depicted as a graph
 * of Flow Elements, which are a set of Activities, Events, Gateways, and
 * Sequence Flows that define finite execution semantics.
 * 
 * @author rsoika
 *
 */
public class BPMNProcess extends BPMNBaseElement {

    
    
    protected Set<BPMNActivity> activities = null;
    protected Set<BPMNEvent> events = null;
    protected Set<BPMNGateway> gateways = null;
    protected Set<BPMNSequenceFlow> sequenceFlows = null;

    protected Node bpmnPlane = null;

    public BPMNProcess() {
        super();
    }

    public BPMNProcess(Node node) {
        super(node);
    }

    public Set<BPMNActivity> getActivities() {
        if (activities == null) {
            activities = new HashSet<BPMNActivity>();
        }
        return activities;
    }

    public void setActivities(Set<BPMNActivity> activities) {
        this.activities = activities;
    }

    public Node getBpmnPlane() {
        return bpmnPlane;
    }

    public void setBpmnPlane(Node bpmnPlane) {
        this.bpmnPlane = bpmnPlane;
    }

    public Set<BPMNEvent> getEvents() {
        if (events == null) {
            events = new HashSet<BPMNEvent>();
        }
        return events;
    }

    public void setEvents(Set<BPMNEvent> events) {
        this.events = events;
    }

    public Set<BPMNGateway> getGateways() {
        if (gateways == null) {
            gateways = new HashSet<BPMNGateway>();
        }
        return gateways;
    }

    public void setGateways(Set<BPMNGateway> gateways) {
        this.gateways = gateways;
    }

    public Set<BPMNSequenceFlow> getSequenceFlows() {
        if (sequenceFlows == null) {
            sequenceFlows = new HashSet<BPMNSequenceFlow>();
        }
        return sequenceFlows;
    }

    public void setSequenceFlows(Set<BPMNSequenceFlow> sequenceFlows) {
        this.sequenceFlows = sequenceFlows;
    }

    /**
     * Helper method to get the SequenceFlow by id
     * 
     * @param id
     * @return
     */
    public BPMNSequenceFlow getSequenceFlowByID(String id) {
        for (BPMNSequenceFlow sf : this.sequenceFlows) {
            if (id.equals(sf.getId())) {
                return sf;
            }
        }
        return null;
    }

    /**
     * Adds a new event
     * <p>
     * <bpmn2:startEvent id="StartEvent_1" name="Start Event 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */ 
    public void addEvent(String id, String name, BPMNEventType type) {

        Element eventElement = this.getDoc().createElement(BPMNModel.BPMN_NS+":"+type.getName());
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        this.getElementNode().appendChild(eventElement);

        BPMNEvent event = new BPMNEvent(type, eventElement);
        getEvents().add(event);
    }

    /**
     * Adds a new Task
     * <p>
     * <bpmn2:sendTask id="SendTask_1" name="Send Task 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */ 
    public void addTask(String id, String name, BPMNTaskType type) {

        Element taskElement = this.getDoc().createElement(BPMNModel.BPMN_NS+":"+type.getName());
        taskElement.setAttribute("id", id);
        taskElement.setAttribute("name", name);
        this.getElementNode().appendChild(taskElement);

        BPMNActivity task = new BPMNActivity(type, taskElement);
        getActivities().add(task);
    }

    /**
     * Adds a new SequenceFlow
     * <p>
     * <bpmn2:sequenceFlow id="SequenceFlow_4" sourceRef="Task_1" targetRef=
     * "SendTask_1"/>
     * 
     * @param id
     * @param source - ID of the source element 
     * @param target - ID of the target element 
     * @throws BPMNInvalidReferenceException 
     */
    public void addSequenceFlow(String id, String source, String target) throws BPMNInvalidReferenceException {

        // validate IDs
        BPMNFlowElement sourceElement = findFlowElementById(source);
        BPMNFlowElement targetElement = findFlowElementById(target);

        if (sourceElement == null || targetElement == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source or Target ID not set");
        }
        if (sourceElement.getId().equals(targetElement.getId())) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target ID can not be the same");
        }
        
        // create sequenceFlow element
        Element sequenceFlow = this.getDoc().createElement(BPMNModel.BPMN_NS + ":sequenceFlow");
        sequenceFlow.setAttribute("id", id);
        sequenceFlow.setAttribute("sourceRef", source);
        sequenceFlow.setAttribute("targetRef", target);
        this.getElementNode().appendChild(sequenceFlow);

        // add outgoing reference to source element
        Element refOut = this.getDoc().createElement(BPMNModel.BPMN_NS + ":outgoing");
        refOut.setTextContent(id);
        sourceElement.getElementNode().appendChild(refOut);
        // add incoming reference to target element
        Element refIn = this.getDoc().createElement(BPMNModel.BPMN_NS + ":incoming");
        refIn.setTextContent(id);
        targetElement.getElementNode().appendChild(refIn);

        BPMNSequenceFlow flow = new BPMNSequenceFlow(sequenceFlow);
        getSequenceFlows().add(flow); 
    }

    /**
     * Returns a FlowElement by id
     * 
     * @param id
     * @return
     */
    public BPMNFlowElement findFlowElementById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Set<BPMNActivity> listA = this.getActivities();
        for (BPMNActivity element : listA) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<BPMNEvent> listE = this.getEvents();
        for (BPMNEvent element : listE) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<BPMNGateway> listG = this.getGateways();
        for (BPMNGateway element : listG) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        return null;
    }
}
