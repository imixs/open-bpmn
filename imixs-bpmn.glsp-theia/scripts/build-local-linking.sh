#!/bin/bash

# Setup yarn links to use the bpmn2-client packages in glsp-theia-integration
# (local development)
#


echo "--- Start linking all necessary packages --- "
# Link client
cd ../imixs-bpmn.glsp-client/bpmn-glsp/
yarn link 
yarn link "@imixs-bpmn/bpmn-glsp"

pwd
cd ../../imixs-bpmn.glsp-theia
pwd

    
#yarn install --force
yarn install
