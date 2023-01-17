# The Open-BPMN Release Process

The release management process of Open-BPMN is based on the [Releasemanagement and versioning](https://github.com/imixs/imixs-workflow/wiki/Releasemanagement-and-versioning) of the Imixs Workflow main project. Because the project contains a Java part and JavaScript (Node.JS) part also the release process is split into two parts. The java part is based on the Maven Release Plugin, the JavaScript part is based on [nmp]((https://docs.npmjs.com/creating-and-publishing-scoped-public-packages). 



## How to Release a new Version

A release can be performed by calling the script "release.sh". This script is as far as possible automated. It releases the current maven snapshots and publish the node.js modules into the [Open-BPMN nmp repository](https://www.npmjs.com/settings/open-bpmn/packages). 

Before you start the release process verifiy if the general build of open-bpmn is sucessfull:

	$ ./scripts/build.sh -b -f

Chack status:

 https://npmjs.com/package/open-bpmn

Login into :

	$ npm login
	
Publish with

	$ npm publish --access public 

https://www.npmjs.com/