# Open-BPMN - App

Open-BPMN-App is a Node.js library and part of the Open-BPMN client library. The module @open-bpmn/open-bpmn-app provides the browser app to use Open-BPMN in a browser.

## Build and Dependencies

To build the client module run

	$ yarn

Please follow the GLSP Theia Integration guide for more details about how to integrate a GLSP Client into the Eclipse Theia platform.


### peerDependencies

The project does no longer need peer dependencies. Overall this makes dependency resolution more stable and should eliminate the need for manual resolution blocks.
However, this also means you have to manually declare the version of sprotty-theia that you want to use in your browser-app e.g:

	  "dependencies": {
	    "@open-bpmn/open-bpmn-theia": "0.8.0",  
	    "@theia/core": "1.33.0",
	    "@theia/editor": "1.33.0",
	    "@theia/filesystem": "1.33.0",
	    "@theia/markers": "1.33.0",
	    "@theia/messages": "1.33.0",
	    "@theia/monaco": "1.33.0",
	    "@theia/navigator": "1.33.0",
	    "@theia/preferences": "1.33.0",
	    "@theia/process": "1.33.0",
	    "@theia/terminal": "1.33.0",
	    "@theia/workspace": "1.33.0",
	    "sprotty-theia": "0.13.0-next.9cbedec"
	  },
  
  