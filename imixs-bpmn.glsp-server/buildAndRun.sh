#!/bin/sh
mvn clean verify -Pm2 -Pfatjar
java -jar target/org.imixs.bpmn.glsp.server-0.0.1-SNAPSHOT-glsp.jar org.imixs.bpmn.glsp.server.launch.BPMN2ServerLauncher --port=8081 --websocket
