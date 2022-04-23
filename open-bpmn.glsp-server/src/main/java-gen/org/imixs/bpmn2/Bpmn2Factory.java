/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.imixs.bpmn2.Bpmn2Package
 * @generated
 */
public interface Bpmn2Factory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    Bpmn2Factory eINSTANCE = org.imixs.bpmn2.impl.Bpmn2FactoryImpl.init();

    /**
     * Returns a new object of class '<em>Import</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Import</em>'.
     * @generated
     */
    Import createImport();

    /**
     * Returns a new object of class '<em>Extension Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Definition</em>'.
     * @generated
     */
    ExtensionDefinition createExtensionDefinition();

    /**
     * Returns a new object of class '<em>Extension Attribute Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Attribute Definition</em>'.
     * @generated
     */
    ExtensionAttributeDefinition createExtensionAttributeDefinition();

    /**
     * Returns a new object of class '<em>Extension Attribute Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Attribute Value</em>'.
     * @generated
     */
    ExtensionAttributeValue createExtensionAttributeValue();

    /**
     * Returns a new object of class '<em>Extension</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension</em>'.
     * @generated
     */
    Extension createExtension();

    /**
     * Returns a new object of class '<em>Relationship</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relationship</em>'.
     * @generated
     */
    Relationship createRelationship();

    /**
     * Returns a new object of class '<em>Definitions</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Definitions</em>'.
     * @generated
     */
    Definitions createDefinitions();

    /**
     * Returns a new object of class '<em>Root Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Root Element</em>'.
     * @generated
     */
    RootElement createRootElement();

    /**
     * Returns a new object of class '<em>Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression</em>'.
     * @generated
     */
    Expression createExpression();

    /**
     * Returns a new object of class '<em>Sequence Flow</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sequence Flow</em>'.
     * @generated
     */
    SequenceFlow createSequenceFlow();

    /**
     * Returns a new object of class '<em>Parallel Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parallel Gateway</em>'.
     * @generated
     */
    ParallelGateway createParallelGateway();

    /**
     * Returns a new object of class '<em>Exclusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Exclusive Gateway</em>'.
     * @generated
     */
    ExclusiveGateway createExclusiveGateway();

    /**
     * Returns a new object of class '<em>Incusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Incusive Gateway</em>'.
     * @generated
     */
    IncusiveGateway createIncusiveGateway();

    /**
     * Returns a new object of class '<em>Complex Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Complex Gateway</em>'.
     * @generated
     */
    ComplexGateway createComplexGateway();

    /**
     * Returns a new object of class '<em>Catch Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Catch Event</em>'.
     * @generated
     */
    CatchEvent createCatchEvent();

    /**
     * Returns a new object of class '<em>Throw Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Throw Event</em>'.
     * @generated
     */
    ThrowEvent createThrowEvent();

    /**
     * Returns a new object of class '<em>Start Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Start Event</em>'.
     * @generated
     */
    StartEvent createStartEvent();

    /**
     * Returns a new object of class '<em>End Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>End Event</em>'.
     * @generated
     */
    EndEvent createEndEvent();

    /**
     * Returns a new object of class '<em>Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Task</em>'.
     * @generated
     */
    Task createTask();

    /**
     * Returns a new object of class '<em>Pool</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pool</em>'.
     * @generated
     */
    Pool createPool();

    /**
     * Returns a new object of class '<em>Icon</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Icon</em>'.
     * @generated
     */
    Icon createIcon();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    Bpmn2Package getBpmn2Package();

} //Bpmn2Factory
