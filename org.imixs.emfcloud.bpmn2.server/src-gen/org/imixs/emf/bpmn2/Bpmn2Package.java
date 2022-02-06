/**
 */
package org.imixs.emf.bpmn2;

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
 * @see org.imixs.emf.bpmn2.Bpmn2Factory
 * @model kind="package"
 * @generated
 */
public interface Bpmn2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bpmn2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/glsp/bpmn2/graph";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bpmn2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bpmn2Package eINSTANCE = org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.imixs.emf.bpmn2.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imixs.emf.bpmn2.impl.ActivityNodeImpl
	 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getActivityNode()
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
	 * The meta object id for the '{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl <em>Task Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imixs.emf.bpmn2.impl.TaskNodeImpl
	 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getTaskNode()
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
	 * The feature id for the '<em><b>Expanded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__EXPANDED = ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__DURATION = ACTIVITY_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Task Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TASK_TYPE = ACTIVITY_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__REFERENCE = ACTIVITY_NODE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Task Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_FEATURE_COUNT = ACTIVITY_NODE_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Task Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_OPERATION_COUNT = ACTIVITY_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imixs.emf.bpmn2.impl.IconImpl <em>Icon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imixs.emf.bpmn2.impl.IconImpl
	 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getIcon()
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
	 * The meta object id for the '{@link org.imixs.emf.bpmn2.impl.WeightedEdgeImpl <em>Weighted Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imixs.emf.bpmn2.impl.WeightedEdgeImpl
	 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getWeightedEdge()
	 * @generated
	 */
	int WEIGHTED_EDGE = 3;

	/**
	 * The feature id for the '<em><b>Args</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__ARGS = GraphPackage.GEDGE__ARGS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__ID = GraphPackage.GEDGE__ID;

	/**
	 * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__CSS_CLASSES = GraphPackage.GEDGE__CSS_CLASSES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__CHILDREN = GraphPackage.GEDGE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__PARENT = GraphPackage.GEDGE__PARENT;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__TRACE = GraphPackage.GEDGE__TRACE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__TYPE = GraphPackage.GEDGE__TYPE;

	/**
	 * The feature id for the '<em><b>Routing Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__ROUTING_POINTS = GraphPackage.GEDGE__ROUTING_POINTS;

	/**
	 * The feature id for the '<em><b>Source Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__SOURCE_ID = GraphPackage.GEDGE__SOURCE_ID;

	/**
	 * The feature id for the '<em><b>Target Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__TARGET_ID = GraphPackage.GEDGE__TARGET_ID;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__SOURCE = GraphPackage.GEDGE__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__TARGET = GraphPackage.GEDGE__TARGET;

	/**
	 * The feature id for the '<em><b>Router Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__ROUTER_KIND = GraphPackage.GEDGE__ROUTER_KIND;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE__PROBABILITY = GraphPackage.GEDGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Weighted Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Weighted Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_EDGE_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imixs.emf.bpmn2.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imixs.emf.bpmn2.impl.CategoryImpl
	 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 4;

	/**
	 * The feature id for the '<em><b>Args</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ARGS = ACTIVITY_NODE__ARGS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ID = ACTIVITY_NODE__ID;

	/**
	 * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__CSS_CLASSES = ACTIVITY_NODE__CSS_CLASSES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__CHILDREN = ACTIVITY_NODE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PARENT = ACTIVITY_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TRACE = ACTIVITY_NODE__TRACE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TYPE = ACTIVITY_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__POSITION = ACTIVITY_NODE__POSITION;

	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__SIZE = ACTIVITY_NODE__SIZE;

	/**
	 * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__EDGE_PLACEMENT = ACTIVITY_NODE__EDGE_PLACEMENT;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__LAYOUT = ACTIVITY_NODE__LAYOUT;

	/**
	 * The feature id for the '<em><b>Layout Options</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__LAYOUT_OPTIONS = ACTIVITY_NODE__LAYOUT_OPTIONS;

	/**
	 * The feature id for the '<em><b>Node Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NODE_TYPE = ACTIVITY_NODE__NODE_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = ACTIVITY_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = ACTIVITY_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_OPERATION_COUNT = ACTIVITY_NODE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.imixs.emf.bpmn2.ActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Node</em>'.
	 * @see org.imixs.emf.bpmn2.ActivityNode
	 * @generated
	 */
	EClass getActivityNode();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.ActivityNode#getNodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Type</em>'.
	 * @see org.imixs.emf.bpmn2.ActivityNode#getNodeType()
	 * @see #getActivityNode()
	 * @generated
	 */
	EAttribute getActivityNode_NodeType();

	/**
	 * Returns the meta object for class '{@link org.imixs.emf.bpmn2.TaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Node</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode
	 * @generated
	 */
	EClass getTaskNode();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.TaskNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode#getName()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.TaskNode#isExpanded <em>Expanded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expanded</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode#isExpanded()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Expanded();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.TaskNode#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode#getDuration()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Duration();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.TaskNode#getTaskType <em>Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task Type</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode#getTaskType()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_TaskType();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.TaskNode#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference</em>'.
	 * @see org.imixs.emf.bpmn2.TaskNode#getReference()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Reference();

	/**
	 * Returns the meta object for class '{@link org.imixs.emf.bpmn2.Icon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Icon</em>'.
	 * @see org.imixs.emf.bpmn2.Icon
	 * @generated
	 */
	EClass getIcon();

	/**
	 * Returns the meta object for class '{@link org.imixs.emf.bpmn2.WeightedEdge <em>Weighted Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Edge</em>'.
	 * @see org.imixs.emf.bpmn2.WeightedEdge
	 * @generated
	 */
	EClass getWeightedEdge();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.WeightedEdge#getProbability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Probability</em>'.
	 * @see org.imixs.emf.bpmn2.WeightedEdge#getProbability()
	 * @see #getWeightedEdge()
	 * @generated
	 */
	EAttribute getWeightedEdge_Probability();

	/**
	 * Returns the meta object for class '{@link org.imixs.emf.bpmn2.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see org.imixs.emf.bpmn2.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the attribute '{@link org.imixs.emf.bpmn2.Category#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.imixs.emf.bpmn2.Category#getName()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Bpmn2Factory getBpmn2Factory();

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
		 * The meta object literal for the '{@link org.imixs.emf.bpmn2.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imixs.emf.bpmn2.impl.ActivityNodeImpl
		 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getActivityNode()
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
		 * The meta object literal for the '{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl <em>Task Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imixs.emf.bpmn2.impl.TaskNodeImpl
		 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getTaskNode()
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
		 * The meta object literal for the '<em><b>Expanded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__EXPANDED = eINSTANCE.getTaskNode_Expanded();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__DURATION = eINSTANCE.getTaskNode_Duration();

		/**
		 * The meta object literal for the '<em><b>Task Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__TASK_TYPE = eINSTANCE.getTaskNode_TaskType();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__REFERENCE = eINSTANCE.getTaskNode_Reference();

		/**
		 * The meta object literal for the '{@link org.imixs.emf.bpmn2.impl.IconImpl <em>Icon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imixs.emf.bpmn2.impl.IconImpl
		 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getIcon()
		 * @generated
		 */
		EClass ICON = eINSTANCE.getIcon();

		/**
		 * The meta object literal for the '{@link org.imixs.emf.bpmn2.impl.WeightedEdgeImpl <em>Weighted Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imixs.emf.bpmn2.impl.WeightedEdgeImpl
		 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getWeightedEdge()
		 * @generated
		 */
		EClass WEIGHTED_EDGE = eINSTANCE.getWeightedEdge();

		/**
		 * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_EDGE__PROBABILITY = eINSTANCE.getWeightedEdge_Probability();

		/**
		 * The meta object literal for the '{@link org.imixs.emf.bpmn2.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imixs.emf.bpmn2.impl.CategoryImpl
		 * @see org.imixs.emf.bpmn2.impl.Bpmn2PackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__NAME = eINSTANCE.getCategory_Name();

	}

} //Bpmn2Package
