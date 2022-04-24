/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent()
 * @model
 * @generated
 */
public interface StartEvent extends CatchEvent {
    /**
     * Returns the value of the '<em><b>Is Interrupting</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Interrupting</em>' attribute.
     * @see #setIsInterrupting(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getStartEvent_IsInterrupting()
     * @model default="true" ordered="false"
     * @generated
     */
    boolean isIsInterrupting();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.StartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Interrupting</em>' attribute.
     * @see #isIsInterrupting()
     * @generated
     */
    void setIsInterrupting(boolean value);

} // StartEvent
