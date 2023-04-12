
# Build and Run

To build the complete project run

    $ ./build.sh

This build script will build the server module with maven and the client modules with yarn. The script finally automatically starts the application.

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

We use nodejs on Linux Debian during development. To manage version of nodejs in debian see: https://phoenixnap.com/kb/update-node-js-version

For development with Eclipse Theia the expected version is ">=10.11.0 <17". For that reason we tested with following version 16.11.0. You can list all current versions [here](https://nodejs.org/en/download/releases/).

In case you have install npm you can install a specific nodejs version with:

    $ sudo n 16.11.0

To install typescript run:

    $ sudo npm install -g typescript
