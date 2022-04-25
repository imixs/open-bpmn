/**
 */
package org.eclipse.bpmn2.impl;

import java.util.Collection;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.MessageFlow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Choreography Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ChoreographyTaskImpl#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChoreographyTaskImpl extends ChoreographyActivityImpl implements ChoreographyTask {
    /**
     * The cached value of the '{@link #getMessageFlowRef() <em>Message Flow Ref</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageFlowRef()
     * @generated
     * @ordered
     */
    protected EList<MessageFlow> messageFlowRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ChoreographyTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.CHOREOGRAPHY_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<MessageFlow> getMessageFlowRef() {
        if (messageFlowRef == null) {
            messageFlowRef = new EObjectResolvingEList<MessageFlow>(MessageFlow.class, this, Bpmn2Package.CHOREOGRAPHY_TASK__MESSAGE_FLOW_REF);
        }
        return messageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.CHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                return getMessageFlowRef();
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
            case Bpmn2Package.CHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
                getMessageFlowRef().addAll((Collection<? extends MessageFlow>)newValue);
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
            case Bpmn2Package.CHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
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
            case Bpmn2Package.CHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                return messageFlowRef != null && !messageFlowRef.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ChoreographyTaskImpl
