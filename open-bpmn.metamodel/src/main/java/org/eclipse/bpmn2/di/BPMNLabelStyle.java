/**
 */
package org.eclipse.bpmn2.di;

import org.eclipse.dd.dc.Font;

import org.eclipse.dd.di.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.di.BPMNLabelStyle#getFont <em>Font</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNLabelStyle()
 * @model extendedMetaData="name='BPMNLabelStyle' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabelStyle extends Style {
    /**
     * Returns the value of the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font</em>' containment reference.
     * @see #setFont(Font)
     * @see org.eclipse.bpmn2.di.BpmnDiPackage#getBPMNLabelStyle_Font()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='Font' namespace='http://www.omg.org/spec/DD/20100524/DC'"
     * @generated
     */
    Font getFont();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.di.BPMNLabelStyle#getFont <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font</em>' containment reference.
     * @see #getFont()
     * @generated
     */
    void setFont(Font value);

} // BPMNLabelStyle
