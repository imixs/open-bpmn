package org.openbpmn.metamodel.test.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This test class is testing the BPMNElement behavior.
 * 
 * @author rsoika
 *
 */
public class TestBPMNElement {
    BPMNModel model;

    /**
     * Test the default bpmn namespaces
     */
    @Test
    public void testContent() {

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        assertNotNull(model);

        try {
            BPMNProcess process = model.openDefaultProces();
            assertNotNull(process);
            Activity task = process.addTask("task_1", "Task-1", null);

            assertNotNull(task.getElementNode());

            task.setDocumentation("some text");

            // we expect a CDATA element
            // test structure....

            NodeList childs = task.getElementNode().getChildNodes();
            assertNotNull(childs);
            assertEquals(1, childs.getLength());
            Node childNode = childs.item(0);
            assertTrue(childNode.getNodeType() == Node.ELEMENT_NODE);
            assertEquals("bpmn2:documentation", childNode.getNodeName());

            // delete content
            task.setChildNodeContent(BPMNNS.BPMN2, "documentation", "", true);

            childs = task.getElementNode().getChildNodes();
            assertNotNull(childs);
            assertEquals(1, childs.getLength());
            childNode = childs.item(0);
            assertTrue(childNode.getNodeType() == Node.ELEMENT_NODE);
            assertEquals("bpmn2:documentation", childNode.getNodeName());
            assertEquals("", task.getChildNodeContent(BPMNNS.BPMN2, "documentation"));

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

}
