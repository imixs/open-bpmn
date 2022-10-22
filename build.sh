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
  if [[ "$1" == -*"r"* ]]; then
    rebuildFrontend='true'
  fi  
  if [[ "$1" == -*"d"* ]]; then
    buildDocker='true'
  fi  
fi

while [ "$1" != "" ]; do
  case $1 in
    -b | --backend )  buildBackend='true'
                      ;;
    -f | --frontend ) buildFrontend='true'
                      ;;
    -r | --frontend ) rebuildFrontend='true'
                      ;;
    -d | --docker ) buildDocker='true'
                      ;;
    -ff | --forcefrontend ) forceFrontend='true'
                      ;;
  esac
  shift
done

[[ "$buildAll" == "true" ]] && echo "  building Backend and Frontend"
[[ "$buildBackend" == "true" ]] && echo "  building Backend Only (-b)"
[[ "$buildFrontend" == "true" ]] && echo "  building Frontend Only (-f)"
[[ "$rebuildFrontend" == "true" ]] && echo "  rebuilding Frontend (-r)"
[[ "$forceFrontend" == "true" ]] && echo "  clean .gitignore files and building Frontend Only (-ff)"
[[ "$buildDocker" == "true" ]] && echo "  building Docker Image"

if [ "$buildAll" == "true" ]; then
  echo "$(date +"[%T.%3N]") Build backend products"
  cd open-bpmn.glsp-server/
  mvn clean install
  cd ../
fi

if [ "$buildBackend" == "true" ]; then
  echo "$(date +"[%T.%3N]") Build backend products"
  mvn clean install -DskipTests
  cd open-bpmn.glsp-server/target
  java -jar open-bpmn.server-0.5.0-SNAPSHOT-glsp.jar org.openbpmn.glsp.BPMNServerLauncher
  cd ../
fi

if [ "$forceFrontend" == "true" ]; then
  # clean up gitignore files
  git clean -xdf
fi

if [ "$buildFrontend" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn start:external
  cd ..
fi

if [ "$rebuildFrontend" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn
  cd ..
fi

if [ "$buildDocker" == "true" ]; then
  mvn clean install -DskipTests
  docker build . -t imixs/open-bpmn:latest
  docker push imixs/open-bpmn:latest
fi


if [ "$buildAll" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn
  yarn start
  cd ..
fi

