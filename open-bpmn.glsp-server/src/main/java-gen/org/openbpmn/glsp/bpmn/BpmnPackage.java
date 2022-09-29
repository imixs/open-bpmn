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
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.BaseElementImpl <em>Base Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.BaseElementImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBaseElement()
     * @generated
     */
    int BASE_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__ARGS = GraphPackage.GNODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__ID = GraphPackage.GNODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__CSS_CLASSES = GraphPackage.GNODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__CHILDREN = GraphPackage.GNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__PARENT = GraphPackage.GNODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__TRACE = GraphPackage.GNODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__TYPE = GraphPackage.GNODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__POSITION = GraphPackage.GNODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__SIZE = GraphPackage.GNODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__EDGE_PLACEMENT = GraphPackage.GNODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__LAYOUT = GraphPackage.GNODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__LAYOUT_OPTIONS = GraphPackage.GNODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__NAME = GraphPackage.GNODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Base Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_FEATURE_COUNT = GraphPackage.GNODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Base Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_OPERATION_COUNT = GraphPackage.GNODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.FlowElementImpl <em>Flow Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.FlowElementImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getFlowElement()
     * @generated
     */
    int FLOW_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__NAME = BASE_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__SYMBOL = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Flow Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Flow Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TaskNodeImpl <em>Task Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TaskNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTaskNode()
     * @generated
     */
    int TASK_NODE = 2;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__ARGS = FLOW_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__CSS_CLASSES = FLOW_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__CHILDREN = FLOW_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__PARENT = FLOW_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__TRACE = FLOW_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__TYPE = FLOW_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__POSITION = FLOW_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__SIZE = FLOW_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__EDGE_PLACEMENT = FLOW_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__LAYOUT = FLOW_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__LAYOUT_OPTIONS = FLOW_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__NAME = FLOW_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE__SYMBOL = FLOW_ELEMENT__SYMBOL;

    /**
     * The number of structural features of the '<em>Task Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Task Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_NODE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GatewayNodeImpl <em>Gateway Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GatewayNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGatewayNode()
     * @generated
     */
    int GATEWAY_NODE = 3;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__ARGS = FLOW_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__CSS_CLASSES = FLOW_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__CHILDREN = FLOW_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__PARENT = FLOW_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__TRACE = FLOW_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__TYPE = FLOW_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__POSITION = FLOW_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__SIZE = FLOW_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__EDGE_PLACEMENT = FLOW_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__LAYOUT = FLOW_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__LAYOUT_OPTIONS = FLOW_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__NAME = FLOW_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE__SYMBOL = FLOW_ELEMENT__SYMBOL;

    /**
     * The number of structural features of the '<em>Gateway Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Gateway Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_NODE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.EventNodeImpl <em>Event Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.EventNodeImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getEventNode()
     * @generated
     */
    int EVENT_NODE = 4;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__ARGS = FLOW_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__CSS_CLASSES = FLOW_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__CHILDREN = FLOW_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__PARENT = FLOW_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__TRACE = FLOW_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__TYPE = FLOW_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__POSITION = FLOW_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__SIZE = FLOW_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__EDGE_PLACEMENT = FLOW_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__LAYOUT = FLOW_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__LAYOUT_OPTIONS = FLOW_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__NAME = FLOW_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Symbol</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE__SYMBOL = FLOW_ELEMENT__SYMBOL;

    /**
     * The number of structural features of the '<em>Event Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Event Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_NODE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LabelImpl <em>Label</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LabelImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLabel()
     * @generated
     */
    int LABEL = 5;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Label</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Label</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.DataObjectImpl <em>Data Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.DataObjectImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getDataObject()
     * @generated
     */
    int DATA_OBJECT = 6;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Data Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Data Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_OBJECT_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.MessageImpl <em>Message</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.MessageImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getMessage()
     * @generated
     */
    int MESSAGE = 7;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Message</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Message</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MESSAGE_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.GroupImpl <em>Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.GroupImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGroup()
     * @generated
     */
    int GROUP = 8;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Group</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Group</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUP_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.TextAnnotationImpl <em>Text Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.TextAnnotationImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTextAnnotation()
     * @generated
     */
    int TEXT_ANNOTATION = 9;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Text Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Text Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEXT_ANNOTATION_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.PoolImpl <em>Pool</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.PoolImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getPool()
     * @generated
     */
    int POOL = 10;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Pool</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Pool</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.LaneImpl <em>Lane</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.LaneImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLane()
     * @generated
     */
    int LANE = 11;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE__NAME = BASE_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.IconImpl <em>Icon</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.IconImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIcon()
     * @generated
     */
    int ICON = 12;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__ARGS = GraphPackage.GCOMPARTMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__ID = GraphPackage.GCOMPARTMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__CSS_CLASSES = GraphPackage.GCOMPARTMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__CHILDREN = GraphPackage.GCOMPARTMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__PARENT = GraphPackage.GCOMPARTMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__TRACE = GraphPackage.GCOMPARTMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__TYPE = GraphPackage.GCOMPARTMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__POSITION = GraphPackage.GCOMPARTMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__SIZE = GraphPackage.GCOMPARTMENT__SIZE;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__LAYOUT = GraphPackage.GCOMPARTMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON__LAYOUT_OPTIONS = GraphPackage.GCOMPARTMENT__LAYOUT_OPTIONS;

    /**
     * The number of structural features of the '<em>Icon</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_FEATURE_COUNT = GraphPackage.GCOMPARTMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Icon</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ICON_OPERATION_COUNT = GraphPackage.GCOMPARTMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.glsp.bpmn.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.glsp.bpmn.impl.SequenceFlowImpl
     * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getSequenceFlow()
     * @generated
     */
    int SEQUENCE_FLOW = 13;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__ARGS = GraphPackage.GEDGE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__ID = GraphPackage.GEDGE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__CSS_CLASSES = GraphPackage.GEDGE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__CHILDREN = GraphPackage.GEDGE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__PARENT = GraphPackage.GEDGE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__TRACE = GraphPackage.GEDGE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__TYPE = GraphPackage.GEDGE__TYPE;

    /**
     * The feature id for the '<em><b>Routing Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__ROUTING_POINTS = GraphPackage.GEDGE__ROUTING_POINTS;

    /**
     * The feature id for the '<em><b>Source Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__SOURCE_ID = GraphPackage.GEDGE__SOURCE_ID;

    /**
     * The feature id for the '<em><b>Target Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__TARGET_ID = GraphPackage.GEDGE__TARGET_ID;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__SOURCE = GraphPackage.GEDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__TARGET = GraphPackage.GEDGE__TARGET;

    /**
     * The feature id for the '<em><b>Router Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__ROUTER_KIND = GraphPackage.GEDGE__ROUTER_KIND;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__NAME = GraphPackage.GEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__CONDITION = GraphPackage.GEDGE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Default Flow</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__DEFAULT_FLOW = GraphPackage.GEDGE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Sequence Flow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Sequence Flow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.BaseElement <em>Base Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Base Element</em>'.
     * @see org.openbpmn.glsp.bpmn.BaseElement
     * @generated
     */
    EClass getBaseElement();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.BaseElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.BaseElement#getName()
     * @see #getBaseElement()
     * @generated
     */
    EAttribute getBaseElement_Name();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Element</em>'.
     * @see org.openbpmn.glsp.bpmn.FlowElement
     * @generated
     */
    EClass getFlowElement();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.FlowElement#getSymbol <em>Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Symbol</em>'.
     * @see org.openbpmn.glsp.bpmn.FlowElement#getSymbol()
     * @see #getFlowElement()
     * @generated
     */
    EAttribute getFlowElement_Symbol();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.TaskNode <em>Task Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task Node</em>'.
     * @see org.openbpmn.glsp.bpmn.TaskNode
     * @generated
     */
    EClass getTaskNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.GatewayNode <em>Gateway Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Gateway Node</em>'.
     * @see org.openbpmn.glsp.bpmn.GatewayNode
     * @generated
     */
    EClass getGatewayNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.EventNode <em>Event Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Event Node</em>'.
     * @see org.openbpmn.glsp.bpmn.EventNode
     * @generated
     */
    EClass getEventNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Label <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label</em>'.
     * @see org.openbpmn.glsp.bpmn.Label
     * @generated
     */
    EClass getLabel();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.DataObject <em>Data Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Object</em>'.
     * @see org.openbpmn.glsp.bpmn.DataObject
     * @generated
     */
    EClass getDataObject();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Message <em>Message</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message</em>'.
     * @see org.openbpmn.glsp.bpmn.Message
     * @generated
     */
    EClass getMessage();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Group <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group</em>'.
     * @see org.openbpmn.glsp.bpmn.Group
     * @generated
     */
    EClass getGroup();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.TextAnnotation <em>Text Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Annotation</em>'.
     * @see org.openbpmn.glsp.bpmn.TextAnnotation
     * @generated
     */
    EClass getTextAnnotation();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Pool <em>Pool</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pool</em>'.
     * @see org.openbpmn.glsp.bpmn.Pool
     * @generated
     */
    EClass getPool();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Lane <em>Lane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane</em>'.
     * @see org.openbpmn.glsp.bpmn.Lane
     * @generated
     */
    EClass getLane();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.Icon <em>Icon</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Icon</em>'.
     * @see org.openbpmn.glsp.bpmn.Icon
     * @generated
     */
    EClass getIcon();

    /**
     * Returns the meta object for class '{@link org.openbpmn.glsp.bpmn.SequenceFlow <em>Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sequence Flow</em>'.
     * @see org.openbpmn.glsp.bpmn.SequenceFlow
     * @generated
     */
    EClass getSequenceFlow();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.SequenceFlow#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.glsp.bpmn.SequenceFlow#getName()
     * @see #getSequenceFlow()
     * @generated
     */
    EAttribute getSequenceFlow_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.SequenceFlow#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Condition</em>'.
     * @see org.openbpmn.glsp.bpmn.SequenceFlow#getCondition()
     * @see #getSequenceFlow()
     * @generated
     */
    EAttribute getSequenceFlow_Condition();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.glsp.bpmn.SequenceFlow#isDefaultFlow <em>Default Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Flow</em>'.
     * @see org.openbpmn.glsp.bpmn.SequenceFlow#isDefaultFlow()
     * @see #getSequenceFlow()
     * @generated
     */
    EAttribute getSequenceFlow_DefaultFlow();

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
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.BaseElementImpl <em>Base Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.BaseElementImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getBaseElement()
         * @generated
         */
        EClass BASE_ELEMENT = eINSTANCE.getBaseElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BASE_ELEMENT__NAME = eINSTANCE.getBaseElement_Name();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.FlowElementImpl <em>Flow Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.FlowElementImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getFlowElement()
         * @generated
         */
        EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

        /**
         * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FLOW_ELEMENT__SYMBOL = eINSTANCE.getFlowElement_Symbol();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.TaskNodeImpl <em>Task Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.TaskNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTaskNode()
         * @generated
         */
        EClass TASK_NODE = eINSTANCE.getTaskNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.GatewayNodeImpl <em>Gateway Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.GatewayNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGatewayNode()
         * @generated
         */
        EClass GATEWAY_NODE = eINSTANCE.getGatewayNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.EventNodeImpl <em>Event Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.EventNodeImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getEventNode()
         * @generated
         */
        EClass EVENT_NODE = eINSTANCE.getEventNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.LabelImpl <em>Label</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.LabelImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLabel()
         * @generated
         */
        EClass LABEL = eINSTANCE.getLabel();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.DataObjectImpl <em>Data Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.DataObjectImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getDataObject()
         * @generated
         */
        EClass DATA_OBJECT = eINSTANCE.getDataObject();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.MessageImpl <em>Message</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.MessageImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getMessage()
         * @generated
         */
        EClass MESSAGE = eINSTANCE.getMessage();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.GroupImpl <em>Group</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.GroupImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getGroup()
         * @generated
         */
        EClass GROUP = eINSTANCE.getGroup();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.TextAnnotationImpl <em>Text Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.TextAnnotationImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getTextAnnotation()
         * @generated
         */
        EClass TEXT_ANNOTATION = eINSTANCE.getTextAnnotation();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.PoolImpl <em>Pool</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.PoolImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getPool()
         * @generated
         */
        EClass POOL = eINSTANCE.getPool();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.LaneImpl <em>Lane</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.LaneImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getLane()
         * @generated
         */
        EClass LANE = eINSTANCE.getLane();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.IconImpl <em>Icon</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.IconImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getIcon()
         * @generated
         */
        EClass ICON = eINSTANCE.getIcon();

        /**
         * The meta object literal for the '{@link org.openbpmn.glsp.bpmn.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.glsp.bpmn.impl.SequenceFlowImpl
         * @see org.openbpmn.glsp.bpmn.impl.BpmnPackageImpl#getSequenceFlow()
         * @generated
         */
        EClass SEQUENCE_FLOW = eINSTANCE.getSequenceFlow();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEQUENCE_FLOW__NAME = eINSTANCE.getSequenceFlow_Name();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEQUENCE_FLOW__CONDITION = eINSTANCE.getSequenceFlow_Condition();

        /**
         * The meta object literal for the '<em><b>Default Flow</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEQUENCE_FLOW__DEFAULT_FLOW = eINSTANCE.getSequenceFlow_DefaultFlow();

    }

} //BpmnPackage
