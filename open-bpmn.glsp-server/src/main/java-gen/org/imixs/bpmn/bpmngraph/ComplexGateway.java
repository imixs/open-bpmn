/**
 */
package org.imixs.bpmn.bpmngraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complex Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.ComplexGateway#getDefaultSequenceFlow <em>Default Sequence Flow</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getComplexGateway()
 * @model
 * @generated
 */
public interface ComplexGateway extends Gateway {
    /**
     * Returns the value of the '<em><b>Default Sequence Flow</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Sequence Flow</em>' reference.
     * @see #setDefaultSequenceFlow(SequenceFlow)
     * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getComplexGateway_DefaultSequenceFlow()
     * @model resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='defaultSequenceFlow'"
     * @generated
     */
    SequenceFlow getDefaultSequenceFlow();

    /**
     * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.ComplexGateway#getDefaultSequenceFlow <em>Default Sequence Flow</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Sequence Flow</em>' reference.
     * @see #getDefaultSequenceFlow()
     * @generated
     */
    void setDefaultSequenceFlow(SequenceFlow value);

} // ComplexGateway
