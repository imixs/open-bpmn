#!/bin/bash
echo "***************************************"
echo "* npm Open BPMN - Release Process     *"
echo "***************************************"

# check parameter count and provide help if count doesn't match
if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Please specify the old and the new version like this:"
    echo "	changeVersion.sh <oldVersionString> <newVersionString>"
    echo "Example:"
    echo "	changeVersion.sh 0.2.0 0.3.0"
    exit 0
fi


SOURCE_VERSION=$1
NEXT_VERSION=$2

echo "...current version: $SOURCE_VERSION"

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
echo "* npm Release Process completed!      *"
echo "* New Version: $NEXT_VERSION"
echo "***************************************"
