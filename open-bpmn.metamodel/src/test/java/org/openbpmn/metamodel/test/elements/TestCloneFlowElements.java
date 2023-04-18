package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This test class reads a BPMN Model instance and copies (clones) elements.
 * This is happening in the modeller during copy/paste actions.
 * 
 * @author rsoika
 *
 */
public class TestCloneFlowElements {

    private static Logger logger = Logger.getLogger(TestCloneFlowElements.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadEmptyModel() {

        logger.info("...read model");
        try {
            String out = "src/test/resources/output/clone-refmodel-1.bpmn";
            BPMNModel model = BPMNModelFactory.read("/refmodel-1.bpmn");

            // copy Task_1
            // The expectation here is that the incoming and outcoming sequence flows will
            // be removed from the copy and the documentation element gets a new id

            BPMNProcess process = model.openProcess(null);

            assertNotNull(process);

            BPMNElementNode originTask = process.findElementNodeById("Task_1");

            // clone....
            BPMNElementNode clonedTask = process.cloneBPMNElementNode(originTask);

            assertNotNull(clonedTask);
            clonedTask.setName("Copy of Task 1");

            // The id from the documentation must be changed!
            NodeList originDocumentationElements = originTask.getElementNode()
                    .getElementsByTagName("bpmn2:documentation");
            NodeList clonedDocumentationElements = clonedTask.getElementNode()
                    .getElementsByTagName("bpmn2:documentation");
            assertNotNull(clonedDocumentationElements);
            assertEquals(1, clonedDocumentationElements.getLength());
            // compare IDs
            Element originDocNode = (Element) originDocumentationElements.item(0);
            Element clonedDocNode = (Element) clonedDocumentationElements.item(0);
            assertNotEquals(originDocNode.getAttribute("id"), clonedDocNode.getAttribute("id"));

            // next we expect no outgoing and incoming sequenceFlows...

            NodeList incomingSequenceFlows = clonedTask.getElementNode()
                    .getElementsByTagName("bpmn2:incoming");
            NodeList outgoingSequenceFlows = clonedTask.getElementNode()
                    .getElementsByTagName("bpmn2:outgoing");

            assertEquals(0, incomingSequenceFlows.getLength());
            assertEquals(0, outgoingSequenceFlows.getLength());

            assertEquals(0, clonedTask.getIngoingSequenceFlows().size());
            assertEquals(0, clonedTask.getOutgoingSequenceFlows().size());

            // finally write the output...
            // we expect that this new model can be opened with other modellers like
            // Eclipse-BPMN2
            model.save(out);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...task cloned successful");
    }

}
