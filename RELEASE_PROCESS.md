# The Open-BPMN Release Process

The release management process of Open-BPMN is based on the [Releasemanagement and versioning](https://github.com/imixs/imixs-workflow/wiki/Releasemanagement-and-versioning) of the Imixs Workflow main project. Because the project contains a Java part and JavaScript (Node.JS) part also the release process is split into two parts. The java part is based on the Maven Release Plugin, the JavaScript part is based on [nmp](https://docs.npmjs.com/creating-and-publishing-scoped-public-packages).

## How to Release a new Version

A release can be performed by calling the script "release.sh". This script is as far as possible automated. It releases the current maven snapshots and publish the node.js modules into the [Open-BPMN nmp repository](https://www.npmjs.com/settings/open-bpmn/packages).

Before you start the release process verify if the general build of open-bpmn is successful:

    $ ./devi build-all

Check current release status on npm:

https://www.npmjs.com/settings/open-bpmn/packages

To be able to publish to the npm repository (https://www.npmjs.com/) you need to login during the release process with your npmjs.com account. Alternatively you can create an _automation token_ and set this token in your local environment first!

    $ cd open-bpmn.glsp-client/
    $ npm config set _authToken=%YOUR_ACCES_TOKEN%

Replace `%YOUR_ACCES_TOKEN%` with a valid Token!

Now you can run the release script:

    $ ./scripts/release.sh

Finally commit your changes in the client version files.

### Release Open-BPMN Client modules only

To just release the latest client modules to [npmjs.com](https://www.npmjs.com/settings/open-bpmn/packages) you can run the script `release_npm.sh`. You need to provide the current client version and the next version:

    $ ./scripts/release_npm.sh <oldVersionString> <newVersionString>

Specify the old and the new version. Example:

    $ ./scripts/release_npm.sh 0.3.0 0.4.0

This will push the libraries to npmjs.com and update the package versions

Finally you can commit your version changes to github.
