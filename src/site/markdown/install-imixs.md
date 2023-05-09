# Installation Guide For Imixs-Open-BPMN

**Open BPMN** supports an extension mechanism to provide additional features to the modelling platform. Running **Open BPMN** with the Imixs-Workflow Extension allows you to model BPMN files that can be executed the [Imixs-Workflow engine](https://www.imixs.org).

**Note:** If you install the Open-BPMN in Visual Studio, the imixs-workflow support is already included!

## Run With Docker

Imixs-Open-BPMN is provided as a separate Docker image. To run Imixs-Open-BPMN with docker you just need to replace the docker image name with `imixs/imixs-open-bpmn`. This will run Open-BPMN with the Imixs-Workflow extension:

    $ docker run -it --rm --name="open-bpmn" \
      -p 3000:3000 \
      imixs/imixs-open-bpmn

After starting the container the application is accessible from your Web Browser:

    http://localhost:3000

See the [general Docker install guide](install.html) for a more detailed description about how to run Open-BPMN with Docker.

# The Imixs-Workflow Extension

The Imixs-Workflow extension allows you to extend BPMN models with attributes to be executable by the [Imixs-Workflow engine](https://www.imixs.org). The extension can be applied to 'Tasks' and 'Catch Events'. The extension can be found in the Tool Palette in the section "Extensions"

<img src="./images/imixs-extension-01.png"  />

After you drag the extension into a 'Task' or 'Catch Event', the element will be marked with a border. The element will now additional properties in the property panel.

<img src="./images/imixs-extension-02.png"  />

The properties depend on the element type. You can find a more detailed description about the different properties on the [Imixs-Workflow project site](https://www.imixs.org/doc/modelling/process.html).
