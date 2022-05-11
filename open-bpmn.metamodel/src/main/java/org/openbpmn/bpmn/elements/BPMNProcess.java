package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
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
     * Creates a new BPMNTask element in this context.
     * <p>
     * <bpmn2:sendTask id="SendTask_1" name="Send Task 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */
    public BPMNActivity addTask(String id, String name, String type) {

        // create a new Dom node...
        Element taskElement = this.getDoc().createElement(BPMNModel.BPMN_NS + ":" + type);
        taskElement.setAttribute("id", id);
        taskElement.setAttribute("name", name);
        this.getElementNode().appendChild(taskElement);

        // build a BPMNActivity instance
        BPMNActivity task = new BPMNActivity(type, taskElement);
        // create a new BPMNShape
        // <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
        Element bpmnShape = this.getDoc().createElement(BPMNModel.DI_NS + ":BPMNShape");
        bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
        bpmnShape.setAttribute("bpmnElement", id);

        this.getBpmnPlane().appendChild(bpmnShape);
        task.setBpmnShape(bpmnShape);

        getActivities().add(task);
        return task;
    }

    /**
     * Adds a new event
     * <p>
     * <bpmn2:exclusiveGateway id="ExclusiveGateway_1" name="Exclusive Gateway 1"
     * gatewayDirection="Diverging">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */
    public BPMNEvent addEvent(String id, String name, String type) {
        // create a new Dom node...
        Element eventElement = this.getDoc().createElement(BPMNModel.BPMN_NS + ":" + type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        this.getElementNode().appendChild(eventElement);

        // build a BPMNActivity instance
        BPMNEvent event = new BPMNEvent(type, eventElement);
        // create a new BPMNShape
        // <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
        Element bpmnShape = this.getDoc().createElement(BPMNModel.DI_NS + ":BPMNShape");
        bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
        bpmnShape.setAttribute("bpmnElement", id);

        this.getBpmnPlane().appendChild(bpmnShape);
        event.setBpmnShape(bpmnShape);

        getEvents().add(event);
        return event;

    }

    /**
     * Adds a new gateway
     * <p>
     * <bpmn2:startEvent id="StartEvent_1" name="Start Event 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     */
    public BPMNGateway addGateway(String id, String name, String type) {
        // create a new Dom node...
        Element eventElement = this.getDoc().createElement(BPMNModel.BPMN_NS + ":" + type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        // set a default
        eventElement.setAttribute("gatewayDirection", "Diverging");

        this.getElementNode().appendChild(eventElement);

        // build a BPMNGateway instance
        BPMNGateway gateway = new BPMNGateway(type, eventElement);
        // create a new BPMNShape
        // <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
        Element bpmnShape = this.getDoc().createElement(BPMNModel.DI_NS + ":BPMNShape");
        bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
        bpmnShape.setAttribute("bpmnElement", id);

        this.getBpmnPlane().appendChild(bpmnShape);
        gateway.setBpmnShape(bpmnShape);

        getGateways().add(gateway);
        return gateway;

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
        // first we need to update the elemnts still connected with this flow
        // <bpmn2:incoming>SequenceFlow_4</bpmn2:incoming>
        // <bpmn2:outgoing>SequenceFlow_5</bpmn2:outgoing>
        BPMNBaseElement targetElement = findBaseElementById(targetRef);
        if (targetElement != null) {
            NodeList childs = targetElement.getElementNode().getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE
                        && (child.getNodeName().equals(BPMNModel.BPMN_NS + ":incoming")
                                || child.getNodeName().equals(BPMNModel.BPMN_NS + ":outgoing"))) {
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
                        && (child.getNodeName().equals(BPMNModel.BPMN_NS + ":incoming")
                                || child.getNodeName().equals(BPMNModel.BPMN_NS + ":outgoing"))) {
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
     * Returns a BPMNBaseElement by given id. <p If no element with the given ID
     * exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNBaseElement findBaseElementById(String id) {
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
    public List<BPMNSequenceFlow> findSequenceFlowsByElementId(String id) {
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
     */
    public void init() {
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
                BPMNActivity activity = new BPMNActivity(resolveBPMNElementType(child), child);

                activity.setBpmnShape(BPMNModel.findChildNodeByName(this.getBpmnPlane(), BPMNModel.DI_NS + ":BPMNShape",
                        activity.getId()));
                getActivities().add(activity);
            } else if (BPMNModel.isEvent(child)) {
                BPMNEvent event = new BPMNEvent(resolveBPMNElementType(child), child);
                event.setBpmnShape(BPMNModel.findChildNodeByName(this.getBpmnPlane(), BPMNModel.DI_NS + ":BPMNShape",
                        event.getId()));
                getEvents().add(event);
            } else if (BPMNModel.isGateway(child)) {
                BPMNGateway gateway = new BPMNGateway(resolveBPMNElementType(child), child);
                gateway.setBpmnShape(BPMNModel.findChildNodeByName(this.getBpmnPlane(), BPMNModel.DI_NS + ":BPMNShape",
                        gateway.getId()));
                getGateways().add(gateway);
            } else if (BPMNModel.isSequenceFlow(child)) {
                BPMNSequenceFlow sequenceFlow = BPMNModel.buildSequenceFlow(child, this);

                sequenceFlow.setBpmnEdge(BPMNModel.findChildNodeByName(this.getBpmnPlane(),
                        BPMNModel.DI_NS + ":BPMNEdge", sequenceFlow.getId()));

                getSequenceFlows().add(sequenceFlow);

            } else {
                // unsupported node type
            }
        }

    }

    /**
     * This helper method resolves the BPMN Type out of the nodes NodeName
     * <p>
     * bpmn2:task => task
     * <p>
     * bpmn2:startEvent => startEvent
     * 
     * @param nodeName
     * @return
     */
    private String resolveBPMNElementType(Node child) {
        String nodeName = child.getNodeName();
        // in case the type starts with a namespace than we cut this prafix
        if (nodeName.startsWith(BPMNModel.BPMN_NS)) {
            nodeName = nodeName.substring(BPMNModel.BPMN_NS.length() + 1);
        }
        return nodeName;
    }
}
