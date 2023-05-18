package org.openbpmn.bpmn;

/**
 * A ModelNotification holds a log message that contains notable information
 * about the current model state.
 * A client can read ModelNotifications and displays them to the user. In
 * Eclipse GLSP this is dan by the GLSP by generating ServerMessageAction.
 */
public class ModelNotification {
    private Severity severity;
    private String message;
    private String details;

    public ModelNotification(Severity severity, String message, String details) {
        this.severity = severity;
        this.message = message;
        this.details = details;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public enum Severity {
        ERROR,
        WARNING,
        INFO;

        public static String toString(final Severity severity) {
            return severity != null ? severity.toString() : null;
        }
    }

}
