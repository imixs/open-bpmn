/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ad Hoc Sub Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.AdHocSubProcess#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.AdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.AdHocSubProcess#getOrdering <em>Ordering</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getAdHocSubProcess()
 * @model
 * @generated
 */
public interface AdHocSubProcess extends SubProcess {
    /**
     * Returns the value of the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Completion Condition</em>' containment reference.
     * @see #setCompletionCondition(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAdHocSubProcess_CompletionCondition()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getCompletionCondition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.AdHocSubProcess#getCompletionCondition <em>Completion Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Completion Condition</em>' containment reference.
     * @see #getCompletionCondition()
     * @generated
     */
    void setCompletionCondition(Expression value);

    /**
     * Returns the value of the '<em><b>Cancel Remaining Instances</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cancel Remaining Instances</em>' attribute.
     * @see #setCancelRemainingInstances(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAdHocSubProcess_CancelRemainingInstances()
     * @model default="true" ordered="false"
     * @generated
     */
    boolean isCancelRemainingInstances();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.AdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cancel Remaining Instances</em>' attribute.
     * @see #isCancelRemainingInstances()
     * @generated
     */
    void setCancelRemainingInstances(boolean value);

    /**
     * Returns the value of the '<em><b>Ordering</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.AdHocOrdering}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ordering</em>' attribute.
     * @see org.eclipse.bpmn2.AdHocOrdering
     * @see #setOrdering(AdHocOrdering)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAdHocSubProcess_Ordering()
     * @model ordered="false"
     * @generated
     */
    AdHocOrdering getOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.AdHocSubProcess#getOrdering <em>Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ordering</em>' attribute.
     * @see org.eclipse.bpmn2.AdHocOrdering
     * @see #getOrdering()
     * @generated
     */
    void setOrdering(AdHocOrdering value);

} // AdHocSubProcess
