/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.ExclusiveGateway;
import org.imixs.bpmn.bpmngraph.SequenceFlow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exclusive Gateway</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.ExclusiveGatewayImpl#getDefaultSequenceFlow <em>Default Sequence Flow</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExclusiveGatewayImpl extends GatewayImpl implements ExclusiveGateway {
    /**
     * The cached value of the '{@link #getDefaultSequenceFlow() <em>Default Sequence Flow</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultSequenceFlow()
     * @generated
     * @ordered
     */
    protected SequenceFlow defaultSequenceFlow;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExclusiveGatewayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmngraphPackage.Literals.EXCLUSIVE_GATEWAY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceFlow getDefaultSequenceFlow() {
        return defaultSequenceFlow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultSequenceFlow(SequenceFlow newDefaultSequenceFlow) {
        SequenceFlow oldDefaultSequenceFlow = defaultSequenceFlow;
        defaultSequenceFlow = newDefaultSequenceFlow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW, oldDefaultSequenceFlow, defaultSequenceFlow));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BpmngraphPackage.EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW:
                return getDefaultSequenceFlow();
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
            case BpmngraphPackage.EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW:
                setDefaultSequenceFlow((SequenceFlow)newValue);
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
            case BpmngraphPackage.EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW:
                setDefaultSequenceFlow((SequenceFlow)null);
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
            case BpmngraphPackage.EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW:
                return defaultSequenceFlow != null;
        }
        return super.eIsSet(featureID);
    }

} //ExclusiveGatewayImpl
