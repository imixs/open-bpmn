/**
 */
package org.eclipse.bpmn2.impl;

import java.util.Collection;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.LaneSet;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Elements Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.FlowElementsContainerImpl#getLaneSets <em>Lane Sets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.FlowElementsContainerImpl#getFlowElements <em>Flow Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FlowElementsContainerImpl extends BaseElementImpl implements FlowElementsContainer {
    /**
     * The cached value of the '{@link #getLaneSets() <em>Lane Sets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLaneSets()
     * @generated
     * @ordered
     */
    protected EList<LaneSet> laneSets;

    /**
     * The cached value of the '{@link #getFlowElements() <em>Flow Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlowElements()
     * @generated
     * @ordered
     */
    protected EList<FlowElement> flowElements;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FlowElementsContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.FLOW_ELEMENTS_CONTAINER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LaneSet> getLaneSets() {
        if (laneSets == null) {
            laneSets = new EObjectContainmentEList<LaneSet>(LaneSet.class, this, Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS);
        }
        return laneSets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FlowElement> getFlowElements() {
        if (flowElements == null) {
            flowElements = new EObjectContainmentEList<FlowElement>(FlowElement.class, this, Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS);
        }
        return flowElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                return ((InternalEList<?>)getLaneSets()).basicRemove(otherEnd, msgs);
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                return ((InternalEList<?>)getFlowElements()).basicRemove(otherEnd, msgs);
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
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                return getLaneSets();
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                return getFlowElements();
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
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                getLaneSets().clear();
                getLaneSets().addAll((Collection<? extends LaneSet>)newValue);
                return;
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                getFlowElements().clear();
                getFlowElements().addAll((Collection<? extends FlowElement>)newValue);
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
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                getLaneSets().clear();
                return;
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                getFlowElements().clear();
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
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__LANE_SETS:
                return laneSets != null && !laneSets.isEmpty();
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER__FLOW_ELEMENTS:
                return flowElements != null && !flowElements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FlowElementsContainerImpl
