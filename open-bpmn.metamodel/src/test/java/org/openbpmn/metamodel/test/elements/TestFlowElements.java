package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
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

            Set<Activity> activities = process.getActivities();
            for (Activity element : activities) {
                logger.info("....... Activity type=" + element.getType() + " id=" + element.getAttribute("id"));
                // bounds
                logger.info("....... Activity Bounds=" + element.getBounds());

            }

            Set<Gateway> gateways = process.getGateways();
            for (Gateway element : gateways) {
                logger.info("....... Gateway type=" + element.getType() + " id=" + element.getAttribute("id"));
            }

            Set<Event> events = process.getEvents();
            for (Event element : events) {
                logger.info("....... Event type=" + element.getType() + " id=" + element.getAttribute("id"));
            }

            // we expect 3 sequnceFlows
            assertEquals(3, process.getSequenceFlows().size());
            SequenceFlow sequenceFlow = (SequenceFlow) process.findElementEdgeById("SequenceFlow_1");
            assertEquals("StartEvent_1", sequenceFlow.getSourceRef());
            assertEquals("Task_1", sequenceFlow.getTargetRef());

            // test waypoints
            Set<BPMNPoint> wayPoints = sequenceFlow.getWayPoints();
            assertNotNull(wayPoints);
            BPMNPoint wayPoint = wayPoints.iterator().next();
            assertEquals(118.0, wayPoint.getX());
            assertEquals(136.0, wayPoint.getY());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful");
    }

    /**
     * This test parses refmodel-6
     * 
     * Test Association Flows
     */
    @Test
    public void testReadRefmodel6() {

        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-6.bpmn");
            BPMNProcess process = model.openDefaultProces();
            assertNotNull(process);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, process.getProcessType());

            // we expect 2 data objects
            assertEquals(2, process.getDataObjects().size());
            // and one association between the two data objects
            assertEquals(1, process.getAssociations().size());
            assertEquals(0, process.getSequenceFlows().size());

            // Analyze the structure of the TextAnnotation
            Set<TextAnnotation> annotations = process.getTextAnnotations();
            assertEquals(1, annotations.size());
            TextAnnotation textAnnotation = annotations.iterator().next();
            assertEquals("Text Annotation Example\n"
                    + "2nd LIne\n<br />", textAnnotation.getText());
            assertEquals("text/html", textAnnotation.getAttribute("textFormat"));

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful");
    }

    /**
     * This test parses refmodel-7
     */
    @Test
    public void testReadRefmodel7() {

        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-7.bpmn");
            System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
            System.out.println("------");
            if (model.getDoc().hasChildNodes()) {
                printNote(model.getDoc().getChildNodes());
            }

            BPMNProcess process = model.openProcess(null);

            assertNotNull(process);
            logger.info("...Process ID=" + process.getAttribute("id"));

            assertEquals(2, process.getActivities().size());
            assertEquals(5, process.getSequenceFlows().size());

            // read gateways....
            Set<Gateway> gateways = process.getGateways();
            assertEquals(1, gateways.size());
            for (Gateway element : gateways) {
                logger.info("....... Gateway type=" + element.getType() + " id=" + element.getAttribute("id"));
                // bounds
                logger.info("....... Gateway Bounds=" + element.getBounds());

            }

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful");
    }

    /**
     * This test parses refmodel-8 - Boundary Event
     */
    @Test
    public void testReadRefmodel8() {

        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-8.bpmn");
            System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
            System.out.println("------");
            if (model.getDoc().hasChildNodes()) {
                printNote(model.getDoc().getChildNodes());
            }

            BPMNProcess process = model.openProcess(null);

            assertNotNull(process);
            logger.info("...Process ID=" + process.getAttribute("id"));

            assertEquals(1, process.getActivities().size());
            assertEquals(1, process.getEvents().size());

            // Load Boundary event and test attribute attachedToRef
            Event boundaryEvent = (Event) process.findElementNodeById("BoundaryEvent_1");
            assertNotNull(boundaryEvent);
            String attachedRef = boundaryEvent.getAttribute("attachedToRef");
            assertEquals("Task_1", attachedRef);

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
