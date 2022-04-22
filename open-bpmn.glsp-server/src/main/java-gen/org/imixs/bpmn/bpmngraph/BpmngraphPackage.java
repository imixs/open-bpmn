/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ExpressionImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getExpression()
     * @generated
     */
    int EXPRESSION = 2;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__ARGS = BASE_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__CSS_CLASSES = BASE_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__CHILDREN = BASE_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__PARENT = BASE_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__TRACE = BASE_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__TYPE = BASE_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__POSITION = BASE_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__SIZE = BASE_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__EDGE_PLACEMENT = BASE_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__LAYOUT = BASE_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__LAYOUT_OPTIONS = BASE_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__NAME = BASE_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

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
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__POSITION = GraphPackage.GEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__SIZE = GraphPackage.GEDGE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__EDGE_PLACEMENT = GraphPackage.GEDGE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__LAYOUT = GraphPackage.GEDGE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__LAYOUT_OPTIONS = GraphPackage.GEDGE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__NAME = GraphPackage.GEDGE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__DOCUMENTATION = GraphPackage.GEDGE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__CATEGORY = GraphPackage.GEDGE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Condition Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__CONDITION_EXPRESSION = GraphPackage.GEDGE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Source Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__SOURCE_REF = GraphPackage.GEDGE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Target Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW__TARGET_REF = GraphPackage.GEDGE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Sequence Flow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW_FEATURE_COUNT = GraphPackage.GEDGE_FEATURE_COUNT + 11;

    /**
     * The number of operations of the '<em>Sequence Flow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_FLOW_OPERATION_COUNT = GraphPackage.GEDGE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.FlowNodeImpl <em>Flow Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.FlowNodeImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getFlowNode()
     * @generated
     */
    int FLOW_NODE = 4;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__ARGS = FLOW_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__CSS_CLASSES = FLOW_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__CHILDREN = FLOW_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__PARENT = FLOW_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__TRACE = FLOW_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__TYPE = FLOW_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__POSITION = FLOW_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__SIZE = FLOW_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__EDGE_PLACEMENT = FLOW_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__LAYOUT = FLOW_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__LAYOUT_OPTIONS = FLOW_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__NAME = FLOW_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__CATEGORY = FLOW_ELEMENT__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__INCOMING = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__OUTGOING = FLOW_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Flow Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Flow Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

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
    int GATEWAY__ARGS = FLOW_ELEMENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__CSS_CLASSES = FLOW_ELEMENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__CHILDREN = FLOW_ELEMENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__PARENT = FLOW_ELEMENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__TRACE = FLOW_ELEMENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__TYPE = FLOW_ELEMENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__POSITION = FLOW_ELEMENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__SIZE = FLOW_ELEMENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__EDGE_PLACEMENT = FLOW_ELEMENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__LAYOUT = FLOW_ELEMENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__LAYOUT_OPTIONS = FLOW_ELEMENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__NAME = FLOW_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY__CATEGORY = FLOW_ELEMENT__CATEGORY;

    /**
     * The number of structural features of the '<em>Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GATEWAY_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ParallelGatewayImpl <em>Parallel Gateway</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ParallelGatewayImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getParallelGateway()
     * @generated
     */
    int PARALLEL_GATEWAY = 6;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__ARGS = GATEWAY__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__ID = GATEWAY__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__CSS_CLASSES = GATEWAY__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__CHILDREN = GATEWAY__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__PARENT = GATEWAY__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__TRACE = GATEWAY__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__TYPE = GATEWAY__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__POSITION = GATEWAY__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__SIZE = GATEWAY__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__EDGE_PLACEMENT = GATEWAY__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__LAYOUT = GATEWAY__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__LAYOUT_OPTIONS = GATEWAY__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__NAME = GATEWAY__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY__CATEGORY = GATEWAY__CATEGORY;

    /**
     * The number of structural features of the '<em>Parallel Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Parallel Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARALLEL_GATEWAY_OPERATION_COUNT = GATEWAY_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ExclusiveGatewayImpl <em>Exclusive Gateway</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ExclusiveGatewayImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getExclusiveGateway()
     * @generated
     */
    int EXCLUSIVE_GATEWAY = 7;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__ARGS = GATEWAY__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__ID = GATEWAY__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__CSS_CLASSES = GATEWAY__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__CHILDREN = GATEWAY__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__PARENT = GATEWAY__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__TRACE = GATEWAY__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__TYPE = GATEWAY__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__POSITION = GATEWAY__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__SIZE = GATEWAY__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__EDGE_PLACEMENT = GATEWAY__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__LAYOUT = GATEWAY__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__LAYOUT_OPTIONS = GATEWAY__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__NAME = GATEWAY__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__CATEGORY = GATEWAY__CATEGORY;

    /**
     * The feature id for the '<em><b>Default Sequence Flow</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW = GATEWAY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Exclusive Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Exclusive Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXCLUSIVE_GATEWAY_OPERATION_COUNT = GATEWAY_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.IncusiveGatewayImpl <em>Incusive Gateway</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.IncusiveGatewayImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getIncusiveGateway()
     * @generated
     */
    int INCUSIVE_GATEWAY = 8;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__ARGS = GATEWAY__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__ID = GATEWAY__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__CSS_CLASSES = GATEWAY__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__CHILDREN = GATEWAY__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__PARENT = GATEWAY__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__TRACE = GATEWAY__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__TYPE = GATEWAY__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__POSITION = GATEWAY__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__SIZE = GATEWAY__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__EDGE_PLACEMENT = GATEWAY__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__LAYOUT = GATEWAY__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__LAYOUT_OPTIONS = GATEWAY__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__NAME = GATEWAY__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__CATEGORY = GATEWAY__CATEGORY;

    /**
     * The feature id for the '<em><b>Default Sequence Flow</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW = GATEWAY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Incusive Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Incusive Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INCUSIVE_GATEWAY_OPERATION_COUNT = GATEWAY_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ComplexGatewayImpl <em>Complex Gateway</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ComplexGatewayImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getComplexGateway()
     * @generated
     */
    int COMPLEX_GATEWAY = 9;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__ARGS = GATEWAY__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__ID = GATEWAY__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__CSS_CLASSES = GATEWAY__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__CHILDREN = GATEWAY__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__PARENT = GATEWAY__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__TRACE = GATEWAY__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__TYPE = GATEWAY__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__POSITION = GATEWAY__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__SIZE = GATEWAY__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__EDGE_PLACEMENT = GATEWAY__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__LAYOUT = GATEWAY__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__LAYOUT_OPTIONS = GATEWAY__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__NAME = GATEWAY__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__CATEGORY = GATEWAY__CATEGORY;

    /**
     * The feature id for the '<em><b>Default Sequence Flow</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY__DEFAULT_SEQUENCE_FLOW = GATEWAY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Complex Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Complex Gateway</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPLEX_GATEWAY_OPERATION_COUNT = GATEWAY_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.EventImpl <em>Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.EventImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getEvent()
     * @generated
     */
    int EVENT = 10;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__ARGS = FLOW_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__ID = FLOW_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__CSS_CLASSES = FLOW_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__CHILDREN = FLOW_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__PARENT = FLOW_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__TRACE = FLOW_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__TYPE = FLOW_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__POSITION = FLOW_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__SIZE = FLOW_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__EDGE_PLACEMENT = FLOW_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__LAYOUT = FLOW_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__LAYOUT_OPTIONS = FLOW_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__NAME = FLOW_NODE__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__DOCUMENTATION = FLOW_NODE__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__CATEGORY = FLOW_NODE__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__INCOMING = FLOW_NODE__INCOMING;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__OUTGOING = FLOW_NODE__OUTGOING;

    /**
     * The number of structural features of the '<em>Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_FEATURE_COUNT = FLOW_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_OPERATION_COUNT = FLOW_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.CatchEventImpl <em>Catch Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.CatchEventImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getCatchEvent()
     * @generated
     */
    int CATCH_EVENT = 11;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__ARGS = EVENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__ID = EVENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__CSS_CLASSES = EVENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__CHILDREN = EVENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__PARENT = EVENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__TRACE = EVENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__TYPE = EVENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__POSITION = EVENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__SIZE = EVENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__EDGE_PLACEMENT = EVENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__LAYOUT = EVENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__LAYOUT_OPTIONS = EVENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__NAME = EVENT__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__CATEGORY = EVENT__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__INCOMING = EVENT__INCOMING;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT__OUTGOING = EVENT__OUTGOING;

    /**
     * The number of structural features of the '<em>Catch Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Catch Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATCH_EVENT_OPERATION_COUNT = EVENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ThrowEventImpl <em>Throw Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ThrowEventImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getThrowEvent()
     * @generated
     */
    int THROW_EVENT = 12;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__ARGS = EVENT__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__ID = EVENT__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__CSS_CLASSES = EVENT__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__CHILDREN = EVENT__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__PARENT = EVENT__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__TRACE = EVENT__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__TYPE = EVENT__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__POSITION = EVENT__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__SIZE = EVENT__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__EDGE_PLACEMENT = EVENT__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__LAYOUT = EVENT__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__LAYOUT_OPTIONS = EVENT__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__NAME = EVENT__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__CATEGORY = EVENT__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__INCOMING = EVENT__INCOMING;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT__OUTGOING = EVENT__OUTGOING;

    /**
     * The number of structural features of the '<em>Throw Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Throw Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THROW_EVENT_OPERATION_COUNT = EVENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.ActivityImpl <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.ActivityImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getActivity()
     * @generated
     */
    int ACTIVITY = 13;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__ARGS = FLOW_NODE__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__ID = FLOW_NODE__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__CSS_CLASSES = FLOW_NODE__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__CHILDREN = FLOW_NODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__PARENT = FLOW_NODE__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__TRACE = FLOW_NODE__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__TYPE = FLOW_NODE__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__POSITION = FLOW_NODE__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__SIZE = FLOW_NODE__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__EDGE_PLACEMENT = FLOW_NODE__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__LAYOUT = FLOW_NODE__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__LAYOUT_OPTIONS = FLOW_NODE__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__NAME = FLOW_NODE__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__DOCUMENTATION = FLOW_NODE__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__CATEGORY = FLOW_NODE__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__INCOMING = FLOW_NODE__INCOMING;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__OUTGOING = FLOW_NODE__OUTGOING;

    /**
     * The number of structural features of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY_FEATURE_COUNT = FLOW_NODE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY_OPERATION_COUNT = FLOW_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.TaskImpl <em>Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.TaskImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getTask()
     * @generated
     */
    int TASK = 14;

    /**
     * The feature id for the '<em><b>Args</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__ARGS = ACTIVITY__ARGS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__ID = ACTIVITY__ID;

    /**
     * The feature id for the '<em><b>Css Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__CSS_CLASSES = ACTIVITY__CSS_CLASSES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__CHILDREN = ACTIVITY__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__PARENT = ACTIVITY__PARENT;

    /**
     * The feature id for the '<em><b>Trace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__TRACE = ACTIVITY__TRACE;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__TYPE = ACTIVITY__TYPE;

    /**
     * The feature id for the '<em><b>Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__POSITION = ACTIVITY__POSITION;

    /**
     * The feature id for the '<em><b>Size</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__SIZE = ACTIVITY__SIZE;

    /**
     * The feature id for the '<em><b>Edge Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__EDGE_PLACEMENT = ACTIVITY__EDGE_PLACEMENT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__LAYOUT = ACTIVITY__LAYOUT;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__LAYOUT_OPTIONS = ACTIVITY__LAYOUT_OPTIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__NAME = ACTIVITY__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Category</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__CATEGORY = ACTIVITY__CATEGORY;

    /**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__INCOMING = ACTIVITY__INCOMING;

    /**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__OUTGOING = ACTIVITY__OUTGOING;

    /**
     * The number of structural features of the '<em>Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK_OPERATION_COUNT = ACTIVITY_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.imixs.bpmn.bpmngraph.impl.PoolImpl <em>Pool</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.imixs.bpmn.bpmngraph.impl.PoolImpl
     * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getPool()
     * @generated
     */
    int POOL = 15;

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
    int ICON = 16;

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
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression</em>'.
     * @see org.imixs.bpmn.bpmngraph.Expression
     * @generated
     */
    EClass getExpression();

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
     * Returns the meta object for the containment reference '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getConditionExpression <em>Condition Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Condition Expression</em>'.
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getConditionExpression()
     * @see #getSequenceFlow()
     * @generated
     */
    EReference getSequenceFlow_ConditionExpression();

    /**
     * Returns the meta object for the reference '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getSourceRef <em>Source Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source Ref</em>'.
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getSourceRef()
     * @see #getSequenceFlow()
     * @generated
     */
    EReference getSequenceFlow_SourceRef();

    /**
     * Returns the meta object for the reference '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getTargetRef <em>Target Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target Ref</em>'.
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getTargetRef()
     * @see #getSequenceFlow()
     * @generated
     */
    EReference getSequenceFlow_TargetRef();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.FlowNode <em>Flow Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Node</em>'.
     * @see org.imixs.bpmn.bpmngraph.FlowNode
     * @generated
     */
    EClass getFlowNode();

    /**
     * Returns the meta object for the reference list '{@link org.imixs.bpmn.bpmngraph.FlowNode#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming</em>'.
     * @see org.imixs.bpmn.bpmngraph.FlowNode#getIncoming()
     * @see #getFlowNode()
     * @generated
     */
    EReference getFlowNode_Incoming();

    /**
     * Returns the meta object for the reference list '{@link org.imixs.bpmn.bpmngraph.FlowNode#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing</em>'.
     * @see org.imixs.bpmn.bpmngraph.FlowNode#getOutgoing()
     * @see #getFlowNode()
     * @generated
     */
    EReference getFlowNode_Outgoing();

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
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.ParallelGateway <em>Parallel Gateway</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parallel Gateway</em>'.
     * @see org.imixs.bpmn.bpmngraph.ParallelGateway
     * @generated
     */
    EClass getParallelGateway();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.ExclusiveGateway <em>Exclusive Gateway</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Exclusive Gateway</em>'.
     * @see org.imixs.bpmn.bpmngraph.ExclusiveGateway
     * @generated
     */
    EClass getExclusiveGateway();

    /**
     * Returns the meta object for the reference '{@link org.imixs.bpmn.bpmngraph.ExclusiveGateway#getDefaultSequenceFlow <em>Default Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Sequence Flow</em>'.
     * @see org.imixs.bpmn.bpmngraph.ExclusiveGateway#getDefaultSequenceFlow()
     * @see #getExclusiveGateway()
     * @generated
     */
    EReference getExclusiveGateway_DefaultSequenceFlow();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.IncusiveGateway <em>Incusive Gateway</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Incusive Gateway</em>'.
     * @see org.imixs.bpmn.bpmngraph.IncusiveGateway
     * @generated
     */
    EClass getIncusiveGateway();

    /**
     * Returns the meta object for the reference '{@link org.imixs.bpmn.bpmngraph.IncusiveGateway#getDefaultSequenceFlow <em>Default Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Sequence Flow</em>'.
     * @see org.imixs.bpmn.bpmngraph.IncusiveGateway#getDefaultSequenceFlow()
     * @see #getIncusiveGateway()
     * @generated
     */
    EReference getIncusiveGateway_DefaultSequenceFlow();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.ComplexGateway <em>Complex Gateway</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Complex Gateway</em>'.
     * @see org.imixs.bpmn.bpmngraph.ComplexGateway
     * @generated
     */
    EClass getComplexGateway();

    /**
     * Returns the meta object for the reference '{@link org.imixs.bpmn.bpmngraph.ComplexGateway#getDefaultSequenceFlow <em>Default Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Default Sequence Flow</em>'.
     * @see org.imixs.bpmn.bpmngraph.ComplexGateway#getDefaultSequenceFlow()
     * @see #getComplexGateway()
     * @generated
     */
    EReference getComplexGateway_DefaultSequenceFlow();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Event</em>'.
     * @see org.imixs.bpmn.bpmngraph.Event
     * @generated
     */
    EClass getEvent();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.CatchEvent <em>Catch Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Catch Event</em>'.
     * @see org.imixs.bpmn.bpmngraph.CatchEvent
     * @generated
     */
    EClass getCatchEvent();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.ThrowEvent <em>Throw Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Throw Event</em>'.
     * @see org.imixs.bpmn.bpmngraph.ThrowEvent
     * @generated
     */
    EClass getThrowEvent();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Activity</em>'.
     * @see org.imixs.bpmn.bpmngraph.Activity
     * @generated
     */
    EClass getActivity();

    /**
     * Returns the meta object for class '{@link org.imixs.bpmn.bpmngraph.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task</em>'.
     * @see org.imixs.bpmn.bpmngraph.Task
     * @generated
     */
    EClass getTask();

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
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ExpressionImpl <em>Expression</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ExpressionImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getExpression()
         * @generated
         */
        EClass EXPRESSION = eINSTANCE.getExpression();

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
         * The meta object literal for the '<em><b>Condition Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEQUENCE_FLOW__CONDITION_EXPRESSION = eINSTANCE.getSequenceFlow_ConditionExpression();

        /**
         * The meta object literal for the '<em><b>Source Ref</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEQUENCE_FLOW__SOURCE_REF = eINSTANCE.getSequenceFlow_SourceRef();

        /**
         * The meta object literal for the '<em><b>Target Ref</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SEQUENCE_FLOW__TARGET_REF = eINSTANCE.getSequenceFlow_TargetRef();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.FlowNodeImpl <em>Flow Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.FlowNodeImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getFlowNode()
         * @generated
         */
        EClass FLOW_NODE = eINSTANCE.getFlowNode();

        /**
         * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FLOW_NODE__INCOMING = eINSTANCE.getFlowNode_Incoming();

        /**
         * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FLOW_NODE__OUTGOING = eINSTANCE.getFlowNode_Outgoing();

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
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ParallelGatewayImpl <em>Parallel Gateway</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ParallelGatewayImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getParallelGateway()
         * @generated
         */
        EClass PARALLEL_GATEWAY = eINSTANCE.getParallelGateway();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ExclusiveGatewayImpl <em>Exclusive Gateway</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ExclusiveGatewayImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getExclusiveGateway()
         * @generated
         */
        EClass EXCLUSIVE_GATEWAY = eINSTANCE.getExclusiveGateway();

        /**
         * The meta object literal for the '<em><b>Default Sequence Flow</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW = eINSTANCE.getExclusiveGateway_DefaultSequenceFlow();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.IncusiveGatewayImpl <em>Incusive Gateway</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.IncusiveGatewayImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getIncusiveGateway()
         * @generated
         */
        EClass INCUSIVE_GATEWAY = eINSTANCE.getIncusiveGateway();

        /**
         * The meta object literal for the '<em><b>Default Sequence Flow</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INCUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW = eINSTANCE.getIncusiveGateway_DefaultSequenceFlow();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ComplexGatewayImpl <em>Complex Gateway</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ComplexGatewayImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getComplexGateway()
         * @generated
         */
        EClass COMPLEX_GATEWAY = eINSTANCE.getComplexGateway();

        /**
         * The meta object literal for the '<em><b>Default Sequence Flow</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMPLEX_GATEWAY__DEFAULT_SEQUENCE_FLOW = eINSTANCE.getComplexGateway_DefaultSequenceFlow();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.EventImpl <em>Event</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.EventImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getEvent()
         * @generated
         */
        EClass EVENT = eINSTANCE.getEvent();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.CatchEventImpl <em>Catch Event</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.CatchEventImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getCatchEvent()
         * @generated
         */
        EClass CATCH_EVENT = eINSTANCE.getCatchEvent();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ThrowEventImpl <em>Throw Event</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ThrowEventImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getThrowEvent()
         * @generated
         */
        EClass THROW_EVENT = eINSTANCE.getThrowEvent();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.ActivityImpl <em>Activity</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.ActivityImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getActivity()
         * @generated
         */
        EClass ACTIVITY = eINSTANCE.getActivity();

        /**
         * The meta object literal for the '{@link org.imixs.bpmn.bpmngraph.impl.TaskImpl <em>Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.imixs.bpmn.bpmngraph.impl.TaskImpl
         * @see org.imixs.bpmn.bpmngraph.impl.BpmngraphPackageImpl#getTask()
         * @generated
         */
        EClass TASK = eINSTANCE.getTask();

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

    }

} //BpmngraphPackage
