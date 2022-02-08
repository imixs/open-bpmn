#!/bin/bash

# Removes local yarn links used for local development
#


echo "--- Start unlinking local packages --- "
# unLink client
cd ../imixs-bpmn.glsp-client/bpmn-glsp/
yarn unlink
yarn unlink "@imixs-bpmn/bpmn-glsp" 
