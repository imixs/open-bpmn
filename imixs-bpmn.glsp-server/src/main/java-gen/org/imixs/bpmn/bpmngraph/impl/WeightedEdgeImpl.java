/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.glsp.graph.impl.GEdgeImpl;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.WeightedEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Weighted Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.WeightedEdgeImpl#getProbability <em>Probability</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WeightedEdgeImpl extends GEdgeImpl implements WeightedEdge {
   /**
    * The default value of the '{@link #getProbability() <em>Probability</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProbability()
    * @generated
    * @ordered
    */
   protected static final String PROBABILITY_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getProbability() <em>Probability</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProbability()
    * @generated
    * @ordered
    */
   protected String probability = PROBABILITY_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected WeightedEdgeImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return BpmngraphPackage.Literals.WEIGHTED_EDGE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getProbability() {
      return probability;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setProbability(String newProbability) {
      String oldProbability = probability;
      probability = newProbability;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.WEIGHTED_EDGE__PROBABILITY, oldProbability, probability));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case BpmngraphPackage.WEIGHTED_EDGE__PROBABILITY:
            return getProbability();
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
         case BpmngraphPackage.WEIGHTED_EDGE__PROBABILITY:
            setProbability((String)newValue);
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
         case BpmngraphPackage.WEIGHTED_EDGE__PROBABILITY:
            setProbability(PROBABILITY_EDEFAULT);
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
         case BpmngraphPackage.WEIGHTED_EDGE__PROBABILITY:
            return PROBABILITY_EDEFAULT == null ? probability != null : !PROBABILITY_EDEFAULT.equals(probability);
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
      result.append(" (probability: ");
      result.append(probability);
      result.append(')');
      return result.toString();
   }

} //WeightedEdgeImpl
