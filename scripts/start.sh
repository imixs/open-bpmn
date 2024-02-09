#!/bin/bash
echo "***************************************"
echo "* Starting - Open BPMN.....           *"
echo "***************************************"

# Test server jar...
if [[ -z "${GLSP_SERVER_JAR}" ]]; then
  # set default jar
  GLSP_SERVER_JAR="open-bpmn.glsp-server/target/open-bpmn.server*-glsp.jar"
fi

echo "Server jar="$GLSP_SERVER_JAR

# Start server in background...
java -jar $GLSP_SERVER_JAR org.openbpmn.glsp.BPMNServerLauncher --port=5007 &

# start client
cd open-bpmn.glsp-client/
yarn start:external --hostname=0.0.0.0 --root-dir=/usr/src/app/open-bpmn.glsp-client/workspace/demo
#cd ..

