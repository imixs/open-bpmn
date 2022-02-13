#!/usr/bin/env bash

# Build all node.js packages
#
echo "***************************************"
echo "* Build - server and client.....      *"
echo "***************************************"

cd imixs-bpmn.glsp-server && mvn clean verify && cd ..

cd imixs-bpmn.glsp-client && yarn

yarn start