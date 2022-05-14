package org.openbpmn.bpmn;

public enum BPMNNS {
    BPMN2("bpmn2"),//
    BPMNDI("bpmndi"), //
    DC("dc"), //
    DI("di");
    
   
    public final String prefix;

    private BPMNNS(String prefix) {
        this.prefix = prefix;
    }
}
