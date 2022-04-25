/**
 */
package org.openbpmn.bpmndi.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openbpmn.bpmndi.BPMNDiagram;
import org.openbpmn.bpmndi.BPMNLabelStyle;
import org.openbpmn.bpmndi.BPMNPlane;
import org.openbpmn.bpmndi.BpmndiPackage;

import org.openbpmn.di.impl.DiagramImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.impl.BPMNDiagramImpl#getPlane <em>Plane</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.BPMNDiagramImpl#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BPMNDiagramImpl extends DiagramImpl implements BPMNDiagram {
    /**
     * The cached value of the '{@link #getPlane() <em>Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlane()
     * @generated
     * @ordered
     */
    protected BPMNPlane plane;

    /**
     * The cached value of the '{@link #getLabelStyle() <em>Label Style</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelStyle()
     * @generated
     * @ordered
     */
    protected EList<BPMNLabelStyle> labelStyle;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmndiPackage.Literals.BPMN_DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNPlane getPlane() {
        return plane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPlane(BPMNPlane newPlane, NotificationChain msgs) {
        BPMNPlane oldPlane = plane;
        plane = newPlane;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmndiPackage.BPMN_DIAGRAM__PLANE, oldPlane, newPlane);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPlane(BPMNPlane newPlane) {
        if (newPlane != plane) {
            NotificationChain msgs = null;
            if (plane != null)
                msgs = ((InternalEObject)plane).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmndiPackage.BPMN_DIAGRAM__PLANE, null, msgs);
            if (newPlane != null)
                msgs = ((InternalEObject)newPlane).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmndiPackage.BPMN_DIAGRAM__PLANE, null, msgs);
            msgs = basicSetPlane(newPlane, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmndiPackage.BPMN_DIAGRAM__PLANE, newPlane, newPlane));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BPMNLabelStyle> getLabelStyle() {
        if (labelStyle == null) {
            labelStyle = new EObjectContainmentEList<BPMNLabelStyle>(BPMNLabelStyle.class, this, BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE);
        }
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BpmndiPackage.BPMN_DIAGRAM__PLANE:
                return basicSetPlane(null, msgs);
            case BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE:
                return ((InternalEList<?>)getLabelStyle()).basicRemove(otherEnd, msgs);
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
            case BpmndiPackage.BPMN_DIAGRAM__PLANE:
                return getPlane();
            case BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE:
                return getLabelStyle();
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
            case BpmndiPackage.BPMN_DIAGRAM__PLANE:
                setPlane((BPMNPlane)newValue);
                return;
            case BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE:
                getLabelStyle().clear();
                getLabelStyle().addAll((Collection<? extends BPMNLabelStyle>)newValue);
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
            case BpmndiPackage.BPMN_DIAGRAM__PLANE:
                setPlane((BPMNPlane)null);
                return;
            case BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE:
                getLabelStyle().clear();
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
            case BpmndiPackage.BPMN_DIAGRAM__PLANE:
                return plane != null;
            case BpmndiPackage.BPMN_DIAGRAM__LABEL_STYLE:
                return labelStyle != null && !labelStyle.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //BPMNDiagramImpl
