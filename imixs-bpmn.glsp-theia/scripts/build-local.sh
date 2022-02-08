#!/bin/bash

# Setup yarn links to use the bpmn2-client packages in glsp-theia-integration
# (local development)
#


echo "--- Start linking all necessary packages --- "
# Link client


yarn add file:../imixs-bpmn.glsp-client/ -W



    
#yarn install --force
yarn install
