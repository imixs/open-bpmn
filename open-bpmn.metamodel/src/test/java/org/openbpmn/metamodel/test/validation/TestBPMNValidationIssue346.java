package org.openbpmn.metamodel.test.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.bpmn.validation.BPMNValidationHandler;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;

/**
 * This test validates a BPMN model with a receiveTask having one meesage flow
 * ingoing and one sequence flow outgoing
 * 
 * Spec:
 * 
 * A Receive Task is often used to start a Process. In a sense, the Process is
 * bootstrapped by the receipt of the
 * Message. In order for the Receive Task to instantiate the Process its
 * instantiate attribute MUST be set to true
 * and it MUST NOT have any incoming Sequence Flow.
 * 
 * @author rsoika
 *
 */
public class TestBPMNValidationIssue346 {
    BPMNModel model;
    private static Logger logger = Logger.getLogger(TestBPMNValidationIssue346.class.getName());

    /**
     * Test simple model with start / end event without a sequence flow.
     * 
     */
    @Test
    public void testContent() {
        try {
            model = BPMNModelFactory.read("/validation_issue_346.bpmn");
            logger.info("...read model");

            Set<BPMNProcess> processes = model.getProcesses();
            assertEquals(2, processes.size());

            // Test default process
            BPMNProcess process = model.openDefaultProces();
            assertNotNull(process);

            BPMNValidationHandler bpmnValidationHandler = new BPMNValidationHandler();
            List<BPMNValidationMarker> result = bpmnValidationHandler.validate(model, true);

            // we expect no errors
            assertEquals(0, result.size());

            // Now open the 'Pool-1' containing a receive Task
            process = model.findProcessByName("Pool-1");
            process = model.openProcess(process.getId());
            assertNotNull(process);
            bpmnValidationHandler = new BPMNValidationHandler();
            result = bpmnValidationHandler.validate(model, true);

            // we expect no errors
            assertEquals(0, result.size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

}
