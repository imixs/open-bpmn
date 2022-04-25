/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Signal#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Signal#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSignal()
 * @model extendedMetaData="name='tSignal' kind='elementOnly'"
 * @generated
 */
public interface Signal extends RootElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSignal_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Signal#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Structure Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure Ref</em>' reference.
     * @see #setStructureRef(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getSignal_StructureRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='structureRef'"
     * @generated
     */
    ItemDefinition getStructureRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Signal#getStructureRef <em>Structure Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure Ref</em>' reference.
     * @see #getStructureRef()
     * @generated
     */
    void setStructureRef(ItemDefinition value);

} // Signal
