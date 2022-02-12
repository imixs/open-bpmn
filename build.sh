#!/usr/bin/env bash

# Build all node.js packages
#
echo "***************************************"
echo "* Build quick mode.....            *"
echo "***************************************"

cd glsp-server && mvn clean verify && cd ..

cd glsp-client && yarn

cd glsp-client

yarn start