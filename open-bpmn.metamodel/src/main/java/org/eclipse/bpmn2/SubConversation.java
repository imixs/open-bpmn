/**
 */
package org.eclipse.bpmn2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.SubConversation#getConversationNodes <em>Conversation Nodes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSubConversation()
 * @model
 * @generated
 */
public interface SubConversation extends ConversationNode {
    /**
     * Returns the value of the '<em><b>Conversation Nodes</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ConversationNode}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Nodes</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getSubConversation_ConversationNodes()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<ConversationNode> getConversationNodes();

} // SubConversation
