/**
 */
package org.imixs.bpmn.bpmngraph.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.glsp.graph.GArgumentable;
import org.eclipse.glsp.graph.GBoundsAware;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GEdgeLayoutable;
import org.eclipse.glsp.graph.GLayouting;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GShapeElement;

import org.imixs.bpmn.bpmngraph.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage
 * @generated
 */
public class BpmngraphSwitch<T> extends Switch<T> {
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static BpmngraphPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BpmngraphSwitch() {
      if (modelPackage == null) {
         modelPackage = BpmngraphPackage.eINSTANCE;
      }
   }

   /**
    * Checks whether this is a switch for the given package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param ePackage the package in question.
    * @return whether this is a switch for the given package.
    * @generated
    */
   @Override
   protected boolean isSwitchFor(EPackage ePackage) {
      return ePackage == modelPackage;
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   @Override
   protected T doSwitch(int classifierID, EObject theEObject) {
      switch (classifierID) {
         case BpmngraphPackage.ACTIVITY_NODE: {
            ActivityNode activityNode = (ActivityNode)theEObject;
            T result = caseActivityNode(activityNode);
            if (result == null) result = caseGNode(activityNode);
            if (result == null) result = caseGShapeElement(activityNode);
            if (result == null) result = caseGEdgeLayoutable(activityNode);
            if (result == null) result = caseGLayouting(activityNode);
            if (result == null) result = caseGModelElement(activityNode);
            if (result == null) result = caseGBoundsAware(activityNode);
            if (result == null) result = caseGArgumentable(activityNode);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BpmngraphPackage.TASK_NODE: {
            TaskNode taskNode = (TaskNode)theEObject;
            T result = caseTaskNode(taskNode);
            if (result == null) result = caseActivityNode(taskNode);
            if (result == null) result = caseGNode(taskNode);
            if (result == null) result = caseGShapeElement(taskNode);
            if (result == null) result = caseGEdgeLayoutable(taskNode);
            if (result == null) result = caseGLayouting(taskNode);
            if (result == null) result = caseGModelElement(taskNode);
            if (result == null) result = caseGBoundsAware(taskNode);
            if (result == null) result = caseGArgumentable(taskNode);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BpmngraphPackage.ICON: {
            Icon icon = (Icon)theEObject;
            T result = caseIcon(icon);
            if (result == null) result = caseGCompartment(icon);
            if (result == null) result = caseGShapeElement(icon);
            if (result == null) result = caseGLayouting(icon);
            if (result == null) result = caseGModelElement(icon);
            if (result == null) result = caseGBoundsAware(icon);
            if (result == null) result = caseGArgumentable(icon);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BpmngraphPackage.SEQUENCE_FLOW: {
            SequenceFlow sequenceFlow = (SequenceFlow)theEObject;
            T result = caseSequenceFlow(sequenceFlow);
            if (result == null) result = caseGEdge(sequenceFlow);
            if (result == null) result = caseGModelElement(sequenceFlow);
            if (result == null) result = caseGArgumentable(sequenceFlow);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BpmngraphPackage.POOL: {
            Pool pool = (Pool)theEObject;
            T result = casePool(pool);
            if (result == null) result = caseActivityNode(pool);
            if (result == null) result = caseGNode(pool);
            if (result == null) result = caseGShapeElement(pool);
            if (result == null) result = caseGEdgeLayoutable(pool);
            if (result == null) result = caseGLayouting(pool);
            if (result == null) result = caseGModelElement(pool);
            if (result == null) result = caseGBoundsAware(pool);
            if (result == null) result = caseGArgumentable(pool);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BpmngraphPackage.GATEWAY: {
            Gateway gateway = (Gateway)theEObject;
            T result = caseGateway(gateway);
            if (result == null) result = caseActivityNode(gateway);
            if (result == null) result = caseGNode(gateway);
            if (result == null) result = caseGShapeElement(gateway);
            if (result == null) result = caseGEdgeLayoutable(gateway);
            if (result == null) result = caseGLayouting(gateway);
            if (result == null) result = caseGModelElement(gateway);
            if (result == null) result = caseGBoundsAware(gateway);
            if (result == null) result = caseGArgumentable(gateway);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Activity Node</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Activity Node</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseActivityNode(ActivityNode object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Task Node</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Task Node</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseTaskNode(TaskNode object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Icon</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Icon</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseIcon(Icon object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSequenceFlow(SequenceFlow object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Pool</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Pool</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePool(Pool object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Gateway</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Gateway</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGateway(Gateway object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GArgumentable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GArgumentable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGArgumentable(GArgumentable object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GModel Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GModel Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGModelElement(GModelElement object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GBounds Aware</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GBounds Aware</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGBoundsAware(GBoundsAware object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GShape Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GShape Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGShapeElement(GShapeElement object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GEdge Layoutable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GEdge Layoutable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGEdgeLayoutable(GEdgeLayoutable object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GLayouting</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GLayouting</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGLayouting(GLayouting object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GNode</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GNode</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGNode(GNode object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GCompartment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GCompartment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGCompartment(GCompartment object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>GEdge</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>GEdge</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseGEdge(GEdge object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch, but this is the last case anyway.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
   @Override
   public T defaultCase(EObject object) {
      return null;
   }

} //BpmngraphSwitch