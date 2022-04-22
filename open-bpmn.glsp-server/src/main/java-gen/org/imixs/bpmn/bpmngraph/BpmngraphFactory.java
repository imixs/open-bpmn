/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage
 * @generated
 */
public interface BpmngraphFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BpmngraphFactory eINSTANCE = org.imixs.bpmn.bpmngraph.impl.BpmngraphFactoryImpl.init();

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
    BpmngraphPackage getBpmngraphPackage();

} //BpmngraphFactory
