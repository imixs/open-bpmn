# The BPMN Routing

In Open-BPMN we use the [Sprotty Manhattan Router](https://github.com/eclipse/sprotty/blob/master/packages/sprotty/src/features/routing/manhattan-edge-router.ts) connecting BPMN elements with BPMN flows. We define some custom implementation to fullfill special requirements to BPMN diagrams. 

<img src="./images/bpmn-routing-01.png" />

## Rounded Corners

To support rounding corners we define a custom `BPMNSequenceFlowView` based on the GLSP `PolylineEdgeViewWithGapsOnIntersections`. This custom View implementation computes rounded corners for the edges.



## AnchorComputer

In BPMN edges are typically connected to a centered north|east|south|west position. As the Sproty default AnchorComputers allow connecting edges to any position on the element boundery we implement a `BPMNElementAnchor` which computes the center position depending on the edge refPoint. 

The `BPMNElementAnchor` is assigend to the model elements 

 - Task 
 - BPMNPort

## Port

The `BPMNPort` is a custom model element extending the `SPort` class from Sprotty.  A Port is a connection point for edges and can be defined within a element (SNode). For the BPMN Event and BPMN Gateway we are defining a Port that matches the position and dimension of the symbol. 

The BPMNPort disables the `selected` feature and adds the BPMN_ELEMENT_ANCHOR_KIND.

Because the BPMNPort is the only connection point for BPMN Events and Gateways, we define in the Server Model only Task and BPMNPort valid Source and Target ElementTypes.


