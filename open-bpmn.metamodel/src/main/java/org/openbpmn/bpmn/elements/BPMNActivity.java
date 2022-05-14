package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Node;

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
public class BPMNActivity extends BPMNFlowElement {

    protected BPMNActivity(BPMNModel model, Node node, String type) {
        super(model, node, type);
    }

}
