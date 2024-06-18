package org.openbpmn.bpmn.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.exceptions.BPMNModelException;

/**
 * The BPMNValidationHandler validates a complete BPMNModel for validation
 * errors
 * 
 */
public class BPMNValidationHandler {

    /**
     * This method validates the current model. It returns a list of
     * validationErrors that contains a lable and a short description pointing to an
     * element that causes a problem.
     * 
     * The param forceValidation indicates if the full model should be validated. In
     * case of forceValidation=false, only elements not yet validated will be
     * validated.
     * 
     * @param forceValidation - if true the full model will be validated.
     * @return
     * @throws BPMNModelException
     */
    public List<BPMNValidationMarker> validate(BPMNModel model, boolean forceValidation) throws BPMNModelException {
        List<BPMNValidationMarker> result = new ArrayList<>();
        // iterate over all process
        Set<BPMNProcess> processes = model.getProcesses();
        for (BPMNProcess _process : processes) {
            result.addAll(validateProcess(_process, forceValidation));
        }
        return result;
    }

    /**
     * This method validates the elements of a single BPMNProcess and returns a list
     * ob BPMNValidationMarkers.
     * 
     * If an element is already validated and forceValidation is false, the
     * validation will be skipped. This allows a caller to perform a live validation
     * for only new added or changed element nodes.
     * 
     * @return
     * @throws BPMNModelException
     */
    public List<BPMNValidationMarker> validateProcess(BPMNProcess process, boolean forceValidation)
            throws BPMNModelException {
        List<BPMNValidationMarker> result = new ArrayList<>();
        // make sure that the process is initialized
        process.init();
        // validate events....
        Set<Event> events = process.getEvents();
        for (Event event : events) {
            if (forceValidation || !event.isValidated()) {
                event.validate();
                event.setValidated(true);
            }
            result.addAll(event.getValidationMarkers());
        }

        // validate gateways....
        Set<Gateway> gateways = process.getGateways();
        for (Gateway gateway : gateways) {
            if (forceValidation || !gateway.isValidated()) {
                gateway.validate();
                gateway.setValidated(true);
            }
            result.addAll(gateway.getValidationMarkers());
        }

        // validate tasks....
        Set<Activity> tasks = process.getActivities();
        for (Activity task : tasks) {
            if (forceValidation || !task.isValidated()) {
                task.validate();
                task.setValidated(true);
            }
            result.addAll(task.getValidationMarkers());
        }

        // finally return the list of all Validation marker of this process
        return result;
    }
}
