# Installation Guide

The architecture of **Open BPMN** makes it possible to run the modeller on various IDEs and platforms. It can be installed on different IDEs such as [Visual Studio Code](https://code.visualstudio.com/), [Eclipse IDE](https://eclipse.org/), [Eclipse Theia](https://theia-ide.org/) or it can be run as a standalone web application.

## Install in Visual Studio Code

To run Open-BPMN in Visual Studio Code you can install the [Open BPMN Extension](https://marketplace.visualstudio.com/items?itemName=open-bpmn.open-bpmn-vscode-extension). Just go to 'Extensions' and search for 'Open-BPMN'

<img src="./images/vscode-integration-install.png" width="500" />

**Note:** The Open-BPMN VS Code extension includes the Imixs-Workflow BPMN Extensions. This makes it possible to model plain BPMN models as also workflow models for [Imixs-Workflow](https://www.imixs.org).

## Run With Docker

Open-BPMN can also be run as a Web Application in a Docker Container. This solution includes the Eclipse Theia Platform. To run Open-BPMN with docker just start a local Docker container:

    $ docker run -it --rm --name="open-bpmn" \
      -p 3000:3000 \
      imixs/open-bpmn

After starting the container the application is accessible from your Web Browser:

    http://localhost:3000

<img src="./images/imixs-bpmn-001.png" width="500" />

### Workspace Directory

The Theia Client is using a local workspace directory `/usr/src/app/open-bpmn.glsp-client/workspace`. The workspace directory is the place to create and edit the BPMN files. With Docker you can change the workspace directory and map it to a local directory with the Docker param -v

In the following example the workspace is mapped to the local directory `/tmp/my-workspace`

    $ docker run -it --rm --name="open-bpmn" \
      -p 3000:3000 \
      -v /tmp/my-workspace:/usr/src/app/open-bpmn.glsp-client/workspace \
      imixs/open-bpmn

## Kubernetes

You can also run Open-BPMN in a Kubernetes cluster. The following is a deplyoment yaml file that you can use as a template for your own configuration. Note also in Kubernetes you can map the workspace directory to a persistence volume.

```
---
###################################################
# Deployment office-demo
###################################################
apiVersion: apps/v1
kind: Deployment
metadata:
  name: modeler-app
  namespace: open-bpmn
  labels:
    app: modeler-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: modeler-app
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: modeler-app
    spec:
      containers:
      - image: imixs/open-bpmn:latest
        name: modeler-app
        imagePullPolicy: Always
        env:
        - name: TZ
          value: Europe/Berlin
        ports:
          - name: web
            containerPort: 3000
        resources:
          requests:
            memory: "128M"
          limits:
            memory: "1G"
      restartPolicy: Always


---
###################################################
# Service open-bpmn
###################################################
apiVersion: v1
kind: Service
metadata:
  name: modeler-app
  namespace: open-bpmn
spec:
  ports:
  - protocol: TCP
    name: web
    port: 3000
  selector:
    app: modeler-app
```

To deploy Open-BPMN in your cluster create the namespace and apply your Pod configuration:

    $ kubectl create namespace open-bpmn
    $ kubectl apply -f apps/open-bpmn
