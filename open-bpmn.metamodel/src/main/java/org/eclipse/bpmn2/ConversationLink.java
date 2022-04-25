/**
 */
package org.eclipse.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conversation Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.ConversationLink#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ConversationLink#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.ConversationLink#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getConversationLink()
 * @model extendedMetaData="name='tConversationLink' kind='elementOnly'"
 * @generated
 */
public interface ConversationLink extends BaseElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getConversationLink_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ConversationLink#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' reference.
     * @see #setSourceRef(InteractionNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getConversationLink_SourceRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='sourceRef'"
     * @generated
     */
    InteractionNode getSourceRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ConversationLink#getSourceRef <em>Source Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' reference.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(InteractionNode value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' reference.
     * @see #setTargetRef(InteractionNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getConversationLink_TargetRef()
     * @model required="true" ordered="false"
     *        extendedMetaData="kind='attribute' name='targetRef'"
     * @generated
     */
    InteractionNode getTargetRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.ConversationLink#getTargetRef <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' reference.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(InteractionNode value);

} // ConversationLink
