#!/bin/bash
echo "***************************************"
echo "* Full ReBuild - Open BPMN.....              *"

./wipe-full.sh
mvn clean install -DskipTests
./build.sh -r
./build.sh -f