#!/bin/bash
echo "***************************************"
echo "* Build - Open BPMN.....             *"
buildBackend='false'
buildFrontend='false'
forceFrontend='false'

if [[ $1 == "-h" ]]; then
  printf "Usage: build.sh [-h] [-b] [-ff] [-f]\n\n"
  echo "Options:"
  echo "  -b   Build Backend"
  echo "  -ff  Remove yarn.lock"
  echo "  -f   Build Frontend"
  exit 0
fi
echo "***************************************"

if [[ "$1" == "" ]]; then
  buildBackend='true'
  buildFrontend='true'
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

[[ "$buildBackend" == "true" ]] && echo "  Build Backend (-b)"
[[ "$forceFrontend" == "true" ]] && echo "  Remove yarn.lock (-ff)"
[[ "$buildFrontend" == "true" ]] && echo "  Build Frontend (-f)"

if [ "$buildBackend" == "true" ]; then
  echo "$(date +"[%T.%3N]") Build backend products"
  cd open-bpmn.glsp-server/
  mvn clean install
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
  yarn start
  cd ..
fi


