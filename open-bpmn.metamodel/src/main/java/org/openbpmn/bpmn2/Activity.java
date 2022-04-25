/**
 */
package org.openbpmn.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.Activity#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Activity#getStartQuantity <em>Start Quantity</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Activity#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Activity#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getActivity()
 * @model
 * @generated
 */
public interface Activity extends FlowNode {
    /**
     * Returns the value of the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is For Compensation</em>' attribute.
     * @see #setIsForCompensation(boolean)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getActivity_IsForCompensation()
     * @model
     * @generated
     */
    boolean isIsForCompensation();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Activity#isIsForCompensation <em>Is For Compensation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is For Compensation</em>' attribute.
     * @see #isIsForCompensation()
     * @generated
     */
    void setIsForCompensation(boolean value);

    /**
     * Returns the value of the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Quantity</em>' attribute.
     * @see #setStartQuantity(int)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getActivity_StartQuantity()
     * @model
     * @generated
     */
    int getStartQuantity();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Activity#getStartQuantity <em>Start Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Quantity</em>' attribute.
     * @see #getStartQuantity()
     * @generated
     */
    void setStartQuantity(int value);

    /**
     * Returns the value of the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Completion Quantity</em>' attribute.
     * @see #setCompletionQuantity(int)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getActivity_CompletionQuantity()
     * @model
     * @generated
     */
    int getCompletionQuantity();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Activity#getCompletionQuantity <em>Completion Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Completion Quantity</em>' attribute.
     * @see #getCompletionQuantity()
     * @generated
     */
    void setCompletionQuantity(int value);

    /**
     * Returns the value of the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' reference.
     * @see #setResources(ResourceRole)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getActivity_Resources()
     * @model
     * @generated
     */
    ResourceRole getResources();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Activity#getResources <em>Resources</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resources</em>' reference.
     * @see #getResources()
     * @generated
     */
    void setResources(ResourceRole value);

} // Activity
