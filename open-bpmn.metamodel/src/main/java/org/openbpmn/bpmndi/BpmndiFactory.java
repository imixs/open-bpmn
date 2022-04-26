/**
 */
package org.openbpmn.bpmndi;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.bpmndi.BpmndiPackage
 * @generated
 */
public interface BpmndiFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BpmndiFactory eINSTANCE = org.openbpmn.bpmndi.impl.BpmndiFactoryImpl.init();

    /**
     * Returns a new object of class '<em>BPMN Diagram</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Diagram</em>'.
     * @generated
     */
    BPMNDiagram createBPMNDiagram();

    /**
     * Returns a new object of class '<em>BPMN Edge</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Edge</em>'.
     * @generated
     */
    BPMNEdge createBPMNEdge();

    /**
     * Returns a new object of class '<em>BPMN Label</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Label</em>'.
     * @generated
     */
    BPMNLabel createBPMNLabel();

    /**
     * Returns a new object of class '<em>BPMN Label Style</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Label Style</em>'.
     * @generated
     */
    BPMNLabelStyle createBPMNLabelStyle();

    /**
     * Returns a new object of class '<em>BPMN Plane</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Plane</em>'.
     * @generated
     */
    BPMNPlane createBPMNPlane();

    /**
     * Returns a new object of class '<em>BPMN Shape</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>BPMN Shape</em>'.
     * @generated
     */
    BPMNShape createBPMNShape();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BpmndiPackage getBpmndiPackage();

} //BpmndiFactory
