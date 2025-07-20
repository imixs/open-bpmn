FROM node:18.19.0-bookworm

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev openjdk-17-jre
WORKDIR /home/node/app
ENV PUPPETEER_SKIP_DOWNLOAD=true

# Create app directory
COPY open-bpmn.glsp-client/ ./open-bpmn.glsp-client/

# Build GLSP Client part
WORKDIR /home/node/app/open-bpmn.glsp-client
# RUN yarn install
RUN yarn install --ignore-engines

# Copy GLSP Server part
WORKDIR /home/node/app
COPY scripts/launch-docker-container.sh ./
COPY open-bpmn.glsp-server/target/open-bpmn.server-*-glsp.jar ./open-bpmn.glsp-server/target/
WORKDIR /home/node/app/open-bpmn.glsp-client

ENV HOME=/home/node
RUN chown -R node:node $HOME/app/
USER node
EXPOSE 3000

#ENTRYPOINT [ "yarn start --root-dir=./open-bpmn.glsp-client/workspace" ]
#ENTRYPOINT [ "yarn start:external --hostname=0.0.0.0 --root-dir=/home/node/app/open-bpmn.glsp-client/workspace/demo" ]
ENTRYPOINT [ "/home/node/app/launch-docker-container.sh" ]
