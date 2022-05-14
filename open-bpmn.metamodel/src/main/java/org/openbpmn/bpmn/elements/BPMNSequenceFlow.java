package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Node;

public class BPMNSequenceFlow extends BPMNBaseElement {
    private static Logger logger = Logger.getLogger(BPMNSequenceFlow.class.getName());

    protected String sourceRef = null;
    protected String targetRef = null;
    protected Node bpmnEdge = null;
    protected List<BPMNPoint> wayPoints=null;
    
    public BPMNSequenceFlow(BPMNModel model,Node node) {
        super(model,node);
        wayPoints=new ArrayList<BPMNPoint>();
        
       
        this.sourceRef=this.getAttribute("sourceRef");
        if (sourceRef==null || sourceRef.isEmpty()) {
            logger.warning("Missing sourceRef!");
        }
       
        this.targetRef=this.getAttribute("targetRef");
        if (targetRef==null || targetRef.isEmpty()) {
            logger.warning("Missing targetRef!");
        }
    }

    
    public Node getBpmnEdge() {
        return bpmnEdge;
    }


    public void setBpmnEdge(Node bpmnEdge) {
        this.bpmnEdge = bpmnEdge;
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
