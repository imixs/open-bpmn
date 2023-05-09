# Overview

**Open BPMN** is a free and open modeling platform to create and maintain business models based on the BPMN 2.0 standard. Open BPMN can be used by business analysts to design a top level business process, as also by architects and developers to model the technical details of complex processing logic. Further more, Open BPMN provides an extension mechanism to adapt the modeling platform to the individual requirements in any business process project.

The goal of this project is to provide an open and extensible graphical BPMN modelling framework for an agile and innovative community around the BPMN standard.

Open BPMN is based on the Eclipse Graphical Language Server Platform (GLSP) - an open-source framework for building diagram editors based on modern web technologies. This architecture allows to run Open BPMN on various IDEs and platforms like [Visual Studio Code](https://code.visualstudio.com/), [Eclipse IDE](https://eclipse.org/), [Eclipse Theia](https://theia-ide.org/), as also as a standalone Web Application.

See the [Install Guide](install.html) for details how to install Open-BPMN.

## What is BPMN 2.0?

The Business Process Model and Notation (BPMN 2.0) is an open standard to describe business processes that can be visualized in diagram editors and executed by process engines compliant with the BPMN 2.0 standard. This makes BPMN an interoperable, interchangeable and open standard in the field of business process management.

BPMN was intended for users at all levels, from the business analysts who create the initial design, to the developers who implement the technical details, and finally, to the business users responsible for managing and monitoring the processes.

As a XML language proposed by the Object Management Group (OMG), BPMN is not only a notation for describing business workflows but also higher-level collaborations between business partners and the choreography of information flows between applications, microservices and cloud platforms.

## Extensibility

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. It can be used by modeling tools to add non-standard elements or Artifacts to satisfy a specific need, such as the unique requirements of a vertical domain, and still have a valid BPMN Core.

One goal of Open BPMN is to not only provide a graphical modeling tool, but also to allow developers and independent projects to easily customize the behavior and appearance of the editor for specific BPM workflow engines that use this BPMN 2.0 extensibility mechanism.

Open Source Workflow Engines like Imixs-Workflow integrate Open BPMN into there tooling platforms and extend the core features of BPMN 2.0 with platform specific functionality.

Learn more about Open BPMN Extensions.

## Architecture

**Open BPMN** is based on the Eclipse Graphical Language Server Platform (GLSP) and provides the following building blocks:

- [open-bpmn.metamodel](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.metamodel) - an open BPMN 2.0 metamodel
- [open-bpmn.glsp-server](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-server) - the GLSP Server implementation
- [open-bpmn.glsp-client](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-client) - the GLSP Client components and Theia integration

### Open-BPMN - BPMN 2.0 Metamodel

**Open BPMN** provides a BPMN 2.0 Metamodel based on pure java. This library can be used to generate a BPMN model programmatically as also import or export a model form any .bpmn file. So in case you want to implement you own BPMN workflow engine the OpenBPMN Metamodel is the perfect library to work with BPMN 2.0 files. THe OpenBPMN Metamodel is based o the org.w3c.dom XML API and includes a set of junit test classes which may be helpful to implement you own business logic.

- [Open BPMN Metamodel](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.metamodel)

### Open-BPMN GLSP-Server

The open-bpmn.glsp-server provides the GLSP Server part. The server part is responsible to load and store the diagram from a .bpmn file.
Open-BPMN GLSP-Client

The open-bpmn.glsp-client is the GLSP Client part of Open BPMN providing the graphical modeling tool.

## Tutorials

Open BPMN is based on Eclipse GLSP and adapts the different concepts in various ways. We provide a short collection of tutorials regarding the GLSP framework and how to customize and adapt Open-BPMN. in Open BPMN.

- [The Module System](architecture/MODULE_SYSTEM.html)
- [Build your Own BPMN Extension](glsp-server/BPMN_EXTENSIONS.html)
- [BPMN Properties](glsp-client/BPMN_PROPERTIES.html)
- [Tool Palette](glsp-client/TOOL_PALETTE.html)
- [Custom Element Views](glsp-client/CUSTOM_VIEWS.html)
- [Context Menus](glsp-client/CONTEXT_MENUS.html)
- [BPMN Router](glsp-client/BPMN_ROUTER.html)
- [Build your Own EMF Model](architecture/BPMN_EMF.html)
- [Anchors and Ports](glsp-client/SPROTTY_ANCHORS_AND_PORTS.html)
