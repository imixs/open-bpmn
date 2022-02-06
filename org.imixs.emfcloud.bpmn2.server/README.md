# Server



## Building the Workflow Diagram example server

To build the server run 

	$ mvn clean verify -Pm2 -Pfatjar

This will generate a sever jar including your EMF model and all necessary components. This is called a 'fatjar'. 

From the /target/ folder you can now start the server by executing the following commands (whereas X.X.X is the current version):

	$ cd target
	$ java -jar org.imixs.emfcloud.bpmn2.server-0.0.1-SNAPSHOT-glsp.jar org.imixs.emfcloud.bpmn2.server.launch.WorkflowServerLauncher

For the HTML5 client you need to run the server on port 8081

	$ java -jar org.imixs.emfcloud.bpmn2.server-0.0.1-SNAPSHOT-glsp.jar org.imixs.emfcloud.bpmn2.server.launch.WorkflowServerLauncher --port=8081 --websocket

To start the example server from within your IDE, run the main method of the class `WorkflowServerLauncher.java` in the module `org.eclipse.glsp.example.workflow.launch` 

Once the server is running, choose a diagram client integration (such as Eclipse Theia, VSCode, Eclipse, or Standalone).



