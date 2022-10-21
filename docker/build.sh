#!/bin/bash
echo "***************************************"
echo "* Build - Open BPMN.....              *"
buildAll='false'
buildBackend='false'
buildFrontend='false'
rebuildFrontend='false'
forceFrontend='false'

if [[ $1 == "-h" ]]; then
  printf "Usage: build.sh [-h] [-b] [-ff] [-f] [-r]\n\n"
  echo "Options:"
  echo "  -b   building Backend..."
  echo "  -ff  remove yarn.lock..."
  echo "  -f   building Frontend..."
  echo "  -r   rebuilding Frontend..."
  exit 0
fi
echo "***************************************"

if [[ "$1" == "" ]]; then
  buildAll='true'
fi


echo "  building Backend and Frontend"


echo "$(date +"[%T.%3N]") run backend products"
java -jar open-bpmn.server-0.4.0-SNAPSHOT-glsp.jar org.openbpmn.glsp.BPMNServerLauncher


yarn start:external


