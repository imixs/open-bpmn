package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;

/**
 * This test class is testing Sub Process - See reference model A.4.0
 * 
 * @author rsoika
 *
 */
public class TestSubProcess {

    private static Logger logger = Logger.getLogger(TestSubProcess.class.getName());

    /**
     * This test tests sub-process-1.bpmn which represents a simple model. We test
     * only the meta model structure after loading the model.
     * 
     */
    @Test
    public void testReadSimpleSubProcess1() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/sub-process-1.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(1, model.getBpmnProcesses().size());
            // load subprocess
            BPMNProcess defaultProcess = model.getDefaultProcess();
            assertNotNull(defaultProcess);
            // we expect that the process not not yet initialized
            assertFalse(defaultProcess.isInitialized());
            // open default process
            defaultProcess = model.openDefaultProcess();
            assertEquals(0, defaultProcess.getEvents().size());
            assertEquals(1, defaultProcess.getActivities().size());

            // open the subProcess
            Activity subProcessActivity = (Activity) defaultProcess.findElementNodeById("Activity_1uvplki");
            assertNotNull(subProcessActivity);
            // open the bpmn process from the subProcess
            BPMNProcess subProcess = subProcessActivity.openSubProcess();
            assertNotNull(subProcess);
            // we expect 2 event in this subProcess
            assertEquals(2, subProcess.getEvents().size());
            assertEquals(0, subProcess.getActivities().size());

            // Test consistence of the BPMN file.
            // We expect one BPMNPlane
            Element diagramNode = model.getBpmnDiagram();
            Set<Element> planeList = model.findChildNodesByName(diagramNode, BPMNNS.BPMNDI, "BPMNPlane");
            assertNotNull(planeList);
            assertEquals(1, planeList.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

    /**
     * This test tests sub-process-1.bpmn which represents a simple model. We test
     * only the meta model structure after loading the model.
     * 
     */
    @Test
    public void testReadSimpleSubProcess2() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/sub-process-2.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(1, model.getBpmnProcesses().size());
            // load subprocess
            BPMNProcess defaultProcess = model.getDefaultProcess();
            assertNotNull(defaultProcess);
            // we expect that the process not not yet initialized
            assertFalse(defaultProcess.isInitialized());
            // open default process
            defaultProcess = model.openDefaultProcess();
            assertEquals(0, defaultProcess.getEvents().size());
            assertEquals(1, defaultProcess.getActivities().size());

            // open the subProcess
            Activity subProcessActivity = (Activity) defaultProcess.findElementNodeById("Activity_1uvplki");
            assertNotNull(subProcessActivity);
            // open the bpmn process from the subProcess
            BPMNProcess subProcess = subProcessActivity.openSubProcess();
            assertNotNull(subProcess);
            // we expect 2 event in this subProcess
            assertEquals(2, subProcess.getEvents().size());
            assertEquals(0, subProcess.getActivities().size());

            // Test consistence of the BPMN file.
            // We expect one BPMNPlane
            Element diagramNode = model.getBpmnDiagram();
            Set<Element> planeList = model.findChildNodesByName(diagramNode, BPMNNS.BPMNDI, "BPMNPlane");
            assertNotNull(planeList);
            assertEquals(1, planeList.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

    /**
     * This test demonstrate the handling of Sub Processes based on reference model
     * A.4.0
     * 
     * This test parses a bpmn file with a sub process. We init the sub process and
     * test the elements.
     * 
     */
    @Test
    public void testReadRefModelA40() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/reference/A.4.0.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(2, model.getBpmnProcesses().size());
            BPMNProcess process = model.openProcess("WFP-6-2");
            assertNotNull(process);
            assertEquals(4, process.getActivities().size());
            // find start events
            Set<? extends BPMNElementNode> startEvents = process
                    .findElementNodes(n -> (BPMNTypes.START_EVENT.equals(n.getType())));
            assertEquals(1, startEvents.size());

            // open sub process
            BPMNElement element = process.findElementById("_ee35fa2c-dfea-40cf-a469-845b765a7b50");
            assertNotNull(element);
            assertTrue(element instanceof Activity);

            Activity task = (Activity) element;

            // Test embedded process
            assertEquals(BPMNTypes.SUB_PROCESS, task.getType());
            assertTrue(task.hasSubProcess());
            BPMNProcess subProcess = task.openSubProcess();
            startEvents = subProcess
                    .findElementNodes(n -> (BPMNTypes.START_EVENT.equals(n.getType())));
            assertEquals(1, startEvents.size());
            Event startEvent = (Event) startEvents.iterator().next();
            assertEquals("_1ffaa550-3225-4c6a-a391-3aaf224723af", startEvent.getId());
            // test if we find task 4
            Activity task4 = (Activity) subProcess.findElementById("_09532ad3-e571-4214-b580-7bebf4bb68b1");
            assertNotNull(task4);
            assertEquals("Task 4", task4.getName());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

}
