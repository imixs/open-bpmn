# Open-BPMN 

Open BPMN is a free and open platform for modeling BPMN 2.0 diagrams.  The primary goal of this project is to provide a graphical BPMN editing framework, which can be easily customized and extended for any BPMN 2.0 compliant execution engine. 

The [Business Process Model and Notation](https://www.omg.org/spec/BPMN/) was intended for users at all levels, from the business analysts who create the initial design, to the developers who implement the technical details, and finally, to the business users responsible for managing and monitoring the processes. BPMN is today the common standard for describing business processes.

BPMN 2.0 is an XML language proposed by the [Object Management Group](https://www.omg.org/spec/BPMN/) (OMG), as a notation for describing not only business workflows (a.k.a. "processes") but also higher-level collaborations between business partners and the choreography of information exchanged between these business partners. Due to the fact that BPMN 2.0 is a XML based formal description language, it can be interpreted and executed by various types of Workflow Engines. 

<img src="./doc/images/imixs-bpmn-001.png" />

## Extensibility 

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. It can be used by modeling tools to add non-standard elements or Artifacts to satisfy a specific need, such as the unique requirements of a vertical domain, and still have a valid BPMN Core.

One goal of *Open BPMN* is to not only provide a graphical modeling tool, but also to allow developers and independent projects to easily customize the behavior and appearance of the editor for specific BPM workflow engines that use this BPMN 2.0 extensibility mechanism.

Open Source Workflow Engines like [Imixs-Workflow](https://www.imixs.org) integrate Open BPMN into there tooling platforms and extend the core features of BPMN 2.0 with platform specific functionality. 

## Architecture

Open BPMN is based on the [Eclipse Graphical Language Server Platform (GLSP)](https://www.eclipse.org/glsp/), an extensible open-source framework for building custom diagram editors based on web technologies.

 - [open-bpmn.metamodel](./open-bpmn.metamodel/README.md) - provides the BPMN 2.0 metamodel
 - [open-bpmn.glsp-server](./open-bpmn.glsp-server/README.md) - contains the GLSP sever implementation
 - [open-bpmn.glsp-client](./open-bpmn.glsp-client/README.md) - contains the GLSP Client components and Theia integration


# The BPMN 2.0 Metamodel

OpenBPMN provides a Java Metamodel which can be used to generate a BPMN model programmatically as also import or export a model form any .bpmn file. So in case you want to implement you own BPMN workflow engine the OpenBPMN Metamodel is the perfect library to work with BPMN 2.0 files. THe OpenBPMN Metamodel is based on the [Eclipse Modeling Framework](https://www.eclipse.org/modeling/emf/) (EMF) and includes a set of junit test classes which may be helpful to implement you own business logic. 

 - [OpenBPMN Metamodel](./open-bpmn.metamodel/README.md)



# Build and Run

To build the complete project run 

	$ ./build.sh

This will build the server module with maven and the client modules with yarn. The script finally automatically starts the application.

The Application can be started from a Web Browser

	http://localhost:3000/

	



## Locally build for Development

During development you can run the frontend and backend in separate tasks. This gives you more control over the CLient and the Backend Component. 

To start the GLSP Server only, run:

	$ ./build.sh -b

To start the GLSP Client only, run:

	$ ./build.sh -f

With the option `-ff` you can force a full rebuild of the client components. This option will remove the yarn.lock file. 

	$ ./build.sh -ff
	
You will find more details in the [Client Section](./open-bpmn.glsp-client/README.md) and the [Server Section](./open-bpmn.glsp-server/README.md).

# Development

Open BPMN is based on [Eclipse GLSP](https://www.eclipse.org/glsp/) and adapts the different concepts in various ways. The following sections provide details about the development with Eclipse GLSP and the solutions used in Open BPMN.

 - [Build your Own EMF Model](./doc/BPMN_EMF.md)
 - [Tool Palette](./doc/TOOL_PALETTE.md)
 - [Custom Element Views](./doc/CUSTOM_VIEWS.md)
 - [Ports](./doc/PORTS.md)
 
 
 
# NodeJS

We use nodejs on Linux Debian during development. To manage version of node js see: https://phoenixnap.com/kb/update-node-js-version

For development with Eclipse Theia the expected version is ">=10.11.0 <13". For that reason we tested with following version 12.22.10.

	
	 

	