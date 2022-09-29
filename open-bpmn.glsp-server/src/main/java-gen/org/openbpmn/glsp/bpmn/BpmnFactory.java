/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.glsp.bpmn.BpmnPackage
 * @generated
 */
public interface BpmnFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BpmnFactory eINSTANCE = org.openbpmn.glsp.bpmn.impl.BpmnFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Base Element GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Base Element GNode</em>'.
     * @generated
     */
    BaseElementGNode createBaseElementGNode();

    /**
     * Returns a new object of class '<em>Flow Element GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Flow Element GNode</em>'.
     * @generated
     */
    FlowElementGNode createFlowElementGNode();

    /**
     * Returns a new object of class '<em>Task GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Task GNode</em>'.
     * @generated
     */
    TaskGNode createTaskGNode();

    /**
     * Returns a new object of class '<em>Gateway GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Gateway GNode</em>'.
     * @generated
     */
    GatewayGNode createGatewayGNode();

    /**
     * Returns a new object of class '<em>Event GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Event GNode</em>'.
     * @generated
     */
    EventGNode createEventGNode();

    /**
     * Returns a new object of class '<em>Label GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Label GNode</em>'.
     * @generated
     */
    LabelGNode createLabelGNode();

    /**
     * Returns a new object of class '<em>Data Object GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Object GNode</em>'.
     * @generated
     */
    DataObjectGNode createDataObjectGNode();

    /**
     * Returns a new object of class '<em>Message GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Message GNode</em>'.
     * @generated
     */
    MessageGNode createMessageGNode();

    /**
     * Returns a new object of class '<em>Group GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Group GNode</em>'.
     * @generated
     */
    GroupGNode createGroupGNode();

    /**
     * Returns a new object of class '<em>Text Annotation GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Text Annotation GNode</em>'.
     * @generated
     */
    TextAnnotationGNode createTextAnnotationGNode();

    /**
     * Returns a new object of class '<em>Pool GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pool GNode</em>'.
     * @generated
     */
    PoolGNode createPoolGNode();

    /**
     * Returns a new object of class '<em>Lane GMode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Lane GMode</em>'.
     * @generated
     */
    LaneGMode createLaneGMode();

    /**
     * Returns a new object of class '<em>Icon GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Icon GNode</em>'.
     * @generated
     */
    IconGNode createIconGNode();

    /**
     * Returns a new object of class '<em>Sequence Flow GNode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sequence Flow GNode</em>'.
     * @generated
     */
    SequenceFlowGNode createSequenceFlowGNode();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BpmnPackage getBpmnPackage();

} //BpmnFactory
