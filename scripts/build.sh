#!/bin/bash
echo "***************************************"
echo "* Build - Open BPMN.....              *"
echo "***************************************"
buildAll='false'
buildBackend='false'
buildFrontend='false'
install='false'
watch='false'
forceFrontend='false'

if [[ $1 == "-h" ]]; then
  printf "Usage: build.sh [-s] [-f] [-b] [-i] [-c]\n\n"
  echo "Options:"
  echo "  -h   help..."
  echo "  -s   start Frontend..."
  echo "  -f   build & start Frontend only..."
  echo "  -b   build & start Backend only..."
  echo "  -i   reinstall Frontend..."
  echo "  -w   watch changes (dev mode)..."
  echo "  -c   clean..."
  echo "  "
  echo "  no options = build & start all..."
  echo "  "
  exit 0
fi
echo "***************************************"

# First compute the current version number from the pom.xml
version=$(grep -oP -m1 '(?<=<version>)[^-SNAPSHOT]+' pom.xml)
echo "... current snapshot version: $version"

if [[ "$1" == "" ]]; then
  buildAll='true'
fi



while [ "$1" != "" ]; do
  case $1 in
    -s | --start) start='true'
                      ;;
    -f | --frontend ) buildFrontend='true'
                      ;;
    -b | --backend )  buildBackend='true'
                      ;;
    -i | --install ) install='true'
                      ;;
    -w | --watch ) watch='true'
                      ;;
    -c | --clean ) clean='true'
                      ;;
    -d | --docker ) buildDocker='true'
                      ;;
  esac
  shift
done

[[ "$buildAll" == "true" ]] && echo "  building Backend & Frontend"
[[ "$clean" == "true" ]] && echo "  clean .gitignore files and building Frontend Only (-c)"
[[ "$buildBackend" == "true" ]] && echo "  build & start Backend (-b)"
[[ "$buildFrontend" == "true" ]] && echo "  build & start Frontend (-f)"
[[ "$install" == "true" ]] && echo "  install Frontend only (-i)"
[[ "$watch" == "true" ]] && echo "  watch mode (-w)"
[[ "$start" == "true" ]] && echo "  start Frontend (-s)"
[[ "$buildDocker" == "true" ]] && echo "  building Docker Image"


if [ "$clean" == "true" ]; then
  # clean up gitignore files
  #git clean -xdf
  mvn clean
  rm open-bpmn.glsp-client/yarn.lock
fi

if [ "$buildBackend" == "true" ]; then
  mvn clean install
  cd open-bpmn.glsp-server/target
  java -jar open-bpmn.server*-glsp.jar org.openbpmn.glsp.launch.BPMNServerLauncher
  cd ../
fi

if [ "$buildFrontend" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn 
  yarn start:external
  cd ..
fi

if [ "$watch" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn watch
  cd ..
fi


if [ "$install" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn install
  cd ..
fi

if [ "$start" == "true" ]; then
  cd open-bpmn.glsp-client/
  yarn start:external --root-dir=../open-bpmn.glsp-client/workspace
  cd ..
fi

if [ "$buildDocker" == "true" ]; then
  mvn clean install -DskipTests
  docker build . -t imixs/open-bpmn
  docker push imixs/open-bpmn
fi


if [ "$buildAll" == "true" ]; then
  cd open-bpmn.glsp-server/
  mvn clean install -DskipTests
  cd ../open-bpmn.glsp-client/
  yarn install
  yarn start
  cd ..
fi

