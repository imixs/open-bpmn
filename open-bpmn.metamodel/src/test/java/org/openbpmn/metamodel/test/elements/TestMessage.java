package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing Message objects
 * 
 * @author rsoika
 *
 */
public class TestMessage {

    private static Logger logger = Logger.getLogger(TestMessage.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadMessage() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-6.bpmn");

            logger.info("...read model");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            // read signal
            assertEquals(1, model.getMessages().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read sucessful ");
    }

    /**
     * This test creates a message element
     */
    @Test
    public void testCreateMessage() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_message.bpmn";
        try {
            logger.info("...create empty model");

            String exporter = "demo";
            String version = "1.0.0";
            String targetNameSpace = "http://org.openbpmn";
            model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
            assertNotNull(model);

            BPMNProcess defaultProcess = model.openDefaultProces();
            assertNotNull(defaultProcess);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());

            // create signal
            model.addMessage("message_1", "My Message");
            // read signal
            assertEquals(1, model.getMessages().size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test creates a message definition to an existing event.
     * We expect that the message is created automatically.
     */
    @Test
    public void testCreateMessageDefinition() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_message_event.bpmn";
        try {
            logger.info("...create empty model");

            String exporter = "demo";
            String version = "1.0.0";
            String targetNameSpace = "http://org.openbpmn";
            model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
            assertNotNull(model);

            BPMNProcess defaultProcess = model.openDefaultProces();
            assertNotNull(defaultProcess);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());

            // create catch event
            Event event = defaultProcess.addEvent("event-1", "Message Event 1", BPMNTypes.CATCH_EVENT);

            // add definition
            event.addEventDefinition(BPMNTypes.EVENT_DEFINITION_MESSAGE);

            // we expect that we have now 1 new Signal in the definition list

            assertEquals(1, model.getMessages().size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test parses a bpmn file and removes message_1 which is referred by an
     * event
     */
    @Test
    public void testDeleteSignal() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_message_event3.bpmn";
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-11.bpmn");

            logger.info("...read model");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            // remove signal
            Message message = model.getMessages().iterator().next();
            assertNotNull(message);

            model.deleteMessage(message.getId());
            // read Message
            assertEquals(0, model.getMessages().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model update sucessful: " + out);

    }

}
