# How to Create a BPMN Model

BPMN 2.0 allows you to visually model and represent complex business processes in a standardized and intuitive way. It provides a set of symbols and notations that can be used to define process flows, activities, gateways, events, and more. By using BPMN, you can create diagrams that effectively communicate the structure and behavior of a business process in a standardized way.

The following section will provide you a short tutorial on how to model a business process using BPMN 2.0:

## Before You Start

Before you start modeling, try gain a clear understanding of your business process. Identify the activities, decisions, events, and participants involved in the process. Even as BPMN helps you to evolve your business process during the modelling process, it is good practice to have a clear understanding about the sequence and dependencies between these elements at the beginning.

Identify the Start and End Points of your process. The start event represents the initiation of the process, and the end event represents its completion.
The completion of a business process means that the its general business goal has been achieved.

<img src="./images/howto/img-01.png" />

Now you can begin modeling the flow of the process by using tasks, events, and gateways to represent the sequence of activities and decisions. Sequence flows are used to define the order of the execution. Sequence Flows are represented by arrows indicating the flow direction.

### Activities

Activities are the most important elements. Activities identify the individual tasks within a process. The Task elements represent these activities. Each task should have a meaningful and descriptive label. You can also differentiate between different types of tasks, such as user tasks, service tasks, or script tasks, based on the nature of the work being performed.

<img src="./images/howto/img-02.png" />

### Gateways

Decision Points identify points where the flow of the process can branch based on certain conditions. Decision points are typically represented by a Gateway symbol. Depending on the nature of the decision, Gateways can have different types, such as an exclusive gateway (XOR), inclusive gateway (OR), or parallel gateway (AND).

<img src="./images/howto/img-03.png" />

### Events

Events represent in a BPMN model something that happens at a specific point in time, such as the start or completion of a task, a timer, a message received, or an error. Beside the start and end event you can use intermediate events to define such a situation.

<img src="./images/howto/img-04.png" />

## Create a New Model

To create a new BPMN model in Open-BPMN you just need to create a new file with the suffix '.bpmn' in your workspace.

<img src="./images/howto/img-05.png" />

Open-BPMN will automatically initialize your new model and opens the diagram plane with the tool palette. You can pick new elements from the tool palette and add them to your diagram. You can use the Sequence Flow to connect elements.

<img src="./images/howto/img-06.png" />

### The Property Panel

From the bottom of the Diagram plane you can open the Property Panel. The property Panel shows additional elements of the selected diagram element.

<img src="./images/howto/img-07.png" />

## Shortcuts and The Command Panel

From the main menu you can choose different commands to adjust your model. Some helpful short cuts can also be executed by the keyboard:

    Alt + F   -   Fits the diagram to the current screen size
    Alt + C   -   Center the diagram or the selected element
    Alt + E   -   Export the diagram a an SVG graphic

**Note:** The keyboard shortcuts may differ depending on the IDE you are using.
