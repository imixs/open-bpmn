/**
 */
package org.openbpmn.bpmn2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.BaseElement#getId <em>Id</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.BaseElement#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getBaseElement()
 * @model abstract="true"
 * @generated
 */
public interface BaseElement extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getBaseElement_Id()
     * @model id="true" ordered="false"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.BaseElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.bpmn2.Documentation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' containment reference list.
     * @see org.openbpmn.bpmn2.Bpmn2Package#getBaseElement_Documentation()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Documentation> getDocumentation();

} // BaseElement
