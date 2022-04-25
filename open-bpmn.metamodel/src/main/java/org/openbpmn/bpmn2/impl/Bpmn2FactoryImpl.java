/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openbpmn.bpmn2.Activity;
import org.openbpmn.bpmn2.Bpmn2Factory;
import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.Definitions;
import org.openbpmn.bpmn2.DocumentRoot;
import org.openbpmn.bpmn2.Documentation;
import org.openbpmn.bpmn2.FlowNode;
import org.openbpmn.bpmn2.RuleTask;
import org.openbpmn.bpmn2.SendTask;
import org.openbpmn.bpmn2.ServiceTask;
import org.openbpmn.bpmn2.Task;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2FactoryImpl extends EFactoryImpl implements Bpmn2Factory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Bpmn2Factory init() {
        try {
            Bpmn2Factory theBpmn2Factory = (Bpmn2Factory)EPackage.Registry.INSTANCE.getEFactory(Bpmn2Package.eNS_URI);
            if (theBpmn2Factory != null) {
                return theBpmn2Factory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new Bpmn2FactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2FactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case Bpmn2Package.DEFINITIONS: return createDefinitions();
            case Bpmn2Package.DOCUMENT_ROOT: return createDocumentRoot();
            case Bpmn2Package.FLOW_NODE: return createFlowNode();
            case Bpmn2Package.DOCUMENTATION: return createDocumentation();
            case Bpmn2Package.ACTIVITY: return createActivity();
            case Bpmn2Package.TASK: return createTask();
            case Bpmn2Package.SEND_TASK: return createSendTask();
            case Bpmn2Package.SERVICE_TASK: return createServiceTask();
            case Bpmn2Package.RULE_TASK: return createRuleTask();
            case Bpmn2Package.PROCESS: return createProcess();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Definitions createDefinitions() {
        DefinitionsImpl definitions = new DefinitionsImpl();
        return definitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowNode createFlowNode() {
        FlowNodeImpl flowNode = new FlowNodeImpl();
        return flowNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Documentation createDocumentation() {
        DocumentationImpl documentation = new DocumentationImpl();
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Activity createActivity() {
        ActivityImpl activity = new ActivityImpl();
        return activity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Task createTask() {
        TaskImpl task = new TaskImpl();
        return task;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SendTask createSendTask() {
        SendTaskImpl sendTask = new SendTaskImpl();
        return sendTask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceTask createServiceTask() {
        ServiceTaskImpl serviceTask = new ServiceTaskImpl();
        return serviceTask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuleTask createRuleTask() {
        RuleTaskImpl ruleTask = new RuleTaskImpl();
        return ruleTask;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.openbpmn.bpmn2.Process createProcess() {
        ProcessImpl process = new ProcessImpl();
        return process;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2Package getBpmn2Package() {
        return (Bpmn2Package)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static Bpmn2Package getPackage() {
        return Bpmn2Package.eINSTANCE;
    }

} //Bpmn2FactoryImpl
