# Failed to send notification message.


**https://github.com/eclipse-glsp/glsp/discussions/702**


I have implemented different GNode Elements providing the `EditableLabel` feature.
I can react on the ApplyLabelEditOperation and everything works fine. But each time I type in the edit field I receive the following exception on the server side:

Dez. 24, 2022 8:47:22 AM org.eclipse.lsp4j.jsonrpc.RemoteEndpoint notify
WARNUNG: Failed to send notification message.
java.lang.reflect.InaccessibleObjectException: Unable to make private java.util.Collections$EmptyMap() accessible: module java.base does not "opens java.util" to unnamed module @2f7c2f4f
	at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:354)
	at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:297)
	at java.base/java.lang.reflect.Constructor.checkCanSetAccessible(Constructor.java:188)
	at java.base/java.lang.reflect.Constructor.setAccessible(Constructor.java:181)
	at com.google.gson.internal.reflect.UnsafeReflectionAccessor.makeAccessible(UnsafeReflectionAccessor.java:44)
	at com.google.gson.internal.ConstructorConstructor.newDefaultConstructor(ConstructorConstructor.java:103)
	at com.google.gson.internal.ConstructorConstructor.get(ConstructorConstructor.java:85)
	at com.google.gson.internal.bind.MapTypeAdapterFactory.create(MapTypeAdapterFactory.java:127)
	at com.google.gson.Gson.getAdapter(Gson.java:489)
	at com.google.gson.Gson.toJson(Gson.java:727)
	at org.eclipse.glsp.graph.gson.PropertyBasedTypeAdapter.writeProperty(PropertyBasedTypeAdapter.java:221)
	at org.eclipse.glsp.graph.gson.PropertyBasedTypeAdapter.writeProperties(PropertyBasedTypeAdapter.java:202)
	at org.eclipse.glsp.graph.gson.PropertyBasedTypeAdapter.write(PropertyBasedTypeAdapter.java:185)
	at com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper.write(TypeAdapterRuntimeTypeWrapper.java:69)
	at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$1.write(ReflectiveTypeAdapterFactory.java:127)
	at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.write(ReflectiveTypeAdapterFactory.java:245)
	at com.google.gson.Gson.toJson(Gson.java:735)
	at org.eclipse.lsp4j.jsonrpc.json.adapters.MessageTypeAdapter.write(MessageTypeAdapter.java:434)
	at org.eclipse.lsp4j.jsonrpc.json.adapters.MessageTypeAdapter.write(MessageTypeAdapter.java:55)
	at com.google.gson.Gson.toJson(Gson.java:735)
	at com.google.gson.Gson.toJson(Gson.java:714)
	at org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler.serialize(MessageJsonHandler.java:145)
	at org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler.serialize(MessageJsonHandler.java:140)
	at org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer.consume(StreamMessageConsumer.java:59)
	at org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.notify(RemoteEndpoint.java:126)
	at org.eclipse.lsp4j.jsonrpc.services.EndpointProxy.invoke(EndpointProxy.java:88)
	at jdk.proxy2/jdk.proxy2.$Proxy38.process(Unknown Source)
	at org.eclipse.glsp.server.actions.ClientActionHandler.send(ClientActionHandler.java:64)
	at org.eclipse.glsp.server.actions.ClientActionHandler.execute(ClientActionHandler.java:58)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.runAction(DefaultActionDispatcher.java:193)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.handleAction(DefaultActionDispatcher.java:172)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.dispatch(DefaultActionDispatcher.java:101)
	at org.eclipse.glsp.server.actions.ActionDispatcher.lambda$dispatchAll$0(ActionDispatcher.java:51)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
	at org.eclipse.glsp.server.actions.ActionDispatcher.dispatchAll(ActionDispatcher.java:51)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.runAction(DefaultActionDispatcher.java:196)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.handleAction(DefaultActionDispatcher.java:172)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.handleNextAction(DefaultActionDispatcher.java:159)
	at org.eclipse.glsp.server.internal.actions.DefaultActionDispatcher.runThread(DefaultActionDispatcher.java:145)
	at java.base/java.lang.Thread.run(Thread.java:833)

	
After some debugging I figured out that this exception is caused by a SetEditValidationResultAction message. But I am still not able to avoid this action.

I tried to implement an additional handler but without any effect:

public class BPMNSetEditValidationResultActionHandler extends AbstractActionHandler<SetEditValidationResultAction> {

    private static Logger logger = Logger.getLogger(BPMNSetEditValidationResultActionHandler.class.getName());

    @Override
    protected List<Action> executeAction(final SetEditValidationResultAction actualAction) {
        // no more action - the GModel is up to date
        return none();
    }

}


See: https://github.com/eclipse-glsp/glsp/discussions/702
	