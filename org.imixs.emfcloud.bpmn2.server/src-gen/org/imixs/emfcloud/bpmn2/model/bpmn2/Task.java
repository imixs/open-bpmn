/**
 */
package org.imixs.emfcloud.bpmn2.model.bpmn2;

import org.eclipse.glsp.graph.GNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getNodeType <em>Node Type</em>}</li>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getTaskType <em>Task Type</em>}</li>
 *   <li>{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getTask()
 * @model
 * @generated
 */
public interface Task extends GNode {
	/**
	 * Returns the value of the '<em><b>Node Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Type</em>' attribute.
	 * @see #setNodeType(String)
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getTask_NodeType()
	 * @model
	 * @generated
	 */
	String getNodeType();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getNodeType <em>Node Type</em>}' attribute.
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
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getTask_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Task Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Type</em>' attribute.
	 * @see #setTaskType(String)
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getTask_TaskType()
	 * @model
	 * @generated
	 */
	String getTaskType();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getTaskType <em>Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Type</em>' attribute.
	 * @see #getTaskType()
	 * @generated
	 */
	void setTaskType(String value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' attribute.
	 * @see #setReference(String)
	 * @see org.imixs.emfcloud.bpmn2.model.bpmn2.Bpmn2Package#getTask_Reference()
	 * @model
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '{@link org.imixs.emfcloud.bpmn2.model.bpmn2.Task#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

} // Task
