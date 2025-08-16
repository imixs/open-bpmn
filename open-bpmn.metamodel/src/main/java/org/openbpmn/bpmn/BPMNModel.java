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
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNElementOrder;
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
import org.openbpmn.bpmn.util.BPMNModelUtil;
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
    // private Element defaultProcessElement;
    private BPMNProcess defaultProcess;
    private BPMNProcess subProcess;
    private Node bpmnDiagram;
    protected Set<Participant> participants = null;
    protected Map<String, BPMNProcess> bpmnProcesses = new ConcurrentHashMap<>();

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

            // Parse all namespaces using the new URI-based method
            parseAllNamespaces();

            // Load BPMNDiagram element....
            loadBpmnDiagram();
            // Load BPMNPlane element....
            // loadBpmnPlanes();

            // init the participant and process list
            loadProcessList();
            loadParticipantList();

            // load global elements
            loadMessageList();
            loadMessageFlowList();
            loadSignalList();

            // defaultProcessElement = openDefaultProcess().getElementNode();
        }

    }

    /**
     * Helper method to parse all namespaces based on the uri
     */
    private void parseAllNamespaces() {
        // Test for default namespace without prefix
        String defaultNameSpace = definitions.getAttribute("xmlns");
        if (defaultNameSpace != null && !defaultNameSpace.isEmpty()) {
            // Try to map the default namespace to any BPMN namespace
            BPMNNS matchedNamespace = findMatchingNamespace(defaultNameSpace);
            if (matchedNamespace != null) {
                if (!getUri(matchedNamespace).equals(defaultNameSpace)) {
                    logger.warning("Non-standard default namespace detected: " + defaultNameSpace + " - mapped to "
                            + matchedNamespace);
                }
                setPrefix(matchedNamespace, "");
                setUri(matchedNamespace, defaultNameSpace);
            }
        }

        // Parse all namespace attributes
        NamedNodeMap defAttributes = definitions.getAttributes();
        for (int j = 0; j < defAttributes.getLength(); j++) {
            Node node = defAttributes.item(j);
            String nodeName = node.getNodeName();
            String nodeValue = node.getNodeValue();

            // Skip non-namespace attributes
            if (!nodeName.startsWith("xmlns:")) {
                continue;
            }

            // String prefix = node.getLocalName(); // Gets the part after 'xmlns:'
            String prefix = nodeName.substring(6);

            // Map URIs to their corresponding BPMN namespace types
            mapUriToNamespace(prefix, nodeValue);
        }
    }

    /**
     * Helper method that maps a URI to the correct BPMN namespace based on the URI
     * value. Uses tolerant matching for non-standard but BPMN-like URIs.
     */
    private void mapUriToNamespace(String prefix, String uri) {
        // Try to find a matching namespace for this URI
        BPMNNS matchedNamespace = findMatchingNamespace(uri);
        if (matchedNamespace != null) {
            // Check if it's a non-standard URI
            if (!getUri(matchedNamespace).equals(uri)) {
                logger.warning("Non-standard namespace detected: " + uri + " with prefix: " + prefix + " - mapped to "
                        + matchedNamespace);
            }
            setPrefix(matchedNamespace, prefix);
            setUri(matchedNamespace, uri);
        }
    }

    /**
     * Finds the matching BPMNNS enum for a given URI.
     * First tries exact matches, then tolerant matching.
     * 
     * @param uri The namespace URI to match
     * @return The matching BPMNNS enum or null if no match found
     */
    private BPMNNS findMatchingNamespace(String uri) {
        if (uri == null || uri.isEmpty()) {
            return null;
        }

        // Try exact matches first for all namespaces
        for (BPMNNS namespace : BPMNNS.values()) {
            if (getUri(namespace).equals(uri)) {
                return namespace;
            }
        }

        // Tolerant matching for BPMN-like URIs
        if (uri.startsWith("http://www.omg.org/spec/BPMN/")) {
            if (uri.endsWith("/MODEL")) {
                return BPMNNS.BPMN2;
            } else if (uri.endsWith("/DI")) {
                return BPMNNS.BPMNDI;
            }
        }

        // Tolerant matching for DD-like URIs
        if (uri.startsWith("http://www.omg.org/spec/DD/")) {
            if (uri.endsWith("/DC")) {
                return BPMNNS.DC;
            } else if (uri.endsWith("/DI")) {
                return BPMNNS.DI;
            }
        }

        return null; // No match found
    }

    /*
     * Search a specific node by its namespace and element name
     */
    public NodeList findElementsByName(Element parent, BPMNNS namespace, String elementName) {
        // 1. Versuch: Mit Prefix
        NodeList result = parent.getElementsByTagName(getPrefix(namespace) + elementName);
        if (result != null && result.getLength() > 0) {
            return result;
        }

        // 2. Fallback: with URI if prefix is empty
        return parent.getElementsByTagNameNS(getUri(namespace), elementName);
    }

    /**
     * This method loads the first BPMNDiagram element. If
     * no BPMNDiagram yet exists, then the method build one.
     * 
     */
    private void loadBpmnDiagram() {
        // find bpmndi:BPMNDiagram
        NodeList diagramList = findElementsByName(doc.getDocumentElement(), BPMNNS.BPMNDI, "BPMNDiagram");
        if (diagramList != null && diagramList.getLength() > 0) {
            bpmnDiagram = diagramList.item(0);
        } else {
            // no diagram included - so we create an empty one
            getLogger().warning("No bpmndi:BPMNDiagram found - created default diagram");
            // <bpmndi:BPMNDiagram id="BPMNDiagram_1" name="Default Process Diagram">
            Element bpmnDiagram = createElement(BPMNNS.BPMNDI, "BPMNDiagram");
            bpmnDiagram.setAttribute("id", "BPMNDiagram_1");
            bpmnDiagram.setAttribute("name", "OpenBPMN Diagram");
            BPMNElementOrder.appendChild(definitions, bpmnDiagram);
            setBpmnDiagram(bpmnDiagram);
        }
    }

    /**
     * This method loads all BPMNPlanes from the BPMNDiagram. If
     * no BPMN Plane yet exists, then the method build one.
     * 
     */
    // private void loadBpmnPlanes() {
    // // find the corresponding BPMNPlane
    // bpmnPlanes = new HashSet<>();
    // NodeList planeList = findElementsByName(doc.getDocumentElement(),
    // BPMNNS.BPMNDI, "BPMNPlane");
    // if (planeList != null && planeList.getLength() > 0) {
    // Element bpmnPlane = (Element) planeList.item(0);
    // bpmnPlanes.add(bpmnPlane);
    // }
    // // if no plane exists yes, we create one
    // if (bpmnPlanes.size() == 0) {
    // // <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="process_1">
    // getLogger().warning("No bpmndi:BPMNPlane found - created default plane");
    // Element bpmnDefaultPlane = createElement(BPMNNS.BPMNDI, "BPMNPlane");
    // bpmnDefaultPlane.setAttribute("id", "BPMNPlane_1");
    // NodeList nodeList = findElementsByName(definitions, BPMNNS.BPMN2,
    // "collaboration");
    // if (nodeList == null || nodeList.getLength() == 0) {
    // // Take the default process as plane ref...
    // nodeList = findElementsByName(definitions, BPMNNS.BPMN2, "process");
    // }
    // if (nodeList != null && nodeList.getLength() > 0) {
    // Element refElement = (Element) nodeList.item(0);
    // bpmnDefaultPlane.setAttribute("bpmnElement", refElement.getAttribute("id"));
    // }
    // getBpmnDiagram().appendChild(bpmnDefaultPlane);
    // bpmnPlanes.add(bpmnDefaultPlane);
    // }
    // }

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
     * The method automatically adds the prefix separator ':' if the prefix is not
     * empty. This is necessary to handle default namespaces without a prefix
     * correctly
     */
    public void setPrefix(BPMNNS ns, String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.isEmpty()) {
            PREFIX_BY_NAMESPACE.put(ns, prefix);
        } else {
            PREFIX_BY_NAMESPACE.put(ns, prefix + ":");
        }

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
        if (!hasNamespace(namespace)) {
            definitions.setAttribute("xmlns:" + namespace, uri);
        }
    }

    /**
     * Returns the public default process
     * 
     * @return
     */
    public BPMNProcess getDefaultProcess() {
        return defaultProcess;
    }

    /**
     * Returns the current subProcess. The subProcess property indicates a
     * SubProcess which is currently expanded. This flag can be used by an UI to
     * visualize the elements of a subprocess instead of the default process
     * 
     * @return
     */
    public BPMNProcess getSubProcess() {
        return subProcess;
    }

    /**
     * The method tests if a given SubProcess ID belongs to an expandable element.
     * This is used by the BPMNToggleSubProcessOperation
     * 
     * @param subProcessId
     * @return
     */
    public boolean isExpandableSubProcess(String subProcessId) {

        if (subProcessId == null) {
            this.subProcess = null;
            return false;
        }

        // find task....
        Activity subTask = (Activity) this.findElementNodeById(subProcessId);
        if (subTask != null) {
            // skip if triggeredByEvent=true
            // if (subTask. "triggeredByEvent".)
            String triggeredByEvent = subTask.getAttribute("triggeredByEvent");
            boolean isTriggeredByEvent = (triggeredByEvent != null && "true".equalsIgnoreCase(triggeredByEvent));
            if (BPMNTypes.SUB_PROCESS.equals(subTask.getType())) {
                // In case of isTriggeredByEvent does not exist or is false we assume that we
                // can expand the subTask
                if (!isTriggeredByEvent) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Sets the currently expanded sub-process by its ID. The sub process is a task
     * element with embedded elements. It behaves like a Process. The Subprocess
     * will be initialized automatically.
     * 
     * @param subProcess
     */
    public void setSubProcess(String subProcessId) {
        try {
            if (subProcessId == null) {
                this.subProcess = null;
                return;
            }

            // find task....
            Activity subTask = (Activity) this.findElementNodeById(subProcessId);
            if (subTask != null) {
                // skip if triggeredByEvent=true
                // if (subTask. "triggeredByEvent".)
                String triggeredByEvent = subTask.getAttribute("triggeredByEvent");
                boolean isTriggeredByEvent = (triggeredByEvent != null && "true".equalsIgnoreCase(triggeredByEvent));

                if (BPMNTypes.SUB_PROCESS.equals(subTask.getType())) {
                    // In case of isTriggeredByEvent - we exit here - seems to make no sense
                    if (isTriggeredByEvent) {
                        logger.info("subProcess - triggeredByEvent=" + isTriggeredByEvent);
                        this.subProcess = null;
                        return;
                    }
                    this.subProcess = subTask.openSubProcess();
                } else {
                    logger.warning("Invalid SubProcessID '" + subProcessId
                            + "' - not of type subProcess. Validate Model!");
                }
            } else {
                logger.warning("Invalid SubProcessID '" + subProcessId
                        + "' - not found in current Model!");
            }

        } catch (BPMNModelException e) {
            logger.warning("Unable to open SubProcess '" + subProcessId + "' - " + e.getMessage());
            this.subProcess = null;
        }
    }

    /**
     * Returns true if the given namespace already exists in the BPMN definitions
     * element.
     * 
     * @param namespace
     * @return
     */
    public boolean hasNamespace(String namespace) {
        return definitions.hasAttribute("xmlns:" + namespace);
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
        if (bpmnProcesses.size() == 0
                || (bpmnProcesses.size() == 1 && bpmnProcesses.values().iterator().next().isEmpty())) {
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

    public Element getCollaborationElement() {
        return collaborationElement;
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

    /**
     * Returns a List of all registered BPMNProcesses.
     * 
     * @return
     */
    public List<BPMNProcess> getBpmnProcessList() {
        if (bpmnProcesses == null) {
            synchronized (this) {
                if (bpmnProcesses == null) {
                    bpmnProcesses = new ConcurrentHashMap<>();
                }
            }
        }
        return new ArrayList<>(bpmnProcesses.values());
    }

    public Map<String, BPMNProcess> getBpmnProcesses() {
        if (bpmnProcesses == null) {
            synchronized (this) {
                if (bpmnProcesses == null) {
                    bpmnProcesses = new ConcurrentHashMap<>();
                }
            }
        }
        return bpmnProcesses;
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

    public Node getBpmnDiagram() {
        return bpmnDiagram;
    }

    public void setBpmnDiagram(Node bpmnDiagram) {
        this.bpmnDiagram = bpmnDiagram;
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
    public Participant addParticipant(String name, String processType) throws BPMNModelException {

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

            // this.getBpmnPlane().setAttribute("bpmnElement", collaborationID);
            // Element bpmnPlane =
            // findBPMNPlaneByProcessId(this.getDefaultProcessElement().getAttribute("id"));
            // bpmnPlane.setAttribute("bpmnElement", collaborationID);

            // Now we migrate all existing processes into the new collaboration element....
            for (BPMNProcess existingProcess : getBpmnProcessList()) {
                Element migratedParticipantNode = createElement(BPMNNS.BPMN2, "participant");
                String participantID = BPMNModel.generateShortID("participant");
                migratedParticipantNode.setAttribute("id", participantID);
                migratedParticipantNode.setAttribute("name", existingProcess.getName());
                migratedParticipantNode.setAttribute("processRef", existingProcess.getId());

                BPMNElementOrder.appendChild(collaborationElement, migratedParticipantNode);

                existingProcess.setAttribute("definitionalCollaborationRef", collaborationElement.getAttribute("id"));
                // finally add a new BPMNParticipant to the participant list
                getParticipants().add(new Participant(this, migratedParticipantNode, existingProcess));
            }

        }

        // now create and add a new Process to this Participant...
        // <bpmn2:process id="Process_2" name="Non-initiating Process"
        // definitionalCollaborationRef="Collaboration_1" isExecutable="false"/>
        int processNumber = this.bpmnProcesses.size() + 1;
        BPMNProcess process = buildProcess(BPMNModel.generateShortID("process"), "Process " + processNumber,
                processType);
        process.setAttribute("definitionalCollaborationRef", collaborationElement.getAttribute("id"));
        process.setName(name);

        // create a new Dom node...
        Element participantNode = createElement(BPMNNS.BPMN2, "participant");
        String participantID = BPMNModel.generateShortID("participant");
        participantNode.setAttribute("id", participantID);
        participantNode.setAttribute("name", name);
        // this.collaborationElement.appendChild(participantNode);
        BPMNElementOrder.appendChild(this.collaborationElement, participantNode);
        // add BPMNParticipant instance
        Participant bpmnParticipant = new Participant(this, participantNode, process);
        getParticipants().add(bpmnParticipant);

        // create the pool shape
        createPool(bpmnParticipant);

        return bpmnParticipant;
    }

    /**
     * Adds a new BPMNParticipant element and the corresponding BPMNProcess to
     * collaboration diagram.
     * <p>
     * The method creates a process of the type 'Private' and updates the
     * collaboration diagram structure.
     * 
     * @param name
     * @param processType
     * @return
     * @throws BPMNModelException
     */
    public Participant addParticipant(String name) throws BPMNModelException {
        return this.addParticipant(name, BPMNTypes.PROCESS_TYPE_PRIVATE);
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
            bpmnLabel.updateDimension(BPMNLabel.DEFAULT_WIDTH, BPMNLabel.DEFAULT_HEIGHT);
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
        if (bpmnProcesses.containsKey(id)) {
            throw new BPMNInvalidIDException(BPMNInvalidIDException.DUPLICATE_ID,
                    "id '" + id + "' is already in use!");
        }
        // xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
        Element processElement = createElement(BPMNNS.BPMN2, "process");
        logger.fine(processElement.getNodeName());
        logger.fine(processElement.getLocalName());
        logger.fine(processElement.getNamespaceURI());
        processElement.setAttribute("id", id);
        // set optional name
        if (name != null) {
            processElement.setAttribute("name", name);
        }

        // set type
        if (type == null || type.isEmpty()) {
            type = BPMNTypes.PROCESS_TYPE_PUBLIC;
        }
        processElement.setAttribute("processType", type);

        definitions.insertBefore(processElement, this.getBpmnDiagram());

        BPMNProcess bpmnProcess = new BPMNProcess(this, processElement, type, false);
        bpmnProcesses.put(bpmnProcess.getId(), bpmnProcess);

        return bpmnProcess;
    }

    /**
     * Finds the matching bpmn plane by a given process id
     * // <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="process_1">
     * 
     * @return
     */
    // public Element xxfindBPMNPlaneByProcessId(String bpmnProcessId) {
    // if (bpmnPlanes == null || bpmnPlanes.size() == 0) {
    // // not defined
    // return null;
    // }
    // for (Element bpmnPlane : bpmnPlanes) {
    // String processID = bpmnPlane.getAttribute("bpmnElement");
    // if (bpmnProcessId.equals(processID)) {
    // return bpmnPlane;
    // }
    // }
    // // default to first defined plane....
    // return bpmnPlanes.iterator().next();
    // }

    /**
     * Creates a BPMN shape node for this element
     * <p>
     * <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
     * 
     * @throws BPMNMissingElementException
     */
    public Element buildBPMNShape(BPMNElementNode bpmnElement) throws BPMNModelException {
        Element bpmnShape;
        // if (getBpmnPlane() == null) {
        if (bpmnElement.getBpmnProcess().getBpmnPlane() == null) {
            throw new BPMNMissingElementException("Missing bpmnPlane in current model context");
        }
        if (bpmnElement.getId() != null) {
            bpmnShape = createElement(BPMNNS.BPMNDI, "BPMNShape");
            bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
            bpmnShape.setAttribute("bpmnElement", bpmnElement.getId());

            // BPMNElementOrder.appendChild(getBpmnPlane(), bpmnShape);
            BPMNElementOrder.appendChild(bpmnElement.getBpmnProcess().getBpmnPlane(), bpmnShape);
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
     * In case no ID is provided (null) the method returns the first public
     * (default) process from the model.
     * 
     * @param string
     * @return BPMNProcess instance
     * @throws BPMNModelException
     */
    public BPMNProcess openProcess(String id) throws BPMNModelException {

        // fetch process by id
        BPMNProcess process = findProcessById(id);

        // if we found a matching process than we can initialize it
        if (process != null) {
            process.init();
        } else {
            logger.warning("BPMNProcess '" + id + "' not defined in current model!");
        }
        return process;
    }

    /**
     * This Method opens the public default BPMNProcess and initializes all BPMN
     * elements of the Process. The default process always exists and is not
     * embedded in a Pool.
     */
    public BPMNProcess openDefaultProcess() {
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
                // this.bpmnPlane.removeChild(participant.getBpmnShape());
                participant.getBpmnProcess().getBpmnPlane().removeChild(participant.getBpmnShape());
            }
            this.collaborationElement.removeChild(participant.getElementNode());
            // remove the participant with its elements
            // this.bpmnProcessList
            bpmnProcesses.remove(process.getId());
            this.getParticipants().remove(participant);
        }

    }

    /**
     * Creates an element with a given namespace
     */
    public Element createElement(BPMNNS ns, String type) {
        Element element = this.getDoc().createElementNS(getUri(ns), getPrefix(ns) + type);
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
                        && (child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + "incoming")
                                || child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + "outgoing"))) {
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
                        && (child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + "incoming")
                                || child.getNodeName().equals(getPrefix(BPMNNS.BPMN2) + "outgoing"))) {
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
            // getBpmnPlane().removeChild(bpmnEdge.getBpmnEdge());

            BPMNProcess process = findProcessById(bpmnEdge.getProcessId());

            // Element bpmnPlane = (Element) findBPMNPlaneElement("BPMNEdge",
            // bpmnEdge.getId());
            // bpmnPlane.removeChild(bpmnEdge.getBpmnEdge());
            process.getBpmnPlane().removeChild(bpmnEdge.getBpmnEdge());
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

        Message message = new Message(this, bpmnElement, BPMNTypes.MESSAGE, this.openDefaultProcess());
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
    public Signal findSignalByName(String name) {
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
     * Finds a Signal element by its ID withing the diagram
     * <p>
     * <bpmn2:signal id="Signal_1" name="My Signal"/>
     * 
     * @param name - name of the signal
     * @throws BPMNInvalidReferenceException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidTypeException
     */
    public Signal findSignal(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Signal signal = null;
        for (Signal _signal : getSignals()) {
            if (id.equals(_signal.getId())) {
                signal = _signal;
                break;

            }
        }
        return signal;
    }

    /**
     * Deletes a Signal element from this diagram.
     * <p>
     * 
     * @param id
     */
    public void deleteSignal(String id) {
        Signal signal = findSignal(id);
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
            // getBpmnPlane().removeChild(message.getBpmnShape());
            message.getBpmnProcess().getBpmnPlane().removeChild(message.getBpmnShape());

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
        List<BPMNProcess> processList = this.getBpmnProcessList();
        for (BPMNProcess process : processList) {
            // analyze the content of the process
            BPMNElement baseElement = process.findElementById(id);
            if (baseElement != null) {
                return baseElement;
            }
        }

        // no corresponding element found!
        return null;
    }

    /**
     * This method returns the bpmn2:extensionElements node for a given BPMN
     * Element. If not yet defined, the method creates an new empty
     * bpmn2:extensionElements node.
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
            // element.insertBefore(extensionElement, element.getFirstChild());
            BPMNElementOrder.appendChild(element, extensionElement);
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
            if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE
                    && tagName.equals(childNode.getNodeName())) {
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
                // extensionElement.appendChild(autoAlignElement);
                BPMNElementOrder.appendChild(extensionElement, autoAlignElement);
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
        List<BPMNProcess> processList = this.getBpmnProcessList();
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
        List<BPMNProcess> processList = this.getBpmnProcessList();
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
                BPMNElementNode baseElement = openDefaultProcess().findElementNodeById(id);
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
        Participant defaultParticipant = findParticipantByProcessId(this.openDefaultProcess().getId());
        return defaultParticipant;
    }

    /**
     * Finds a Process by its name
     * 
     * 
     * The method returns null if no matching process was found.
     * 
     * Note: The method does not open the process by default. So a call
     * 'model.openProcess' may be needed in addition.
     * 
     * @param name
     * @return
     * @throws BPMNModelException
     */
    public BPMNProcess findProcessByName(String processName) throws BPMNModelException {
        if (processName == null || processName.isEmpty()) {
            return null; // no name provided!
        }
        List<BPMNProcess> processList = getBpmnProcessList();
        for (BPMNProcess _process : processList) {
            if (processName.equals(_process.getName())) {
                _process.init();
                return _process;
            }
        }
        // no matching process found;
        return null;
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
        String tagName = getPrefix(ns) + nodeName;
        if (parent != null && nodeName != null) {
            NodeList childs = parent.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                Node childNode = childs.item(i);
                if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE
                        && tagName.equals(childNode.getNodeName())) {
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
     * 
     * if the ID == null the method returns the first public
     * (default) process from the model.
     * 
     * @param processId
     * @return
     */
    public BPMNProcess findProcessById(String processId) {
        if (bpmnProcesses == null) {
            synchronized (this) {
                if (bpmnProcesses == null) {
                    bpmnProcesses = new ConcurrentHashMap<>();
                }
            }
        }

        // Null-Check am Anfang
        if (processId != null) {
            return bpmnProcesses.get(processId);
        } else {
            if (defaultProcess != null) {
                return defaultProcess;
            }
            // fallback resolve the default process
            Iterator<BPMNProcess> it = getBpmnProcessList().iterator();
            while (it.hasNext()) {
                BPMNProcess p = it.next();
                // if we have only one process take that one
                if (bpmnProcesses.size() == 1) {
                    // we take the only available process
                    if (!BPMNTypes.PROCESS_TYPE_PUBLIC.equals(p.getProcessType())) {
                        // auto fix process type
                        logger.info("fix wrong process type to PUBIC");
                        p.setProcessType(BPMNTypes.PROCESS_TYPE_PUBLIC);
                    }
                    defaultProcess = p;
                    return p;

                }
                // try to get the first public process
                if (BPMNTypes.PROCESS_TYPE_PUBLIC.equals(p.getProcessType())
                        || bpmnProcesses.size() == 1) {
                    // we take the first public process (should only exists once in the model)
                    defaultProcess = p;
                    return p;
                }

            }
            // not found!
            return null;
        }
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
            writeToOutputStream(doc, output);
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
            writeToOutputStream(doc, output);
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

    public static boolean isMessage(Node node) {
        return (BPMNTypes.MESSAGE.equals(node.getLocalName()));
    }

    public static boolean isMessage(BPMNElementNode element) {
        return isMessage(element.getElementNode());
    }

    /**
     * Returns true if the node is a dataStore node.
     * 
     * @param node
     * @return
     */
    public static boolean isDataStore(Node node) {
        return (BPMNTypes.DATASTOREREFERENCE.equals(node.getLocalName()));
    }

    public static boolean isDataStore(BPMNElementNode element) {
        return isDataStore(element.getElementNode());
    }

    /**
     * Returns true if the node is a dataStore node.
     * 
     * @param node
     * @return
     */
    public static boolean isDataStoreReference(Node node) {
        return (BPMNTypes.DATASTOREREFERENCE.equals(node.getLocalName()));
    }

    public static boolean isDataStoreReference(BPMNElementNode element) {
        return isDataStoreReference(element.getElementNode());
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
    // public Node xfindBPMNPlaneElement(String nodeName, String id) {
    // if (id == null || id.isEmpty() || bpmnPlanes == null || nodeName == null) {
    // return null;
    // }
    // String fullNodeName = getPrefix(BPMNNS.BPMNDI) + nodeName;
    // // iterate over all registered bpmnPlanes
    // for (Element bpmnPlane : bpmnPlanes) {
    // NodeList childList = bpmnPlane.getChildNodes();
    // for (int i = 0; i < childList.getLength(); i++) {
    // Node child = childList.item(i);
    // // check the attribute bpmnElement
    // if (fullNodeName.equals(child.getNodeName()) && child.hasAttributes()) {
    // // get attributes names and values
    // NamedNodeMap attributesMap = child.getAttributes();
    // for (int j = 0; j < attributesMap.getLength(); j++) {
    // Node attr = attributesMap.item(j);
    // if ("bpmnElement".equals(attr.getNodeName()) &&
    // id.equals(attr.getNodeValue())) {
    // // found it!
    // return child;
    // }
    // }
    // }
    // }
    // }
    // // not found!
    // return null;
    // }

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
        int publicCount = 0;
        participants = new LinkedHashSet<Participant>();
        List<Element> invalidParticipantElementList = new ArrayList<Element>();
        // NodeList collaborationNodeList =
        // definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + "collaboration");
        NodeList collaborationNodeList = findElementsByName(definitions, BPMNNS.BPMN2, "collaboration");

        if (collaborationNodeList != null && collaborationNodeList.getLength() > 0) {

            // we only take the first collaboration element (this is what is expected)
            collaborationElement = (Element) collaborationNodeList.item(0);
            // now find all participants...
            // NodeList participantList = collaborationElement
            // .getElementsByTagName(getPrefix(BPMNNS.BPMN2) + "participant");
            NodeList participantList = findElementsByName(collaborationElement, BPMNNS.BPMN2, "participant");

            logger.fine("..found " + participantList.getLength() + " participants");
            for (int i = 0; i < participantList.getLength(); i++) {
                Element item = (Element) participantList.item(i);
                String processRef = item.getAttribute("processRef");
                if (processRef == null || processRef.isEmpty()) {
                    // See issue #299
                    logger.warning("Participant " + item.getAttribute("id")
                            + " has no processRef! Element will be removed...");
                    invalidParticipantElementList.add(item);
                    continue;
                }
                BPMNProcess process = findProcessById(processRef);
                if (process != null) {
                    if (process.isPublicProcess()) {
                        publicCount++;
                    }
                    participants.add(new Participant(this, item, process));
                }
            }

            // remove deprecated participant elements form model
            for (Element _participant : invalidParticipantElementList) {
                collaborationElement.removeChild(_participant);
            }

            // if we do not have a process at all or a public default process is missing in
            // the participant list, we create a default process now
            if (bpmnProcesses.size() == 0 || (participants.size() > 0 && publicCount == 0)) {
                logger.warning(
                        "Invalid model structure: No public process node found - missing default process will be added...");
                // Do we have a normal process diagram?
                // create a new default participant
                addParticipant("Default Process", BPMNTypes.PROCESS_TYPE_PUBLIC);
                // send a notification
                this.setDirty(true);
                this.getNotifications().add(new ModelNotification(ModelNotification.Severity.INFO,
                        "Default process created.",
                        "Empty model or missing public process. Default process was created successful."));

            } else if (publicCount > 1) {
                getLogger()
                        .warning("Invalid model structure! The model contains more than one public process instance!");
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
        bpmnProcesses = new ConcurrentHashMap<>();
        int publicCount = 0;

        // find processes...
        NodeList processList = findElementsByName(definitions, BPMNNS.BPMN2, "process");
        if (processList != null && processList.getLength() > 0) {

            // first we need to test if all processes have a processType attribute or if we
            // found at lease one public process!
            for (int i = 0; i < processList.getLength(); i++) {
                Element processElement = (Element) processList.item(i);
                if (BPMNTypes.PROCESS_TYPE_PUBLIC.equals(processElement.getAttribute("processType"))) {
                    publicCount++;
                }
            }

            // now we can build the process list
            for (int i = 0; i < processList.getLength(); i++) {
                Element processElement = (Element) processList.item(i);

                String processType = processElement.getAttribute("processType");
                if (processType.isEmpty()) {
                    // fix missing type attribute!

                    // Issue 409
                    // in case that the process contains a laneSet we can be sure this is a private
                    // process.
                    Set<Element> laneSets = findChildNodesByName(processElement, BPMNNS.BPMN2, "laneSet");
                    if (laneSets != null && laneSets.size() > 0) {
                        processType = BPMNTypes.PROCESS_TYPE_PRIVATE;
                    } else {
                        // if the model doese not contain at least one public process, we default now to
                        // a public type
                        if (publicCount == 0) {
                            processType = BPMNTypes.PROCESS_TYPE_PUBLIC;
                            publicCount++;

                        } else {
                            // else set the process to a private type!
                            processType = BPMNTypes.PROCESS_TYPE_PRIVATE;
                        }
                    }

                    logger.warning(
                            "Set missing processType=" + processType + " for process "
                                    + processElement.getAttribute("id"));
                    processElement.setAttribute("processType", processType);
                    setDirty(true);
                }

                BPMNProcess bpmnProcess = new BPMNProcess(this, processElement, processType, false);
                bpmnProcesses.put(bpmnProcess.getId(), bpmnProcess);
                if (BPMNTypes.PROCESS_TYPE_PUBLIC.equals(processType)) {
                    defaultProcess = bpmnProcess;
                    // defaultProcessElement = processElement;
                }

            }
        }

        // in case we found no process at all, we create a default process now
        if (bpmnProcesses.size() == 0) {
            // just create the missing public default process
            BPMNProcess publicProcess = buildProcess("process_1",
                    "Default Process", BPMNTypes.PROCESS_TYPE_PUBLIC);
            bpmnProcesses.put(publicProcess.getId(), publicProcess);
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
        // NodeList collaborationNodeList =
        // definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + "collaboration");
        NodeList collaborationNodeList = findElementsByName(definitions, BPMNNS.BPMN2, "collaboration");
        if (collaborationNodeList != null && collaborationNodeList.getLength() > 0) {
            // we only take the first collaboration element (this is what is expected)
            collaborationElement = (Element) collaborationNodeList.item(0);
            // now find all messageFlows...
            // NodeList messageFlowList = collaborationElement
            // .getElementsByTagName(getPrefix(BPMNNS.BPMN2) + BPMNTypes.MESSAGE_FLOW);
            NodeList messageFlowList = findElementsByName(collaborationElement, BPMNNS.BPMN2, BPMNTypes.MESSAGE_FLOW);
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
     * The method detects duplicates and prints a warning if found.
     * 
     * @throws BPMNModelException
     */
    private void loadSignalList() throws BPMNModelException {
        signals = new LinkedHashSet<Signal>();
        List<String> duplicates = new ArrayList<>();
        // NodeList signalNodeList =
        // definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + BPMNTypes.SIGNAL);
        NodeList signalNodeList = findElementsByName(definitions, BPMNNS.BPMN2, BPMNTypes.SIGNAL);
        if (signalNodeList != null && signalNodeList.getLength() > 0) {
            for (int i = 0; i < signalNodeList.getLength(); i++) {
                Element item = (Element) signalNodeList.item(i);
                Signal signal = new Signal(this, item);
                String id = signal.getId();
                if (duplicates.contains(id)) {
                    logger.warning("Duplicate bpmn2:signal '" + id + "'' found, signal will be ignored!");
                } else {
                    duplicates.add(id);
                    signals.add(signal);
                }
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
        // NodeList messageNodeList =
        // definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) +
        // BPMNTypes.MESSAGE);
        NodeList messageNodeList = findElementsByName(definitions, BPMNNS.BPMN2, BPMNTypes.MESSAGE);
        if (messageNodeList != null && messageNodeList.getLength() > 0) {
            for (int i = 0; i < messageNodeList.getLength(); i++) {
                Element item = (Element) messageNodeList.item(i);
                Message message = new Message(this, item, BPMNTypes.MESSAGE, this.openDefaultProcess());
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
    public void writeToOutputStream(Document doc, OutputStream output) throws TransformerException {
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
        xmlString = BPMNModelUtil.cleanCDATAWhiteSpace(xmlString);

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

}
