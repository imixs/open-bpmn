package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.List;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;
import org.w3c.dom.Element;

/**
 * An Activity is work that is performed within a Business Process. An Activity
 * can be atomic or non-atomic (compound). The types of Activities that are a
 * part of a Process are: Task, Sub-Process, and Call Activity, which allows the
 * inclusion of re-usable Tasks and Processes in the diagram. However, a Process
 * is not a specific graphical object. Instead, it is a set of graphical
 * objects. The following sub clauses will focus on the graphical objects Sub-
 * Process and Task. Activities represent points in a Process flow where work is
 * performed. They are the executable elements of a BPMN Process. The Activity
 * class is an abstract element, sub-classing from FlowElement
 * 
 * @author rsoika
 *
 */
public class Activity extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 110.0;
    public final static double DEFAULT_HEIGHT = 50.0;

    protected Activity(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess) throws BPMNModelException {
        super(model, node, type, bpmnProcess);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    /**
     * Remove any embedded bpmndi:BPMNLabel element within the bpmndi:BPMNShape
     * 
     * Positioning of the label is part of the client. Any position update should
     * ignore these settings in Open-BPMN.
     * 
     */
    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);

        // remove optional BPMNLabel
        Element bpmnLabel = getModel().findChildNodeByName(this.bpmnShape, BPMNNS.BPMNDI, "BPMNLabel");
        if (bpmnLabel != null) {
            this.bpmnShape.removeChild(bpmnLabel);
        }
    }

    /**
     * This method adds a new Boundary Event this activity.
     * 
     * The element has not position assigned yet!
     * 
     * @return
     * @throws BPMNModelException
     */
    public Event addBoundaryEvent(String eventID, String name) throws BPMNModelException {
        Event boundaryEvent;
        boundaryEvent = bpmnProcess.addEvent(eventID, name, BPMNTypes.BOUNDARY_EVENT);
        boundaryEvent.setAttribute("attachedToRef", this.getId());
        return boundaryEvent;
    }

    /**
     * This method returns a list of all boundaray Events attached to this activity.
     * 
     * @return
     */
    public List<Event> getAllBoundaryEvents() {
        List<Event> result = new ArrayList<>();
        for (Event e : bpmnProcess.getEvents()) {
            if (BPMNTypes.BOUNDARY_EVENT.equals(e.getType())) {

                // test ref....
                if (this.getId().equals(e.getAttribute("attachedToRef"))) {
                    result.add(e);
                }

            }
        }
        return result;
    }

    /**
     * Validate Start, End, Catch and Throw event types
     */
    @Override
    public List<BPMNValidationMarker> validate() {
        resetValidation();

        if (this.getIngoingSequenceFlows().size() == 0
                || this.getOutgoingSequenceFlows().size() == 0) {
            this.addValidationMarker(new BPMNValidationMarker("Task",
                    "A Task must have at least one ingoing and one outgoing Sequence Flow!", this.getId(),
                    BPMNValidationMarker.ErrorType.ERROR));
        }

        return this.getValidationMarkers();
    }

}
