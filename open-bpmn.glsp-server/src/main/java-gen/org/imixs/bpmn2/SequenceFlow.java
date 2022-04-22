/**
 */
package org.imixs.bpmn2;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.SequenceFlow#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.imixs.bpmn2.SequenceFlow#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.imixs.bpmn2.SequenceFlow#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getSequenceFlow()
 * @model
 * @generated
 */
public interface SequenceFlow extends GEdge, FlowElement {
    /**
     * Returns the value of the '<em><b>Condition Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition Expression</em>' containment reference.
     * @see #setConditionExpression(Expression)
     * @see org.imixs.bpmn2.Bpmn2Package#getSequenceFlow_ConditionExpression()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='conditionExpression'"
     * @generated
     */
    Expression getConditionExpression();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.SequenceFlow#getConditionExpression <em>Condition Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition Expression</em>' containment reference.
     * @see #getConditionExpression()
     * @generated
     */
    void setConditionExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn2.FlowNode#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' reference.
     * @see #setSourceRef(FlowNode)
     * @see org.imixs.bpmn2.Bpmn2Package#getSequenceFlow_SourceRef()
     * @see org.imixs.bpmn2.FlowNode#getOutgoing
     * @model opposite="outgoing" resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='sourceRef'"
     * @generated
     */
    FlowNode getSourceRef();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.SequenceFlow#getSourceRef <em>Source Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' reference.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(FlowNode value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn2.FlowNode#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' reference.
     * @see #setTargetRef(FlowNode)
     * @see org.imixs.bpmn2.Bpmn2Package#getSequenceFlow_TargetRef()
     * @see org.imixs.bpmn2.FlowNode#getIncoming
     * @model opposite="incoming" resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='targetRef'"
     * @generated
     */
    FlowNode getTargetRef();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.SequenceFlow#getTargetRef <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' reference.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(FlowNode value);

} // SequenceFlow
