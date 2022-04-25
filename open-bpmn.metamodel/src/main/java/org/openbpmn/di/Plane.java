/**
 */
package org.openbpmn.di;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.Plane#getPlaneElement <em>Plane Element</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.di.DiPackage#getPlane()
 * @model abstract="true"
 * @generated
 */
public interface Plane extends Node {
    /**
     * Returns the value of the '<em><b>Plane Element</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.di.DiagramElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plane Element</em>' containment reference list.
     * @see org.openbpmn.di.DiPackage#getPlane_PlaneElement()
     * @model containment="true"
     * @generated
     */
    EList<DiagramElement> getPlaneElement();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean plane_element_type(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Plane
