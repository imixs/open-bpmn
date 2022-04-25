/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Message#getItemRef <em>Item Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Message#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getMessage()
 * @model extendedMetaData="name='tMessage' kind='elementOnly'"
 * @generated
 */
public interface Message extends RootElement {
    /**
     * Returns the value of the '<em><b>Item Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Ref</em>' reference.
     * @see #setItemRef(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMessage_ItemRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='itemRef'"
     * @generated
     */
    ItemDefinition getItemRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Message#getItemRef <em>Item Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Ref</em>' reference.
     * @see #getItemRef()
     * @generated
     */
    void setItemRef(ItemDefinition value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMessage_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Message#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Message
