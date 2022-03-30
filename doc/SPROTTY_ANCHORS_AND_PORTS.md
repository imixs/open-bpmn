# Anchors & Ports

Sprotty provides different Routing algorithm to connect nodes with a edge. 

<img src="./images/sprotty-router.gif" />

The end of an edge is connected to your node element. The connection point can be controlled by either an Anchor Computer or by defining Ports. Both concepts will be introduced in the following sections.


## AnchorComputers

Each Router algorithm provided by Sprotty defines so called AncohrComputers which are responsible to compute the exact position the end of a edge is connected with the node element. If you do not define a custom implementation the edge will end at the boundaries of the node element. 

You can implement you own Anchor computing the exact position an edge should target you Node Element.

### The Interface IAnchorComputer

For a custom implementation you simple need to implement the Interface `IAnchorComputer`:

````javascript
import { injectable } from 'inversify';
import {
	Bounds,
	Point,
	ManhattanEdgeRouter,
	SConnectableElement,
	IAnchorComputer
} from 'sprotty';

export const CUSTOM_TASK_ANCHOR_KIND = 'custom-task';

/**
 * This CustomTaskAnchor computes a custom anchor point for a task element.
 */
@injectable()
export class CustomTaskAnchor implements IAnchorComputer {

	static KIND = ManhattanEdgeRouter.KIND + ':' + CUSTOM_TASK_ANCHOR_KIND;

	get kind(): string {
		return CustomTaskAnchor.KIND;
	}

	getAnchor(connectable: SConnectableElement, refPoint: Point, offset: number): Point {
		// compute an custom anchor point 
       .....
		return { x: customXPos, y: customYPos };
	}
}
````

You need to provide a unique AnchorKind so that the Router algorithm can choose the correct AnchorComputer.
In the Method getAnchor you can now compute the x/y position within your element which is provide in the parameter `connectable`. The parameter `refPoint` defines the position from where the edge is entering you element and the `offset` defines an optional offset. 

Next you need to overwrite the method `get anchorKind()` in you SNode implementation to give the router algorithm a hint which AnchorComputer to choose. It is possibel to implement different Anchorcomputers for different router algorithim. In the example above we define a CustomTaskAnchor for the ManhattanEdgeRouter with the name 'custom-task'. This name is retunred by the CustomTaksNode implementation:


````javascript
export class CustomTaskNode extends RectangularNode {
     ....

    /*
     * Returns the anchorComputer Kind for CustomTaskNode
     */
     get anchorKind(): string {
        return CUSTOM_TASK_ANCHOR_KIND;
    }
}
````

Finally you need to bind your CustomAnchor in your ContainerModule:


````javascript
const myDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    ....

    // bind the Custom AnchorComputer
    bind(TYPES.IAnchorComputer).to(CustomTaskAnchor).inSingletonScope();
    ....
}
  .....
````

That's it. Now each time you connect an edge with your CustomTask the CustomTaskAnchor computer will be choosen to compute the exact position to connnect the edge with your node element. 

## Ports

Another way how you can define how edges are connected with our node elements are Ports. 
Ports in GLSP are a way to describe docking points for a graphical node element to connect edges. Ports are a concept of [Eclipse Sprotty](https://github.com/eclipse/sprotty) and are adapted by GLSP. Ports are optional. This means that if you do not define explicit ports you can connect an edge on any border of your graphical element.


If you have more complex model elements ports are a good solution to define where edges can be placed to connect your nodes.

<img src="./images/ports-01.png" />

If you define ports, edges can only be placed on specific docking positions within your node element. This gives you more control about the design and layout of your diagrams.

<img src="./images/ports-02.png" />

### How to Define Ports

Ports are part of your model. This means first of all you have to define ports along with your node builder class

#### GLSP Server

```java
public class EventNodeBuilder extends AbstractGNodeBuilder<EventNode, EventNodeBuilder> {

	....

   @Override
   public void setProperties(final EventNode node) {
      super.setProperties(node);
      .....
      node.getChildren().add(createPort(node, -5.0, -25.0, "_north"));
      node.getChildren().add(createPort(node, -25.0, -5.0, "_west"));
      node.getChildren().add(createPort(node, 15.0, -5.0, "_east"));
      node.getChildren().add(createPort(node, -5.0, 15.0, "_south"));
   }

   private GPort createPort(final EventNode node, final Double x, final Double y, final String subID) {
      return new GPortBuilder()
         .id(node.getId() + subID)
         .position(x, y)
         .size(5.0, 5.0)
         .build();
   }
}
```


Next you need to extend your DiagramConfiguration by providing the EdgeHints:

```java
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

   ....
DefaultTypes.PORT
   /**
    * Returns the edge type hints for the diagram implementation. Edge type hints are sent to the client and used to
    * validate whether certain operations for edges are allowed without having to query the server again.
    *
    * @return List of all edge type hints for the diagram implementation.
    */
   @Override
   public List<EdgeTypeHint> getEdgeTypeHints() {
      List<EdgeTypeHint> edgeHints = new ArrayList<>();
      edgeHints.add(createDefaultEdgeTypeHint(EDGE));
      EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(ModelTypes.SEQUENCE_FLOW);
      edgeHints.add(sequenceFlowHint);
      return edgeHints;
   }

   @Override
   public EdgeTypeHint createDefaultEdgeTypeHint(final String elementId) {
      EdgeTypeHint hint = super.createDefaultEdgeTypeHint(elementId);
      // allow tasks and ports
      hint.setSourceElementTypeIds(Arrays.asList(DefaultTypes.PORT,ModelTypes.MANUAL_TASK));
      hint.setTargetElementTypeIds(Arrays.asList(DefaultTypes.PORT,ModelTypes.MANUAL_TASK));
      return hint;
   }

	....

}
```

#### GLSP Client 

Now you server model provides additional port information to the client. To render the ports on you client, you have to provide the port element within your diagram configuration:


```javascript
const bpmnDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
  
    .....
    configureModelElement(context, 'port', RectangularPort, RectangularNodeView);
    ......
});
```

In case you have defined custom views you need to ensure that your ports are rendered by calling the `renderChildren` method. 

```javascript
@injectable()
export class EventNodeView extends ShapeView {
	render(element: EventNode, context: RenderingContext): VNode | undefined {

	....
	
			vnode = (
				<g transform={'scale(1) translate(0,0)'} class-sprotty-node={true}>
					<circle r="20" cx="0" cy="0" ></circle>
					<g class-icon={true}>
						<path transform={'scale(2.0) translate(-7.5,-7.5)'}
							d={eventSymbol} />
					</g>
					{context.renderChildren(element)}
				</g>
			);
	....
}
```


#### Layout

The ports can be designed in various ways by just providing CSS definitions. See the following example which highlights the ports only if a edge modification is recognized:

```css
.sprotty-node .sprotty-port {
	stroke-width: 1;
	stroke: transparent;
	fill: transparent;
}
.sprotty-graph.edge-modification-not-allowed-mode .sprotty-node:hover .sprotty-port,
.sprotty-graph.edge-creation-select-target-mode .sprotty-node:hover .sprotty-port,
.sprotty-graph.edge-reconnect-select-target-mode .sprotty-node:hover .sprotty-port {
	fill: #d4d4d478;	
}
.sprotty-graph.edge-creation-select-target-mode .sprotty-node .sprotty-port.mouseover,
.sprotty-graph.edge-reconnect-select-target-mode .sprotty-node .sprotty-port.mouseover {
	fill: rgb(85, 87, 83);
	stroke: #f57900;
} 
```
