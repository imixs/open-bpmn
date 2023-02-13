package org.openbpmn.metamodel.test.edges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing SequenceFlows and Conditions
 * 
 * @author rsoika
 *
 */
public class TestSequenceFlows {

    private static Logger logger = Logger.getLogger(TestSequenceFlows.class.getName());

    /**
     * This test parses a bpmn file and does some tests with conditional sequence
     * flows
     */
    @Test
    public void testReadConditions() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-12.bpmn");
            logger.info("...read model");
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            // count sequenceflows...
            assertEquals(3, process.getSequenceFlows().size());

            // Test SequenceFlow_6 which should have a condition...
            BPMNElementEdge edge6 = process.findElementEdgeById("SequenceFlow_6");
            assertNotNull(edge6);
            assertEquals("sequenceFlow", edge6.getType());
            SequenceFlow sequenceFlow6 = (SequenceFlow) edge6;
            String expression = sequenceFlow6.getConditionExpression();
            assertNotNull(expression);
            assertEquals("1==1", expression);

            // Now lets test the Gatway
            Gateway gateway = (Gateway) process.findElementById("ExclusiveGateway_1");
            assertNotNull(gateway);

            Set<SequenceFlow> sequenceFlows = gateway.getOutgoingSequenceFlows();
            assertEquals(2, sequenceFlows.size());

            // test a single sequenceFLow by stream api....
            sequenceFlow6 = sequenceFlows.stream()
                    .filter(s -> "SequenceFlow_6".equals(s.getId())).findFirst().orElse(null);
            assertNotNull(sequenceFlow6);

            // we expect that SequenceFlow_5 is the default
            assertEquals("SequenceFlow_5", gateway.getAttribute("default"));

            // now test if SequenceFlow_5 resolves to default....
            SequenceFlow sequenceFlow5 = (SequenceFlow) process.findElementEdgeById("SequenceFlow_5");
            assertNotNull(sequenceFlow5);
            assertTrue(sequenceFlow5.isDefault());

            // ... and SequenceFlow_6 should not be default flow...
            assertFalse(sequenceFlow6.isDefault());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

}
