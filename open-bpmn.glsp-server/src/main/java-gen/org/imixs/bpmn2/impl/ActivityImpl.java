/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EClass;

import org.imixs.bpmn2.Activity;
import org.imixs.bpmn2.Bpmn2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ActivityImpl extends FlowNodeImpl implements Activity {
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

} //ActivityImpl
