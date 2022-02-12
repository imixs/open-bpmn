/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage
 * @generated
 */
public interface BpmngraphFactory extends EFactory {
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   BpmngraphFactory eINSTANCE = org.imixs.bpmn.bpmngraph.impl.BpmngraphFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Activity Node</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Activity Node</em>'.
    * @generated
    */
   ActivityNode createActivityNode();

   /**
    * Returns a new object of class '<em>Icon</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Icon</em>'.
    * @generated
    */
   Icon createIcon();

   /**
    * Returns a new object of class '<em>Weighted Edge</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Weighted Edge</em>'.
    * @generated
    */
   WeightedEdge createWeightedEdge();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   BpmngraphPackage getBpmngraphPackage();

} //BpmngraphFactory
