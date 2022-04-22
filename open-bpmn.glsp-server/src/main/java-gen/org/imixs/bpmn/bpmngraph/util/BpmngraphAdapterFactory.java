/**
 */
package org.imixs.bpmn.bpmngraph.util;

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

import org.imixs.bpmn.bpmngraph.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage
 * @generated
 */
public class BpmngraphAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BpmngraphPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmngraphAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BpmngraphPackage.eINSTANCE;
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
    protected BpmngraphSwitch<Adapter> modelSwitch =
        new BpmngraphSwitch<Adapter>() {
            @Override
            public Adapter caseBaseElement(BaseElement object) {
                return createBaseElementAdapter();
            }
            @Override
            public Adapter caseFlowElement(FlowElement object) {
                return createFlowElementAdapter();
            }
            @Override
            public Adapter caseExpression(Expression object) {
                return createExpressionAdapter();
            }
            @Override
            public Adapter caseSequenceFlow(SequenceFlow object) {
                return createSequenceFlowAdapter();
            }
            @Override
            public Adapter caseFlowNode(FlowNode object) {
                return createFlowNodeAdapter();
            }
            @Override
            public Adapter caseGateway(Gateway object) {
                return createGatewayAdapter();
            }
            @Override
            public Adapter caseParallelGateway(ParallelGateway object) {
                return createParallelGatewayAdapter();
            }
            @Override
            public Adapter caseExclusiveGateway(ExclusiveGateway object) {
                return createExclusiveGatewayAdapter();
            }
            @Override
            public Adapter caseIncusiveGateway(IncusiveGateway object) {
                return createIncusiveGatewayAdapter();
            }
            @Override
            public Adapter caseComplexGateway(ComplexGateway object) {
                return createComplexGatewayAdapter();
            }
            @Override
            public Adapter caseEvent(Event object) {
                return createEventAdapter();
            }
            @Override
            public Adapter caseCatchEvent(CatchEvent object) {
                return createCatchEventAdapter();
            }
            @Override
            public Adapter caseThrowEvent(ThrowEvent object) {
                return createThrowEventAdapter();
            }
            @Override
            public Adapter caseActivity(Activity object) {
                return createActivityAdapter();
            }
            @Override
            public Adapter caseTask(Task object) {
                return createTaskAdapter();
            }
            @Override
            public Adapter casePool(Pool object) {
                return createPoolAdapter();
            }
            @Override
            public Adapter caseIcon(Icon object) {
                return createIconAdapter();
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
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.BaseElement <em>Base Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.BaseElement
     * @generated
     */
    public Adapter createBaseElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.FlowElement
     * @generated
     */
    public Adapter createFlowElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Expression
     * @generated
     */
    public Adapter createExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.SequenceFlow <em>Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow
     * @generated
     */
    public Adapter createSequenceFlowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.FlowNode <em>Flow Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.FlowNode
     * @generated
     */
    public Adapter createFlowNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Gateway <em>Gateway</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Gateway
     * @generated
     */
    public Adapter createGatewayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.ParallelGateway <em>Parallel Gateway</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.ParallelGateway
     * @generated
     */
    public Adapter createParallelGatewayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.ExclusiveGateway <em>Exclusive Gateway</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.ExclusiveGateway
     * @generated
     */
    public Adapter createExclusiveGatewayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.IncusiveGateway <em>Incusive Gateway</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.IncusiveGateway
     * @generated
     */
    public Adapter createIncusiveGatewayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.ComplexGateway <em>Complex Gateway</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.ComplexGateway
     * @generated
     */
    public Adapter createComplexGatewayAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Event
     * @generated
     */
    public Adapter createEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.CatchEvent <em>Catch Event</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.CatchEvent
     * @generated
     */
    public Adapter createCatchEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.ThrowEvent <em>Throw Event</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.ThrowEvent
     * @generated
     */
    public Adapter createThrowEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Activity
     * @generated
     */
    public Adapter createActivityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Task
     * @generated
     */
    public Adapter createTaskAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Pool <em>Pool</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Pool
     * @generated
     */
    public Adapter createPoolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.imixs.bpmn.bpmngraph.Icon <em>Icon</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.imixs.bpmn.bpmngraph.Icon
     * @generated
     */
    public Adapter createIconAdapter() {
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

} //BpmngraphAdapterFactory
