package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestReadBPMNNodes {

    private static Logger logger = Logger.getLogger(TestReadBPMNNodes.class.getName());

    static BPMNModel model = null;

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

            // test the label position of the event
            BPMNLabel label = dataObject.getLabel();
            assertNotNull(label);
            assertEquals(101.0, label.getPosition().getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

}
