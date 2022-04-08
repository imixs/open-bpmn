#!/bin/bash
echo "***************************************"
echo "* Build - Open BPMN.....              *"
buildAll='false'
buildBackend='false'
buildFrontend='false'
forceFrontend='false'

if [[ $1 == "-h" ]]; then
  printf "Usage: build.sh [-h] [-b] [-ff] [-f]\n\n"
  echo "Options:"
  echo "  -b   building Backend..."
  echo "  -ff  remove yarn.lock..."
  echo "  -f   building Frontend..."
  exit 0
fi
echo "***************************************"

if [[ "$1" == "" ]]; then
  buildAll='true'
fi

if [[ ${#1} -gt 2 ]]; then
  if [[ "$1" == -*"b"* ]]; then
    buildBackend='true'
  fi
  if [[ "$1" == -*"f"* ]]; then
    buildFrontend='true'
  fi
  if [[ "$1" == -*"ff"* ]]; then
    forceFrontend='true'
  fi
fi

while [ "$1" != "" ]; do
  case $1 in
    -b | --backend )  buildBackend='true'
                      ;;
    -f | --frontend ) buildFrontend='true'
                      ;;
    -ff | --forcefrontend ) forceFrontend='true'
                      ;;
  esac
  shift
done

[[ "$buildAll" == "true" ]] && echo "  building Backend and Frontend"
[[ "$buildBackend" == "true" ]] && echo "  building Backend Only (-b)"
[[ "$buildFrontend" == "true" ]] && echo "  building Frontend Only (-f)"
[[ "$forceFrontend" == "true" ]] && echo "  building Frontend only and remove yarn.lock (-ff)"

if [ "$buildAll" == "true" ]; then
  echo "$(date +"[%T.%3N]") Build backend products"
  cd open-bpmn.glsp-server/
  mvn clean install
  cd ../
fi

if [ "$buildBackend" == "true" ]; then
  echo "$(date +"[%T.%3N]") Build backend products"
  cd open-bpmn.glsp-server/
  mvn clean install
  cd ./target
  java -jar open-bpmn.server-0.0.9-SNAPSHOT-glsp.jar org.imixs.bpmn.glsp.BPMNServerLauncher
  cd ../
fi

if [ "$forceFrontend" == "true" ]; then
  cd open-bpmn.glsp-client/
  rm -f ./yarn.lock
  cd ..
fi

if [ "$buildFrontend" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn
  yarn start:external
  cd ..
fi


if [ "$buildAll" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn
  yarn start
  cd ..
fi

