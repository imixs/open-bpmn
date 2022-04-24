/**
 */
package org.eclipse.bpmn2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.SubProcess#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.SubProcess#isTriggeredByEvent <em>Triggered By Event</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSubProcess()
 * @model extendedMetaData="name='tSubProcess' kind='elementOnly'"
 * @generated
 */
public interface SubProcess extends Activity, FlowElementsContainer, InteractionNode {
    /**
     * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Artifact}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifacts</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getSubProcess_Artifacts()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Artifact> getArtifacts();

    /**
     * Returns the value of the '<em><b>Triggered By Event</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Triggered By Event</em>' attribute.
     * @see #setTriggeredByEvent(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSubProcess_TriggeredByEvent()
     * @model default="false" ordered="false"
     * @generated
     */
    boolean isTriggeredByEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.SubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Triggered By Event</em>' attribute.
     * @see #isTriggeredByEvent()
     * @generated
     */
    void setTriggeredByEvent(boolean value);

} // SubProcess
