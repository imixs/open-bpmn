/**
 */
package org.imixs.bpmn.bpmngraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.TaskNode#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.TaskNode#getTaskType <em>Task Type</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getTaskNode()
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
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getTaskNode_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.TaskNode#getName <em>Name</em>}' attribute.
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
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getTaskNode_TaskType()
    * @model
    * @generated
    */
   String getTaskType();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.TaskNode#getTaskType <em>Task Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Task Type</em>' attribute.
    * @see #getTaskType()
    * @generated
    */
   void setTaskType(String value);

} // TaskNode
