/**
 */
package org.openbpmn.bpmndi;

import org.eclipse.emf.common.util.EList;

import org.openbpmn.di.Diagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNDiagram#getPlane <em>Plane</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNDiagram#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNDiagram()
 * @model extendedMetaData="name='BPMNDiagram' kind='elementOnly'"
 * @generated
 */
public interface BPMNDiagram extends Diagram {
    /**
     * Returns the value of the '<em><b>Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plane</em>' containment reference.
     * @see #setPlane(BPMNPlane)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNDiagram_Plane()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNPlane' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    BPMNPlane getPlane();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNDiagram#getPlane <em>Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Plane</em>' containment reference.
     * @see #getPlane()
     * @generated
     */
    void setPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>Label Style</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.bpmndi.BPMNLabelStyle}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' containment reference list.
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNDiagram_LabelStyle()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<BPMNLabelStyle> getLabelStyle();

} // BPMNDiagram
