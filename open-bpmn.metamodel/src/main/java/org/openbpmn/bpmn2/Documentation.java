/**
 */
package org.openbpmn.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.Documentation#getText <em>Text</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Documentation#getTextFormat <em>Text Format</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getDocumentation()
 * @model
 * @generated
 */
public interface Documentation extends BaseElement {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDocumentation_Text()
     * @model
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Documentation#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Text Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Format</em>' attribute.
     * @see #setTextFormat(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDocumentation_TextFormat()
     * @model
     * @generated
     */
    String getTextFormat();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Documentation#getTextFormat <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Format</em>' attribute.
     * @see #getTextFormat()
     * @generated
     */
    void setTextFormat(String value);

} // Documentation
