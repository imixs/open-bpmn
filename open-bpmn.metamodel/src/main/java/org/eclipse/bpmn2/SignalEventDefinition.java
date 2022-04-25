/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.SignalEventDefinition#getSignalRef <em>Signal Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSignalEventDefinition()
 * @model extendedMetaData="name='tSignalEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface SignalEventDefinition extends EventDefinition {
    /**
     * Returns the value of the '<em><b>Signal Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Signal Ref</em>' reference.
     * @see #setSignalRef(Signal)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSignalEventDefinition_SignalRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='signalRef'"
     * @generated
     */
    Signal getSignalRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SignalEventDefinition#getSignalRef <em>Signal Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Signal Ref</em>' reference.
     * @see #getSignalRef()
     * @generated
     */
    void setSignalRef(Signal value);

} // SignalEventDefinition
