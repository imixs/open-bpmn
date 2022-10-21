FROM node:16-buster

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev openjdk-11-jre

# Create app directory
WORKDIR /usr/src/app

# Run GLSP Server part
COPY open-bpmn.glsp-server/target/open-bpmn.server-0.4.0-SNAPSHOT-glsp.jar ./open-bpmn.glsp-server/target/
# Build & RUN GLSP Client part
COPY open-bpmn.glsp-client/ ./open-bpmn.glsp-client/

COPY docker/build.sh ./
RUN chmod +x build.sh
WORKDIR /usr/src/app/open-bpmn.glsp-client
RUN yarn
WORKDIR /usr/src/app
EXPOSE 3000
CMD [ "./build.sh" ]
