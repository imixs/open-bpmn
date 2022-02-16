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
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.ActivityNodeImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getActivityNode()
    * @generated
    */
   int ACTIVITY_NODE = 0;

   /**
    * The feature id for the '<em><b>Args</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__ARGS = GraphPackage.GNODE__ARGS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__ID = GraphPackage.GNODE__ID;

   /**
    * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__CSS_CLASSES = GraphPackage.GNODE__CSS_CLASSES;

   /**
    * The feature id for the '<em><b>Children</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__CHILDREN = GraphPackage.GNODE__CHILDREN;

   /**
    * The feature id for the '<em><b>Parent</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__PARENT = GraphPackage.GNODE__PARENT;

   /**
    * The feature id for the '<em><b>Trace</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__TRACE = GraphPackage.GNODE__TRACE;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__TYPE = GraphPackage.GNODE__TYPE;

   /**
    * The feature id for the '<em><b>Position</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__POSITION = GraphPackage.GNODE__POSITION;

   /**
    * The feature id for the '<em><b>Size</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__SIZE = GraphPackage.GNODE__SIZE;

   /**
    * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__EDGE_PLACEMENT = GraphPackage.GNODE__EDGE_PLACEMENT;

   /**
    * The feature id for the '<em><b>Layout</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__LAYOUT = GraphPackage.GNODE__LAYOUT;

   /**
    * The feature id for the '<em><b>Layout Options</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__LAYOUT_OPTIONS = GraphPackage.GNODE__LAYOUT_OPTIONS;

   /**
    * The feature id for the '<em><b>Node Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE__NODE_TYPE = GraphPackage.GNODE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Activity Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE_FEATURE_COUNT = GraphPackage.GNODE_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Activity Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ACTIVITY_NODE_OPERATION_COUNT = GraphPackage.GNODE_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl <em>Task Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.TaskNodeImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getTaskNode()
    * @generated
    */
   int TASK_NODE = 1;

   /**
    * The feature id for the '<em><b>Args</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__ARGS = ACTIVITY_NODE__ARGS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__ID = ACTIVITY_NODE__ID;

   /**
    * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__CSS_CLASSES = ACTIVITY_NODE__CSS_CLASSES;

   /**
    * The feature id for the '<em><b>Children</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__CHILDREN = ACTIVITY_NODE__CHILDREN;

   /**
    * The feature id for the '<em><b>Parent</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__PARENT = ACTIVITY_NODE__PARENT;

   /**
    * The feature id for the '<em><b>Trace</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__TRACE = ACTIVITY_NODE__TRACE;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__TYPE = ACTIVITY_NODE__TYPE;

   /**
    * The feature id for the '<em><b>Position</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__POSITION = ACTIVITY_NODE__POSITION;

   /**
    * The feature id for the '<em><b>Size</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__SIZE = ACTIVITY_NODE__SIZE;

   /**
    * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__EDGE_PLACEMENT = ACTIVITY_NODE__EDGE_PLACEMENT;

   /**
    * The feature id for the '<em><b>Layout</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__LAYOUT = ACTIVITY_NODE__LAYOUT;

   /**
    * The feature id for the '<em><b>Layout Options</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__LAYOUT_OPTIONS = ACTIVITY_NODE__LAYOUT_OPTIONS;

   /**
    * The feature id for the '<em><b>Node Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__NODE_TYPE = ACTIVITY_NODE__NODE_TYPE;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__NAME = ACTIVITY_NODE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Task Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE__TASK_TYPE = ACTIVITY_NODE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Task Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE_FEATURE_COUNT = ACTIVITY_NODE_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>Task Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TASK_NODE_OPERATION_COUNT = ACTIVITY_NODE_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.IconImpl <em>Icon</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.IconImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getIcon()
    * @generated
    */
   int ICON = 2;

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
   int SEQUENCE_FLOW = 3;

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
    * The feature id for the '<em><b>Condition</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SEQUENCE_FLOW__CONDITION = GraphPackage.GEDGE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Sequence Flow</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SEQUENCE_FLOW_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Sequence Flow</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SEQUENCE_FLOW_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;


   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.PoolImpl <em>Pool</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.PoolImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getPool()
    * @generated
    */
   int POOL = 4;

   /**
    * The feature id for the '<em><b>Args</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__ARGS = ACTIVITY_NODE__ARGS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__ID = ACTIVITY_NODE__ID;

   /**
    * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__CSS_CLASSES = ACTIVITY_NODE__CSS_CLASSES;

   /**
    * The feature id for the '<em><b>Children</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__CHILDREN = ACTIVITY_NODE__CHILDREN;

   /**
    * The feature id for the '<em><b>Parent</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__PARENT = ACTIVITY_NODE__PARENT;

   /**
    * The feature id for the '<em><b>Trace</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__TRACE = ACTIVITY_NODE__TRACE;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__TYPE = ACTIVITY_NODE__TYPE;

   /**
    * The feature id for the '<em><b>Position</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__POSITION = ACTIVITY_NODE__POSITION;

   /**
    * The feature id for the '<em><b>Size</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__SIZE = ACTIVITY_NODE__SIZE;

   /**
    * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__EDGE_PLACEMENT = ACTIVITY_NODE__EDGE_PLACEMENT;

   /**
    * The feature id for the '<em><b>Layout</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__LAYOUT = ACTIVITY_NODE__LAYOUT;

   /**
    * The feature id for the '<em><b>Layout Options</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__LAYOUT_OPTIONS = ACTIVITY_NODE__LAYOUT_OPTIONS;

   /**
    * The feature id for the '<em><b>Node Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__NODE_TYPE = ACTIVITY_NODE__NODE_TYPE;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL__NAME = ACTIVITY_NODE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Pool</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL_FEATURE_COUNT = ACTIVITY_NODE_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Pool</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int POOL_OPERATION_COUNT = ACTIVITY_NODE_OPERATION_COUNT + 0;


   /**
    * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.GatewayImpl <em>Gateway</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.imixs.bpmn.bpmngraph.impl.GatewayImpl
    * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getGateway()
    * @generated
    */
   int GATEWAY = 5;

   /**
    * The feature id for the '<em><b>Args</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__ARGS = ACTIVITY_NODE__ARGS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__ID = ACTIVITY_NODE__ID;

   /**
    * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__CSS_CLASSES = ACTIVITY_NODE__CSS_CLASSES;

   /**
    * The feature id for the '<em><b>Children</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__CHILDREN = ACTIVITY_NODE__CHILDREN;

   /**
    * The feature id for the '<em><b>Parent</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__PARENT = ACTIVITY_NODE__PARENT;

   /**
    * The feature id for the '<em><b>Trace</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__TRACE = ACTIVITY_NODE__TRACE;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__TYPE = ACTIVITY_NODE__TYPE;

   /**
    * The feature id for the '<em><b>Position</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__POSITION = ACTIVITY_NODE__POSITION;

   /**
    * The feature id for the '<em><b>Size</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__SIZE = ACTIVITY_NODE__SIZE;

   /**
    * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__EDGE_PLACEMENT = ACTIVITY_NODE__EDGE_PLACEMENT;

   /**
    * The feature id for the '<em><b>Layout</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__LAYOUT = ACTIVITY_NODE__LAYOUT;

   /**
    * The feature id for the '<em><b>Layout Options</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__LAYOUT_OPTIONS = ACTIVITY_NODE__LAYOUT_OPTIONS;

   /**
    * The feature id for the '<em><b>Node Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__NODE_TYPE = ACTIVITY_NODE__NODE_TYPE;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__NAME = ACTIVITY_NODE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Gateway Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY__GATEWAY_TYPE = ACTIVITY_NODE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Gateway</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY_FEATURE_COUNT = ACTIVITY_NODE_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>Gateway</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int GATEWAY_OPERATION_COUNT = ACTIVITY_NODE_OPERATION_COUNT + 0;


   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.ActivityNode <em>Activity Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Activity Node</em>'.
    * @see org.imixs.bpmn.bpmngraph.ActivityNode
    * @generated
    */
   EClass getActivityNode();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.ActivityNode#getNodeType <em>Node Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Node Type</em>'.
    * @see org.imixs.bpmn.bpmngraph.ActivityNode#getNodeType()
    * @see #getActivityNode()
    * @generated
    */
   EAttribute getActivityNode_NodeType();

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
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.TaskNode#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.imixs.bpmn.bpmngraph.TaskNode#getName()
    * @see #getTaskNode()
    * @generated
    */
   EAttribute getTaskNode_Name();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.TaskNode#getTaskType <em>Task Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Task Type</em>'.
    * @see org.imixs.bpmn.bpmngraph.TaskNode#getTaskType()
    * @see #getTaskNode()
    * @generated
    */
   EAttribute getTaskNode_TaskType();

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
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Pool <em>Pool</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Pool</em>'.
    * @see org.imixs.bpmn.bpmngraph.Pool
    * @generated
    */
   EClass getPool();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.Pool#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.imixs.bpmn.bpmngraph.Pool#getName()
    * @see #getPool()
    * @generated
    */
   EAttribute getPool_Name();

   /**
    * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Gateway <em>Gateway</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Gateway</em>'.
    * @see org.imixs.bpmn.bpmngraph.Gateway
    * @generated
    */
   EClass getGateway();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.Gateway#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.imixs.bpmn.bpmngraph.Gateway#getName()
    * @see #getGateway()
    * @generated
    */
   EAttribute getGateway_Name();

   /**
    * Returns the meta object for the attribute '{@link org.imixs.bpmn.bpmngraph.Gateway#getGatewayType <em>Gateway Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Gateway Type</em>'.
    * @see org.imixs.bpmn.bpmngraph.Gateway#getGatewayType()
    * @see #getGateway()
    * @generated
    */
   EAttribute getGateway_GatewayType();

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
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.ActivityNodeImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getActivityNode()
       * @generated
       */
      EClass ACTIVITY_NODE = eINSTANCE.getActivityNode();

      /**
       * The meta object literal for the '<em><b>Node Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute ACTIVITY_NODE__NODE_TYPE = eINSTANCE.getActivityNode_NodeType();

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
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TASK_NODE__NAME = eINSTANCE.getTaskNode_Name();

      /**
       * The meta object literal for the '<em><b>Task Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TASK_NODE__TASK_TYPE = eINSTANCE.getTaskNode_TaskType();

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
       * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute SEQUENCE_FLOW__CONDITION = eINSTANCE.getSequenceFlow_Condition();

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
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute POOL__NAME = eINSTANCE.getPool_Name();

      /**
       * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.GatewayImpl <em>Gateway</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.imixs.bpmn.bpmngraph.impl.GatewayImpl
       * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getGateway()
       * @generated
       */
      EClass GATEWAY = eINSTANCE.getGateway();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute GATEWAY__NAME = eINSTANCE.getGateway_Name();

      /**
       * The meta object literal for the '<em><b>Gateway Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute GATEWAY__GATEWAY_TYPE = eINSTANCE.getGateway_GatewayType();

   }

} //BpmngraphPackage
