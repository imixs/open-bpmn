/**
 */
package org.openbpmn.bpmn2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.DocumentRoot#getRootElement <em>Root Element</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getDocumentRoot()
 * @model
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Root Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Element</em>' containment reference.
     * @see #setRootElement(RootElement)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDocumentRoot_RootElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    RootElement getRootElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.DocumentRoot#getRootElement <em>Root Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Root Element</em>' containment reference.
     * @see #getRootElement()
     * @generated
     */
    void setRootElement(RootElement value);

} // DocumentRoot
