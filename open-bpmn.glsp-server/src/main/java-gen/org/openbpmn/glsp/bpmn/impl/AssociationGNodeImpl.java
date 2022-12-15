/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.glsp.graph.impl.GEdgeImpl;

import org.openbpmn.glsp.bpmn.AssociationGNode;
import org.openbpmn.glsp.bpmn.BpmnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association GNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.glsp.bpmn.impl.AssociationGNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.glsp.bpmn.impl.AssociationGNodeImpl#getAssociationDirection <em>Association Direction</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociationGNodeImpl extends GEdgeImpl implements AssociationGNode {
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
     * The default value of the '{@link #getAssociationDirection() <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssociationDirection()
     * @generated
     * @ordered
     */
    protected static final String ASSOCIATION_DIRECTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAssociationDirection() <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssociationDirection()
     * @generated
     * @ordered
     */
    protected String associationDirection = ASSOCIATION_DIRECTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AssociationGNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmnPackage.Literals.ASSOCIATION_GNODE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, BpmnPackage.ASSOCIATION_GNODE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAssociationDirection() {
        return associationDirection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAssociationDirection(String newAssociationDirection) {
        String oldAssociationDirection = associationDirection;
        associationDirection = newAssociationDirection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmnPackage.ASSOCIATION_GNODE__ASSOCIATION_DIRECTION, oldAssociationDirection, associationDirection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BpmnPackage.ASSOCIATION_GNODE__NAME:
                return getName();
            case BpmnPackage.ASSOCIATION_GNODE__ASSOCIATION_DIRECTION:
                return getAssociationDirection();
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
            case BpmnPackage.ASSOCIATION_GNODE__NAME:
                setName((String)newValue);
                return;
            case BpmnPackage.ASSOCIATION_GNODE__ASSOCIATION_DIRECTION:
                setAssociationDirection((String)newValue);
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
            case BpmnPackage.ASSOCIATION_GNODE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BpmnPackage.ASSOCIATION_GNODE__ASSOCIATION_DIRECTION:
                setAssociationDirection(ASSOCIATION_DIRECTION_EDEFAULT);
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
            case BpmnPackage.ASSOCIATION_GNODE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BpmnPackage.ASSOCIATION_GNODE__ASSOCIATION_DIRECTION:
                return ASSOCIATION_DIRECTION_EDEFAULT == null ? associationDirection != null : !ASSOCIATION_DIRECTION_EDEFAULT.equals(associationDirection);
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
        result.append(", associationDirection: ");
        result.append(associationDirection);
        result.append(')');
        return result.toString();
    }

} //AssociationGNodeImpl
