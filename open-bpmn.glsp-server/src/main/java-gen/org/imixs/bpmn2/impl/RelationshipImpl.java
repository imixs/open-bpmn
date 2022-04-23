/**
 */
package org.imixs.bpmn2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.Relationship;
import org.imixs.bpmn2.RelationshipDirection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.impl.RelationshipImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.RelationshipImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.RelationshipImpl#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipImpl extends BaseElementImpl implements Relationship {
    /**
     * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected static final RelationshipDirection DIRECTION_EDEFAULT = RelationshipDirection.NONE;

    /**
     * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected RelationshipDirection direction = DIRECTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSources() <em>Sources</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSources()
     * @generated
     * @ordered
     */
    protected EList<EObject> sources;

    /**
     * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargets()
     * @generated
     * @ordered
     */
    protected EList<EObject> targets;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelationshipImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.RELATIONSHIP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationshipDirection getDirection() {
        return direction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDirection(RelationshipDirection newDirection) {
        RelationshipDirection oldDirection = direction;
        direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.RELATIONSHIP__DIRECTION, oldDirection, direction));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EObject> getSources() {
        if (sources == null) {
            sources = new EObjectResolvingEList<EObject>(EObject.class, this, Bpmn2Package.RELATIONSHIP__SOURCES);
        }
        return sources;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EObject> getTargets() {
        if (targets == null) {
            targets = new EObjectResolvingEList<EObject>(EObject.class, this, Bpmn2Package.RELATIONSHIP__TARGETS);
        }
        return targets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.RELATIONSHIP__DIRECTION:
                return getDirection();
            case Bpmn2Package.RELATIONSHIP__SOURCES:
                return getSources();
            case Bpmn2Package.RELATIONSHIP__TARGETS:
                return getTargets();
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
            case Bpmn2Package.RELATIONSHIP__DIRECTION:
                setDirection((RelationshipDirection)newValue);
                return;
            case Bpmn2Package.RELATIONSHIP__SOURCES:
                getSources().clear();
                getSources().addAll((Collection<? extends EObject>)newValue);
                return;
            case Bpmn2Package.RELATIONSHIP__TARGETS:
                getTargets().clear();
                getTargets().addAll((Collection<? extends EObject>)newValue);
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
            case Bpmn2Package.RELATIONSHIP__DIRECTION:
                setDirection(DIRECTION_EDEFAULT);
                return;
            case Bpmn2Package.RELATIONSHIP__SOURCES:
                getSources().clear();
                return;
            case Bpmn2Package.RELATIONSHIP__TARGETS:
                getTargets().clear();
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
            case Bpmn2Package.RELATIONSHIP__DIRECTION:
                return direction != DIRECTION_EDEFAULT;
            case Bpmn2Package.RELATIONSHIP__SOURCES:
                return sources != null && !sources.isEmpty();
            case Bpmn2Package.RELATIONSHIP__TARGETS:
                return targets != null && !targets.isEmpty();
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
        result.append(" (direction: ");
        result.append(direction);
        result.append(')');
        return result.toString();
    }

} //RelationshipImpl
