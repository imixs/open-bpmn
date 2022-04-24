/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inclusive Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.InclusiveGateway#getDefault <em>Default</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getInclusiveGateway()
 * @model extendedMetaData="name='tInclusiveGateway' kind='elementOnly'"
 * @generated
 */
public interface InclusiveGateway extends Gateway {
    /**
     * Returns the value of the '<em><b>Default</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' reference.
     * @see #setDefault(SequenceFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getInclusiveGateway_Default()
     * @model resolveProxies="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='default'"
     * @generated
     */
    SequenceFlow getDefault();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.InclusiveGateway#getDefault <em>Default</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' reference.
     * @see #getDefault()
     * @generated
     */
    void setDefault(SequenceFlow value);

} // InclusiveGateway
