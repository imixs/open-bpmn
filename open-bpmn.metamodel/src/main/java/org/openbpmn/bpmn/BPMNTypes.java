package org.openbpmn.bpmn;

import java.util.Arrays;
import java.util.List;

import org.openbpmn.bpmn.elements.DataObject;
import org.openbpmn.bpmn.elements.DataStoreReference;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;

/**
 * The BPMNTypes provides constants defining the visual elements contained in a
 * BPMNDiagram.
 * 
 * @author rsoika
 *
 */
public class BPMNTypes {

    // Process Types
    public static final String PROCESS_TYPE_PUBLIC = "Public";
    public static final String PROCESS_TYPE_PRIVATE = "Private";
    public static final String PROCESS_TYPE_NONE = "None";

    // Tasks
    public static final String TASK = "task";
    public static final String USER_TASK = "userTask";
    public static final String SCRIPT_TASK = "scriptTask";
    public static final String SERVICE_TASK = "serviceTask";
    public static final String SEND_TASK = "sendTask";
    public static final String MANUAL_TASK = "manualTask";
    public static final String BUSINESSRULE_TASK = "businessRuleTask";
    public static final String RECEIVE_TASK = "receiveTask";
    public static final String SUB_PROCESS = "subProcess";
    public static final String ADHOC_SUB_PROCESS = "adHocSubProcess";
    public static final String TRANSACTION = "transaction";
    public static final String CALL_ACTIVITY = "callActivity";

    // Events
    public static final String EVENT = "event";
    public static final String START_EVENT = "startEvent";
    public static final String END_EVENT = "endEvent";
    public static final String CATCH_EVENT = "intermediateCatchEvent";
    public static final String THROW_EVENT = "intermediateThrowEvent";
    public static final String BOUNDARY_EVENT = "boundaryEvent";

    // Event Definitions
    public static final String EVENT_DEFINITION_CANCEL = "cancelEventDefinition";
    public static final String EVENT_DEFINITION_CONDITIONAL = "conditionalEventDefinition";
    public static final String EVENT_DEFINITION_COMPENSATION = "compensationEventDefinition";
    public static final String EVENT_DEFINITION_ERROR = "errorEventDefinition";
    public static final String EVENT_DEFINITION_ESCALATION = "escalationEventDefinition";
    public static final String EVENT_DEFINITION_LINK = "linkEventDefinition";
    public static final String EVENT_DEFINITION_MESSAGE = "messageEventDefinition";
    public static final String EVENT_DEFINITION_SIGNAL = "signalEventDefinition";
    public static final String EVENT_DEFINITION_TERMINATE = "terminateEventDefinition";
    public static final String EVENT_DEFINITION_TIMER = "timerEventDefinition";

    // Multiple Event Definitions
    public static final String MULTIPLE_EVENT_DEFINITIONS = "multipleEventDefinition";

    // Gateways
    public static final String GATEWAY = "gateway";
    public static final String EXCLUSIVE_GATEWAY = "exclusiveGateway";
    public static final String INCLUSIVE_GATEWAY = "inclusiveGateway";
    public static final String EVENTBASED_GATEWAY = "eventBasedGateway";
    public static final String PARALLEL_GATEWAY = "parallelGateway";
    public static final String COMPLEX_GATEWAY = "complexGateway";

    // Others
    public static final String DATAOBJECT = "dataObject";
    public static final String DATASTORE = "dataStore";
    public static final String DATASTOREREFERENCE = "dataStoreReference";
    public static final String TEXTANNOTATION = "textAnnotation";
    public static final String POOL = "pool";
    public static final String LANE = "lane";
    public static final String MESSAGE = "message";
    public static final String SIGNAL = "signal";
    public static final String SEQUENCE_FLOW = "sequenceFlow";
    public static final String MESSAGE_FLOW = "messageFlow";
    public static final String ASSOCIATION = "association";
    public static final String BPMNLABEL = "BPMNLabel";
    public static final String EXTENSION = "extension";
    public static final String PARTICIPANT = "participant";
    public static final String LANESET = "laneSet";

    // Type Collections
    public final static List<String> BPMN_TASKS = Arrays.asList(new String[] { //
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.USER_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.BUSINESSRULE_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.RECEIVE_TASK, //
            BPMNTypes.SUB_PROCESS
    });

    public final static List<String> BPMN_ACTIVITIES = Arrays.asList(new String[] { //
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.USER_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.BUSINESSRULE_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.RECEIVE_TASK, //
            BPMNTypes.SUB_PROCESS, //
            BPMNTypes.ADHOC_SUB_PROCESS, //
            BPMNTypes.TRANSACTION, //
            BPMNTypes.CALL_ACTIVITY
    });

    public final static List<String> BPMN_EVENTS = Arrays.asList(new String[] { //
            BPMNTypes.EVENT, //
            BPMNTypes.START_EVENT, //
            BPMNTypes.END_EVENT, //
            BPMNTypes.CATCH_EVENT, //
            BPMNTypes.THROW_EVENT, //
            BPMNTypes.BOUNDARY_EVENT //
    });

    public final static List<String> BPMN_FLOWELEMENT_NODES = Arrays.asList(//
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.USER_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.BUSINESSRULE_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.RECEIVE_TASK, //
            BPMNTypes.SUB_PROCESS, //

            BPMNTypes.EXCLUSIVE_GATEWAY, //
            BPMNTypes.PARALLEL_GATEWAY, //
            BPMNTypes.EVENTBASED_GATEWAY, //
            BPMNTypes.COMPLEX_GATEWAY, //
            BPMNTypes.INCLUSIVE_GATEWAY, //

            BPMNTypes.START_EVENT, //
            BPMNTypes.END_EVENT, //
            BPMNTypes.CATCH_EVENT, //
            BPMNTypes.THROW_EVENT, //
            BPMNTypes.BOUNDARY_EVENT //
    );

    /**
     * Returns true if the given element is a FlowElement, which are all
     * BPMNElementNodes from the type Event, Gateway or Activity.
     * SequenceFlows are not included as this is a Edge type!
     */
    public static boolean isFlowElementNode(BPMNElement element) {
        if (element instanceof BPMNElementNode) {
            return BPMN_FLOWELEMENT_NODES.contains(((BPMNElementNode) element).getType());
        }
        return false;
    }

    /**
     * Returns true if the given element is a DataObject
     */
    public static boolean isDataObjectNode(BPMNElement element) {
        if (element instanceof DataObject) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given element is a DataStore
     */
    public static boolean isDataStoreNode(BPMNElement element) {
        if (element instanceof DataStoreReference) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given element is a TextAnnotation
     */
    public static boolean isTextAnnotationNode(BPMNElement element) {
        if (element instanceof TextAnnotation) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given element is a Gateway
     */
    public static boolean isGateway(BPMNElement element) {
        if (element instanceof BPMNElementNode) {
            return BPMN_GATEWAYS.contains(((BPMNElementNode) element).getType());
        }
        return false;
    }

    /**
     * Returns true if the given element is a Task
     */
    public static boolean isTask(BPMNElement element) {
        if (element instanceof BPMNElementNode) {
            return BPMN_TASKS.contains(((BPMNElementNode) element).getType());
        }
        return false;
    }

    /**
     * Returns true if the given element is a SequenceFlow
     */
    public static boolean isSequenceFlow(BPMNElement element) {
        if (element instanceof SequenceFlow) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given element is a SequenceFlow
     */
    public static boolean isEdge(BPMNElement element) {
        if (element instanceof BPMNElementEdge) {
            return true;
        }
        return false;
    }

    public final static List<String> BPMN_NODE_ELEMENTS = Arrays.asList(//
            BPMNTypes.TASK, //
            BPMNTypes.MANUAL_TASK, //
            BPMNTypes.USER_TASK, //
            BPMNTypes.SCRIPT_TASK, //
            BPMNTypes.BUSINESSRULE_TASK, //
            BPMNTypes.SERVICE_TASK, //
            BPMNTypes.SEND_TASK, //
            BPMNTypes.RECEIVE_TASK, //
            BPMNTypes.SUB_PROCESS, //

            BPMNTypes.EXCLUSIVE_GATEWAY, //
            BPMNTypes.PARALLEL_GATEWAY, //
            BPMNTypes.EVENTBASED_GATEWAY, //
            BPMNTypes.COMPLEX_GATEWAY, //
            BPMNTypes.INCLUSIVE_GATEWAY, //

            BPMNTypes.START_EVENT, //
            BPMNTypes.END_EVENT, //
            BPMNTypes.CATCH_EVENT, //
            BPMNTypes.THROW_EVENT, //
            BPMNTypes.BOUNDARY_EVENT, //

            BPMNTypes.DATAOBJECT, //
            BPMNTypes.DATASTORE, //
            BPMNTypes.TEXTANNOTATION, //
            BPMNTypes.MESSAGE, //

            BPMNTypes.POOL);

    public final static List<String> BPMN_EDGE_ELEMENTS = Arrays.asList(new String[] { //
            BPMNTypes.SEQUENCE_FLOW, //
            BPMNTypes.MESSAGE_FLOW, //
            BPMNTypes.ASSOCIATION //
    });

    public final static List<String> BPMN_EVENT_DEFINITIONS = Arrays.asList(new String[] { //
            BPMNTypes.EVENT_DEFINITION_CANCEL, //
            BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
            BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
            BPMNTypes.EVENT_DEFINITION_ERROR, //
            BPMNTypes.EVENT_DEFINITION_ESCALATION, //
            BPMNTypes.EVENT_DEFINITION_LINK, //
            BPMNTypes.EVENT_DEFINITION_MESSAGE, //
            BPMNTypes.EVENT_DEFINITION_SIGNAL, //
            BPMNTypes.EVENT_DEFINITION_TIMER, //
            BPMNTypes.EVENT_DEFINITION_TERMINATE, //
            BPMNTypes.EVENT_DEFINITION_COMPENSATION });

    public final static List<String> BPMN_GATEWAYS = Arrays.asList(new String[] { //
            BPMNTypes.GATEWAY, //
            BPMNTypes.EXCLUSIVE_GATEWAY, //
            BPMNTypes.INCLUSIVE_GATEWAY, //
            BPMNTypes.PARALLEL_GATEWAY, //
            BPMNTypes.EVENTBASED_GATEWAY, //
            BPMNTypes.COMPLEX_GATEWAY //
    });

}
