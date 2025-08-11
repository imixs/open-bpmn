package org.openbpmn.metamodel.external;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;

/**
 * This test class tests reading bpmn.io model file
 * 
 * @author rsoika
 *
 */
public class TestBPMN_ADONIS_Model {

    private static Logger logger = Logger.getLogger(TestBPMN_ADONIS_Model.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a collaboration model
     */
    @SuppressWarnings("unused")
    @Test
    public void testReadModel() {
        logger.info("...read bpmn.adonis model");
        try {
            model = BPMNModelFactory.read("/external/bpmn-adonis-test-model.bpmn");
            // we expect the existence of 2 process elements

            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(3, participants.size());

            assertTrue(model.isCollaborationDiagram());
            // test shape of first collaboration
            Participant participant = model.findParticipantById("_7b8057f2-41dc-480c-a82e-454bf47f9736");
            assertNotNull(participant);

            // test shape
            assertNotNull(participant.getBpmnShape());

            // Test Startereignis
            BPMNProcess process1 = model.openProcess(participant.getProcessId());
            assertNotNull(process1);
            assertEquals("process_7b8057f2-41dc-480c-a82e-454bf47f9736", process1.getId());
            Set<? extends BPMNElementNode> startEvents = process1
                    .findElementNodes(n -> (BPMNTypes.START_EVENT.equals(n.getType())));
            assertEquals(1, startEvents.size());
            Event startEvent = (Event) startEvents.iterator().next();
            assertEquals("_31a9ee02-335a-44db-a43a-ab7b37323a7e", startEvent.getId());
            // test bpmnShape -> <omgdc:Bounds width="56" height="56" x="389" y="218" />
            Element shapeElement = startEvent.getBpmnShape();
            assertNotNull(shapeElement);
            BPMNBounds bounds = startEvent.getBounds();
            assertNotNull(bounds);
            BPMNPoint position = bounds.getPosition();
            assertNotNull(position);
            assertEquals(389, position.getX());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful! ");
    }

}
