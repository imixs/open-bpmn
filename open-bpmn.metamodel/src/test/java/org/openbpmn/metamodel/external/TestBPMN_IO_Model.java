package org.openbpmn.metamodel.external;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests reading bpmn.io model file
 * 
 * @author rsoika
 *
 */
public class TestBPMN_IO_Model {

    private static Logger logger = Logger.getLogger(TestBPMN_IO_Model.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a collaboration model
     */
    @SuppressWarnings("unused")
    @Test
    public void testReadModel() {
        logger.info("...read bpmn.io model");
        try {
            model = BPMNModelFactory.read("/external/bpmn-io-test-model.bpmn");
            // we expect the existence of 2 process elements

            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());

            assertTrue(model.isCollaborationDiagram());

            // get first participant and load the process context
            Participant bpmnParticipant = participants.iterator().next();
            BPMNProcess process = model.openProcess("Process_0bnmnuz");
            assertNotNull(process);
            // we expect 35 Task element in this process
            assertEquals(35, process.getActivities().size());

            // Test Flow_0oxb85w and Activity_1uvs84q
            SequenceFlow squenceFlow = (SequenceFlow) process.findElementEdgeById("Flow_0oxb85w");
            assertNotNull(squenceFlow);
            assertEquals("Activity_1uvs84q", squenceFlow.getSourceRef());
            assertEquals("Gateway_0kc8enp", squenceFlow.getTargetRef());
            // test Gateway
            Gateway gateway = (Gateway) process.findElementNodeById("Gateway_0kc8enp");
            assertNotNull(gateway);
            // now the source is a unknown element..
            BPMNElementNode sourceElement = process.findElementNodeById("Activity_1uvs84q");
            assertNotNull(sourceElement);
            assertEquals("subProcess", sourceElement.getType());

            // We expect 10 Text Annotations. But these are not assigned correctly. We
            // expect an auto assignment to the default process
            BPMNProcess defaultProcess = model.openDefaultProces();
            Set<TextAnnotation> textAnnotations = defaultProcess.getTextAnnotations();
            assertEquals(12, textAnnotations.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful! ");
    }

}
