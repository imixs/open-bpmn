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
            case BpmngraphPackage.EXPRESSION: return createExpression();
            case BpmngraphPackage.SEQUENCE_FLOW: return createSequenceFlow();
            case BpmngraphPackage.PARALLEL_GATEWAY: return createParallelGateway();
            case BpmngraphPackage.EXCLUSIVE_GATEWAY: return createExclusiveGateway();
            case BpmngraphPackage.INCUSIVE_GATEWAY: return createIncusiveGateway();
            case BpmngraphPackage.COMPLEX_GATEWAY: return createComplexGateway();
            case BpmngraphPackage.CATCH_EVENT: return createCatchEvent();
            case BpmngraphPackage.THROW_EVENT: return createThrowEvent();
            case BpmngraphPackage.TASK: return createTask();
            case BpmngraphPackage.POOL: return createPool();
            case BpmngraphPackage.ICON: return createIcon();
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
