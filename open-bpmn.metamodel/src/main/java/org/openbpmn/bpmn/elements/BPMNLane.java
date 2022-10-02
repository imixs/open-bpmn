package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.List;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * The BPMNLane object represents a lane in a laneset contianed by a process
 * <p>
 * e.g.:
 * <p>
 * 
 * <pre>{@code  
 *     <bpmn2:process definitionalCollaborationRef="collaboration_1" id=
"process_1" name="Process 1" processType="Private">
        <bpmn2:laneSet id="laneset_1" name="Lane Set 1">
            <bpmn2:lane id="lane_1" name="Lane 1">
                <bpmn2:flowNodeRef>task_1</bpmn2:flowNodeRef>
            </bpmn2:lane>
        </bpmn2:laneSet>
        <bpmn2:task id="task_1" name="Task"/>
    </bpmn2:process>
 * }
 * </pre>
 * </p>
 * A dc:Bounds element is part of a bpmndi:BPMNShape or bpmndi:BPMNLabel
 * 
 * @author rsoika
 *
 */
public class BPMNLane extends BPMNBaseElement {

    /**
     * Creates a BPMNLane out of the bpmn elemnet
     * <p>
     * < <bpmn2:lane id="lane_1" name="Lane 1">
     * </p>
     * 
     * @param width
     * @throws BPMNMissingElementException
     */
    public BPMNLane(BPMNModel model, Element node) {
        super(model, node);

    }

    /**
     * This method insets a given BPMNFlowElment to this lane
     * <p>
     * {@code
     *  <bpmn2:laneSet id="LaneSet_1" name="Lane Set 1">
          <bpmn2:lane id="Lane_1" name="Lane 1">
             <bpmn2:flowNodeRef>StartEvent_4</bpmn2:flowNodeRef>
        }
     * 
     * @param element
     * @param laneId
     * @throws BPMNInvalidReferenceException
     */
    public void insert(BPMNFlowElement bpmnFlowEement) throws BPMNInvalidReferenceException {
        // append flowNodeRef
        Element flowNodeRef = model.createElement(BPMNNS.BPMN2, "flowNodeRef");
        Text textNode = this.getDoc().createTextNode(bpmnFlowEement.getId());
        flowNodeRef.appendChild(textNode);
        this.getElementNode().appendChild(flowNodeRef);
    }

    /**
     * Returns true if the given BPMNFlow Element is part of this lane
     * 
     * @param element a BPMNFlowElement
     * @return
     */
    public boolean contains(BPMNFlowElement bpmnElement) {
        // get all bpmn2:flowNodeRef
        List<Element> refs = BPMNModel.findChildNodesByName(this.getElementNode(),
                BPMNNS.BPMN2.prefix + ":flowNodeRef");
        for (Element element : refs) {
            if (bpmnElement.getId().equals(element.getTextContent())) {
                return true;
            }
        }
        // not found
        return false;
    }

    /**
     * Returns a list of a all BPMNFlowElement IDs contained by this lane
     * 
     * @return - list of ids
     */
    public List<String> getFlowElementIDs() {
        List<String> result = new ArrayList<String>();
        List<Element> refs = BPMNModel.findChildNodesByName(this.getElementNode(),
                BPMNNS.BPMN2.prefix + ":flowNodeRef");
        for (Element element : refs) {
            result.add(element.getTextContent());
        }
        return result;
    }

}
