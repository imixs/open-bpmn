/**
 */
package org.openbpmn.glsp.bpmn;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Element GNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.FlowElementGNode#getSymbol <em>Symbol</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getFlowElementGNode()
 * @model
 * @generated
 */
public interface FlowElementGNode extends BaseElementGNode {
    /**
     * Returns the value of the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Symbol</em>' attribute.
     * @see #setSymbol(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getFlowElementGNode_Symbol()
     * @model
     * @generated
     */
    String getSymbol();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.FlowElementGNode#getSymbol <em>Symbol</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Symbol</em>' attribute.
     * @see #getSymbol()
     * @generated
     */
    void setSymbol(String value);

} // FlowElementGNode
