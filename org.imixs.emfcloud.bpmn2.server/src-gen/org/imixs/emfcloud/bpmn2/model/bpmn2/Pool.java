/**
 */
package org.imixs.emfcloud.bpmn2.model.bpmn2;

import org.eclipse.glsp.graph.GNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Pool#getNodeType <em>Node Type</em>}</li>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Pool#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getPool()
 * @model
 * @generated
 */
public interface Pool extends GNode {
	/**
	 * Returns the value of the '<em><b>Node Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Type</em>' attribute.
	 * @see #setNodeType(String)
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getPool_NodeType()
	 * @model
	 * @generated
	 */
	String getNodeType();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Pool#getNodeType <em>Node Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Type</em>' attribute.
	 * @see #getNodeType()
	 * @generated
	 */
	void setNodeType(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getPool_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Pool#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Pool
