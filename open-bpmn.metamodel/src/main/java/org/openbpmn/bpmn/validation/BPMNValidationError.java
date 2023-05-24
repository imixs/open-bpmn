package org.openbpmn.bpmn.validation;

import org.openbpmn.bpmn.BPMNModel;

/**
 * The BPMNValidationError defines the result of a Model validation for a single
 * BPMN Element.
 * 
 * @see BPMNModel.validate
 */
public class BPMNValidationError {

    private final String label;
    private final String description;
    private final String elementId;
    private ErrorType errorType;

    public BPMNValidationError(final String label, final String description, final String elementId,
            final ErrorType errorType) {
        super();
        this.label = label;
        this.description = description;
        this.elementId = elementId;
        this.errorType = errorType;

    }

    public static enum ErrorType {
        INFO,
        WARNING,
        ERROR
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public String getElementId() {
        return elementId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elementId == null) ? 0 : elementId.hashCode());
        result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BPMNValidationError other = (BPMNValidationError) obj;
        if (elementId == null) {
            if (other.elementId != null)
                return false;
        } else if (!elementId.equals(other.elementId))
            return false;
        if (errorType == null) {
            if (other.errorType != null)
                return false;
        } else if (!errorType.equals(other.errorType))
            return false;
        return true;
    }

}
