/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.Gateway;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gateway</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.GatewayImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.GatewayImpl#getGatewayType <em>Gateway Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GatewayImpl extends ActivityNodeImpl implements Gateway {
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
    * The default value of the '{@link #getGatewayType() <em>Gateway Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getGatewayType()
    * @generated
    * @ordered
    */
   protected static final String GATEWAY_TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getGatewayType() <em>Gateway Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getGatewayType()
    * @generated
    * @ordered
    */
   protected String gatewayType = GATEWAY_TYPE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected GatewayImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return BpmngraphPackage.Literals.GATEWAY;
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
         eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.GATEWAY__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getGatewayType() {
      return gatewayType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setGatewayType(String newGatewayType) {
      String oldGatewayType = gatewayType;
      gatewayType = newGatewayType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.GATEWAY__GATEWAY_TYPE, oldGatewayType, gatewayType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case BpmngraphPackage.GATEWAY__NAME:
            return getName();
         case BpmngraphPackage.GATEWAY__GATEWAY_TYPE:
            return getGatewayType();
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
         case BpmngraphPackage.GATEWAY__NAME:
            setName((String)newValue);
            return;
         case BpmngraphPackage.GATEWAY__GATEWAY_TYPE:
            setGatewayType((String)newValue);
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
         case BpmngraphPackage.GATEWAY__NAME:
            setName(NAME_EDEFAULT);
            return;
         case BpmngraphPackage.GATEWAY__GATEWAY_TYPE:
            setGatewayType(GATEWAY_TYPE_EDEFAULT);
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
         case BpmngraphPackage.GATEWAY__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case BpmngraphPackage.GATEWAY__GATEWAY_TYPE:
            return GATEWAY_TYPE_EDEFAULT == null ? gatewayType != null : !GATEWAY_TYPE_EDEFAULT.equals(gatewayType);
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
      result.append(", gatewayType: ");
      result.append(gatewayType);
      result.append(')');
      return result.toString();
   }

} //GatewayImpl
