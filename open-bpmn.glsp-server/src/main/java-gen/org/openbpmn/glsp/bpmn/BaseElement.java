/**
 */
package org.openbpmn.glsp.bpmn;

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
 *   <li>{@link org.openbpmn.glsp.bpmn.BaseElement#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.BaseElement#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBaseElement()
 * @model
 * @generated
 */
public interface BaseElement extends GNode {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBaseElement_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.BaseElement#getName <em>Name</em>}' attribute.
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
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBaseElement_Documentation()
     * @model
     * @generated
     */
    String getDocumentation();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.BaseElement#getDocumentation <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Documentation</em>' attribute.
     * @see #getDocumentation()
     * @generated
     */
    void setDocumentation(String value);

} // BaseElement
