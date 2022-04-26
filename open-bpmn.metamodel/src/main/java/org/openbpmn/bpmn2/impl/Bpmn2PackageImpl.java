/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openbpmn.bpmn2.Activity;
import org.openbpmn.bpmn2.BaseElement;
import org.openbpmn.bpmn2.Bpmn2Factory;
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

import org.openbpmn.bpmndi.BpmndiPackage;

import org.openbpmn.bpmndi.impl.BpmndiPackageImpl;

import org.openbpmn.dc.DcPackage;

import org.openbpmn.dc.impl.DcPackageImpl;

import org.openbpmn.di.DiPackage;

import org.openbpmn.di.impl.DiPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2PackageImpl extends EPackageImpl implements Bpmn2Package {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass definitionsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass rootElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass baseElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass flowElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass flowNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass activityEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass taskEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sendTaskEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass serviceTaskEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ruleTaskEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass processEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resourceRoleEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.openbpmn.bpmn2.Bpmn2Package#eNS_URI
     * @see #init()
     * @generated
     */
    private Bpmn2PackageImpl() {
        super(eNS_URI, Bpmn2Factory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link Bpmn2Package#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static Bpmn2Package init() {
        if (isInited) return (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);

        // Obtain or create and register package
        Object registeredBpmn2Package = EPackage.Registry.INSTANCE.get(eNS_URI);
        Bpmn2PackageImpl theBpmn2Package = registeredBpmn2Package instanceof Bpmn2PackageImpl ? (Bpmn2PackageImpl)registeredBpmn2Package : new Bpmn2PackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BpmndiPackage.eNS_URI);
        BpmndiPackageImpl theBpmndiPackage = (BpmndiPackageImpl)(registeredPackage instanceof BpmndiPackageImpl ? registeredPackage : BpmndiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);
        DiPackageImpl theDiPackage = (DiPackageImpl)(registeredPackage instanceof DiPackageImpl ? registeredPackage : DiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);
        DcPackageImpl theDcPackage = (DcPackageImpl)(registeredPackage instanceof DcPackageImpl ? registeredPackage : DcPackage.eINSTANCE);

        // Create package meta-data objects
        theBpmn2Package.createPackageContents();
        theBpmndiPackage.createPackageContents();
        theDiPackage.createPackageContents();
        theDcPackage.createPackageContents();

        // Initialize created meta-data
        theBpmn2Package.initializePackageContents();
        theBpmndiPackage.initializePackageContents();
        theDiPackage.initializePackageContents();
        theDcPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theBpmn2Package.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI, theBpmn2Package);
        return theBpmn2Package;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDefinitions() {
        return definitionsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_Name() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_TargetNamespace() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_ExpressionLanguage() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_TypeLanguage() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_Exporter() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_ExporterVersion() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_Diagrams() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_Processes() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRootElement() {
        return rootElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBaseElement() {
        return baseElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseElement_Id() {
        return (EAttribute)baseElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBaseElement_Documentation() {
        return (EReference)baseElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFlowElement() {
        return flowElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFlowElement_Name() {
        return (EAttribute)flowElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFlowNode() {
        return flowNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentation() {
        return documentationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentation_Text() {
        return (EAttribute)documentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentation_TextFormat() {
        return (EAttribute)documentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getActivity() {
        return activityEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getActivity_IsForCompensation() {
        return (EAttribute)activityEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getActivity_StartQuantity() {
        return (EAttribute)activityEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getActivity_CompletionQuantity() {
        return (EAttribute)activityEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getActivity_Resources() {
        return (EReference)activityEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTask() {
        return taskEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSendTask() {
        return sendTaskEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSendTask_Implementation() {
        return (EAttribute)sendTaskEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getServiceTask() {
        return serviceTaskEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getServiceTask_Implementation() {
        return (EAttribute)serviceTaskEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRuleTask() {
        return ruleTaskEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRuleTask_Implementation() {
        return (EAttribute)ruleTaskEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcess() {
        return processEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcess_IsExecutable() {
        return (EAttribute)processEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getProcess_IsClosed() {
        return (EAttribute)processEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcess_Resources() {
        return (EReference)processEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResourceRole() {
        return resourceRoleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResourceRole_Name() {
        return (EAttribute)resourceRoleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2Factory getBpmn2Factory() {
        return (Bpmn2Factory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        definitionsEClass = createEClass(DEFINITIONS);
        createEAttribute(definitionsEClass, DEFINITIONS__NAME);
        createEAttribute(definitionsEClass, DEFINITIONS__TARGET_NAMESPACE);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPRESSION_LANGUAGE);
        createEAttribute(definitionsEClass, DEFINITIONS__TYPE_LANGUAGE);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPORTER);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPORTER_VERSION);
        createEReference(definitionsEClass, DEFINITIONS__DIAGRAMS);
        createEReference(definitionsEClass, DEFINITIONS__PROCESSES);

        rootElementEClass = createEClass(ROOT_ELEMENT);

        baseElementEClass = createEClass(BASE_ELEMENT);
        createEAttribute(baseElementEClass, BASE_ELEMENT__ID);
        createEReference(baseElementEClass, BASE_ELEMENT__DOCUMENTATION);

        flowElementEClass = createEClass(FLOW_ELEMENT);
        createEAttribute(flowElementEClass, FLOW_ELEMENT__NAME);

        flowNodeEClass = createEClass(FLOW_NODE);

        documentationEClass = createEClass(DOCUMENTATION);
        createEAttribute(documentationEClass, DOCUMENTATION__TEXT);
        createEAttribute(documentationEClass, DOCUMENTATION__TEXT_FORMAT);

        activityEClass = createEClass(ACTIVITY);
        createEAttribute(activityEClass, ACTIVITY__IS_FOR_COMPENSATION);
        createEAttribute(activityEClass, ACTIVITY__START_QUANTITY);
        createEAttribute(activityEClass, ACTIVITY__COMPLETION_QUANTITY);
        createEReference(activityEClass, ACTIVITY__RESOURCES);

        taskEClass = createEClass(TASK);

        sendTaskEClass = createEClass(SEND_TASK);
        createEAttribute(sendTaskEClass, SEND_TASK__IMPLEMENTATION);

        serviceTaskEClass = createEClass(SERVICE_TASK);
        createEAttribute(serviceTaskEClass, SERVICE_TASK__IMPLEMENTATION);

        ruleTaskEClass = createEClass(RULE_TASK);
        createEAttribute(ruleTaskEClass, RULE_TASK__IMPLEMENTATION);

        processEClass = createEClass(PROCESS);
        createEAttribute(processEClass, PROCESS__IS_EXECUTABLE);
        createEAttribute(processEClass, PROCESS__IS_CLOSED);
        createEReference(processEClass, PROCESS__RESOURCES);

        resourceRoleEClass = createEClass(RESOURCE_ROLE);
        createEAttribute(resourceRoleEClass, RESOURCE_ROLE__NAME);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        BpmndiPackage theBpmndiPackage = (BpmndiPackage)EPackage.Registry.INSTANCE.getEPackage(BpmndiPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        definitionsEClass.getESuperTypes().add(this.getBaseElement());
        rootElementEClass.getESuperTypes().add(this.getBaseElement());
        flowElementEClass.getESuperTypes().add(this.getBaseElement());
        flowNodeEClass.getESuperTypes().add(this.getFlowElement());
        documentationEClass.getESuperTypes().add(this.getBaseElement());
        activityEClass.getESuperTypes().add(this.getFlowNode());
        taskEClass.getESuperTypes().add(this.getActivity());
        sendTaskEClass.getESuperTypes().add(this.getTask());
        serviceTaskEClass.getESuperTypes().add(this.getTask());
        ruleTaskEClass.getESuperTypes().add(this.getTask());
        processEClass.getESuperTypes().add(this.getRootElement());
        resourceRoleEClass.getESuperTypes().add(this.getBaseElement());

        // Initialize classes, features, and operations; add parameters
        initEClass(definitionsEClass, Definitions.class, "Definitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDefinitions_Name(), ecorePackage.getEString(), "name", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_TargetNamespace(), ecorePackage.getEString(), "targetNamespace", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_ExpressionLanguage(), ecorePackage.getEString(), "expressionLanguage", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_TypeLanguage(), ecorePackage.getEString(), "typeLanguage", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_Exporter(), ecorePackage.getEString(), "exporter", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_ExporterVersion(), ecorePackage.getEString(), "exporterVersion", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDefinitions_Diagrams(), theBpmndiPackage.getBPMNDiagram(), null, "diagrams", null, 1, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDefinitions_Processes(), this.getProcess(), null, "processes", null, 1, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(rootElementEClass, RootElement.class, "RootElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(baseElementEClass, BaseElement.class, "BaseElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBaseElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBaseElement_Documentation(), this.getDocumentation(), null, "documentation", null, 0, -1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(flowElementEClass, FlowElement.class, "FlowElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flowNodeEClass, FlowNode.class, "FlowNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(documentationEClass, Documentation.class, "Documentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentation_Text(), ecorePackage.getEString(), "text", null, 0, 1, Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentation_TextFormat(), ecorePackage.getEString(), "textFormat", null, 0, 1, Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getActivity_IsForCompensation(), ecorePackage.getEBoolean(), "isForCompensation", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getActivity_StartQuantity(), ecorePackage.getEInt(), "startQuantity", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getActivity_CompletionQuantity(), ecorePackage.getEInt(), "completionQuantity", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getActivity_Resources(), this.getResourceRole(), null, "resources", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sendTaskEClass, SendTask.class, "SendTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSendTask_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, SendTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(serviceTaskEClass, ServiceTask.class, "ServiceTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getServiceTask_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, ServiceTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ruleTaskEClass, RuleTask.class, "RuleTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRuleTask_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, RuleTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(processEClass, org.openbpmn.bpmn2.Process.class, "Process", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getProcess_IsExecutable(), ecorePackage.getEBoolean(), "isExecutable", null, 0, 1, org.openbpmn.bpmn2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getProcess_IsClosed(), ecorePackage.getEBoolean(), "isClosed", null, 0, 1, org.openbpmn.bpmn2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProcess_Resources(), this.getResourceRole(), null, "resources", null, 0, 1, org.openbpmn.bpmn2.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(resourceRoleEClass, ResourceRole.class, "ResourceRole", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getResourceRole_Name(), ecorePackage.getEString(), "name", null, 0, 1, ResourceRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
        addAnnotation
          (getDefinitions_Diagrams(),
           source,
           new String[] {
               "kind", "element",
               "name", "BPMNDiagram",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/DI"
           });
        addAnnotation
          (getDefinitions_Processes(),
           source,
           new String[] {
               "kind", "element",
               "name", "process",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/MODEL"
           });
    }

} //Bpmn2PackageImpl
