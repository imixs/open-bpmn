/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.Extension;
import org.imixs.bpmn2.ExtensionDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.impl.ExtensionImpl#isMustUnderstand <em>Must Understand</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.ExtensionImpl#getDefinition <em>Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionImpl extends MinimalEObjectImpl.Container implements Extension {
    /**
     * The default value of the '{@link #isMustUnderstand() <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMustUnderstand()
     * @generated
     * @ordered
     */
    protected static final boolean MUST_UNDERSTAND_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMustUnderstand() <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMustUnderstand()
     * @generated
     * @ordered
     */
    protected boolean mustUnderstand = MUST_UNDERSTAND_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefinition()
     * @generated
     * @ordered
     */
    protected ExtensionDefinition definition;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExtensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.EXTENSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isMustUnderstand() {
        return mustUnderstand;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMustUnderstand(boolean newMustUnderstand) {
        boolean oldMustUnderstand = mustUnderstand;
        mustUnderstand = newMustUnderstand;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.EXTENSION__MUST_UNDERSTAND, oldMustUnderstand, mustUnderstand));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionDefinition getDefinition() {
        return definition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDefinition(ExtensionDefinition newDefinition, NotificationChain msgs) {
        ExtensionDefinition oldDefinition = definition;
        definition = newDefinition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Bpmn2Package.EXTENSION__DEFINITION, oldDefinition, newDefinition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefinition(ExtensionDefinition newDefinition) {
        if (newDefinition != definition) {
            NotificationChain msgs = null;
            if (definition != null)
                msgs = ((InternalEObject)definition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.EXTENSION__DEFINITION, null, msgs);
            if (newDefinition != null)
                msgs = ((InternalEObject)newDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.EXTENSION__DEFINITION, null, msgs);
            msgs = basicSetDefinition(newDefinition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.EXTENSION__DEFINITION, newDefinition, newDefinition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Bpmn2Package.EXTENSION__DEFINITION:
                return basicSetDefinition(null, msgs);
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
            case Bpmn2Package.EXTENSION__MUST_UNDERSTAND:
                return isMustUnderstand();
            case Bpmn2Package.EXTENSION__DEFINITION:
                return getDefinition();
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
            case Bpmn2Package.EXTENSION__MUST_UNDERSTAND:
                setMustUnderstand((Boolean)newValue);
                return;
            case Bpmn2Package.EXTENSION__DEFINITION:
                setDefinition((ExtensionDefinition)newValue);
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
            case Bpmn2Package.EXTENSION__MUST_UNDERSTAND:
                setMustUnderstand(MUST_UNDERSTAND_EDEFAULT);
                return;
            case Bpmn2Package.EXTENSION__DEFINITION:
                setDefinition((ExtensionDefinition)null);
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
            case Bpmn2Package.EXTENSION__MUST_UNDERSTAND:
                return mustUnderstand != MUST_UNDERSTAND_EDEFAULT;
            case Bpmn2Package.EXTENSION__DEFINITION:
                return definition != null;
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
        result.append(" (mustUnderstand: ");
        result.append(mustUnderstand);
        result.append(')');
        return result.toString();
    }

} //ExtensionImpl
