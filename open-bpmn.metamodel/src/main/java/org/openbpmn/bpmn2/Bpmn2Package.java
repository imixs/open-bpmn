/**
 */
package org.openbpmn.bpmn2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.openbpmn.bpmn2.Bpmn2Factory
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
    String eNS_URI = "http://www.omg.org/spec/BPMN/20100501/MODEL-XMI";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "bpmn2";

    /**
     * The package content type ID.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eCONTENT_TYPE = "bpmn";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    Bpmn2Package eINSTANCE = org.openbpmn.bpmn2.impl.Bpmn2PackageImpl.init();

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.BaseElementImpl <em>Base Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.BaseElementImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getBaseElement()
     * @generated
     */
    int BASE_ELEMENT = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__ID = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT__DOCUMENTATION = 1;

    /**
     * The number of structural features of the '<em>Base Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Base Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BASE_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.DefinitionsImpl <em>Definitions</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.DefinitionsImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDefinitions()
     * @generated
     */
    int DEFINITIONS = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__NAME = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__TARGET_NAMESPACE = BASE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Expression Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__EXPRESSION_LANGUAGE = BASE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Type Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__TYPE_LANGUAGE = BASE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Exporter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__EXPORTER = BASE_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Exporter Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__EXPORTER_VERSION = BASE_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Diagrams</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__DIAGRAMS = BASE_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Processes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS__PROCESSES = BASE_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Definitions</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Definitions</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFINITIONS_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.RootElementImpl <em>Root Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.RootElementImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getRootElement()
     * @generated
     */
    int ROOT_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROOT_ELEMENT__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROOT_ELEMENT__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Root Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROOT_ELEMENT_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Root Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROOT_ELEMENT_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.DocumentRootImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 2;

    /**
     * The feature id for the '<em><b>Root Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ROOT_ELEMENT = 0;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.FlowElementImpl <em>Flow Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.FlowElementImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getFlowElement()
     * @generated
     */
    int FLOW_ELEMENT = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_ELEMENT__NAME = BASE_ELEMENT_FEATURE_COUNT + 0;

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
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.FlowNodeImpl <em>Flow Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.FlowNodeImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getFlowNode()
     * @generated
     */
    int FLOW_NODE = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__ID = FLOW_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE__NAME = FLOW_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Flow Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Flow Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_NODE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.DocumentationImpl <em>Documentation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.DocumentationImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDocumentation()
     * @generated
     */
    int DOCUMENTATION = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION__TEXT = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Text Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION__TEXT_FORMAT = BASE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Documentation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Documentation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENTATION_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.ActivityImpl <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.ActivityImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getActivity()
     * @generated
     */
    int ACTIVITY = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__ID = FLOW_NODE__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__DOCUMENTATION = FLOW_NODE__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__NAME = FLOW_NODE__NAME;

    /**
     * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__IS_FOR_COMPENSATION = FLOW_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__START_QUANTITY = FLOW_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__COMPLETION_QUANTITY = FLOW_NODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY__RESOURCES = FLOW_NODE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY_FEATURE_COUNT = FLOW_NODE_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTIVITY_OPERATION_COUNT = FLOW_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.TaskImpl <em>Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.TaskImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getTask()
     * @generated
     */
    int TASK = 8;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__ID = ACTIVITY__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__NAME = ACTIVITY__NAME;

    /**
     * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__IS_FOR_COMPENSATION = ACTIVITY__IS_FOR_COMPENSATION;

    /**
     * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__START_QUANTITY = ACTIVITY__START_QUANTITY;

    /**
     * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__COMPLETION_QUANTITY = ACTIVITY__COMPLETION_QUANTITY;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TASK__RESOURCES = ACTIVITY__RESOURCES;

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
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.SendTaskImpl <em>Send Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.SendTaskImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getSendTask()
     * @generated
     */
    int SEND_TASK = 9;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__ID = TASK__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__DOCUMENTATION = TASK__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__NAME = TASK__NAME;

    /**
     * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__IS_FOR_COMPENSATION = TASK__IS_FOR_COMPENSATION;

    /**
     * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__START_QUANTITY = TASK__START_QUANTITY;

    /**
     * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__COMPLETION_QUANTITY = TASK__COMPLETION_QUANTITY;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__RESOURCES = TASK__RESOURCES;

    /**
     * The feature id for the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK__IMPLEMENTATION = TASK_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Send Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Send Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEND_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.ServiceTaskImpl <em>Service Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.ServiceTaskImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getServiceTask()
     * @generated
     */
    int SERVICE_TASK = 10;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__ID = TASK__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__DOCUMENTATION = TASK__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__NAME = TASK__NAME;

    /**
     * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__IS_FOR_COMPENSATION = TASK__IS_FOR_COMPENSATION;

    /**
     * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__START_QUANTITY = TASK__START_QUANTITY;

    /**
     * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__COMPLETION_QUANTITY = TASK__COMPLETION_QUANTITY;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__RESOURCES = TASK__RESOURCES;

    /**
     * The feature id for the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK__IMPLEMENTATION = TASK_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Service Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Service Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.RuleTaskImpl <em>Rule Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.RuleTaskImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getRuleTask()
     * @generated
     */
    int RULE_TASK = 11;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__ID = TASK__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__DOCUMENTATION = TASK__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__NAME = TASK__NAME;

    /**
     * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__IS_FOR_COMPENSATION = TASK__IS_FOR_COMPENSATION;

    /**
     * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__START_QUANTITY = TASK__START_QUANTITY;

    /**
     * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__COMPLETION_QUANTITY = TASK__COMPLETION_QUANTITY;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__RESOURCES = TASK__RESOURCES;

    /**
     * The feature id for the '<em><b>Implementation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK__IMPLEMENTATION = TASK_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Rule Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Rule Task</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RULE_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.ProcessImpl <em>Process</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.ProcessImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getProcess()
     * @generated
     */
    int PROCESS = 12;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS__ID = ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS__DOCUMENTATION = ROOT_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Is Executable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS__IS_EXECUTABLE = ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Is Closed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS__IS_CLOSED = ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Resources</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS__RESOURCES = ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Process</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_FEATURE_COUNT = ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Process</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCESS_OPERATION_COUNT = ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.openbpmn.bpmn2.impl.ResourceRoleImpl <em>Resource Role</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.bpmn2.impl.ResourceRoleImpl
     * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getResourceRole()
     * @generated
     */
    int RESOURCE_ROLE = 13;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESOURCE_ROLE__ID = BASE_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESOURCE_ROLE__DOCUMENTATION = BASE_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESOURCE_ROLE__NAME = BASE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Resource Role</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESOURCE_ROLE_FEATURE_COUNT = BASE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Resource Role</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESOURCE_ROLE_OPERATION_COUNT = BASE_ELEMENT_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.Definitions <em>Definitions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Definitions</em>'.
     * @see org.openbpmn.bpmn2.Definitions
     * @generated
     */
    EClass getDefinitions();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getName()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Namespace</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getTargetNamespace()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_TargetNamespace();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression Language</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getExpressionLanguage()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_ExpressionLanguage();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type Language</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getTypeLanguage()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_TypeLanguage();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getExporter <em>Exporter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exporter</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getExporter()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_Exporter();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exporter Version</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getExporterVersion()
     * @see #getDefinitions()
     * @generated
     */
    EAttribute getDefinitions_ExporterVersion();

    /**
     * Returns the meta object for the containment reference list '{@link org.openbpmn.bpmn2.Definitions#getDiagrams <em>Diagrams</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Diagrams</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getDiagrams()
     * @see #getDefinitions()
     * @generated
     */
    EReference getDefinitions_Diagrams();

    /**
     * Returns the meta object for the containment reference list '{@link org.openbpmn.bpmn2.Definitions#getProcesses <em>Processes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Processes</em>'.
     * @see org.openbpmn.bpmn2.Definitions#getProcesses()
     * @see #getDefinitions()
     * @generated
     */
    EReference getDefinitions_Processes();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.RootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Root Element</em>'.
     * @see org.openbpmn.bpmn2.RootElement
     * @generated
     */
    EClass getRootElement();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.openbpmn.bpmn2.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the containment reference '{@link org.openbpmn.bpmn2.DocumentRoot#getRootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Root Element</em>'.
     * @see org.openbpmn.bpmn2.DocumentRoot#getRootElement()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_RootElement();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.BaseElement <em>Base Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Base Element</em>'.
     * @see org.openbpmn.bpmn2.BaseElement
     * @generated
     */
    EClass getBaseElement();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.BaseElement#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.openbpmn.bpmn2.BaseElement#getId()
     * @see #getBaseElement()
     * @generated
     */
    EAttribute getBaseElement_Id();

    /**
     * Returns the meta object for the containment reference list '{@link org.openbpmn.bpmn2.BaseElement#getDocumentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Documentation</em>'.
     * @see org.openbpmn.bpmn2.BaseElement#getDocumentation()
     * @see #getBaseElement()
     * @generated
     */
    EReference getBaseElement_Documentation();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Element</em>'.
     * @see org.openbpmn.bpmn2.FlowElement
     * @generated
     */
    EClass getFlowElement();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.FlowElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.bpmn2.FlowElement#getName()
     * @see #getFlowElement()
     * @generated
     */
    EAttribute getFlowElement_Name();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.FlowNode <em>Flow Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Node</em>'.
     * @see org.openbpmn.bpmn2.FlowNode
     * @generated
     */
    EClass getFlowNode();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.Documentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Documentation</em>'.
     * @see org.openbpmn.bpmn2.Documentation
     * @generated
     */
    EClass getDocumentation();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Documentation#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.openbpmn.bpmn2.Documentation#getText()
     * @see #getDocumentation()
     * @generated
     */
    EAttribute getDocumentation_Text();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Documentation#getTextFormat <em>Text Format</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text Format</em>'.
     * @see org.openbpmn.bpmn2.Documentation#getTextFormat()
     * @see #getDocumentation()
     * @generated
     */
    EAttribute getDocumentation_TextFormat();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Activity</em>'.
     * @see org.openbpmn.bpmn2.Activity
     * @generated
     */
    EClass getActivity();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Activity#isIsForCompensation <em>Is For Compensation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is For Compensation</em>'.
     * @see org.openbpmn.bpmn2.Activity#isIsForCompensation()
     * @see #getActivity()
     * @generated
     */
    EAttribute getActivity_IsForCompensation();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Activity#getStartQuantity <em>Start Quantity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Quantity</em>'.
     * @see org.openbpmn.bpmn2.Activity#getStartQuantity()
     * @see #getActivity()
     * @generated
     */
    EAttribute getActivity_StartQuantity();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Activity#getCompletionQuantity <em>Completion Quantity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Completion Quantity</em>'.
     * @see org.openbpmn.bpmn2.Activity#getCompletionQuantity()
     * @see #getActivity()
     * @generated
     */
    EAttribute getActivity_CompletionQuantity();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.bpmn2.Activity#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Resources</em>'.
     * @see org.openbpmn.bpmn2.Activity#getResources()
     * @see #getActivity()
     * @generated
     */
    EReference getActivity_Resources();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task</em>'.
     * @see org.openbpmn.bpmn2.Task
     * @generated
     */
    EClass getTask();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.SendTask <em>Send Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Send Task</em>'.
     * @see org.openbpmn.bpmn2.SendTask
     * @generated
     */
    EClass getSendTask();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.SendTask#getImplementation <em>Implementation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation</em>'.
     * @see org.openbpmn.bpmn2.SendTask#getImplementation()
     * @see #getSendTask()
     * @generated
     */
    EAttribute getSendTask_Implementation();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.ServiceTask <em>Service Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Service Task</em>'.
     * @see org.openbpmn.bpmn2.ServiceTask
     * @generated
     */
    EClass getServiceTask();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.ServiceTask#getImplementation <em>Implementation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation</em>'.
     * @see org.openbpmn.bpmn2.ServiceTask#getImplementation()
     * @see #getServiceTask()
     * @generated
     */
    EAttribute getServiceTask_Implementation();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.RuleTask <em>Rule Task</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Rule Task</em>'.
     * @see org.openbpmn.bpmn2.RuleTask
     * @generated
     */
    EClass getRuleTask();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.RuleTask#getImplementation <em>Implementation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation</em>'.
     * @see org.openbpmn.bpmn2.RuleTask#getImplementation()
     * @see #getRuleTask()
     * @generated
     */
    EAttribute getRuleTask_Implementation();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.Process <em>Process</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Process</em>'.
     * @see org.openbpmn.bpmn2.Process
     * @generated
     */
    EClass getProcess();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Process#isIsExecutable <em>Is Executable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Executable</em>'.
     * @see org.openbpmn.bpmn2.Process#isIsExecutable()
     * @see #getProcess()
     * @generated
     */
    EAttribute getProcess_IsExecutable();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.Process#isIsClosed <em>Is Closed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Closed</em>'.
     * @see org.openbpmn.bpmn2.Process#isIsClosed()
     * @see #getProcess()
     * @generated
     */
    EAttribute getProcess_IsClosed();

    /**
     * Returns the meta object for the reference '{@link org.openbpmn.bpmn2.Process#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Resources</em>'.
     * @see org.openbpmn.bpmn2.Process#getResources()
     * @see #getProcess()
     * @generated
     */
    EReference getProcess_Resources();

    /**
     * Returns the meta object for class '{@link org.openbpmn.bpmn2.ResourceRole <em>Resource Role</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resource Role</em>'.
     * @see org.openbpmn.bpmn2.ResourceRole
     * @generated
     */
    EClass getResourceRole();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.bpmn2.ResourceRole#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.bpmn2.ResourceRole#getName()
     * @see #getResourceRole()
     * @generated
     */
    EAttribute getResourceRole_Name();

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
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.DefinitionsImpl <em>Definitions</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.DefinitionsImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDefinitions()
         * @generated
         */
        EClass DEFINITIONS = eINSTANCE.getDefinitions();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__NAME = eINSTANCE.getDefinitions_Name();

        /**
         * The meta object literal for the '<em><b>Target Namespace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__TARGET_NAMESPACE = eINSTANCE.getDefinitions_TargetNamespace();

        /**
         * The meta object literal for the '<em><b>Expression Language</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__EXPRESSION_LANGUAGE = eINSTANCE.getDefinitions_ExpressionLanguage();

        /**
         * The meta object literal for the '<em><b>Type Language</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__TYPE_LANGUAGE = eINSTANCE.getDefinitions_TypeLanguage();

        /**
         * The meta object literal for the '<em><b>Exporter</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__EXPORTER = eINSTANCE.getDefinitions_Exporter();

        /**
         * The meta object literal for the '<em><b>Exporter Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DEFINITIONS__EXPORTER_VERSION = eINSTANCE.getDefinitions_ExporterVersion();

        /**
         * The meta object literal for the '<em><b>Diagrams</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DEFINITIONS__DIAGRAMS = eINSTANCE.getDefinitions_Diagrams();

        /**
         * The meta object literal for the '<em><b>Processes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DEFINITIONS__PROCESSES = eINSTANCE.getDefinitions_Processes();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.RootElementImpl <em>Root Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.RootElementImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getRootElement()
         * @generated
         */
        EClass ROOT_ELEMENT = eINSTANCE.getRootElement();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.DocumentRootImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDocumentRoot()
         * @generated
         */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
         * The meta object literal for the '<em><b>Root Element</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__ROOT_ELEMENT = eINSTANCE.getDocumentRoot_RootElement();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.BaseElementImpl <em>Base Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.BaseElementImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getBaseElement()
         * @generated
         */
        EClass BASE_ELEMENT = eINSTANCE.getBaseElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BASE_ELEMENT__ID = eINSTANCE.getBaseElement_Id();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BASE_ELEMENT__DOCUMENTATION = eINSTANCE.getBaseElement_Documentation();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.FlowElementImpl <em>Flow Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.FlowElementImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getFlowElement()
         * @generated
         */
        EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FLOW_ELEMENT__NAME = eINSTANCE.getFlowElement_Name();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.FlowNodeImpl <em>Flow Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.FlowNodeImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getFlowNode()
         * @generated
         */
        EClass FLOW_NODE = eINSTANCE.getFlowNode();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.DocumentationImpl <em>Documentation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.DocumentationImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getDocumentation()
         * @generated
         */
        EClass DOCUMENTATION = eINSTANCE.getDocumentation();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENTATION__TEXT = eINSTANCE.getDocumentation_Text();

        /**
         * The meta object literal for the '<em><b>Text Format</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENTATION__TEXT_FORMAT = eINSTANCE.getDocumentation_TextFormat();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.ActivityImpl <em>Activity</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.ActivityImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getActivity()
         * @generated
         */
        EClass ACTIVITY = eINSTANCE.getActivity();

        /**
         * The meta object literal for the '<em><b>Is For Compensation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ACTIVITY__IS_FOR_COMPENSATION = eINSTANCE.getActivity_IsForCompensation();

        /**
         * The meta object literal for the '<em><b>Start Quantity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ACTIVITY__START_QUANTITY = eINSTANCE.getActivity_StartQuantity();

        /**
         * The meta object literal for the '<em><b>Completion Quantity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ACTIVITY__COMPLETION_QUANTITY = eINSTANCE.getActivity_CompletionQuantity();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ACTIVITY__RESOURCES = eINSTANCE.getActivity_Resources();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.TaskImpl <em>Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.TaskImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getTask()
         * @generated
         */
        EClass TASK = eINSTANCE.getTask();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.SendTaskImpl <em>Send Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.SendTaskImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getSendTask()
         * @generated
         */
        EClass SEND_TASK = eINSTANCE.getSendTask();

        /**
         * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEND_TASK__IMPLEMENTATION = eINSTANCE.getSendTask_Implementation();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.ServiceTaskImpl <em>Service Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.ServiceTaskImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getServiceTask()
         * @generated
         */
        EClass SERVICE_TASK = eINSTANCE.getServiceTask();

        /**
         * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SERVICE_TASK__IMPLEMENTATION = eINSTANCE.getServiceTask_Implementation();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.RuleTaskImpl <em>Rule Task</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.RuleTaskImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getRuleTask()
         * @generated
         */
        EClass RULE_TASK = eINSTANCE.getRuleTask();

        /**
         * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RULE_TASK__IMPLEMENTATION = eINSTANCE.getRuleTask_Implementation();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.ProcessImpl <em>Process</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.ProcessImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getProcess()
         * @generated
         */
        EClass PROCESS = eINSTANCE.getProcess();

        /**
         * The meta object literal for the '<em><b>Is Executable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROCESS__IS_EXECUTABLE = eINSTANCE.getProcess_IsExecutable();

        /**
         * The meta object literal for the '<em><b>Is Closed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PROCESS__IS_CLOSED = eINSTANCE.getProcess_IsClosed();

        /**
         * The meta object literal for the '<em><b>Resources</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROCESS__RESOURCES = eINSTANCE.getProcess_Resources();

        /**
         * The meta object literal for the '{@link org.openbpmn.bpmn2.impl.ResourceRoleImpl <em>Resource Role</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.bpmn2.impl.ResourceRoleImpl
         * @see org.openbpmn.bpmn2.impl.Bpmn2PackageImpl#getResourceRole()
         * @generated
         */
        EClass RESOURCE_ROLE = eINSTANCE.getResourceRole();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RESOURCE_ROLE__NAME = eINSTANCE.getResourceRole_Name();

    }

} //Bpmn2Package
