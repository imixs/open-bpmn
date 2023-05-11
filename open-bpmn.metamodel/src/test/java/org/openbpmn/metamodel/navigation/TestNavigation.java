package org.openbpmn.metamodel.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.navigation.BPMNFlowIterator;
import org.openbpmn.bpmn.navigation.BPMNFlowNavigator;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

public class TestNavigation {
    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

    /**
     * Loads a model and navigates....
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testSimpleNavigation() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-15.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProces();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");

        assertNotNull(task1);

        BPMNFlowIterator<Event> eventNavigator = new BPMNFlowIterator<Event>(task1,
                n -> n instanceof Event);

        assertNotNull(eventNavigator);

        while (eventNavigator.hasNext()) {
            System.out.println(eventNavigator.next().getId());
        }
    }

    /**
     * Loads a model and navigates....
     * 
     * Event-7 : target should be Task-2
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testSimpleEventNavigation() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-15.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProces();

        // get Event5
        BPMNElementNode event5 = process.findElementNodeById("event_cFt0jw");

        assertNotNull(event5);

        // find next task or event.....
        BPMNFlowNavigator<BPMNElementNode> eventNavigator = new BPMNFlowNavigator<BPMNElementNode>(event5,
                n -> ((n instanceof Event) || (n instanceof Activity)));

        // We expect the Task 2 as the next target.
        assertNotNull(eventNavigator);
        BPMNElementNode nextNode = eventNavigator.next();
        assertNotNull(nextNode);
        assertTrue(nextNode instanceof Activity);
        assertEquals("task_1gbByA", nextNode.getId());

    }

    /**
     * Loads a model and navigates....
     * 
     * Event-7 : target should be Task-2
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testTargetNavigation() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-15.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProces();

        // get Event7
        BPMNElementNode event7 = process.findElementNodeById("event_H4GvRQ");

        assertNotNull(event7);

        BPMNFlowNavigator<Event> eventNavigator = new BPMNFlowNavigator<Event>(event7,
                n -> n instanceof Event);

        assertNotNull(eventNavigator);

    }

    /**
     * This class navigates from a Link Throw Event to a Link Catch event
     * 
     * It is a example implementation of a navigator linking two events
     * 
     */
    class LinkNavigator {

        public BPMNElementNode findNext(BPMNElementNode node) {

            // is it a throw event?
            if (BPMNTypes.THROW_EVENT.equals(node.getType())) {

                String linkName = node.getName();

                // now find the corresponding first catch event with the same name

                Set<? extends BPMNElementNode> filteredElementList = node.getBpmnProcess()
                        .findElementNodes(
                                n -> (BPMNTypes.CATCH_EVENT.equals(n.getType()) //
                                        && ((Event) n).getEventDefinitionsByType(BPMNTypes.EVENT_DEFINITION_LINK)
                                                .size() > 0 //
                                        && linkName.equals(n.getName())));

                // return the first one...
                if (filteredElementList != null && filteredElementList.size() > 0) {
                    return filteredElementList.iterator().next();
                }

            }

            // no match
            return null;
        }

    }
}
