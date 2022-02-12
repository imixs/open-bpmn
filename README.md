# Imixs-BPMN (GLSP)

BPMN Modeler based on Eclipse Graphical Language Server Platform

 - [imixs-bpmn.glsp-server](./imixs-bpmn.glsp-server/README.md) - contains the GLSP sever implementation
 - [imixs-bpmn.glsp-client](./imixs-bpmn.glsp-client/README.md) - contains the GLSP Client components and Theia integration


## Build and Run

To build the complete project run 

	$ ./build.sh

This will build the server module with maven and the client modules with yarn. The script finally automatically starts the application.

The Application can be started from a Web Browser

	http://localhost:3000/

	
<img src="./doc/imixs-bpmn-001.png" />