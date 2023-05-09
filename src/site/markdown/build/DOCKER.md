# Docker

Open-BPMN provides a Docker image to run the BPMN modeler as a Container in Docker or Kubernetes.

The Open-BPMN Docker image is based on the [official NodeJS image (node:16-buster)](https://hub.docker.com/_/node). The container image contains a pre-build application and exposes the HTTP port 3000 to run the Client in a Web Browser.

## Run

To run the docker image locally run:

    $ docker run --name="open-bpmn" \
      --rm \
      -p 3000:3000 \
      imixs/open-bpmn

After starting the container the application is available on

    http://localhost:3000

To stop the container run:

    $ docker stop open-bpmn

### Workspace Directory

The Theia Client is using a local workspace directory `/usr/src/app/open-bpmn.glsp-client/workspace`. You can change this directory and map it to a local directory with the Docker param -v

In the following example we map the workspace to the local directory /tmp/my-workspace

    $ docker run --name="open-bpmn" \
      --rm \
      -p 3000:3000 \
      -v /tmp/my-workspace:/usr/src/app/open-bpmn.glsp-client/workspace \
      imixs/open-bpmn

## Build

To build the docker image from sources run:

    $ docker build . -t imixs/open-bpmn

### Start Script

The Open-BPMN Dockerfile is using a start script to launch the GLSP Server and the GLSP Theia client. The script is reading the GLSP Server version from the environment variable `GLSP_SERVER_JAR`. You can overwrite this variable to build custom images.

Here is an example for a Dockerfile that copies a custom version of a server:

```
FROM imixs/open-bpmn:latest
# copy imixs-server module
COPY imixs-open-bpmn.server/target/imixs-open-bpmn.server-*-glsp.jar ./open-bpmn.glsp-server/target/
ENV GLSP_SERVER_JAR=open-bpmn.glsp-server/target/imixs-open-bpmn.server-*-glsp.jar
```

### Dockerfile Entrypoint

In the Dockerfile we are using a custom entrypoint and set the yarn param `--hostname=0.0.0.0`

**Note:** Setting the environment param `--hostname=0.0.0.0` is important to allow access form outside the container. Find more details also [here](https://dev.to/hagevvashi/don-t-forget-to-give-host-0-0-0-0-to-the-startup-option-of-webpack-dev-server-using-docker-1483) and [here](https://github.com/theia-ide/theia-apps/tree/master/theia-cpp-docker).
