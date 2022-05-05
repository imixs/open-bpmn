package org.openbpmn.bpmn.elements;

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

    public enum TaskType {
        TASK(Constants.TASK),SEND(Constants.SEND_TASK), MANUAL(Constants.MANUAL_TASK);

        private String bpmnType;

        TaskType(String type) {
            this.bpmnType = type;
        }

        private static class Constants {
            public static final String SEND_TASK = "bpmn2:sendTask";
            public static final String MANUAL_TASK = "bpmn2:manualTask";
            public static final String TASK = "bpmn2:task";
        }

        public String getBpmnType() {
            return bpmnType;
        }
    }
    
    public BPMNActivity(String type, Node node) {
        super(type, node);
    }
    
    public BPMNActivity(TaskType type, Node node) {
        super(type.bpmnType, node);
    }

    

}
