package org.openbpmn.bpmn.elements;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.exceptions.BPMNInvalidIDException;
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
 * <p>
 * The processType defines the level of abstraction modeled by this Process. A
 * “private Process“ is modeled within a Pool showing those flow elements that
 * are relevant to a specific “internal“ part of the organization.
 * <p>
 * A “public Process“ is modeled without a surrounding Pool and shows only those
 * flow elements that are relevant to “external“ consumers. By default
 * processType “none” meaning undefined is not used by OpenBPMN.
 * 
 * Note: Each processType can be used within a Collaboration .
 * 
 * @author rsoika
 *
 */
public class BPMNProcess extends BPMNBaseElement {

    protected String processType = BPMNTypes.PROCESS_TYPE_NONE;
    protected Set<BPMNActivity> activities = null;
    protected Set<BPMNEvent> events = null;
    protected Set<BPMNGateway> gateways = null;
    protected Set<BPMNSequenceFlow> sequenceFlows = null;
    protected Set<BPMNLane> lanes = null;
    protected Element laneSet = null;

    private boolean initalized = false;

    public BPMNProcess() {
        super();
    }

    /**
     * The method creates a BPMNProcess instance form a bpmn2:process model element.
     * <p>
     * The method also verify the processType attribute and defaults to 'Public' if
     * no processType is set or is invalid.
     * 
     * @param model
     * @param element
     */
    public BPMNProcess(BPMNModel model, Element element, String processType) {
        super(model, element);

        // set public if not yet specified
        if (processType == null || processType.isEmpty() || (!BPMNTypes.PROCESS_TYPE_PRIVATE.equals(processType)
                && !BPMNTypes.PROCESS_TYPE_PUBLIC.equals(processType))) {
            BPMNModel.getLogger().warning("bpmn2:process does not define a valid processType - default to 'Public'");
            processType = BPMNTypes.PROCESS_TYPE_PUBLIC;
            element.setAttribute("processType", processType);
        }
        setProcessType(processType);
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    /**
     * This method parses the content of the process element and adds all tasks,
     * gateways and events. This is a lazy loading mechanism called by the BPMNModel
     * method OpenProcess()
     * 
     * @throws BPMNModelException
     */
    public void init() throws BPMNModelException {
        if (!initalized) {
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
                    this.createBPMNActivityByNode((Element) child);
                } else if (BPMNModel.isEvent(child)) {
                    this.createBPMNEventByNode((Element) child);
                } else if (BPMNModel.isGateway(child)) {
                    this.createBPMNGatewayByNode((Element) child);
                } else if (BPMNModel.isSequenceFlow(child)) {
                    this.createBPMNSequenceFlowByNode((Element) child);
                } else if (BPMNModel.isLaneSet(child)) {
                    this.createBPMNLanesByNode((Element) child);
                } else {
                    // unsupported node type
                }
            }
            initalized = true;
        }
    }

    /**
     * Returns all BPMNFlowElements contained in this process
     * 
     * @return
     */
    public Set<BPMNFlowElement> getBPMNFlowElements() {
        Set<BPMNFlowElement> result = new LinkedHashSet<BPMNFlowElement>();

        result.addAll(this.getActivities());
        result.addAll(this.getEvents());
        result.addAll(this.getGateways());
        return result;
    }

    /**
     * Returns all BPMNActivity elements contained in this process
     * 
     * @return
     */
    public Set<BPMNActivity> getActivities() {
        if (activities == null) {
            activities = new LinkedHashSet<BPMNActivity>();
        }
        return activities;
    }

    public void setActivities(Set<BPMNActivity> activities) {
        this.activities = activities;
    }

    public Set<BPMNEvent> getEvents() {
        if (events == null) {
            events = new LinkedHashSet<BPMNEvent>();
        }
        return events;
    }

    public void setEvents(Set<BPMNEvent> events) {
        this.events = events;
    }

    public Set<BPMNGateway> getGateways() {
        if (gateways == null) {
            gateways = new LinkedHashSet<BPMNGateway>();
        }
        return gateways;
    }

    public void setGateways(Set<BPMNGateway> gateways) {
        this.gateways = gateways;
    }

    public Set<BPMNSequenceFlow> getSequenceFlows() {
        if (sequenceFlows == null) {
            sequenceFlows = new LinkedHashSet<BPMNSequenceFlow>();
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
     * Creates a new BPMNTask element.
     * <p>
     * <bpmn2:sendTask id="SendTask_1" name="Send Task 1">
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNActivity addTask(String id, String name, String type) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (BPMNActivity node : getActivities()) {
            if (node.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }

        // create a new Dom node...
        Element taskElement = model.createElement(BPMNNS.BPMN2, type);
        taskElement.setAttribute("id", id);
        taskElement.setAttribute("name", name);
        this.getElementNode().appendChild(taskElement);

        // add BPMNEvent instance
        BPMNActivity task = this.createBPMNActivityByNode(taskElement);

        return task;
    }

    /**
     * Creates a new BPMNEvent element and adds the element into this process
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
    public BPMNEvent addEvent(String id, String name, String type) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (BPMNEvent node : getEvents()) {
            if (node.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }

        // create a new Dom node...
        Element eventElement = model.createElement(BPMNNS.BPMN2, type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        this.getElementNode().appendChild(eventElement);

        // add BPMNEvent instance
        BPMNEvent event = this.createBPMNEventByNode(eventElement);

        return event;

    }

    /**
     * Creates a new BPMNGateway element and adds the element into this process
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
    public BPMNGateway addGateway(String id, String name, String type) throws BPMNModelException {
        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (BPMNGateway node : getGateways()) {
            if (node.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }
        // create a new Dom node...
        Element eventElement = model.createElement(BPMNNS.BPMN2, type);
        eventElement.setAttribute("id", id);
        eventElement.setAttribute("name", name);
        // set a default
        eventElement.setAttribute("gatewayDirection", "Diverging");

        this.getElementNode().appendChild(eventElement);

        // add BPMNGateway instance
        BPMNGateway gateway = this.createBPMNGatewayByNode(eventElement);
        return gateway;

    }

    /**
     * Creates a new BPMNSequenceFlow element and adds the element into this process
     * context.
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
    public BPMNSequenceFlow addSequenceFlow(String id, String source, String target)
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
        BPMNSequenceFlow flow = this.createBPMNSequenceFlowByNode(sequenceFlow);
        // add default waypoints
        //flow.addWayPoint(sourceElement.getBounds().getCenter());
        //flow.addWayPoint(targetElement.getBounds().getCenter());
        return flow;

    }

    /**
     * Creates a new BPMNLane element and adds the element into this process
     * context.
     * <p>
     * The method verifies if a the process already has a bpmn2:laneSet section. If
     * not, the method creates one.
     * <p>
     * {@code
     * <bpmn2:laneSet id="LaneSet_1" name="Lane Set 1">
     * <bpmn2:lane id="Lane_1" name="Lane 1">
     * }
     * 
     * @param model - current model instance
     * @param name  - name of the Lane
     * @return BPMNLane
     * @throws BPMNMissingElementException
     */
    public BPMNLane addLane(BPMNModel model, String name) throws BPMNMissingElementException {

        if (!BPMNTypes.PROCESS_TYPE_PRIVATE.equals(this.getProcessType())) {
            throw new BPMNMissingElementException(BPMNMissingElementException.MISSING_ELEMENT,
                    "Lanes can only be added to a private process instance");
        }

        // first verify if the process already contains a LaneSet. If not we create
        // a bpmn2:laneSet
        if (this.laneSet == null) {

            BPMNModel.log("create laneset...");
            // create the default collaboration element
            String laneSetID = "laneset_1";
            laneSet = model.createElement(BPMNNS.BPMN2, "laneSet");
            laneSet.setAttribute("id", laneSetID);
            laneSet.setAttribute("name", "Lane Set 1");
            this.getElementNode().insertBefore(laneSet, this.getElementNode().getFirstChild());
        }
        // add the new Lane
        Element lane = model.createElement(BPMNNS.BPMN2, "lane");
        String laneId = BPMNModel.generateShortID("lane");
        lane.setAttribute("id", laneId);
        lane.setAttribute("name", name);
        laneSet.appendChild(lane);

        BPMNModel.debug("new lane '" + laneId + "' added");
        BPMNLane bpmnLane = new BPMNLane(model, lane);

        // Autocompute the Lane Bounds
        BPMNParticipant bpmnParticipant = model.findBPMNParticipantByProcessId(this.getId());
        if (bpmnParticipant != null) {
            // add shape
            // create shape element
            /*
             * <bpmndi:BPMNShape id="BPMNShape_Lane_1" bpmnElement="Lane_1"
             * isHorizontal="true"> <dc:Bounds height="150.0" width="500.0" x="130.0"
             * y="100.0"/> </bpmndi:BPMNShape>
             */

            // if the pool already contains lanes, than we autoincrease the height of the
            // pool
            int currentWidth = (int) bpmnParticipant.getBounds().getDimension().getWidth();
            int currentHeight = (int) bpmnParticipant.getBounds().getDimension().getHeight();
            int expansion = 0;
            int currentLaneCount = this.getLanes().size();
            if (currentLaneCount > 0) {
                // increase height...
                expansion = currentHeight / currentLaneCount;
                bpmnParticipant.getBounds().setDimension(currentWidth, currentHeight + expansion);
            }

            Element lanebpmnShape = model.createElement(BPMNNS.BPMNDI, "BPMNShape");
            lanebpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape_Lane"));
            lanebpmnShape.setAttribute("bpmnElement", laneId);
            model.getBpmnPlane().appendChild(lanebpmnShape);
            bpmnLane.setBpmnShape(lanebpmnShape);

            BPMNBounds poolBounds = bpmnParticipant.getBounds();

            int laneX = (int) (poolBounds.getPosition().getX() + BPMNParticipant.POOL_OFFSET);
            int laneY = (int) poolBounds.getPosition().getY();
            if (currentLaneCount > 0) {
                laneY = laneY + (currentLaneCount * expansion);
            }

            int laneWidth = (int) (poolBounds.getDimension().getWidth() - BPMNParticipant.POOL_OFFSET);
            int laneHeight = (int) (poolBounds.getDimension().getHeight() / (currentLaneCount + 1));
            if (currentLaneCount > 0) {
                // overlap lanes by 1 pixel
                // laneY--;
                // laneHeight++;
            }

            bpmnLane.getBounds().setDimension(laneWidth, laneHeight);
            bpmnLane.getBounds().setPosition(laneX, laneY);

        }

        this.getLanes().add(bpmnLane);
        return bpmnLane;
    }

    /**
     * Returns a BPMNLane by a LaneID. The method throws a
     * BPMNInvalidReferenceException if a lane with the given ID does not exist is
     * this process.
     * <p>
     * {@code
     *  <bpmn2:laneSet id="LaneSet_1" name="Lane Set 1">
          <bpmn2:lane id="Lane_1" name="Lane 1">
             <bpmn2:flowNodeRef>StartEvent_4</bpmn2:flowNodeRef>
        }
     * 
     * @param element
     * @param laneId
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     */
    public BPMNLane findLane(String laneId) throws BPMNInvalidReferenceException, BPMNMissingElementException {
        if (laneId == null || laneId.isEmpty()) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE, "laneId not set");
        }
        if (laneSet == null || laneId.isEmpty()) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "process '" + this.getId() + "' does not define a laneSet");
        }
        // find the lane
        Set<Element> lanes = BPMNModel.findChildNodesByName(laneSet, "bpmn2:lane");
        Element lane = null;
        for (Element _lane : lanes) {
            if (laneId.equals(_lane.getAttribute("id"))) {
                lane = _lane;
                break;
            }
        }
        if (lane == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "lane '" + laneId + "' not defined in current laneSet");
        }

        return new BPMNLane(model, lane);

    }

    /**
     * Returns true if the process contains a laneset
     * 
     * @return
     */
    public boolean hasLanes() {
        return (lanes != null);
    }

    public Set<BPMNLane> getLanes() {
        if (lanes == null) {
            lanes = new LinkedHashSet<BPMNLane>();
        }
        return lanes;
    }

    public void setLanes(Set<BPMNLane> lanes) {
        this.lanes = lanes;
    }

    /**
     * Deletes a BPMNLane and all referred element from this process.
     * <p>
     * 
     * @param id
     */
    public void deleteLane(String id) {
        BPMNLane lane = (BPMNLane) findBaseElementById(id);
        if (lane == null) {
            // does not exist
            return;
        }

        Set<String> flowElementList = lane.getFlowElementIDs();
        for (String flowEleemntID : flowElementList) {
            this.deleteBPMNBaseElement(flowEleemntID);
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(lane.getElementNode());
        if (lane.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(lane.getBpmnShape());
        }
        this.getLanes().remove(lane);
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
        Set<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(task.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(task.getElementNode());
        if (task.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(task.getBpmnShape());
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
        Set<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(event.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(event.getElementNode());
        if (event.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(event.getBpmnShape());
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
        Set<BPMNSequenceFlow> flowList = findSequenceFlowsByElementId(getway.getId());

        // remove all flows
        for (BPMNSequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // finally delete the task element and the shape
        this.getElementNode().removeChild(getway.getElementNode());
        if (getway.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(getway.getBpmnShape());
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
            model.getBpmnPlane().removeChild(seqenceFlow.getBpmnEdge());
        }

        this.getSequenceFlows().remove(seqenceFlow);
    }

    /**
     * Deletes a BPMNBase element from this process
     * 
     * @param id
     */
    public void deleteBPMNBaseElement(String id) {

        BPMNBaseElement baseElement = findBaseElementById(id);
        if (baseElement instanceof BPMNLane) {
            this.deleteLane(id);
        }
        if (baseElement instanceof BPMNActivity) {
            this.deleteTask(id);
        }
        if (baseElement instanceof BPMNEvent) {
            this.deleteEvent(id);
        }
        if (baseElement instanceof BPMNGateway) {
            this.deleteGateway(id);
        }
        if (baseElement instanceof BPMNSequenceFlow) {
            this.deleteSequenceFlow(id);
        }
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

        // test Lanes...
        Set<BPMNLane> lanes = this.getLanes();
        for (BPMNLane element : lanes) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        // test FlowElements...
        BPMNBaseElement result = findBPMNFlowElementById(id);
        if (result != null) {
            return result;
        }

        // test SequenceFlows
        result = this.findBPMNSequenceFlowById(id);
        if (result != null) {
            return result;
        }

        // no match!
        return null;
    }

    /**
     * Finds a BPMNFlowElement by ID within the current process.
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
     * Parses a LaneSet Node and creates a set of BPMNLane objects
     * <p>
     * 
     * <pre>
     * {@code
        <bpmn2:laneSet id="laneset_1" name="Lane Set 1">
          <bpmn2:lane id="lane_3upqyg" name="Lane 1">
            <bpmn2:flowNodeRef>task_1</bpmn2:flowNodeRef>
          </bpmn2:lane>
        </bpmn2:laneSet>
     * }
     * 
     * @param element - a bpmn node
     * @return
     * @throws BPMNModelException
     */
    private void createBPMNLanesByNode(Element laneSet) {
        this.laneSet = laneSet;
        // find Child Nodes
        Set<Element> laneNodes = BPMNModel.findChildNodesByName(laneSet, BPMNNS.BPMN2.prefix + ":lane");
        for (Element _lane : laneNodes) {
            BPMNLane bpmnLane = new BPMNLane(model, _lane);
            this.getLanes().add(bpmnLane);
        }
    }

    private BPMNActivity createBPMNActivityByNode(Element element) throws BPMNModelException {
        BPMNActivity task = new BPMNActivity(model, element, element.getLocalName(), this);
        getActivities().add(task);
        return task;
    }

    /**
     * Creates a new BPMNEvent from a existing BPMN Node and adds the BPMNEvent into
     * the event list
     * 
     * Adds a new BPMNEvent from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNEvent createBPMNEventByNode(Element element) throws BPMNModelException {
        BPMNEvent event = new BPMNEvent(model, element, element.getLocalName(), this);
        getEvents().add(event);
        return event;
    }

    /**
     * Adds a new BPMNGateway from a existing Element Node
     * 
     * @param eventElement
     * @return
     * @throws BPMNModelException
     */
    private BPMNGateway createBPMNGatewayByNode(Element element) throws BPMNModelException {
        BPMNGateway gateway = new BPMNGateway(model, element, element.getLocalName(), this);
        getGateways().add(gateway);
        return gateway;
    }

    /**
     * Adds a new BPMNSequenceFlow from a existing Element Node
     * 
     * @param eventElement
     * @return
     */
    private BPMNSequenceFlow createBPMNSequenceFlowByNode(Element element) {
        BPMNSequenceFlow flow = new BPMNSequenceFlow(model, element, this);
        getSequenceFlows().add(flow);
        return flow;
    }

    /**
     * Returns a list of sequenceFlows associated with a given FlowElement
     * 
     * @param id of a flowElement
     * @return list of SequenceFlows
     */
    private Set<BPMNSequenceFlow> findSequenceFlowsByElementId(String id) {
        Set<BPMNSequenceFlow> result = new LinkedHashSet<BPMNSequenceFlow>();
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
     * This method inserts a lane before a target lane within an existing laneset. A
     * client need to re-initalize the process after an insert.
     * 
     * @param laneTest
     * @param lane2
     */
    public void insertLaneBefore(BPMNLane laneTest, BPMNLane lane2) {

        this.laneSet.insertBefore(laneTest.getElementNode(), lane2.getElementNode());
        // reorder the BPMNLanes
        BPMNParticipant bpmnParticipant = model.findBPMNParticipantByProcessId(this.getId());
        LinkedHashSet<BPMNLane> newOrderedLaneSet = new LinkedHashSet<BPMNLane>();
        Iterator<BPMNLane> laneIterator = this.lanes.iterator();
        while (laneIterator.hasNext()) {
            BPMNLane aLane = laneIterator.next();
            if (!aLane.getId().equals(laneTest.getId()) && !aLane.getId().equals(lane2.getId())) {
                newOrderedLaneSet.add(aLane);
                continue;
            }
            if (aLane.getId().equals(lane2.getId())) {
                newOrderedLaneSet.add(laneTest);
                newOrderedLaneSet.add(aLane);
                continue;
            }
            if (aLane.getId().equals(laneTest.getId())) {
                continue;
            }
        }

        this.setLanes(newOrderedLaneSet);
        try { // now recompute the position of the lanes according to their order within the
              // laneset
            BPMNBounds poolBounds = bpmnParticipant.getBounds();
            int bpmnLaneX = (int) (poolBounds.getPosition().getX() + BPMNParticipant.POOL_OFFSET);
            int bpmnLaneY = (int) poolBounds.getPosition().getY();
            Iterator<BPMNLane> laneSetIterator = this.getLanes().iterator();
            while (laneSetIterator.hasNext()) {
                // get BPMNBounds and GNode for this lane...
                BPMNLane lane = laneSetIterator.next();
                // System.out.println(" --->" + lane.getId() + " new yPos="+bpmnLaneY);
                BPMNBounds bpmnLaneBounds = lane.getBounds();
                // Update absolute BPMN position
                bpmnLaneBounds.setPosition(bpmnLaneX, bpmnLaneY);
                // adjust laneY for the next iteration
                bpmnLaneY = (int) (bpmnLaneY + bpmnLaneBounds.getDimension().getHeight());
            }
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Error updating bonds for LaneSet: " + e.getMessage());
        }

    }

}
