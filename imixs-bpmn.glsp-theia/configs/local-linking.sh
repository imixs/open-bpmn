#!/bin/bash

# Setup yarn links to use the glsp-client packages in glsp-theia-integration
# (local development)
#
# Usage:
#  $ linkClient linkCmd baseDir
#       * linkCmd: The link command (link|unlink)
#       * baseDir: The base directory
function linkClient(){
    #cd $2/glsp-client || exit
    cd $2/bpmn2-client || exit
    
    # ???cd examples/workflow-glsp || exit
    
    yarn $1
    cd ../../packages/client || exit
    yarn $1
    cd ../protocol || exit
    yarn $1
    cd $2/glsp-client ||exit
    yarn install --force
    cd node_modules/sprotty || exit
    yarn $1
}

#### MAIN Script
# Script to (un)link the the glsp packages that spawn accross multiple repositories
# for local development.
# Usage:
#  $ ./local-linking.sh baseDir (--unlink [opional])
#       * baseDir: The base directory. All glsp repositories are expected to be
#                  childs of this directory
#       * --unlink: Optional flag. Set if packages should be unlinked instead of linked

baseDir=$(cd $1|| exit; pwd)
if [[ "$baseDir" == "" ]]; then
    echo "ERROR: No basedir was defined"
    exit 0
fi


if [[ "$2" != "--unlink" ]]; then
    echo "--- Start linking all necessary packages --- "
    # Link client
    linkClient link $baseDir
    cd $baseDir/bpmn2-theia || exit
    #yarn link sprotty @eclipse-glsp/client  @eclipse-glsp/protocol @eclipse-glsp-examples/workflow-glsp
    yarn link sprotty @eclipse-glsp/client  @eclipse-glsp/protocol
    yarn install --force
    echo "--- LINKING SUCCESSFULL --- "
else
    echo "--- Start unlinking all previously linked packages --- "
    yarn unlink sprotty @eclipse-glsp/client  @eclipse-glsp/protocol  @eclipse-glsp-examples/workflow-glsp
    yarn install --force
    linkClient unlink $baseDir
    echo "--- UNLINKING SUCCESSFULL --- "
fi
