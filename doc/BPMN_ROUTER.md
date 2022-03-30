# The BPMN Router

In Open-BPMN we use a custom BPMN Router based on the [Sprotty Manhattan Router](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/manhattan-edge-router.ts).



## Rounded Corners

To support rounding corners we define a custom `BPMNSequenceFlowView` based on the GLSP `PolylineEdgeViewWithGapsOnIntersections`. 



## AnchorComputer

We also implement a custom AnchorComputer to compute the anchor points for Task, Event and Gateway Elements. 



### The Implementation of the BPMN Router

The implementation of the default routers and anchoring is located in Sprotty. Based on the registered edge routes (see the [router module](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/di.config.ts) where they are registered) and the router type of the edge in the GModel, the respective router implementation (e.g. [Manhattan router](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/manhattan-edge-router.ts)) and anchoring (e.g. [Manhattan anchor computer](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/manhattan-anchors.ts)) is selected and applied on the client.

In OpenBPMN we bind your own BPMN Router and anchorComputer on the client. 

    bind(YourEdgeRouter).toSelf().inSingletonScope();
    bind(TYPES.IEdgeRouter).toService(YourEdgeRouter);

the  router specify the kind:

export class YourEdgeRouter extends AbstractEdgeRouter {
    static readonly KIND = 'yourrouter';
   ...
}

Then, in the GModel set the respective router kind to select your router for particular edges.


