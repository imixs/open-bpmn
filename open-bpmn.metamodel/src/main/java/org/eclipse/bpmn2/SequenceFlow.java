/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.SequenceFlow#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.SequenceFlow#isIsImmediate <em>Is Immediate</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.SequenceFlow#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.SequenceFlow#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSequenceFlow()
 * @model
 * @generated
 */
public interface SequenceFlow extends FlowElement {
    /**
     * Returns the value of the '<em><b>Condition Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition Expression</em>' containment reference.
     * @see #setConditionExpression(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSequenceFlow_ConditionExpression()
     * @model containment="true" ordered="false"
     * @generated
     */
    Expression getConditionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SequenceFlow#getConditionExpression <em>Condition Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition Expression</em>' containment reference.
     * @see #getConditionExpression()
     * @generated
     */
    void setConditionExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Is Immediate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Immediate</em>' attribute.
     * @see #setIsImmediate(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSequenceFlow_IsImmediate()
     * @model ordered="false"
     * @generated
     */
    boolean isIsImmediate();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SequenceFlow#isIsImmediate <em>Is Immediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Immediate</em>' attribute.
     * @see #isIsImmediate()
     * @generated
     */
    void setIsImmediate(boolean value);

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.FlowNode#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' reference.
     * @see #setSourceRef(FlowNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSequenceFlow_SourceRef()
     * @see org.eclipse.bpmn2.FlowNode#getOutgoing
     * @model opposite="outgoing" resolveProxies="false" required="true" ordered="false"
     * @generated
     */
    FlowNode getSourceRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SequenceFlow#getSourceRef <em>Source Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' reference.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(FlowNode value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.FlowNode#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' reference.
     * @see #setTargetRef(FlowNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSequenceFlow_TargetRef()
     * @see org.eclipse.bpmn2.FlowNode#getIncoming
     * @model opposite="incoming" resolveProxies="false" required="true" ordered="false"
     * @generated
     */
    FlowNode getTargetRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SequenceFlow#getTargetRef <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' reference.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(FlowNode value);

} // SequenceFlow
