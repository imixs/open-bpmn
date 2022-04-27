package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This test class reads a BPMN Model instance and analyzes the content.
 * 
 * @author rsoika
 *
 */
public class TestFlowElements {

    private static Logger logger = Logger.getLogger(TestFlowElements.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadEmptyModel() {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/process_2.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        System.out.println("------");
        if (model.getDoc().hasChildNodes()) {
            printNote(model.getDoc().getChildNodes());
        }

        // read tasks....
        List<org.openbpmn.bpmn.elements.Process> processList = model.getProcesList();
        assertEquals(1,processList.size());
        for (org.openbpmn.bpmn.elements.Process process : processList) {
            logger.info("...Process ID=" + process.getAttributes().get("id"));

            Set<Activity> activities = process.getActivities();
            for (Activity element : activities) {
                logger.info("....... Activity type=" + element.getType() + " id=" + element.getAttributes().get("id"));
                // bounds
                logger.info("....... Activity Bounds=" + element.getBounds());
                
            }

            Set<Gateway> gateways = process.getGateways();
            for (Gateway element : gateways) {
                logger.info("....... Gateway type=" + element.getType() + " id=" + element.getAttributes().get("id"));
            }

            Set<Event> events = process.getEvents();
            for (Event element : events) {
                logger.info("....... Event type=" + element.getType() + " id=" + element.getAttributes().get("id"));
            }
            
            // we expect 3 sequnceFlows
            assertEquals(3,process.getSequenceFlows().size());
            
        }

        logger.info("...model read sucessful");
    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }

                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());
                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

}
