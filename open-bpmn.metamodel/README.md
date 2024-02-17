<h1><img width="200" src="../doc/images/logo-openbpmn-no-margin.png" /> Meta Model</h1>

**Open BPMN** provides a Java Meta Model used to create, load and manipulate a BPMN model programmatically. The Meta Model includes factory and builder classes to easily operate on a BPMN model instance. The library is based on the `org.w3c.dom` XML API and operates directly on the dom Nodes which makes this Meta Model very flexible. The Open BPMN Meta Model allows you to handle any kind of BPMN model and also work with BPMN 2.0 extensions and add your own extensions.

## The BPMNModelFactory

With the utility class `BPMNModelFactory` you find methods to create a model from scratch or to load an existing one. The `BPMNModelFactory` returns a `BPMNModel` instance to be used to read, update or delete any kind of bpmn elements.

### Create a New Model

The following example shows how to create a empty BPMN model file:

```java
String exporter = "demo";
String version = "1.0.0";
String targetNameSpace = "http://org.openbpmn";
BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
model.save("src/test/resources/create-process_1.bpmn");
```

This file contains a default process with no elements. With the default process instance you can add BPMN flow elements as you can see in the next example:

```java
BPMNProcess processContext = model.openDefaultProcess();
processContext.addEvent("start_1","Start",EventType.START);
processContext.addEvent("end_1","End",EventType.END);
processContext.addTask("task_1","Task",TaskType.TASK);
model.save("src/test/resources/create-process_1.bpmn");
```

### Load an existing Model

To load an existing BPMN model  call the `read` method. Again you can open the default process with the method `openDefaultProcess` or you can
open a specific process within your model by its id:

```java
// load model
BPMNModel model = BPMNModelFactory.read("/process_1-empty-1.bpmn");
// open default process....
BPMNProcess defaultProcess = model.openProcess(null);
// add a second process
BPMNProcess secondProcess = model.buildProcess("P-000002","Example");
// store the model
model.save("src/test/resources/process_1-update-1.bpmn");
```

## Collaboration Model

If a BPMN model contains more than one process it is called a collaboration model. Such a model defines *Participants* where each holds one process.
You can easily extend a simple model to become a collaboration model by creating additional Participants:

```java
BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
BPMNProcess processContext = model.addParticipant("participant_1", "Sales Team");
model.save("src/test/resources/create-process_1.bpmn");
```

**Note:** Each participant contains it own process. You can open the participant process by calling 'openProcess()' or you can open the process by its ID. In addition the model also contains a default process which is also called the 'Public' process which is not contained in a Pool

## Positioning Elements

BPMN separates the `bpmn2:process` defintion from the `bpmndi:BPMNDiagram` defintion. This allows a workflow engine to operate only on the process, while a modeling tool can layout the model by operating on the BPMNDiagram defintion. This *separation of concerns* is an important and strong concept in BPMN2. 

Working with a graphical editor like **Open BPMN** allows you to move BPMN Elements within a diagram. In case of a BPMN Collaboration Model this means moving a BPMN Element from one Pool into another the process assignment of the BPMN element changes. The position of a BPMN element defines to which process the element belongs to. The Open BPMN Meta Model makes this process transparent by automatically updating the process assignment whenever you change the position of a BPMN element. This also applies to lanes within a pool.

```java
// create a model with 2 participants
Participant participantSales = model.addParticipant("Sales Team");
participantSales.setBounds(10, 10,700, 300);
BPMNProcess salesProcess = participantSales.openProcess();

Participant participantMarketing = model.addParticipant("Marketing Team");
participantMarketing.setBounds(10, 400,700, 300);
BPMNProcess marketingProcess = participantMarketing.openProcess();

// add a new task
Activity task = defaultProcess.addTask("task_1", "Task", BPMNTypes.TASK);

task.setPosition(50, 50);
// The task is now part of the sales process
assertTrue(salesProcess == task.getBpmnProcess());

// change position....
task.setPosition(50, 450);
// The task is now be part of the marketing process
assertTrue(marketingProcess == task.getBpmnProcess());

```

## BPMN Extensions

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. This extension mechanism can be used by modeling tools and workflow engines. The Open-BPMN meta model provides convenience methods to operate on the BPMN 2.0 extension.

To access the `bpmn2:extensionElements` you can call the  the method `findExtensionsElement`. This method expects the root BPMN element containing the extensions. In the following example we access the extensions for a bpmn task element 'myTask':


```java
  Element extensionElement = myModel.findBPMN2Extensions(myTask);

```

To access an extension element directly you can call the method `findExtensionsElement`. This method expects the root BPMN element and a namespace and extension name. The following example returns the Open-BPMN auto-align extension element from the `bpmn2: defintions` : 

```java
 Element extensionElement = myModel.findExtensionElement(myModel.getDefinitions(), BPMNModelFactory.OPEN_BPMN_NAMESPACE, "auto-align");

```
Here you can see the corresponding BPMN file: 

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<bpmn2:definitions ...>
  <bpmn2:extensionElements>
    <open-bpmn:auto-align>true</open-bpmn:auto-align>
  </bpmn2:extensionElements>
  ....
</bpmn2:definitions>
```

**Note:** This method automatically creates the corresponding extension element if it is not yet part of the model. 


