package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;

/**
 * In some cases the `BPMNApplyPropertiesUpdateOperation` causes on the server
 * side a more complex data update that requires an update of the property panel
 * on the client side. For example adding a new Event or Diagram Definition is
 * such a situation where the complete panel need to be updated on the client
 * side.
 * 
 * In this cases the sever can send a `BPMNPropertiesUpdateAction` to the
 * client. The BPMN Property Panel reacts on this kind of action and updates the
 * panel content.
 * 
 */
public class BPMNPropertiesUpdateAction extends Action {

    public static final String KIND = "BPMNPropertiesUpdateAction";
    private String data;
    private String schema;
    private String uiSchema;

    public BPMNPropertiesUpdateAction() {
        super(KIND);
    }

    public BPMNPropertiesUpdateAction(String data, String schema, String uiSchema) {
        super(KIND);
        this.data = data;
        this.schema = schema;
        this.uiSchema = uiSchema;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUiSchema() {
        return uiSchema;
    }

    public void setUiSchema(String uiSchema) {
        this.uiSchema = uiSchema;
    }

}