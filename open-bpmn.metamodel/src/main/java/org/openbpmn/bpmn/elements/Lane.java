package org.openbpmn.bpmn.elements;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
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
public class Lane extends BPMNElementNode {

    private static Logger logger = Logger.getLogger(Lane.class.getName());

    public final static double MIN_WIDTH = 270.0;
    public final static double MIN_HEIGHT = 100.0;
    public final static double DEFAULT_WIDTH = 470.0;
    public final static double DEFAULT_HEIGHT = 150.0;

    /**
     * Creates a BPMNLane out of the bpmn elemnet
     * <p>
     * < <bpmn2:lane id="lane_1" name="Lane 1">
     * </p>
     * 
     * @param width
     * @throws BPMNMissingElementException
     */
    public Lane(BPMNModel model, Element node) {
        super(model, node);
        // find the BPMNShape element.
        bpmnShape = (Element) model.findBPMNPlaneElement("BPMNShape", getId());
    }

    /**
     * Returns the BPMNProcess the lance is a member of.
     * 
     * To find the BPMNProcess of a lane we need to first fetch the processID form
     * the outer laneset.
     */
    @Override
    public BPMNProcess getBpmnProcess() {
        String processID = getProcessId();
        try {
            return this.model.openProcess(processID);
        } catch (BPMNModelException e) {
            logger.severe("Faild to resolve BPMNProcess for lane '" + this.getId() + "!' ");
        }
        return null;
    }

    /**
     * This method returns the corresponding BPMNProcess ID for this Element.
     * 
     * For a lane the process id is the parent of the parent lane set
     * 
     * @param bpmynElement
     * @return
     */
    @Override
    public String getProcessId() {
        Element laneSet = (Element) getElementNode().getParentNode();
        // now get the parent node of the laneset which is the process
        Element parentProcess = (Element) laneSet.getParentNode();
        return parentProcess.getAttribute("id");
    }

    /**
     * This method returns the LaneSet element node from the bpmn2:process
     * 
     * @return
     */
    public Element getLaneSet() {
        Element laneSet = (Element) this.getElementNode().getParentNode();
        return laneSet;
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
    public void insert(BPMNElementNode bpmnEementNode) throws BPMNInvalidReferenceException {
        // append flowNodeRef
        Element flowNodeRef = model.createElement(BPMNNS.BPMN2, "flowNodeRef");
        Text textNode = this.getDoc().createTextNode(bpmnEementNode.getId());
        flowNodeRef.appendChild(textNode);
        this.getElementNode().appendChild(flowNodeRef);
    }

    /**
     * Returns true if the given BPMNFlow Element is part of this lane
     * 
     * @param element a BPMNFlowElement
     * @return
     */
    public boolean contains(BPMNElementNode bpmnElement) {
        // get all bpmn2:flowNodeRef
        Set<Element> refs = BPMNModel.findChildNodesByName(this.getElementNode(),
                getModel().getPrefix(BPMNNS.BPMN2) + ":flowNodeRef");
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
    public Set<String> getFlowElementIDs() {
        Set<String> result = new LinkedHashSet<String>();
        Set<Element> refs = BPMNModel.findChildNodesByName(this.getElementNode(),
                getModel().getPrefix(BPMNNS.BPMN2) + ":flowNodeRef");
        for (Element element : refs) {
            result.add(element.getTextContent());
        }
        return result;
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }

}
