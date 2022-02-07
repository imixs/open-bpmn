/**
 */
package org.imixs.bpmn.emf;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.emf.TaskNode#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.emf.TaskNode#isExpanded <em>Expanded</em>}</li>
 *   <li>{@link org.imixs.bpmn.emf.TaskNode#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.imixs.bpmn.emf.TaskNode#getTaskType <em>Task Type</em>}</li>
 *   <li>{@link org.imixs.bpmn.emf.TaskNode#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode()
 * @model
 * @generated
 */
public interface TaskNode extends ActivityNode {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.imixs.bpmn.emf.TaskNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Expanded</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expanded</em>' attribute.
	 * @see #setExpanded(boolean)
	 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode_Expanded()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isExpanded();

	/**
	 * Sets the value of the '{@link org.imixs.bpmn.emf.TaskNode#isExpanded <em>Expanded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expanded</em>' attribute.
	 * @see #isExpanded()
	 * @generated
	 */
	void setExpanded(boolean value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(int)
	 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode_Duration()
	 * @model default="0" required="true"
	 * @generated
	 */
	int getDuration();

	/**
	 * Sets the value of the '{@link org.imixs.bpmn.emf.TaskNode#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(int value);

	/**
	 * Returns the value of the '<em><b>Task Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Type</em>' attribute.
	 * @see #setTaskType(String)
	 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode_TaskType()
	 * @model
	 * @generated
	 */
	String getTaskType();

	/**
	 * Sets the value of the '{@link org.imixs.bpmn.emf.TaskNode#getTaskType <em>Task Type</em>}' attribute.
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
	 * @see org.imixs.bpmn.emf.Bpmn2Package#getTaskNode_Reference()
	 * @model
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '{@link org.imixs.bpmn.emf.TaskNode#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

} // TaskNode
