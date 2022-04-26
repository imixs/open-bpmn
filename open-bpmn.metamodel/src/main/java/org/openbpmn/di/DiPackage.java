/**
 */
package org.openbpmn.di;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.openbpmn.di.DiFactory
 * @model kind="package"
 * @generated
 */
public interface DiPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "di";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.omg.org/spec/DD/20100524/DI";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "di";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DiPackage eINSTANCE = org.openbpmn.di.impl.DiPackageImpl.init();

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.DiagramImpl <em>Diagram</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.DiagramImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getDiagram()
     * @generated
     */
    int DIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__DOCUMENTATION = 0;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__OWNED_STYLE = 1;

    /**
     * The feature id for the '<em><b>Root Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__ROOT_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__ID = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__NAME = 4;

    /**
     * The feature id for the '<em><b>Resolution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM__RESOLUTION = 5;

    /**
     * The number of structural features of the '<em>Diagram</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Diagram</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.DiagramElementImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getDiagramElement()
     * @generated
     */
    int DIAGRAM_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNING_DIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNING_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__OWNED_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__MODEL_ELEMENT = 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__STYLE = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__ID = 5;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT__ANY_ATTRIBUTE = 6;

    /**
     * The number of structural features of the '<em>Diagram Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_FEATURE_COUNT = 7;

    /**
     * The number of operations of the '<em>Diagram Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.EdgeImpl <em>Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.EdgeImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getEdge()
     * @generated
     */
    int EDGE = 2;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNING_DIAGRAM = DIAGRAM_ELEMENT__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNING_ELEMENT = DIAGRAM_ELEMENT__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__OWNED_ELEMENT = DIAGRAM_ELEMENT__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__MODEL_ELEMENT = DIAGRAM_ELEMENT__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__STYLE = DIAGRAM_ELEMENT__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__ID = DIAGRAM_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__ANY_ATTRIBUTE = DIAGRAM_ELEMENT__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__SOURCE = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TARGET = DIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Waypoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__WAYPOINT = DIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_OPERATION_COUNT = DIAGRAM_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.NodeImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getNode()
     * @generated
     */
    int NODE = 6;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNING_DIAGRAM = DIAGRAM_ELEMENT__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNING_ELEMENT = DIAGRAM_ELEMENT__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OWNED_ELEMENT = DIAGRAM_ELEMENT__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__MODEL_ELEMENT = DIAGRAM_ELEMENT__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__STYLE = DIAGRAM_ELEMENT__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__ID = DIAGRAM_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__ANY_ATTRIBUTE = DIAGRAM_ELEMENT__ANY_ATTRIBUTE;

    /**
     * The number of structural features of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_FEATURE_COUNT = DIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_OPERATION_COUNT = DIAGRAM_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.LabelImpl <em>Label</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.LabelImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getLabel()
     * @generated
     */
    int LABEL = 3;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__ANY_ATTRIBUTE = NODE__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL__BOUNDS = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Label</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.LabeledEdgeImpl <em>Labeled Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.LabeledEdgeImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getLabeledEdge()
     * @generated
     */
    int LABELED_EDGE = 4;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNING_DIAGRAM = EDGE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNING_ELEMENT = EDGE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNED_ELEMENT = EDGE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__MODEL_ELEMENT = EDGE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__STYLE = EDGE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__ID = EDGE__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__ANY_ATTRIBUTE = EDGE__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__SOURCE = EDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__TARGET = EDGE__TARGET;

    /**
     * The feature id for the '<em><b>Waypoint</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__WAYPOINT = EDGE__WAYPOINT;

    /**
     * The feature id for the '<em><b>Owned Label</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE__OWNED_LABEL = EDGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Labeled Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE_FEATURE_COUNT = EDGE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Labeled Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_EDGE_OPERATION_COUNT = EDGE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.ShapeImpl <em>Shape</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.ShapeImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getShape()
     * @generated
     */
    int SHAPE = 8;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__ANY_ATTRIBUTE = NODE__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE__BOUNDS = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.LabeledShapeImpl <em>Labeled Shape</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.LabeledShapeImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getLabeledShape()
     * @generated
     */
    int LABELED_SHAPE = 5;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNING_DIAGRAM = SHAPE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNING_ELEMENT = SHAPE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNED_ELEMENT = SHAPE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__MODEL_ELEMENT = SHAPE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__STYLE = SHAPE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__ID = SHAPE__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__ANY_ATTRIBUTE = SHAPE__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Bounds</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__BOUNDS = SHAPE__BOUNDS;

    /**
     * The feature id for the '<em><b>Owned Label</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE__OWNED_LABEL = SHAPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Labeled Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE_FEATURE_COUNT = SHAPE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Labeled Shape</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABELED_SHAPE_OPERATION_COUNT = SHAPE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.PlaneImpl <em>Plane</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.PlaneImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getPlane()
     * @generated
     */
    int PLANE = 7;

    /**
     * The feature id for the '<em><b>Owning Diagram</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNING_DIAGRAM = NODE__OWNING_DIAGRAM;

    /**
     * The feature id for the '<em><b>Owning Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNING_ELEMENT = NODE__OWNING_ELEMENT;

    /**
     * The feature id for the '<em><b>Owned Element</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__OWNED_ELEMENT = NODE__OWNED_ELEMENT;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__MODEL_ELEMENT = NODE__MODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__STYLE = NODE__STYLE;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__ID = NODE__ID;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__ANY_ATTRIBUTE = NODE__ANY_ATTRIBUTE;

    /**
     * The feature id for the '<em><b>Plane Element</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE__PLANE_ELEMENT = NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Plane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

    /**
     * The operation id for the '<em>Plane element type</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE___PLANE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP = NODE_OPERATION_COUNT + 0;

    /**
     * The number of operations of the '<em>Plane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLANE_OPERATION_COUNT = NODE_OPERATION_COUNT + 1;

    /**
     * The meta object id for the '{@link org.openbpmn.di.impl.StyleImpl <em>Style</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.di.impl.StyleImpl
     * @see org.openbpmn.di.impl.DiPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 9;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE__ID = 0;

    /**
     * The number of structural features of the '<em>Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STYLE_OPERATION_COUNT = 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Diagram <em>Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Diagram</em>'.
     * @see org.openbpmn.di.Diagram
     * @generated
     */
    EClass getDiagram();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.Diagram#getDocumentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.openbpmn.di.Diagram#getDocumentation()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Documentation();

    /**
     * Returns the meta object for the reference list '{@link org.openbpmn.di.Diagram#getOwnedStyle <em>Owned Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Style</em>'.
     * @see org.openbpmn.di.Diagram#getOwnedStyle()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_OwnedStyle();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.Diagram#getRootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Root Element</em>'.
     * @see org.openbpmn.di.Diagram#getRootElement()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_RootElement();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.Diagram#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.openbpmn.di.Diagram#getId()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Id();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.Diagram#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.di.Diagram#getName()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.Diagram#getResolution <em>Resolution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resolution</em>'.
     * @see org.openbpmn.di.Diagram#getResolution()
     * @see #getDiagram()
     * @generated
     */
    EAttribute getDiagram_Resolution();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.DiagramElement <em>Diagram Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Diagram Element</em>'.
     * @see org.openbpmn.di.DiagramElement
     * @generated
     */
    EClass getDiagramElement();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.DiagramElement#getOwningDiagram <em>Owning Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owning Diagram</em>'.
     * @see org.openbpmn.di.DiagramElement#getOwningDiagram()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwningDiagram();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.DiagramElement#getOwningElement <em>Owning Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owning Element</em>'.
     * @see org.openbpmn.di.DiagramElement#getOwningElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwningElement();

    /**
     * Returns the meta object for the reference list '{@link org.openbpmn.di.DiagramElement#getOwnedElement <em>Owned Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Element</em>'.
     * @see org.openbpmn.di.DiagramElement#getOwnedElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_OwnedElement();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.DiagramElement#getModelElement <em>Model Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.openbpmn.di.DiagramElement#getModelElement()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_ModelElement();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.DiagramElement#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Style</em>'.
     * @see org.openbpmn.di.DiagramElement#getStyle()
     * @see #getDiagramElement()
     * @generated
     */
    EReference getDiagramElement_Style();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.DiagramElement#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.openbpmn.di.DiagramElement#getId()
     * @see #getDiagramElement()
     * @generated
     */
    EAttribute getDiagramElement_Id();

    /**
     * Returns the meta object for the attribute list '{@link org.openbpmn.di.DiagramElement#getAnyAttribute <em>Any Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.openbpmn.di.DiagramElement#getAnyAttribute()
     * @see #getDiagramElement()
     * @generated
     */
    EAttribute getDiagramElement_AnyAttribute();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge</em>'.
     * @see org.openbpmn.di.Edge
     * @generated
     */
    EClass getEdge();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.Edge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.openbpmn.di.Edge#getSource()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Source();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.di.Edge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.openbpmn.di.Edge#getTarget()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Target();

    /**
     * Returns the meta object for the containment reference list '{@link org.openbpmn.di.Edge#getWaypoint <em>Waypoint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Waypoint</em>'.
     * @see org.openbpmn.di.Edge#getWaypoint()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Waypoint();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Label <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Label</em>'.
     * @see org.openbpmn.di.Label
     * @generated
     */
    EClass getLabel();

    /**
     * Returns the meta object for the containment reference '{@link org.openbpmn.di.Label#getBounds <em>Bounds</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bounds</em>'.
     * @see org.openbpmn.di.Label#getBounds()
     * @see #getLabel()
     * @generated
     */
    EReference getLabel_Bounds();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.LabeledEdge <em>Labeled Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Labeled Edge</em>'.
     * @see org.openbpmn.di.LabeledEdge
     * @generated
     */
    EClass getLabeledEdge();

    /**
     * Returns the meta object for the reference list '{@link org.openbpmn.di.LabeledEdge#getOwnedLabel <em>Owned Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Label</em>'.
     * @see org.openbpmn.di.LabeledEdge#getOwnedLabel()
     * @see #getLabeledEdge()
     * @generated
     */
    EReference getLabeledEdge_OwnedLabel();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.LabeledShape <em>Labeled Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Labeled Shape</em>'.
     * @see org.openbpmn.di.LabeledShape
     * @generated
     */
    EClass getLabeledShape();

    /**
     * Returns the meta object for the reference list '{@link org.openbpmn.di.LabeledShape#getOwnedLabel <em>Owned Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Owned Label</em>'.
     * @see org.openbpmn.di.LabeledShape#getOwnedLabel()
     * @see #getLabeledShape()
     * @generated
     */
    EReference getLabeledShape_OwnedLabel();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see org.openbpmn.di.Node
     * @generated
     */
    EClass getNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Plane <em>Plane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Plane</em>'.
     * @see org.openbpmn.di.Plane
     * @generated
     */
    EClass getPlane();

    /**
     * Returns the meta object for the containment reference list '{@link org.openbpmn.di.Plane#getPlaneElement <em>Plane Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Plane Element</em>'.
     * @see org.openbpmn.di.Plane#getPlaneElement()
     * @see #getPlane()
     * @generated
     */
    EReference getPlane_PlaneElement();

    /**
     * Returns the meta object for the '{@link org.openbpmn.di.Plane#plane_element_type(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Plane element type</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Plane element type</em>' operation.
     * @see org.openbpmn.di.Plane#plane_element_type(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
     * @generated
     */
    EOperation getPlane__Plane_element_type__DiagnosticChain_Map();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Shape <em>Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Shape</em>'.
     * @see org.openbpmn.di.Shape
     * @generated
     */
    EClass getShape();

    /**
     * Returns the meta object for the containment reference '{@link org.openbpmn.di.Shape#getBounds <em>Bounds</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bounds</em>'.
     * @see org.openbpmn.di.Shape#getBounds()
     * @see #getShape()
     * @generated
     */
    EReference getShape_Bounds();

    /**
     * Returns the meta object for class '{@link org.openbpmn.di.Style <em>Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Style</em>'.
     * @see org.openbpmn.di.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.di.Style#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.openbpmn.di.Style#getId()
     * @see #getStyle()
     * @generated
     */
    EAttribute getStyle_Id();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DiFactory getDiFactory();

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
         * The meta object literal for the '{@link org.openbpmn.di.impl.DiagramImpl <em>Diagram</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.DiagramImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getDiagram()
         * @generated
         */
        EClass DIAGRAM = eINSTANCE.getDiagram();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__DOCUMENTATION = eINSTANCE.getDiagram_Documentation();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__OWNED_STYLE = eINSTANCE.getDiagram_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Root Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM__ROOT_ELEMENT = eINSTANCE.getDiagram_RootElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__ID = eINSTANCE.getDiagram_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

        /**
         * The meta object literal for the '<em><b>Resolution</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM__RESOLUTION = eINSTANCE.getDiagram_Resolution();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.DiagramElementImpl <em>Diagram Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.DiagramElementImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getDiagramElement()
         * @generated
         */
        EClass DIAGRAM_ELEMENT = eINSTANCE.getDiagramElement();

        /**
         * The meta object literal for the '<em><b>Owning Diagram</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNING_DIAGRAM = eINSTANCE.getDiagramElement_OwningDiagram();

        /**
         * The meta object literal for the '<em><b>Owning Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNING_ELEMENT = eINSTANCE.getDiagramElement_OwningElement();

        /**
         * The meta object literal for the '<em><b>Owned Element</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__OWNED_ELEMENT = eINSTANCE.getDiagramElement_OwnedElement();

        /**
         * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__MODEL_ELEMENT = eINSTANCE.getDiagramElement_ModelElement();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DIAGRAM_ELEMENT__STYLE = eINSTANCE.getDiagramElement_Style();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT__ID = eINSTANCE.getDiagramElement_Id();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT__ANY_ATTRIBUTE = eINSTANCE.getDiagramElement_AnyAttribute();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.EdgeImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

        /**
         * The meta object literal for the '<em><b>Waypoint</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EDGE__WAYPOINT = eINSTANCE.getEdge_Waypoint();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.LabelImpl <em>Label</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.LabelImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getLabel()
         * @generated
         */
        EClass LABEL = eINSTANCE.getLabel();

        /**
         * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABEL__BOUNDS = eINSTANCE.getLabel_Bounds();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.LabeledEdgeImpl <em>Labeled Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.LabeledEdgeImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getLabeledEdge()
         * @generated
         */
        EClass LABELED_EDGE = eINSTANCE.getLabeledEdge();

        /**
         * The meta object literal for the '<em><b>Owned Label</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABELED_EDGE__OWNED_LABEL = eINSTANCE.getLabeledEdge_OwnedLabel();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.LabeledShapeImpl <em>Labeled Shape</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.LabeledShapeImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getLabeledShape()
         * @generated
         */
        EClass LABELED_SHAPE = eINSTANCE.getLabeledShape();

        /**
         * The meta object literal for the '<em><b>Owned Label</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LABELED_SHAPE__OWNED_LABEL = eINSTANCE.getLabeledShape_OwnedLabel();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.NodeImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getNode()
         * @generated
         */
        EClass NODE = eINSTANCE.getNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.PlaneImpl <em>Plane</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.PlaneImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getPlane()
         * @generated
         */
        EClass PLANE = eINSTANCE.getPlane();

        /**
         * The meta object literal for the '<em><b>Plane Element</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLANE__PLANE_ELEMENT = eINSTANCE.getPlane_PlaneElement();

        /**
         * The meta object literal for the '<em><b>Plane element type</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation PLANE___PLANE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getPlane__Plane_element_type__DiagnosticChain_Map();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.ShapeImpl <em>Shape</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.ShapeImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getShape()
         * @generated
         */
        EClass SHAPE = eINSTANCE.getShape();

        /**
         * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SHAPE__BOUNDS = eINSTANCE.getShape_Bounds();

        /**
         * The meta object literal for the '{@link org.openbpmn.di.impl.StyleImpl <em>Style</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.di.impl.StyleImpl
         * @see org.openbpmn.di.impl.DiPackageImpl#getStyle()
         * @generated
         */
        EClass STYLE = eINSTANCE.getStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STYLE__ID = eINSTANCE.getStyle_Id();

    }

} //DiPackage
