package org.openbpmn.bpmn.elements;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class MessageFlow extends BPMNElementEdge {

    private static Logger logger = Logger.getLogger(MessageFlow.class.getName());

    public MessageFlow(BPMNModel model, Element node) {
        super(model, node);

        this.type = BPMNTypes.MESSAGE_FLOW;

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
}
