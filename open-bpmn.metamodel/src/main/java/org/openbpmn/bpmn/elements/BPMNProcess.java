package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    protected Set<BPMNParticipant> participants = null;
    protected Set<BPMNSequenceFlow> sequenceFlows = null;

    protected Node bpmnPlane = null;

    public BPMNProcess() {
        super();
    }

    public BPMNProcess(BPMNModel model, Element item) {
        super(model, item);
    }

    public BPMNModel getModel() {
        return model;
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

    public Set<BPMNParticipant> getParticipants() {
        if (participants == null) {
            participants = new HashSet<BPMNParticipant>();
        }
        return participants;
    }

    public void setParticipants(Set<BPMNParticipant> participants) {
        this.participants = participants;
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
     * Builds a new BPMNTask element and adds this element into this process
     * context.
     * <p>
     * <bpmn2:sendTask id="SendTask_1" name="Send Task 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNActivity buildTask(String id, String name, String type) throws BPMNModelException {

        // create a new Dom node...
        Element taskElement = model.createElement(BPMNNS.BPMN2, type);
        taskElement.setAttribute("id", id);
        taskElement.setAttribute("name", name);
        this.getElementNode().appendChild(taskElement);

        // add BPMNEvent instance
        BPMNActivity task = this.addActivity(taskElement);
        return task;
    }

    /**
     * Adds a new BPMNTask from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNActivity addActivity(Element element) throws BPMNModelException {
        BPMNActivity task = new BPMNActivity(model, element, element.getLocalName());
        getActivities().add(task);
        return task;
    }

    /**
     * Builds a new BPMNEvent element and adds this element into this process
     * context.
     * <p>
     * <bpmn2:startEvent id="StartEvent_1" name="Start Event 1">
     * 
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNEvent buildEvent(String id, String name, String type) throws BPMNModelException {
        // create a new Dom node...
        Element eventElement = model.createElement(BPMNNS.BPMN2, type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        this.getElementNode().appendChild(eventElement);

        // add BPMNEvent instance
        BPMNEvent event = this.addEvent(eventElement);

        return event;

    }

    /**
     * Adds a new BPMNEvent from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNEvent addEvent(Element element) throws BPMNModelException {
        BPMNEvent event = new BPMNEvent(model, element, element.getLocalName());
        getEvents().add(event);
        return event;
    }

    /**
     * Builds a new BPMNGateway element and adds this element into this process
     * context.
     * <p>
     * <bpmn2:exclusiveGateway id="ExclusiveGateway_1" name="Exclusive Gateway 1"
     * gatewayDirection="Diverging">
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNGateway buildGateway(String id, String name, String type) throws BPMNModelException {
        // create a new Dom node...
        Element eventElement = model.createElement(BPMNNS.BPMN2, type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        // set a default
        eventElement.setAttribute("gatewayDirection", "Diverging");

        this.getElementNode().appendChild(eventElement);

        // add BPMNGateway instance
        BPMNGateway gateway = this.addGateway(eventElement);
        return gateway;

    }

    /**
     * Adds a new BPMNGateway from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNGateway addGateway(Element element) throws BPMNModelException {
        BPMNGateway gateway = new BPMNGateway(model, element, element.getLocalName());
        getGateways().add(gateway);
        return gateway;
    }

    //

    /**
     * Builds a new BPMNParticipant element and adds this element into this process
     * context.
     * <p>
     * <bpmn2:participant id="Participant_1" name="Pool 1"/>
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNParticipant buildParticipant(String id, String name, String type) throws BPMNModelException {
        // create a new Dom node...
        Element eventElement = model.createElement(BPMNNS.BPMN2, type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);

        this.getElementNode().appendChild(eventElement);

        // add BPMNGateway instance
        BPMNParticipant participant = this.addParticipant(eventElement);
        return participant;

    }

    /**
     * Adds a new BPMNGateway from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNParticipant addParticipant(Element element) throws BPMNModelException {
        BPMNParticipant participant = new BPMNParticipant(model, element, element.getLocalName());
        getParticipants().add(participant);
        return participant;
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
     * @throws BPMNMissingElementException
     */
    public BPMNSequenceFlow buildSequenceFlow(String id, String source, String target)
            throws BPMNInvalidReferenceException, BPMNMissingElementException {

        // validate IDs
        BPMNFlowElement sourceElement = (BPMNFlowElement) findBaseElementById(source);
        BPMNFlowElement targetElement = (BPMNFlowElement) findBaseElementById(target);

        if (sourceElement == null || targetElement == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source or Target ID not set");
        }
        if (sourceElement.getId().equals(targetElement.getId())) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target ID can not be the same");
        }

        // create sequenceFlow element
        Element sequenceFlow = model.createElement(BPMNNS.BPMN2, "sequenceFlow");
        sequenceFlow.setAttribute("id", id);
        sequenceFlow.setAttribute("sourceRef", source);
        sequenceFlow.setAttribute("targetRef", target);

        this.getElementNode().appendChild(sequenceFlow);

        // add outgoing reference to source element
        Element refOut = model.createElement(BPMNNS.BPMN2, "outgoing");
        refOut.setTextContent(id);
        sourceElement.getElementNode().appendChild(refOut);
        // add incoming reference to target element
        Element refIn = model.createElement(BPMNNS.BPMN2, "incoming");
        refIn.setTextContent(id);
        targetElement.getElementNode().appendChild(refIn);

        // add BPMNGateway instance
        BPMNSequenceFlow flow = this.addSequenceFlow(sequenceFlow);
        // add default waypoints
        flow.addWayPoint(sourceElement.getBounds().getCenter());
        flow.addWayPoint(targetElement.getBounds().getCenter());
        return flow;

    }

    /**
     * Adds a new BPMNEvent from a existing Element Node
     * 
     * @param eventElement
     * @return
     */
    private BPMNSequenceFlow addSequenceFlow(Element element) {
        BPMNSequenceFlow flow = new BPMNSequenceFlow(model, element);
        getSequenceFlows().add(flow);
        return flow;
    }

    /**
     * Deletes a BPMNTask element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteTask(String id) {
        BPMNActivity task = (BPMNActivity) findBaseElementById(id);
        if (task == null) {
            // does not exist
            return;
        }
        List<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(task.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(task.getElementNode());
        if (task.getBpmnShape() != null) {
            this.getBpmnPlane().removeChild(task.getBpmnShape());
        }

        this.getActivities().remove(task);
    }

    /**
     * Deletes a BPMNEvent element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteEvent(String id) {
        BPMNEvent event = (BPMNEvent) findBaseElementById(id);
        if (event == null) {
            // does not exist
            return;
        }
        List<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(event.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(event.getElementNode());
        if (event.getBpmnShape() != null) {
            this.getBpmnPlane().removeChild(event.getBpmnShape());
        }

        this.getEvents().remove(event);
    }

    /**
     * Deletes a BPMNGateway element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteGateway(String id) {
        BPMNGateway getway = (BPMNGateway) findBaseElementById(id);
        if (getway == null) {
            // does not exist
            return;
        }
        List<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(getway.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(getway.getElementNode());
        if (getway.getBpmnShape() != null) {
            this.getBpmnPlane().removeChild(getway.getBpmnShape());
        }

        this.getGateways().remove(getway);
    }

    /**
     * Deletes a BPMNSequenceFlow element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteSequenceFlow(String id) {
        BPMNSequenceFlow seqenceFlow = (BPMNSequenceFlow) findBaseElementById(id);
        if (seqenceFlow == null) {
            // does not exist
            return;
        }

        String targetRef = seqenceFlow.getTargetRef();
        String soureRef = seqenceFlow.getSourceRef();
        // first we need to update the elements still connected with this flow
        // <bpmn2:incoming>SequenceFlow_4</bpmn2:incoming>
        // <bpmn2:outgoing>SequenceFlow_5</bpmn2:outgoing>
        BPMNBaseElement targetElement = findBaseElementById(targetRef);
        if (targetElement != null) {
            NodeList childs = targetElement.getElementNode().getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE
                        && (child.getNodeName().equals(BPMNNS.BPMN2.prefix + ":incoming")
                                || child.getNodeName().equals(BPMNNS.BPMN2.prefix + ":outgoing"))) {
                    if (id.equals(child.getTextContent())) {
                        targetElement.getElementNode().removeChild(child);
                        break;
                    }
                }
            }
        }
        BPMNBaseElement sourceElement = findBaseElementById(soureRef);
        if (sourceElement != null) {
            NodeList childs = sourceElement.getElementNode().getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE
                        && (child.getNodeName().equals(BPMNNS.BPMN2.prefix + ":incoming")
                                || child.getNodeName().equals(BPMNNS.BPMN2.prefix + ":outgoing"))) {
                    if (id.equals(child.getTextContent())) {
                        sourceElement.getElementNode().removeChild(child);
                        break;
                    }
                }
            }
        }

        // Finally delete the flow element and the edge
        this.getElementNode().removeChild(seqenceFlow.getElementNode());
        if (seqenceFlow.getBpmnEdge() != null) {
            this.getBpmnPlane().removeChild(seqenceFlow.getBpmnEdge());
        }

        this.getSequenceFlows().remove(seqenceFlow);
    }

    /**
     * Finds a BPMNBaseElement by given id.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNBaseElement findBaseElementById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        // test flowElements...
        BPMNBaseElement result = findBPMNFlowElementById(id);

        if (result != null) {
            return result;
        }

        // test sequence flows
        result = this.findBPMNSequenceFlowById(id);
        if (result != null) {
            return result;
        }

        // no match!
        return null;
    }

    /**
     * Finds a BPMNFlowElement by ID
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNFlowElement findBPMNFlowElementById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        Set<BPMNParticipant> listP = this.getParticipants();
        for (BPMNParticipant element : listP) {
            if (id.equals(element.getId())) {
                return element;
            }
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

    /**
     * Finds a BPMNSequence by ID
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNSequenceFlow findBPMNSequenceFlowById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Set<BPMNSequenceFlow> listF = this.getSequenceFlows();
        for (BPMNSequenceFlow element : listF) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        return null;
    }

    /**
     * Returns a list of sequenceFlows associated with a given FlowElement
     * 
     * @param id of a flowElement
     * @return list of SequenceFlows
     */
    private List<BPMNSequenceFlow> findSequenceFlowsByElementId(String id) {
        List<BPMNSequenceFlow> result = new ArrayList<BPMNSequenceFlow>();
        if (id == null || id.isEmpty()) {
            return result;
        }
        Set<BPMNSequenceFlow> listA = this.getSequenceFlows();
        for (BPMNSequenceFlow flow : listA) {
            if (id.equals(flow.sourceRef) || id.equals(flow.targetRef)) {
                result.add(flow);
            }
        }
        return result;
    }

    /**
     * This method parses the content of the process element and adds all tasks,
     * gateways and events
     * 
     * @throws BPMNModelException
     */
    public void init() throws BPMNModelException {
        // now find all relevant bpmn meta elements
        NodeList childs = this.getElementNode().getChildNodes();
        for (int j = 0; j < childs.getLength(); j++) {
            Node child = childs.item(j);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                // continue if not a new element node
                continue;
            }

            // check element type
            if (BPMNModel.isActivity(child)) {
                this.addActivity((Element) child);
            } else if (BPMNModel.isEvent(child)) {
                this.addEvent((Element) child);
            } else if (BPMNModel.isGateway(child)) {
                this.addGateway((Element) child);
            } else if (BPMNModel.isSequenceFlow(child)) {
                this.addSequenceFlow((Element) child);
            } else if (BPMNModel.isParticipant(child)) {
                this.addParticipant((Element) child);
            } else {
                // unsupported node type
            }
        }

    }

}
