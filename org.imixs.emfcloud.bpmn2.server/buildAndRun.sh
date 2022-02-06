#!/bin/sh
mvn clean verify -Pm2 -Pfatjar
java -jar target/org.imixs.emfcloud.bpmn2.server-0.0.1-SNAPSHOT-glsp.jar org.imixs.emfcloud.bpmn2.server.launch.BPMN2ServerLauncher --port=8081 --websocket
