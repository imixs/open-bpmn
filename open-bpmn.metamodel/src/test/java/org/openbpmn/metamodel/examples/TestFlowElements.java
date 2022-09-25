package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
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
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-1.bpmn");
            System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
            System.out.println("------");
            if (model.getDoc().hasChildNodes()) {
                printNote(model.getDoc().getChildNodes());
            }

            // read tasks....
            BPMNProcess process = model.openProcess(null);

            assertNotNull(process);
            logger.info("...Process ID=" + process.getAttribute("id"));

            Set<BPMNActivity> activities = process.getActivities();
            for (BPMNActivity element : activities) {
                logger.info("....... Activity type=" + element.getType() + " id=" + element.getAttribute("id"));
                // bounds
                logger.info("....... Activity Bounds=" + element.getBounds());

            }

            Set<BPMNGateway> gateways = process.getGateways();
            for (BPMNGateway element : gateways) {
                logger.info("....... Gateway type=" + element.getType() + " id=" + element.getAttribute("id"));
            }

            Set<BPMNEvent> events = process.getEvents();
            for (BPMNEvent element : events) {
                logger.info("....... Event type=" + element.getType() + " id=" + element.getAttribute("id"));
            }

            // we expect 3 sequnceFlows
            assertEquals(3, process.getSequenceFlows().size());
            BPMNSequenceFlow sequenceFlow = process.getSequenceFlowByID("SequenceFlow_1");
            assertEquals("StartEvent_1", sequenceFlow.getSourceRef());
            assertEquals("Task_1", sequenceFlow.getTargetRef());

            // test waypoints
            List<BPMNPoint> wayPoints = sequenceFlow.getWayPoints();
            assertNotNull(wayPoints);
            BPMNPoint wayPoint = wayPoints.get(0);
            assertEquals(118.0, wayPoint.getX());
            assertEquals(136.0, wayPoint.getY());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
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
