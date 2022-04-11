# Imixs BPMN - GLSP Client

This module contains the Imixs BPMN GLSP client module and the Theia integration. 

## Build the Client Module with yarn

To build the client modules run

	$ yarn

After a sucessfull build you can start the theia integration with 

	$ yarn start

And launch the modeler from your browser:

	http://localhost:3000/



## The build script for Development

During development you can run the frontend and backend in separate tasks. We provide a build script that gives you more control over the CLient and the Backend Component. 

To start the GLSP Server only, run:

	$ ./build.sh -b

To start the GLSP Client only, run:

	$ ./build.sh -f

With the option `-ff` you can force a full rebuild of the client components. This option will remove cached nodejs modules. 

	$ ./build.sh -ff
	
	
## Cleanup Workspace

If you only want to clean up the workspace, you can run the script:

	$ ./wipe-full.sh


## Dependencies

The open-bpmn-glsp client module depends on the following nodejs versions:


TODO

......
https://stackoverflow.com/questions/56838735/how-to-import-a-common-module-in-cra-using-yarn-workspaces