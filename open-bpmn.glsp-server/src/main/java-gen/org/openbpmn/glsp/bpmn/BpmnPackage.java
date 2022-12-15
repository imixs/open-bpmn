/**
 */
package org.openbpmn.glsp.bpmn;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.glsp.graph.GraphPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openbpmn.glsp.bpmn.BpmnFactory
 * @model kind="package"
 * @generated
 */
public interface BpmnPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "bpmn";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.openbpmn.org/bpmn/glsp";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "bpmn";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BpmnPackage eINSTANCE = org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl.init();

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNGNodeImpl <em>BPMNG Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.BPMNGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNGNode()
     * @generated
     */
    int BPMNG_NODE = 0;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__ARGS = GraphPackage.GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__ID = GraphPackage.GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__CSS_CLASSES = GraphPackage.GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__CHILDREN = GraphPackage.GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__PARENT = GraphPackage.GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__TRACE = GraphPackage.GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__TYPE = GraphPackage.GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__POSITION = GraphPackage.GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__SIZE = GraphPackage.GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__EDGE_PLACEMENT = GraphPackage.GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__LAYOUT = GraphPackage.GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__LAYOUT_OPTIONS = GraphPackage.GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__NAME = GraphPackage.GNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE__KIND = GraphPackage.GNODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>BPMNG Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE_FEATURE_COUNT = GraphPackage.GNODE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>BPMNG Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_NODE_OPERATION_COUNT = GraphPackage.GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNGEdgeImpl <em>BPMNG Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.BPMNGEdgeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNGEdge()
     * @generated
     */
    int BPMNG_EDGE = 1;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__ARGS = GraphPackage.GEDGE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__ID = GraphPackage.GEDGE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__CSS_CLASSES = GraphPackage.GEDGE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__CHILDREN = GraphPackage.GEDGE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__PARENT = GraphPackage.GEDGE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__TRACE = GraphPackage.GEDGE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__TYPE = GraphPackage.GEDGE__TYPE;

    /**
     * The feature id for the '<em><b>Routing Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__ROUTING_POINTS = GraphPackage.GEDGE__ROUTING_POINTS;

    /**
     * The feature id for the '<em><b>Source Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__SOURCE_ID = GraphPackage.GEDGE__SOURCE_ID;

    /**
     * The feature id for the '<em><b>Target Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__TARGET_ID = GraphPackage.GEDGE__TARGET_ID;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__SOURCE = GraphPackage.GEDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__TARGET = GraphPackage.GEDGE__TARGET;

    /**
     * The feature id for the '<em><b>Router Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__ROUTER_KIND = GraphPackage.GEDGE__ROUTER_KIND;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__NAME = GraphPackage.GEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE__KIND = GraphPackage.GEDGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>BPMNG Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>BPMNG Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMNG_EDGE_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.IconGCompartmentImpl <em>Icon GCompartment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.IconGCompartmentImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIconGCompartment()
     * @generated
     */
    int ICON_GCOMPARTMENT = 2;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__ARGS = GraphPackage.GCOMPARTMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__ID = GraphPackage.GCOMPARTMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__CSS_CLASSES = GraphPackage.GCOMPARTMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__CHILDREN = GraphPackage.GCOMPARTMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__PARENT = GraphPackage.GCOMPARTMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__TRACE = GraphPackage.GCOMPARTMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__TYPE = GraphPackage.GCOMPARTMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__POSITION = GraphPackage.GCOMPARTMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__SIZE = GraphPackage.GCOMPARTMENT__SIZE;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__LAYOUT = GraphPackage.GCOMPARTMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT__LAYOUT_OPTIONS = GraphPackage.GCOMPARTMENT__LAYOUT_OPTIONS;

    /**
     * The number of structural features of the '<em>Icon GCompartment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT_FEATURE_COUNT = GraphPackage.GCOMPARTMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Icon GCompartment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GCOMPARTMENT_OPERATION_COUNT = GraphPackage.GCOMPARTMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl <em>Task GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTaskGNode()
     * @generated
     */
    int TASK_GNODE = 3;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Task GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Task GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl <em>Gateway GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGatewayGNode()
     * @generated
     */
    int GATEWAY_GNODE = 4;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Gateway GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Gateway GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.EventGNodeImpl <em>Event GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.EventGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getEventGNode()
     * @generated
     */
    int EVENT_GNODE = 5;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Event GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Event GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl <em>Label GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLabelGNode()
     * @generated
     */
    int LABEL_GNODE = 6;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Label GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Label GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl <em>Data Object GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getDataObjectGNode()
     * @generated
     */
    int DATA_OBJECT_GNODE = 7;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Data Object GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Data Object GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl <em>Message GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getMessageGNode()
     * @generated
     */
    int MESSAGE_GNODE = 8;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Message GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Message GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl <em>Group GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGroupGNode()
     * @generated
     */
    int GROUP_GNODE = 9;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Group GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Group GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl <em>Text Annotation GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTextAnnotationGNode()
     * @generated
     */
    int TEXT_ANNOTATION_GNODE = 10;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Text Annotation GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Text Annotation GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl <em>Pool GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getPoolGNode()
     * @generated
     */
    int POOL_GNODE = 11;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Pool GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Pool GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl <em>Lane GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLaneGNode()
     * @generated
     */
    int LANE_GNODE = 12;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__ARGS = BPMNG_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__ID = BPMNG_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__CSS_CLASSES = BPMNG_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__CHILDREN = BPMNG_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__PARENT = BPMNG_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__TRACE = BPMNG_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__TYPE = BPMNG_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__POSITION = BPMNG_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__SIZE = BPMNG_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__EDGE_PLACEMENT = BPMNG_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__LAYOUT = BPMNG_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__LAYOUT_OPTIONS = BPMNG_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__NAME = BPMNG_NODE__NAME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__KIND = BPMNG_NODE__KIND;

    /**
     * The number of structural features of the '<em>Lane GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE_FEATURE_COUNT = BPMNG_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Lane GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE_OPERATION_COUNT = BPMNG_NODE_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.BPMNGNode <em>BPMNG Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>BPMNG Node</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGNode
     * @generated
     */
    EClass getBPMNGNode();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNGNode#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGNode#getName()
     * @see #getBPMNGNode()
     * @generated
     */
    EAttribute getBPMNGNode_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNGNode#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGNode#getKind()
     * @see #getBPMNGNode()
     * @generated
     */
    EAttribute getBPMNGNode_Kind();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.BPMNGEdge <em>BPMNG Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>BPMNG Edge</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGEdge
     * @generated
     */
    EClass getBPMNGEdge();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGEdge#getName()
     * @see #getBPMNGEdge()
     * @generated
     */
    EAttribute getBPMNGEdge_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNGEdge#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNGEdge#getKind()
     * @see #getBPMNGEdge()
     * @generated
     */
    EAttribute getBPMNGEdge_Kind();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.IconGCompartment <em>Icon GCompartment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Icon GCompartment</em>'.
     * @see org.openbpmn.glsp.bpmn.IconGCompartment
     * @generated
     */
    EClass getIconGCompartment();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.TaskGNode <em>Task GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.TaskGNode
     * @generated
     */
    EClass getTaskGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.GatewayGNode <em>Gateway GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Gateway GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.GatewayGNode
     * @generated
     */
    EClass getGatewayGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.EventGNode <em>Event GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Event GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.EventGNode
     * @generated
     */
    EClass getEventGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.LabelGNode <em>Label GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.LabelGNode
     * @generated
     */
    EClass getLabelGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.DataObjectGNode <em>Data Object GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Object GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.DataObjectGNode
     * @generated
     */
    EClass getDataObjectGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.MessageGNode <em>Message GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.MessageGNode
     * @generated
     */
    EClass getMessageGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.GroupGNode <em>Group GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.GroupGNode
     * @generated
     */
    EClass getGroupGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.TextAnnotationGNode <em>Text Annotation GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Annotation GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.TextAnnotationGNode
     * @generated
     */
    EClass getTextAnnotationGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.PoolGNode <em>Pool GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pool GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.PoolGNode
     * @generated
     */
    EClass getPoolGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.LaneGNode <em>Lane GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.LaneGNode
     * @generated
     */
    EClass getLaneGNode();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BpmnFactory getBpmnFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNGNodeImpl <em>BPMNG Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.BPMNGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNGNode()
         * @generated
         */
        EClass BPMNG_NODE = eINSTANCE.getBPMNGNode();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMNG_NODE__NAME = eINSTANCE.getBPMNGNode_Name();

        /**
         * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMNG_NODE__KIND = eINSTANCE.getBPMNGNode_Kind();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNGEdgeImpl <em>BPMNG Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.BPMNGEdgeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNGEdge()
         * @generated
         */
        EClass BPMNG_EDGE = eINSTANCE.getBPMNGEdge();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMNG_EDGE__NAME = eINSTANCE.getBPMNGEdge_Name();

        /**
         * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMNG_EDGE__KIND = eINSTANCE.getBPMNGEdge_Kind();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.IconGCompartmentImpl <em>Icon GCompartment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.IconGCompartmentImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIconGCompartment()
         * @generated
         */
        EClass ICON_GCOMPARTMENT = eINSTANCE.getIconGCompartment();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl <em>Task GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTaskGNode()
         * @generated
         */
        EClass TASK_GNODE = eINSTANCE.getTaskGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl <em>Gateway GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGatewayGNode()
         * @generated
         */
        EClass GATEWAY_GNODE = eINSTANCE.getGatewayGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.EventGNodeImpl <em>Event GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.EventGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getEventGNode()
         * @generated
         */
        EClass EVENT_GNODE = eINSTANCE.getEventGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl <em>Label GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLabelGNode()
         * @generated
         */
        EClass LABEL_GNODE = eINSTANCE.getLabelGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl <em>Data Object GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getDataObjectGNode()
         * @generated
         */
        EClass DATA_OBJECT_GNODE = eINSTANCE.getDataObjectGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl <em>Message GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getMessageGNode()
         * @generated
         */
        EClass MESSAGE_GNODE = eINSTANCE.getMessageGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl <em>Group GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGroupGNode()
         * @generated
         */
        EClass GROUP_GNODE = eINSTANCE.getGroupGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl <em>Text Annotation GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTextAnnotationGNode()
         * @generated
         */
        EClass TEXT_ANNOTATION_GNODE = eINSTANCE.getTextAnnotationGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl <em>Pool GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getPoolGNode()
         * @generated
         */
        EClass POOL_GNODE = eINSTANCE.getPoolGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl <em>Lane GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLaneGNode()
         * @generated
         */
        EClass LANE_GNODE = eINSTANCE.getLaneGNode();

    }

} //BpmnPackage
