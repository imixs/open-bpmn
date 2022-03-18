# Imixs BPMN - GLSP Client

This module contains the Imixs BPMN GLSP client module and the Theia integration. 

## Build and Start

To build the client modules run

	$ yarn

After a sucessfull build you can start the theia integration with 

	$ yarn start

And launch the modeler from your browser:

	http://localhost:3000/

## Cleanup Workspace

Node.js creates during the build phase an internal cache including all necessary modules. If you whant to start form screath with a clean workspace you can run the following scripts.

To clean the node_moduels and lib directories run:

	$ ./wipe-quick.sh
	
For a full wipe includind the yarn.lock file run:

	$ ./wipe-full.sh

After a wipe you can rebuild the project with `yarn install`



## Dependencies

The open-bpmn-glsp client module depends on the following nodejs versions:

