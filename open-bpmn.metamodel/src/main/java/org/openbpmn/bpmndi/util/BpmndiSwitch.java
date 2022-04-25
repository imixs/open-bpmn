/**
 */
package org.openbpmn.bpmndi.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.openbpmn.bpmndi.*;

import org.openbpmn.di.Diagram;
import org.openbpmn.di.DiagramElement;
import org.openbpmn.di.Edge;
import org.openbpmn.di.Label;
import org.openbpmn.di.LabeledEdge;
import org.openbpmn.di.LabeledShape;
import org.openbpmn.di.Node;
import org.openbpmn.di.Plane;
import org.openbpmn.di.Shape;
import org.openbpmn.di.Style;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openbpmn.bpmndi.BpmndiPackage
 * @generated
 */
public class BpmndiSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BpmndiPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiSwitch() {
        if (modelPackage == null) {
            modelPackage = BpmndiPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case BpmndiPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_DIAGRAM: {
                BPMNDiagram bpmnDiagram = (BPMNDiagram)theEObject;
                T result = caseBPMNDiagram(bpmnDiagram);
                if (result == null) result = caseDiagram(bpmnDiagram);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_EDGE: {
                BPMNEdge bpmnEdge = (BPMNEdge)theEObject;
                T result = caseBPMNEdge(bpmnEdge);
                if (result == null) result = caseLabeledEdge(bpmnEdge);
                if (result == null) result = caseEdge(bpmnEdge);
                if (result == null) result = caseDiagramElement(bpmnEdge);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_LABEL: {
                BPMNLabel bpmnLabel = (BPMNLabel)theEObject;
                T result = caseBPMNLabel(bpmnLabel);
                if (result == null) result = caseLabel(bpmnLabel);
                if (result == null) result = caseNode(bpmnLabel);
                if (result == null) result = caseDiagramElement(bpmnLabel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_LABEL_STYLE: {
                BPMNLabelStyle bpmnLabelStyle = (BPMNLabelStyle)theEObject;
                T result = caseBPMNLabelStyle(bpmnLabelStyle);
                if (result == null) result = caseStyle(bpmnLabelStyle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_PLANE: {
                BPMNPlane bpmnPlane = (BPMNPlane)theEObject;
                T result = caseBPMNPlane(bpmnPlane);
                if (result == null) result = casePlane(bpmnPlane);
                if (result == null) result = caseNode(bpmnPlane);
                if (result == null) result = caseDiagramElement(bpmnPlane);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmndiPackage.BPMN_SHAPE: {
                BPMNShape bpmnShape = (BPMNShape)theEObject;
                T result = caseBPMNShape(bpmnShape);
                if (result == null) result = caseLabeledShape(bpmnShape);
                if (result == null) result = caseShape(bpmnShape);
                if (result == null) result = caseNode(bpmnShape);
                if (result == null) result = caseDiagramElement(bpmnShape);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNDiagram(BPMNDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNEdge(BPMNEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Label</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNLabel(BPMNLabel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Label Style</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNLabelStyle(BPMNLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Plane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Plane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNPlane(BPMNPlane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNShape(BPMNShape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagram(Diagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramElement(DiagramElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdge(Edge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Labeled Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Labeled Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabeledEdge(LabeledEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNode(Node object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabel(Label object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Plane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Plane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePlane(Plane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShape(Shape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Labeled Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Labeled Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabeledShape(LabeledShape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //BpmndiSwitch
