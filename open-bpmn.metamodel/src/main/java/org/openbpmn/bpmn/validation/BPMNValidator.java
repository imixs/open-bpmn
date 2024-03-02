package org.openbpmn.bpmn.validation;

import java.util.List;

/**
 * This interface indicates that a BPMN element can be validated. The interface
 * is used by BPMNElement.
 * 
 */
public interface BPMNValidator {

    /**
     * This method validates the element within the current model and returns a list
     * of Validation Errors or an empty list if no errors where found.
     * 
     * @return - list of validation errors or null if no errors exist.
     */
    public default List<BPMNValidationMarker> validate() {
        return null;
    }

    /**
     * Returns true if the element was validated. This method can be used by a model
     * validator to skip already validated elements. The status is set by the
     * 'validate' method.
     * 
     * @return - true if the element was already validated.
     */
    public default boolean isValidated() {
        return true;
    }

    /**
     * Resets the current validation status of this element. The validation status
     * can be verified by the method 'isValidated'.
     */
    public void resetValidation();

}
