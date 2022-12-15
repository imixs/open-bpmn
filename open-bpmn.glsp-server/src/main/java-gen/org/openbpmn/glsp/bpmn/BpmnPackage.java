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
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.BaseElementGNodeImpl <em>Base Element GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.BaseElementGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBaseElementGNode()
     * @generated
     */
    int BASE_ELEMENT_GNODE = 0;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__ARGS = GraphPackage.GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__ID = GraphPackage.GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__CSS_CLASSES = GraphPackage.GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__CHILDREN = GraphPackage.GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__PARENT = GraphPackage.GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__TRACE = GraphPackage.GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__TYPE = GraphPackage.GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__POSITION = GraphPackage.GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__SIZE = GraphPackage.GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__EDGE_PLACEMENT = GraphPackage.GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__LAYOUT = GraphPackage.GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__LAYOUT_OPTIONS = GraphPackage.GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE__NAME = GraphPackage.GNODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Base Element GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE_FEATURE_COUNT = GraphPackage.GNODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Base Element GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_GNODE_OPERATION_COUNT = GraphPackage.GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.FlowElementGNodeImpl <em>Flow Element GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.FlowElementGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getFlowElementGNode()
     * @generated
     */
    int FLOW_ELEMENT_GNODE = 1;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE__SYMBOL = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Flow Element GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Flow Element GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl <em>Task GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TaskGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTaskGNode()
     * @generated
     */
    int TASK_GNODE = 2;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__ARGS = FLOW_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__ID = FLOW_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__CSS_CLASSES = FLOW_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__CHILDREN = FLOW_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__PARENT = FLOW_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__TRACE = FLOW_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__TYPE = FLOW_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__POSITION = FLOW_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__SIZE = FLOW_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__EDGE_PLACEMENT = FLOW_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__LAYOUT = FLOW_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__LAYOUT_OPTIONS = FLOW_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__NAME = FLOW_ELEMENT_GNODE__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE__SYMBOL = FLOW_ELEMENT_GNODE__SYMBOL;

    /**
     * The number of structural features of the '<em>Task GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE_FEATURE_COUNT = FLOW_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Task GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_GNODE_OPERATION_COUNT = FLOW_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl <em>Gateway GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GatewayGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGatewayGNode()
     * @generated
     */
    int GATEWAY_GNODE = 3;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__ARGS = FLOW_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__ID = FLOW_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__CSS_CLASSES = FLOW_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__CHILDREN = FLOW_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__PARENT = FLOW_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__TRACE = FLOW_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__TYPE = FLOW_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__POSITION = FLOW_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__SIZE = FLOW_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__EDGE_PLACEMENT = FLOW_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__LAYOUT = FLOW_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__LAYOUT_OPTIONS = FLOW_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__NAME = FLOW_ELEMENT_GNODE__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE__SYMBOL = FLOW_ELEMENT_GNODE__SYMBOL;

    /**
     * The number of structural features of the '<em>Gateway GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE_FEATURE_COUNT = FLOW_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Gateway GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_GNODE_OPERATION_COUNT = FLOW_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.EventGNodeImpl <em>Event GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.EventGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getEventGNode()
     * @generated
     */
    int EVENT_GNODE = 4;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__ARGS = FLOW_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__ID = FLOW_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__CSS_CLASSES = FLOW_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__CHILDREN = FLOW_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__PARENT = FLOW_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__TRACE = FLOW_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__TYPE = FLOW_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__POSITION = FLOW_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__SIZE = FLOW_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__EDGE_PLACEMENT = FLOW_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__LAYOUT = FLOW_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__LAYOUT_OPTIONS = FLOW_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__NAME = FLOW_ELEMENT_GNODE__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE__SYMBOL = FLOW_ELEMENT_GNODE__SYMBOL;

    /**
     * The number of structural features of the '<em>Event GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE_FEATURE_COUNT = FLOW_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Event GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_GNODE_OPERATION_COUNT = FLOW_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl <em>Label GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LabelGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLabelGNode()
     * @generated
     */
    int LABEL_GNODE = 5;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Label GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Label GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl <em>Data Object GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.DataObjectGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getDataObjectGNode()
     * @generated
     */
    int DATA_OBJECT_GNODE = 6;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Data Object GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Data Object GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl <em>Message GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.MessageGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getMessageGNode()
     * @generated
     */
    int MESSAGE_GNODE = 7;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Message GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Message GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl <em>Group GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GroupGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGroupGNode()
     * @generated
     */
    int GROUP_GNODE = 8;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Group GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Group GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl <em>Text Annotation GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TextAnnotationGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTextAnnotationGNode()
     * @generated
     */
    int TEXT_ANNOTATION_GNODE = 9;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Text Annotation GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Text Annotation GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl <em>Pool GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.PoolGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getPoolGNode()
     * @generated
     */
    int POOL_GNODE = 10;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Pool GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Pool GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl <em>Lane GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LaneGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLaneGNode()
     * @generated
     */
    int LANE_GNODE = 11;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__ARGS = BASE_ELEMENT_GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__ID = BASE_ELEMENT_GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__CSS_CLASSES = BASE_ELEMENT_GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__CHILDREN = BASE_ELEMENT_GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__PARENT = BASE_ELEMENT_GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__TRACE = BASE_ELEMENT_GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__TYPE = BASE_ELEMENT_GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__POSITION = BASE_ELEMENT_GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__SIZE = BASE_ELEMENT_GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__EDGE_PLACEMENT = BASE_ELEMENT_GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__LAYOUT = BASE_ELEMENT_GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__LAYOUT_OPTIONS = BASE_ELEMENT_GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE__NAME = BASE_ELEMENT_GNODE__NAME;

    /**
     * The number of structural features of the '<em>Lane GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE_FEATURE_COUNT = BASE_ELEMENT_GNODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Lane GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_GNODE_OPERATION_COUNT = BASE_ELEMENT_GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.IconGNodeImpl <em>Icon GNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.IconGNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIconGNode()
     * @generated
     */
    int ICON_GNODE = 12;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__ARGS = GraphPackage.GCOMPARTMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__ID = GraphPackage.GCOMPARTMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__CSS_CLASSES = GraphPackage.GCOMPARTMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__CHILDREN = GraphPackage.GCOMPARTMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__PARENT = GraphPackage.GCOMPARTMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__TRACE = GraphPackage.GCOMPARTMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__TYPE = GraphPackage.GCOMPARTMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__POSITION = GraphPackage.GCOMPARTMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__SIZE = GraphPackage.GCOMPARTMENT__SIZE;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__LAYOUT = GraphPackage.GCOMPARTMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE__LAYOUT_OPTIONS = GraphPackage.GCOMPARTMENT__LAYOUT_OPTIONS;

    /**
     * The number of structural features of the '<em>Icon GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE_FEATURE_COUNT = GraphPackage.GCOMPARTMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Icon GNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_GNODE_OPERATION_COUNT = GraphPackage.GCOMPARTMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNEdgeImpl <em>BPMN Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.BPMNEdgeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNEdge()
     * @generated
     */
    int BPMN_EDGE = 13;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__ARGS = GraphPackage.GEDGE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__ID = GraphPackage.GEDGE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__CSS_CLASSES = GraphPackage.GEDGE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__CHILDREN = GraphPackage.GEDGE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__PARENT = GraphPackage.GEDGE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__TRACE = GraphPackage.GEDGE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__TYPE = GraphPackage.GEDGE__TYPE;

    /**
     * The feature id for the '<em><b>Routing Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__ROUTING_POINTS = GraphPackage.GEDGE__ROUTING_POINTS;

    /**
     * The feature id for the '<em><b>Source Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__SOURCE_ID = GraphPackage.GEDGE__SOURCE_ID;

    /**
     * The feature id for the '<em><b>Target Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__TARGET_ID = GraphPackage.GEDGE__TARGET_ID;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__SOURCE = GraphPackage.GEDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__TARGET = GraphPackage.GEDGE__TARGET;

    /**
     * The feature id for the '<em><b>Router Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__ROUTER_KIND = GraphPackage.GEDGE__ROUTER_KIND;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__NAME = GraphPackage.GEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE__KIND = GraphPackage.GEDGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>BPMN Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>BPMN Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPMN_EDGE_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.BaseElementGNode <em>Base Element GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Base Element GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.BaseElementGNode
     * @generated
     */
    EClass getBaseElementGNode();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BaseElementGNode#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.BaseElementGNode#getName()
     * @see #getBaseElementGNode()
     * @generated
     */
    EAttribute getBaseElementGNode_Name();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.FlowElementGNode <em>Flow Element GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Element GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.FlowElementGNode
     * @generated
     */
    EClass getFlowElementGNode();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.FlowElementGNode#getSymbol <em>Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Symbol</em>'.
     * @see org.openbpmn.glsp.bpmn.FlowElementGNode#getSymbol()
     * @see #getFlowElementGNode()
     * @generated
     */
    EAttribute getFlowElementGNode_Symbol();

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
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.IconGNode <em>Icon GNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Icon GNode</em>'.
     * @see org.openbpmn.glsp.bpmn.IconGNode
     * @generated
     */
    EClass getIconGNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.BPMNEdge <em>BPMN Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>BPMN Edge</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNEdge
     * @generated
     */
    EClass getBPMNEdge();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNEdge#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNEdge#getName()
     * @see #getBPMNEdge()
     * @generated
     */
    EAttribute getBPMNEdge_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BPMNEdge#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see org.openbpmn.glsp.bpmn.BPMNEdge#getKind()
     * @see #getBPMNEdge()
     * @generated
     */
    EAttribute getBPMNEdge_Kind();

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
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.BaseElementGNodeImpl <em>Base Element GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.BaseElementGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBaseElementGNode()
         * @generated
         */
        EClass BASE_ELEMENT_GNODE = eINSTANCE.getBaseElementGNode();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BASE_ELEMENT_GNODE__NAME = eINSTANCE.getBaseElementGNode_Name();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.FlowElementGNodeImpl <em>Flow Element GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.FlowElementGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getFlowElementGNode()
         * @generated
         */
        EClass FLOW_ELEMENT_GNODE = eINSTANCE.getFlowElementGNode();

        /**
         * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FLOW_ELEMENT_GNODE__SYMBOL = eINSTANCE.getFlowElementGNode_Symbol();

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

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.IconGNodeImpl <em>Icon GNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.IconGNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIconGNode()
         * @generated
         */
        EClass ICON_GNODE = eINSTANCE.getIconGNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.BPMNEdgeImpl <em>BPMN Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.BPMNEdgeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBPMNEdge()
         * @generated
         */
        EClass BPMN_EDGE = eINSTANCE.getBPMNEdge();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMN_EDGE__NAME = eINSTANCE.getBPMNEdge_Name();

        /**
         * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPMN_EDGE__KIND = eINSTANCE.getBPMNEdge_Kind();

    }

} //BpmnPackage
