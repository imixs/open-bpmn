# Open BPMN 2.0 - Metamodel


OpenBPMN provides a Java Metamodel which can be used to create, load and manipulate a BPMN model programmatically. The Metamodel provides factory and model classes to work with an BPMN model instance. The library is based on the `org.w3c.dom` XML API and operates directly on the dom Nodes which makes the Metamodel very flexible. This concept allows you to handle any kind of BPMN model and also work with BPMN 2.0 extensions or add your own extensions. 

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

This file contains no processes and no elements. You can easily extend the model and create a process instance with the ID 'process_1'

```java
	BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
	model.addProcess("process_1");
	model.save("src/test/resources/create-process_1.bpmn");
```


With a process instance you can add elements to the process:


```java
	BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
	model.addProcess("process_1");
	BPMNProcess processContext = model.openContext("process_1");
	processContext.addEvent("start_1","Start",EventType.START);
	processContext.addEvent("end_1","End",EventType.END);
	processContext.addTask("task_1","Task",TaskType.TASK);
	model.save("src/test/resources/create-process_1.bpmn");
```

        




### Load a existing Model

To load a model instance and add a new Process into the model you just need this:

```java
	// load model
	BPMNModel model = BPMNModelFactory.read("/process_1-empty-1.bpmn");
	// open default process....
	BPMNProcess process = model.openContext(null);
	// add a second process
	model.addProcess("P-000002");
	// store the model
	model.save("src/test/resources/process_1-update-1.bpmn");
```

