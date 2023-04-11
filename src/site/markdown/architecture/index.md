# Open BPMN - Architecture

Open BPMN is based on the Eclipse Graphical Language Server Platform (GLSP) and provides the following building blocks:

* [open-bpmn.metamodel](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.metamodel) - an open BPMN 2.0 metamodel
* [open-bpmn.glsp-server](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-server) - the GLSP Server implementation
* [open-bpmn.glsp-client](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-client) - the GLSP Client components and Theia integration

## Open-BPMN - BPMN 2.0 Metamodel

OpenBPMN provides a BPMN 2.0 Metamodel based on pure java. This library can be used to generate a BPMN model programmatically as also import or export a model form any .bpmn file. So in case you want to implement you own BPMN workflow engine the OpenBPMN Metamodel is the perfect library to work with BPMN 2.0 files. THe OpenBPMN Metamodel is based o the org.w3c.dom XML API and includes a set of junit test classes which may be helpful to implement you own business logic.

* [OpenBPMN Metamodel](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.metamodel)

## Open-BPMN GLSP-Server

The open-bpmn.glsp-server provides the GLSP Server part. The server part is responsible to load and store the diagram from a .bpmn file.
Open-BPMN GLSP-Client

The open-bpmn.glsp-client is the GLSP Client part of Open BPMN providing the graphical modeling tool.

* [OpenBPMN Server](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-server)

## Open-BPMN Client

The client part provides a the webview and is build with NodeJS. The project is based on [Eclipse GLSP](https://www.eclipse.org/glsp/) and [JSONForms](https://github.com/eclipsesource/jsonforms)

* [OpenBPMN Client](https://github.com/imixs/open-bpmn/tree/master/open-bpmn.glsp-client)
