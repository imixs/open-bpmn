package org.openbpmn.bpmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
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
import javax.xml.transform.stream.StreamSource;

import org.openbpmn.bpmn.elements.BPMNProcess;
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
            BPMNTaskType.TASK, //
            BPMNTaskType.MANUAL, //
            BPMNTaskType.SEND, //
            BPMNTaskType.SERVICE, //
            BPMNTaskType.SCRIPT, BPMNTaskType.USER, //
            "receiveTask", "businessRuleTask", "subProcess", "adHocSubProcess", "transaction", "callActivity" });

    public static List<String> BPMN_TASKS = Arrays.asList(new String[] { //
            BPMNTaskType.TASK, //
            BPMNTaskType.MANUAL, //
            BPMNTaskType.SEND, //
            BPMNTaskType.SERVICE, //
            BPMNTaskType.SCRIPT, BPMNTaskType.USER //
    });

    public static List<String> BPMN_EVENTS = Arrays
            .asList(new String[] { "startEvent", "endEvent", "catchEvent", "throwEvent" });

    public static List<String> BPMN_GATEWAYS = Arrays
            .asList(new String[] { "exclusiveGateway", "inclusiveGateway", "parallelGateway", "complexGateway" });

    public static List<String> BPMN_SQUENCEFLOWS = Arrays.asList(new String[] { "sequenceFlow" });

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
        setNameSpaceUri(BPMNNS.DI, "http://www.omg.org/spec/DD/20100524/DC");
        setNameSpaceUri(BPMNNS.DC, "http://www.omg.org/spec/DD/20100524/DI");

    }

    /**
     * This method instantiates a new BPMN model based on a given
     * org.w3c.dom.Document. The method parses the BPMN namespaces. If
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
     * This Method opens a BPMN context (e.g. a Process) with the given ID. The
     * method returns null if no process with the given ID exists.
     * <p>
     * In case no ID is provided (null) the method returns the first (default)
     * process from the model.
     * 
     * @param string
     * @return BPMNProcess instance
     */
    public BPMNProcess openContext(String id) {

        // find process
        NodeList processList = definitions.getElementsByTagName(BPMNNS.BPMN2.prefix + ":process");
        logger.fine("..found " + processList.getLength() + " processes");

        for (int i = 0; i < processList.getLength(); i++) {
            Node item = processList.item(i);
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
     * Adds a new process to the definitions
     * <p>
     * The method also generates the BPMNPlane
     * 
     * @param id
     * @return
     */
    public BPMNModel addProcess(String id) {

        // xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
        Element process = createElement(BPMNNS.BPMN2, "process");

        System.out.println(process.getNodeName());
        System.out.println(process.getLocalName());
        System.out.println(process.getNamespaceURI());

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

        return this;
    }

    /**
     * This helper method returns a set of child nodes by name from a given parent
     * node.
     * 
     * @param parent
     * @param nodeName
     * @return
     */
    public static List<Node> findChildNodesByName(Node parent, String nodeName) {
        List<Node> result = new ArrayList<Node>();
        if (parent != null && nodeName != null) {
            NodeList childs = parent.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                Node childNode = childs.item(i);
                if (nodeName.equals(childNode.getNodeName())) {
                    result.add(childNode);
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
     * 
     * @param doc
     * @param output
     * @throws TransformerException
     */
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        String IDENTITY_XSLT_WITH_INDENT = // workaround to remove newlines
                "<xsl:stylesheet version='1.0' " + //
                        "xmlns:xsl='http://www.w3.org/1999/XSL/Transform' xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\">"
                        + //
                        "<xsl:output indent=\"yes\" cdata-section-elements=\"cdata-other-elements\"/>" + //
                        "<xsl:strip-space elements=\"*\"/>" + //
                        " <xsl:template match=\"@*|node()\">" + //
                        "   <xsl:copy>" + //
                        "      <xsl:apply-templates select=\"@*|node()\"/>" + //
                        "    </xsl:copy>" + //
                        " </xsl:template>" + //
                        "</xsl:stylesheet>";

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
        Transformer transformer = transformerFactory
                .newTransformer(new StreamSource(new StringReader(IDENTITY_XSLT_WITH_INDENT)));
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        // pretty print
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.transform(source, result);
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

    /**
     * Returns true if the node is a gateway node.
     * 
     * @param node
     * @return
     */
    public static boolean isGateway(Node node) {
        return (BPMN_GATEWAYS.contains(node.getLocalName()));
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
     * This helper method returns a BPMN node child node for the given parent
     * element with the corresponding name and id
     *
     * @param parent   - parent Element
     * @param nodeName - name of the child element
     * @param id       of the child element
     */
    public static Node findChildNodeByName(Node parent, String nodeName, String id) {
        if (id == null || id.isEmpty() || parent == null || nodeName == null) {
            return null;
        }
        NodeList childList = parent.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);

            // check the attribute bpmnElement
            if (nodeName.equals(child.getNodeName()) && child.hasAttributes()) {
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
     * Returns the central loger instance
     * 
     * @return
     */
    public static Logger getLogger() {
        return logger;
    }

}
