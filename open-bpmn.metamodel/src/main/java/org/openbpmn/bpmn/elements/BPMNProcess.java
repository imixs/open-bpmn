package org.openbpmn.bpmn.elements;

import java.util.HashSet;
import java.util.Set;

import org.openbpmn.bpmn.elements.BPMNActivity.TaskType;
import org.openbpmn.bpmn.elements.BPMNEvent.EventType;
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
    public void addEvent(String id, String name, EventType type) {
      
        Element eventElement = this.getElementNode().getOwnerDocument().createElement(type.getBpmnType());
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        this.getElementNode().appendChild(eventElement);

        BPMNEvent event = new BPMNEvent(type, eventElement);
        getEvents().add(event);
    }
    /**
     * Adds a new Task
     * <p>
     *  <bpmn2:sendTask id="SendTask_1" name="Send Task 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */
    public void addTask(String id, String name, TaskType type) {
      
        Element taskElement = this.getElementNode().getOwnerDocument().createElement(type.getBpmnType());
        taskElement.setAttribute("id", id);
        taskElement.setAttribute("name", name);
        this.getElementNode().appendChild(taskElement);

        BPMNActivity task = new BPMNActivity(type, taskElement);
        getActivities().add(task);
    }
}
