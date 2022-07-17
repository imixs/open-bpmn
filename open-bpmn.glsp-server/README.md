# Imixs BPMN - GLSP Server

This is the Imixs BPMN GLSP Server module used by the Imixs BPMN Client modules. 

## Building the Open BPMN server

To build the server part of Open BPMN run the maven command:

	$ mvn clean verify -Pm2 -Pfatjar

This will generate a sever jar including your EMF model and all necessary components. This is called a 'fatjar'. 

From the /target/ folder you can now start the server by executing the following commands (whereas X.X.X is the current version):

	$ cd target
	$ java -jar open-bpmn.server-0.3.0-SNAPSHOT-glsp.jar org.imixs.bpmn.glsp.server.launch.BPMN2ServerLauncher

## Build & Run

During development you can use the 'build' script to build & run the backend in a separat task. This gives you more control as the server can be rebuild and started independent from your Client components. 

To build and start the GLSP Server only, run:

	$ ./build.sh -b

	
	
### Debug - Eclipse

You can also launch the server from Eclipse IDE in the Debug mode. 	

Just start the `org.openbpmn.glsp.BPMNServerLauncher` application in Debug mode (Debug As - Java Application"

<img src="../doc/images/eclipse-debug.png" />