/**
 */
package org.openbpmn.bpmndi;

import org.openbpmn.bpmn2.BaseElement;

import org.openbpmn.di.Plane;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Plane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNPlane#getBpmnElement <em>Bpmn Element</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNPlane()
 * @model
 * @generated
 */
public interface BPMNPlane extends Plane {
    /**
     * Returns the value of the '<em><b>Bpmn Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bpmn Element</em>' reference.
     * @see #setBpmnElement(BaseElement)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNPlane_BpmnElement()
     * @model ordered="false"
     * @generated
     */
    BaseElement getBpmnElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNPlane#getBpmnElement <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' reference.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(BaseElement value);

} // BPMNPlane
