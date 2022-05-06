package org.openbpmn.bpmn;

public enum BPMNGatewayType {
    GATEWAY(Constants.GATEWAY), //
    EXCLUSIVE(Constants.EXCLUSIVE_GATEWAY), //
    INCLUSIVE(Constants.INCLUSIVE_GATEWAY), //
    PARALLEL(Constants.PARALLEL_GATEWAY), //
    COMPLEX(Constants.COMPLEX_GATEWAY), //
    EVENT(Constants.EVENT_GATEWAY);

    public String name;

    BPMNGatewayType(String name) {
        this.name = name;
    }

    private static class Constants {
        public static final String GATEWAY = "gateway";
        public static final String EXCLUSIVE_GATEWAY = "exclusiveGateway";
        public static final String INCLUSIVE_GATEWAY = "inclusiveGateway";
        public static final String EVENT_GATEWAY = "eventGateway";
        public static final String PARALLEL_GATEWAY = "parallelGateway";
        public static final String COMPLEX_GATEWAY = "complexGateway";
    }

    public String getName() {
        return name;
    }

}
