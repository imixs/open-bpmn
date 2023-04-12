# How to update a Model Element

If you want to change values for some element properties on the client side, you need to send an action event to the model server to apply the new values to your model. For this purpose you need to implement a corresponding GLSP Server Action so that your client can send an update. On the Server side you need to generate than another Action to update the model state of your GModel.

For the following example let's assume that you have a model element named 'Event' with the properties 'name' and 'documentation'. And you have some part on your client that adds new information to the property 'documentation'. Let's see how this will work from the server side:


### The Apply Operation

First of all you need to define an Apply Operation on your Server which accepts the ID of the element and an expression describing what changed. This action is later send from your client to the server: 


````java
public class ApplyEventUpdateOperation extends Operation {

    private String id;
    private String expression;

    public ApplyEventUpdateOperation() {
        super("applyEventUpdate");
    }

    public ApplyEventUpdateOperation(final String nodeId, final String expression) {
        this();
        this.id = nodeId;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(final String nodeId) {
        this.id = nodeId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(final String expression) {
        this.expression = expression;
    }
}
````

In this example we use an expression holding the name of the property and the new value:

	documentation:My New Doc....
	
Or you can also describe your changes into some kind of XML or other format.  


### The Apply Operation Handler

Next we need to implement a OperationHandler for our new Apply Action. This class handles the incomming action:

````java
public class ApplyEventUpdateOperation extends AbstractOperationHandler<ApplyEventUpdateOperation> {

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected GModelState modelState;

    @Override
    protected void executeOperation(final ApplyEventUpdateOperation operation) {
        String expression = operation.getExpression();
        // extract the property and the value
        ....
        // is it an update of the property 'documentation' ?
        if (expression.startsWith("documentation:")) {
	        // dispatch a new EventEditOperation
	        ....
	        actionDispatcher.dispatch(new EventEditOperation(operation.getId(), "documentation", value));
        }
    }

}
````

The OperationHandler extracts the property name and the value from the expression. If you send more complex data like XML you have to parse the data here. 

The Operation Handler now need to create a new Event to update the concrete model object. For that reason we define a EditEventOperation.

### The Edit Operation 

This class again is a simple POJO:

````java
public class EditEventOperation extends Operation {

    private String id;
    private String feature;
    private String value;

    public EditEventOperation() {
        super("editEvent");
    }

    public EditEventOperation(final String id, final String feature, final String value) {
        this();
        System.out.println("...create new EventEditOperation - ID=" + id + " feature=" + feature + " value=" + value);
        this.id = id;
        this.feature = feature;
        this.value = value;
    }
	
	// getter and setters ....
}
````

And also we need again an Operation Handler for this event.

### The Edit Operation Handler

The Edit Operation Handler is now updating our real GModel. For that reason the class injects the current modelState and fetches the corresponding Element from the Server model. If the concrete model element can be found by its ID we update the corresponding properties (features). 

````java
public class EditEventOperationHandler extends AbstractOperationHandler<EditEventOperation> {
    @Inject
    protected GModelState modelState;

    @Override
    protected void executeOperation(final EditEventOperation operation) {
        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }
        switch (operation.getFeature()) {
        case "name":
            element.get().setName(operation.getValue());
            break;
        case "documentation":
            element.get().setDocumentation(operation.getValue());
            break;
        default:
            throw new GLSPServerException("Cannot edit element at feature '" + operation.getFeature() + "'");
        }
    }
}
````



### Binding the Operation Handlers

Finally we need to bind the handler to your DiagramModule in the method configureOperationHandlers

````java
public class BPMNDiagramModule extends GModelJsonDiagramModule {
    ....
    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
        super.configureOperationHandlers(binding);

		.....

        // bind Apply Operation Hander (send from the client)
        binding.add(ApplyEventUpdateOperationHandler.class);
        // bind Edit Operation Handler (send by the server)
        binding.add(EditEventOperationHandler.class);
    }
}
````

That's it. Now you can send property updates from your client to the server.

## The Client Side

On the GLSP Client side we can now fire a corresponding applyEventUpdate Action each time a property of our Event Element has changed:

````javascript
@injectable()
export class MyUIExtension extends AbstractUIExtension implements EditModeListener, SelectionListener {

	@inject(TYPES.IActionDispatcher)
	protected readonly actionDispatcher: ActionDispatcher;

	....
	// Update the property 'documentation' for the selected element ID
	const action = new ApplyEventUpdateOperation(this.selectedElementId, 'documentation:' + _newValue);
	this.actionDispatcher.dispatch(action);

}

export class ApplyEventUpdateOperation implements Action {
	static readonly KIND = 'applyEventUpdate';
	readonly kind = ApplyEventUpdateOperation.KIND;
	constructor(readonly id: string, readonly expression: string) { }
}


````

That's it. The changes send by your GLSP Client will be processed by the server which is updating the GModel directly and finally sends the updated GModel back to the client.




## Notification Loop

See: https://github.com/eclipse-emfcloud/emfcloud/discussions/124