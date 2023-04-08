package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the creation of specific BPMN element types
 * 
 * @author rsoika
 *
 */
public class TestCreateElements {

    private static Logger logger = Logger.getLogger(TestCreateElements.class.getName());

    /**
     * This test creates a bpmn file with a process definition
     */
    @Test
    public void testCreateDataObject() {
        String out = "src/test/resources/output/process-example-6.bpmn";

        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            BPMNProcess process = model.openDefaultProces();
            // Create a DataObject
            process.addDataObject("dataobject_1", "DataObject-1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        assertEquals(1, model.getProcesses().size());

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
