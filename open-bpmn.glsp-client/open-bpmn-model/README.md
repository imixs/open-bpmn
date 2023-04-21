# Open-BPMN - Model

The Open-BPMN-Model is a Node.js library and part of the [Open-BPMN](https://www.open-bpmn.org/) project. The module `@open-bpmn/open-bpmn-model` provides a library to map server side model objects to the GLSP Client library. The module also exports a set of helper methods to categorize model elements and navigate through the GLSP model index.

The module can be used to extend the GLSP Client part of Open-BPMN. 


## Build and Dependencies

To build the client module run

	$ yarn

The module open-bpmn-model depends only on the GLSP library `@eclipse-glsp/client`. To include this module just add the required objects and methods into a import declaration:

	import { Icon,
	         isContainerNode,
	         isBPMNLabelNode,
	         isEventNode,
	         isGatewayNode,
	         isTaskNode,
	         isBoundaryEvent
	       } from '@open-bpmn/open-bpmn-model';
       

## Objects & Interfaces

The Open-BPMN module `@open-bpmn/open-bpmn-model` defines the following interfaces and object classes:

**BPMNFlowElement** 

And interface implemented by all BPMN element nodes

**TaskNode**

The model object `TaskNode` defines a BPMN Task (Activity)


**TaskNode**

The model object `TaskNode` defines a BPMN Task (Activity)


**EventNode**

The model object `EventNode` defines a BPMN Event. This includes all types of Events, e.g. Start Events, Stop Events, Catch Events, Boundary Events,... . A Event can be combind with a Event Defintion to visualize the sub-type of an event (e.g. Message Event, Signal Event,...)

**GatewayNode**

The model object `GatewayNode` defines a BPMN Gateway used for all types of BPMN Gateways. A Gateway contains a Icon (Symbol) to visualize the type. 

**MessageNode**

The model object `MessageNode` defines a BPMN Message

**TextAnnotation**

The model object `TextAnnotation` defines a BPMN TextAnnotation


**DataObjectNode**

The model object `DataObjectNode` defines a BPMN DataObject

**PoolNode**

The model object `PoolNode` defines a BPMN Pool (Participant) and can include BPMN Lanes (LaneNode)

**LaneNode**

The model object `LaneNode` defines a BPMN LaneNode

**LabelNode**

The model object `LabelNode` defines a GLSP specific node to render a BPMN Label as part of a Gateway, Event, or Data Element. A LabelNode can be placed on the diagram plane but is assigned to a BPMN Element.


**Icon**

The model object `Icon` defines a GLSP specific node to render a Icon used for compartments of Task, Events and Gateways. 




