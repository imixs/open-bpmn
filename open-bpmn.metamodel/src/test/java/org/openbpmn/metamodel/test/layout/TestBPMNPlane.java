package org.openbpmn.metamodel.test.layout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;

/**
 * This test class is testing BPMNPlane element
 * 
 * @author rsoika
 *
 */
public class TestBPMNPlane {

    private static Logger logger = Logger.getLogger(TestBPMNPlane.class.getName());

    /**
     * This test tests refmodel-1 and the BPMNPlane associated with the default
     * process
     * 
     */
    @Test
    public void testReadSimpleProcess() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-1.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(1, model.getBpmnProcesses().size());
            // load subprocess
            BPMNProcess defaultProcess = model.getDefaultProcess();
            assertNotNull(defaultProcess);

            // Test consistence of the BPMN file.
            // We expect one BPMNPlane
            Element diagramNode = model.getBpmnDiagram();
            Set<Element> planeList = model.findChildNodesByName(diagramNode, BPMNNS.BPMNDI, "BPMNPlane");
            assertNotNull(planeList);
            assertEquals(1, planeList.size());

            Element bpmnPlane = planeList.iterator().next();
            // bpmnElement should be 'process_1'
            assertEquals("process_1", bpmnPlane.getAttribute("bpmnElement"));

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

    /**
     * This test tests refmodel-1_missing_element ref
     * 
     * See Issue #427
     * 
     * In this model file a BPMNPlane exists but does not reifere to a process. Thi
     * sis detected by the method resolveBPMNPlane.
     * 
     * What we expect that the BPMNPlane with not reference defaults to the first
     * process.
     * 
     */
    @Test
    public void testReadSimpleProcess_Issue427() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-1_missingelement.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(1, model.getBpmnProcesses().size());
            // load subprocess
            BPMNProcess defaultProcess = model.getDefaultProcess();
            assertNotNull(defaultProcess);

            // Test consistence of the BPMN file.
            // We expect one BPMNPlane
            Element diagramNode = model.getBpmnDiagram();
            Set<Element> planeList = model.findChildNodesByName(diagramNode, BPMNNS.BPMNDI, "BPMNPlane");
            assertNotNull(planeList);
            assertEquals(1, planeList.size());

            Element bpmnPlane = planeList.iterator().next();
            // bpmnElement should be 'process_1'
            assertEquals("process_1", bpmnPlane.getAttribute("bpmnElement"));

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }
}
