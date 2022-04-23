/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.ExtensionDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn2.ExtensionDefinition#getExtensionAttributeDefinitions <em>Extension Attribute Definitions</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getExtensionDefinition()
 * @model
 * @generated
 */
public interface ExtensionDefinition extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionDefinition_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.ExtensionDefinition#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Extension Attribute Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.imixs.bpmn2.ExtensionAttributeDefinition}.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getExtensionDefinition <em>Extension Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension Attribute Definitions</em>' containment reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionDefinition_ExtensionAttributeDefinitions()
     * @see org.imixs.bpmn2.ExtensionAttributeDefinition#getExtensionDefinition
     * @model opposite="extensionDefinition" containment="true" transient="true" derived="true" ordered="false"
     * @generated
     */
    EList<ExtensionAttributeDefinition> getExtensionAttributeDefinitions();

} // ExtensionDefinition
