/**
 */
package org.openbpmn.bpmn2;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.Process#isIsExecutable <em>Is Executable</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Process#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Process#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getProcess()
 * @model
 * @generated
 */
public interface Process extends RootElement {
    /**
     * Returns the value of the '<em><b>Is Executable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Executable</em>' attribute.
     * @see #setIsExecutable(boolean)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getProcess_IsExecutable()
     * @model
     * @generated
     */
    boolean isIsExecutable();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Process#isIsExecutable <em>Is Executable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Executable</em>' attribute.
     * @see #isIsExecutable()
     * @generated
     */
    void setIsExecutable(boolean value);

    /**
     * Returns the value of the '<em><b>Is Closed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Closed</em>' attribute.
     * @see #setIsClosed(boolean)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getProcess_IsClosed()
     * @model
     * @generated
     */
    boolean isIsClosed();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Process#isIsClosed <em>Is Closed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Closed</em>' attribute.
     * @see #isIsClosed()
     * @generated
     */
    void setIsClosed(boolean value);

    /**
     * Returns the value of the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' reference.
     * @see #setResources(ResourceRole)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getProcess_Resources()
     * @model
     * @generated
     */
    ResourceRole getResources();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Process#getResources <em>Resources</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resources</em>' reference.
     * @see #getResources()
     * @generated
     */
    void setResources(ResourceRole value);

} // Process
