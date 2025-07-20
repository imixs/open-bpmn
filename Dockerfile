FROM node:18.17.1-bookworm
RUN apt-get update && apt-get install -y openjdk-17-jre && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .
RUN chown -R node:node /app/ && chmod +x /app/scripts/launch-docker-container.sh

USER node
WORKDIR /app/open-bpmn.glsp-client

# create recentworkspace.json 
RUN mkdir -p /home/node/.theia && \
    echo '{"recentRoots":["file:///app/open-bpmn.glsp-client/workspace"]}' > /home/node/.theia/recentworkspace.json && \
    chown -R node:node /home/node/.theia/

EXPOSE 3000
CMD ["yarn", "start", "--hostname=0.0.0.0", "--port=3000"]