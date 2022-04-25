/**
 */
package org.openbpmn.bpmn2.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.openbpmn.bpmn2.Activity;
import org.openbpmn.bpmn2.BaseElement;
import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.Definitions;
import org.openbpmn.bpmn2.Documentation;
import org.openbpmn.bpmn2.FlowElement;
import org.openbpmn.bpmn2.FlowElementsContainer;
import org.openbpmn.bpmn2.FlowNode;
import org.openbpmn.bpmn2.RootElement;
import org.openbpmn.bpmn2.RuleTask;
import org.openbpmn.bpmn2.SendTask;
import org.openbpmn.bpmn2.ServiceTask;
import org.openbpmn.bpmn2.Task;

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
 * @see org.openbpmn.bpmn2.Bpmn2Package
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
            case Bpmn2Package.DEFINITIONS: {
                Definitions definitions = (Definitions)theEObject;
                T result = caseDefinitions(definitions);
                if (result == null) result = caseBaseElement(definitions);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.BASE_ELEMENT: {
                BaseElement baseElement = (BaseElement)theEObject;
                T result = caseBaseElement(baseElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.FLOW_ELEMENT: {
                FlowElement flowElement = (FlowElement)theEObject;
                T result = caseFlowElement(flowElement);
                if (result == null) result = caseBaseElement(flowElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.FLOW_NODE: {
                FlowNode flowNode = (FlowNode)theEObject;
                T result = caseFlowNode(flowNode);
                if (result == null) result = caseFlowElement(flowNode);
                if (result == null) result = caseBaseElement(flowNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.DOCUMENTATION: {
                Documentation documentation = (Documentation)theEObject;
                T result = caseDocumentation(documentation);
                if (result == null) result = caseBaseElement(documentation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.ACTIVITY: {
                Activity activity = (Activity)theEObject;
                T result = caseActivity(activity);
                if (result == null) result = caseFlowNode(activity);
                if (result == null) result = caseFlowElement(activity);
                if (result == null) result = caseBaseElement(activity);
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
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.SEND_TASK: {
                SendTask sendTask = (SendTask)theEObject;
                T result = caseSendTask(sendTask);
                if (result == null) result = caseTask(sendTask);
                if (result == null) result = caseActivity(sendTask);
                if (result == null) result = caseFlowNode(sendTask);
                if (result == null) result = caseFlowElement(sendTask);
                if (result == null) result = caseBaseElement(sendTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.SERVICE_TASK: {
                ServiceTask serviceTask = (ServiceTask)theEObject;
                T result = caseServiceTask(serviceTask);
                if (result == null) result = caseTask(serviceTask);
                if (result == null) result = caseActivity(serviceTask);
                if (result == null) result = caseFlowNode(serviceTask);
                if (result == null) result = caseFlowElement(serviceTask);
                if (result == null) result = caseBaseElement(serviceTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.RULE_TASK: {
                RuleTask ruleTask = (RuleTask)theEObject;
                T result = caseRuleTask(ruleTask);
                if (result == null) result = caseTask(ruleTask);
                if (result == null) result = caseActivity(ruleTask);
                if (result == null) result = caseFlowNode(ruleTask);
                if (result == null) result = caseFlowElement(ruleTask);
                if (result == null) result = caseBaseElement(ruleTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.ROOT_ELEMENT: {
                RootElement rootElement = (RootElement)theEObject;
                T result = caseRootElement(rootElement);
                if (result == null) result = caseBaseElement(rootElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.FLOW_ELEMENTS_CONTAINER: {
                FlowElementsContainer flowElementsContainer = (FlowElementsContainer)theEObject;
                T result = caseFlowElementsContainer(flowElementsContainer);
                if (result == null) result = caseBaseElement(flowElementsContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case Bpmn2Package.PROCESS: {
                org.openbpmn.bpmn2.Process process = (org.openbpmn.bpmn2.Process)theEObject;
                T result = caseProcess(process);
                if (result == null) result = caseFlowElementsContainer(process);
                if (result == null) result = caseBaseElement(process);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
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
     * Returns the result of interpreting the object as an instance of '<em>Documentation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documentation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentation(Documentation object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Send Task</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Send Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSendTask(SendTask object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Service Task</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Service Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseServiceTask(ServiceTask object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Rule Task</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Rule Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRuleTask(RuleTask object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Flow Elements Container</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flow Elements Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlowElementsContainer(FlowElementsContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Process</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Process</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseProcess(org.openbpmn.bpmn2.Process object) {
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
