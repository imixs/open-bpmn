/**
 */
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.StandardLoopCharacteristics;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Loop Characteristics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.StandardLoopCharacteristicsImpl#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.StandardLoopCharacteristicsImpl#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.StandardLoopCharacteristicsImpl#isTestBefore <em>Test Before</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StandardLoopCharacteristicsImpl extends LoopCharacteristicsImpl implements StandardLoopCharacteristics {
    /**
     * The cached value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopCondition()
     * @generated
     * @ordered
     */
    protected Expression loopCondition;

    /**
     * The default value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopMaximum()
     * @generated
     * @ordered
     */
    protected static final int LOOP_MAXIMUM_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopMaximum()
     * @generated
     * @ordered
     */
    protected int loopMaximum = LOOP_MAXIMUM_EDEFAULT;

    /**
     * The default value of the '{@link #isTestBefore() <em>Test Before</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTestBefore()
     * @generated
     * @ordered
     */
    protected static final boolean TEST_BEFORE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTestBefore() <em>Test Before</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTestBefore()
     * @generated
     * @ordered
     */
    protected boolean testBefore = TEST_BEFORE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StandardLoopCharacteristicsImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.eINSTANCE.getStandardLoopCharacteristics();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getLoopCondition() {
        return loopCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLoopCondition(Expression newLoopCondition, NotificationChain msgs) {
        Expression oldLoopCondition = loopCondition;
        loopCondition = newLoopCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, oldLoopCondition, newLoopCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoopCondition(Expression newLoopCondition) {
        if (newLoopCondition != loopCondition) {
            NotificationChain msgs = null;
            if (loopCondition != null)
                msgs = ((InternalEObject)loopCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, null, msgs);
            if (newLoopCondition != null)
                msgs = ((InternalEObject)newLoopCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, null, msgs);
            msgs = basicSetLoopCondition(newLoopCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, newLoopCondition, newLoopCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLoopMaximum() {
        return loopMaximum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoopMaximum(int newLoopMaximum) {
        int oldLoopMaximum = loopMaximum;
        loopMaximum = newLoopMaximum;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM, oldLoopMaximum, loopMaximum));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTestBefore() {
        return testBefore;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTestBefore(boolean newTestBefore) {
        boolean oldTestBefore = testBefore;
        testBefore = newTestBefore;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE, oldTestBefore, testBefore));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
                return basicSetLoopCondition(null, msgs);
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
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
                return getLoopCondition();
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
                return getLoopMaximum();
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
                return isTestBefore();
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
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
                setLoopCondition((Expression)newValue);
                return;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
                setLoopMaximum((Integer)newValue);
                return;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
                setTestBefore((Boolean)newValue);
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
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
                setLoopCondition((Expression)null);
                return;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
                setLoopMaximum(LOOP_MAXIMUM_EDEFAULT);
                return;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
                setTestBefore(TEST_BEFORE_EDEFAULT);
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
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
                return loopCondition != null;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
                return loopMaximum != LOOP_MAXIMUM_EDEFAULT;
            case Bpmn2Package.STANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
                return testBefore != TEST_BEFORE_EDEFAULT;
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
        result.append(" (loopMaximum: ");
        result.append(loopMaximum);
        result.append(", testBefore: ");
        result.append(testBefore);
        result.append(')');
        return result.toString();
    }

} //StandardLoopCharacteristicsImpl
