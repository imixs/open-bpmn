package org.openbpmn.bpmn.elements;

import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

public class SequenceFlow extends BPMNElementEdge {
    protected BPMNProcess bpmnProcess = null;
    protected String conditionExpression = null;

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
        if (conditionExpression == null && this.hasChildNode(BPMNNS.BPMN2, "conditionExpression")) {
            // lazy loading of content
            conditionExpression = this.getChildNodeContent(BPMNNS.BPMN2, "conditionExpression");
        }
        return conditionExpression;
    }

    /**
     * Set the new bpmn2:conditionExpression content for this SequenceFlow.
     * <p>
     * If the given expression is null or blank, the method will remove an existing
     * bpmn2:conditionExpression.
     * 
     * The method also updates the default attribute for an optional Gateway and
     * returns true if the default state has changed.
     * 
     * @param String expression
     * @return true if the default state has changed
     * @throws BPMNModelException
     */
    public boolean setConditionExpression(String expression) throws BPMNModelException {
        Element expressionNode = null;
        if (expression != null && !expression.isEmpty()) {
            expressionNode = this.setChildNodeContent(BPMNNS.BPMN2, "conditionExpression",
                    expression, true);
            // xsi:type="bpmn2:tFormalExpression" id="FormalExpression_1"
            expressionNode.setAttribute("xsi:type", "bpmn2:tFormalExpression");
            expressionNode.setAttribute("id", BPMNModel.generateShortID("formalExpression"));
        } else {
            this.deleteChildNodesByName("conditionExpression");

        }

        this.conditionExpression = expression;

        // now we can update the default state for this sequenceflow...
        return this.updateDefault();
    }

    /**
     * This method updates the default state for an optional connected Gateway....
     * <p>
     * The default state is set in case only one of the outgoing sequence flows has
     * no condition. If more than one sequenceFlow have an empty condition, than no
     * default can be set!
     * <p>
     * For ParallelGateways a default is not possible
     * 
     * See also issue #176
     * 
     * @return true in case the default state has changed.
     */
    private boolean updateDefault() {
        // first find the Source element
        BPMNElementNode sourceGateway = this.getSourceElement();
        if (sourceGateway != null && sourceGateway instanceof Gateway) {
            String defaultFlowID = "";
            if (!BPMNTypes.PARALLEL_GATEWAY.equals(sourceGateway.getType())) {
                String oldDefaultFlow = sourceGateway.getAttribute("default");
                Set<SequenceFlow> sequenceFlows = sourceGateway.getOutgoingSequenceFlows();
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    String condition = sequenceFlow.getConditionExpression();
                    if ((condition == null || condition.isEmpty())) {
                        // did we already found a default?
                        if (defaultFlowID.isEmpty()) {
                            // no
                            defaultFlowID = sequenceFlow.getId();
                        } else {
                            // yes - no default can be computed at all!
                            defaultFlowID = "";
                            break;
                        }
                    }
                }
                sourceGateway.setAttribute("default", defaultFlowID);
                return (!defaultFlowID.equals(oldDefaultFlow));
            }
        }
        return false;
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
