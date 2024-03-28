# VS-Code

To run Open-BPMN in Visual Studio Code you can install the [Open BPMN Extension](https://marketplace.visualstudio.com/items?itemName=open-bpmn.open-bpmn-vscode-extension). Just go to 'Extensions' and search for 'Open-BPMN'

Open-BPMN for VS-Code is based on JDK 17. Make sure that you have installed Visual Studio Code and Java version 17 or a higher

To install Java 17 or a higher version, you can download it from the [Microsoft Download Page](https://learn.microsoft.com/en-us/java/openjdk/download) or you can install it from your operating system's package manager. If you have any questions you will find help in the [Open-BPMN Discussion Forum](https://github.com/imixs/open-bpmn/discussions).

<img src="./images/vscode-integration-install.png" width="800" />

## Compare .bpmn files with Text-Diff Editor

To compare different versions of .bpmn files in the text-diff editor of VS-Code, you can overwrite the editorAssociations in the workbench settings.

1. Open Settings ( Ctrl + , ) and search for editorAssociations
2. You will see the .bpmn associations where you can add a new option for Git:’

```
Item = {git,gitlens}:/**/*.{bpmn,bpmn2}
Value = default
```

<img src="./images/vscode-bpmn-diff-editor-768x322.png" width="800" />


Now BPMN files will always be opened in a git file compare with text editor per default


<img src="./images/vscode-bpmn-diff-editor-compare-view.png" width="800" />