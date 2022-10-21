#!/bin/bash
echo "***************************************"
echo "* Start - Open BPMN.....              *"
nohup java -jar open-bpmn.server-0.4.0-SNAPSHOT-glsp.jar org.openbpmn.glsp.BPMNServerLauncher
yarn start:external
