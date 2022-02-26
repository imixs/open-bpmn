/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.FlowElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.FlowElementImpl#getCategory <em>Category</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowElementImpl extends BaseElementImpl implements FlowElement {
   /**
    * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCategory()
    * @generated
    * @ordered
    */
   protected EList<String> category;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected FlowElementImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return BpmngraphPackage.Literals.FLOW_ELEMENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<String> getCategory() {
      if (category == null) {
         category = new EDataTypeUniqueEList<String>(String.class, this, BpmngraphPackage.FLOW_ELEMENT__CATEGORY);
      }
      return category;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case BpmngraphPackage.FLOW_ELEMENT__CATEGORY:
            return getCategory();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case BpmngraphPackage.FLOW_ELEMENT__CATEGORY:
            getCategory().clear();
            getCategory().addAll((Collection<? extends String>)newValue);
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
         case BpmngraphPackage.FLOW_ELEMENT__CATEGORY:
            getCategory().clear();
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
         case BpmngraphPackage.FLOW_ELEMENT__CATEGORY:
            return category != null && !category.isEmpty();
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
      result.append(" (category: ");
      result.append(category);
      result.append(')');
      return result.toString();
   }

} //FlowElementImpl
