package org.openbpmn.bpmn.elements;

import java.util.LinkedHashSet;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelUtil;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The BPMNLane object represents a Collaboration element
 * <p>
 * e.g.:
 * <p>
 * 
 * <pre>{@code  
<bpmn2:collaboration id="collaboration_1">
    <bpmn2:participant name="Participant" processRef="participant_1 id="6f8a52"/>
    <bpmn2:messageFlow messageRef="Message_1" name="Message Flow 1" sourceRef=
"a6da20e" targetRef="51d6a147" id="a5038"/>
</bpmn2:collaboration>
    * }
 * </pre>
 * </p>
 * 
 * @author rsoika
 *
 */
public class CollaborationElement extends BPMNElementNode {
    Element bpmnPlane = null;

    public CollaborationElement(BPMNModel model, Element node) {
        super(model, node);
    }

    /**
     * This method returns the bpmnPlane for the collaboration element if one
     * exists.
     * 
     * @return
     */
    public Element getBPMNPlane() {
        if (bpmnPlane == null) {
            // find COllaboration plane.
            bpmnPlane = BPMNModelUtil.findBPMNPlane(model, this.getId());
        }
        return bpmnPlane;
    }

    @Override
    public double getDefaultWidth() {
        return 0.0;
    }

    @Override
    public double getDefaultHeight() {
        return 0.0;
    }

    /**
     * This helper method loads the MessageFlow elements from a collaboration
     * diagram located in the 'bpmn2:collaboration' section. This section is
     * optional.
     * 
     * @throws BPMNModelException
     * 
     */
    public Set<MessageFlow> loadMessageFlowList() throws BPMNModelException {
        LinkedHashSet<MessageFlow> messageFlows = new LinkedHashSet<MessageFlow>();
        // NodeList collaborationNodeList =
        // definitions.getElementsByTagName(getPrefix(BPMNNS.BPMN2) + "collaboration");

        // now find all messageFlows...
        // NodeList messageFlowList = collaborationElement
        // .getElementsByTagName(getPrefix(BPMNNS.BPMN2) + BPMNTypes.MESSAGE_FLOW);
        NodeList messageFlowList = model.findElementsByName(this.elementNode, BPMNNS.BPMN2, BPMNTypes.MESSAGE_FLOW);
        logger.fine("..found " + messageFlowList.getLength() + " messageFlows");
        for (int i = 0; i < messageFlowList.getLength(); i++) {
            Element item = (Element) messageFlowList.item(i);
            MessageFlow messageFlow = new MessageFlow(model, item);
            messageFlows.add(messageFlow);
        }

        return messageFlows;
    }

    public void removeParticipant(Participant participant) {
        this.elementNode.removeChild(participant.getElementNode());
    }
}
