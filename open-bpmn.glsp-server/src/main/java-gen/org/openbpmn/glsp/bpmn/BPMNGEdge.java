/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMNG Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBPMNGEdge()
 * @model
 * @generated
 */
public interface BPMNGEdge extends GEdge {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBPMNGEdge_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see #setKind(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getBPMNGEdge_Kind()
     * @model
     * @generated
     */
    String getKind();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see #getKind()
     * @generated
     */
    void setKind(String value);

} // BPMNGEdge
