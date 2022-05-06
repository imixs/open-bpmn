package org.openbpmn.bpmn;

public enum BPMNTaskType {
   
        TASK(Constants.TASK), //
        SEND(Constants.SEND_TASK), //
        SCRIPT(Constants.SCRIPT_TASK), //
        SERVICE(Constants.SERVICE_TASK), //
        USER(Constants.USER_TASK), //
        MANUAL(Constants.MANUAL_TASK);

        public String name;

        BPMNTaskType(String name) {
            this.name = name;
        }

        private static class Constants {
            public static final String USER_TASK = "userTask";
            public static final String SCRIPT_TASK = "scriptTask";
            public static final String SERVICE_TASK = "serviceTask";
            public static final String SEND_TASK = "sendTask";
            public static final String MANUAL_TASK = "manualTask";
            public static final String TASK = "task";
        }

        public String getName() {
            return name;
        }
    }


