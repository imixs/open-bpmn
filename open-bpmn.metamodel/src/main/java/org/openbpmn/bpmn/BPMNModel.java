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
import java.util.List;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
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
    private static Logger logger = Logger.getLogger(BPMNModel.class.getName());

    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public final static String BPMN_NS = "bpmn2";
    public final static String DI_NS = "bpmndi";
    public final static String DC_NS = "dc";
    private BPMNProcess context = null;

    public static List<String> BPMN_ACTIVITIES = Arrays
            .asList(new String[] { "task", "serviceTask", "sendTask", "receiveTask", "userTask", "manualTask",
                    "businessRuleTask", "scriptTask", "subProcess", "adHocSubProcess", "transaction", "callActivity" });

    public static List<String> BPMN_EVENTS = Arrays
            .asList(new String[] { "startEvent", "endEvent", "catchEvent", "throwEvent" });

    public static List<String> BPMN_GATEWAYS = Arrays
            .asList(new String[] { "exclusiveGateway", "inclusiveGateway", "parallelGateway", "complexGateway" });

    public static List<String> BPMN_SQUENCEFLOWS = Arrays.asList(new String[] { "sequenceFlow" });

    private Element definitions;
    private Document doc;

    private Node bpmnDiagram;

    public BPMNModel(Document doc) {
        this.doc = doc;

        definitions = doc.getDocumentElement();
        // find bpmndi:BPMNDiagram
        NodeList diagramList = doc.getElementsByTagName(DI_NS + ":BPMNDiagram");
        if (diagramList != null && diagramList.getLength() > 0) {
            bpmnDiagram = diagramList.item(0);
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
        NodeList processList = definitions.getElementsByTagName(BPMN_NS + ":process");
        logger.info("..found " + processList.getLength() + " processes");

        for (int i = 0; i < processList.getLength(); i++) {
            Node item = processList.item(i);

            context = new BPMNProcess(item);

            if (id != null && !id.equals(context.getId())) {
                // not match of the requested processs ID
                continue;
            }

            // find the bpmndi:BPMNPlane
            context.setBpmnPlane(findChildNodeByName(bpmnDiagram, DI_NS + ":BPMNPlane", context.getId()));

            context.init();

            return context;
        }

        return null;
    }

    /**
     * Helper method to build a SequenceFlow meta object from a bpmn2:sequenceFlow.
     * <p>
     * <bpmn2:sequenceFlow id="flow_4" sourceRef="Task_1" targetRef= "SendTask_1"/>
     * <p>
     * 
     * @param node
     * @param process
     * @return
     */
    public static BPMNSequenceFlow buildSequenceFlow(Node node, org.openbpmn.bpmn.elements.BPMNProcess process) {
        BPMNSequenceFlow sequenceFlow = null;
        if ((BPMN_NS + ":sequenceFlow").equals(node.getNodeName())) {
            sequenceFlow = new BPMNSequenceFlow(node);

            // parse waypoints (di:waypoint)
            Node bpmnEdge = findChildNodeByName(process.getBpmnPlane(), DI_NS + ":BPMNEdge", sequenceFlow.getId());
            List<Node> wayPoints = BPMNModel.findChildNodesByName(bpmnEdge, "di:waypoint");
            for (Node wayPoint : wayPoints) {
                NamedNodeMap wayPointattributeMap = wayPoint.getAttributes();
                BPMNPoint point = new BPMNPoint(wayPointattributeMap.getNamedItem("x").getNodeValue(), //
                        wayPointattributeMap.getNamedItem("y").getNodeValue());
                sequenceFlow.getWayPoints().add(point);
            }
        }
        return sequenceFlow;
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
        Element process = doc.createElement(BPMN_NS + ":process");
        process.setAttribute("id", id);
        // definitions.appendChild(process);
        definitions.insertBefore(process, this.getBpmnDiagram());

        // add bpmndi:BPMNPlane
        // <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="process_2">
        Element bpmnPlane = doc.createElement(BPMNModel.DI_NS + ":BPMNPlane");
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
       // Transformer transformer = transformerFactory.newTransformer();
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
        String type = node.getNodeName();
        // cut namespce
        if (type.startsWith(BPMN_NS + ":")) {
            type = type.substring((BPMN_NS + ":").length());
        }
        return (BPMN_ACTIVITIES.contains(type));
    }

    /**
     * Returns true if the node is a gateway node.
     * 
     * @param node
     * @return
     */
    public static boolean isGateway(Node node) {
        String type = node.getNodeName();
        // cut namespce
        if (type.startsWith(BPMN_NS + ":")) {
            type = type.substring((BPMN_NS + ":").length());
        }
        return (BPMN_GATEWAYS.contains(type));
    }

    /**
     * Returns true if the node is an event node.
     * 
     * @param node
     * @return
     */
    public static boolean isEvent(Node node) {
        String type = node.getNodeName();
        // cut namespce
        if (type.startsWith(BPMN_NS + ":")) {
            type = type.substring((BPMN_NS + ":").length());
        }
        return (BPMN_EVENTS.contains(type));
    }

    /**
     * Returns true if the node is a sequenceFlow.
     * 
     * @param node
     * @return
     */
    public static boolean isSequenceFlow(Node node) {
        String type = node.getNodeName();
        // cut namespce
        if (type.startsWith(BPMN_NS + ":")) {
            type = type.substring((BPMN_NS + ":").length());
        }
        return (BPMN_SQUENCEFLOWS.contains(type));
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
                NamedNodeMap nodeMap = child.getAttributes();
                for (int j = 0; j < nodeMap.getLength(); j++) {
                    Node node = nodeMap.item(j);
                    if ("bpmnElement".equals(node.getNodeName()) && id.equals(node.getNodeValue())) {
                        // found it!
                        return child;
                    }
                }
            }

        }
        // not found!
        return null;
    }

}
