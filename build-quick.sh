#!/usr/bin/env bash

# Build all node.js packages
#
echo "***************************************"
echo "* Build -  client.....      *"
echo "***************************************"

cd imixs-bpmn.glsp-client && yarn

yarn start