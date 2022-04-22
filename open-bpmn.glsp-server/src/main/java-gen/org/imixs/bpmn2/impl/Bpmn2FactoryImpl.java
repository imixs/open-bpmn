/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.imixs.bpmn2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2FactoryImpl extends EFactoryImpl implements Bpmn2Factory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Bpmn2Factory init() {
        try {
            Bpmn2Factory theBpmn2Factory = (Bpmn2Factory)EPackage.Registry.INSTANCE.getEFactory(Bpmn2Package.eNS_URI);
            if (theBpmn2Factory != null) {
                return theBpmn2Factory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new Bpmn2FactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2FactoryImpl() {
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
            case Bpmn2Package.EXPRESSION: return createExpression();
            case Bpmn2Package.SEQUENCE_FLOW: return createSequenceFlow();
            case Bpmn2Package.PARALLEL_GATEWAY: return createParallelGateway();
            case Bpmn2Package.EXCLUSIVE_GATEWAY: return createExclusiveGateway();
            case Bpmn2Package.INCUSIVE_GATEWAY: return createIncusiveGateway();
            case Bpmn2Package.COMPLEX_GATEWAY: return createComplexGateway();
            case Bpmn2Package.CATCH_EVENT: return createCatchEvent();
            case Bpmn2Package.THROW_EVENT: return createThrowEvent();
            case Bpmn2Package.START_EVENT: return createStartEvent();
            case Bpmn2Package.END_EVENT: return createEndEvent();
            case Bpmn2Package.TASK: return createTask();
            case Bpmn2Package.POOL: return createPool();
            case Bpmn2Package.ICON: return createIcon();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression createExpression() {
        ExpressionImpl expression = new ExpressionImpl();
        return expression;
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
    public ParallelGateway createParallelGateway() {
        ParallelGatewayImpl parallelGateway = new ParallelGatewayImpl();
        return parallelGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExclusiveGateway createExclusiveGateway() {
        ExclusiveGatewayImpl exclusiveGateway = new ExclusiveGatewayImpl();
        return exclusiveGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IncusiveGateway createIncusiveGateway() {
        IncusiveGatewayImpl incusiveGateway = new IncusiveGatewayImpl();
        return incusiveGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComplexGateway createComplexGateway() {
        ComplexGatewayImpl complexGateway = new ComplexGatewayImpl();
        return complexGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CatchEvent createCatchEvent() {
        CatchEventImpl catchEvent = new CatchEventImpl();
        return catchEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ThrowEvent createThrowEvent() {
        ThrowEventImpl throwEvent = new ThrowEventImpl();
        return throwEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StartEvent createStartEvent() {
        StartEventImpl startEvent = new StartEventImpl();
        return startEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndEvent createEndEvent() {
        EndEventImpl endEvent = new EndEventImpl();
        return endEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Task createTask() {
        TaskImpl task = new TaskImpl();
        return task;
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
    public Bpmn2Package getBpmn2Package() {
        return (Bpmn2Package)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static Bpmn2Package getPackage() {
        return Bpmn2Package.eINSTANCE;
    }

} //Bpmn2FactoryImpl
