#!/bin/bash
echo "***************************************"
echo "* Open BPMN - Release Process.....    *"
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



echo "...release node.js modules.....    *"
cd open-bpmn.glsp-client
echo "...publish to https://www.npmjs.com/"
# 
# We use a automated API token here!
# $ npm config set _authToken=%YOUR_ACCES_TOKEN%
npm login

# Publish model
cd open-bpmn-model
npm publish --access public 
cd ..
cd open-bpmn-properties
npm publish --access public 
cd ..
cd open-bpmn-glsp
npm publish --access public 
cd ..
cd open-bpmn-theia
npm publish --access public 
# The app will no longer be published to npmjs
# Because this is not necessary
#cd ..
#cd open-bpmn-app
#npm publish --access public 

cd ../..

NEXT_VERSION=$(grep -oP -m1 '(?<=<version>)[^-SNAPSHOT]+' pom.xml)
echo "... upgrade open-bpmn client version: $NEXT_VERSION"

###################
## REPLACE verison in node.js ##
###################

# replace manifest and nodejs versions
# Examples:
# "version": "0.3.0",
# "@open-bpmn/open-bpmn-model": "0.3.0",

find * -name 'package.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$NEXT_VERSION\",/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-model\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-model\": \"$NEXT_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-glsp\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-glsp\": \"$NEXT_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-properties\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-properties\": \"$NEXT_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-theia\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-theia\": \"$NEXT_VERSION\"/g"
find * -name 'lerna.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$NEXT_VERSION\",/g"
find * -name 'launch.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$NEXT_VERSION\",/g"
find * -name 'bpmn-glsp-server-contribution.ts' | xargs perl -pi -e "s/open-bpmn.server-${SOURCE_VERSION}/open-bpmn.server-$NEXT_VERSION/g"
find * -name 'build.sh' | xargs perl -pi -e "s/open-bpmn.server-${SOURCE_VERSION}/open-bpmn.server-$NEXT_VERSION/g"


echo "... done"











echo "***************************************"
echo "* Release Process completed!          *"
echo "* New Version: $NEXT_VERSION"
echo "***************************************"
