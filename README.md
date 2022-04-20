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

 - [open-bpmn.glsp-server](./open-bpmn.glsp-server/README.md) - contains the GLSP sever implementation
 - [open-bpmn.glsp-client](./open-bpmn.glsp-client/README.md) - contains the GLSP Client components and Theia integration


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

# BPMN 2.0

The following section gives a short overview of the BPMN specification. Imixs BPMN is based on version 2.0.2. You can find the full specification [here](https://www.omg.org/spec/BPMN/2.0.2/PDF).

## Process 

A Process describes a sequence or flow of Activities in an organization with the objective of carrying out work. In BPMN a Process is depicted as a graph of Flow Elements, which are a set of Activities, Events, Gateways, and Sequence Flows that define finite execution semantics (see Figure 10.1). Processes can be defined at any level from enterprise-wide Processes to Processes performed by a single person. Low-level Processes can be grouped together to achieve a common business goal.

The Process package contains classes that are used for modeling the flow of Activities, Events, and Gateways, and how they are sequenced within a Process. When a Process is defined it is contained within Definitions

## Activities

An Activity is work that is performed within a Business Process. An Activity can be atomic or non-atomic (compound). The types of Activities that are a part of a Process are: Task, Sub-Process, and Call Activity, which allows the inclusion of re-usable Tasks and Processes in the diagram. However, a Process is not a specific graphical object. Instead, it is a set of graphical objects. The following sub clauses will focus on the graphical objects Sub-Process and Task.

**Activities** represent points in a **Process** flow where work is performed. They are the executable elements of a BPMN Process.
The Activity class is an abstract element, sub-classing from FlowElement


### Tasks (page 154)

A Task is an atomic Activity within a Process flow. A Task is used when the work in the Process cannot be broken down to a finer level of detail. Generally, an end-us er and/or applications are used to perform the Task when it is executed.
A Task is visualized as a shape which is a rectangle that has rounded corners 

 - A Task is a rounded corner rectangle that MUST be drawn with a single thin line.
 - A Task MAY have labels (e.g., its name and/or other attributes) placed inside the shape, or above or below the shape, in any direction or location, depending on the preference of the modeler or modeling tool . 

####  Types of Tasks

There are different types of Tasks identified within BPMN to separate the types of inherent behavior that Tasks might represent. The list of Task types MAY be extended along with any corresponding indicators. A Task which is not further specified is called Abstract Task. The notation of the Abstract
Task is shown in Figure 10.8.
Service Task
A Service Task is a Task that uses some sort of service, which coul d be a Web service or an automated application.
A Service Task object shares the same shape as the Task, which is a rectangle that has rounded corners. However, there
is a graphical marker in the upper left corner of the shape that indicates that the Task is a Service Task (see Figure
10.11).

A **Service Task** is a rounded corner rectangle that MUST be drawn with a single thin line and includes a marker that
distinguishes the shape from other Task types (as shown in Figure 10.1

A **Send Task** is a simple Task that is designed to send a Message to an external Participant (relative to the Process). Once the Message has been sent, the Task is completed. The actual Participant which the Message is sent can be identified by connecting he Send Task to a Participant using a Message Flows within the definitional Collaboration of the Process 

A **User Task**  is a rounded corner rectangle that MUST be drawn with a single thin line and includes a human figure
marker that distinguishes the shape from other Task types

A **Script Task** is executed by a business process e ngine. The modeler or implementer defi nes a script in a language that
the engine can interpret. When the Task is ready to start, the engine will execute the script. When the script is completed,
the Task will also be completed


# Development

Open BPMN is based on [Eclipse GLSP](https://www.eclipse.org/glsp/) and adapts the different concepts in various ways. The following sections provide details about the development with Eclipse GLSP and the solutions used in Open BPMN.

 - [Build your Own EMF Model](./doc/BPMN_EMF.md)
 - [Tool Palette](./doc/TOOL_PALETTE.md)
 - [Custom Element Views](./doc/CUSTOM_VIEWS.md)
 - [Ports](./doc/PORTS.md)
 
 
 
# NodeJS

We use nodejs on Linux Debian during development. To manage version of node js see: https://phoenixnap.com/kb/update-node-js-version

For development with Eclipse Theia the expected version is ">=10.11.0 <13". For that reason we tested with following version 12.22.10.

	
	 

	