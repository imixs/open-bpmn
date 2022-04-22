/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EClass;

import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.Event;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class EventImpl extends FlowNodeImpl implements Event {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.EVENT;
    }

} //EventImpl
