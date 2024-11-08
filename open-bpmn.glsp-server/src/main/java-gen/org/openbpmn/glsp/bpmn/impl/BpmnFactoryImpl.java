/**
 */
package org.openbpmn.glsp.bpmn.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openbpmn.glsp.bpmn.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnFactoryImpl extends EFactoryImpl implements BpmnFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static BpmnFactory init() {
		try {
			BpmnFactory theBpmnFactory = (BpmnFactory)EPackage.Registry.INSTANCE.getEFactory(BpmnPackage.eNS_URI);
			if (theBpmnFactory != null) {
				return theBpmnFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BpmnFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BpmnFactoryImpl() {
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
			case BpmnPackage.BPMNG_NODE: return createBPMNGNode();
			case BpmnPackage.BPMNG_EDGE: return createBPMNGEdge();
			case BpmnPackage.ICON_GCOMPARTMENT: return createIconGCompartment();
			case BpmnPackage.TASK_GNODE: return createTaskGNode();
			case BpmnPackage.GATEWAY_GNODE: return createGatewayGNode();
			case BpmnPackage.EVENT_GNODE: return createEventGNode();
			case BpmnPackage.LABEL_GNODE: return createLabelGNode();
			case BpmnPackage.DATA_OBJECT_GNODE: return createDataObjectGNode();
			case BpmnPackage.DATA_STORE_GNODE: return createDataStoreGNode();
			case BpmnPackage.MESSAGE_GNODE: return createMessageGNode();
			case BpmnPackage.GROUP_GNODE: return createGroupGNode();
			case BpmnPackage.TEXT_ANNOTATION_GNODE: return createTextAnnotationGNode();
			case BpmnPackage.POOL_GNODE: return createPoolGNode();
			case BpmnPackage.LANE_GNODE: return createLaneGNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public BPMNGNode createBPMNGNode() {
		BPMNGNodeImpl bpmngNode = new BPMNGNodeImpl();
		return bpmngNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public BPMNGEdge createBPMNGEdge() {
		BPMNGEdgeImpl bpmngEdge = new BPMNGEdgeImpl();
		return bpmngEdge;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public IconGCompartment createIconGCompartment() {
		IconGCompartmentImpl iconGCompartment = new IconGCompartmentImpl();
		return iconGCompartment;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public TaskGNode createTaskGNode() {
		TaskGNodeImpl taskGNode = new TaskGNodeImpl();
		return taskGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public GatewayGNode createGatewayGNode() {
		GatewayGNodeImpl gatewayGNode = new GatewayGNodeImpl();
		return gatewayGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EventGNode createEventGNode() {
		EventGNodeImpl eventGNode = new EventGNodeImpl();
		return eventGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public LabelGNode createLabelGNode() {
		LabelGNodeImpl labelGNode = new LabelGNodeImpl();
		return labelGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public DataObjectGNode createDataObjectGNode() {
		DataObjectGNodeImpl dataObjectGNode = new DataObjectGNodeImpl();
		return dataObjectGNode;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataStoreGNode createDataStoreGNode() {
		DataStoreGNodeImpl dataStoreGNode = new DataStoreGNodeImpl();
		return dataStoreGNode;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public MessageGNode createMessageGNode() {
		MessageGNodeImpl messageGNode = new MessageGNodeImpl();
		return messageGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public GroupGNode createGroupGNode() {
		GroupGNodeImpl groupGNode = new GroupGNodeImpl();
		return groupGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public TextAnnotationGNode createTextAnnotationGNode() {
		TextAnnotationGNodeImpl textAnnotationGNode = new TextAnnotationGNodeImpl();
		return textAnnotationGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public PoolGNode createPoolGNode() {
		PoolGNodeImpl poolGNode = new PoolGNodeImpl();
		return poolGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public LaneGNode createLaneGNode() {
		LaneGNodeImpl laneGNode = new LaneGNodeImpl();
		return laneGNode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public BpmnPackage getBpmnPackage() {
		return (BpmnPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static BpmnPackage getPackage() {
		return BpmnPackage.eINSTANCE;
	}

} //BpmnFactoryImpl
