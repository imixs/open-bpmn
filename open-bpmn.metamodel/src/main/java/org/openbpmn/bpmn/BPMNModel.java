package org.openbpmn.bpmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
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
    private BPMNProcess context = null;

    public static List<String> BPMN_ACTIVITIES = Arrays.asList(new String[] { //
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.USER_TASK, //
            "receiveTask", "businessRuleTask", "subProcess", "adHocSubProcess", "transaction", "callActivity" });

    public static List<String> BPMN_TASKS = Arrays.asList(new String[] { //
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.USER_TASK //
    });

    public static List<String> BPMN_EVENTS = Arrays.asList(new String[] { //
            BPMNTypes.START_EVENT, //
            BPMNTypes.END_EVENT, //
            BPMNTypes.CATCH_EVENT, //
            BPMNTypes.THROW_EVENT //
    });

    public final static List<String> BPMN_FLOWELEMENTS = Arrays.asList(//
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.EXCLUSIVE_GATEWAY, //
            BPMNTypes.INCLUSIVE_GATEWAY, //
            BPMNTypes.START_EVENT, //
            BPMNTypes.END_EVENT, //
            BPMNTypes.CATCH_EVENT, //
            BPMNTypes.THROW_EVENT, //
            BPMNTypes.SEQUENCE_FLOW);

    public static List<String> BPMN_EVENT_DEFINITIONS = Arrays.asList(new String[] { //
            BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
            BPMNTypes.EVENT_DEFINITION_TIMER, //
            BPMNTypes.EVENT_DEFINITION_SIGNAL, //
            BPMNTypes.EVENT_DEFINITION_MESSAGE, //
            BPMNTypes.EVENT_DEFINITION_LINK, //
            BPMNTypes.EVENT_DEFINITION_ERROR, //
            BPMNTypes.EVENT_DEFINITION_TERMINATE, //
            BPMNTypes.EVENT_DEFINITION_COMPENSATION });

    public static List<String> BPMN_GATEWAYS = Arrays.asList(new String[] { //
            BPMNTypes.EXCLUSIVE_GATEWAY, //
            BPMNTypes.INCLUSIVE_GATEWAY, //
            BPMNTypes.PARALLEL_GATEWAY, //
            BPMNTypes.COMPLEX_GATEWAY //
    });

    public static List<String> BPMN_SQUENCEFLOWS = Arrays.asList(new String[] { BPMNTypes.SEQUENCE_FLOW });

    public static final String PARTICIPANT = "participant";

    private Element definitions;
    private Document doc;

    private Node bpmnDiagram;

    private final Map<BPMNNS, String> URI_BY_NAMESPACE = new HashMap<>();

    /**
     * Returns the namespace uri for a given namespace
     * 
     * @param ns
     * @return
     */
    public String getNameSpaceUri(BPMNNS ns) {
        return URI_BY_NAMESPACE.get(ns);
    }

    public void setNameSpaceUri(BPMNNS ns, String uri) {
        URI_BY_NAMESPACE.put(ns, uri);
    }

    /**
     * This method instantiates a new BPMN model with the default BPMN namespaces.
     * 
     * @param doc
     */
    private BPMNModel() {
        setNameSpaceUri(BPMNNS.BPMN2, "http://www.omg.org/spec/BPMN/20100524/MODEL");
        setNameSpaceUri(BPMNNS.BPMNDI, "http://www.omg.org/spec/BPMN/20100524/DI");
        setNameSpaceUri(BPMNNS.DI, "http://www.omg.org/spec/DD/20100524/DI");
        setNameSpaceUri(BPMNNS.DC, "http://www.omg.org/spec/DD/20100524/DC");

    }

    /**
     * This method instantiates a new BPMN model based on a given
     * org.w3c.dom.Document. The method parses the BPMN namespaces.
     * 
     * @param doc
     */
    public BPMNModel(Document doc) {
        this();
        if (doc != null) {
            this.doc = doc;

            definitions = doc.getDocumentElement();

            // parse the BPMN namespaces
            NamedNodeMap defAttributes = definitions.getAttributes();
            for (int j = 0; j < defAttributes.getLength(); j++) {
                Node node = defAttributes.item(j);
                // update to new namespace uri if not matching the default URI
                if (BPMNNS.BPMN2.prefix.equals(node.getLocalName())
                        && !getNameSpaceUri(BPMNNS.BPMN2).equals(node.getNodeValue())) {
                    logger.fine("...set BPMN2 namespace URI: " + node.getNodeValue());
                    setNameSpaceUri(BPMNNS.BPMN2, node.getNodeValue());
                }
                if (BPMNNS.BPMNDI.prefix.equals(node.getLocalName())
                        && !getNameSpaceUri(BPMNNS.BPMNDI).equals(node.getNodeValue())) {
                    logger.fine("...set BPMNDI namespace URI: " + node.getNodeValue());
                    setNameSpaceUri(BPMNNS.BPMNDI, node.getNodeValue());
                }
                if (BPMNNS.DC.prefix.equals(node.getLocalName())
                        && !getNameSpaceUri(BPMNNS.DC).equals(node.getNodeValue())) {
                    logger.fine("...set DC namespace URI: " + node.getNodeValue());
                    setNameSpaceUri(BPMNNS.DC, node.getNodeValue());
                }
                if (BPMNNS.DI.prefix.equals(node.getLocalName())
                        && !getNameSpaceUri(BPMNNS.DI).equals(node.getNodeValue())) {
                    logger.fine("...set DI namespace URI: " + node.getNodeValue());
                    setNameSpaceUri(BPMNNS.DI, node.getNodeValue());
                }
            }

            // find bpmndi:BPMNDiagram
            NodeList diagramList = doc.getElementsByTagName(BPMNNS.BPMNDI.prefix + ":BPMNDiagram");
            if (diagramList != null && diagramList.getLength() > 0) {
                bpmnDiagram = diagramList.item(0);
            }
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
        if (!definitions.hasAttribute("xmlns:" + namespace)) {
            definitions.setAttribute("xmlns:" + namespace, uri);
        }
    }

    public BPMNProcess getContext() {

        return context;
    }

    public Node getBpmnDiagram() {
        return bpmnDiagram;
    }

    public void setBpmnDiagram(Node bpmnDiagram) {
        this.bpmnDiagram = bpmnDiagram;
    }

    /**
     * Builds a new BPMNProcess element and adds this into this model instance. This
     * method also generates the BPMNPlane
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
     * @param id
     * @param name
     * @param type - EventType
     * @throws BPMNModelException
     */
    public BPMNProcess buildProcess(String id) throws BPMNModelException {
        // xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
        Element process = createElement(BPMNNS.BPMN2, "process");
        logger.fine(process.getNodeName());
        logger.fine(process.getLocalName());
        logger.fine(process.getNamespaceURI());
        process.setAttribute("id", id);
        // definitions.appendChild(process);
        definitions.insertBefore(process, this.getBpmnDiagram());

        // add bpmndi:BPMNPlane
        // <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="process_2">
        Element bpmnPlane = createElement(BPMNNS.BPMNDI, "BPMNPlane");
        bpmnPlane.setAttribute("id", "BPMNPlane_" + id);
        bpmnPlane.setAttribute("bpmnElement", id);
        this.getBpmnDiagram().appendChild(bpmnPlane);
        // definitions.insertBefore(bpmnPlane,this.getBpmnDiagram());

        BPMNProcess bpmnProcess = openContext(id);
        // new BPMNProcess(this, process);
        return bpmnProcess;
    }

    /**
     * This Method opens a BPMN context (e.g. a Process) with the given ID. The
     * method returns null if no process with the given ID exists.
     * <p>
     * In case no ID is provided (null) the method returns the first (default)
     * process from the model.
     * 
     * @param string
     * @return BPMNProcess instance
     * @throws BPMNModelException
     */
    public BPMNProcess openContext(String id) throws BPMNModelException {

        // find process
        NodeList processList = definitions.getElementsByTagName(BPMNNS.BPMN2.prefix + ":process");
        logger.fine("..found " + processList.getLength() + " processes");

        for (int i = 0; i < processList.getLength(); i++) {
            Element item = (Element) processList.item(i);
            context = new BPMNProcess(this, item);
            if (id != null && !id.equals(context.getId())) {
                // not match of the requested processs ID
                continue;
            }
            context.setBpmnPlane(findBpmnPlane(bpmnDiagram, context.getId()));

            context.init();
            return context;
        }

        return null;
    }

    public Element createElement(BPMNNS ns, String type) {
        Element element = this.getDoc().createElementNS(getNameSpaceUri(ns), ns.prefix + ":" + type);
        return element;
    }

    /**
     * This helper method returns a set of child nodes by name from a given parent
     * node.
     * 
     * @param parent
     * @param nodeName
     * @return - list of nodes. If no nodes were found, the method returns an empty
     *         list
     */
    public static List<Element> findChildNodesByName(Element parent, String nodeName) {
        List<Element> result = new ArrayList<Element>();
        if (parent != null && nodeName != null) {
            NodeList childs = parent.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                Node childNode = childs.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE && nodeName.equals(childNode.getNodeName())) {
                    result.add((Element) childNode);
                }
            }
        }
        return result;
    }

    /**
     * Writes the current instance to the file system.
     * 
     * @param file
     */
    public void save(String file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            if (doc == null) {
                logger.severe("...unable to save file - doc is null!");
            }

            writeXml(doc, output);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void save(URI targetURI) {
        save(Paths.get(targetURI).toString());
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
        return encoder.encodeToString(buffer);

    }

    /**
     * Generates a random short 6 byte id with a prafix
     * <p>
     * HEX: 0-9, a-f. For example: BPMNShape_a55ac5, BPMNShape_1382c1
     * 
     * @return
     */
    public static String generateShortID(String prefix) {
        byte[] buffer = new byte[4];
        random.nextBytes(buffer);
        return prefix + "_" + encoder.encodeToString(buffer);

    }

    /**
     * Helper method to write the dom document to an output stream
     * <p>
     * We also call the helper method 'clearBlankLines' to get rid of unnecessary
     * white space. See also discussion here
     * https://stackoverflow.com/questions/12669686/how-to-remove-extra-empty-lines-from-xml-file/12670194#12670194
     * 
     * @param doc
     * @param output
     * @throws TransformerException
     */
    private void writeXml(Document doc, OutputStream output) throws TransformerException {
        // clenup blank lines
        clearBlankLines();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    /**
     * This helper method use XPath to find all whitespace-only TEXT nodes in the
     * current doucment, iterate through them and remove each one from its parent
     * (using getParentNode().removeChild()). Something like this would do (doc
     * would be your DOM document object):
     * 
     * See:
     * https://stackoverflow.com/questions/12669686/how-to-remove-extra-empty-lines-from-xml-file/12670194#12670194
     * 
     * @param element
     */
    private void clearBlankLines() {
        try {
            XPath xp = XPathFactory.newInstance().newXPath();
            NodeList nl;
            nl = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);
            for (int i = 0; i < nl.getLength(); ++i) {
                Node node = nl.item(i);
                node.getParentNode().removeChild(node);
            }
        } catch (XPathExpressionException e) {
            logger.warning("Failed to clean blank up lines during save: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the node is an activity node.
     * 
     * @param node
     * @return
     */
    public static boolean isActivity(Node node) {
        return (BPMN_ACTIVITIES.contains(node.getLocalName()));
    }

    public static boolean isActivity(BPMNBaseElement element) {
        return isActivity(element.getElementNode());
    }

    /**
     * Returns true if the node is an participant node.
     * 
     * @param node
     * @return
     */
    public static boolean isParticipant(Node node) {
        return (PARTICIPANT.equals(node.getLocalName()));
    }

    /**
     * Returns true if the node is a gateway node.
     * 
     * @param node
     * @return
     */
    public static boolean isGateway(Node node) {
        return (BPMN_GATEWAYS.contains(node.getLocalName()));
    }

    public static boolean isGateway(BPMNBaseElement element) {
        return isGateway(element.getElementNode());
    }

    /**
     * Returns true if the node is an event node.
     * 
     * @param node
     * @return
     */
    public static boolean isEvent(Node node) {
        return (BPMN_EVENTS.contains(node.getLocalName()));
    }

    public static boolean isEvent(BPMNBaseElement element) {
        return isEvent(element.getElementNode());
    }

    /**
     * Returns true if the node is a sequenceFlow.
     * 
     * @param node
     * @return
     */
    public static boolean isSequenceFlow(Node node) {
        return (BPMN_SQUENCEFLOWS.contains(node.getLocalName()));
    }

    /**
     * This method finds a bpmndi:BPMNPlane within a bpmn2:definitions. The Id is a
     * reference to a bpmn2:process. In some cases bpmn2:definitions contains only
     * one bpmndi:BPMNPlane without a reference to a process (default plane) in this
     * case, if no matching plane with the given id was found the method returns the
     * default plane.
     * 
     * <pre>
     * {@code
     * <bpmn2:definitions >
     *   <bpmn2:process id="Process_1" name="Process 1" isExecutable="false"> ...
     *   <bpmndi:BPMNDiagram id="BPMNDiagram_1">
     *      <bpmndi:BPMNPlane id="BPMNPlane_1">
     *      
     * }</pre
     * 
     * @param bpmnDefinitions
     * @param nodeName
     * @param id
     * @return
     */
    public static Node findBpmnPlane(Node bpmnDefinitions, String id) {
        if (id == null || id.isEmpty() || bpmnDefinitions == null) {
            return null;
        }

        String diagramID = "";
        if (bpmnDefinitions.getAttributes() != null && bpmnDefinitions.getAttributes().getNamedItem("id") != null) {
            diagramID = bpmnDefinitions.getAttributes().getNamedItem("id").getNodeValue();
        }

        String name = BPMNNS.BPMNDI.prefix + ":BPMNPlane";
        Node defaultBpmnPlane = null;
        NodeList childList = bpmnDefinitions.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);

            // check the attribute bpmnElement
            if (name.equals(child.getNodeName()) && child.hasAttributes()) {
                // get attributes names and values
                NamedNodeMap attributesMap = child.getAttributes();
                Node bpmnElement = attributesMap.getNamedItem("bpmnElement");
                if (bpmnElement != null) {
                    if (id.equals(bpmnElement.getNodeValue())) {
                        // found by id
                        return child;
                    }
                } else {
                    // set default plane
                    defaultBpmnPlane = child;
                }

            }

        }
        // return defaultPlane or null if no plane was found!
        if (defaultBpmnPlane == null) {
            logger.warning("No BPMNPlane for " + id + " found in " + diagramID);
            return null;
        }
        // Fallback to default bpmnPlane....
        String bpmnPlaneID = "";
        if (defaultBpmnPlane.getAttributes() != null && defaultBpmnPlane.getAttributes().getNamedItem("id") != null) {
            bpmnPlaneID = defaultBpmnPlane.getAttributes().getNamedItem("id").getNodeValue();
        }
        logger.warning(
                "No BPMNPlane for " + id + " found in " + diagramID + " - fallback to default plane " + bpmnPlaneID);
        return defaultBpmnPlane;
    }

    /**
     * This helper method returns a BPMNDI node for the given bpmnPlane. A BPMNDI
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
     * @param id        - the id referign to the main BPMN element
     */
    public static Node findBPMNPlaneElement(Node bpmnPlane, String nodeName, String id) {
        if (id == null || id.isEmpty() || bpmnPlane == null || nodeName == null) {
            return null;
        }
        String fullNodeName = BPMNNS.BPMNDI.prefix + ":" + nodeName;
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
     * Returns the central loger instance
     * 
     * @return
     */
    public static Logger getLogger() {
        return logger;
    }

}
