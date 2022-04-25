/**
 */
package org.openbpmn.bpmn2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.bpmn2.Bpmn2Package
 * @generated
 */
public interface Bpmn2Factory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    Bpmn2Factory eINSTANCE = org.openbpmn.bpmn2.impl.Bpmn2FactoryImpl.init();

    /**
     * Returns a new object of class '<em>Definitions</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Definitions</em>'.
     * @generated
     */
    Definitions createDefinitions();

    /**
     * Returns a new object of class '<em>Flow Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Flow Node</em>'.
     * @generated
     */
    FlowNode createFlowNode();

    /**
     * Returns a new object of class '<em>Documentation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Documentation</em>'.
     * @generated
     */
    Documentation createDocumentation();

    /**
     * Returns a new object of class '<em>Activity</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Activity</em>'.
     * @generated
     */
    Activity createActivity();

    /**
     * Returns a new object of class '<em>Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Task</em>'.
     * @generated
     */
    Task createTask();

    /**
     * Returns a new object of class '<em>Send Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Send Task</em>'.
     * @generated
     */
    SendTask createSendTask();

    /**
     * Returns a new object of class '<em>Service Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Service Task</em>'.
     * @generated
     */
    ServiceTask createServiceTask();

    /**
     * Returns a new object of class '<em>Rule Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Rule Task</em>'.
     * @generated
     */
    RuleTask createRuleTask();

    /**
     * Returns a new object of class '<em>Process</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Process</em>'.
     * @generated
     */
    Process createProcess();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    Bpmn2Package getBpmn2Package();

} //Bpmn2Factory
