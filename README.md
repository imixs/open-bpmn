# Open-BPMN 

BPMN Modeler based on Eclipse Graphical Language Server Platform

 - [open-bpmn.glsp-server](./open-bpmn.glsp-server/README.md) - contains the GLSP sever implementation
 - [open-bpmn.glsp-client](./open-bpmn.glsp-client/README.md) - contains the GLSP Client components and Theia integration


## Build and Run

To build the complete project run 

	$ ./build.sh

This will build the server module with maven and the client modules with yarn. The script finally automatically starts the application.

The Application can be started from a Web Browser

	http://localhost:3000/

	
<img src="./doc/imixs-bpmn-001.png" />


## Locally build backend/frontend

If you want only build the backend (and not the front-end), run:

	$ ./build.sh -b

If you just want to build the frontend, run:

	$ ./build.sh -f

With the option `-ff` you can force a full rebuild of the client components. This option will remove the yarn.lock file. 

	$ ./build.sh -ff

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

We use nodejs on Linux debian during develpment.

To manage version of node js see: https://phoenixnap.com/kb/update-node-js-version

For development with Eclipse Theia the expected version is ">=10.11.0 <13". For that reason we tested with following versions:

	12.22.10
	16.13.2
	
	 
 
# Debug

During development it will be more easy to start the GLSP Server and the Client in separate threads to see what is happening on the server. 

First start the GLSP Client without the server:

	$ ./start.sh	
	
Next start the glsp server manually with:

	$ cd open-bpmn.glsp-server
	$ ./launch.sh

The server is starting on Port 5007.


	