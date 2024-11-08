/**
 */
package org.openbpmn.glsp.bpmn.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.glsp.graph.GArgumentable;
import org.eclipse.glsp.graph.GBoundsAware;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GEdgeLayoutable;
import org.eclipse.glsp.graph.GLayouting;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GShapeElement;

import org.openbpmn.glsp.bpmn.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.glsp.bpmn.BpmnPackage
 * @generated
 */
public class BpmnAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static BpmnPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BpmnAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BpmnPackage.eINSTANCE;
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
    protected BpmnSwitch<Adapter> modelSwitch =
        new BpmnSwitch<Adapter>() {
			@Override
			public Adapter caseBPMNGNode(BPMNGNode object) {
				return createBPMNGNodeAdapter();
			}
			@Override
			public Adapter caseBPMNGEdge(BPMNGEdge object) {
				return createBPMNGEdgeAdapter();
			}
			@Override
			public Adapter caseIconGCompartment(IconGCompartment object) {
				return createIconGCompartmentAdapter();
			}
			@Override
			public Adapter caseTaskGNode(TaskGNode object) {
				return createTaskGNodeAdapter();
			}
			@Override
			public Adapter caseGatewayGNode(GatewayGNode object) {
				return createGatewayGNodeAdapter();
			}
			@Override
			public Adapter caseEventGNode(EventGNode object) {
				return createEventGNodeAdapter();
			}
			@Override
			public Adapter caseLabelGNode(LabelGNode object) {
				return createLabelGNodeAdapter();
			}
			@Override
			public Adapter caseDataObjectGNode(DataObjectGNode object) {
				return createDataObjectGNodeAdapter();
			}
			@Override
			public Adapter caseDataStoreGNode(DataStoreGNode object) {
				return createDataStoreGNodeAdapter();
			}
			@Override
			public Adapter caseMessageGNode(MessageGNode object) {
				return createMessageGNodeAdapter();
			}
			@Override
			public Adapter caseGroupGNode(GroupGNode object) {
				return createGroupGNodeAdapter();
			}
			@Override
			public Adapter caseTextAnnotationGNode(TextAnnotationGNode object) {
				return createTextAnnotationGNodeAdapter();
			}
			@Override
			public Adapter casePoolGNode(PoolGNode object) {
				return createPoolGNodeAdapter();
			}
			@Override
			public Adapter caseLaneGNode(LaneGNode object) {
				return createLaneGNodeAdapter();
			}
			@Override
			public Adapter caseGArgumentable(GArgumentable object) {
				return createGArgumentableAdapter();
			}
			@Override
			public Adapter caseGModelElement(GModelElement object) {
				return createGModelElementAdapter();
			}
			@Override
			public Adapter caseGBoundsAware(GBoundsAware object) {
				return createGBoundsAwareAdapter();
			}
			@Override
			public Adapter caseGShapeElement(GShapeElement object) {
				return createGShapeElementAdapter();
			}
			@Override
			public Adapter caseGEdgeLayoutable(GEdgeLayoutable object) {
				return createGEdgeLayoutableAdapter();
			}
			@Override
			public Adapter caseGLayouting(GLayouting object) {
				return createGLayoutingAdapter();
			}
			@Override
			public Adapter caseGNode(GNode object) {
				return createGNodeAdapter();
			}
			@Override
			public Adapter caseGEdge(GEdge object) {
				return createGEdgeAdapter();
			}
			@Override
			public Adapter caseGCompartment(GCompartment object) {
				return createGCompartmentAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.BPMNGNode <em>BPMNG Node</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.BPMNGNode
	 * @generated
	 */
    public Adapter createBPMNGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.BPMNGEdge <em>BPMNG Edge</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.BPMNGEdge
	 * @generated
	 */
    public Adapter createBPMNGEdgeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.IconGCompartment <em>Icon GCompartment</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.IconGCompartment
	 * @generated
	 */
    public Adapter createIconGCompartmentAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.TaskGNode <em>Task GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.TaskGNode
	 * @generated
	 */
    public Adapter createTaskGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.GatewayGNode <em>Gateway GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.GatewayGNode
	 * @generated
	 */
    public Adapter createGatewayGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.EventGNode <em>Event GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.EventGNode
	 * @generated
	 */
    public Adapter createEventGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.LabelGNode <em>Label GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.LabelGNode
	 * @generated
	 */
    public Adapter createLabelGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.DataObjectGNode <em>Data Object GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.DataObjectGNode
	 * @generated
	 */
    public Adapter createDataObjectGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.DataStoreGNode <em>Data Store GNode</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.DataStoreGNode
	 * @generated
	 */
	public Adapter createDataStoreGNodeAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.MessageGNode <em>Message GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.MessageGNode
	 * @generated
	 */
    public Adapter createMessageGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.GroupGNode <em>Group GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.GroupGNode
	 * @generated
	 */
    public Adapter createGroupGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.TextAnnotationGNode <em>Text Annotation GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.TextAnnotationGNode
	 * @generated
	 */
    public Adapter createTextAnnotationGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.PoolGNode <em>Pool GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.PoolGNode
	 * @generated
	 */
    public Adapter createPoolGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.openbpmn.glsp.bpmn.LaneGNode <em>Lane GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.openbpmn.glsp.bpmn.LaneGNode
	 * @generated
	 */
    public Adapter createLaneGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GArgumentable <em>GArgumentable</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GArgumentable
	 * @generated
	 */
    public Adapter createGArgumentableAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GModelElement <em>GModel Element</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GModelElement
	 * @generated
	 */
    public Adapter createGModelElementAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GBoundsAware <em>GBounds Aware</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GBoundsAware
	 * @generated
	 */
    public Adapter createGBoundsAwareAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GShapeElement <em>GShape Element</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GShapeElement
	 * @generated
	 */
    public Adapter createGShapeElementAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GEdgeLayoutable <em>GEdge Layoutable</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GEdgeLayoutable
	 * @generated
	 */
    public Adapter createGEdgeLayoutableAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GLayouting <em>GLayouting</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GLayouting
	 * @generated
	 */
    public Adapter createGLayoutingAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GNode <em>GNode</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GNode
	 * @generated
	 */
    public Adapter createGNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GEdge <em>GEdge</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GEdge
	 * @generated
	 */
    public Adapter createGEdgeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.glsp.graph.GCompartment <em>GCompartment</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.glsp.graph.GCompartment
	 * @generated
	 */
    public Adapter createGCompartmentAdapter() {
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

} //BpmnAdapterFactory
