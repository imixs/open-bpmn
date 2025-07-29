# XSD Validation

Download XSD files

```
wget https://www.omg.org/spec/BPMN/20100501/BPMN20.xsd
wget https://www.omg.org/spec/BPMN/20100501/BPMNDI.xsd
wget https://www.omg.org/spec/BPMN/20100501/DC.xsd
wget https://www.omg.org/spec/BPMN/20100501/DI.xsd
wget https://www.omg.org/spec/BPMN/20100501/Semantic.xsd
```

Install `xmlstarlet`

```
$ sudo apt install xmlstarlet
```

Validate with

```
xmlstarlet validate -e --xsd BPMN20.xsd A.1.0-roundtrip.bpmn
```

# Test Result

```
A.1.0-roundtrip.bpmn:12.57:
Element '{http://www.omg.org/spec/BPMN/20100524/MODEL}documentation':
This element is not expected.
Expected is one of (
    {http://www.omg.org/spec/BPMN/20100524/MODEL}outgoing,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}property,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}dataOutput,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}dataOutputAssociation,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}outputSet,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}eventDefinition,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}cancelEventDefinition,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}compensateEventDefinition,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}conditionalEventDefinition,
    {http://www.omg.org/spec/BPMN/20100524/MODEL}errorEventDefinition ).
```
