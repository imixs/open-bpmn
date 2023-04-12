# BPMN Extensions

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. It can be used by modeling tools to add non-standard elements or Artifacts to satisfy a specific need, such as the unique requirements of a vertical domain, and still have a valid BPMN Core.
A BPMN 2.0 Extension defines a `ExtensionDefinition` and a `ExtensionAttributeDefinition`. The latter defines a list of attributes that can be attached to any BPMN element. The attribute list defines the name and type of the new attribute. This allows BPMN adopters to integrate any meta model into the BPMN meta model and reuse already existing model elements.

**Open-BPMN** supports the extensibility mechanism of BPMN 2.0 by so called "BPMNExtension Points". An extension point allows adopters to add custom properties to any BPMN Element managed within the Open BPMN modeling tool. This extension mechanism is also used by Open-BPMN to support the BPMN Process Modeling Conformance level. In the following section you will learn how to implement custom BPMN Extensions and to extend the Open-BPMN modelling tool.

# Development

To implement a new extension point within Open-BPMN an adaptor has to implement a BPMNExtension and define a new Server Module to register the new Extension. Open-BPMN will automatically integrate tne BPMNExtension into the modeling life-cycle and manage to store the new attributes into the .bpmn model file.

## The BPMNExtension

A `BPMNExtension` is a Java interface that need to be implemented to define a new extension point within Open-BPMN .

As an adopter you implement a new `org.imixs.openbpmn.BPMNExtension` to describe custom properties in a separate schemata. The schemata are defined by the JSONForms project. This is a very flexible an easy to use extension mechanism.

The `BPMNExtension` defines the following methods:

### getNamespace

Unique identifier specifying the Extension namespace. The default namespace is 'bpmn2'. Implementations should overwrite this method.

```java
    @Override
    public String getNamespace() {
        return "foo";
    }
```

### getNamespaceURI

Unique target namespace URI

```java
   @Override
   public String getNamespaceURI() {
       return "http://www.foo.org/bpmn2";
   }
```

### getLabel

Returns the Extension label to be used in the Tool Palette

```java
    @Override
    public String getLabel() {
        return "My-Extension";
    }
```

### handlesElementTypeId

Returns true if the given ElementTypeID can be handled by this extension. This method is used to verify if a custom implementation of an extension can be applied to a BPMNModelElement.

```java
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return (BPMNTypes.TASK.equals(elementTypeId));
    }
```

### handlesBPMNElement

Validates whether the given {@link BPMNBaseElement} can be handled by this BPMN extension. The default implementation returns true. Implementations can accept only specific BPMN element types or elements containing specific BPMN 2.0 extensions.

```java
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        if (bpmnElement instanceof Activity) {
            Activity task = (Activity) bpmnElement;
            if (task.getType().equals(BPMNTypes.TASK)) {
                // next check the extension attribute imixs:username
                if (task.hasAttribute(getNamespace() + ":username")) {
                    return true;
                }
            }
        }
        return false;
    }
```

### getPriority

Returns the priority of this action handler. The priority is used to derive the execution order if multiple extension handlers should execute the same BPMNBaseElement. The default priority is `0` and the priority is sorted descending. This means handlers with a priority &gt; 0 are executed before handlers with a default priority and handlers with a priority >0 are executed afterwards.

### addExtension

Adds an extension to a given BPMN Element

```java
    public void addExtension(final BPMNElement bpmnElement) {
        if (bpmnElement instanceof Activity) {
            bpmnElement.setExtensionAttribute(getNamespace(), "username", "100");
        }
    }
```

### buildPropertiesForm

This Method is called to generate a JSON Forms Object when the element is loaded. The method adds the BPMNElement properties into a JSON Schema.

You can add a new data field by calling

```java
	builder.add("myField","someData")
             uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("username", "category"). //

     schemaBuilder.addProperty("username", "string", "Please enter your name");
```

This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the JsonForms

### updatePropertiesData

Updates the properties data provided by the modeling tool in the corresponding BPMN Element. The method receives the BPMNElement and a json object containing all new values. An extension can also update the given json object during this operation if needed.

## Register a BPMNExtension

To register a custom BPMNExtension you need to extend class `BPMNDiagramModule` and overwrite the method `configureBPMNExtensions`. In this method the new custom Extension Points can be registered:

```java
public class MyBPMNDiagramModule extends BPMNDiagramModule {

 public void configureBPMNExtensions(final Multibinder<BPMNExtension> binding) {
        // bind BPMN default extensions
        super.configureBPMNExtensions(binding);

        // Bind custom BPMNExtensions
        binding.addBinding().to(MyBPMNTaskExtension.class);
    }
}
```

Finally you implement a custom GLSP ServerLauncher to configure a new ServerModule:

```java
public final class ImixsBPMNServerLauncher {
    private MyBPMNServerLauncher() {
    }
    public static void main(final String[] args) {
        try {
            DefaultCLIParser cliParser = new DefaultCLIParser(args, "bpmn server");
            LaunchUtil.configure(cliParser);
            int port = cliParser.parsePort();
            ServerModule serverModule = new ServerModule().configureDiagramModule(new MyBPMNDiagramModule());
            GLSPServerLauncher launcher = new SocketGLSPServerLauncher(serverModule);
            launcher.start("localhost", port);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
```

After the new ServerModule is started Open-BPMN will add the Extension to the Tool-Palette to add the Extension to a BPMN Element. After an Element was extended, the Open-BPMN Property Section will automatically show a new Section to edit the extension attributes.

<img src="../images/open-bpmn-extension-01.png" />
