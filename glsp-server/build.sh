#!/usr/bin/env bash

# build the server
#
echo "***************************************"
echo "* build sever....                     *"
echo "***************************************"

mvn clean verify -Pm2 -Pfatjar