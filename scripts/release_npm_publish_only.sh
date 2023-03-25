#!/bin/bash
echo "***************************************"
echo "* npm Open BPMN - Publish Only Process*"
echo "***************************************"


echo "...publish node.js modules.....    *"
cd open-bpmn.glsp-client
echo "...login to https://www.npmjs.com/"
# 
# We use a automated API token here!
# $ npm config set _authToken=%YOUR_ACCES_TOKEN%
npm login

# Publish model
cd open-bpmn-model
npm publish --access public 
cd ..
cd open-bpmn-properties
npm publish --access public 
cd ..
cd open-bpmn-glsp
npm publish --access public 
cd ..
cd open-bpmn-theia
npm publish --access public 
# The app will no longer be published to npmjs
# Because this is not necessary
#cd ..
#cd open-bpmn-app
#npm publish --access public 

cd ../..

echo "***************************************"
echo "* npm Publich Process completed!      *"
echo "***************************************"
