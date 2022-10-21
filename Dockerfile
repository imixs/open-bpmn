FROM node:16

# Install app dependencies
RUN apt-get update && apt-get install -y libxkbfile-dev libsecret-1-dev

# Create app directory
WORKDIR /usr/src/app


# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY open-bpmn.glsp-client/ ./

#RUN npm install
RUN yarn
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
#COPY . .

EXPOSE 3000
CMD [ "node", "server.js" ]
