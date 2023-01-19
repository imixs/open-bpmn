#!/bin/bash
echo "***************************************"
echo "* Open BPMN - Maven Release Process   *"
echo "***************************************"

# Check java_home
if [[ "$JAVA_HOME" == *"11"* ]]; then
    echo $JAVA_HOME
else
    echo "JAVA_HOME is not set to JDK 11!"
    exit 0
fi


# First compute the current version number from the pom.xml
SOURCE_VERSION=$(grep -oP -m1 '(?<=<version>)[^-SNAPSHOT]+' pom.xml)
echo "...current version: $SOURCE_VERSION"

echo "...release server part.....    "
mvn clean install
mvn release:clean release:prepare -DautoVersionSubmodules=true
mvn release:perform



NEXT_VERSION=$(grep -oP -m1 '(?<=<version>)[^-SNAPSHOT]+' pom.xml)





echo "***************************************"
echo "* Maven Release Process completed!    *"
echo "* New Version: $NEXT_VERSION"
echo "***************************************"
