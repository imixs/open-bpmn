package org.openbpmn.bpmn.elements.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The BPMNElementEdge is the abstract super class for all BPMN edge types
 * (SequenceFlow, MessageFlow, Association).
 * <p>
 * The BPMNElementEdge provides general attributes like the source and target
 * ref and also the wayPoint attribute.
 * <p>
 * A BPMNElementEdge extends the AbstractBPMNElement
 * 
 * @author rsoika
 */
public abstract class BPMNElementEdge extends BPMNElement {
    private static Logger logger = Logger.getLogger(BPMNElementEdge.class.getName());
    protected String type = null;

    protected String sourceRef = null;
    protected String targetRef = null;
    protected Element bpmnEdge = null;
    protected Set<BPMNPoint> wayPoints = null;

    public BPMNElementEdge(BPMNModel model, Element node) {
        super(model, node);
    }

    public BPMNElementEdge(BPMNModel model, Element node, String _type) {
        super(model, node);
        this.type = _type;

        wayPoints = new LinkedHashSet<BPMNPoint>();

        this.sourceRef = this.getAttribute("sourceRef");
        if (sourceRef.isEmpty()) {
            logger.warning("Missing sourceRef!");
        }

        this.targetRef = this.getAttribute("targetRef");
        if (targetRef.isEmpty()) {
            logger.warning("Missing targetRef!");
        }

        // find the BPMNShape element. If not defined create a new one

        bpmnEdge = (Element) model.findBPMNPlaneElement("BPMNEdge", getId());
        if (bpmnEdge == null) {
            // create shape element
            createBPMNEdge();
        } else {
            // parse waypoints (di:waypoint)
            Set<Element> wayPoints = model.findChildNodesByName(bpmnEdge, BPMNNS.DI, "waypoint");
            for (Element wayPoint : wayPoints) {
                NamedNodeMap wayPointattributeMap = wayPoint.getAttributes();
                BPMNPoint point = new BPMNPoint(wayPointattributeMap.getNamedItem("x").getNodeValue(), //
                        wayPointattributeMap.getNamedItem("y").getNodeValue());
                getWayPoints().add(point);
            }
        }

    }

    public Element getBpmnEdge() {
        return bpmnEdge;
    }

    public void setBpmnEdge(Element bpmnEdge) {
        this.bpmnEdge = bpmnEdge;
    }

    /**
     * Returns the BPMN sub type
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set the BPMN sub type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the sourc ref ID
     * 
     * @return
     */
    public String getSourceRef() {
        return sourceRef;
    }

    /**
     * Returns the Connected BPMN source element
     * 
     * @return
     */
    public BPMNElementNode getSourceElement() {
        return this.model.findElementNodeById(sourceRef);
    }

    /**
     * This method updates the sourceReference of the edge and also updates
     * the outgoing child element of the corresponding element node
     * 
     * @param sourceRef
     */
    public void setSourceRef(String sourceRef) {

        if (sourceRef != null && sourceRef.equals(this.sourceRef)) {
            return; // no op!
        }

        // do we have an old connection?
        if (this.sourceRef != null && !this.sourceRef.isEmpty()) {
            // find old source Element and remove the outgoing connection
            BPMNElementNode oldSourceNode = this.model.findElementNodeById(this.sourceRef);
            // first remove outgoing reference list
            Set<Element> outgoingElements = model.findChildNodesByName(oldSourceNode.elementNode, BPMNNS.BPMN2,
                    "outgoing");
            Iterator<Element> iter = outgoingElements.iterator();
            while (iter.hasNext()) {
                Element child = iter.next();
                if (this.getId().equals(child.getTextContent())) {
                    oldSourceNode.getElementNode().removeChild(child);
                    break;
                }
            }
        }

        // Now update the ref id
        this.sourceRef = sourceRef;
        this.getElementNode().setAttribute("sourceRef", this.sourceRef);

        // add outgoing reference to source element
        BPMNElementNode newSourceNode = this.model.findElementNodeById(sourceRef);
        if (newSourceNode != null) {
            Element refOut = model.createElement(BPMNNS.BPMN2, "outgoing");
            refOut.setTextContent(this.getId());
            newSourceNode.getElementNode().appendChild(refOut);

            // update ref of edgeShape
            Element edgeShape = this.getBpmnEdge();
            if (edgeShape != null) {
                edgeShape.setAttribute("sourceElement", newSourceNode.getBpmnShape().getAttribute("id"));
            }
        }

    }

    /**
     * Returns the target ref id
     * 
     * @return
     */
    public String getTargetRef() {
        return targetRef;
    }

    public void setTargetRef(String targetRef) {

        if (targetRef != null && targetRef.equals(this.targetRef)) {
            return; // no op!
        }
        // do we have an old connection?
        if (this.targetRef != null && !this.targetRef.isEmpty()) {
            // find old source Element and remove the outgoing connection
            BPMNElementNode oldTargetNode = this.model.findElementNodeById(this.targetRef);
            // first remove outgoing reference list
            Set<Element> outgoingElements = model.findChildNodesByName(oldTargetNode.elementNode, BPMNNS.BPMN2,
                    "incoming");
            Iterator<Element> iter = outgoingElements.iterator();
            while (iter.hasNext()) {
                Element child = iter.next();
                if (this.getId().equals(child.getTextContent())) {
                    oldTargetNode.getElementNode().removeChild(child);
                    break;
                }
            }
        }
        // Now update the ref and the outcoing connection
        this.targetRef = targetRef;
        this.getElementNode().setAttribute("targetRef", this.targetRef);

        BPMNElementNode newTargetNode = this.model.findElementNodeById(targetRef);
        // add outgoing reference to source element
        if (newTargetNode != null) {
            Element refOut = model.createElement(BPMNNS.BPMN2, "incoming");
            refOut.setTextContent(this.getId());
            newTargetNode.getElementNode().appendChild(refOut);

            // update ref of edgeShape
            Element edgeShape = this.getBpmnEdge();
            if (edgeShape != null) {
                edgeShape.setAttribute("targetElement", newTargetNode.getBpmnShape().getAttribute("id"));
            }
        }
    }

    /**
     * Returns the Connected BPMN target element
     * 
     * @return
     */
    public BPMNElementNode getTargetElement() {
        return this.model.findElementNodeById(targetRef);
    }

    public Set<BPMNPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(Set<BPMNPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    /**
     * Adds a new waypoint to the BPMNEdge element
     * 
     * <pre>
     * {@code
     * <bpmndi:BPMNEdge id="BPMNEdge_SequenceFlow_1" 
     *      bpmnElement="SequenceFlow_1" 
     *      sourceElement="BPMNShape_StartEvent_1" 
     *      targetElement="BPMNShape_ManualTask_1"> 
     *      <di:waypoint x="138.0" y="100.0"/>
     *      <di:waypoint x="214.0" y="100.0"/> 
     *      <bpmndi:BPMNLabel id="BPMNLabel_3"/>
     * </bpmndi:BPMNEdge>
     * }
     * </pre>
     */
    public void addWayPoint(BPMNPoint wayPoint) {
        // update shape
        if (this.bpmnEdge != null) {
            Element diwayPoint = model.createElement(BPMNNS.DI, "waypoint");
            diwayPoint.setAttribute("x", wayPoint.getX() + "");
            diwayPoint.setAttribute("y", wayPoint.getY() + "");
            this.bpmnEdge.appendChild(diwayPoint);
        } else {
            logger.warning("missing bpmnShape for SequenceFlow: " + this.getId());
        }
        this.wayPoints.add(wayPoint);
    }

    /**
     * Creates the default waypoints if no waypoints are defined. This is useful in
     * case of a backend creation of edges.
     * 
     * @param wayPoint
     */
    public void addDefaultWayPoints() {
        // Waypoint exist?
        if (this.getWayPoints().size() > 0) {
            return;
        }

        BPMNElementNode sourceElement = this.getSourceElement();
        BPMNElementNode targetElement = this.getTargetElement();
        if (sourceElement != null && targetElement != null) {
            addWayPoint(sourceElement.bounds.getCenter());
            addWayPoint(targetElement.bounds.getCenter());
        }
    }

    /**
     * Removes all wayPoints form this BPMNSequenceFlow
     */
    public void clearWayPoints() {
        NodeList childList = bpmnEdge.getChildNodes();
        List<Node> deletionList = new ArrayList<Node>();
        // find all di:waypoint
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if ((model.getPrefix(BPMNNS.DI) + ":waypoint").equals(child.getNodeName()) && child.hasAttributes()) {
                // collect node....
                deletionList.add(child);
            }
        }
        // remove nodes from Edge element...
        for (Node element : deletionList) {
            bpmnEdge.removeChild(element);
        }
        // reset wayPoints
        wayPoints.clear();
    }

    /**
     * Creates a BPMNEdge node for this element
     * <p>
     * <bpmndi:BPMNBPMNEdge id="BPMNBPMNEdge_1" bpmnElement="StartEvent_1">
     */
    protected void createBPMNEdge() {
        if (bpmnEdge != null) {
            BPMNModel.getLogger().warning("bpmnShape already exits");
        }
        if (model.getBpmnPlane() == null) {
            BPMNModel.getLogger().warning("Missing bpmnPlane in current diagram context");
        }
        if (this.getId() != null) {
            bpmnEdge = model.createElement(BPMNNS.BPMNDI, "BPMNEdge");
            bpmnEdge.setAttribute("id", BPMNModel.generateShortID("BPMNEdge"));
            bpmnEdge.setAttribute("bpmnElement", this.getId());
            model.getBpmnPlane().appendChild(bpmnEdge);
        } else {
            BPMNModel.getLogger().warning("Missing ID attribute!");
        }
    }

}
