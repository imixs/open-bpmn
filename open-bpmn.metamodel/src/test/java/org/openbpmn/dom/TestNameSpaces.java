package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class reads a BPMN Models with different namespace declarations
 * 
 * @author rsoika
 *
 */
public class TestNameSpaces {

    private static Logger logger = Logger.getLogger(TestNameSpaces.class.getName());

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

        logger.info("...model read successful");
    }

    /**
     * This Test verifies if a BPMN model with custom namespace URIs can be read if
     * no bpmn2: exists
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testReadModelWithoutBPMN2Prefix() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_1_custom_namespace-nopaefix.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        // next validate the default namespace mapped to bpmn2
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));

        // add a new element without a prefix....
        logger.info("...model read successful");
    }

    /**
     * This test parses a bpmn file with a default namespace (no prefix) and adds a
     * second task element
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testProcessExample01() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_1_custom_namespace-nopaefix.bpmn");
        String out = "src/test/resources/output/process_1_custom_namespace-nopaefix.bpmn";

        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL", model.getUri(BPMNNS.BPMN2));

        BPMNProcess process = model.openDefaultProces();
        assertNotNull(process);
        assertEquals(2, process.getEvents().size()); // we expect 2 events (start and end event)

        logger.info("...model read successful");

        // Now if we add a new task element to this model the xml namespace should be
        // created without a prefix
        // 'task' and not 'bpmn2:task'
        model.openDefaultProces().addTask("task-example-002", "Example Task 002", BPMNTypes.TASK);

        model.save(out);
        logger.info("...model update successful: " + out);
    }

    /**
     * This test parses a bpmn file with a default namespace (no prefix) and adds a
     * second task element
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testProcessExample02() throws BPMNModelException {

        logger.info("...read model");

        BPMNModel model = BPMNModelFactory.read("/process_1_custom_namespace-2.bpmn");

        BPMNProcess process = model.openDefaultProces();

        assertNotNull(process);

        BPMNElement element = process.findElementById("Gf17b6be0df1a4474ade00c089b0af2a4");

        assertNotNull(element);

        if (element instanceof Gateway) {
            Gateway gateway = (Gateway) element;
            Set<SequenceFlow> sequenceFlowsIn = gateway.getIngoingSequenceFlows();
            assertNotNull(sequenceFlowsIn);
            assertEquals(1, sequenceFlowsIn.size());

            Set<SequenceFlow> sequenceFlowsOut = gateway.getOutgoingSequenceFlows();
            assertNotNull(sequenceFlowsOut);
            assertEquals(2, sequenceFlowsOut.size());

        } else {
            fail();
        }

        logger.info("...model read successful");
    }

}
