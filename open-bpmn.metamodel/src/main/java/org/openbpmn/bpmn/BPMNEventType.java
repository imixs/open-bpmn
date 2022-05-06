package org.openbpmn.bpmn;

public enum BPMNEventType {
    EVENT(Constants.EVENT), START(Constants.START_EVENT), END(Constants.END_EVENT), CATCH(Constants.CATCH_EVENT), THROW(Constants.THROW_EVENT);

    public String name;

    BPMNEventType(String name) {
        this.name = name;
    }

    private static class Constants {
        public static final String EVENT = "event";
        public static final String START_EVENT = "startEvent";
        public static final String END_EVENT = "endEvent";
        public static final String CATCH_EVENT = "catchEvent";
        public static final String THROW_EVENT = "throwEvent";
    }

    public String getName() {
        return name;
    }

}
