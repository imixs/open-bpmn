package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class BPMNSequenceFlow extends BPMNBaseElement {

    protected String sourceRef = null;
    protected String targetRef = null;
    protected List<BPMNPoint> wayPoints=null;
    public BPMNSequenceFlow(Node node) {
        super(node);
        
        wayPoints=new ArrayList<BPMNPoint>();
    }

    public String getSourceRef() {
        return sourceRef;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public String getTargetRef() {
        return targetRef;
    }

    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }

    public List<BPMNPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<BPMNPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }
    

}
