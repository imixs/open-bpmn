/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association GNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.AssociationGNode#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.AssociationGNode#getAssociationDirection <em>Association Direction</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getAssociationGNode()
 * @model
 * @generated
 */
public interface AssociationGNode extends GEdge {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getAssociationGNode_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.AssociationGNode#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Association Direction</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Association Direction</em>' attribute.
     * @see #setAssociationDirection(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getAssociationGNode_AssociationDirection()
     * @model
     * @generated
     */
    String getAssociationDirection();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.AssociationGNode#getAssociationDirection <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Association Direction</em>' attribute.
     * @see #getAssociationDirection()
     * @generated
     */
    void setAssociationDirection(String value);

} // AssociationGNode
