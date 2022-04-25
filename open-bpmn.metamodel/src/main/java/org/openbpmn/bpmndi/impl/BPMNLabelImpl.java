/**
 */
package org.openbpmn.bpmndi.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.openbpmn.bpmndi.BPMNLabel;
import org.openbpmn.bpmndi.BPMNLabelStyle;
import org.openbpmn.bpmndi.BpmndiPackage;

import org.openbpmn.di.impl.LabelImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.impl.BPMNLabelImpl#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BPMNLabelImpl extends LabelImpl implements BPMNLabel {
    /**
     * The cached value of the '{@link #getLabelStyle() <em>Label Style</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelStyle()
     * @generated
     * @ordered
     */
    protected BPMNLabelStyle labelStyle;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNLabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmndiPackage.Literals.BPMN_LABEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabelStyle getLabelStyle() {
        if (labelStyle != null && labelStyle.eIsProxy()) {
            InternalEObject oldLabelStyle = (InternalEObject)labelStyle;
            labelStyle = (BPMNLabelStyle)eResolveProxy(oldLabelStyle);
            if (labelStyle != oldLabelStyle) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmndiPackage.BPMN_LABEL__LABEL_STYLE, oldLabelStyle, labelStyle));
            }
        }
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabelStyle basicGetLabelStyle() {
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelStyle(BPMNLabelStyle newLabelStyle) {
        BPMNLabelStyle oldLabelStyle = labelStyle;
        labelStyle = newLabelStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmndiPackage.BPMN_LABEL__LABEL_STYLE, oldLabelStyle, labelStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BpmndiPackage.BPMN_LABEL__LABEL_STYLE:
                if (resolve) return getLabelStyle();
                return basicGetLabelStyle();
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
            case BpmndiPackage.BPMN_LABEL__LABEL_STYLE:
                setLabelStyle((BPMNLabelStyle)newValue);
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
            case BpmndiPackage.BPMN_LABEL__LABEL_STYLE:
                setLabelStyle((BPMNLabelStyle)null);
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
            case BpmndiPackage.BPMN_LABEL__LABEL_STYLE:
                return labelStyle != null;
        }
        return super.eIsSet(featureID);
    }

} //BPMNLabelImpl
