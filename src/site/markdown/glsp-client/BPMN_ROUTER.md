# The BPMN Routing

In Open-BPMN we use the [Sprotty Manhattan Router](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/manhattan-edge-router.ts) connecting BPMN elements with BPMN flows. We define some custom implementation to fullfil special requirements to BPMN diagrams like the rounded corners.

<img src="../images/bpmn-routing-01.png" />

## Rounded Corners

To support rounding corners we define a custom `BPMNSequenceFlowView` based on the GLSP `PolylineEdgeViewWithGapsOnIntersections`. This custom View implementation computes rounded corners for the edges.
