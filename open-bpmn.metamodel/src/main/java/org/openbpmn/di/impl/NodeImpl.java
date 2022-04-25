/**
 */
package org.openbpmn.di.impl;

import org.eclipse.emf.ecore.EClass;

import org.openbpmn.di.DiPackage;
import org.openbpmn.di.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class NodeImpl extends DiagramElementImpl implements Node {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.NODE;
    }

} //NodeImpl
