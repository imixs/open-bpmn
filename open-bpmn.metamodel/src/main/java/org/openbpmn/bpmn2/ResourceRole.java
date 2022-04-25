/**
 */
package org.openbpmn.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.ResourceRole#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getResourceRole()
 * @model abstract="true"
 * @generated
 */
public interface ResourceRole extends BaseElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getResourceRole_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.ResourceRole#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // ResourceRole
