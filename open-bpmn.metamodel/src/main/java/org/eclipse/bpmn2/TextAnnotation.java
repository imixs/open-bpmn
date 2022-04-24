/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.TextAnnotation#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.TextAnnotation#getTextFormat <em>Text Format</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation()
 * @model
 * @generated
 */
public interface TextAnnotation extends Artifact {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation_Text()
     * @model required="true" ordered="false"
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.TextAnnotation#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Text Format</b></em>' attribute.
     * The default value is <code>"text/plain"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Format</em>' attribute.
     * @see #setTextFormat(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getTextAnnotation_TextFormat()
     * @model default="text/plain" ordered="false"
     * @generated
     */
    String getTextFormat();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.TextAnnotation#getTextFormat <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Format</em>' attribute.
     * @see #getTextFormat()
     * @generated
     */
    void setTextFormat(String value);

} // TextAnnotation
