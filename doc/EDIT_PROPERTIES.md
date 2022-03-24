# How to update a Model Element

If you have changed values for element properties on the client side you need to send an action event to the model server to apply the new values to the model. 

## The Server Side

On the server side you need to define a Operation which eceps the ID of the element and an expression describing what changed. 

````java
public class ApplyEventEditOperation extends Operation {

    private String id;
    private String expression;

    public ApplyEventEditOperation() {
        super("applyEventEdit");
    }

    public ApplyEventEditOperation(final String nodeId, final String expression) {
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

The expression can be a string describing the change. For example this can be simple the name of the property and the new value:

	name:My New Task Name
	
Or you can also describe your changes into some kind of XML. 


## Implement a OperationHandler

Next you need implement a OperationHandler for your Operation.

````java
public class ApplyEventEditOperationHandler extends AbstractOperationHandler<ApplyEventEditOperation> {

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected GModelState modelState;

    @Override
    protected void executeOperation(final ApplyEventEditOperation operation) {
        String text = operation.getExpression();
        if (text.startsWith("name:")) {
	        // extract the property and the value
	        ....
	        // dispatch a new EventEditOperation
	        ....
	        actionDispatcher.dispatch(new EventEditOperation(operation.getId(), "name", value));
        }
    }

}



## Binding the Operation Hanlder

And finally you need to find the handler to your DiagramModule in the method configureOperationHandlers

````java
public class BPMNDiagramModule extends GModelJsonDiagramModule {

    ....

    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
        super.configureOperationHandlers(binding);

		.....

        // register operations
        binding.add(ApplyEventEditOperationHandler.class);
    }
}
````




## Notification Loop

See: https://github.com/eclipse-emfcloud/emfcloud/discussions/124