package org.openbpmn.bpmn.elements;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A Process describes a sequence or flow of Activities in an organization with
 * the objective of carrying out work. In BPMN a Process is depicted as a graph
 * of Flow Elements, which are a set of Activities, Events, Gateways, and
 * Sequence Flows that define finite execution semantics.
 * 
 * @author rsoika
 *
 */
public class Process extends BaseElement {

    protected Set<Activity> activities = null;
    protected Set<Event> events = null;
    protected Set<Gateway> gateways = null;
    protected Set<SequenceFlow> sequenceFlows = null;

    public Process() {
        super();
    }

    public Process(Map<String, String> attributes) {
        super(attributes);
    }

    public Set<Activity> getActivities() {
        if (activities == null) {
            activities = new HashSet<Activity>();
        }
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Event> getEvents() {
        if (events == null) {
            events = new HashSet<Event>();
        }
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Gateway> getGateways() {
        if (gateways == null) {
            gateways = new HashSet<Gateway>();
        }
        return gateways;
    }

    public void setGateways(Set<Gateway> gateways) {
        this.gateways = gateways;
    }

    public Set<SequenceFlow> getSequenceFlows() {
        if (sequenceFlows == null) {
            sequenceFlows = new HashSet<SequenceFlow>();
        }
        return sequenceFlows;
    }

    public void setSequenceFlows(Set<SequenceFlow> sequenceFlows) {
        this.sequenceFlows = sequenceFlows;
    }

}
