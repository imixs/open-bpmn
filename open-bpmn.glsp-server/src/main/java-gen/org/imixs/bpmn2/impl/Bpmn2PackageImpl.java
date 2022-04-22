/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GraphPackageImpl;

import org.imixs.bpmn2.Activity;
import org.imixs.bpmn2.BaseElement;
import org.imixs.bpmn2.Bpmn2Factory;
import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.CatchEvent;
import org.imixs.bpmn2.ComplexGateway;
import org.imixs.bpmn2.EndEvent;
import org.imixs.bpmn2.Event;
import org.imixs.bpmn2.ExclusiveGateway;
import org.imixs.bpmn2.Expression;
import org.imixs.bpmn2.FlowElement;
import org.imixs.bpmn2.FlowNode;
import org.imixs.bpmn2.Gateway;
import org.imixs.bpmn2.Icon;
import org.imixs.bpmn2.IncusiveGateway;
import org.imixs.bpmn2.ParallelGateway;
import org.imixs.bpmn2.Pool;
import org.imixs.bpmn2.SequenceFlow;
import org.imixs.bpmn2.StartEvent;
import org.imixs.bpmn2.Task;
import org.imixs.bpmn2.ThrowEvent;

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
    private EClass expressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sequenceFlowEClass = null;

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
    private EClass gatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parallelGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass exclusiveGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass incusiveGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass complexGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass eventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass catchEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass throwEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass startEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endEventEClass = null;

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
    private EClass poolEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iconEClass = null;

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
     * @see org.imixs.bpmn2.Bpmn2Package#eNS_URI
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
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(GraphPackage.eNS_URI);
        GraphPackageImpl theGraphPackage = (GraphPackageImpl)(registeredPackage instanceof GraphPackageImpl ? registeredPackage : GraphPackage.eINSTANCE);

        // Create package meta-data objects
        theBpmn2Package.createPackageContents();
        theGraphPackage.createPackageContents();

        // Initialize created meta-data
        theBpmn2Package.initializePackageContents();
        theGraphPackage.initializePackageContents();

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
    public EAttribute getBaseElement_Documentation() {
        return (EAttribute)baseElementEClass.getEStructuralFeatures().get(1);
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
    public EAttribute getFlowElement_Category() {
        return (EAttribute)flowElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpression() {
        return expressionEClass;
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
    public EReference getSequenceFlow_ConditionExpression() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSequenceFlow_SourceRef() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSequenceFlow_TargetRef() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(2);
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
    public EReference getFlowNode_Incoming() {
        return (EReference)flowNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFlowNode_Outgoing() {
        return (EReference)flowNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGateway() {
        return gatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParallelGateway() {
        return parallelGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExclusiveGateway() {
        return exclusiveGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExclusiveGateway_DefaultSequenceFlow() {
        return (EReference)exclusiveGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIncusiveGateway() {
        return incusiveGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIncusiveGateway_DefaultSequenceFlow() {
        return (EReference)incusiveGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComplexGateway() {
        return complexGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComplexGateway_DefaultSequenceFlow() {
        return (EReference)complexGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEvent() {
        return eventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCatchEvent() {
        return catchEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getThrowEvent() {
        return throwEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStartEvent() {
        return startEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStartEvent_IsInterrupting() {
        return (EAttribute)startEventEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndEvent() {
        return endEventEClass;
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
    public EClass getTask() {
        return taskEClass;
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
        baseElementEClass = createEClass(BASE_ELEMENT);
        createEAttribute(baseElementEClass, BASE_ELEMENT__NAME);
        createEAttribute(baseElementEClass, BASE_ELEMENT__DOCUMENTATION);

        flowElementEClass = createEClass(FLOW_ELEMENT);
        createEAttribute(flowElementEClass, FLOW_ELEMENT__CATEGORY);

        expressionEClass = createEClass(EXPRESSION);

        sequenceFlowEClass = createEClass(SEQUENCE_FLOW);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__CONDITION_EXPRESSION);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__SOURCE_REF);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__TARGET_REF);

        flowNodeEClass = createEClass(FLOW_NODE);
        createEReference(flowNodeEClass, FLOW_NODE__INCOMING);
        createEReference(flowNodeEClass, FLOW_NODE__OUTGOING);

        gatewayEClass = createEClass(GATEWAY);

        parallelGatewayEClass = createEClass(PARALLEL_GATEWAY);

        exclusiveGatewayEClass = createEClass(EXCLUSIVE_GATEWAY);
        createEReference(exclusiveGatewayEClass, EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        incusiveGatewayEClass = createEClass(INCUSIVE_GATEWAY);
        createEReference(incusiveGatewayEClass, INCUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        complexGatewayEClass = createEClass(COMPLEX_GATEWAY);
        createEReference(complexGatewayEClass, COMPLEX_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        eventEClass = createEClass(EVENT);

        catchEventEClass = createEClass(CATCH_EVENT);

        throwEventEClass = createEClass(THROW_EVENT);

        startEventEClass = createEClass(START_EVENT);
        createEAttribute(startEventEClass, START_EVENT__IS_INTERRUPTING);

        endEventEClass = createEClass(END_EVENT);

        activityEClass = createEClass(ACTIVITY);

        taskEClass = createEClass(TASK);

        poolEClass = createEClass(POOL);

        iconEClass = createEClass(ICON);
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
        expressionEClass.getESuperTypes().add(this.getBaseElement());
        sequenceFlowEClass.getESuperTypes().add(theGraphPackage.getGEdge());
        sequenceFlowEClass.getESuperTypes().add(this.getFlowElement());
        flowNodeEClass.getESuperTypes().add(this.getFlowElement());
        gatewayEClass.getESuperTypes().add(this.getFlowElement());
        parallelGatewayEClass.getESuperTypes().add(this.getGateway());
        exclusiveGatewayEClass.getESuperTypes().add(this.getGateway());
        incusiveGatewayEClass.getESuperTypes().add(this.getGateway());
        complexGatewayEClass.getESuperTypes().add(this.getGateway());
        eventEClass.getESuperTypes().add(this.getFlowNode());
        catchEventEClass.getESuperTypes().add(this.getEvent());
        throwEventEClass.getESuperTypes().add(this.getEvent());
        startEventEClass.getESuperTypes().add(this.getCatchEvent());
        endEventEClass.getESuperTypes().add(this.getThrowEvent());
        activityEClass.getESuperTypes().add(this.getFlowNode());
        taskEClass.getESuperTypes().add(this.getActivity());
        poolEClass.getESuperTypes().add(this.getBaseElement());
        iconEClass.getESuperTypes().add(theGraphPackage.getGCompartment());

        // Initialize classes, features, and operations; add parameters
        initEClass(baseElementEClass, BaseElement.class, "BaseElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBaseElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBaseElement_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flowElementEClass, FlowElement.class, "FlowElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowElement_Category(), ecorePackage.getEString(), "category", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sequenceFlowEClass, SequenceFlow.class, "SequenceFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSequenceFlow_ConditionExpression(), this.getExpression(), null, "conditionExpression", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSequenceFlow_SourceRef(), this.getFlowNode(), this.getFlowNode_Outgoing(), "sourceRef", null, 1, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSequenceFlow_TargetRef(), this.getFlowNode(), this.getFlowNode_Incoming(), "targetRef", null, 1, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(flowNodeEClass, FlowNode.class, "FlowNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFlowNode_Incoming(), this.getSequenceFlow(), this.getSequenceFlow_TargetRef(), "incoming", null, 0, -1, FlowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlowNode_Outgoing(), this.getSequenceFlow(), this.getSequenceFlow_SourceRef(), "outgoing", null, 0, -1, FlowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gatewayEClass, Gateway.class, "Gateway", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(parallelGatewayEClass, ParallelGateway.class, "ParallelGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(exclusiveGatewayEClass, ExclusiveGateway.class, "ExclusiveGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExclusiveGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, ExclusiveGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(incusiveGatewayEClass, IncusiveGateway.class, "IncusiveGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIncusiveGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, IncusiveGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(complexGatewayEClass, ComplexGateway.class, "ComplexGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComplexGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, ComplexGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(eventEClass, Event.class, "Event", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(catchEventEClass, CatchEvent.class, "CatchEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(throwEventEClass, ThrowEvent.class, "ThrowEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(startEventEClass, StartEvent.class, "StartEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStartEvent_IsInterrupting(), ecorePackage.getEBoolean(), "isInterrupting", "true", 0, 1, StartEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(endEventEClass, EndEvent.class, "EndEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(activityEClass, Activity.class, "Activity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iconEClass, Icon.class, "Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
          (expressionEClass,
           source,
           new String[] {
               "name", "tExpression",
               "kind", "mixed"
           });
        addAnnotation
          (getSequenceFlow_ConditionExpression(),
           source,
           new String[] {
               "kind", "element",
               "name", "conditionExpression"
           });
        addAnnotation
          (getSequenceFlow_SourceRef(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "sourceRef"
           });
        addAnnotation
          (getSequenceFlow_TargetRef(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "targetRef"
           });
        addAnnotation
          (flowNodeEClass,
           source,
           new String[] {
               "name", "tFlowNode",
               "kind", "elementOnly"
           });
        addAnnotation
          (getFlowNode_Incoming(),
           source,
           new String[] {
               "kind", "element",
               "name", "incoming",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/MODEL"
           });
        addAnnotation
          (getFlowNode_Outgoing(),
           source,
           new String[] {
               "kind", "element",
               "name", "outgoing",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/MODEL"
           });
        addAnnotation
          (exclusiveGatewayEClass,
           source,
           new String[] {
               "name", "tExclusiveGateway",
               "kind", "elementOnly"
           });
        addAnnotation
          (getExclusiveGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (getIncusiveGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (getComplexGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (startEventEClass,
           source,
           new String[] {
               "name", "tStartEvent",
               "kind", "elementOnly"
           });
        addAnnotation
          (getStartEvent_IsInterrupting(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "isInterrupting"
           });
        addAnnotation
          (endEventEClass,
           source,
           new String[] {
               "name", "tEndEvent",
               "kind", "elementOnly"
           });
    }

} //Bpmn2PackageImpl
