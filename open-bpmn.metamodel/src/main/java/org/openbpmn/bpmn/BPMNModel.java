package org.openbpmn.bpmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

    private Node bpmnDiagram;
    
    public BPMNModel(Document doc) {
        this.doc = doc;

        definitions = doc.getDocumentElement();
        // find  bpmndi:BPMNDiagram
        NodeList diagramList = doc.getElementsByTagName("bpmndi:BPMNDiagram");
        if (diagramList!=null && diagramList.getLength()>0) {
            bpmnDiagram=diagramList.item(0);
        }
    }

    public Element getDefinitions() {
        return definitions;
    }

    public Document getDoc() {
        return doc;
    }

    public Node getBpmnDiagram() {
        return bpmnDiagram;
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

            org.openbpmn.bpmn.elements.Process process = new org.openbpmn.bpmn.elements.Process(item);

            // find the bpmndi:BPMNPlane
            process.setBpmnPlane(findChildNodeByName(bpmnDiagram,"bpmndi:BPMNPlane",process.getId()));

            // now find all activities
            NodeList childs = item.getChildNodes();
            for (int j = 0; j < childs.getLength(); j++) {
                Node child = childs.item(j);

                // Node shape = getShape(id);
                // if (shape!=null) {
                // logger.info("wir haben ein Shape element für " + id);
                // }
                if (isActivity(child)) {
                    Activity activity = new Activity(child.getNodeName(), child);
                    activity.setBpmnShape(findChildNodeByName(process.getBpmnPlane(),"bpmndi:BPMNShape", activity.getId()));
                    process.getActivities().add(activity);
                } else if (isEvent(child)) {
                    Event event = new Event(child.getNodeName(), child);
                    event.setBpmnShape(findChildNodeByName(process.getBpmnPlane(),"bpmndi:BPMNShape",event.getId()));
                    process.getEvents().add(event);
                } else if (isGateway(child)) {
                    Gateway gateway = new Gateway(child.getNodeName(), child);
                    gateway.setBpmnShape(findChildNodeByName(process.getBpmnPlane(),"bpmndi:BPMNShape", gateway.getId()));
                    process.getGateways().add(gateway);
                } else if (isSequenceFlow(child)) {
                    SequenceFlow sequenceFlow = new SequenceFlow(child.getNodeName(), child);
                    sequenceFlow.setBpmnShape(findChildNodeByName(process.getBpmnPlane(),"bpmndi:BPMNShape", sequenceFlow.getId()));
                    process.getSequenceFlows().add(sequenceFlow);
                } else {
                    // unsupported node type
                }
            }

            result.add(process);
        }

        return result;
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
     * This helper method returns a BPMN node child node for the given parent element with the corresponding name and id
     *
     * @param parent - parent Element
     * @param nodeName - name of the child element 
     * @param id of the child element
     */
    private Node findChildNodeByName(Node parent,String nodeName, String id) {
        if (id == null || id.isEmpty() || parent==null  || nodeName==null) {
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
