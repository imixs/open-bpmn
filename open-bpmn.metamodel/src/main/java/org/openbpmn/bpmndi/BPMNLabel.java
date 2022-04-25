/**
 */
package org.openbpmn.bpmndi;

import org.openbpmn.di.Label;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNLabel#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNLabel()
 * @model
 * @generated
 */
public interface BPMNLabel extends Label {
    /**
     * Returns the value of the '<em><b>Label Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' reference.
     * @see #setLabelStyle(BPMNLabelStyle)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNLabel_LabelStyle()
     * @model ordered="false"
     * @generated
     */
    BPMNLabelStyle getLabelStyle();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNLabel#getLabelStyle <em>Label Style</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Style</em>' reference.
     * @see #getLabelStyle()
     * @generated
     */
    void setLabelStyle(BPMNLabelStyle value);

} // BPMNLabel
