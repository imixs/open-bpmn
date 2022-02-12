/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.TaskNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl#getTaskType <em>Task Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskNodeImpl extends ActivityNodeImpl implements TaskNode {
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The default value of the '{@link #getTaskType() <em>Task Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTaskType()
    * @generated
    * @ordered
    */
   protected static final String TASK_TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTaskType() <em>Task Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTaskType()
    * @generated
    * @ordered
    */
   protected String taskType = TASK_TYPE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected TaskNodeImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return BpmngraphPackage.Literals.TASK_NODE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName() {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName) {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.TASK_NODE__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getTaskType() {
      return taskType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTaskType(String newTaskType) {
      String oldTaskType = taskType;
      taskType = newTaskType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.TASK_NODE__TASK_TYPE, oldTaskType, taskType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case BpmngraphPackage.TASK_NODE__NAME:
            return getName();
         case BpmngraphPackage.TASK_NODE__TASK_TYPE:
            return getTaskType();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case BpmngraphPackage.TASK_NODE__NAME:
            setName((String)newValue);
            return;
         case BpmngraphPackage.TASK_NODE__TASK_TYPE:
            setTaskType((String)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID) {
      switch (featureID) {
         case BpmngraphPackage.TASK_NODE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case BpmngraphPackage.TASK_NODE__TASK_TYPE:
            setTaskType(TASK_TYPE_EDEFAULT);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID) {
      switch (featureID) {
         case BpmngraphPackage.TASK_NODE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case BpmngraphPackage.TASK_NODE__TASK_TYPE:
            return TASK_TYPE_EDEFAULT == null ? taskType != null : !TASK_TYPE_EDEFAULT.equals(taskType);
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuilder result = new StringBuilder(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(", taskType: ");
      result.append(taskType);
      result.append(')');
      return result.toString();
   }

} //TaskNodeImpl
