/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ResourceParameterBinding#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ResourceParameterBinding#getParameterRef <em>Parameter Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameterBinding()
 * @model extendedMetaData="name='tResourceParameterBinding' kind='elementOnly'"
 * @generated
 */
public interface ResourceParameterBinding extends BaseElement {
    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameterBinding_Expression()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ResourceParameterBinding#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Parameter Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Ref</em>' reference.
     * @see #setParameterRef(ResourceParameter)
     * @see org.eclipse.bpmn2.Bpmn2Package#getResourceParameterBinding_ParameterRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='parameterRef'"
     * @generated
     */
    ResourceParameter getParameterRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ResourceParameterBinding#getParameterRef <em>Parameter Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter Ref</em>' reference.
     * @see #getParameterRef()
     * @generated
     */
    void setParameterRef(ResourceParameter value);

} // ResourceParameterBinding
