/**
 */
package org.imixs.emf.bpmn2;

import org.eclipse.glsp.graph.GNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.emf.bpmn2.ActivityNode#getNodeType <em>Node Type</em>}</li>
 * </ul>
 *
 * @see org.imixs.emf.bpmn2.Bpmn2Package#getActivityNode()
 * @model
 * @generated
 */
public interface ActivityNode extends GNode {
	/**
	 * Returns the value of the '<em><b>Node Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Type</em>' attribute.
	 * @see #setNodeType(String)
	 * @see org.imixs.emf.bpmn2.Bpmn2Package#getActivityNode_NodeType()
	 * @model
	 * @generated
	 */
	String getNodeType();

	/**
	 * Sets the value of the '{@link org.imixs.emf.bpmn2.ActivityNode#getNodeType <em>Node Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Type</em>' attribute.
	 * @see #getNodeType()
	 * @generated
	 */
	void setNodeType(String value);

} // ActivityNode
