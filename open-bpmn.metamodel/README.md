<h1><img width="200" src="../doc/images/logo-test.png" /> Metamodel</h1>

# Open BPMN 2.0 - Metamodel

**Open BPMN** provides a Java Metamodel which can be used to create, load and manipulate a BPMN model programmatically. The Metamodel provides factory and model classes to work with an BPMN model instance. The library is based on the `org.w3c.dom` XML API and operates directly on the dom Nodes which makes the Metamodel very flexible. This concept allows you to handle any kind of BPMN model and also work with BPMN 2.0 extensions or add your own extensions. 

## The BPMNModelFactory

The class `BPMNModelFactory` provides methods to create an empty model or to load an existing one. The `BPMNModelFactory` returns a BPMNModel instance which can be used to read, update or delete elements.

### Create a Model

The following example shows how to create a empty BPMN model file:

```java
	String exporter = "demo";
	String version = "1.0.0";
	String targetNameSpace = "http://org.openbpmn";
	BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
	model.save("src/test/resources/create-process_1.bpmn");
```

This file contains a default process with no elements. With the default process instance you can add BPMN flow elements:


```java
	BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
	BPMNProcess processContext = model.openDefaultProcess();
	processContext.addEvent("start_1","Start",EventType.START);
	processContext.addEvent("end_1","End",EventType.END);
	processContext.addTask("task_1","Task",TaskType.TASK);
	model.save("src/test/resources/create-process_1.bpmn");
```



### Load a existing Model

To load a model instance call the 'load' method. Again you can open the default process with  "openDefaultProcess" or you can 
open a proces by its id:

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

If a BPMN model contains more than one process it is called a collaboration model. This model defines Participants where each holds one process. 
You can easily extend the simple model to a collaboration model and create additional Participants:

```java
	BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
	BPMNProcess processContext = model.addParticipant("participant_1", "Sales Team");
	model.save("src/test/resources/create-process_1.bpmn");
```
**Note:** Each participant contains it own process. You can open the participant process by calling 'openProcess()' or you can open the process by its ID. In addition the model also contains a default process which is also called the 'Public' process which is not contianed in a Pool
        




