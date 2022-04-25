/**
 */
package org.openbpmn.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.RootElement#getRootElements <em>Root Elements</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getRootElement()
 * @model abstract="true"
 * @generated
 */
public interface RootElement extends BaseElement {
    /**
     * Returns the value of the '<em><b>Root Elements</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Elements</em>' containment reference.
     * @see #setRootElements(RootElement)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getRootElement_RootElements()
     * @model containment="true" ordered="false"
     * @generated
     */
    RootElement getRootElements();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.RootElement#getRootElements <em>Root Elements</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Root Elements</em>' containment reference.
     * @see #getRootElements()
     * @generated
     */
    void setRootElements(RootElement value);

} // RootElement
