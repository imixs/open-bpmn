FROM node:18.19.0-bookworm

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev openjdk-17-jre

WORKDIR /usr/src/app

ENV PUPPETEER_SKIP_DOWNLOAD=true


# Create app directory
COPY open-bpmn.glsp-client/ ./open-bpmn.glsp-client/
# Build GLSP Client part
WORKDIR /usr/src/app/open-bpmn.glsp-client
RUN yarn clean
RUN yarn install

# Copy GLSP Server part
WORKDIR /usr/src/app
COPY open-bpmn.glsp-server/target/open-bpmn.server-*-glsp.jar ./open-bpmn.glsp-server/target/
# Copy Start script
COPY scripts/start.sh start.sh

EXPOSE 3000

ENTRYPOINT [ "/usr/src/app/start.sh" ]
