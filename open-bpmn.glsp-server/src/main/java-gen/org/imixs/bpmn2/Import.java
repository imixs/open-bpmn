/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.Import#getImportType <em>Import Type</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Import#getLocation <em>Location</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Import#getNamespace <em>Namespace</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getImport()
 * @model
 * @generated
 */
public interface Import extends EObject {
    /**
     * Returns the value of the '<em><b>Import Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Import Type</em>' attribute.
     * @see #setImportType(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getImport_ImportType()
     * @model
     * @generated
     */
    String getImportType();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Import#getImportType <em>Import Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Import Type</em>' attribute.
     * @see #getImportType()
     * @generated
     */
    void setImportType(String value);

    /**
     * Returns the value of the '<em><b>Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location</em>' attribute.
     * @see #setLocation(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getImport_Location()
     * @model
     * @generated
     */
    String getLocation();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Import#getLocation <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' attribute.
     * @see #getLocation()
     * @generated
     */
    void setLocation(String value);

    /**
     * Returns the value of the '<em><b>Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace</em>' attribute.
     * @see #setNamespace(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getImport_Namespace()
     * @model
     * @generated
     */
    String getNamespace();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Import#getNamespace <em>Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespace</em>' attribute.
     * @see #getNamespace()
     * @generated
     */
    void setNamespace(String value);

} // Import
