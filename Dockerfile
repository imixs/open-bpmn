FROM node:16-buster

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev openjdk-11-jre

# Create app directory
WORKDIR /usr/src/app

# Copy GLSP Server part
COPY open-bpmn.glsp-server/target/open-bpmn.server-*-glsp.jar ./open-bpmn.glsp-server/target/

# Build GLSP Client part
COPY open-bpmn.glsp-client/ ./open-bpmn.glsp-client/
WORKDIR /usr/src/app/open-bpmn.glsp-client
RUN yarn install

# Copy Start script
WORKDIR /usr/src/app
COPY scripts/start.sh start.sh

EXPOSE 3000

ENTRYPOINT [ "/usr/src/app/start.sh" ]
