/**
 */
package org.openbpmn.glsp.graph.impl;

import org.eclipse.emf.ecore.EClass;
import org.openbpmn.glsp.graph.BpmnPackage;
import org.openbpmn.glsp.graph.GatewayNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gateway Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GatewayNodeImpl extends FlowElementImpl implements GatewayNode {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GatewayNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmnPackage.Literals.GATEWAY_NODE;
    }

} //GatewayNodeImpl
