package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the creation of a BPMN Model with elements containing a
 * documentation element
 * 
 * @author rsoika
 *
 */
public class TestDocumentation {

    private static Logger logger = Logger.getLogger(TestDocumentation.class.getName());

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows
     */
    @Test
    public void testCreateModelWithElementAndDocumentation() {
        String out = "src/test/resources/create-process_documentation_1.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            BPMNProcess processContext = model.openDefaultProces();
            assertNotNull(processContext);
            // add a start and end event
            Event bpmnElement = processContext.addEvent("start_1", "Start", BPMNTypes.START_EVENT);

            // add a documentation - we expect a CDATA tag
            String value = "Some text with \n<markup>code</markup>\n Finish!";
            bpmnElement.setDocumentation(value);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * Test to read a documentation element with a CDATA tag
     * <p>
     * This is the expected documentation element value format
     */
    @Test
    public void testReadElementDocumentationCDATA() {
        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/process_documentation_CDATA.bpmn");
            BPMNProcess process = model.openProcess(null);
            Event startEvent = (Event) process.findElementNodeById("start_1");
            assertEquals("Some text with \n"
                    + "<markup>code</markup>!", startEvent.getDocumentation());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

    }

    /**
     * Test to read a documentation element with a Text (no CDATA)
     * <p>
     * Normally the documentation value should be in a CDATA tag.
     */
    @Test
    public void testReadElementDocumentationText() {
        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/process_documentation_TEXT.bpmn");
            BPMNProcess process = model.openProcess(null);
            Event startEvent = (Event) process.findElementNodeById("start_1");
            assertEquals("Some text with", startEvent.getDocumentation());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

    }

}
