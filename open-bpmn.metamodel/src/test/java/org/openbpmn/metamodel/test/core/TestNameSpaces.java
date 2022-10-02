package org.openbpmn.metamodel.test.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNModelException;

/**
 * This test class is testing the namespaces
 * 
 * @author rsoika
 *
 */
public class TestNameSpaces {

    /**
     * Test the default bpmn namespaces
     */
    @Test
    public void testDefaultNamespaces() {

        BPMNModel model;
        try {
            model = new BPMNModel(null);

            assertEquals("bpmn2", BPMNNS.BPMN2.toString().toLowerCase());
            assertEquals("bpmndi", BPMNNS.BPMNDI.toString().toLowerCase());

            assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getNameSpaceUri(BPMNNS.BPMN2));
        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

    /**
     * test customizing the namespace uri
     */
    @Test
    public void testCustomNamespaces() {
        try {
            BPMNModel model1 = new BPMNModel(null);
            BPMNModel model2 = new BPMNModel(null);
            assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model1.getNameSpaceUri(BPMNNS.BPMN2));

            // set a custom uri for bpmn2
            model1.setNameSpaceUri(BPMNNS.BPMN2, "http://www.omg.org/spec/BPMN/20100000/MODEL");
            assertEquals("http://www.omg.org/spec/BPMN/20100000/MODEL", model1.getNameSpaceUri(BPMNNS.BPMN2));

            // model2 still holds the default uri
            assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model2.getNameSpaceUri(BPMNNS.BPMN2));
        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

}
