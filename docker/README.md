# Docker

Open-BPMN provides a Docker image to run the BPMN modeler as a Container in Docker or Kubernetes. 

The Open-BPMN Docker image is based on the [official NodeJS image (node:16-buster)](https://hub.docker.com/_/node). The container image contains a prebuild appliction and exposes the port 3000

In the Dockerfile we are using the entrypoint:

	ENTRYPOINT [ "yarn", "start", "--hostname=0.0.0.0" ]

setting the environment param `--hostname=0.0.0.0` is important to allow access form outside the container. Find more details also [here](https://dev.to/hagevvashi/don-t-forget-to-give-host-0-0-0-0-to-the-startup-option-of-webpack-dev-server-using-docker-1483) and [here](https://github.com/theia-ide/theia-apps/tree/master/theia-cpp-docker). 

## Build

To build the docker image from sources run:

	$ docker build . -t imixs/open-bpmn

	
## Run

To run the docker image locally run:

	$ docker run --name="open-bpmn" \
	  --rm \
      -p 3000:3000 \
      imixs/open-bpmn
      
After starting the container the applicaiton is available on 

	http://localhost:3000
	      
To stop the container run:

	$ docker stop open-bpmn