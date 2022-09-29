/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Flow GNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#isDefaultFlow <em>Default Flow</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.glsp.bpmn.BpmnPackage#getSequenceFlowGNode()
 * @model
 * @generated
 */
public interface SequenceFlowGNode extends GEdge {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getSequenceFlowGNode_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' attribute.
     * @see #setCondition(String)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getSequenceFlowGNode_Condition()
     * @model
     * @generated
     */
    String getCondition();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#getCondition <em>Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' attribute.
     * @see #getCondition()
     * @generated
     */
    void setCondition(String value);

    /**
     * Returns the value of the '<em><b>Default Flow</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Flow</em>' attribute.
     * @see #setDefaultFlow(boolean)
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#getSequenceFlowGNode_DefaultFlow()
     * @model
     * @generated
     */
    boolean isDefaultFlow();

    /**
     * Sets the value of the '{@link org.openbpmn.glsp.bpmn.SequenceFlowGNode#isDefaultFlow <em>Default Flow</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Flow</em>' attribute.
     * @see #isDefaultFlow()
     * @generated
     */
    void setDefaultFlow(boolean value);

} // SequenceFlowGNode
