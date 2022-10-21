# Docker

## Build


	$ docker build . -t imixs/open-bpmn

	
## Run

	$ docker run --name="open-bpmn" \
	  --rm \
      -p 8080:3000 \
      imixs/open-bpmn