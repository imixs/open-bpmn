package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

public class SequenceFlow extends BPMNElementEdge {
    protected BPMNProcess bpmnProcess = null;

    public SequenceFlow(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess) {
        super(model, node, _type);
        this.bpmnProcess = _bpmnProcess;
    }

    /**
     * Returns the BPMN Process this element belongs to.
     * 
     * @return
     */
    public BPMNProcess getProcess() {
        return bpmnProcess;
    }

    /**
     * Returns the conditionExpression if exits or null if no conditionExpression is
     * defined.
     * 
     * @return String - can be null
     */
    public String getConditionExpression() {
        return this.getChildNodeContent("conditionExpression");
    }

    /**
     * Set the new bpmn2:conditionExpression content for this SequenceFlow.
     * <p>
     * If the given expression is null or blank, the method will remove an existing
     * bpmn2:conditionExpression.
     * 
     * @param String expression
     * @return the new child node or null if no expression was set.
     * @throws BPMNModelException
     */
    public Element setConditionExpression(String expression) throws BPMNModelException {
        if (expression != null && !expression.isEmpty()) {
            Element expressionNode = this.setChildNodeContent("conditionExpression", expression);

            // xsi:type="bpmn2:tFormalExpression" id="FormalExpression_1"
            expressionNode.setAttribute("xsi:type", "bpmn2:tFormalExpression");
            expressionNode.setAttribute("id", BPMNModel.generateShortID("FormalExpression"));

            return expressionNode;
        } else {
            this.deleteChildNodesByName("conditionExpression");
            return null;
        }
    }

    /**
     * This method returns true if this SequenceFlow is the default flow of a
     * Gateway Source element
     * 
     * @return
     */
    public boolean isDefault() {
        // first find the Source element
        BPMNElementNode sourceGateway = this.getSourceElement();
        if (sourceGateway != null && sourceGateway instanceof Gateway) {
            // check if the 'default' attribute matches this sequenceFlowID
            return this.getId().equals(sourceGateway.getAttribute("default"));
        }
        // the source element is not a gateway
        return false;

    }
}
