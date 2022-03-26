package org.imixs.bpmn.glsp.elements.event.edit;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.operations.Operation;

/**
 * The ApplyEventEditOperation is an {@link Action} that directly manipulates
 * the EventNode model representation on server side. The action is send from
 * the client to the server.
 * <p>
 * Each operation is uniquely defined by its KIND
 * <p>
 * The Operations are handled by the {@link ApplyEventUpdateOperationHandler}.
 * The operation handler is responsible of processing the operation and updates
 * the model representation accordingly.
 * <p>
 * The expression is a string that describes with attribute should be updated:
 * <p>
 * ATTRIBUTE_NAME:NEW_VALUE
 *
 */
public class ApplyEventUpdateOperation extends Operation {

    public static final String DOCUMENTATION_PREFIX = "documentation:";
    public static final String NAME_PREFIX = "name:";

    private String id;
    private String jsonData;

    public ApplyEventUpdateOperation() {
        super("applyEventUpdate");
    }

    public ApplyEventUpdateOperation(final String nodeId, final String jsonData) {
        this();
        this.id = nodeId;
        this.jsonData = jsonData;
    }

    public String getId() {
        return id;
    }

    public void setId(final String nodeId) {
        this.id = nodeId;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(final String jsonData) {
        this.jsonData = jsonData;
    }

}
