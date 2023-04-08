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
import org.openbpmn.bpmn.elements.Signal;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing Signal Events
 * 
 * @author rsoika
 *
 */
public class TestSignal {

    private static Logger logger = Logger.getLogger(TestSignal.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadSignal() {
        BPMNModel model = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-10.bpmn");

            logger.info("...read model");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            Event event1 = (Event) process.findElementNodeById("IntermediateCatchEvent_1");
            assertNotNull(event1);

            // read signal
            assertEquals(1, model.getSignals().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read sucessful ");
    }

    /**
     * This test creates a signal element
     */
    @Test
    public void testCreateSignal() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_signal.bpmn";
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
            model.addSignal("signal_1", "My Signal");
            // read signal
            assertEquals(1, model.getSignals().size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test creates a signal definition to an existing event.
     * We expect that the signal is created automatically.
     */
    @Test
    public void testCreateSignalDefinition() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_signal_event.bpmn";
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
            Event event = defaultProcess.addEvent("event-1", "Signal Event 1", BPMNTypes.CATCH_EVENT);

            // add definition
            event.addEventDefinition(BPMNTypes.EVENT_DEFINITION_SIGNAL);

            // we expect that we have now 1 new Signal in the definition list

            assertEquals(1, model.getSignals().size());

        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test parses a bpmn file and removes singnal_1 which is referred by an
     * event
     */
    @Test
    public void testDeleteSignal() {
        BPMNModel model = null;
        String out = "src/test/resources/output/create_signal_event3.bpmn";
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-10.bpmn");

            logger.info("...read model");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            // remove signal
            Signal signal = model.getSignals().iterator().next();
            assertNotNull(signal);

            model.deleteSignal(signal.getId());
            // read signal
            assertEquals(0, model.getSignals().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model update sucessful: " + out);

    }

}
