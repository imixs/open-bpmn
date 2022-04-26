/**
 */
package org.openbpmn.bpmn2.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.openbpmn.bpmn2.Activity;
import org.openbpmn.bpmn2.BaseElement;
import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.Definitions;
import org.openbpmn.bpmn2.Documentation;
import org.openbpmn.bpmn2.FlowElement;
import org.openbpmn.bpmn2.FlowNode;
import org.openbpmn.bpmn2.ResourceRole;
import org.openbpmn.bpmn2.RootElement;
import org.openbpmn.bpmn2.RuleTask;
import org.openbpmn.bpmn2.SendTask;
import org.openbpmn.bpmn2.ServiceTask;
import org.openbpmn.bpmn2.Task;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.bpmn2.Bpmn2Package
 * @generated
 */
public class Bpmn2AdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static Bpmn2Package modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2AdapterFactory() {
        if (modelPackage == null) {
            modelPackage = Bpmn2Package.eINSTANCE;
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
    protected Bpmn2Switch<Adapter> modelSwitch =
        new Bpmn2Switch<Adapter>() {
            @Override
            public Adapter caseDefinitions(Definitions object) {
                return createDefinitionsAdapter();
            }
            @Override
            public Adapter caseRootElement(RootElement object) {
                return createRootElementAdapter();
            }
            @Override
            public Adapter caseBaseElement(BaseElement object) {
                return createBaseElementAdapter();
            }
            @Override
            public Adapter caseFlowElement(FlowElement object) {
                return createFlowElementAdapter();
            }
            @Override
            public Adapter caseFlowNode(FlowNode object) {
                return createFlowNodeAdapter();
            }
            @Override
            public Adapter caseDocumentation(Documentation object) {
                return createDocumentationAdapter();
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
            public Adapter caseSendTask(SendTask object) {
                return createSendTaskAdapter();
            }
            @Override
            public Adapter caseServiceTask(ServiceTask object) {
                return createServiceTaskAdapter();
            }
            @Override
            public Adapter caseRuleTask(RuleTask object) {
                return createRuleTaskAdapter();
            }
            @Override
            public Adapter caseProcess(org.openbpmn.bpmn2.Process object) {
                return createProcessAdapter();
            }
            @Override
            public Adapter caseResourceRole(ResourceRole object) {
                return createResourceRoleAdapter();
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
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.Definitions <em>Definitions</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.Definitions
     * @generated
     */
    public Adapter createDefinitionsAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.RootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.RootElement
     * @generated
     */
    public Adapter createRootElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.BaseElement <em>Base Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.BaseElement
     * @generated
     */
    public Adapter createBaseElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.FlowElement
     * @generated
     */
    public Adapter createFlowElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.FlowNode <em>Flow Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.FlowNode
     * @generated
     */
    public Adapter createFlowNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.Documentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.Documentation
     * @generated
     */
    public Adapter createDocumentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.Activity
     * @generated
     */
    public Adapter createActivityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.Task
     * @generated
     */
    public Adapter createTaskAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.SendTask <em>Send Task</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.SendTask
     * @generated
     */
    public Adapter createSendTaskAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.ServiceTask <em>Service Task</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.ServiceTask
     * @generated
     */
    public Adapter createServiceTaskAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.RuleTask <em>Rule Task</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.RuleTask
     * @generated
     */
    public Adapter createRuleTaskAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.Process <em>Process</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.Process
     * @generated
     */
    public Adapter createProcessAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openbpmn.bpmn2.ResourceRole <em>Resource Role</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openbpmn.bpmn2.ResourceRole
     * @generated
     */
    public Adapter createResourceRoleAdapter() {
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

} //Bpmn2AdapterFactory
