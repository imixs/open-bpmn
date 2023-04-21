# Open-BPMN - Theia

Open-BPMN-Theia is a Node.js library and part of the [Open-BPMN](https://www.open-bpmn.org/) project. The module `@open-bpmn/open-bpmn-thea` integrates  Open-BPMN into the Eclipse Theia platform. 
## Build and Dependencies

To build the client module run

	$ yarn

Please follow the [GLSP Theia Integration guide](https://github.com/eclipse-glsp/glsp-theia-integration) for more details about how to integrate a GLSP Client into the Eclipse Theia platform.

## GLSP Theia Integration

**Please Note:** Due to a transitive dependency to `sprotty-theia` it's currently not possible to safely restrict the maximum version of Theia packages. If you encounter build errors related to multiple resolved Theia versions please add a resolutions block to the `package.json` of your project e.g. for `1.0.0-theia1.27.0`:

```json
...
 "resolutions": {
    "**/@theia/core": "1.27.0",
    "**/@theia/editor": "1.27.0",
    "**/@theia/filesystem": "1.27.0",
    "**/@theia/messages": "1.27.0",
    "**/@theia/monaco": "1.27.0"
  },
...
```

### More information

For more information, please visit the [Eclipse GLSP Theia Integration repository](https://github.com/eclipse-glsp/glsp-theia-integration)  and the [Eclipse GLSP Website](https://www.eclipse.org/glsp/).
If you have questions, please raise them in the [discussions](https://github.com/eclipse-glsp/glsp/discussions) and have a look at our [communication and support options](https://www.eclipse.org/glsp/contact/).

