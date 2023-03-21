package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;

/**
 * In some cases the `BPMNApplyPropertiesUpdateOperation` causes on the server
 * side a more complex data update that requires an update of the property panel
 * on the client side. For example adding a new Event or Diagram Definition is
 * such a situation where the complete panel need to be updated on the client
 * side.
 * 
 * In this cases the sever can send a `BPMNPropertyPanelUpdateAction` to the
 * client. The BPMN Property Panel reacts on this kind of action and updates the
 * panel content.
 * 
 */
public class BPMNPropertyPanelUpdateAction extends Action {

    public static final String KIND = "propertyPanelUpdate";

    // private String selectedElementID;
    // private String category;

    public BPMNPropertyPanelUpdateAction() {
        super(KIND);

    }

    // public BPMNPropertyPanelUpdateAction(final String selectedElementID, final
    // String category) {
    // super(KIND);
    // this.selectedElementID = selectedElementID;
    // this.category = category;

    // }
}