[![Java CI with Maven](https://github.com/imixs/open-bpmn/actions/workflows/maven.yml/badge.svg)](https://github.com/imixs/open-bpmn/actions/workflows/maven.yml)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/1694/badge)](https://bestpractices.coreinfrastructure.org/projects/1694)
[![Join a discussion](https://img.shields.io/badge/discuss-on%20github-4CB697)](https://github.com/imixs/open-bpmn/discussions)
[![License](https://img.shields.io/badge/license-EPL-blue.svg)](https://github.com/imixs/open-bpmn/blob/master/LICENSE)
<img width="200" src="./doc/images/logo-openbpmn-no-margin.png" />

**Open BPMN** is a free and open modeling platform to create and maintain business models based on the BPMN 2.0 standard. **Open BPMN** can be used by business analysts to design a top level business process, as also by architects and developers to model the technical details of complex processing logic.
Further more, **Open BPMN** provides an extension mechanism to adapt the modeling platform to the individual requirements in any business process project.

The goal of this project is to provide an open and extensible graphical BPMN modelling framework for an agile and innovative community around the BPMN standard.

<center><img  width="800" src="./doc/images/imixs-bpmn-001.png" /></center>
<h2 align="center"><a href="https://open-bpmn.imixs.org" target="_blank">Start the Online Demo</a></h2>

**Open BPMN** is based on the [Eclipse Graphical Language Server Platform (GLSP)](https://www.eclipse.org/glsp/) - an open-source framework for building diagram editors based on modern web technologies. This architecture allows to run **Open BPMN** on various IDEs and platforms like Visual Studio Code, Eclipse IDE, Eclipse Theia, as also as a standalone Web Application.

## What is BPMN 2.0?

The [Business Process Model and Notation](https://www.omg.org/spec/BPMN/) (BPMN 2.0) is an open standard to describe business processes that can be visualized in diagram editors and executed by process engines compliant with the BPMN 2.0 standard. This makes BPMN an interoperable, interchangeable and open standard in the field of business process management.

BPMN was intended for users at all levels, from the business analysts who create the initial design, to the developers who implement the technical details, and finally, to the business users responsible for managing and monitoring the processes.

As a XML language proposed by the [Object Management Group](https://www.omg.org/spec/BPMN/) (OMG), BPMN
is not only a notation for describing business workflows but also higher-level collaborations between business partners and the choreography of information flows between applications, microservices and cloud platforms.

## Extensibility

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. It can be used by modeling tools to add non-standard elements or Artifacts to satisfy a specific need, such as the unique requirements of a vertical domain, and still have a valid BPMN Core.

One goal of _Open BPMN_ is to not only provide a graphical modeling tool, but also to allow developers and independent projects to easily customize the behavior and appearance of the editor for specific BPM workflow engines that use this BPMN 2.0 extensibility mechanism.

Open Source Workflow Engines like [Imixs-Workflow](https://www.imixs.org) integrate Open BPMN into there tooling platforms and extend the core features of BPMN 2.0 with platform specific functionality.

[Learn more about Open BPMN Extensions.](https://www.open-bpmn.org/glsp-server/BPMN_EXTENSIONS.html)

## Architecture

Open BPMN is based on the [Eclipse Graphical Language Server Platform (GLSP)](https://www.eclipse.org/glsp/) and provides the following building blocks:

- [open-bpmn.metamodel](./open-bpmn.metamodel/README.md) - an open BPMN 2.0 metamodel
- [open-bpmn.glsp-server](./open-bpmn.glsp-server/README.md) - the GLSP Server implementation
- [open-bpmn.glsp-client](./open-bpmn.glsp-client/README.md) - the GLSP Client components and Theia integration

### Open-BPMN - BPMN 2.0 Metamodel

OpenBPMN provides a BPMN 2.0 Metamodel based on pure java. This library can be used to generate a BPMN model programmatically as also import or export a model form any .bpmn file. So in case you want to implement you own BPMN workflow engine the OpenBPMN Metamodel is the perfect library to work with BPMN 2.0 files. THe OpenBPMN Metamodel is based o the `org.w3c.dom` XML API and includes a set of junit test classes which may be helpful to implement you own business logic.

- [OpenBPMN Metamodel](./open-bpmn.metamodel/README.md)

### Open-BPMN GLSP-Server

The [open-bpmn.glsp-server](./open-bpmn.glsp-server/README.md) provides the GLSP Server part. The server part is responsible to load and store the diagram from a .bpmn file.

### Open-BPMN GLSP-Client

The [open-bpmn.glsp-client](./open-bpmn.glsp-client/README.md) is the GLSP Client part of Open BPMN providing the graphical modelling tool.

# Tutorials

Open BPMN is based on [Eclipse GLSP](https://www.eclipse.org/glsp/) and adapts a lot concepts to provide you a flexible and customizable modelling platform.
You can find many information on the  [Open-BPMN Web site](https://www.open-bpmn.org) including tutorials regarding the GLSP framework and how to customize and adapt Open-BPMN.

- [Build your Own BPMN Extension](https://www.open-bpmn.org/glsp-server/BPMN_EXTENSIONS.html)
- [The BPMN Properties Panel](https://www.open-bpmn.org/glsp-client/BPMN_PROPERTIES.html)
- [Extending the Tool Palette](https://www.open-bpmn.org/glsp-client/TOOL_PALETTE.html)
- [Build your Own EMF Model](https://www.open-bpmn.org/architecture/BPMN_EMF.html)

# Build and Run

To build the complete project run

    $ ./build.sh

This will build the server module with maven and the client modules with yarn. The script finally automatically starts the application.

The Application can be started from a Web Browser

    http://localhost:3000/

**Note:** When you have installed Open-BPMN as an Extension in your VS-Code platform, you need to disable the extension before you start development!

## Locally build for Development

During development you can run the frontend and backend in separate tasks. This gives you more control over the CLient and the Backend Component.

To build & start the GLSP Server only, run:

    $ ./build.sh -b

To build & start the GLSP Client only, run:

    $ ./build.sh -f

To start the GLSP Client without building, run:

    $ ./build.sh -s

For a full clean & reinstall of the GLSP Client (after upgrades), run:

    $ ./build.sh -c -i

You will find more details in the [Client Section](./open-bpmn.glsp-client/README.md) and the [Server Section](./open-bpmn.glsp-server/README.md).

### NodeJS

For development the JavaScript tools [Node.js](https://nodejs.org/en/about) and [yarn](https://yarnpkg.com/) need to be installed in the correct version. 
Using the Node Version Manager (NVM) you can easily manage multiple versions of Node.js on a single machine. It’s an essential tool for development with Node.js as it allows you to switch between different versions of Node.js without having to go through the hassle of installing or uninstalling Node.js manually each time.
To use nvm in Debian 12 run:

    $ sudo apt install build-essential libssl-dev
    $ curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/master/install.sh | bash 

Now you can install a specific version of Node.js using the nvm install command, followed by the version number. For example, to install Node.js 18, you would type:

    $ nvm install 18 

You can list all available version with:

    $ nvm ls-remote

In the current code base we are using 18.17.1 for development. Before version 1.2.x we used 14.21.3. 

