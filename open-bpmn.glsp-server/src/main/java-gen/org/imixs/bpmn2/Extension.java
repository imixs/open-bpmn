/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.Extension#isMustUnderstand <em>Must Understand</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Extension#getDefinition <em>Definition</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getExtension()
 * @model
 * @generated
 */
public interface Extension extends EObject {
    /**
     * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Must Understand</em>' attribute.
     * @see #setMustUnderstand(boolean)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtension_MustUnderstand()
     * @model
     * @generated
     */
    boolean isMustUnderstand();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Extension#isMustUnderstand <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Must Understand</em>' attribute.
     * @see #isMustUnderstand()
     * @generated
     */
    void setMustUnderstand(boolean value);

    /**
     * Returns the value of the '<em><b>Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definition</em>' containment reference.
     * @see #setDefinition(ExtensionDefinition)
     * @see org.imixs.bpmn2.Bpmn2Package#getExtension_Definition()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    ExtensionDefinition getDefinition();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Extension#getDefinition <em>Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition</em>' containment reference.
     * @see #getDefinition()
     * @generated
     */
    void setDefinition(ExtensionDefinition value);

} // Extension
