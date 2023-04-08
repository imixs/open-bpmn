package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.MessageFlow;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestReadBPMNEdges {

    private static Logger logger = Logger.getLogger(TestReadBPMNEdges.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a collaboration bpmn file containing 1 participants and a
     * task in the default process connected by a MessageFlow.
     * 
     */
    @Test
    public void testReadCollaborationModelWithMessageFlows() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-9.bpmn");

            // we expect 2 Processes (default + participant)
            assertEquals(2, model.getProcesses().size());

            // verify MessageFlow
            Set<MessageFlow> messageFlows = model.getMessageFlows();
            assertNotNull(messageFlows);
            assertEquals(1, messageFlows.size());

            MessageFlow messageFlow = messageFlows.iterator().next();

            assertEquals("task1", messageFlow.getTargetRef());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

    /**
     * This test reads a bpmn file containing 2 Dataobjects with an assoication
     * 
     */
    /**
     * This test reads a bpmn file containing a DataObject
     */
    @Test
    public void testReadDataObject() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-6.bpmn");

            BPMNProcess process = model.openDefaultProces();
            assertNotNull(process);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, process.getProcessType());

            assertEquals(2, process.getDataObjects().size());

            BPMNElementNode dataObject = process.findElementNodeById("DataObject_2");
            assertNotNull(dataObject);

            // test association
            Set<Association> associations = process.getAssociations();
            assertNotNull(associations);
            assertEquals(1, associations.size());

            Association association = associations.iterator().next();
            assertEquals("DataObject_2", association.getSourceRef());
            assertEquals("DataObject_3", association.getTargetRef());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

}
