# Imixs BPMN - GLSP Client

This module contains the Imixs BPMN GLSP client module and the Theia integration. 

## Build the Client Module with yarn

To build the client modules you need to install `npm`, `nodejs` and `yarn`. Next run:

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
	$ ./build.sh -r

## JsonForms

We use the [JsonForms component](https://github.com/eclipsesource/jsonforms) to render the property panel of a selected BPMN element. 
JsonForms provides different renderes to display form elements like Input fields and buttons in a common layout. In OpenBPMN we use the so called 'VanillaRenderer' to display the property form elements.

If you want to contribute to this project or implement a new renderer components it is recommended to first create a fork of the JsonForms project so you can later start pull a request to contribute your code.

For a first time setup:

* Install [node.js](https://nodejs.org/) (only Node 14 and npm 6 is currently supported)
* Fork this repository on Github 
* Clone this repository

Now you can start building the project and run the examples. First of all you need to install the base dependencies, then run lerna and then build all packages. After that you can execute the example application.

So starting from the root directory:

	# reset and clean everything
	git clean -dfx

	# build JSON Forms
	npm ci
	npm run init
	npm run build

	# e.g. Start React Vanilla example application
	cd packages/vanilla && npm run dev 

Run the example from your web browser: http://localhost:8080/#array-with-detail

<img src="../doc/images/jsonforms/example-01.png" />
