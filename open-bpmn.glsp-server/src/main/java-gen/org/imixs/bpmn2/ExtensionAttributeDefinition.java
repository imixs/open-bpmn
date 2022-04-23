/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getType <em>Type</em>}</li>
 *   <li>{@link org.imixs.bpmn2.ExtensionAttributeDefinition#isIsReference <em>Is Reference</em>}</li>
 *   <li>{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getExtensionDefinition <em>Extension Definition</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getExtensionAttributeDefinition()
 * @model
 * @generated
 */
public interface ExtensionAttributeDefinition extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionAttributeDefinition_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionAttributeDefinition_Type()
     * @model
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Is Reference</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Reference</em>' attribute.
     * @see #setIsReference(boolean)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionAttributeDefinition_IsReference()
     * @model
     * @generated
     */
    boolean isIsReference();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.ExtensionAttributeDefinition#isIsReference <em>Is Reference</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Reference</em>' attribute.
     * @see #isIsReference()
     * @generated
     */
    void setIsReference(boolean value);

    /**
     * Returns the value of the '<em><b>Extension Definition</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn2.ExtensionDefinition#getExtensionAttributeDefinitions <em>Extension Attribute Definitions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension Definition</em>' container reference.
     * @see #setExtensionDefinition(ExtensionDefinition)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtensionAttributeDefinition_ExtensionDefinition()
     * @see org.imixs.bpmn2.ExtensionDefinition#getExtensionAttributeDefinitions
     * @model opposite="extensionAttributeDefinitions" resolveProxies="false" required="true" derived="true" ordered="false"
     * @generated
     */
    ExtensionDefinition getExtensionDefinition();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.ExtensionAttributeDefinition#getExtensionDefinition <em>Extension Definition</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extension Definition</em>' container reference.
     * @see #getExtensionDefinition()
     * @generated
     */
    void setExtensionDefinition(ExtensionDefinition value);

} // ExtensionAttributeDefinition
