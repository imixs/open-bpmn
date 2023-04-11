# The Module System

In Open-BPMN we are using the ES Module System (ESM). The following sections contain information how to work with modules and how to setup new modules.


## Create a New Sub Module

Modules are located as sub directories in /open-bpmn.glsp-client/ . This is also called a MonoRepo:

	.
	├── open-bpmn.glsp-client
	│   ├── open-bpmn-app/
	│   ├── open-bpmn-glsp/
	│   ├── open-bpmn-theia/
	│   ├── sub1/
	│   │   ├── src
	│   │   │   ├── dummy1.tsx
	│   │   │   └── index.ts
	│   │   ├── package.json
	│   │   └── tsconfig.json		
	│   ├── package.json
	│   ├── tsconfig.json
	│   └── yarn.lock


In this example 'sub1' is a sub module within the MonoRepo 'open-bpmn.glsp-client'.


### The Parent package.json and tsconfig.json

To work with a sub module using `yarn` within a MonoRepo first add your new module to the 'workspaces' section in the parent `package.json` file:

	  ....
	  "workspaces": [
	    "open-bpmn-glsp",
	    "open-bpmn-theia",
	    "open-bpmn-app",
	    "sub1"
	  ],
	  ....

Next it is also necessary to add the module also into the 'include' section of the `tsconfig.json` file:

	  .....
	  "include": [
		"open-bpmn-glsp/src", 
		"open-bpmn-theia/src", 
		"open-bpmn-app",
		"sub1/src"
	   ]
	   ....

**Note:** In this case you have to add the /src/ sub folder containing your source files. 


### The Module package.json and tsconfig.json

Within the module the file `package.json` can be quite simple but should contain a @domain name and a version number:

	{
	  "private": "true",
	  "name": "@open-bpmn/sub1",
	  "version": "0.0.9",
	  "dependencies": {
	    "@eclipse-glsp/client": "0.9.0"
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
	  "main": "lib/index",
	  "types": "lib/index"
	}

The file `tsconfig.json` can look like this:

	{
	  "extends": "@eclipse-glsp/ts-config/tsconfig.json",
	  "compilerOptions": {
	    "rootDir": "src",
	    "outDir": "lib",
	    "baseUrl": ".",
	    "skipLibCheck": true
	  },
	  "include": ["src"]
	}

### The index.ts file

Beside the module sources your sub module must include the file `index.ts`. This file is refered in the `package.json` file and need to resolve the imports. At least this file exports all functionallity provided by the module source files:


````javascript
/********************************************************************************
 * LCopyright (c) 
 ********************************************************************************/
export * from './dummy1';
````


### The module code

The module source code is simply exporting some functionality:


````javascript
// export a function
export function log (message: string) {
	console.log(message);
}

// export a class
export class logger {
	name?: string;
	constructor (name: string) {
		this.name = name;
	}
	log (message: string) {
		console.log('[${this.name}] ${message}')
	}
}	
````


## Import from a Sub Module

To import functionality from your sub module now you can now add the module into the dependency section of your `package.json` file:

	  ....
	  "dependencies": {
	    ....
	    "@open-bpmn/sub1": "0.0.9",
	    ....
	  },


In your main module code you are now able to add a import declaration like this:

````javascript
import * as loggerModule from '@open-bpmn/sub1';
````	
	