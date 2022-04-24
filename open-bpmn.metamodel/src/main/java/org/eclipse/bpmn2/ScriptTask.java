/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ScriptTask#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ScriptTask#getScriptFormat <em>Script Format</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask()
 * @model
 * @generated
 */
public interface ScriptTask extends Task {
    /**
     * Returns the value of the '<em><b>Script</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script</em>' attribute.
     * @see #setScript(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask_Script()
     * @model required="true" ordered="false"
     * @generated
     */
    String getScript();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ScriptTask#getScript <em>Script</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script</em>' attribute.
     * @see #getScript()
     * @generated
     */
    void setScript(String value);

    /**
     * Returns the value of the '<em><b>Script Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script Format</em>' attribute.
     * @see #setScriptFormat(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getScriptTask_ScriptFormat()
     * @model ordered="false"
     * @generated
     */
    String getScriptFormat();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ScriptTask#getScriptFormat <em>Script Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script Format</em>' attribute.
     * @see #getScriptFormat()
     * @generated
     */
    void setScriptFormat(String value);

} // ScriptTask
