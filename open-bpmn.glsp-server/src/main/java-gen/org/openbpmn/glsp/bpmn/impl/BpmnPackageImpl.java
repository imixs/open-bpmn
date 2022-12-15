/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GraphPackageImpl;

import org.openbpmn.glsp.bpmn.AssociationGNode;
import org.openbpmn.glsp.bpmn.BaseElementGNode;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.bpmn.FlowElementGNode;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.bpmn.GroupGNode;
import org.openbpmn.glsp.bpmn.IconGNode;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.bpmn.LaneGNode;
import org.openbpmn.glsp.bpmn.MessageFlowGNode;
import org.openbpmn.glsp.bpmn.MessageGNode;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.bpmn.SequenceFlowGNode;
import org.openbpmn.glsp.bpmn.TaskGNode;
import org.openbpmn.glsp.bpmn.TextAnnotationGNode;

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
    private EClass baseElementGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass flowElementGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass taskGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass gatewayGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass eventGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataObjectGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass messageGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass groupGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass textAnnotationGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass poolGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass laneGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iconGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sequenceFlowGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass associationGNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass messageFlowGNodeEClass = null;

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
    public EClass getBaseElementGNode() {
        return baseElementGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseElementGNode_Name() {
        return (EAttribute)baseElementGNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFlowElementGNode() {
        return flowElementGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFlowElementGNode_Symbol() {
        return (EAttribute)flowElementGNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTaskGNode() {
        return taskGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGatewayGNode() {
        return gatewayGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEventGNode() {
        return eventGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelGNode() {
        return labelGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataObjectGNode() {
        return dataObjectGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMessageGNode() {
        return messageGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGroupGNode() {
        return groupGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTextAnnotationGNode() {
        return textAnnotationGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPoolGNode() {
        return poolGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLaneGNode() {
        return laneGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIconGNode() {
        return iconGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSequenceFlowGNode() {
        return sequenceFlowGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlowGNode_Name() {
        return (EAttribute)sequenceFlowGNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlowGNode_Condition() {
        return (EAttribute)sequenceFlowGNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceFlowGNode_DefaultFlow() {
        return (EAttribute)sequenceFlowGNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAssociationGNode() {
        return associationGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAssociationGNode_Name() {
        return (EAttribute)associationGNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAssociationGNode_AssociationDirection() {
        return (EAttribute)associationGNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMessageFlowGNode() {
        return messageFlowGNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMessageFlowGNode_Name() {
        return (EAttribute)messageFlowGNodeEClass.getEStructuralFeatures().get(0);
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
        baseElementGNodeEClass = createEClass(BASE_ELEMENT_GNODE);
        createEAttribute(baseElementGNodeEClass, BASE_ELEMENT_GNODE__NAME);

        flowElementGNodeEClass = createEClass(FLOW_ELEMENT_GNODE);
        createEAttribute(flowElementGNodeEClass, FLOW_ELEMENT_GNODE__SYMBOL);

        taskGNodeEClass = createEClass(TASK_GNODE);

        gatewayGNodeEClass = createEClass(GATEWAY_GNODE);

        eventGNodeEClass = createEClass(EVENT_GNODE);

        labelGNodeEClass = createEClass(LABEL_GNODE);

        dataObjectGNodeEClass = createEClass(DATA_OBJECT_GNODE);

        messageGNodeEClass = createEClass(MESSAGE_GNODE);

        groupGNodeEClass = createEClass(GROUP_GNODE);

        textAnnotationGNodeEClass = createEClass(TEXT_ANNOTATION_GNODE);

        poolGNodeEClass = createEClass(POOL_GNODE);

        laneGNodeEClass = createEClass(LANE_GNODE);

        iconGNodeEClass = createEClass(ICON_GNODE);

        sequenceFlowGNodeEClass = createEClass(SEQUENCE_FLOW_GNODE);
        createEAttribute(sequenceFlowGNodeEClass, SEQUENCE_FLOW_GNODE__NAME);
        createEAttribute(sequenceFlowGNodeEClass, SEQUENCE_FLOW_GNODE__CONDITION);
        createEAttribute(sequenceFlowGNodeEClass, SEQUENCE_FLOW_GNODE__DEFAULT_FLOW);

        associationGNodeEClass = createEClass(ASSOCIATION_GNODE);
        createEAttribute(associationGNodeEClass, ASSOCIATION_GNODE__NAME);
        createEAttribute(associationGNodeEClass, ASSOCIATION_GNODE__ASSOCIATION_DIRECTION);

        messageFlowGNodeEClass = createEClass(MESSAGE_FLOW_GNODE);
        createEAttribute(messageFlowGNodeEClass, MESSAGE_FLOW_GNODE__NAME);
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
        baseElementGNodeEClass.getESuperTypes().add(theGraphPackage.getGNode());
        flowElementGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        taskGNodeEClass.getESuperTypes().add(this.getFlowElementGNode());
        gatewayGNodeEClass.getESuperTypes().add(this.getFlowElementGNode());
        eventGNodeEClass.getESuperTypes().add(this.getFlowElementGNode());
        labelGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        dataObjectGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        messageGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        groupGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        textAnnotationGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        poolGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        laneGNodeEClass.getESuperTypes().add(this.getBaseElementGNode());
        iconGNodeEClass.getESuperTypes().add(theGraphPackage.getGCompartment());
        sequenceFlowGNodeEClass.getESuperTypes().add(theGraphPackage.getGEdge());
        associationGNodeEClass.getESuperTypes().add(theGraphPackage.getGEdge());
        messageFlowGNodeEClass.getESuperTypes().add(theGraphPackage.getGEdge());

        // Initialize classes, features, and operations; add parameters
        initEClass(baseElementGNodeEClass, BaseElementGNode.class, "BaseElementGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBaseElementGNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseElementGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flowElementGNodeEClass, FlowElementGNode.class, "FlowElementGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowElementGNode_Symbol(), ecorePackage.getEString(), "symbol", null, 0, 1, FlowElementGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(taskGNodeEClass, TaskGNode.class, "TaskGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(gatewayGNodeEClass, GatewayGNode.class, "GatewayGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(eventGNodeEClass, EventGNode.class, "EventGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(labelGNodeEClass, LabelGNode.class, "LabelGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dataObjectGNodeEClass, DataObjectGNode.class, "DataObjectGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(messageGNodeEClass, MessageGNode.class, "MessageGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(groupGNodeEClass, GroupGNode.class, "GroupGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(textAnnotationGNodeEClass, TextAnnotationGNode.class, "TextAnnotationGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(poolGNodeEClass, PoolGNode.class, "PoolGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(laneGNodeEClass, LaneGNode.class, "LaneGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iconGNodeEClass, IconGNode.class, "IconGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sequenceFlowGNodeEClass, SequenceFlowGNode.class, "SequenceFlowGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSequenceFlowGNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, SequenceFlowGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSequenceFlowGNode_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, SequenceFlowGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSequenceFlowGNode_DefaultFlow(), ecorePackage.getEBoolean(), "defaultFlow", null, 0, 1, SequenceFlowGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(associationGNodeEClass, AssociationGNode.class, "AssociationGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAssociationGNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, AssociationGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAssociationGNode_AssociationDirection(), ecorePackage.getEString(), "associationDirection", null, 0, 1, AssociationGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(messageFlowGNodeEClass, MessageFlowGNode.class, "MessageFlowGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMessageFlowGNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, MessageFlowGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //BpmnPackageImpl
