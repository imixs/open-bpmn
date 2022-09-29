/**
 */
package org.openbpmn.glsp.bpmn.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openbpmn.glsp.bpmn.BpmnPackage
 * @generated
 */
public class BpmnSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BpmnPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmnSwitch() {
        if (modelPackage == null) {
            modelPackage = BpmnPackage.eINSTANCE;
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
            case BpmnPackage.BASE_ELEMENT: {
                BaseElement baseElement = (BaseElement)theEObject;
                T result = caseBaseElement(baseElement);
                if (result == null) result = caseGNode(baseElement);
                if (result == null) result = caseGShapeElement(baseElement);
                if (result == null) result = caseGEdgeLayoutable(baseElement);
                if (result == null) result = caseGLayouting(baseElement);
                if (result == null) result = caseGModelElement(baseElement);
                if (result == null) result = caseGBoundsAware(baseElement);
                if (result == null) result = caseGArgumentable(baseElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.FLOW_ELEMENT: {
                FlowElement flowElement = (FlowElement)theEObject;
                T result = caseFlowElement(flowElement);
                if (result == null) result = caseBaseElement(flowElement);
                if (result == null) result = caseGNode(flowElement);
                if (result == null) result = caseGShapeElement(flowElement);
                if (result == null) result = caseGEdgeLayoutable(flowElement);
                if (result == null) result = caseGLayouting(flowElement);
                if (result == null) result = caseGModelElement(flowElement);
                if (result == null) result = caseGBoundsAware(flowElement);
                if (result == null) result = caseGArgumentable(flowElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.TASK_NODE: {
                TaskNode taskNode = (TaskNode)theEObject;
                T result = caseTaskNode(taskNode);
                if (result == null) result = caseFlowElement(taskNode);
                if (result == null) result = caseBaseElement(taskNode);
                if (result == null) result = caseGNode(taskNode);
                if (result == null) result = caseGShapeElement(taskNode);
                if (result == null) result = caseGEdgeLayoutable(taskNode);
                if (result == null) result = caseGLayouting(taskNode);
                if (result == null) result = caseGModelElement(taskNode);
                if (result == null) result = caseGBoundsAware(taskNode);
                if (result == null) result = caseGArgumentable(taskNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.GATEWAY_NODE: {
                GatewayNode gatewayNode = (GatewayNode)theEObject;
                T result = caseGatewayNode(gatewayNode);
                if (result == null) result = caseFlowElement(gatewayNode);
                if (result == null) result = caseBaseElement(gatewayNode);
                if (result == null) result = caseGNode(gatewayNode);
                if (result == null) result = caseGShapeElement(gatewayNode);
                if (result == null) result = caseGEdgeLayoutable(gatewayNode);
                if (result == null) result = caseGLayouting(gatewayNode);
                if (result == null) result = caseGModelElement(gatewayNode);
                if (result == null) result = caseGBoundsAware(gatewayNode);
                if (result == null) result = caseGArgumentable(gatewayNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.EVENT_NODE: {
                EventNode eventNode = (EventNode)theEObject;
                T result = caseEventNode(eventNode);
                if (result == null) result = caseFlowElement(eventNode);
                if (result == null) result = caseBaseElement(eventNode);
                if (result == null) result = caseGNode(eventNode);
                if (result == null) result = caseGShapeElement(eventNode);
                if (result == null) result = caseGEdgeLayoutable(eventNode);
                if (result == null) result = caseGLayouting(eventNode);
                if (result == null) result = caseGModelElement(eventNode);
                if (result == null) result = caseGBoundsAware(eventNode);
                if (result == null) result = caseGArgumentable(eventNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.LABEL: {
                Label label = (Label)theEObject;
                T result = caseLabel(label);
                if (result == null) result = caseBaseElement(label);
                if (result == null) result = caseGNode(label);
                if (result == null) result = caseGShapeElement(label);
                if (result == null) result = caseGEdgeLayoutable(label);
                if (result == null) result = caseGLayouting(label);
                if (result == null) result = caseGModelElement(label);
                if (result == null) result = caseGBoundsAware(label);
                if (result == null) result = caseGArgumentable(label);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.DATA_OBJECT: {
                DataObject dataObject = (DataObject)theEObject;
                T result = caseDataObject(dataObject);
                if (result == null) result = caseBaseElement(dataObject);
                if (result == null) result = caseGNode(dataObject);
                if (result == null) result = caseGShapeElement(dataObject);
                if (result == null) result = caseGEdgeLayoutable(dataObject);
                if (result == null) result = caseGLayouting(dataObject);
                if (result == null) result = caseGModelElement(dataObject);
                if (result == null) result = caseGBoundsAware(dataObject);
                if (result == null) result = caseGArgumentable(dataObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.MESSAGE: {
                Message message = (Message)theEObject;
                T result = caseMessage(message);
                if (result == null) result = caseBaseElement(message);
                if (result == null) result = caseGNode(message);
                if (result == null) result = caseGShapeElement(message);
                if (result == null) result = caseGEdgeLayoutable(message);
                if (result == null) result = caseGLayouting(message);
                if (result == null) result = caseGModelElement(message);
                if (result == null) result = caseGBoundsAware(message);
                if (result == null) result = caseGArgumentable(message);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.GROUP: {
                Group group = (Group)theEObject;
                T result = caseGroup(group);
                if (result == null) result = caseBaseElement(group);
                if (result == null) result = caseGNode(group);
                if (result == null) result = caseGShapeElement(group);
                if (result == null) result = caseGEdgeLayoutable(group);
                if (result == null) result = caseGLayouting(group);
                if (result == null) result = caseGModelElement(group);
                if (result == null) result = caseGBoundsAware(group);
                if (result == null) result = caseGArgumentable(group);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.TEXT_ANNOTATION: {
                TextAnnotation textAnnotation = (TextAnnotation)theEObject;
                T result = caseTextAnnotation(textAnnotation);
                if (result == null) result = caseBaseElement(textAnnotation);
                if (result == null) result = caseGNode(textAnnotation);
                if (result == null) result = caseGShapeElement(textAnnotation);
                if (result == null) result = caseGEdgeLayoutable(textAnnotation);
                if (result == null) result = caseGLayouting(textAnnotation);
                if (result == null) result = caseGModelElement(textAnnotation);
                if (result == null) result = caseGBoundsAware(textAnnotation);
                if (result == null) result = caseGArgumentable(textAnnotation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.POOL: {
                Pool pool = (Pool)theEObject;
                T result = casePool(pool);
                if (result == null) result = caseBaseElement(pool);
                if (result == null) result = caseGNode(pool);
                if (result == null) result = caseGShapeElement(pool);
                if (result == null) result = caseGEdgeLayoutable(pool);
                if (result == null) result = caseGLayouting(pool);
                if (result == null) result = caseGModelElement(pool);
                if (result == null) result = caseGBoundsAware(pool);
                if (result == null) result = caseGArgumentable(pool);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.LANE: {
                Lane lane = (Lane)theEObject;
                T result = caseLane(lane);
                if (result == null) result = caseBaseElement(lane);
                if (result == null) result = caseGNode(lane);
                if (result == null) result = caseGShapeElement(lane);
                if (result == null) result = caseGEdgeLayoutable(lane);
                if (result == null) result = caseGLayouting(lane);
                if (result == null) result = caseGModelElement(lane);
                if (result == null) result = caseGBoundsAware(lane);
                if (result == null) result = caseGArgumentable(lane);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.ICON: {
                Icon icon = (Icon)theEObject;
                T result = caseIcon(icon);
                if (result == null) result = caseGCompartment(icon);
                if (result == null) result = caseGShapeElement(icon);
                if (result == null) result = caseGLayouting(icon);
                if (result == null) result = caseGModelElement(icon);
                if (result == null) result = caseGBoundsAware(icon);
                if (result == null) result = caseGArgumentable(icon);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BpmnPackage.SEQUENCE_FLOW: {
                SequenceFlow sequenceFlow = (SequenceFlow)theEObject;
                T result = caseSequenceFlow(sequenceFlow);
                if (result == null) result = caseGEdge(sequenceFlow);
                if (result == null) result = caseGModelElement(sequenceFlow);
                if (result == null) result = caseGArgumentable(sequenceFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Base Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Base Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBaseElement(BaseElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Flow Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flow Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlowElement(FlowElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Task Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Task Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTaskNode(TaskNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gateway Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gateway Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGatewayNode(GatewayNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEventNode(EventNode object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Data Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataObject(DataObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroup(Group object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Annotation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Annotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextAnnotation(TextAnnotation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pool</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePool(Pool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLane(Lane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Icon</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Icon</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIcon(Icon object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSequenceFlow(SequenceFlow object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GArgumentable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GArgumentable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGArgumentable(GArgumentable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GModel Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGModelElement(GModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GBounds Aware</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GBounds Aware</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGBoundsAware(GBoundsAware object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GShape Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GShape Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGShapeElement(GShapeElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GEdge Layoutable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GEdge Layoutable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGEdgeLayoutable(GEdgeLayoutable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GLayouting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GLayouting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGLayouting(GLayouting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GNode</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GNode</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGNode(GNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GCompartment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GCompartment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGCompartment(GCompartment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>GEdge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>GEdge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGEdge(GEdge object) {
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

} //BpmnSwitch
