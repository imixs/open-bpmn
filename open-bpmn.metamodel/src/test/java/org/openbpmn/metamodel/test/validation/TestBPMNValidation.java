package org.openbpmn.metamodel.test.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.bpmn.validation.BPMNValidationHandler;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;

/**
 * This test class is testing the BPMN Validation
 * 
 * @author rsoika
 *
 */
public class TestBPMNValidation {
    BPMNModel model;

    /**
     * Test simple model with start / end event without a sequence flow.
     * 
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
            Event startEvent = process.addEvent("start_1", "Start", BPMNTypes.START_EVENT);
            Event endEvent = process.addEvent("end_1", "End", BPMNTypes.END_EVENT);

            BPMNValidationHandler bpmnValidationHandler = new BPMNValidationHandler();
            List<BPMNValidationMarker> result = bpmnValidationHandler.validate(model, true);

            // we expect two errors
            assertEquals(2, result.size());

            // now connect both elements
            process.addSequenceFlow("seq_1", startEvent.getId(), endEvent.getId());
            result = bpmnValidationHandler.validate(model, true);
            // ... we expect no more errors
            assertEquals(0, result.size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

}
