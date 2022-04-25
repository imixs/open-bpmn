/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.ResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.impl.ProcessImpl#isIsExecutable <em>Is Executable</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.ProcessImpl#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.ProcessImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProcessImpl extends RootElementImpl implements org.openbpmn.bpmn2.Process {
    /**
     * The default value of the '{@link #isIsExecutable() <em>Is Executable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsExecutable()
     * @generated
     * @ordered
     */
    protected static final boolean IS_EXECUTABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsExecutable() <em>Is Executable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsExecutable()
     * @generated
     * @ordered
     */
    protected boolean isExecutable = IS_EXECUTABLE_EDEFAULT;

    /**
     * The default value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsClosed()
     * @generated
     * @ordered
     */
    protected static final boolean IS_CLOSED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsClosed()
     * @generated
     * @ordered
     */
    protected boolean isClosed = IS_CLOSED_EDEFAULT;

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
    protected ProcessImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.PROCESS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsExecutable() {
        return isExecutable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsExecutable(boolean newIsExecutable) {
        boolean oldIsExecutable = isExecutable;
        isExecutable = newIsExecutable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.PROCESS__IS_EXECUTABLE, oldIsExecutable, isExecutable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsClosed() {
        return isClosed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsClosed(boolean newIsClosed) {
        boolean oldIsClosed = isClosed;
        isClosed = newIsClosed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.PROCESS__IS_CLOSED, oldIsClosed, isClosed));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, Bpmn2Package.PROCESS__RESOURCES, oldResources, resources));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.PROCESS__RESOURCES, oldResources, resources));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.PROCESS__IS_EXECUTABLE:
                return isIsExecutable();
            case Bpmn2Package.PROCESS__IS_CLOSED:
                return isIsClosed();
            case Bpmn2Package.PROCESS__RESOURCES:
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
            case Bpmn2Package.PROCESS__IS_EXECUTABLE:
                setIsExecutable((Boolean)newValue);
                return;
            case Bpmn2Package.PROCESS__IS_CLOSED:
                setIsClosed((Boolean)newValue);
                return;
            case Bpmn2Package.PROCESS__RESOURCES:
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
            case Bpmn2Package.PROCESS__IS_EXECUTABLE:
                setIsExecutable(IS_EXECUTABLE_EDEFAULT);
                return;
            case Bpmn2Package.PROCESS__IS_CLOSED:
                setIsClosed(IS_CLOSED_EDEFAULT);
                return;
            case Bpmn2Package.PROCESS__RESOURCES:
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
            case Bpmn2Package.PROCESS__IS_EXECUTABLE:
                return isExecutable != IS_EXECUTABLE_EDEFAULT;
            case Bpmn2Package.PROCESS__IS_CLOSED:
                return isClosed != IS_CLOSED_EDEFAULT;
            case Bpmn2Package.PROCESS__RESOURCES:
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
        result.append(" (isExecutable: ");
        result.append(isExecutable);
        result.append(", isClosed: ");
        result.append(isClosed);
        result.append(')');
        return result.toString();
    }

} //ProcessImpl
