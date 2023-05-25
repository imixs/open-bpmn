package org.openbpmn.bpmn.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;

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
                continue;
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
                continue;
            }

            // Catch Event?
            if (BPMNTypes.CATCH_EVENT.equals(event.getType())) {
                if (event.getOutgoingSequenceFlows().size() == 0) {
                    validationErrors.add(new BPMNValidationError("Catch Event",
                            "A Catch Event must have at least one outgoing  Sequence Flow!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }
                continue;
            }

            // Throw Event?
            if (BPMNTypes.THROW_EVENT.equals(event.getType())) {
                if (event.getIngoingSequenceFlows().size() == 0) {
                    validationErrors.add(new BPMNValidationError("Throw Event",
                            "A Throw Event must have at least one ingoing  Sequence Flow!", event.getId(),
                            BPMNValidationError.ErrorType.ERROR));
                }
                continue;
            }

        }

        // validate gateways....
        Set<Gateway> gateways = process.getGateways();
        for (Gateway gateway : gateways) {
            if (gateway.getIngoingSequenceFlows().size() == 0
                    || gateway.getOutgoingSequenceFlows().size() == 0) {
                validationErrors.add(new BPMNValidationError("Gateway",
                        "A Gateway must have at least one ingoing and one outgoing Sequence Flow!", gateway.getId(),
                        BPMNValidationError.ErrorType.ERROR));

                continue;
            }

            // validate tasks....
            Set<Activity> tasks = process.getActivities();
            for (Activity task : tasks) {
                if (task.getIngoingSequenceFlows().size() == 0
                        || task.getOutgoingSequenceFlows().size() == 0) {
                    validationErrors.add(new BPMNValidationError("Task",
                            "A Task must have at least one ingoing and one outgoing Sequence Flow!", gateway.getId(),
                            BPMNValidationError.ErrorType.ERROR));

                    continue;
                }

            }
        }
    }
}
