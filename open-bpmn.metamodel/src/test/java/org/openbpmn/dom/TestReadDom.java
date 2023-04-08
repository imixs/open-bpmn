package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This test class reads a BPMN Model instance and prints the node elements
 * 
 * @author rsoika
 *
 */
public class TestReadDom {

    private static Logger logger = Logger.getLogger(TestReadDom.class.getName());

    /**
     * This test parses a bpmn file
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testReadEmptyModel() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_1.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        System.out.println("------");
        if (model.getDoc().hasChildNodes()) {
            printNote(model.getDoc().getChildNodes());
        }

        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));

        logger.info("...model read sucessful");
    }

    /**
     * This test parses a bpmn file
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testProcessExample01() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_example-01.bpmn");
        String out = "src/test/resources/output/process-example-9.bpmn";
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        System.out.println("------");
        if (model.getDoc().hasChildNodes()) {
            printNote(model.getDoc().getChildNodes());
        }

        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));

        BPMNProcess process = model.openDefaultProces();
        assertNotNull(process);
        assertEquals(1, process.getEvents().size()); // we expect 1 start event

        logger.info("...model read sucessful");

        // Now if we add a new task element to this model the xml namespace should be
        // 'bpmn:task' and not 'bpmn2:task'
        model.openDefaultProces().addTask("task-2", "Task 2", BPMNTypes.TASK);

        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

    /**
     * This Test verifies if a BPMN model with custom namespace URIs can be read.
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testReadEmptyModelWithCustomNamespace() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_1_custom_namespace.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        // next validate the custom BPMN Namespace for bpmn2
        assertEquals("http://www.omg.org/spec/BPMN/20100000/MODEL", model.getUri(BPMNNS.BPMN2));

        // now we read once again a model with the default namespace
        // we expect that the default namespace is set again!
        model = BPMNModelFactory.read("/process_1.bpmn");
        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));

        logger.info("...model read sucessful");

    }

    /**
     * This test parses a blank (empty) bpmn file and creaes a default process
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testReadBlankFile() throws BPMNModelException {
        logger.info("...read blank model");

        String out = "src/test/resources/out_blank.bpmn";

        BPMNModel model = BPMNModelFactory.read("/blank.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());

        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));
        assertNotNull(model);
        BPMNProcess defaultProcess = model.openDefaultProces();
        assertNotNull(defaultProcess);
        assertEquals("process_1", defaultProcess.getId());

        model.save(out);
        logger.info("...blank model created sucessful: " + out);
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
