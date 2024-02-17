package org.openbpmn.bpmn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.MessageFlow;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.Signal;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNInvalidIDException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNXMLUtil;
import org.openbpmn.bpmn.validation.BPMNValidationError;
import org.openbpmn.bpmn.validation.BPMNValidationHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A BPMNModel represents the dom tree of a BPMN model.
 * <p>
 * The model elements can be read or updated. The method 'save' writes the mode
 * back into the filesystem.
 * 
 * @author rsoika
 *
 */
public class BPMNModel {
    protected static Logger logger = Logger.getLogger(BPMNModel.class.getName());

    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    private Document doc;
    private Element definitions;
    private Node bpmnDiagram;
    protected Element bpmnPlane = null;
    protected Set<Participant> participants = null;
    protected Set<BPMNProcess> processes = null;
    protected Set<MessageFlow> messageFlows = null;
    protected Set<Signal> signals = null;
    protected Set<Message> messages = null;
    protected Element collaborationElement = null;
    private boolean isDirty = false;
    private List<ModelNotification> notifications = null;

    private final Map<BPMNNS, String> URI_BY_NAMESPACE = new HashMap<>();
    private final Map<BPMNNS, String> PREFIX_BY_NAMESPACE = new HashMap<>();
    public static final String FILE_PREFIX = "file://";

    /**
     * This method instantiates a new BPMN model with the default BPMN namespaces
     * and prefixes.
     * 
     * @param doc
     */
    private BPMNModel() {
        setUri(BPMNNS.BPMN2, "http://www.omg.org/spec/BPMN/20100524/MODEL");
        setUri(BPMNNS.BPMNDI, "http://www.omg.org/spec/BPMN/20100524/DI");
        setUri(BPMNNS.DI, "http://www.omg.org/spec/DD/20100524/DI");
        setUri(BPMNNS.DC, "http://www.omg.org/spec/DD/20100524/DC");

        setPrefix(BPMNNS.BPMN2, "bpmn2");
        setPrefix(BPMNNS.BPMNDI, "bpmndi");
        setPrefix(BPMNNS.DI, "di");
        setPrefix(BPMNNS.DC, "dc");
        notifications = new ArrayList<ModelNotification>();
    }

    /**
     * This method instantiates a new BPMN model based on a given
     * org.w3c.dom.Document. The method parses the BPMN namespaces.
     * <p>
     * The method throws a BPMNModelException in case the model file dose not show a
     * valid BPMN 2.0 structure.
     * <p>
     * If the given model does not include a bpmndi:BPMNDiagram section the method
     * will create an empty default diagram.
     * 
     * @param doc
     * @throws BPMNModelException
     */
    public BPMNModel(Document doc) throws BPMNModelException {
        this();
        if (doc != null) {
            this.doc = doc;

            definitions = doc.getDocumentElement();

            // parse the BPMN namespaces
            NamedNodeMap defAttributes = definitions.getAttributes();
            for (int j = 0; j < defAttributes.getLength(); j++) {
                Node node = defAttributes.item(j);
                // update to new namespace uri if not matching the default URI
                // NOTE:
                // The primary namespace can be either 'bpmn2' or 'bpmn' !
                if ("bpmn".equals(node.getLocalName()) || "bpmn2".equals(node.getLocalName())) {

                    // In case the deprecated 'bpmn' namespace is used, we need to adjust the
                    // internal prefix...
                    if ("bpmn".equals(node.getLocalName())) {
                        setPrefix(BPMNNS.BPMN2, "bpmn");
                    }

                    if (!getUri(BPMNNS.BPMN2).equals(node.getNodeValue())) {
                        logger.warning("...set BPMN namespace URI: " + node.getNodeValue());
                        setUri(BPMNNS.BPMN2, node.getNodeValue());
                    }
                }

                if (getPrefix(BPMNNS.BPMN2).equals(node.getLocalName())
                        && !getUri(BPMNNS.BPMN2).equals(node.getNodeValue())) {
                    logger.fine("...set BPMN2 namespace URI: " + node.getNodeValue());
                    setUri(BPMNNS.BPMN2, node.getNodeValue());
                }

                if (getPrefix(BPMNNS.BPMNDI).equals(node.getLocalName())
                        && !getUri(BPMNNS.BPMNDI).equals(node.getNodeValue())) {
                    logger.fine("...set BPMNDI namespace URI: " + node.getNodeValue());
                    setUri(BPMNNS.BPMNDI, node.getNodeValue());
                }
                if (getPrefix(BPMNNS.DC).equals(node.getLocalName())
                        && !getUri(BPMNNS.DC).equals(node.getNodeValue())) {
                    logger.fine("...set DC namespace URI: " + node.getNodeValue());
                    setUri(BPMNNS.DC, node.getNodeValue());
                }
                if (getPrefix(BPMNNS.DI).equals(node.getLocalName())
                        && !getUri(BPMNNS.DI).equals(node.getNodeValue())) {
                    logger.fine("...set DI namespace URI: " + node.getNodeValue());
                    setUri(BPMNNS.DI, node.getNodeValue());
                }
            }

            // find bpmndi:BPMNDiagram
            NodeList diagramList = doc.getElementsByTagName(getPrefix(BPMNNS.BPMNDI) + ":BPMNDiagram");
            if (diagramList != null && diagramList.getLength() > 0) {
                bpmnDiagram = diagramList.item(0);
            } else {
                // no diagram included - so we create an empty one
                getLogger().warning("No bpmndi:BPMNDiagram found - created default diagram");
                // <bpmndi:BPMNDiagram id="BPMNDiagram_1" name="Default Process Diagram">
                Element bpmnDiagram = createElement(BPMNNS.BPMNDI, "BPMNDiagram");
                bpmnDiagram.setAttribute("id", "BPMNDiagram_1");
                bpmnDiagram.setAttribute("name", "OpenBPMN Diagram");
                definitions.appendChild(bpmnDiagram);
                setBpmnDiagram(bpmnDiagram);
            }

            // find BPMNPlane
            NodeList planeList = doc.getElementsByTagName(getPrefix(BPMNNS.BPMNDI) + ":BPMNPlane");
            if (planeList != null && planeList.getLength() > 0) {
                bpmnPlane = (Element) planeList.item(0);
            }

            // init the participant and process list
            loadParticipantList();
            loadProcessList();
            loadMessageList();
            loadMessageFlowList();
            loadSignalList();
        }
    }

    /**
     * Returns the namespace uri for a given namespace
     * 
     * @param ns
     * @return
     */
    public String getUri(BPMNNS ns) {
        return URI_BY_NAMESPACE.get(ns);
    }

    public void setUri(BPMNNS ns, String uri) {
        URI_BY_NAMESPACE.put(ns, uri);
    }

    /**
     * Returns the namespace prefix for a given BPMN namespace - e.g. 'bpmn2' or
     * 'bpmndi'
     * <p>
     * This is necessary because a bpmn model can work with the default namespace
     * prefix 'bpmn2' or 'bpmn'. The model instance automatically detects the used
     * namespace prefix and updates the prefix when loading a model file.
     * 
     * @param ns
     * @return
     */
    public String getPrefix(BPMNNS ns) {
        return PREFIX_BY_NAMESPACE.get(ns);
    }

    /**
     * Updates the namespace prefix for a given BPMN namespace - e.g. 'bpmn2' or
     * 'bpmndi'
     */
    public void setPrefix(BPMNNS ns, String prefix) {
        PREFIX_BY_NAMESPACE.put(ns, prefix);
    }

    public Element getDefinitions() {
        return definitions;
    }

    public Document getDoc() {
        return doc;
    }

    /**
     * Adds a new xml namespace if not yet defined
     * 
     * @param namespace
     * @param uri
     */
    public void addNamespace(String namespace, String uri) {
        if (!definitions.hasAttribute("xmlns:" + namespace)) {
            definitions.setAttribute("xmlns:" + namespace, uri);
        }
    }

    /**
     * Returns an internal isDirty flag
     * 
     * @return
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * Returns true if the model contains no processes with elements
     * 
     * @return
     */
    public boolean isEmpty() {
        if (processes.size() == 0 || (processes.size() == 1 && processes.iterator().next().isEmpty())) {
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all available ModelNotifications. A client can process the
     * notifications.
     * 
     * @return
     */
    public List<ModelNotification> getNotifications() {
        return this.notifications;
    }

    /**
     * Set the internal isDirty flag. This flag can be used to mark a model as
     * dirty.
     * 
     * @param isDirty
     */
    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    public Set<Participant> getParticipants() {
        if (participants == null) {
            participants = new LinkedHashSet<Participant>();
        }
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public Set<BPMNProcess> getProcesses() {
        if (processes == null) {
            processes = new LinkedHashSet<BPMNProcess>();
        }
        return processes;
    }

    public Set<MessageFlow> getMessageFlows() {
        if (messageFlows == null) {
            messageFlows = new LinkedHashSet<MessageFlow>();
        }
        return messageFlows;
    }

    public void setMessageFlows(Set<MessageFlow> messageFlows) {
        this.messageFlows = messageFlows;
    }

    public Set<Signal> getSignals() {
        if (signals == null) {
            signals = new LinkedHashSet<Signal>();
        }
        return signals;
    }

    public void setSignals(Set<Signal> signals) {
        this.signals = signals;
    }

    public Set<Message> getMessages() {
        if (messages == null) {
            messages = new LinkedHashSet<Message>();
        }
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void setProcesses(Set<BPMNProcess> processes) {
        this.processes = processes;
    }

    public Node getBpmnDiagram() {
        return bpmnDiagram;
    }

    public void setBpmnDiagram(Node bpmnDiagram) {
        this.bpmnDiagram = bpmnDiagram;
    }

    public Element getBpmnPlane() {
        return bpmnPlane;
    }

    public void setBpmnPlane(Element bpmnPlane) {
        this.bpmnPlane = bpmnPlane;
    }

    /**
     * This method returns true if the current model contains a CollaborationDiagram
     * 
     * @return
     */
    public boolean isCollaborationDiagram() {
        return collaborationElement != null;
    }

    /**
     * Adds a new BPMNParticipant element and the corresponding BPMNProcess to the
     * model.
     * <p>
     * The method verifies if a the model already has a bpmn2:collaboration section.
     * If not, the method creates one.
     * <p>
     * In case the diagram type switches from a process diagram to a collaboration
     * diagram, the method also updates the attribute 'bpmnElement' of the bpmnPlane
     * to the new created bpmn2:collaboration element id.
     * <p>
     * {@code
     * <bpmn2:collaboration id="Collaboration_1" name="Default Collaboration">
     * <bpmn2:participant id="Participant_1" name="Pool 1"/>
     * }
     * 
     * @param name
     * @return the BPMNParticipant
     * @throws BPMNModelException
     */
    public Participant addParticipant(String name) throws BPMNModelException {

        // first verify if the model already is a Collaboration model. If not we create
        // a bpmn2:collaboration
        if (!this.isCollaborationDiagram()) {
            logger.info("Migrating Process Diagram to Collaboration Diagram.");
            // create the default collaboration element
            String collaborationID = "collaboration_1";
            collaborationElement = createElement(BPMNNS.BPMN2, "collaboration");
            collaborationElement.setAttribute("id", collaborationID);
            collaborationElement.setAttribute("name", "Default Collaboration");
            this.definitions.insertBefore(collaborationElement, definitions.getFirstChild());
            // update the BPMNPlane attribute 'bpmnElement' which now references the
            // collaboration element
            this.getBpmnPlane().setAttribute("bpmnElement", collaborationID);

            // Now we migrate all existing processes into the new collaboration element....
            for (BPMNProcess existingProcess : processes) {
                Element migratedParticipantNode = createElement(BPMNNS.BPMN2, "participant");
                String participantID = BPMNModel.generateShortID("participant");
                migratedParticipantNode.setAttribute("id", participantID);
                migratedParticipantNode.setAttribute("name", existingProcess.getName());
                migratedParticipantNode.setAttribute("processRef", existingProcess.getId());

                collaborationElement.appendChild(migratedParticipantNode);
                existingProcess.setAttribute("definitionalCollaborationRef", collaborationElement.getAttribute("id"));
                // finally add a new BPMNParticipant to the participant list
                getParticipants().add(new Participant(this, migratedParticipantNode));
            }

        }

        // create a new Dom node...
        Element participantNode = createElement(BPMNNS.BPMN2, "participant");
        String participantID = BPMNModel.generateShortID("participant");
        participantNode.setAttribute("id", participantID);
        participantNode.setAttribute("name", name);
        this.collaborationElement.appendChild(participantNode);
        // add BPMNParticipant instance
        Participant bpmnParticipant = new Participant(this, participantNode);
        getParticipants().add(bpmnParticipant);

        // now create and add a new Process to this Participant...
        // <bpmn2:process id="Process_2" name="Non-initiating Process"
        // definitionalCollaborationRef="Collaboration_1" isExecutable="false"/>
        int processNumber = this.getProcesses().size() + 1;
        BPMNProcess process = buildProcess(BPMNModel.generateShortID("process"), "Process " + processNumber,
                BPMNTypes.PROCESS_TYPE_PRIVATE);
        process.setAttribute("definitionalCollaborationRef", collaborationElement.getAttribute("id"));
        process.setName(name);

        bpmnParticipant.setProcessRef(process.getId());
        bpmnParticipant.setBpmnProcess(process);
        // create the pool shape
        createPool(bpmnParticipant);

        return bpmnParticipant;
    }

    /**
     * This method creates a BPMNShape for this participant. The shape element
     * represents the Pool within the BPMNDiagram section.
     * 
     * @throws BPMNModelException
     */
    private void createPool(Participant bpmnParticipant) throws BPMNModelException {
        if (!bpmnParticipant.hasPool()) {
            // create shape element
            Element poolShape = this.buildBPMNShape(bpmnParticipant);
            bpmnParticipant.setBpmnShape(poolShape);
            bpmnParticipant.setBounds(10.0, 10.0, Participant.DEFAULT_WIDTH, Participant.DEFAULT_HEIGHT);
            // create BPMNLabel
            BPMNLabel bpmnLabel = new BPMNLabel(this, poolShape);
            bpmnLabel.updateLocation(10.0, 10.0);
        }
    }

    /**
     * Builds a new BPMNProcess element and adds this into this model instance. This
     * method also generates a default BPMNPlane if not yet created
     * <p>
     * 
     * <pre>
     * {@code
     *   <bpmn2:process id="process_1"/>
         <bpmndi:BPMNDiagram id="BPMNDiagram_1" name="Default Process Diagram">
            <bpmndi:BPMNPlane bpmnElement="process_1" id="BPMNPlane_process_1"/>
         </bpmndi:BPMNDiagram>
     * }
     * </pre>
     * 
     * <p>
     * The method also expects a process type (public|private). If not type is
     * specified the method builds a public default process.
     * <p>
     * A BPMNModelException is thrown if the given id is already in use.
     * 
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    protected BPMNProcess buildProcess(String id, String name, String type) throws BPMNModelException {

        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // verify id
        for (BPMNProcess process : processes) {
            if (process.getId().equals(id)) {
                throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                        "id '" + id + "' is already in use!");
            }
        }
        // xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
        Element process = createElement(BPMNNS.BPMN2, "process");
        logger.fine(process.getNodeName());
        logger.fine(process.getLocalName());
        logger.fine(process.getNamespaceURI());
        process.setAttribute("id", id);
        // set optional name
        if (name != null) {
            process.setAttribute("name", name);
        }

        // set type
        if (type == null || type.isEmpty()) {
            type = BPMNTypes.PROCESS_TYPE_PUBLIC;
        }
        process.setAttribute("processType", type);

        definitions.insertBefore(process, this.getBpmnDiagram());

        BPMNProcess bpmnProcess = new BPMNProcess(this, process, type);
        this.getProcesses().add(bpmnProcess);

        // add an empty BPMNPlane tag
        // <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="process_2">
        if (bpmnPlane == null) {
            bpmnPlane = createElement(BPMNNS.BPMNDI, "BPMNPlane");
            bpmnPlane.setAttribute("id", "BPMNPlane_1");
            bpmnPlane.setAttribute("bpmnElement", id);
            this.getBpmnDiagram().appendChild(bpmnPlane);
        }

        return bpmnProcess;
    }

    /**
     * Creates a BPMN shape node for this element
     * <p>
     * <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
     * 
     * @throws BPMNMissingElementException
     */
    public Element buildBPMNShape(BPMNElementNode bpmnElement) throws BPMNModelException {
        Element bpmnShape;
        if (getBpmnPlane() == null) {
            throw new BPMNMissingElementException("Missing bpmnPlane in current model context");
        }
        if (bpmnElement.getId() != null) {
            bpmnShape = createElement(BPMNNS.BPMNDI, "BPMNShape");
            bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
            bpmnShape.setAttribute("bpmnElement", bpmnElement.getId());
            getBpmnPlane().appendChild(bpmnShape);
            return bpmnShape;
        } else {
            throw new BPMNInvalidReferenceException("Missing ID attribute");
        }
    }

    /**
     * This Method opens a BPMNProcess with the given ID and initializes all BPMN
     * elements of the Process. This is a lazy loading mechanism to avoid loading
     * the full model if not needed.
     * <p>
     * The method returns null if no process with the given ID exists.
     * <p>
     * In case no ID is provided (null) the method returns the first public
     * (default) process from the model.
     * 
     * @param string
     * @return BPMNProcess instance
     * @throws BPMNModelException
     */
    public BPMNProcess openProcess(String id) throws BPMNModelException {
        BPMNProcess process = null;
        if (processes != null) {
            Iterator<BPMNProcess> it = processes.iterator();

            while (it.hasNext()) {
                BPMNProcess p = it.next();
                // default process?
                if (id == null || id.isEmpty()) {
                    if (BPMNTypes.PROCESS_TYPE_PUBLIC.equals(p.getProcessType())) {
                        // we take the first public process (should only exists once in the model)
                        process = p;
                        break;
                    }
                } else {
                    // verify process by id
                    if (id.equals(p.getId())) {
                        process = p;
                        break;
                    }
                }
            }
        }

        // if we found a matching process than we can initialize it
        if (process != null) {
            process.init();
        }
        return process;
    }

    /**
     * This Method opens the public default BPMNProcess and initializes all BPMN
     * elements of the Process. The default process always exists and is not
     * embedded in a Pool.
     */
    public BPMNProcess openDefaultProces() {
        try {
            return openProcess(null);
        } catch (BPMNModelException e) {
            logger.warning("no default process defined!");
            return null;
        }
    }

    /**
     * Deletes a BPMNParticipant element from this model.
     * <p>
     * 
     * @param id
     */
    public void deleteParticipant(Participant participant) {
        if (participant != null) {
            BPMNProcess process = participant.openProcess();
            // delete all Lanes - we need first to collect the IDs to avoid recursive calls
            Iterator<Lane> lanesIterator = process.getLanes().iterator();
            List<String> laneIDs = new ArrayList<String>();
            while (lanesIterator.hasNext()) {
                Lane lane = lanesIterator.next();
                laneIDs.add(lane.getId());
            }
            for (String laneID : laneIDs) {
                // delete Lane by id....
                process.deleteLane(laneID);
            }
            // delete remaining FlowElements...
            process.deleteAllNodes();
            this.definitions.removeChild(process.getElementNode());
            if (participant.hasPool()) {
                this.bpmnPlane.removeChild(participant.getBpmnShape());
            }
            this.collaborationElement.removeChild(participant.getElementNode());
            // remove the participant with its elements
            this.getProcesses().remove(process);
            this.getParticipants().remove(participant);
        }

    }

    /**
     * Creates an element with a given namespace
     */
    public Element createElement(BPMNNS ns, String type) {
        Element element = this.getDoc().createElementNS(getUri(ns), getPrefix(ns) + ":" + type);
        return element;
    }

    /**
     * Adds a MessageFlow. The method computes and validates the source and target
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
    public MessageFlow addMessageFlow(String id, String sourceId, String targetId)
            throws BPMNInvalidReferenceException, BPMNMissingElementException, BPMNInvalidTypeException {
        if (sourceId == null || sourceId.equals(targetId)) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target ID can not be the same");
        }

        // validate Source and Target IDs
        // both must be part of the same process
        BPMNElementNode sourceElement = findElementNodeById(sourceId);
        BPMNElementNode targetElement = findElementNodeById(targetId);
        if (sourceElement == null || targetElement == null) {
            throw new BPMNInvalidReferenceException(BPMNInvalidReferenceException.INVALID_REFERENCE,
                    "Source and Target must be part of the process!");
        }

        // create sequenceFlow element
        Element bpmnEdgeElement = createElement(BPMNNS.BPMN2, BPMNTypes.MESSAGE_FLOW);
        bpmnEdgeElement.setAttribute("id", id);
        bpmnEdgeElement.setAttribute("sourceRef", sourceId);
        bpmnEdgeElement.setAttribute("targetRef", targetId);

        // this.definitions.appendChild(bpmnEdgeElement);

        this.collaborationElement.appendChild(bpmnEdgeElement);

        // .insertAfter(bpmnEdgeElement, this.collaborationElement.getLastChild());

        MessageFlow messageFlow = new MessageFlow(this, bpmnEdgeElement);
        getMessageFlows().add(messageFlow);

        messageFlow.addDefaultWayPoints();

        // add refs to the BPMNEdge element...
        Element edgeShape = messageFlow.getBpmnEdge();
        edgeShape.setAttribute("sourceElement", sourceElement.getBpmnShape().getAttribute("id"));
        edgeShape.setAttribute("targetElement", targetElement.getBpmnShape().getAttribute("id"));

        return messageFlow;
    }

    /**
     * Deletes a BPMNEdge element from this context.
     * <p>
     * 
     * @param id
     */
    public void deleteMessageFlow(String id) {
        BPMNElementEdge bpmnEdge = this.findElementEdgeById(id);
        if (bpmnEdge == null) {
            // does not exist
            return;
        }

        String targetRef = bpmnEdge.getTargetRef();
        String sourceRef = bpmnEdge.getSourceRef();
        // first we need to update the elements still connected with this flow
        // <bpmn2:incoming>SequenceFlow_4</bpmn2:incoming>
        // <bpmn2:outgoing>SequenceFlow_5</bpmn2:outgoing>
        BPMNElementNode targetElement = findElementNodeById(targetRef);
        if (targetElement != null) {
            NodeList childs = targetElement.getElementNode().getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE
                        && (child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + ":incoming")
                                || child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + ":outgoing"))) {
                    if (id.equals(child.getTextContent())) {
                        targetElement.getElementNode().removeChild(child);
                        break;
                    }
                }
            }
        }
        BPMNElementNode sourceElement = findElementNodeById(sourceRef);
        if (sourceElement != null) {
            NodeList childs = sourceElement.getElementNode().getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE
                        && (child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + ":incoming")
                                || child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + ":outgoing"))) {
                    if (id.equals(child.getTextContent())) {
                        sourceElement.getElementNode().removeChild(child);
                        break;
                    }
                }
            }
        }

        // delete the flow element and the edge
        this.collaborationElement.removeChild(bpmnEdge.getElementNode());
        if (bpmnEdge.getBpmnEdge() != null) {
            getBpmnPlane().removeChild(bpmnEdge.getBpmnEdge());
        }

        // finally we remove the messageFlow object form the messageFlow list
        getMessageFlows().remove(bpmnEdge);

    }

    /**
     * Adds a Signal element to the diagram
     * <p>
     * <bpmn2:signal id="Signal_1" name="My Signal"/>
     * 
     * @param id
     * @param name - name of the signal
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidTypeException
     * @throws BPMNInvalidIDException
     */
    public Signal addSignal(String id, String name) throws BPMNInvalidReferenceException, BPMNMissingElementException,
            BPMNInvalidTypeException, BPMNInvalidIDException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }
        // create sequenceFlow element
        Element bpmnEdgeElement = createElement(BPMNNS.BPMN2, BPMNTypes.SIGNAL);
        bpmnEdgeElement.setAttribute("id", id);
        bpmnEdgeElement.setAttribute("name", name);

        this.definitions.insertBefore(bpmnEdgeElement, this.getBpmnDiagram());

        Signal signal = new Signal(this, bpmnEdgeElement);
        getSignals().add(signal);

        return signal;
    }

    /**
     * Adds a Message element to the diagram
     * <p>
     * <bpmn2:message id="Message_4" name="Message 4" />
     * 
     * @param id
     * @param name - name of the message
     * @throws BPMNModelException
     */
    public Message addMessage(String id, String name) throws BPMNModelException {

        // verify id
        if (id == null || id.isEmpty()) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.MISSING_ID, "id must not be empty or null!");
        }

        // create sequenceFlow element
        Element bpmnElement = createElement(BPMNNS.BPMN2, BPMNTypes.MESSAGE);
        bpmnElement.setAttribute("id", id);
        bpmnElement.setAttribute("name", name);

        this.definitions.insertBefore(bpmnElement, this.getBpmnDiagram());

        Message message = new Message(this, bpmnElement, BPMNTypes.MESSAGE, this.openDefaultProces());
        getMessages().add(message);

        return message;
    }

    /**
     * Finds a Signal element by name withing the diagram
     * <p>
     * <bpmn2:signal id="Signal_1" name="My Signal"/>
     * 
     * @param name - name of the signal
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidTypeException
     */
    public Signal findSignalByName(String name)
            throws BPMNInvalidReferenceException, BPMNMissingElementException, BPMNInvalidTypeException {

        if (name == null || name.isEmpty()) {
            return null;
        }
        for (Signal signal : signals) {
            if (name.equals(signal.getName())) {
                return signal;
            }
        }
        return null;
    }

    /**
     * Deletes a Signal element from this diagram.
     * <p>
     * 
     * @param id
     */
    public void deleteSignal(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }

        Signal signal = null;
        for (Signal _signal : getSignals()) {
            if (id.equals(_signal.getId())) {
                signal = _signal;
                break;

            }
        }

        if (signal == null) {
            // does not exist
            return;
        }

        // find all SignalDefinitions in Events referring this Signal ID
        Set<Event> events = findAllEvents();
        // test if the event has a event definition with this signal ID
        for (Event event : events) {
            Set<Element> definitionList = event.getEventDefinitions();
            for (Element e : definitionList) {

                if (BPMNTypes.EVENT_DEFINITION_SIGNAL.equals(e.getLocalName())) {
                    String refID = e.getAttribute("signalRef");

                    if (id.equals(refID)) {
                        try {
                            event.deleteEventDefinition(e.getAttribute("id"));
                        } catch (BPMNModelException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }

        // delete the element from teh definitions
        this.definitions.removeChild(signal.getElementNode());

        // finally we remove the signal object form the signals list
        getSignals().remove(signal);

    }

    /**
     * Deletes a Message element from this diagram.
     * <p>
     * The method removes the message element and the corresponding shape element.
     * 
     * @param id
     */
    public void deleteMessage(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }

        Message message = null;
        for (Message _message : getMessages()) {
            if (id.equals(_message.getId())) {
                message = _message;
                break;

            }
        }

        if (message == null) {
            // does not exist
            return;
        }

        // find all MessageDefinitions in Events referring this Message ID
        Set<Event> events = findAllEvents();
        // test if the event has a event definition with this message ID
        for (Event event : events) {
            Set<Element> definitionList = event.getEventDefinitions();
            for (Element e : definitionList) {

                if (BPMNTypes.EVENT_DEFINITION_MESSAGE.equals(e.getLocalName())) {
                    String refID = e.getAttribute("messageRef");
                    if (id.equals(refID)) {
                        try {
                            event.deleteEventDefinition(e.getAttribute("id"));
                        } catch (BPMNModelException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }

        // delete the element from the definitions and also the shape
        this.definitions.removeChild(message.getElementNode());
        if (message.getBpmnShape() != null) {
            getBpmnPlane().removeChild(message.getBpmnShape());
        }

        // finally we remove the message object form the message list
        getMessages().remove(message);

    }

    /**
     * Finds a BPMNElement by ID within this model. The Element can be a Node or an
     * Edge. The method iterates over all existing Processes and its contained
     * FlowElements.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id - the BPMN Element id
     * @return
     */
    public BPMNElement findElementById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        // test signals...
        for (Signal signal : signals) {
            if (id.equals(signal.getId())) {
                return signal;
            }
        }

        // test messages...
        for (Message message : messages) {
            if (id.equals(message.getId())) {
                return message;
            }
        }

        // test for participant...
        Participant participant = this.findParticipantById(id);
        if (participant != null) {
            return participant;
        }

        // iterate over all processes
        Set<BPMNProcess> processList = this.getProcesses();
        for (BPMNProcess process : processList) {
            if (id.equals(process.getId())) {
                // the id matches a Process
                throw new IllegalArgumentException("unable to return process - not implemented!");

            } else {
                // analyze the content of the process
                BPMNElement baseElement = process.findElementById(id);
                if (baseElement != null) {
                    return baseElement;
                }
            }
        }

        // no corresponding element found!
        return null;
    }

    /**
     * This method returns the bpmn2:extensionElements node for a given BPMN
     * Element. If not yet defined, the
     * method creates an new emtpy bpmn2:extensionElements node.
     * 
     * The bpmn2:extensionElements node can be used to read or create custom
     * extension elements.
     * 
     * @param defaultModel
     * @param nameSpace
     * @param elementName
     * @return
     */
    public Element findBPMN2Extensions(Element element) {
        // find extension elements
        Element extensionElement = findChildNodeByName(element, BPMNNS.BPMN2,
                "extensionElements");
        if (extensionElement == null) {
            extensionElement = createElement(BPMNNS.BPMN2, "extensionElements");
            element.insertBefore(extensionElement, element.getFirstChild());

        }
        return extensionElement;
    }

    /**
     * This method returns an extension element for a given element node defined by
     * its namespace and tag name. If the extension element is not yet defined, the
     * method creates an empty one.
     * 
     * @param defaultModel
     * @param nameSpace
     * @param elementName
     * @return
     * @throws BPMNInvalidReferenceException
     */
    public Element findExtensionElement(Element element, String namespace, String elementName)
            throws BPMNInvalidReferenceException {
        Element extensionElement = findBPMN2Extensions(element);
        Element autoAlignElement = null;
        // resolve the tag name
        String tagName = namespace + ":" + elementName;
        NodeList childs = extensionElement.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node childNode = childs.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && tagName.equals(childNode.getNodeName())) {
                autoAlignElement = (Element) childNode;
                break;
            }
        }

        // If extension element is not yet defined, we create a new empty one....
        if (autoAlignElement == null) {

            if (definitions.hasAttribute("xmlns:" + namespace)) {
                // find schema uri
                String nameSpaceUri = definitions.getAttribute("xmlns:" + namespace);
                autoAlignElement = getDoc().createElementNS(nameSpaceUri,
                        namespace + ":" + elementName);
                extensionElement.appendChild(autoAlignElement);
            } else {
                throw new BPMNInvalidReferenceException("Namespace '" + namespace + "' not defined!");
            }
        }
        return autoAlignElement;
    }

    /**
     * Returns all Events within this model
     * <p>
     * If no event exists, the method returns an empty list.
     * 
     * @return the list of events or an empty is if no events exist.
     */
    public Set<Event> findAllEvents() {
        LinkedHashSet<Event> result = new LinkedHashSet<Event>();
        // iterate over all processes
        Set<BPMNProcess> processList = this.getProcesses();
        for (BPMNProcess process : processList) {
            try {
                process.init();
            } catch (BPMNModelException e) {
                e.printStackTrace();
            }
            result.addAll(process.getEvents());
        }
        return result;
    }

    /**
     * Returns all Activities within this model
     * <p>
     * If no Activities exists, the method returns an empty list.
     * 
     * @return the list of Activities or an empty is if no Activities exist.
     */
    public Set<Activity> findAllActivities() {
        LinkedHashSet<Activity> result = new LinkedHashSet<Activity>();
        // iterate over all processes
        Set<BPMNProcess> processList = this.getProcesses();
        for (BPMNProcess process : processList) {
            try {
                process.init();
            } catch (BPMNModelException e) {
                e.printStackTrace();
            }
            result.addAll(process.getActivities());
        }
        return result;
    }

    /**
     * Finds a BPMNElementNode by ID within this model. The method iterates over all
     * existing Processes and its contained FlowElements.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id - the BPMN Element id
     * @return
     */
    public BPMNElementNode findElementNodeById(String id) {

        BPMNElement result = this.findElementById(id);
        if (result != null && result instanceof BPMNElementNode) {
            return (BPMNElementNode) result;
        }

        // no corresponding element found!
        return null;
    }

    /**
     * Finds a BPMNElementEdge by ID within this model. The method iterates over all
     * existing Processes and its contained FlowElements.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id - the BPMN Element id
     * @return
     */
    public BPMNElementEdge findElementEdgeById(String id) {

        BPMNElement result = this.findElementById(id);
        if (result != null && result instanceof BPMNElementEdge) {
            return (BPMNElementEdge) result;
        }

        // no corresponding element found!
        return null;
    }

    /**
     * This method returns the BPMNBounds element of a BPMNBaseElement with the
     * given ID. This finder method can be used to just adjust the Bounds in the
     * Diagram section of a model.
     * <p>
     * In case of a process diagram, the method verifies all FlowElements within the
     * default process.
     * <p>
     * In case of a collaboration diagram, the method verifies all Participants and
     * also all FlowElements within the corresponding processes.
     * <p>
     * If no element with the given ID exists, the method returns null.
     * 
     * @param id         - the BPMN Element id
     * @param deepSearch - if true also containing elements like Pools are analyzed.
     * @return
     */
    public BPMNBounds findBPMNBoundsById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            if (isCollaborationDiagram()) {
                // iterate over all participants
                Set<Participant> participantList = this.getParticipants();
                for (Participant participant : participantList) {
                    if (id.equals(participant.getId())) {
                        // the id matches the participant
                        return participant.getBounds();
                    } else {
                        // analyze the content of the process
                        BPMNElementNode baseElement = participant.openProcess().findElementNodeById(id);
                        if (baseElement != null) {
                            return baseElement.getBounds();
                        }
                    }
                }
            } else {
                // just analyze the default process
                BPMNElementNode baseElement = openDefaultProces().findElementNodeById(id);
                if (baseElement != null) {
                    return baseElement.getBounds();
                }
            }
        } catch (BPMNMissingElementException e) {
            logger.warning("Failed to found Bounds for element '" + id + "' : " + e.getMessage());
        }

        // no corresponding element found!
        return null;
    }

    /**
     * This method returns the BPMN Participant (Pool) a element contains to based
     * on a given absolute position in the diagram. This method is used to move a
     * element within a diagram from one process pool into another.
     * <p>
     * If the model type is no Collaboration diagram the method throws a
     * BPMNInvalidTypeException
     * <p>
     * If no matching Participant can be found, than the method returns null.
     * 
     * @param point - an absolute point within the current diagram
     * @return a Participant or null
     * @throws BPMNInvalidTypeException
     */
    public Participant findParticipantByPoint(BPMNPoint point) throws BPMNInvalidTypeException {
        if (!this.isCollaborationDiagram()) {
            throw new BPMNInvalidTypeException(BPMNInvalidTypeException.INVALID_TYPE,
                    "Model type is no collaboration diagram!");
        }

        if (point != null && isCollaborationDiagram()) {
            // iterate over all participants
            for (Participant participant : this.getParticipants()) {
                // try to get the bound
                try {
                    BPMNBounds participantBounds;
                    participantBounds = participant.getBounds();
                    if (participantBounds.containsPoint(point)) {
                        return participant;
                    }
                } catch (BPMNMissingElementException e) {
                    // participant does not contain bounds, so we can skip this one
                }
            }
        }
        // no participant - return the default Participant
        Participant defaultParticipant = findParticipantByProcessId(this.openDefaultProces().getId());
        return defaultParticipant;
    }

    /**
     * Finds a BPMNParticipant by its id
     * 
     * @param id
     * @return
     */
    public Participant findParticipantById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        if (isCollaborationDiagram()) {
            // iterate over all participants
            Set<Participant> participantList = this.getParticipants();
            for (Participant participant : participantList) {
                if (id.equals(participant.getId())) {
                    // the id matches the participant
                    return participant;
                }
            }
        }

        // no corresponding element found!
        return null;
    }

    /**
     * Finds a corresponding BPMNParticipant by a processID
     * 
     * @param id
     * @return
     */
    public Participant findParticipantByProcessId(String processId) {
        if (processId == null || processId.isEmpty()) {
            return null;
        }
        if (isCollaborationDiagram()) {
            for (Participant _participant : participants) {
                if (processId.equals(_participant.getProcessRef())) {
                    return _participant;
                }
            }
        }
        // no corresponding element found!
        return null;
    }

    /**
     * This helper method returns a set of child nodes by name from a given parent
     * node. If no nodes were found, the method returns an empty list.
     * <p>
     * The method compares the Name including the namespace of the child elements.
     * <p>
     * See also {@link #findChildNodeByName(Element parent, String nodeName)
     * findChildNodeByName}
     * 
     * @param parent
     * @param nodeName
     * @return - list of nodes. If no nodes were found, the method returns an empty
     *         list
     */
    public Set<Element> findChildNodesByName(Element parent, BPMNNS ns, String nodeName) {
        Set<Element> result = new LinkedHashSet<Element>();
        // resolve the tag name
        String tagName = getPrefix(ns) + ":" + nodeName;
        if (parent != null && nodeName != null) {
            NodeList childs = parent.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                Node childNode = childs.item(i);

                if (childNode.getNodeType() == Node.ELEMENT_NODE && tagName.equals(childNode.getNodeName())) {
                    result.add((Element) childNode);
                }
            }
        }
        return result;
    }

    /**
     * This helper method returns the first child node by name from a given parent
     * node. If no nodes were found the method returns null.
     * 
     * See also {@link #findChildNodesByName(Element parent, String nodeName)
     * findChildNodesByName}
     * 
     * @param parent
     * @param nodeName
     * @return - Child Element matching the given node name. If no nodes were found,
     *         the method returns null
     */
    public Element findChildNodeByName(Element parent, BPMNNS ns, String nodeName) {
        Set<Element> elementList = findChildNodesByName(parent, ns, nodeName);
        if (elementList.iterator().hasNext()) {
            // return first element
            return elementList.iterator().next();
        } else {
            // no child elements with the given name found!
            return null;
        }
    }

    /**
     * Find a BPMNProcess by its ID.
     * The method returns null if no process with this ID exists.
     * 
     * @param processid
     * @return
     */
    public BPMNProcess findProcessById(String processid) {
        for (BPMNProcess _process : processes) {
            if (_process.getId().equals(processid)) {
                return _process;
            }
        }
        return null;
    }

    /**
     * Writes the current instance to the file system.
     *
     * The method also resolves all open-bpmn:file-link elements and updates the
     * corresponding content.
     * 
     * @param file
     */
    public void save(File file) {
        logger.finest("...save BPMN model to file: " + file);
        // On windows the filepath can be something like:
        // '/c:/Users/Max Musterman/test.bpmn'
        // See #249
        // try (FileOutputStream output = new FileOutputStream(filePath)) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            logger.finest("...start writing to file outputstream....");
            if (doc == null) {
                logger.severe("...unable to save file - doc is null!");
            }

            // finally write the xml to disk
            writeXml(doc, output);
        } catch (TransformerException | IOException e) {
            logger.warning("Failed to save BPMN file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Writes the current instance to the file system.
     * 
     * If the file location is an URI and starting with 'file://' it will be
     * automatically remove the file protocol
     * 
     * The method also resolves all open-bpmn:file-link elements and updates the
     * corresponding content.
     * 
     * @param filePath
     */
    @Deprecated
    public void save(String filePath) {
        logger.warning("DEPRECATED - SHOULD NOT BE CALLED...save BPMN model to file: " + filePath);
        // If we have a file prefix from a URI object, we remove this part
        if (filePath.startsWith(FILE_PREFIX)) {
            filePath = filePath.replace(FILE_PREFIX, "");
        }
        logger.warning("...final file path=" + filePath);
        logger.warning("...creating file object from " + filePath);
        File file = new File(filePath);

        // On windows the filepath can be something like:
        // '/c:/Users/Max Musterman/test.bpmn'
        // See #249
        // try (FileOutputStream output = new FileOutputStream(filePath)) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            logger.warning("...start writing to file outputstream....");
            if (doc == null) {
                logger.severe("...unable to save file - doc is null!");
            }

            // finally write the xml to disk
            writeXml(doc, output);
        } catch (TransformerException | IOException e) {
            logger.warning("Failed to save BPMN file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Writes the current instance to the file system.
     * 
     * See {@code save(String)}
     */
    @Deprecated
    public void save(URI targetURI) {
        logger.warning("...save BPMN model to URI=" + targetURI);
        String result = Paths.get(targetURI).toString();
        logger.warning("              => filePath=" + result);
        save(result);
    }

    /**
     * Generates a random 12 byte id
     * <p>
     * HEX: 0-9, a-f. For example: 6587fddb, c0f182c1
     * 
     * @return
     */
    public static String generateShortID() {
        byte[] buffer = new byte[12];
        random.nextBytes(buffer);
        String id = encoder.encodeToString(buffer);
        // remove -_
        id = id.replace("-", "0");
        id = id.replace("_", "0");
        return id;

    }

    /**
     * Generates a random short 6 byte id with a prefix
     * <p>
     * HEX: 0-9, a-f. For example: BPMNShape_a55ac5, BPMNShape_1382c1
     * 
     * @return
     */
    public static String generateShortID(String prefix) {
        byte[] buffer = new byte[4];
        random.nextBytes(buffer);
        String id = encoder.encodeToString(buffer);
        // remove -_
        id = id.replace("-", "0");
        id = id.replace("_", "0");
        return prefix + "_" + id;

    }

    /**
     * Returns true if the node is an activity node.
     * 
     * @param node
     * @return
     */
    public static boolean isActivity(Node node) {
        return (BPMNTypes.BPMN_ACTIVITIES.contains(node.getLocalName()));
    }

    public static boolean isActivity(BPMNElementNode element) {
        return isActivity(element.getElementNode());
    }

    /**
     * Returns true if the node is an participant node.
     * 
     * @param node
     * @return
     */
    public static boolean isParticipant(Node node) {
        return (BPMNTypes.PARTICIPANT.equals(node.getLocalName()));
    }

    /**
     * Returns true if the node is an LaneSet node.
     * 
     * @param node
     * @return
     */
    public static boolean isLaneSet(Node node) {
        return (BPMNTypes.LANESET.equals(node.getLocalName()));
    }

    /**
     * Returns true if the node is a gateway node.
     * 
     * @param node
     * @return
     */
    public static boolean isGateway(Node node) {
        return (BPMNTypes.BPMN_GATEWAYS.contains(node.getLocalName()));
    }

    public static boolean isGateway(BPMNElementNode element) {
        return isGateway(element.getElementNode());
    }

    /**
     * Returns true if the node is an event node.
     * 
     * @param node
     * @return
     */
    public static boolean isEvent(Node node) {
        return (BPMNTypes.BPMN_EVENTS.contains(node.getLocalName()));
    }

    public static boolean isEvent(BPMNElementNode element) {
        return isEvent(element.getElementNode());
    }

    /**
     * Returns true if the node is a dataObject node.
     * 
     * @param node
     * @return
     */
    public static boolean isDataObject(Node node) {
        return (BPMNTypes.DATAOBJECT.equals(node.getLocalName()));
    }

    public static boolean isDataObject(BPMNElementNode element) {
        return isDataObject(element.getElementNode());
    }

    /**
     * Returns true if the node is a textAnnotation node.
     * 
     * @param node
     * @return
     */
    public static boolean isTextAnnotation(Node node) {
        return (BPMNTypes.TEXTANNOTATION.equals(node.getLocalName()));
    }

    public static boolean isTextAnnotation(BPMNElementNode element) {
        return isTextAnnotation(element.getElementNode());
    }

    /**
     * Returns true if the node is a sequenceFlow.
     * 
     * @param node
     * @return
     */
    public static boolean isSequenceFlow(Node node) {
        return (BPMNTypes.SEQUENCE_FLOW.equals(node.getLocalName()));
    }

    /**
     * Returns true if the node is a messageFlow.
     * 
     * @param node
     * @return
     */
    public static boolean isMessageFlow(Node node) {
        return (BPMNTypes.MESSAGE_FLOW.equals(node.getLocalName()));
    }

    /**
     * Returns true if the node is a Association.
     * 
     * @param node
     * @return
     */
    public static boolean isAssociation(Node node) {
        return (BPMNTypes.ASSOCIATION.equals(node.getLocalName()));
    }

    /**
     * This helper method returns a BPMNDI node for the given bpmnElement. A BPMNDI
     * element is identified by the ID stored in the attribute bpmnElement
     * <p>
     * 
     * <pre>
     *  {@code<bpmndi:BPMNShape id="BPMNShape_ihwxlQ" bpmnElement="event-1">}
     * </pre>
     * <p>
     * 
     * @param bpmnPlane - the bpmnPlane Element
     * @param nodeName  - name of the element
     * @param id        - the id referring to the main BPMN element
     */
    public Node findBPMNPlaneElement(String nodeName, String id) {
        if (id == null || id.isEmpty() || bpmnPlane == null || nodeName == null) {
            return null;
        }
        String fullNodeName = getPrefix(BPMNNS.BPMNDI) + ":" + nodeName;
        NodeList childList = bpmnPlane.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            // check the attribute bpmnElement
            if (fullNodeName.equals(child.getNodeName()) && child.hasAttributes()) {
                // get attributes names and values
                NamedNodeMap attributesMap = child.getAttributes();
                for (int j = 0; j < attributesMap.getLength(); j++) {
                    Node attr = attributesMap.item(j);
                    if ("bpmnElement".equals(attr.getNodeName()) && id.equals(attr.getNodeValue())) {
                        // found it!
                        return child;
                    }
                }
            }

        }
        // not found!
        return null;
    }

    /**
     * Returns the central logger instance
     * 
     * @return
     */
    public static Logger getLogger() {
        return logger;
    }

    public static void log(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }

    public static void debug(String message) {
        logger.info(message);
    }

    /**
     * This helper method loads the participant elements from a collaboration
     * diagram located in the 'bpmn2:collaboration' section. This section is
     * optional.
     * <p>
     * The method also removes deprecated participant elements automatically. See
     * Issue #299
     * 
     * @throws BPMNModelException
     */
    private void loadParticipantList() throws BPMNModelException {
        participants = new LinkedHashSet<Participant>();
        List<Element> invalidParticipantElementList = new ArrayList<Element>();
        NodeList collaborationNodeList = definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":collaboration");
        if (collaborationNodeList != null && collaborationNodeList.getLength() > 0) {

            // we only take the first collaboration element (this is what is expected)
            collaborationElement = (Element) collaborationNodeList.item(0);
            // now find all participants...
            NodeList participantList = collaborationElement
                    .getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":participant");
            logger.fine("..found " + participantList.getLength() + " participants");
            for (int i = 0; i < participantList.getLength(); i++) {
                Element item = (Element) participantList.item(i);
                Participant participant;
                String processRef = item.getAttribute("processRef");
                if (processRef == null || processRef.isEmpty()) {
                    // See issue #299
                    logger.warning("Participant " + item.getAttribute("id")
                            + " has no processRef! Element will be removed...");
                    invalidParticipantElementList.add(item);
                    continue;
                }
                participant = new Participant(this, item);
                // set processRef
                participant.setProcessRef(item.getAttribute("processRef"));
                // update process...
                participants.add(participant);
            }

            // remove deprecated participant elements form model
            for (Element _participant : invalidParticipantElementList) {
                collaborationElement.removeChild(_participant);
            }
        }
    }

    /**
     * This helper method loads the process elements from the current model. If the
     * model does not yet contain any process the method returns null
     * <p>
     * The method also verify the attribute processType. If the processType is not
     * set and but the process ID is listed in the optional list of participants the
     * processType will be set to 'Private'.
     * 
     * @param string
     * @return BPMNProcess instance
     * @throws BPMNModelException
     */
    private void loadProcessList() throws BPMNModelException {
        processes = new LinkedHashSet<BPMNProcess>();
        int publicCount = 0;

        // find process
        NodeList processList = definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":process");
        if (processList != null && processList.getLength() > 0) {
            for (int i = 0; i < processList.getLength(); i++) {
                Element item = (Element) processList.item(i);
                String id = item.getAttribute("id");
                if (id == null) {
                    id = "";
                }
                String processType = item.getAttribute("processType");
                if (processType.isEmpty()) {
                    // we do not have a process Type provided. We now verify
                    // if the process ID is listed in the list of participants.
                    // If the participant has a shape (Pool) than the type is 'Private'. Otherwise
                    // it seems to be the public default process
                    for (Participant participant : participants) {
                        if (id.equals(participant.getProcessRef())) {
                            // do we have a pool for this participant?
                            if (participant.getBpmnShape() != null) {
                                processType = BPMNTypes.PROCESS_TYPE_PRIVATE;
                            } else {
                                // no Pool - so we assume this is the public process
                                processType = BPMNTypes.PROCESS_TYPE_PUBLIC;
                                publicCount++;
                            }
                            break;
                        }
                    }
                } else {
                    if (BPMNTypes.PROCESS_TYPE_PUBLIC.equals(processType)) {
                        publicCount++;
                    }
                }

                BPMNProcess bpmnProcess = new BPMNProcess(this, item, processType);
                processes.add(bpmnProcess);

                // we need to update the containing Participant
                // This assignment can only be done now because the loadParticipantList is
                // called
                // before this method.
                Participant participant = bpmnProcess.findParticipant();
                if (participant != null) {
                    participant.setBpmnProcess(bpmnProcess);
                } else {
                    // verify if we have a participant for this process. If not we create one. Issue
                    // # #297
                    // <bpmn2:participant id="participant_1" name="Default Process"
                    // processRef="process_1"/>
                    if (isCollaborationDiagram()) {
                        logger.warning("Process " + bpmnProcess.getId()
                                + " has no participant reference! Missing element will be added...");

                        // create a new Dom node...
                        Element participantNode = createElement(BPMNNS.BPMN2, "participant");
                        String participantID = BPMNModel.generateShortID("participant");
                        participantNode.setAttribute("id", participantID);
                        participantNode.setAttribute("name", bpmnProcess.getName());
                        participantNode.setAttribute("processRef", bpmnProcess.getId());
                        this.collaborationElement.appendChild(participantNode);
                        // update model
                        Participant _new_participant = new Participant(this, participantNode);
                        _new_participant.setBpmnProcess(bpmnProcess);
                        participants.add(_new_participant);
                    }

                }

            }
        }

        // if we do not have a process at all or a public default process is missing in
        // the participant list, we create a default process now
        if (processes.size() == 0 || (participants.size() > 0 && publicCount == 0)) {
            buildProcess("process_" + (processes.size() + 1), "Default Process", BPMNTypes.PROCESS_TYPE_PUBLIC);
        } else if (publicCount > 1) {
            getLogger().warning("Invalid model structure! The model contains more than one public process instance!");
        }

    }

    /**
     * This helper method loads the MessageFlow elements from a collaboration
     * diagram located in the 'bpmn2:collaboration' section. This section is
     * optional.
     * 
     * @throws BPMNModelException
     * 
     */
    private void loadMessageFlowList() throws BPMNModelException {
        messageFlows = new LinkedHashSet<MessageFlow>();
        NodeList collaborationNodeList = definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":collaboration");
        if (collaborationNodeList != null && collaborationNodeList.getLength() > 0) {
            // we only take the first collaboration element (this is what is expected)
            collaborationElement = (Element) collaborationNodeList.item(0);
            // now find all messageFlows...
            NodeList messageFlowList = collaborationElement
                    .getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":" + BPMNTypes.MESSAGE_FLOW);
            logger.fine("..found " + messageFlowList.getLength() + " messageFlows");
            for (int i = 0; i < messageFlowList.getLength(); i++) {
                Element item = (Element) messageFlowList.item(i);
                MessageFlow messageFlow = new MessageFlow(this, item);
                messageFlows.add(messageFlow);
            }
        }
    }

    /**
     * This helper method loads the Signal elements from the diagram -
     * 'bpmn2:signal' .
     * 
     * @throws BPMNModelException
     */
    private void loadSignalList() throws BPMNModelException {
        signals = new LinkedHashSet<Signal>();
        NodeList signalNodeList = definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":" + BPMNTypes.SIGNAL);
        if (signalNodeList != null && signalNodeList.getLength() > 0) {
            for (int i = 0; i < signalNodeList.getLength(); i++) {
                Element item = (Element) signalNodeList.item(i);
                Signal signal = new Signal(this, item);
                signals.add(signal);
            }
        }
    }

    /**
     * This helper method loads the Message elements from the diagram -
     * 'bpmn2:message' .
     * 
     * @throws BPMNModelException
     */
    private void loadMessageList() throws BPMNModelException {
        messages = new LinkedHashSet<Message>();
        NodeList messageNodeList = definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + ":" + BPMNTypes.MESSAGE);
        if (messageNodeList != null && messageNodeList.getLength() > 0) {
            for (int i = 0; i < messageNodeList.getLength(); i++) {
                Element item = (Element) messageNodeList.item(i);
                Message message = new Message(this, item, BPMNTypes.MESSAGE, this.openDefaultProces());
                messages.add(message);
            }
        }
    }

    /**
     * Helper method to write the dom document to an output stream
     * <p>
     * The output file is prettified with an indent of 2 spaces
     * 
     * @param doc
     * @param output
     * @throws TransformerException
     */
    private void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        /*
         * The following code section is to handle a bad implementation in the
         * Imixs-BPMN (Eclipse-BPMN2) implementation
         * 
         * To ensure that the Open-BPMN model file is still readable by eclipse-bpmn we
         * need to remove the whitespace before and after CDATA tags.
         * 
         * See details: https://github.com/imixs/open-bpmn/issues/194
         * 
         * Otherwise we could just do here:
         * 
         * StreamResult result = new StreamResult(output);
         * transformer.transform(source, result);
         */
        // === BUGFIX START ===

        // first transform the result xml into a string
        StringWriter w = new StringWriter();
        Result dest = new StreamResult(w);
        transformer.transform(source, dest);
        String xmlString = w.toString();
        // No indentation (whitespace) for elements with a CDATA section.
        // See
        // https://stackoverflow.com/questions/55853220/handling-change-in-newlines-by-xml-transformation-for-cdata-from-java-8-to-java/75568933
        // xmlString =
        // xmlString.replaceAll(">\\s*+(<\\!\\[CDATA\\[(.|\\n|\\r\\n)*?]\\]>)\\s*</",
        // ">$1</");
        xmlString = BPMNXMLUtil.cleanCDATAWhiteSpace(xmlString);

        // write output
        try {
            output.write(xmlString.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // === BUGFIX END ===
    }

    /**
     * This method validates the current model. It returns a list of
     * validationErrors that contains a lable and a short description pointing to an
     * element that causes a problem.
     * <p>
     * If the list is empty, no errors where detected
     * 
     * @return list of BPMNValidationError. Can be empty if no errors where
     *         detected.
     */
    public List<BPMNValidationError> validate() {
        BPMNValidationHandler validationHandler = new BPMNValidationHandler(this);
        validationHandler.validate();
        return validationHandler.getValidationErrors();
    }

}
