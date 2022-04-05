/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.glsp.graph.impl.GEdgeImpl;

import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.SequenceFlow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#isDefaultFlow <em>Default Flow</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceFlowImpl extends GEdgeImpl implements SequenceFlow {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    protected static final String CONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    protected String condition = CONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #isDefaultFlow() <em>Default Flow</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDefaultFlow()
     * @generated
     * @ordered
     */
    protected static final boolean DEFAULT_FLOW_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDefaultFlow() <em>Default Flow</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDefaultFlow()
     * @generated
     * @ordered
     */
    protected boolean defaultFlow = DEFAULT_FLOW_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SequenceFlowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmngraphPackage.Literals.SEQUENCE_FLOW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCondition() {
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCondition(String newCondition) {
        String oldCondition = condition;
        condition = newCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__CONDITION, oldCondition, condition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDefaultFlow() {
        return defaultFlow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultFlow(boolean newDefaultFlow) {
        boolean oldDefaultFlow = defaultFlow;
        defaultFlow = newDefaultFlow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__DEFAULT_FLOW, oldDefaultFlow, defaultFlow));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                return getName();
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION:
                return getCondition();
            case BpmngraphPackage.SEQUENCE_FLOW__DEFAULT_FLOW:
                return isDefaultFlow();
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
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                setName((String)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION:
                setCondition((String)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__DEFAULT_FLOW:
                setDefaultFlow((Boolean)newValue);
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
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION:
                setCondition(CONDITION_EDEFAULT);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__DEFAULT_FLOW:
                setDefaultFlow(DEFAULT_FLOW_EDEFAULT);
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
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION:
                return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
            case BpmngraphPackage.SEQUENCE_FLOW__DEFAULT_FLOW:
                return defaultFlow != DEFAULT_FLOW_EDEFAULT;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", condition: ");
        result.append(condition);
        result.append(", defaultFlow: ");
        result.append(defaultFlow);
        result.append(')');
        return result.toString();
    }

} //SequenceFlowImpl
