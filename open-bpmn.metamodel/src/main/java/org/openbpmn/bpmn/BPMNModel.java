package org.openbpmn.bpmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.SequenceFlow;
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

    List<String> BPMN_ACTIVITIES = Arrays.asList(new String[] { "bpmn2:task", "bpmn2:serviceTask", "bpmn2:sendTask",
            "bpmn2:receiveTask", "bpmn2:userTask", "bpmn2:manualTask", "bpmn2:businessRuleTask", "bpmn2:scriptTask",
            "bpmn2:subProcess", "bpmn2:adHocSubProcess", "bpmn2:transaction", "bpmn2:callActivity" });

    List<String> BPMN_EVENTS = Arrays
            .asList(new String[] { "bpmn2:startEvent", "bpmn2:endEvent", "bpmn2:catchEvent", "bpmn2:throwEvent" });

    List<String> BPMN_GATEWAYS = Arrays.asList(new String[] { "bpmn2:exclusiveGateway", "bpmn2:inclusiveGateway",
            "bpmn2:parallelGateway", "bpmn2:complexGateway" });

    List<String> BPMN_SQUENCEFLOWS = Arrays.asList(new String[] { "bpmn2:sequenceFlow" });

    private Element definitions;
    private Document doc;

    public BPMNModel(Document doc) {
        this.doc = doc;

        definitions = doc.getDocumentElement();
    }

    public Element getDefinitions() {
        return definitions;
    }

    public Document getDoc() {
        return doc;
    }

    public BPMNModel addProcess(String id) {
        Element process = doc.createElement("bpmn2:process");
        process.setAttribute("id", id);
        definitions.appendChild(process);

        return this;
    }

    /**
     * Writes the current instance to the file system.
     * 
     * @param file
     */
    public void save(String file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            writeXml(doc, output);
        } catch (TransformerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(source, result);

    }

    /**
     * This method returns a list of all process elements
     * <p>
     * bpmn2:process
     */
    public List<org.openbpmn.bpmn.elements.Process> getProcesList() {

        List<org.openbpmn.bpmn.elements.Process> result = new ArrayList<org.openbpmn.bpmn.elements.Process>();
        // find process
        NodeList processList = definitions.getElementsByTagName("bpmn2:process");
        logger.info("..found " + processList.getLength() + " processes");

        for (int i = 0; i < processList.getLength(); i++) {
            Node item = processList.item(i);
            org.openbpmn.bpmn.elements.Process process = new org.openbpmn.bpmn.elements.Process(readAttributes(item));

            // now find all activities
            NodeList childs = item.getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);

                Map<String, String> attributes = readAttributes(child);
                String id=attributes.get("id");
                
//                Node shape = getShape(id);
//                if (shape!=null) {
//                    logger.info("wir haben ein Shape element für " + id);
//                }
                if (isActivity(child)) {
                    Activity activity = new Activity(child.getNodeName(),attributes );
                    process.getActivities().add(activity);
                } else if (isEvent(child)) {
                    Event event = new Event(child.getNodeName(),attributes);
                    process.getEvents().add(event);
                } else if (isGateway(child)) {
                    Gateway gateway = new Gateway(child.getNodeName(), attributes);
                    process.getGateways().add(gateway);
                } else if (isSequenceFlow(child)) {
                    SequenceFlow sequenceFlow = new SequenceFlow(child.getNodeName(),attributes);
                    process.getSequenceFlows().add(sequenceFlow);
                } else {
                    // unsupported node type
                }
            }

            result.add(process);
        }

        return result;
    }

    /**
     * Returns true if the node is an activity node.
     * 
     * @param node
     * @return
     */
    private boolean isActivity(Node node) {
        String type = node.getNodeName();
        return (BPMN_ACTIVITIES.contains(type));
    }

    /**
     * Returns true if the node is a gateway node.
     * 
     * @param node
     * @return
     */
    private boolean isGateway(Node node) {
        String type = node.getNodeName();
        return (BPMN_GATEWAYS.contains(type));
    }

    /**
     * Returns true if the node is an event node.
     * 
     * @param node
     * @return
     */
    private boolean isEvent(Node node) {
        String type = node.getNodeName();
        return (BPMN_EVENTS.contains(type));
    }

    /**
     * Returns true if the node is a sequenceFlow.
     * 
     * @param node
     * @return
     */
    private boolean isSequenceFlow(Node node) {
        String type = node.getNodeName();
        return (BPMN_SQUENCEFLOWS.contains(type));
    }

    /**
     * Helper method reads the attributes of a node.
     * 
     * @param node
     * @return
     */
    private Map<String, String> readAttributes(Node sourceNode) {
        Map<String, String> result = new HashMap<String, String>();
        if (sourceNode.hasAttributes()) {
            // get attributes names and values
            NamedNodeMap nodeMap = sourceNode.getAttributes();
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node node = nodeMap.item(i);
                result.put(node.getNodeName(), node.getNodeValue());
            }
        }
        return result;
    }

    /**
     * This method findes BPMNShape for a given element id
     * <p>
     */
    private Node getShape(String elementId) {

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
         XPathExpression expr;
        try {
            expr = xpath.compile("//bpmndi\\:BPMNShape[@bpmnElement=\"" +elementId + "\"]");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            
            if (nl!=null && nl.getLength()==1) {
                return nl.item(0);
            }
        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
