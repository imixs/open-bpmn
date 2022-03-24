#!/usr/bin/env bash

# launch the server
#
echo "***************************************"
echo "* Launch sever....                    *"
echo "***************************************"

 cd ./target
 java -jar open-bpmn.server-0.0.7-SNAPSHOT-glsp.jar org.imixs.bpmn.glsp.BPMNServerLauncher