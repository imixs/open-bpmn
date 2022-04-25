/**
 */
package org.openbpmn.bpmndi.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.bpmndi.BpmndiPackage
 * @generated
 */
public class BpmndiAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BpmndiPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BpmndiPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BpmndiSwitch<Adapter> modelSwitch =
        new BpmndiSwitch<Adapter>() {
            @Override
            public Adapter caseDocumentRoot(DocumentRoot object) {
                return createDocumentRootAdapter();
            }
            @Override
            public Adapter caseBPMNDiagram(BPMNDiagram object) {
                return createBPMNDiagramAdapter();
            }
            @Override
            public Adapter caseBPMNEdge(BPMNEdge object) {
                return createBPMNEdgeAdapter();
            }
            @Override
            public Adapter caseBPMNLabel(BPMNLabel object) {
                return createBPMNLabelAdapter();
            }
            @Override
            public Adapter caseBPMNLabelStyle(BPMNLabelStyle object) {
                return createBPMNLabelStyleAdapter();
            }
            @Override
            public Adapter caseBPMNPlane(BPMNPlane object) {
                return createBPMNPlaneAdapter();
            }
            @Override
            public Adapter caseBPMNShape(BPMNShape object) {
                return createBPMNShapeAdapter();
            }
            @Override
            public Adapter caseDiagram(Diagram object) {
                return createDiagramAdapter();
            }
            @Override
            public Adapter caseDiagramElement(DiagramElement object) {
                return createDiagramElementAdapter();
            }
            @Override
            public Adapter caseEdge(Edge object) {
                return createEdgeAdapter();
            }
            @Override
            public Adapter caseLabeledEdge(LabeledEdge object) {
                return createLabeledEdgeAdapter();
            }
            @Override
            public Adapter caseNode(Node object) {
                return createNodeAdapter();
            }
            @Override
            public Adapter caseLabel(Label object) {
                return createLabelAdapter();
            }
            @Override
            public Adapter caseStyle(Style object) {
                return createStyleAdapter();
            }
            @Override
            public Adapter casePlane(Plane object) {
                return createPlaneAdapter();
            }
            @Override
            public Adapter caseShape(Shape object) {
                return createShapeAdapter();
            }
            @Override
            public Adapter caseLabeledShape(LabeledShape object) {
                return createLabeledShapeAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.DocumentRoot
     * @generated
     */
    public Adapter createDocumentRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNDiagram <em>BPMN Diagram</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNDiagram
     * @generated
     */
    public Adapter createBPMNDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNEdge <em>BPMN Edge</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNEdge
     * @generated
     */
    public Adapter createBPMNEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNLabel <em>BPMN Label</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNLabel
     * @generated
     */
    public Adapter createBPMNLabelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNLabelStyle <em>BPMN Label Style</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNLabelStyle
     * @generated
     */
    public Adapter createBPMNLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNPlane <em>BPMN Plane</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNPlane
     * @generated
     */
    public Adapter createBPMNPlaneAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmndi.BPMNShape <em>BPMN Shape</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmndi.BPMNShape
     * @generated
     */
    public Adapter createBPMNShapeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Diagram <em>Diagram</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Diagram
     * @generated
     */
    public Adapter createDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.DiagramElement <em>Diagram Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.DiagramElement
     * @generated
     */
    public Adapter createDiagramElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Edge
     * @generated
     */
    public Adapter createEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.LabeledEdge <em>Labeled Edge</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.LabeledEdge
     * @generated
     */
    public Adapter createLabeledEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Node
     * @generated
     */
    public Adapter createNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Label <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Label
     * @generated
     */
    public Adapter createLabelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Style <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Style
     * @generated
     */
    public Adapter createStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Plane <em>Plane</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Plane
     * @generated
     */
    public Adapter createPlaneAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.Shape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.Shape
     * @generated
     */
    public Adapter createShapeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.di.LabeledShape <em>Labeled Shape</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.di.LabeledShape
     * @generated
     */
    public Adapter createLabeledShapeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //BpmndiAdapterFactory
