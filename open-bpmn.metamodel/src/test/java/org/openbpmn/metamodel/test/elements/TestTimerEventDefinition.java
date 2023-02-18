package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;

/**
 * This test class is testing Signal Events
 * 
 * @author rsoika
 *
 */
public class TestTimerEventDefinition {

    private static Logger logger = Logger.getLogger(TestTimerEventDefinition.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadTimerEventDefinitions() {
        BPMNModel model = null;
        Event event = null;
        Element timerDefinition = null;
        Set<Element> eventDefinitions = null;
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-13.bpmn");

            logger.info("...read model");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);
            assertEquals(4, process.getEvents().size());

            // test start event
            event = (Event) process.findElementNodeById("StartEvent_1");
            assertNotNull(event);
            // get all timerEventDefinitions...
            eventDefinitions = event.getEventDefinitionsByType("timerEventDefinition");
            assertEquals(1, eventDefinitions.size());
            // test the first definition
            timerDefinition = eventDefinitions.iterator().next();
            Set<Element> timeDateList = model.findChildNodesByName(timerDefinition,
                    BPMNNS.BPMN2, "timeDate");
            Element timeDate = timeDateList.iterator().next();
            assertEquals("42", timeDate.getTextContent());

            // test IntermediateCatchEvent_1
            event = (Event) process.findElementNodeById("IntermediateCatchEvent_1");
            assertNotNull(event);
            eventDefinitions = event.getEventDefinitionsByType("timerEventDefinition");
            assertEquals(1, eventDefinitions.size());
            timerDefinition = eventDefinitions.iterator().next();
            Set<Element> timeCycleList = model.findChildNodesByName(timerDefinition,
                    BPMNNS.BPMN2, "timeCycle");
            Element timeCycle = timeCycleList.iterator().next();
            assertEquals("43", timeCycle.getTextContent());

            // test IntermediateCatchEvent_2
            event = (Event) process.findElementNodeById("IntermediateCatchEvent_2");
            assertNotNull(event);
            eventDefinitions = event.getEventDefinitionsByType("timerEventDefinition");
            assertEquals(1, eventDefinitions.size());
            timerDefinition = eventDefinitions.iterator().next();
            Element timeDuration = model.findChildNodeByName(timerDefinition,
                    BPMNNS.BPMN2, "timeDuration");

            assertEquals("44", timeDuration.getTextContent());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful ");
    }

}
