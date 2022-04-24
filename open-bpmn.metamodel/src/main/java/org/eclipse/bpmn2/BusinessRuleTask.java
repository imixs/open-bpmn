/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Rule Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.BusinessRuleTask#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask()
 * @model extendedMetaData="name='tBusinessRuleTask' kind='elementOnly'"
 * @generated
 */
public interface BusinessRuleTask extends Task {
    /**
     * Returns the value of the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation</em>' attribute.
     * @see #setImplementation(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getBusinessRuleTask_Implementation()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='implementation'"
     * @generated
     */
    String getImplementation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.BusinessRuleTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation</em>' attribute.
     * @see #getImplementation()
     * @generated
     */
    void setImplementation(String value);

} // BusinessRuleTask
