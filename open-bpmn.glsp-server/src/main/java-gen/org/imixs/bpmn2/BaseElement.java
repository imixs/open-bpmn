/**
 */
package org.imixs.bpmn2;

import org.eclipse.glsp.graph.GNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.BaseElement#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn2.BaseElement#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getBaseElement()
 * @model abstract="true"
 * @generated
 */
public interface BaseElement extends GNode {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getBaseElement_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.BaseElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' attribute.
     * @see #setDocumentation(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getBaseElement_Documentation()
     * @model
     * @generated
     */
    String getDocumentation();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.BaseElement#getDocumentation <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Documentation</em>' attribute.
     * @see #getDocumentation()
     * @generated
     */
    void setDocumentation(String value);

} // BaseElement
