package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A BPMNParticipant is a container for a BPMNProcess. A BPMNParticipant can
 * contain a bpmnShape element which is in this case a BPMN Pool. If a
 * BPMNParticipant does not contain a bpmnShape (Pool) this indicates that this
 * is the public default process
 * 
 * @author rsoika
 *
 */
public class Participant extends BPMNElementNode {

    public final static double MIN_WIDTH = 300.0;
    public final static double MIN_HEIGHT = 100.0;
    public final static double DEFAULT_WIDTH = 800.0;
    public final static double DEFAULT_HEIGHT = 210.0;
    public final static double POOL_OFFSET = 30.0;

    protected BPMNLabel label = null;
    private String processRef = null;

    /**
     * Creates a new participant from a element node. The process is mandatory and
     * defines the reference to an existing process within the current model.
     * 
     * @param model
     * @param node
     * @param processRef
     * @throws BPMNModelException
     */
    public Participant(BPMNModel model, Element node, BPMNProcess process) throws BPMNModelException {
        super(model, node);
        setBpmnProcess(process);
        // find the BPMNShape element - suche in allen BPMNPlanes
        bpmnShape = findBPMNShapeInAllPlanes(model, getId());
    }

    /**
     * Searches for a BPMNShape element with the given participant ID across all
     * BPMNPlane elements in the BPMN document. This method is necessary because
     * participants can be referenced from different diagram planes (main
     * collaboration diagram vs. subprocess diagrams).
     * 
     * @param model         the BPMNModel containing the document to search
     * @param participantId the ID of the participant to find the corresponding
     *                      BPMNShape for
     * @return the BPMNShape element that references the participant ID, or null if
     *         not found
     */
    private Element findBPMNShapeInAllPlanes(BPMNModel model, String participantId) {
        // Search all BPMNPlane elements in the entire document
        NodeList allPlanes = model.getDoc().getElementsByTagNameNS(
                model.getUri(BPMNNS.BPMNDI), "BPMNPlane");

        // Search each BPMNPlane for the BPMNShape
        for (int p = 0; p < allPlanes.getLength(); p++) {
            Element plane = (Element) allPlanes.item(p);
            Element shape = findBPMNShapeInPlane(model, plane, participantId);
            if (shape != null) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Searches for a BPMNShape element within a specific BPMNPlane that references
     * the given participant ID through its bpmnElement attribute.
     * 
     * @param model         the BPMNModel for accessing namespace prefixes
     * @param plane         the BPMNPlane element to search within
     * @param participantId the participant ID to match against BPMNShape
     *                      bpmnElement attributes
     * @return the matching BPMNShape element, or null if no match is found in this
     *         plane
     */
    private Element findBPMNShapeInPlane(BPMNModel model, Element plane, String participantId) {
        String fullNodeName = model.getPrefix(BPMNNS.BPMNDI) + "BPMNShape";
        NodeList childList = plane.getChildNodes();

        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (fullNodeName.equals(child.getNodeName()) && child.hasAttributes()) {
                NamedNodeMap attributesMap = child.getAttributes();
                for (int j = 0; j < attributesMap.getLength(); j++) {
                    Node attr = attributesMap.item(j);
                    if ("bpmnElement".equals(attr.getNodeName()) &&
                            participantId.equals(attr.getNodeValue())) {
                        return (Element) child;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Updates the name of the participant and also the corresponding process name.
     */
    @Override
    public void setName(String name) {
        super.setName(name);
        // update name of corresponding process
        this.bpmnProcess.setName(name);
    }

    public boolean hasPool() {
        return (bpmnShape != null);
    }

    /**
     * Returns the initialized BPMNProcess of this participant or returns null if no
     * process exists
     * 
     * @return BPMNProcess
     */
    public BPMNProcess openProcess() {
        try {
            return model.openProcess(processRef);
        } catch (BPMNModelException e) {
            BPMNModel.getLogger().severe("Participant process '" + processRef + "' not fund!");
        }
        return null;
    }

    public String getProcessRef() {
        return processRef;
    }

    /**
     * This method updates the processRef attribute of the participant.
     * If the id is empty or null the attribute will be removed.
     * 
     * @param processRef
     */
    public void setProcessRef(String processRef) {
        this.processRef = processRef;
        if (processRef != null && !processRef.isEmpty()) {
            this.getElementNode().setAttribute("processRef", processRef);
        } else {
            this.getElementNode().removeAttribute("processRef");
        }
    }

    /**
     * This method returns the corresponding BPMNProcess ID for this Element.
     * 
     * @param bpmynElement
     * @return
     */
    @Override
    public String getProcessId() {
        return getProcessRef();
    }

    /**
     * Updates the BPMNProcessRef of the Participant
     * 
     * @return
     */
    public void setBpmnProcess(BPMNProcess _process) {
        this.bpmnProcess = _process;
        this.setProcessRef(_process.getId());
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    /**
     * Remove any embedded bpmndi:BPMNLabel element within the bpmndi:BPMNShape
     * 
     * Positioning of the label is part of the client. Any position update should
     * ignore these settings in Open-BPMN.
     */
    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);

        // remove optional BPMNLabel
        Element bpmnLabel = getModel().findChildNodeByName(this.bpmnShape, BPMNNS.BPMNDI, "BPMNLabel");
        if (bpmnLabel != null) {
            this.bpmnShape.removeChild(bpmnLabel);
        }
    }
}
