/**
 */
package org.openbpmn.bpmndi;

import org.openbpmn.dc.Font;

import org.openbpmn.di.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNLabelStyle#getFont <em>Font</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNLabelStyle()
 * @model
 * @generated
 */
public interface BPMNLabelStyle extends Style {
    /**
     * Returns the value of the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font</em>' containment reference.
     * @see #setFont(Font)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNLabelStyle_Font()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Font getFont();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNLabelStyle#getFont <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font</em>' containment reference.
     * @see #getFont()
     * @generated
     */
    void setFont(Font value);

} // BPMNLabelStyle
