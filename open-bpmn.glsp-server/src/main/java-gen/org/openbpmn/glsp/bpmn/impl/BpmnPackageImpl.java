/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GraphPackageImpl;

import org.openbpmn.glsp.bpmn.BPMNGEdge;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.BpmnFactory;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.bpmn.DataStoreGNode;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.bpmn.GroupGNode;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.bpmn.LaneGNode;
import org.openbpmn.glsp.bpmn.MessageGNode;
import org.openbpmn.glsp.bpmn.PoolGNode;
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
    private EClass bpmngNodeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass bpmngEdgeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iconGCompartmentEClass = null;

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
	private EClass dataStoreGNodeEClass = null;

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
    @Override
				public EClass getBPMNGNode() {
		return bpmngNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EAttribute getBPMNGNode_Name() {
		return (EAttribute)bpmngNodeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EAttribute getBPMNGNode_Kind() {
		return (EAttribute)bpmngNodeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getBPMNGEdge() {
		return bpmngEdgeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EAttribute getBPMNGEdge_Name() {
		return (EAttribute)bpmngEdgeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EAttribute getBPMNGEdge_Kind() {
		return (EAttribute)bpmngEdgeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getIconGCompartment() {
		return iconGCompartmentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getTaskGNode() {
		return taskGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getGatewayGNode() {
		return gatewayGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getEventGNode() {
		return eventGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getLabelGNode() {
		return labelGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getDataObjectGNode() {
		return dataObjectGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDataStoreGNode() {
		return dataStoreGNodeEClass;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getMessageGNode() {
		return messageGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getGroupGNode() {
		return groupGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getTextAnnotationGNode() {
		return textAnnotationGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getPoolGNode() {
		return poolGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EClass getLaneGNode() {
		return laneGNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
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
		bpmngNodeEClass = createEClass(BPMNG_NODE);
		createEAttribute(bpmngNodeEClass, BPMNG_NODE__NAME);
		createEAttribute(bpmngNodeEClass, BPMNG_NODE__KIND);

		bpmngEdgeEClass = createEClass(BPMNG_EDGE);
		createEAttribute(bpmngEdgeEClass, BPMNG_EDGE__NAME);
		createEAttribute(bpmngEdgeEClass, BPMNG_EDGE__KIND);

		iconGCompartmentEClass = createEClass(ICON_GCOMPARTMENT);

		taskGNodeEClass = createEClass(TASK_GNODE);

		gatewayGNodeEClass = createEClass(GATEWAY_GNODE);

		eventGNodeEClass = createEClass(EVENT_GNODE);

		labelGNodeEClass = createEClass(LABEL_GNODE);

		dataObjectGNodeEClass = createEClass(DATA_OBJECT_GNODE);

		dataStoreGNodeEClass = createEClass(DATA_STORE_GNODE);

		messageGNodeEClass = createEClass(MESSAGE_GNODE);

		groupGNodeEClass = createEClass(GROUP_GNODE);

		textAnnotationGNodeEClass = createEClass(TEXT_ANNOTATION_GNODE);

		poolGNodeEClass = createEClass(POOL_GNODE);

		laneGNodeEClass = createEClass(LANE_GNODE);
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
		bpmngNodeEClass.getESuperTypes().add(theGraphPackage.getGNode());
		bpmngEdgeEClass.getESuperTypes().add(theGraphPackage.getGEdge());
		iconGCompartmentEClass.getESuperTypes().add(theGraphPackage.getGCompartment());
		taskGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		gatewayGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		eventGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		labelGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		dataObjectGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		dataStoreGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		messageGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		groupGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		textAnnotationGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		poolGNodeEClass.getESuperTypes().add(this.getBPMNGNode());
		laneGNodeEClass.getESuperTypes().add(this.getBPMNGNode());

		// Initialize classes, features, and operations; add parameters
		initEClass(bpmngNodeEClass, BPMNGNode.class, "BPMNGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBPMNGNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, BPMNGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBPMNGNode_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, BPMNGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bpmngEdgeEClass, BPMNGEdge.class, "BPMNGEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBPMNGEdge_Name(), ecorePackage.getEString(), "name", null, 0, 1, BPMNGEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBPMNGEdge_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, BPMNGEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iconGCompartmentEClass, IconGCompartment.class, "IconGCompartment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(taskGNodeEClass, TaskGNode.class, "TaskGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gatewayGNodeEClass, GatewayGNode.class, "GatewayGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eventGNodeEClass, EventGNode.class, "EventGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelGNodeEClass, LabelGNode.class, "LabelGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataObjectGNodeEClass, DataObjectGNode.class, "DataObjectGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataStoreGNodeEClass, DataStoreGNode.class, "DataStoreGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(messageGNodeEClass, MessageGNode.class, "MessageGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(groupGNodeEClass, GroupGNode.class, "GroupGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textAnnotationGNodeEClass, TextAnnotationGNode.class, "TextAnnotationGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(poolGNodeEClass, PoolGNode.class, "PoolGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(laneGNodeEClass, LaneGNode.class, "LaneGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //BpmnPackageImpl
