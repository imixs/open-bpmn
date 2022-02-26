/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.imixs.bpmn.bpmngraph.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmngraphFactoryImpl extends EFactoryImpl implements BpmngraphFactory {
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static BpmngraphFactory init() {
      try {
         BpmngraphFactory theBpmngraphFactory = (BpmngraphFactory)EPackage.Registry.INSTANCE.getEFactory(BpmngraphPackage.eNS_URI);
         if (theBpmngraphFactory != null) {
            return theBpmngraphFactory;
         }
      }
      catch (Exception exception) {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new BpmngraphFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BpmngraphFactoryImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass) {
      switch (eClass.getClassifierID()) {
         case BpmngraphPackage.BASE_ELEMENT: return createBaseElement();
         case BpmngraphPackage.FLOW_ELEMENT: return createFlowElement();
         case BpmngraphPackage.TASK_NODE: return createTaskNode();
         case BpmngraphPackage.GATEWAY_NODE: return createGatewayNode();
         case BpmngraphPackage.EVENT_NODE: return createEventNode();
         case BpmngraphPackage.POOL: return createPool();
         case BpmngraphPackage.ICON: return createIcon();
         case BpmngraphPackage.SEQUENCE_FLOW: return createSequenceFlow();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BaseElement createBaseElement() {
      BaseElementImpl baseElement = new BaseElementImpl();
      return baseElement;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FlowElement createFlowElement() {
      FlowElementImpl flowElement = new FlowElementImpl();
      return flowElement;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TaskNode createTaskNode() {
      TaskNodeImpl taskNode = new TaskNodeImpl();
      return taskNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public GatewayNode createGatewayNode() {
      GatewayNodeImpl gatewayNode = new GatewayNodeImpl();
      return gatewayNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EventNode createEventNode() {
      EventNodeImpl eventNode = new EventNodeImpl();
      return eventNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Pool createPool() {
      PoolImpl pool = new PoolImpl();
      return pool;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Icon createIcon() {
      IconImpl icon = new IconImpl();
      return icon;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SequenceFlow createSequenceFlow() {
      SequenceFlowImpl sequenceFlow = new SequenceFlowImpl();
      return sequenceFlow;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BpmngraphPackage getBpmngraphPackage() {
      return (BpmngraphPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static BpmngraphPackage getPackage() {
      return BpmngraphPackage.eINSTANCE;
   }

} //BpmngraphFactoryImpl
