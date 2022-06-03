/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GraphPackageImpl;

import org.openbpmn.glsp.bpmn.BaseElement;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.FlowElement;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.bpmn.Icon;
import org.openbpmn.glsp.bpmn.Pool;
import org.openbpmn.glsp.bpmn.SequenceFlow;
import org.openbpmn.glsp.bpmn.TaskNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnPackageImpl extends EPackageImpl implements BpmnPackage {
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
    private EClass taskNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass gatewayNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass eventNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass poolEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iconEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sequenceFlowEClass = null;

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
     * @see org.openbpmn.glsp.bpmn.BpmnPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private BpmnPackageImpl() {
        super(eNS_URI, BpmnFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link BpmnPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static BpmnPackage init() {
        if (isInited) return (BpmnPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredBpmnPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        BpmnPackageImpl theBpmnPackage = registeredBpmnPackage instanceof BpmnPackageImpl ? (BpmnPackageImpl)registeredBpmnPackage : new BpmnPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(GraphPackage.eNS_URI);
        GraphPackageImpl theGraphPackage = (GraphPackageImpl)(registeredPackage instanceof GraphPackageImpl ? registeredPackage : GraphPackage.eINSTANCE);

        // Create package meta-data objects
        theBpmnPackage.createPackageContents();
        theGraphPackage.createPackageContents();

        // Initialize created meta-data
        theBpmnPackage.initializePackageContents();
        theGraphPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theBpmnPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(BpmnPackage.eNS_URI, theBpmnPackage);
        return theBpmnPackage;
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
    public EAttribute getBaseElement_Name() {
        return (EAttribute)baseElementEClass.getEStructuralFeatures().get(0);
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
    public EAttribute getFlowElement_Symbol() {
        return (EAttribute)flowElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTaskNode() {
        return taskNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGatewayNode() {
        return gatewayNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEventNode() {
        return eventNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPool() {
        return poolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIcon() {
        return iconEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSequenceFlow() {
        return sequenceFlowEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlow_Name() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlow_Condition() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlow_DefaultFlow() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmnFactory getBpmnFactory() {
        return (BpmnFactory)getEFactoryInstance();
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
        baseElementEClass = createEClass(BASE_ELEMENT);
        createEAttribute(baseElementEClass, BASE_ELEMENT__NAME);

        flowElementEClass = createEClass(FLOW_ELEMENT);
        createEAttribute(flowElementEClass, FLOW_ELEMENT__SYMBOL);

        taskNodeEClass = createEClass(TASK_NODE);

        gatewayNodeEClass = createEClass(GATEWAY_NODE);

        eventNodeEClass = createEClass(EVENT_NODE);

        poolEClass = createEClass(POOL);

        iconEClass = createEClass(ICON);

        sequenceFlowEClass = createEClass(SEQUENCE_FLOW);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__NAME);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__CONDITION);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__DEFAULT_FLOW);
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
        GraphPackage theGraphPackage = (GraphPackage)EPackage.Registry.INSTANCE.getEPackage(GraphPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        baseElementEClass.getESuperTypes().add(theGraphPackage.getGNode());
        flowElementEClass.getESuperTypes().add(this.getBaseElement());
        taskNodeEClass.getESuperTypes().add(this.getFlowElement());
        gatewayNodeEClass.getESuperTypes().add(this.getFlowElement());
        eventNodeEClass.getESuperTypes().add(this.getFlowElement());
        poolEClass.getESuperTypes().add(this.getBaseElement());
        iconEClass.getESuperTypes().add(theGraphPackage.getGCompartment());
        sequenceFlowEClass.getESuperTypes().add(theGraphPackage.getGEdge());

        // Initialize classes, features, and operations; add parameters
        initEClass(baseElementEClass, BaseElement.class, "BaseElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBaseElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flowElementEClass, FlowElement.class, "FlowElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowElement_Symbol(), ecorePackage.getEString(), "symbol", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(taskNodeEClass, TaskNode.class, "TaskNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(gatewayNodeEClass, GatewayNode.class, "GatewayNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(eventNodeEClass, EventNode.class, "EventNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iconEClass, Icon.class, "Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sequenceFlowEClass, SequenceFlow.class, "SequenceFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSequenceFlow_Name(), ecorePackage.getEString(), "name", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSequenceFlow_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSequenceFlow_DefaultFlow(), ecorePackage.getEBoolean(), "defaultFlow", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //BpmnPackageImpl
