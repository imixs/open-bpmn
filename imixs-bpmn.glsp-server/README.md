# Imixs BPMN - GLSP Server

This is the Imixs BPMN GLSP Server module used by the Imixs BPMN Client modules. 

## Building the Workflow Diagram example server

To build the server run 

	$ mvn clean verify -Pm2 -Pfatjar

This will generate a sever jar including your EMF model and all necessary components. This is called a 'fatjar'. 

From the /target/ folder you can now start the server by executing the following commands (whereas X.X.X is the current version):

	$ cd target
	$ java -jar org.imixs.bpmn.glsp.server-0.0.1-SNAPSHOT-glsp.jar org.imixs.bpmn.glsp.server.launch.BPMN2ServerLauncher

