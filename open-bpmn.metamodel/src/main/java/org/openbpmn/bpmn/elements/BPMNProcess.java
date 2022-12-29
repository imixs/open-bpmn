package org.openbpmn.bpmn.elements;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNInvalidIDException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
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
public class BPMNProcess extends BPMNElement {

    protected String processType = BPMNTypes.PROCESS_TYPE_NONE;
    protected Set<Activity> activities = null;
    protected Set<DataObject> dataObjects = null;
    protected Set<TextAnnotation> textAnnotations = null;
    protected Set<Event> events = null;
    protected Set<Gateway> gateways = null;
    protected Set<SequenceFlow> sequenceFlows = null;
    protected Set<Association> associations = null;

    protected Set<Lane> lanes = null;
    protected Element laneSet = null;

    private boolean initalized = false;

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
                } else if (BPMNModel.isDataObject(child)) {
                    this.createBPMNDataObjectByNode((Element) child);

                } else if (BPMNModel.isTextAnnotation(child)) {
                    this.createBPMNTextAnnotationByNode((Element) child);

                } else if (BPMNModel.isSequenceFlow(child)) {
                    this.createBPMNSequenceFlowByNode((Element) child);
                } else if (BPMNModel.isAssociation(child)) {
                    this.createBPMNAssociationByNode((Element) child);

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
//    public Set<BPMNElementNode> getBPMNNodes() {
//        Set<BPMNElementNode> result = new LinkedHashSet<BPMNElementNode>();
//
//        result.addAll(this.getActivities());
//        result.addAll(this.getEvents());
//        result.addAll(this.getGateways());
//        return result;
//    }

    /**
     * Returns all BPMNElementNodes contained in this process
     * 
     * @return
     */
    public Set<BPMNElementNode> getAllNodes() {

        LinkedHashSet<BPMNElementNode> result = new LinkedHashSet<BPMNElementNode>();

        result.addAll(getActivities());
        result.addAll(getEvents());
        result.addAll(getGateways());
        result.addAll(getDataObjects());

        return result;
    }

    /**
     * Returns all BPMNActivity elements contained in this process
     * 
     * @return
     */
    public Set<Activity> getActivities() {
        if (activities == null) {
            activities = new LinkedHashSet<Activity>();
        }
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Event> getEvents() {
        if (events == null) {
            events = new LinkedHashSet<Event>();
        }
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Gateway> getGateways() {
        if (gateways == null) {
            gateways = new LinkedHashSet<Gateway>();
        }
        return gateways;
    }

    public void setGateways(Set<Gateway> gateways) {
        this.gateways = gateways;
    }

    /**
     * Returns all BPMN DataObject elements contained in this process
     * 
     * @return
     */
    public Set<DataObject> getDataObjects() {
        if (dataObjects == null) {
            dataObjects = new LinkedHashSet<DataObject>();
        }
        return dataObjects;
    }

    public void setDataObjects(Set<DataObject> dataObjects) {
        this.dataObjects = dataObjects;
    }

    public Set<TextAnnotation> getTextAnnotations() {
        if (textAnnotations == null)
            textAnnotations = new LinkedHashSet<TextAnnotation>();
        return textAnnotations;
    }

    public void setTextAnnotations(Set<TextAnnotation> textAnnotations) {
        this.textAnnotations = textAnnotations;
    }

    public Set<SequenceFlow> getSequenceFlows() {
        if (sequenceFlows == null) {
            sequenceFlows = new LinkedHashSet<SequenceFlow>();
        }
        return sequenceFlows;
    }

    public void setSequenceFlows(Set<SequenceFlow> sequenceFlows) {
        this.sequenceFlows = sequenceFlows;
    }

    public Set<Association> getAssociations() {
        if (associations == null) {
            associations = new LinkedHashSet<Association>();
        }
        return associations;
    }

    public void setAssociations(Set<Association> accociations) {
        this.associations = accociations;
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
    public Activity addTask(String id, String name, String type) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (Activity node : getActivities()) {
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
        Activity task = this.createBPMNActivityByNode(taskElement);

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
    public Event addEvent(String id, String name, String type) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (Event node : getEvents()) {
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
        Event event = this.createBPMNEventByNode(eventElement);

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
    public Gateway addGateway(String id, String name, String type) throws BPMNModelException {
        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (Gateway node : getGateways()) {
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
        Gateway gateway = this.createBPMNGatewayByNode(eventElement);
        return gateway;

    }

    /**
     * Creates a new BPMNDataObject element.
     * <p>
     * <bpmn2:dataObject id="DataObject_1" name="Data Object 1"/>
     * 
     * @param id
     * @param name
     * @throws BPMNModelException
     */
    public DataObject addDataObject(String id, String name) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (DataObject node : getDataObjects()) {
            if (node.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }

        // create a new Dom node...
        Element dataObjectElement = model.createElement(BPMNNS.BPMN2, "dataObject");
        dataObjectElement.setAttribute("id", id);
        dataObjectElement.setAttribute("name", name);
        this.getElementNode().appendChild(dataObjectElement);

        // add BPMNDataObject instance
        DataObject dataObject = this.createBPMNDataObjectByNode(dataObjectElement);

        return dataObject;
    }

    
    /**
     * Creates a new BPMN TextAnnotation element.
     * <p>
     * <bpmn2:textAnnotation id="Annotation_1" name="Text Annotation 1"/>
     * 
     * @param id
     * @param name
     * @throws BPMNModelException
     */
    public TextAnnotation addTextAnnotation(String id) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (TextAnnotation node : getTextAnnotations()) {
            if (node.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }

        // create a new Dom node...
        Element textAnnotationElement = model.createElement(BPMNNS.BPMN2, "textAnnotation");
        textAnnotationElement.setAttribute("id", id);
        this.getElementNode().appendChild(textAnnotationElement);

        // add BPMN TextAnnotation instance
        TextAnnotation textAnnotation = this.createBPMNTextAnnotationByNode(textAnnotationElement);

        return textAnnotation;
    }

    
    
    /**
     * Adds a SequenceFlow. The method computes and validates the source and target
     * elements based on this process context.
     * <p>
     * <bpmn2:sequenceFlow id="SequenceFlow_4" sourceRef="Task_1" targetRef=
     * "SendTask_1"/>
     * <p>
     * Note: In case of a SequenceFlow the edge ID is added to the BPMNFlow Element.
     * For MessageFlows and Association this additional reference is skipped.
     * 
     * @param id
     * @param source - ID of the source element
     * @param target - ID of the target element
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidTypeException
     */
    public SequenceFlow addSequenceFlow(String id, String source, String target)
            throws BPMNInvalidReferenceException, BPMNMissingElementException, BPMNInvalidTypeException {

        // validate Source and Target IDs
        // both must be part of the same process
        BPMNElementNode sourceElement = model.findElementNodeById(source);
        BPMNElementNode targetElement = model.findElementNodeById(target);

        if (sourceElement == null || targetElement == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target must be part of the process!");
        }

        if (source == null || source.equals(target)) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target ID can not be the same");
        }

        // create sequenceFlow element
        Element bpmnEdgeElement = model.createElement(BPMNNS.BPMN2, BPMNTypes.SEQUENCE_FLOW);
        bpmnEdgeElement.setAttribute("id", id);
        bpmnEdgeElement.setAttribute("sourceRef", source);
        bpmnEdgeElement.setAttribute("targetRef", target);

        this.getElementNode().appendChild(bpmnEdgeElement);

        SequenceFlow sequenceFlow = this.createBPMNSequenceFlowByNode(bpmnEdgeElement);

        // add refs to the BPMNEdge element...
        Element edgeShape = sequenceFlow.getBpmnEdge();
        edgeShape.setAttribute("sourceElement", sourceElement.getBpmnShape().getAttribute("id"));
        edgeShape.setAttribute("targetElement", targetElement.getBpmnShape().getAttribute("id"));

        // add a default waypoint
        // <di:waypoint xsi:type="dc:Point" x="156.0" y="105.0"/>
        // <di:waypoint xsi:type="dc:Point" x="243.0" y="105.0"/>
        sequenceFlow.addDefaultWayPoints();

        // add outgoing reference to source element
        Element refOut = model.createElement(BPMNNS.BPMN2, "outgoing");
        refOut.setTextContent(id);
        sourceElement.getElementNode().appendChild(refOut);
        // add incoming reference to target element
        Element refIn = model.createElement(BPMNNS.BPMN2, "incoming");
        refIn.setTextContent(id);
        targetElement.getElementNode().appendChild(refIn);

        return sequenceFlow;
    }

    /**
     * Adds a Association. The method computes and validates the source and target
     * elements based on this process context.
     * <p>
     * <bpmn2:sequenceFlow id="SequenceFlow_4" sourceRef="Task_1" targetRef=
     * "SendTask_1"/>
     * 
     * @param id
     * @param source - ID of the source element
     * @param target - ID of the target element
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidTypeException
     */
    public Association addAssociation(String id, String source, String target)
            throws BPMNInvalidReferenceException, BPMNMissingElementException, BPMNInvalidTypeException {

        BPMNElementNode sourceElement = model.findElementNodeById(source);
        BPMNElementNode targetElement = model.findElementNodeById(target);

        if (source == null || source.equals(target)) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target ID can not be the same");
        }
        if (sourceElement == null || targetElement == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target must be part of the process!");
        }

        // validate Source and Target IDs
        // both must be part of the same process

        // create sequenceFlow element
        Element bpmnEdgeElement = model.createElement(BPMNNS.BPMN2, BPMNTypes.ASSOCIATION);
        bpmnEdgeElement.setAttribute("id", id);
        bpmnEdgeElement.setAttribute("sourceRef", source);
        bpmnEdgeElement.setAttribute("targetRef", target);

        this.getElementNode().appendChild(bpmnEdgeElement);

        Association association = this.createBPMNAssociationByNode(bpmnEdgeElement);
        association.addDefaultWayPoints();

        // add refs to the BPMNEdge element...
        Element edgeShape = association.getBpmnEdge();
        edgeShape.setAttribute("sourceElement", sourceElement.getBpmnShape().getAttribute("id"));
        edgeShape.setAttribute("targetElement", targetElement.getBpmnShape().getAttribute("id"));

        return association;
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
    public Lane addLane(BPMNModel model, String name) throws BPMNMissingElementException {

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
        Lane bpmnLane = new Lane(model, lane);

        // Autocompute the Lane Bounds
        Participant bpmnParticipant = model.findParticipantByProcessId(this.getId());
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

            int laneX = (int) (poolBounds.getPosition().getX() + Participant.POOL_OFFSET);
            int laneY = (int) poolBounds.getPosition().getY();
            if (currentLaneCount > 0) {
                laneY = laneY + (currentLaneCount * expansion);
            }

            int laneWidth = (int) (poolBounds.getDimension().getWidth() - Participant.POOL_OFFSET);
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
     * Return true if the process is the public default process
     */
    public boolean isPublicProcess() {
        return BPMNTypes.PROCESS_TYPE_PUBLIC.equals(getProcessType());
    }

    /**
     * Returns true if the process contains a laneset
     * 
     * @return
     */
    public boolean hasLanes() {
        return (lanes != null);
    }

    public Set<Lane> getLanes() {
        if (lanes == null) {
            lanes = new LinkedHashSet<Lane>();
        }
        return lanes;
    }

    public void setLanes(Set<Lane> lanes) {
        this.lanes = lanes;
    }

    /**
     * This method deletes all BPMN Elements and Lanes form the process
     * 
     */
    public void deleteAllNodes() {

        // frist delete all lanes
        Iterator<Lane> ll = this.getLanes().iterator();
        while (ll.hasNext()) {
            deleteLane(ll.next().getId());
        }

        // now the rest of elements...
        Iterator<BPMNElementNode> aa = this.getAllNodes().iterator();
        while (aa.hasNext()) {
            deleteElementNode(aa.next().getId());
        }

    }

    /**
     * Deletes a BPMNLane and all referred element from this process.
     * <p>
     * 
     * @param id
     */
    public void deleteLane(String id) {
        Lane lane = (Lane) findLaneById(id);
        if (lane == null) {
            // does not exist
            return;
        }

        Set<String> flowElementList = lane.getFlowElementIDs();
        for (String flowEleemntID : flowElementList) {
            this.removeAllEdgesFromElement(flowEleemntID);
        }

        // delete the shape
        if (lane.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(lane.getBpmnShape());
        }
        this.getLanes().remove(lane);
    }

    /**
     * Deletes a BPMN Element from this process
     * 
     * @param id
     */
    public void deleteElementNode(String id) {
        BPMNElementNode bpmnElement = findElementNodeById(id);
        if (bpmnElement == null) {
            // does not exist
            return;
        }

        // test if the elmen tis a lane
        Lane lane = this.findLaneById(id);
        if (lane != null) {
            this.deleteLane(id);
            return;
        }

        // remove all flows
        removeAllEdgesFromElement(bpmnElement.getId());

        // delete the shape
        this.getElementNode().removeChild(bpmnElement.getElementNode());
        if (bpmnElement.getBpmnShape() != null) {
            model.getBpmnPlane().removeChild(bpmnElement.getBpmnShape());
        }

        // finally delete the element from the corresponding list
        if (bpmnElement instanceof Activity) {
            this.getActivities().remove(bpmnElement);
        }
        if (bpmnElement instanceof Gateway) {
            this.getGateways().remove(bpmnElement);
        }
        if (bpmnElement instanceof Event) {
            this.getEvents().remove(bpmnElement);
        }
        if (bpmnElement instanceof DataObject) {
            this.getDataObjects().remove(bpmnElement);
        }
        if (bpmnElement instanceof TextAnnotation) {
            this.getTextAnnotations().remove(bpmnElement);
        }
    }

    /**
     * Deletes a BPMNBase element from this process
     * 
     * @param id
     */
    public void deleteBPMNElementNode(String id) {

        BPMNElementNode baseElement = findElementNodeById(id);
        if (baseElement instanceof Lane) {
            this.deleteLane(id);
            return;
        }
        if (baseElement instanceof Activity) {
            this.deleteTask(id);
            return;
        }
        if (baseElement instanceof Event) {
            this.deleteEvent(id);
            return;
        }
        if (baseElement instanceof Gateway) {
            this.deleteGateway(id);
            return;
        }

    }

    /**
     * Deletes a SquenceFlow from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteSequenceFlow(String id) {

        BPMNElementEdge bpmnEdge = (BPMNElementEdge) findElementEdgeById(id);
        if (bpmnEdge != null && bpmnEdge instanceof SequenceFlow) {
            removeElementEdge(id);
            this.getSequenceFlows().remove(bpmnEdge);
        }
    }

    /**
     * Deletes a Association from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteAssociation(String id) {
        BPMNElementEdge bpmnEdge = (BPMNElementEdge) findElementEdgeById(id);
        if (bpmnEdge != null && bpmnEdge instanceof Association) {
            removeElementEdge(id);
            this.getAssociations().remove(bpmnEdge);
        }
    }

    /**
     * Deletes a BPMN Task element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteTask(String id) {
        deleteElementNode(id);
    }

    /**
     * Deletes a BPMN Event element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteEvent(String id) {
        deleteElementNode(id);
    }

    /**
     * Deletes a BPMN Gateway element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteGateway(String id) {
        deleteElementNode(id);
    }

    /**
     * Deletes a BPMN DataObject element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteDataObject(String id) {
        deleteElementNode(id);
    }

    /**
     * Deletes a BPMN TextAnnotation element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteTextAnnotation(String id) {
        deleteElementNode(id);
    }

    /**
     * Helper method to delete a BPMNEdge element from this context.
     * <p>
     * 
     * @param id
     */
    private void removeElementEdge(String id) {
        BPMNElementEdge bpmnEdge = (BPMNElementEdge) findElementEdgeById(id);
        if (bpmnEdge == null) {
            // does not exist
            return;
        }

        String targetRef = bpmnEdge.getTargetRef();
        String soureRef = bpmnEdge.getSourceRef();
        // In case of a SequenceFlow we need to update the referenced inside the
        // referred elements
        // <bpmn2:incoming>SequenceFlow_4</bpmn2:incoming>
        // <bpmn2:outgoing>SequenceFlow_5</bpmn2:outgoing>
        BPMNElementNode targetElement = findElementNodeById(targetRef);
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
        BPMNElementNode sourceElement = findElementNodeById(soureRef);
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
        this.getElementNode().removeChild(bpmnEdge.getElementNode());
        if (bpmnEdge.getBpmnEdge() != null) {
            model.getBpmnPlane().removeChild(bpmnEdge.getBpmnEdge());
        }

    }

    /**
     * Helper method to deletes all SequenceFlows, Associations and MessageFlows
     * from an element
     * 
     */
    private void removeAllEdgesFromElement(String elementId) {
        // remove all SequenceFlows
        Set<SequenceFlow> flowList = findSequenceFlowsByElementId(elementId);
        for (SequenceFlow flow : flowList) {
            deleteSequenceFlow(flow.getId());
        }

        // remove all Associations
        Set<Association> accociationList = findAssociationsByElementId(elementId);
        for (Association flow : accociationList) {
            deleteAssociation(flow.getId());
        }

        // remove all MessageFlows
        Set<MessageFlow> messageFlowList = findMessageFlowsByElementId(elementId);
        for (MessageFlow flow : messageFlowList) {
            model.deleteMessageFlow(flow.getId());
        }
    }

    /**
     * Finds an BPMN Element within this process. The element can be either a Node
     * or an Edge
     * 
     */
    public BPMNElement findElementById(String id) {
        BPMNElement result = null;
        result = findElementNodeById(id);
        if (result != null) {
            // Node Element found
            return result;
        }

        result = findLaneById(id);
        if (result != null) {
            // Lane found
            return result;
        }

        result = findElementEdgeById(id);
        if (result != null) {
            // Edge Element found
            return result;
        }

        return null;
    }

    /**
     * Finds a BPMNElementNode by ID within the current process.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNElementNode findElementNodeById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        Set<Activity> listA = this.getActivities();
        for (Activity element : listA) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<Event> listE = this.getEvents();
        for (Event element : listE) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<Gateway> listG = this.getGateways();
        for (Gateway element : listG) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<DataObject> listD = this.getDataObjects();
        for (DataObject element : listD) {
            if (id.equals(element.getId())) {
                return element;
            }
        }
        
        Set<TextAnnotation> listT = this.getTextAnnotations();
        for (TextAnnotation element : listT) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        return null;
    }

    /**
     * Returns all BPMNFlowElements contained in this process
     * 
     * @return
     */
    // public Set<BPMNElementNode> getBPMNNodes() {
    // Set<BPMNElementNode> result = new LinkedHashSet<BPMNElementNode>();
    //
    // result.addAll(this.getActivities());
    // result.addAll(this.getEvents());
    // result.addAll(this.getGateways());
    // return result;
    // }

    /**
     * Finds a BPMN Edge Element by given id. An Edge can be a SequenceFlow, a
     * MessageFlow or an Association
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public BPMNElementEdge findElementEdgeById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Set<SequenceFlow> listF = this.getSequenceFlows();
        for (SequenceFlow element : listF) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<MessageFlow> listM = this.getModel().getMessageFlows();
        for (MessageFlow element : listM) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        Set<Association> listA = this.getAssociations();
        for (Association element : listA) {
            if (id.equals(element.getId())) {
                return element;
            }
        }

        return null;
    }

    /**
     * This method returns the BPMNParticipant in case the Process represents a BPMN
     * Pool
     * 
     * @return
     */
    public Participant findParticipant() {
        Participant result = this.getModel().findParticipantByProcessId(this.getId());
        return result;
    }

    /**
     * Finds a BPMNLane by ID within the current process.
     * <p>
     * If no lane with the given ID exists, the method returns null.
     * 
     * @param id
     * @return
     */
    public Lane findLaneById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        Set<Lane> lanes = this.getLanes();
        for (Lane element : lanes) {
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
            Lane bpmnLane = new Lane(model, _lane);
            this.getLanes().add(bpmnLane);
        }
    }

    private Activity createBPMNActivityByNode(Element element) throws BPMNModelException {
        Activity task = new Activity(model, element, element.getLocalName(), this);
        getActivities().add(task);
        return task;
    }

    private DataObject createBPMNDataObjectByNode(Element element) throws BPMNModelException {
        DataObject dataObject = new DataObject(model, element, element.getLocalName(), this);
        getDataObjects().add(dataObject);
        return dataObject;
    }

    private TextAnnotation createBPMNTextAnnotationByNode(Element element) throws BPMNModelException {
        TextAnnotation textAnnotation = new TextAnnotation(model, element, element.getLocalName(), this);
        
        // read text <bpmn2:text>
        Set<Element> textNodes = BPMNModel.findChildNodesByName(element, BPMNNS.BPMN2.prefix + ":text");
        if (textNodes!=null && textNodes.size()>0) {
            Element textNode = textNodes.iterator().next();
            textAnnotation.setText(textNode.getTextContent());
        }
        
        getTextAnnotations().add(textAnnotation);
        return textAnnotation;
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
    private Event createBPMNEventByNode(Element element) throws BPMNModelException {
        Event event = new Event(model, element, element.getLocalName(), this);
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
    private Gateway createBPMNGatewayByNode(Element element) throws BPMNModelException {
        Gateway gateway = new Gateway(model, element, element.getLocalName(), this);
        getGateways().add(gateway);
        return gateway;
    }

    /**
     * Adds a new BPMNSequenceFlow from a existing Element Node
     * 
     * @param eventElement
     * @return
     */
    private SequenceFlow createBPMNSequenceFlowByNode(Element element) {
        SequenceFlow flow = new SequenceFlow(model, element, element.getLocalName(), this);
        getSequenceFlows().add(flow);
        return flow;
    }

    /**
     * Adds a new BPMNAssociation from a existing Element Node
     * 
     * @param eventElement
     * @return
     */
    private Association createBPMNAssociationByNode(Element element) {
        Association flow = new Association(model, element, element.getLocalName(), this);
        getAssociations().add(flow);
        return flow;
    }

    /**
     * Returns a list of sequenceFlows associated with a given FlowElement
     * 
     * @param id of a flowElement
     * @return list of SequenceFlows
     */
    private Set<SequenceFlow> findSequenceFlowsByElementId(String id) {
        Set<SequenceFlow> result = new LinkedHashSet<SequenceFlow>();
        if (id == null || id.isEmpty()) {
            return result;
        }
        Set<SequenceFlow> listA = this.getSequenceFlows();
        for (SequenceFlow flow : listA) {
            if (id.equals(flow.getSourceRef()) || id.equals(flow.getTargetRef())) {
                result.add(flow);
            }
        }
        return result;
    }

    /**
     * Returns a list of accociations associated with a given FlowElement
     * 
     * @param id of a flowElement
     * @return list of Accociations
     */
    private Set<Association> findAssociationsByElementId(String id) {
        Set<Association> result = new LinkedHashSet<Association>();
        if (id == null || id.isEmpty()) {
            return result;
        }
        Set<Association> listA = this.getAssociations();
        for (Association flow : listA) {
            if (id.equals(flow.getSourceRef()) || id.equals(flow.getTargetRef())) {
                result.add(flow);
            }
        }
        return result;
    }

    /**
     * Returns a list of MessageFlows associated with a given FlowElement
     * 
     * @param id of a flowElement
     * @return list of MessageFlows
     */
    private Set<MessageFlow> findMessageFlowsByElementId(String id) {
        Set<MessageFlow> result = new LinkedHashSet<MessageFlow>();
        if (id == null || id.isEmpty()) {
            return result;
        }
        Set<MessageFlow> listA = this.getModel().getMessageFlows();
        for (MessageFlow flow : listA) {
            if (id.equals(flow.getSourceRef()) || id.equals(flow.getTargetRef())) {
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
    public void insertLaneBefore(Lane laneTest, Lane lane2) {

        this.laneSet.insertBefore(laneTest.getElementNode(), lane2.getElementNode());
        // reorder the BPMNLanes
        Participant bpmnParticipant = model.findParticipantByProcessId(this.getId());
        LinkedHashSet<Lane> newOrderedLaneSet = new LinkedHashSet<Lane>();
        Iterator<Lane> laneIterator = this.lanes.iterator();
        while (laneIterator.hasNext()) {
            Lane aLane = laneIterator.next();
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
            int bpmnLaneX = (int) (poolBounds.getPosition().getX() + Participant.POOL_OFFSET);
            int bpmnLaneY = (int) poolBounds.getPosition().getY();
            Iterator<Lane> laneSetIterator = this.getLanes().iterator();
            while (laneSetIterator.hasNext()) {
                // get BPMNBounds and GNode for this lane...
                Lane lane = laneSetIterator.next();
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
