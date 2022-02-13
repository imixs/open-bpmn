#!/usr/bin/env bash

# Wipe out all node.js packages
#
echo "***************************************"
echo "* Wipe Out - quick mode.....          *"
echo "***************************************"

rm -R ./bpmn-browser-app/lib
rm -R ./bpmn-browser-app/node_modules
rm -R ./bpmn-browser-app/src-gen
rm -R ./bpmn-browser-app/gen-webpack*
rm -R ./bpmn-browser-app/webpack*
rm -R ./bpmn-browser-app/logs



rm -R ./bpmn-glsp/lib
rm -R ./bpmn-glsp/node_modules

rm -R ./bpmn-theia/lib
rm -R ./bpmn-theia/node_modules

rm -R node_modules
