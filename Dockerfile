FROM node:16-buster

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev openjdk-11-jre

WORKDIR /usr/src/app
# Create app directory
COPY open-bpmn.glsp-client/ ./open-bpmn.glsp-client/
# Build GLSP Client part
WORKDIR /usr/src/app/open-bpmn.glsp-client
RUN yarn install

# Copy GLSP Server part
WORKDIR /usr/src/app
COPY open-bpmn.glsp-server/target/open-bpmn.server-*-glsp.jar ./open-bpmn.glsp-server/target/
# Copy Start script
COPY scripts/start.sh start.sh

EXPOSE 3000

ENTRYPOINT [ "/usr/src/app/start.sh" ]
