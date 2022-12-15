/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Flow GNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.MessageFlowGNode#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getMessageFlowGNode()
 * @model
 * @generated
 */
public interface MessageFlowGNode extends GEdge {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getMessageFlowGNode_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.MessageFlowGNode#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // MessageFlowGNode
