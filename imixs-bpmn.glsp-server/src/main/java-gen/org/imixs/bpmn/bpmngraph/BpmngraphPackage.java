/**
 */
package org.imixs.bpmn.bpmngraph;

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
 * @see org.imixs.bpmn.bpmngraph.BpmngraphFactory
 * @model kind="package"
 * @generated
 */
public interface BpmngraphPackage extends EPackage {
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "bpmngraph";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://www.imixs.org/bpmn/glsp/graph";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "bpmngraph";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   BpmngraphPackage eINSTANCE = org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl.init();

   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.BaseElementImpl <em>Base Element</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.BaseElementImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getBaseElement()
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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BASE_ELEMENT__DOCUMENTATION = GraphPackage.GNODE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Base Element</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BASE_ELEMENT_FEATURE_COUNT = GraphPackage.GNODE_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>Base Element</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BASE_ELEMENT_OPERATION_COUNT = GraphPackage.GNODE_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.FlowElementImpl <em>Flow Element</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.FlowElementImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getFlowElement()
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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FLOW_ELEMENT__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

   /**
    * The feature id for the '<em><b>Category</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FLOW_ELEMENT__CATEGORY = BASE_ELEMENT_FEATURE_COUNT + 0;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl <em>Task Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getTaskNode()
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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

   /**
    * The feature id for the '<em><b>Category</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__CATEGORY = FLOW_ELEMENT__CATEGORY;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.GatewayNodeImpl <em>Gateway Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.GatewayNodeImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getGatewayNode()
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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY_NODE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

   /**
    * The feature id for the '<em><b>Category</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY_NODE__CATEGORY = FLOW_ELEMENT__CATEGORY;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.EventNodeImpl <em>Event Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.EventNodeImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getEventNode()
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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EVENT_NODE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

   /**
    * The feature id for the '<em><b>Category</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EVENT_NODE__CATEGORY = FLOW_ELEMENT__CATEGORY;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.PoolImpl <em>Pool</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.PoolImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getPool()
    * @generated
    */
   int POOL = 5;

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
    * The feature id for the '<em><b>Documentation</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.IconImpl <em>Icon</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.IconImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getIcon()
    * @generated
    */
   int ICON = 6;

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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getSequenceFlow()
    * @generated
    */
   int SEQUENCE_FLOW = 7;

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
    * The number of structural features of the '<em>Sequence Flow</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SEQUENCE_FLOW_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>Sequence Flow</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SEQUENCE_FLOW_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;


   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.BaseElement <em>Base Element</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Base Element</em>'.
    * @see org.imixs.bpmn.bpmngraph.BaseElement
    * @generated
    */
   EClass getBaseElement();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.BaseElement#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.imixs.bpmn.bpmngraph.BaseElement#getName()
    * @see #getBaseElement()
    * @generated
    */
   EAttribute getBaseElement_Name();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.BaseElement#getDocumentation <em>Documentation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Documentation</em>'.
    * @see org.imixs.bpmn.bpmngraph.BaseElement#getDocumentation()
    * @see #getBaseElement()
    * @generated
    */
   EAttribute getBaseElement_Documentation();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.FlowElement <em>Flow Element</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Flow Element</em>'.
    * @see org.imixs.bpmn.bpmngraph.FlowElement
    * @generated
    */
   EClass getFlowElement();

   /**
    * Returns the meta object for the attribute list '{@link org.imixs.bpmn.bpmngraph.FlowElement#getCategory <em>Category</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Category</em>'.
    * @see org.imixs.bpmn.bpmngraph.FlowElement#getCategory()
    * @see #getFlowElement()
    * @generated
    */
   EAttribute getFlowElement_Category();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.TaskNode <em>Task Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Task Node</em>'.
    * @see org.imixs.bpmn.bpmngraph.TaskNode
    * @generated
    */
   EClass getTaskNode();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.GatewayNode <em>Gateway Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Gateway Node</em>'.
    * @see org.imixs.bpmn.bpmngraph.GatewayNode
    * @generated
    */
   EClass getGatewayNode();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.EventNode <em>Event Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Event Node</em>'.
    * @see org.imixs.bpmn.bpmngraph.EventNode
    * @generated
    */
   EClass getEventNode();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Pool <em>Pool</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Pool</em>'.
    * @see org.imixs.bpmn.bpmngraph.Pool
    * @generated
    */
   EClass getPool();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Icon <em>Icon</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Icon</em>'.
    * @see org.imixs.bpmn.bpmngraph.Icon
    * @generated
    */
   EClass getIcon();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.SequenceFlow <em>Sequence Flow</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Sequence Flow</em>'.
    * @see org.imixs.bpmn.bpmngraph.SequenceFlow
    * @generated
    */
   EClass getSequenceFlow();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getName()
    * @see #getSequenceFlow()
    * @generated
    */
   EAttribute getSequenceFlow_Name();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getCondition <em>Condition</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Condition</em>'.
    * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getCondition()
    * @see #getSequenceFlow()
    * @generated
    */
   EAttribute getSequenceFlow_Condition();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   BpmngraphFactory getBpmngraphFactory();

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
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.BaseElementImpl <em>Base Element</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.BaseElementImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getBaseElement()
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
       * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute BASE_ELEMENT__DOCUMENTATION = eINSTANCE.getBaseElement_Documentation();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.FlowElementImpl <em>Flow Element</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.FlowElementImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getFlowElement()
       * @generated
       */
      EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

      /**
       * The meta object literal for the '<em><b>Category</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FLOW_ELEMENT__CATEGORY = eINSTANCE.getFlowElement_Category();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl <em>Task Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getTaskNode()
       * @generated
       */
      EClass TASK_NODE = eINSTANCE.getTaskNode();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.GatewayNodeImpl <em>Gateway Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.GatewayNodeImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getGatewayNode()
       * @generated
       */
      EClass GATEWAY_NODE = eINSTANCE.getGatewayNode();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.EventNodeImpl <em>Event Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.EventNodeImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getEventNode()
       * @generated
       */
      EClass EVENT_NODE = eINSTANCE.getEventNode();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.PoolImpl <em>Pool</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.PoolImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getPool()
       * @generated
       */
      EClass POOL = eINSTANCE.getPool();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.IconImpl <em>Icon</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.IconImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getIcon()
       * @generated
       */
      EClass ICON = eINSTANCE.getIcon();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getSequenceFlow()
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

   }

} //BpmngraphPackage
