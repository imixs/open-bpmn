/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Association#getAssociationDirection <em>Association Direction</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Association#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Association#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getAssociation()
 * @model extendedMetaData="name='tAssociation' kind='elementOnly'"
 * @generated
 */
public interface Association extends Artifact {
    /**
     * Returns the value of the '<em><b>Association Direction</b></em>' attribute.
     * The default value is <code>"None"</code>.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.AssociationDirection}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Association Direction</em>' attribute.
     * @see org.eclipse.bpmn2.AssociationDirection
     * @see #setAssociationDirection(AssociationDirection)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAssociation_AssociationDirection()
     * @model default="None" ordered="false"
     *        extendedMetaData="kind='attribute' name='associationDirection'"
     * @generated
     */
    AssociationDirection getAssociationDirection();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Association#getAssociationDirection <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Association Direction</em>' attribute.
     * @see org.eclipse.bpmn2.AssociationDirection
     * @see #getAssociationDirection()
     * @generated
     */
    void setAssociationDirection(AssociationDirection value);

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' reference.
     * @see #setSourceRef(BaseElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAssociation_SourceRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='sourceRef'"
     * @generated
     */
    BaseElement getSourceRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Association#getSourceRef <em>Source Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' reference.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(BaseElement value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' reference.
     * @see #setTargetRef(BaseElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getAssociation_TargetRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='targetRef'"
     * @generated
     */
    BaseElement getTargetRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Association#getTargetRef <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' reference.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(BaseElement value);

} // Association
