/**
 */
package org.openbpmn.di;

import org.eclipse.emf.common.util.EList;

import org.openbpmn.dc.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.Edge#getSource <em>Source</em>}</li>
 *   <li>{@link org.openbpmn.di.Edge#getTarget <em>Target</em>}</li>
 *   <li>{@link org.openbpmn.di.Edge#getWaypoint <em>Waypoint</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.di.DiPackage#getEdge()
 * @model abstract="true"
 * @generated
 */
public interface Edge extends DiagramElement {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see org.openbpmn.di.DiPackage#getEdge_Source()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    DiagramElement getSource();

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see org.openbpmn.di.DiPackage#getEdge_Target()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    DiagramElement getTarget();

    /**
     * Returns the value of the '<em><b>Waypoint</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.dc.Point}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Waypoint</em>' containment reference list.
     * @see org.openbpmn.di.DiPackage#getEdge_Waypoint()
     * @model containment="true" lower="2"
     * @generated
     */
    EList<Point> getWaypoint();

} // Edge
