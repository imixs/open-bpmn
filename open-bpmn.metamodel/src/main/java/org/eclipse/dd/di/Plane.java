/**
 */
package org.eclipse.dd.di;

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
 *   <li>{@link org.eclipse.dd.di.Plane#getPlaneElement <em>Plane Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.dd.di.DiPackage#getPlane()
 * @model abstract="true"
 * @generated
 */
public interface Plane extends Node {
    /**
     * Returns the value of the '<em><b>Plane Element</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.dd.di.DiagramElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plane Element</em>' containment reference list.
     * @see org.eclipse.dd.di.DiPackage#getPlane_PlaneElement()
     * @model containment="true"
     * @generated
     */
    EList<DiagramElement> getPlaneElement();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * @param context The cache of context-specific information.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean plane_element_type(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Plane
