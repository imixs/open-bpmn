/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.RootElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.impl.RootElementImpl#getRootElements <em>Root Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class RootElementImpl extends BaseElementImpl implements RootElement {
    /**
     * The cached value of the '{@link #getRootElements() <em>Root Elements</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRootElements()
     * @generated
     * @ordered
     */
    protected RootElement rootElements;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RootElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.ROOT_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RootElement getRootElements() {
        return rootElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRootElements(RootElement newRootElements, NotificationChain msgs) {
        RootElement oldRootElements = rootElements;
        rootElements = newRootElements;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS, oldRootElements, newRootElements);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRootElements(RootElement newRootElements) {
        if (newRootElements != rootElements) {
            NotificationChain msgs = null;
            if (rootElements != null)
                msgs = ((InternalEObject)rootElements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS, null, msgs);
            if (newRootElements != null)
                msgs = ((InternalEObject)newRootElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS, null, msgs);
            msgs = basicSetRootElements(newRootElements, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS, newRootElements, newRootElements));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS:
                return basicSetRootElements(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS:
                return getRootElements();
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
            case Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS:
                setRootElements((RootElement)newValue);
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
            case Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS:
                setRootElements((RootElement)null);
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
            case Bpmn2Package.ROOT_ELEMENT__ROOT_ELEMENTS:
                return rootElements != null;
        }
        return super.eIsSet(featureID);
    }

} //RootElementImpl
