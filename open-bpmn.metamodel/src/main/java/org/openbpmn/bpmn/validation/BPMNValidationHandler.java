package org.openbpmn.bpmn.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;

/**
 * The BPMNValidationHandler validates a complete BPMNModel for validation
 * errors
 * 
 */
public class BPMNValidationHandler {

    BPMNModel model = null;
    List<BPMNValidationError> validationErrors = null;

    public BPMNValidationHandler(BPMNModel model) {
        this.model = model;
        validationErrors = new ArrayList<>();
    }

    /**
     * Returns the list of all available Validation Errors
     * 
     * @return
     */
    public List<BPMNValidationError> getValidationErrors() {
        return validationErrors;
    }

    /**
     * This method validates the current model. It returns a list of
     * validationErrors that contains a lable and a short description pointing to an
     * element that causes a problem.
     * 
     * @return
     */
    public List<BPMNValidationError> validate() {
        validationErrors = new ArrayList<>();
        // iterate over all process
        Set<BPMNProcess> processes = model.getProcesses();
        for (BPMNProcess _process : processes) {
            validateProcess(_process);
        }
        return validationErrors;
    }

    /**
     * This method validates the elements of a single BPMNProcess. The
     * validationErrors are added to the validationErrors list
     * 
     * @return
     */
    private void validateProcess(BPMNProcess process) {

        // validate events....
        Set<Event> events = process.getEvents();
        for (Event event : events) {

            // Start event?
            if (BPMNTypes.START_EVENT.equals(event.getType())) {

                if (event.getIngoingSequenceFlows().size() > 0) {
                    validationErrors.add(new BPMNValidationError("Start Event",
                            "A Start Event may not have incoming Sequence Flows!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }
                if (event.getOutgoingSequenceFlows().size() != 1) {
                    validationErrors.add(new BPMNValidationError("Start Event",
                            "A Start Event must have exactly one outgoing  Sequence Flow!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }

            }

            // END event?
            if (BPMNTypes.END_EVENT.equals(event.getType())) {

                if (event.getIngoingSequenceFlows().size() == 0) {
                    validationErrors.add(new BPMNValidationError("End Event",
                            "An End Event must have at least one incoming Sequence Flow!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }
                if (event.getOutgoingSequenceFlows().size() > 0) {
                    validationErrors.add(new BPMNValidationError("End Event",
                            "An End Event must NOT have any outgoing  Sequence Flow!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }

            }
        }

    }
}
