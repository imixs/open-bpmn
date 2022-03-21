#!/usr/bin/env bash

# Wipe out all node.js packages
#
echo "***************************************"
echo "* Wipe Out - full mode.....           *"
echo "***************************************"

rm -R ./open-bpmn-app/lib
rm -R ./open-bpmn-app/node_modules
rm -R ./open-bpmn-app/src-gen
rm -R ./open-bpmn-app/gen-webpack*
rm -R ./open-bpmn-app/webpack*
rm -R ./open-bpmn-app/logs



rm -R ./open-bpmn-glsp/lib
rm -R ./open-bpmn-glsp/node_modules

rm -R ./open-bpmn-theia/lib
rm -R ./open-bpmn-theia/node_modules


rm -R node_modules

rm yarn.lock