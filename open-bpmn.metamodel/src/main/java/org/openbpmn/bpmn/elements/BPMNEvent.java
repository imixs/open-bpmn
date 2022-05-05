package org.openbpmn.bpmn.elements;

import org.w3c.dom.Node;

public class BPMNEvent extends BPMNFlowElement {

    public enum EventType {
        START(Constants.START_EVENT), END(Constants.END_EVENT), CATCH(Constants.CATCH_EVENT),
        THROW(Constants.THROW_EVENT);

        private String bpmnType;

        EventType(String type) {
            this.bpmnType = type;
        }

        private static class Constants {
            public static final String START_EVENT = "bpmn2:startEvent";
            public static final String END_EVENT = "bpmn2:endEvent";
            public static final String CATCH_EVENT = "bpmn2:catchEvent";
            public static final String THROW_EVENT = "bpmn2:throwEvent";
        }

        public String getBpmnType() {
            return bpmnType;
        }
    }
    
    public BPMNEvent(EventType type, Node node) {
        super(type.bpmnType, node);
    }

    public BPMNEvent(String type, Node node) {
        super(type, node);
    }

}
