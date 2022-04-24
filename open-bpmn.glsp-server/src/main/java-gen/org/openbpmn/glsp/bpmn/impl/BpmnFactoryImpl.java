/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openbpmn.glsp.bpmn.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnFactoryImpl extends EFactoryImpl implements BpmnFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BpmnFactory init() {
        try {
            BpmnFactory theBpmnFactory = (BpmnFactory)EPackage.Registry.INSTANCE.getEFactory(BpmnPackage.eNS_URI);
            if (theBpmnFactory != null) {
                return theBpmnFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BpmnFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmnFactoryImpl() {
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
            case BpmnPackage.BASE_ELEMENT: return createBaseElement();
            case BpmnPackage.FLOW_ELEMENT: return createFlowElement();
            case BpmnPackage.TASK_NODE: return createTaskNode();
            case BpmnPackage.GATEWAY_NODE: return createGatewayNode();
            case BpmnPackage.EVENT_NODE: return createEventNode();
            case BpmnPackage.POOL: return createPool();
            case BpmnPackage.ICON: return createIcon();
            case BpmnPackage.SEQUENCE_FLOW: return createSequenceFlow();
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
    public BpmnPackage getBpmnPackage() {
        return (BpmnPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BpmnPackage getPackage() {
        return BpmnPackage.eINSTANCE;
    }

} //BpmnFactoryImpl
