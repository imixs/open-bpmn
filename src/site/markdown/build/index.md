
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

You will find more details in the [Client Section](./open-bpmn.glsp-client/index.html) and the [Server Section](./open-bpmn.glsp-server/index.html).

### NodeJS and NPM

We use nodejs on Linux Debian 12 during development. NodeJS and NPM can be installed directly from the Debian package manager:

    $ sudo apt install nodejs npm

After installation you can check the version:

    $ npm --version
    $ nodejs --version


After you installed npm you can install `yarn`:

    $ npm install --global yarn

Check the version with

    $ yarn --version

The following optional packages may be needed to be installed on your Debian system for building with yarn: 

 - libsecret-1-dev
 - node-gyp

