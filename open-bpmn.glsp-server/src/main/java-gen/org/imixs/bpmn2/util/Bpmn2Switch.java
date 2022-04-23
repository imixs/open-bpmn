/**
 */
package org.imixs.bpmn2.util;

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

import org.imixs.bpmn2.*;

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
 * @see org.imixs.bpmn2.Bpmn2Package
 * @generated
 */
public class Bpmn2Switch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static Bpmn2Package modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2Switch() {
        if (modelPackage == null) {
            modelPackage = Bpmn2Package.eINSTANCE;
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
            case Bpmn2Package.BASE_ELEMENT: {
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
            case Bpmn2Package.IMPORT: {
                Import import_ = (Import)theEObject;
                T result = caseImport(import_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EXTENSION_DEFINITION: {
                ExtensionDefinition extensionDefinition = (ExtensionDefinition)theEObject;
                T result = caseExtensionDefinition(extensionDefinition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EXTENSION_ATTRIBUTE_DEFINITION: {
                ExtensionAttributeDefinition extensionAttributeDefinition = (ExtensionAttributeDefinition)theEObject;
                T result = caseExtensionAttributeDefinition(extensionAttributeDefinition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE: {
                ExtensionAttributeValue extensionAttributeValue = (ExtensionAttributeValue)theEObject;
                T result = caseExtensionAttributeValue(extensionAttributeValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EXTENSION: {
                Extension extension = (Extension)theEObject;
                T result = caseExtension(extension);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.RELATIONSHIP: {
                Relationship relationship = (Relationship)theEObject;
                T result = caseRelationship(relationship);
                if (result == null) result = caseBaseElement(relationship);
                if (result == null) result = caseGNode(relationship);
                if (result == null) result = caseGShapeElement(relationship);
                if (result == null) result = caseGEdgeLayoutable(relationship);
                if (result == null) result = caseGLayouting(relationship);
                if (result == null) result = caseGModelElement(relationship);
                if (result == null) result = caseGBoundsAware(relationship);
                if (result == null) result = caseGArgumentable(relationship);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.DEFINITIONS: {
                Definitions definitions = (Definitions)theEObject;
                T result = caseDefinitions(definitions);
                if (result == null) result = caseBaseElement(definitions);
                if (result == null) result = caseGNode(definitions);
                if (result == null) result = caseGShapeElement(definitions);
                if (result == null) result = caseGEdgeLayoutable(definitions);
                if (result == null) result = caseGLayouting(definitions);
                if (result == null) result = caseGModelElement(definitions);
                if (result == null) result = caseGBoundsAware(definitions);
                if (result == null) result = caseGArgumentable(definitions);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.ROOT_ELEMENT: {
                RootElement rootElement = (RootElement)theEObject;
                T result = caseRootElement(rootElement);
                if (result == null) result = caseBaseElement(rootElement);
                if (result == null) result = caseGNode(rootElement);
                if (result == null) result = caseGShapeElement(rootElement);
                if (result == null) result = caseGEdgeLayoutable(rootElement);
                if (result == null) result = caseGLayouting(rootElement);
                if (result == null) result = caseGModelElement(rootElement);
                if (result == null) result = caseGBoundsAware(rootElement);
                if (result == null) result = caseGArgumentable(rootElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.FLOW_ELEMENT: {
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
            case Bpmn2Package.EXPRESSION: {
                Expression expression = (Expression)theEObject;
                T result = caseExpression(expression);
                if (result == null) result = caseBaseElement(expression);
                if (result == null) result = caseGNode(expression);
                if (result == null) result = caseGShapeElement(expression);
                if (result == null) result = caseGEdgeLayoutable(expression);
                if (result == null) result = caseGLayouting(expression);
                if (result == null) result = caseGModelElement(expression);
                if (result == null) result = caseGBoundsAware(expression);
                if (result == null) result = caseGArgumentable(expression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.SEQUENCE_FLOW: {
                SequenceFlow sequenceFlow = (SequenceFlow)theEObject;
                T result = caseSequenceFlow(sequenceFlow);
                if (result == null) result = caseGEdge(sequenceFlow);
                if (result == null) result = caseFlowElement(sequenceFlow);
                if (result == null) result = caseBaseElement(sequenceFlow);
                if (result == null) result = caseGArgumentable(sequenceFlow);
                if (result == null) result = caseGNode(sequenceFlow);
                if (result == null) result = caseGShapeElement(sequenceFlow);
                if (result == null) result = caseGEdgeLayoutable(sequenceFlow);
                if (result == null) result = caseGLayouting(sequenceFlow);
                if (result == null) result = caseGModelElement(sequenceFlow);
                if (result == null) result = caseGBoundsAware(sequenceFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.FLOW_NODE: {
                FlowNode flowNode = (FlowNode)theEObject;
                T result = caseFlowNode(flowNode);
                if (result == null) result = caseFlowElement(flowNode);
                if (result == null) result = caseBaseElement(flowNode);
                if (result == null) result = caseGNode(flowNode);
                if (result == null) result = caseGShapeElement(flowNode);
                if (result == null) result = caseGEdgeLayoutable(flowNode);
                if (result == null) result = caseGLayouting(flowNode);
                if (result == null) result = caseGModelElement(flowNode);
                if (result == null) result = caseGBoundsAware(flowNode);
                if (result == null) result = caseGArgumentable(flowNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.GATEWAY: {
                Gateway gateway = (Gateway)theEObject;
                T result = caseGateway(gateway);
                if (result == null) result = caseFlowElement(gateway);
                if (result == null) result = caseBaseElement(gateway);
                if (result == null) result = caseGNode(gateway);
                if (result == null) result = caseGShapeElement(gateway);
                if (result == null) result = caseGEdgeLayoutable(gateway);
                if (result == null) result = caseGLayouting(gateway);
                if (result == null) result = caseGModelElement(gateway);
                if (result == null) result = caseGBoundsAware(gateway);
                if (result == null) result = caseGArgumentable(gateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.PARALLEL_GATEWAY: {
                ParallelGateway parallelGateway = (ParallelGateway)theEObject;
                T result = caseParallelGateway(parallelGateway);
                if (result == null) result = caseGateway(parallelGateway);
                if (result == null) result = caseFlowElement(parallelGateway);
                if (result == null) result = caseBaseElement(parallelGateway);
                if (result == null) result = caseGNode(parallelGateway);
                if (result == null) result = caseGShapeElement(parallelGateway);
                if (result == null) result = caseGEdgeLayoutable(parallelGateway);
                if (result == null) result = caseGLayouting(parallelGateway);
                if (result == null) result = caseGModelElement(parallelGateway);
                if (result == null) result = caseGBoundsAware(parallelGateway);
                if (result == null) result = caseGArgumentable(parallelGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EXCLUSIVE_GATEWAY: {
                ExclusiveGateway exclusiveGateway = (ExclusiveGateway)theEObject;
                T result = caseExclusiveGateway(exclusiveGateway);
                if (result == null) result = caseGateway(exclusiveGateway);
                if (result == null) result = caseFlowElement(exclusiveGateway);
                if (result == null) result = caseBaseElement(exclusiveGateway);
                if (result == null) result = caseGNode(exclusiveGateway);
                if (result == null) result = caseGShapeElement(exclusiveGateway);
                if (result == null) result = caseGEdgeLayoutable(exclusiveGateway);
                if (result == null) result = caseGLayouting(exclusiveGateway);
                if (result == null) result = caseGModelElement(exclusiveGateway);
                if (result == null) result = caseGBoundsAware(exclusiveGateway);
                if (result == null) result = caseGArgumentable(exclusiveGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.INCUSIVE_GATEWAY: {
                IncusiveGateway incusiveGateway = (IncusiveGateway)theEObject;
                T result = caseIncusiveGateway(incusiveGateway);
                if (result == null) result = caseGateway(incusiveGateway);
                if (result == null) result = caseFlowElement(incusiveGateway);
                if (result == null) result = caseBaseElement(incusiveGateway);
                if (result == null) result = caseGNode(incusiveGateway);
                if (result == null) result = caseGShapeElement(incusiveGateway);
                if (result == null) result = caseGEdgeLayoutable(incusiveGateway);
                if (result == null) result = caseGLayouting(incusiveGateway);
                if (result == null) result = caseGModelElement(incusiveGateway);
                if (result == null) result = caseGBoundsAware(incusiveGateway);
                if (result == null) result = caseGArgumentable(incusiveGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.COMPLEX_GATEWAY: {
                ComplexGateway complexGateway = (ComplexGateway)theEObject;
                T result = caseComplexGateway(complexGateway);
                if (result == null) result = caseGateway(complexGateway);
                if (result == null) result = caseFlowElement(complexGateway);
                if (result == null) result = caseBaseElement(complexGateway);
                if (result == null) result = caseGNode(complexGateway);
                if (result == null) result = caseGShapeElement(complexGateway);
                if (result == null) result = caseGEdgeLayoutable(complexGateway);
                if (result == null) result = caseGLayouting(complexGateway);
                if (result == null) result = caseGModelElement(complexGateway);
                if (result == null) result = caseGBoundsAware(complexGateway);
                if (result == null) result = caseGArgumentable(complexGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.EVENT: {
                Event event = (Event)theEObject;
                T result = caseEvent(event);
                if (result == null) result = caseFlowNode(event);
                if (result == null) result = caseFlowElement(event);
                if (result == null) result = caseBaseElement(event);
                if (result == null) result = caseGNode(event);
                if (result == null) result = caseGShapeElement(event);
                if (result == null) result = caseGEdgeLayoutable(event);
                if (result == null) result = caseGLayouting(event);
                if (result == null) result = caseGModelElement(event);
                if (result == null) result = caseGBoundsAware(event);
                if (result == null) result = caseGArgumentable(event);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.CATCH_EVENT: {
                CatchEvent catchEvent = (CatchEvent)theEObject;
                T result = caseCatchEvent(catchEvent);
                if (result == null) result = caseEvent(catchEvent);
                if (result == null) result = caseFlowNode(catchEvent);
                if (result == null) result = caseFlowElement(catchEvent);
                if (result == null) result = caseBaseElement(catchEvent);
                if (result == null) result = caseGNode(catchEvent);
                if (result == null) result = caseGShapeElement(catchEvent);
                if (result == null) result = caseGEdgeLayoutable(catchEvent);
                if (result == null) result = caseGLayouting(catchEvent);
                if (result == null) result = caseGModelElement(catchEvent);
                if (result == null) result = caseGBoundsAware(catchEvent);
                if (result == null) result = caseGArgumentable(catchEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.THROW_EVENT: {
                ThrowEvent throwEvent = (ThrowEvent)theEObject;
                T result = caseThrowEvent(throwEvent);
                if (result == null) result = caseEvent(throwEvent);
                if (result == null) result = caseFlowNode(throwEvent);
                if (result == null) result = caseFlowElement(throwEvent);
                if (result == null) result = caseBaseElement(throwEvent);
                if (result == null) result = caseGNode(throwEvent);
                if (result == null) result = caseGShapeElement(throwEvent);
                if (result == null) result = caseGEdgeLayoutable(throwEvent);
                if (result == null) result = caseGLayouting(throwEvent);
                if (result == null) result = caseGModelElement(throwEvent);
                if (result == null) result = caseGBoundsAware(throwEvent);
                if (result == null) result = caseGArgumentable(throwEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.START_EVENT: {
                StartEvent startEvent = (StartEvent)theEObject;
                T result = caseStartEvent(startEvent);
                if (result == null) result = caseCatchEvent(startEvent);
                if (result == null) result = caseEvent(startEvent);
                if (result == null) result = caseFlowNode(startEvent);
                if (result == null) result = caseFlowElement(startEvent);
                if (result == null) result = caseBaseElement(startEvent);
                if (result == null) result = caseGNode(startEvent);
                if (result == null) result = caseGShapeElement(startEvent);
                if (result == null) result = caseGEdgeLayoutable(startEvent);
                if (result == null) result = caseGLayouting(startEvent);
                if (result == null) result = caseGModelElement(startEvent);
                if (result == null) result = caseGBoundsAware(startEvent);
                if (result == null) result = caseGArgumentable(startEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.END_EVENT: {
                EndEvent endEvent = (EndEvent)theEObject;
                T result = caseEndEvent(endEvent);
                if (result == null) result = caseThrowEvent(endEvent);
                if (result == null) result = caseEvent(endEvent);
                if (result == null) result = caseFlowNode(endEvent);
                if (result == null) result = caseFlowElement(endEvent);
                if (result == null) result = caseBaseElement(endEvent);
                if (result == null) result = caseGNode(endEvent);
                if (result == null) result = caseGShapeElement(endEvent);
                if (result == null) result = caseGEdgeLayoutable(endEvent);
                if (result == null) result = caseGLayouting(endEvent);
                if (result == null) result = caseGModelElement(endEvent);
                if (result == null) result = caseGBoundsAware(endEvent);
                if (result == null) result = caseGArgumentable(endEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.ACTIVITY: {
                Activity activity = (Activity)theEObject;
                T result = caseActivity(activity);
                if (result == null) result = caseFlowNode(activity);
                if (result == null) result = caseFlowElement(activity);
                if (result == null) result = caseBaseElement(activity);
                if (result == null) result = caseGNode(activity);
                if (result == null) result = caseGShapeElement(activity);
                if (result == null) result = caseGEdgeLayoutable(activity);
                if (result == null) result = caseGLayouting(activity);
                if (result == null) result = caseGModelElement(activity);
                if (result == null) result = caseGBoundsAware(activity);
                if (result == null) result = caseGArgumentable(activity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.TASK: {
                Task task = (Task)theEObject;
                T result = caseTask(task);
                if (result == null) result = caseActivity(task);
                if (result == null) result = caseFlowNode(task);
                if (result == null) result = caseFlowElement(task);
                if (result == null) result = caseBaseElement(task);
                if (result == null) result = caseGNode(task);
                if (result == null) result = caseGShapeElement(task);
                if (result == null) result = caseGEdgeLayoutable(task);
                if (result == null) result = caseGLayouting(task);
                if (result == null) result = caseGModelElement(task);
                if (result == null) result = caseGBoundsAware(task);
                if (result == null) result = caseGArgumentable(task);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.POOL: {
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
            case Bpmn2Package.ICON: {
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
     * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseImport(Import object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtensionDefinition(ExtensionDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension Attribute Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension Attribute Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtensionAttributeDefinition(ExtensionAttributeDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension Attribute Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension Attribute Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtensionAttributeValue(ExtensionAttributeValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtension(Extension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelationship(Relationship object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Definitions</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Definitions</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDefinitions(Definitions object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Root Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Root Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRootElement(RootElement object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExpression(Expression object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Flow Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flow Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlowNode(FlowNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gateway</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGateway(Gateway object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parallel Gateway</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parallel Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParallelGateway(ParallelGateway object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Exclusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Exclusive Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExclusiveGateway(ExclusiveGateway object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Incusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Incusive Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIncusiveGateway(IncusiveGateway object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Complex Gateway</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Complex Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComplexGateway(ComplexGateway object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEvent(Event object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Catch Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Catch Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCatchEvent(CatchEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Throw Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Throw Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseThrowEvent(ThrowEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Start Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStartEvent(StartEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndEvent(EndEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActivity(Activity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTask(Task object) {
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

} //Bpmn2Switch
