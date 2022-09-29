#!/bin/bash

# this script can be used to change the version recursively throughout the project
# Initial version from lhein, 2014

# check parameter count and provide help if count doesn't match
if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Please specify the old and the new version like this:"
    echo "	changeVersion.sh <oldVersionString> <newVersionString>"
    echo "Example:"
    echo "	changeVersion.sh 0.2.0 0.3.0"
    exit 0
fi

CMD_1=$1
CMD_2=$2

# cut away the snapshot from the source version
SOURCE_VERSION=${CMD_1}
SOURCE_VERSION_PATTERN_MAVEN="${SOURCE_VERSION}-SNAPSHOT"
SOURCE_VERSION_PATTERN_NODEJS="\"version\": \"${SOURCE_VERSION}\""

# cut away the snapshot from the target version
# nodeJS "version": "0.3.0",
TARGET_VERSION="${CMD_2}"
TARGET_VERSION_PATTERN_MAVEN="${TARGET_VERSION}-SNAPSHOT"
TARGET_VERSION_PATTERN_NODEJS="\"version\": \"${TARGET_VERSION}\""

echo "Using the following settings:"
echo "	Source Version:	$SOURCE_VERSION"
echo "	Target Version:	$TARGET_VERSION"
echo "Replacing now..."

###################
## REPLACE LOGIC ##
###################

# replace regular maven versions
find * -name 'pom.xml' | xargs perl -pi -e "s/<version>$SOURCE_VERSION_PATTERN_MAVEN<\/version>/<version>${TARGET_VERSION}-SNAPSHOT<\/version>/g"

# replace manifest and nodejs versions
# Examples:
# "version": "0.3.0",
# "@open-bpmn/open-bpmn-model": "0.3.0",
find * -name 'package.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$TARGET_VERSION\",/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-model\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-model\": \"$TARGET_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-glsp\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-glsp\": \"$TARGET_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-properties\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-properties\": \"$TARGET_VERSION\"/g"
find * -name 'package.json' | xargs perl -pi -e "s/\"\@open-bpmn\/open-bpmn-theia\": \"${SOURCE_VERSION}\"/\"\@open-bpmn\/open-bpmn-theia\": \"$TARGET_VERSION\"/g"
find * -name 'lerna.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$TARGET_VERSION\",/g"
find * -name 'launch.json' | xargs perl -pi -e "s/\"version\": \"${SOURCE_VERSION}\",/\"version\": \"$TARGET_VERSION\",/g"
find * -name 'bpmn-glsp-server-contribution.ts' | xargs perl -pi -e "s/open-bpmn.server-${SOURCE_VERSION}/open-bpmn.server-$TARGET_VERSION/g"
find * -name 'build.sh' | xargs perl -pi -e "s/open-bpmn.server-${SOURCE_VERSION}/open-bpmn.server-$TARGET_VERSION/g"


echo "DONE!"

