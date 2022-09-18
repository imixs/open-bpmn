# BPMN Extensions

BPMN 2.0 introduces an extensibility mechanism that allows extending standard BPMN elements with additional properties and behavior. It can be used by modeling tools to add non-standard elements or Artifacts to satisfy a specific need, such as the unique requirements of a vertical domain, and still have a valid BPMN Core.

The `BPMNExtension` is the extension point which allows you to add custom properties to any BPMN Element managed within the Open BPMN modeling tool.An Extension defines the ExtensionDefinition and ExtensionAttributeDefinition. The latter defines a list of attributes that can be attached to any BPMN element. The attribute list defines the name and type of the new attribute. This allows BPMN adopters to integrate any meta model into the BPMN meta model and reuse  already existing model elements.

# Development

Open-BPMN supports the extensibility mechanism by so called "BPMNExtensions". A `BPMNExtension` is a Java interface that can be implemented to extend Open-BPMN with additional functionality. This extension mechanism is also used by Open-BPMN to support the BPMN Process Modeling Conformance level.

As an adopter you can implement a new BPMNExtension and describe your properties in a separate schemata. The schemata are defined by the JSONForms project. This is a very flexible an easy to use extension mechanism.

## The BPMNExtension

The `BPMNExtension` defines the following methods:

### getNamespace

Unique identifier specifying the Extension namespace. The default namespace is 'bpmn2'. Implementations should overwrite this method.

### getNamespaceURI

Unique target namespace URI

### getLabel
   
Returns the Extension label to be used in the Tool Palette

### handlesElementTypeId

Returns true if the given ElementTypeID can be handled by this extension. This method is used to verify if a custom implementation of an extension can be applied to a BPMNModelElement.

### handlesBPMNElement

Validates whether the given {@link BPMNBaseElement} can be handled by this BPMN extension. The default implementation returns true. Implementations can accept only specific BPMN element types or elements containing specific BPMN 2.0 extensions.

### getPriority

Returns the priority of this action handler. The priority is used to derive the execution order if multiple extension handlers should execute the same  BPMNBaseElement. The default priority is `0` and the priority is sorted descending. This means handlers with a priority &gt; 0 are executed before handlers with a default priority and handlers with a priority >0 are executed afterwards.

### addExtension

Adds an extension to a given BPMN Element

### buildPropertiesForm

This Method is called to generate a JSON Forms Object when the element is loaded. The method adds the BPMNElement properties into a JSON Schema.

You can add a new data field by calling

```java
	builder.add("myField","someData")
             uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
    
     schemaBuilder.addProperty("name", "string", "Please enter your name");
```

This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the JsonForms


### updatePropertiesData

Updates the properties data provided by the modeling tool in the corresponding BPMN Element. The method receives the BPMNElement and a json object containing all new values. An extension can also update the given json object during this operation if needed. 


## Register a BPMNExtension

To register a BPMNExtension the method `configureBPMNExtensions` of the `BPMNDiagramModule` can be overwritten. In this method you can register new custom Extension:

```java
    public void configureBPMNExtensions(final Multibinder<BPMNExtension> binding) {
        // bind BPMN default extensions
        super.configureBPMNExtensions(binding);

        // register custom Extensions 
        binding.addBinding().to(MyBPMNTaskExtension.class);
    }
```
