/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.openbpmn.bpmn2.Activity;
import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.ResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.impl.ActivityImpl#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.ActivityImpl#getStartQuantity <em>Start Quantity</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.ActivityImpl#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.ActivityImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl extends FlowNodeImpl implements Activity {
    /**
     * The default value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsForCompensation()
     * @generated
     * @ordered
     */
    protected static final boolean IS_FOR_COMPENSATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsForCompensation()
     * @generated
     * @ordered
     */
    protected boolean isForCompensation = IS_FOR_COMPENSATION_EDEFAULT;

    /**
     * The default value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartQuantity()
     * @generated
     * @ordered
     */
    protected static final int START_QUANTITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartQuantity()
     * @generated
     * @ordered
     */
    protected int startQuantity = START_QUANTITY_EDEFAULT;

    /**
     * The default value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompletionQuantity()
     * @generated
     * @ordered
     */
    protected static final int COMPLETION_QUANTITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompletionQuantity()
     * @generated
     * @ordered
     */
    protected int completionQuantity = COMPLETION_QUANTITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getResources() <em>Resources</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected ResourceRole resources;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActivityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.ACTIVITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsForCompensation() {
        return isForCompensation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsForCompensation(boolean newIsForCompensation) {
        boolean oldIsForCompensation = isForCompensation;
        isForCompensation = newIsForCompensation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION, oldIsForCompensation, isForCompensation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getStartQuantity() {
        return startQuantity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartQuantity(int newStartQuantity) {
        int oldStartQuantity = startQuantity;
        startQuantity = newStartQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__START_QUANTITY, oldStartQuantity, startQuantity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getCompletionQuantity() {
        return completionQuantity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompletionQuantity(int newCompletionQuantity) {
        int oldCompletionQuantity = completionQuantity;
        completionQuantity = newCompletionQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY, oldCompletionQuantity, completionQuantity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceRole getResources() {
        if (resources != null && resources.eIsProxy()) {
            InternalEObject oldResources = (InternalEObject)resources;
            resources = (ResourceRole)eResolveProxy(oldResources);
            if (resources != oldResources) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Bpmn2Package.ACTIVITY__RESOURCES, oldResources, resources));
            }
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceRole basicGetResources() {
        return resources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResources(ResourceRole newResources) {
        ResourceRole oldResources = resources;
        resources = newResources;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ACTIVITY__RESOURCES, oldResources, resources));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
                return isIsForCompensation();
            case Bpmn2Package.ACTIVITY__START_QUANTITY:
                return getStartQuantity();
            case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
                return getCompletionQuantity();
            case Bpmn2Package.ACTIVITY__RESOURCES:
                if (resolve) return getResources();
                return basicGetResources();
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
            case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
                setIsForCompensation((Boolean)newValue);
                return;
            case Bpmn2Package.ACTIVITY__START_QUANTITY:
                setStartQuantity((Integer)newValue);
                return;
            case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
                setCompletionQuantity((Integer)newValue);
                return;
            case Bpmn2Package.ACTIVITY__RESOURCES:
                setResources((ResourceRole)newValue);
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
            case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
                setIsForCompensation(IS_FOR_COMPENSATION_EDEFAULT);
                return;
            case Bpmn2Package.ACTIVITY__START_QUANTITY:
                setStartQuantity(START_QUANTITY_EDEFAULT);
                return;
            case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
                setCompletionQuantity(COMPLETION_QUANTITY_EDEFAULT);
                return;
            case Bpmn2Package.ACTIVITY__RESOURCES:
                setResources((ResourceRole)null);
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
            case Bpmn2Package.ACTIVITY__IS_FOR_COMPENSATION:
                return isForCompensation != IS_FOR_COMPENSATION_EDEFAULT;
            case Bpmn2Package.ACTIVITY__START_QUANTITY:
                return startQuantity != START_QUANTITY_EDEFAULT;
            case Bpmn2Package.ACTIVITY__COMPLETION_QUANTITY:
                return completionQuantity != COMPLETION_QUANTITY_EDEFAULT;
            case Bpmn2Package.ACTIVITY__RESOURCES:
                return resources != null;
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
        result.append(" (isForCompensation: ");
        result.append(isForCompensation);
        result.append(", startQuantity: ");
        result.append(startQuantity);
        result.append(", completionQuantity: ");
        result.append(completionQuantity);
        result.append(')');
        return result.toString();
    }

} //ActivityImpl
