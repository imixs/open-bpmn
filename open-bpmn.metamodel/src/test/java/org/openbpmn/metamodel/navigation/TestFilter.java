package org.openbpmn.metamodel.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

/**
 * This JUnit test verifies the filter methods
 * 
 */
public class TestFilter {
    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

    /**
     * Loads a model and test the filter method.
     * 
     * The method applies a filter to match ThrowEvents only within a process.
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFilterMethod() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-15.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProces();

        Set<? extends BPMNElementNode> throwEvents = process
                .findElementNodes(n -> BPMNTypes.THROW_EVENT.equals(n.getType()));

        assertNotNull(throwEvents);
        // We expect exactly one result.

        assertEquals(1, throwEvents.size());

    }

}
