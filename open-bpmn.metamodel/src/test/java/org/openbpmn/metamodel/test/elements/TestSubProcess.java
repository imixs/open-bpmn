package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing Sub Process - See reference model A.4.0
 * 
 * @author rsoika
 *
 */
public class TestSubProcess {

    private static Logger logger = Logger.getLogger(TestSubProcess.class.getName());

    /**
     * This test demonstrate the handling of Sub Processes.
     * 
     * This test parses a bpmn file with a sub process. We init the sub process and
     * test the elements.
     * 
     */
    @Test
    public void testReadMessage() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/reference/A.4.0.bpmn");

            logger.info("...read model");

            // test processes
            assertEquals(2, model.getProcesses().size());
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
            assertEquals(BPMNTypes.SUB_PROCESS, task.getType());

            // Test embedded process
            BPMNProcess subProcess = new BPMNProcess(model, task.getElementNode(), null);
            subProcess.init();
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
