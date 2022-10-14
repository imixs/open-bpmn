Manhattan Routing and ChangeRoutingPointsOperation

There seems to be a general problem between the Sprotty Manhattan Router and the ChangeRoutingPointsOperation.  Still after hours of debugging I can not clearly solve the problem. But I gathered some hopefully useful information:

The rout() method of the Sprotty ManhattanEdgeRouter is computing the routing points as we can see it visually on the screen.

The ChangeRoutingPointsOperation seems to be somehow connected to the GLSP EdgeEditListener. As far as I understand the code (did I ever mentioned that the code is poorly commented? ;-), the idea of this listener is to send a ChangeRoutingPointsOperation on a MouseUp event. 

            if (latestEdge && (0, smodel_util_1.isRoutable)(latestEdge)) {
                result.push(protocol_1.ChangeRoutingPointsOperation.create([{ elementId: latestEdge.id, newRoutingPoints: latestEdge.routingPoints }]));
                this.routingHandle = undefined;
            }


This looks like the event is simply sending the current routingPoints to the server.
But the problem is, that the AnchorPoints are missing in this list. And this makes the routing information in case of BPMN useless for other tools as the anchorPoints are an important part of the routing. 

Now the big question is: Where have these routing points gone?
