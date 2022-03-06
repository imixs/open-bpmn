# How to add you own Theia Extension

You can extend the application by adding additional theia extensions. To to this follow this short guideline.

## 1. Create a new node.js module

First you need to create a new empty node.js module in a separate folder located under /open-bpmn.glsp-client. The module should have the following file structure:


	.
	├── package.json
	├── src
	│   └── browser
	│       ├── custom-module.ts
	│       └── custom-widget.tsx
	└── tsconfig.json

The example code used here is aligned to the [Theia getting-started-extension](https://github.com/vince-fugnitto/theia-custom-getting-started)

**package.json**

```
{
  "private": "true",
  "name": "@open-bpmn/open-bpmn-welcome-page",
  "version": "0.0.6",
  "description": "GLSP sprotty diagrams for the BPMN DSL",
  "license": "(GPL-3.0)",
  "keywords": [
    "glsp",
    "bpmn",
    "diagram"
  ],
  "dependencies": {
    "@theia/getting-started": "1.20.0",
    "@eclipse-glsp/client": "0.9.0",
    "balloon-css": "^0.5.0"
  },
  "devDependencies": {
    "@eclipse-glsp/config": "0.9.0",
    "rimraf": "^2.6.1",
    "typescript": "^3.9.2"
  },
  "scripts": {
    "prepare": "yarn clean && yarn build && yarn lint",
    "clean": "rimraf lib",
    "build": "tsc",
    "lint": "eslint -c ../.eslintrc.js --ext .ts,.tsx ./src",
    "watch": "tsc -w"
  },
  "files": [
    "lib",
    "src",
    "build",
    "css"
  ],
  "theiaExtensions": [
    {
      "frontend": "lib/browser/custom-getting-started-frontend-module"
    }
  ]
}
```

**tsconfig.json**

```
{
  "extends": "@eclipse-glsp/ts-config/tsconfig.json",
  "compilerOptions": {
    "rootDir": "src",
    "outDir": "lib",
    "baseUrl": ".",
    "skipLibCheck": true,
    "types": []
  },
  "include": ["src"]
}
```


Finally make sure that you add the new module to your root package.json file workspaces section:

	....
	  "workspaces": [
	    "open-bpmn-glsp",
	    "open-bpmn-theia",
	    "open-bpmn-app",
	    "open-bpmn-welcome-page"
	  ],
	....
  
  

Find also more examples [here](https://theia-ide.org/docs/authoring_extensions).
