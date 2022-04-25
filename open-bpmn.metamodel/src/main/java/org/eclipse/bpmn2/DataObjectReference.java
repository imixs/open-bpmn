/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Object Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataObjectReference#getDataObjectRef <em>Data Object Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataObjectReference()
 * @model extendedMetaData="name='tDataObjectReference' kind='elementOnly'"
 * @generated
 */
public interface DataObjectReference extends FlowElement, ItemAwareElement {
    /**
     * Returns the value of the '<em><b>Data Object Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Object Ref</em>' reference.
     * @see #setDataObjectRef(DataObject)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataObjectReference_DataObjectRef()
     * @model resolveProxies="false" required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='dataObjectRef'"
     * @generated
     */
    DataObject getDataObjectRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataObjectReference#getDataObjectRef <em>Data Object Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Object Ref</em>' reference.
     * @see #getDataObjectRef()
     * @generated
     */
    void setDataObjectRef(DataObject value);

} // DataObjectReference
