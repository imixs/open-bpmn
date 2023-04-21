# Open-BPMN - GLSP

Open-BPMN-GLSP is a Node.js library and part of the [Open-BPMN](https://www.open-bpmn.org/) project. The module `@open-bpmn/open-bpmn-glsp` integrates the Open-BPMN model and ui components into the GLSP Diagram and is the main library to setup the Open BPMN client part. You can adapt or extend this module if you plan to extend Open-BPMN on the client side.

## Build and Dependencies

To build the client module run

	$ yarn

The module open-bpmn-glsp provides a di.config file and defines all the bindings needed for the client part. See the source code of [di.config.ts](./src/di.config.ts) for more insights.
