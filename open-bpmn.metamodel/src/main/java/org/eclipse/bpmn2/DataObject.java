/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DataObject#isIsCollection <em>Is Collection</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDataObject()
 * @model extendedMetaData="name='tDataObject' kind='elementOnly'"
 * @generated
 */
public interface DataObject extends FlowElement, ItemAwareElement {
    /**
     * Returns the value of the '<em><b>Is Collection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Collection</em>' attribute.
     * @see #setIsCollection(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDataObject_IsCollection()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isCollection'"
     * @generated
     */
    boolean isIsCollection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DataObject#isIsCollection <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Collection</em>' attribute.
     * @see #isIsCollection()
     * @generated
     */
    void setIsCollection(boolean value);

} // DataObject
