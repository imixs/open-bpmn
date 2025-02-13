# Guide to Implementing a GLSP Server Launch Configuration

This guide provides information about how to implement a server launch configuration for a GLSP (Graphical Language Server Platform) contribution. The example and the concepts can be applied to any GLSP-based server.

## 1. Overview

The GLSPServerContribution interface is responsible for launching or connecting to a GLSP server. The BaseGLSPServerContribution class provides a base implementation with utility methods for managing server processes and client connections. To create a custom server launch configuration, you need to extend this class and implement the required methods.

### Build and Start Scripts

The build and start process for the GLSP server is managed using Yarn scripts defined in the package.json file. These scripts simplify the development workflow by automating the build and launch of the application. The build script compiles the application in development mode, while the start and start:external scripts launch the application with specific configurations. The start:external script includes the --glspDebug flag, which enables debugging mode and prevents the automatic launch of the server, assuming it is already running externally. This setup is particularly useful during development and debugging.

```
...
  "scripts": {
    "build": "theia build --mode development",
    "start": "theia start --GLSP_PORT=5007 --root-dir=../workspace",
    "start:external": "theia start --GLSP_PORT=5007 --root-dir=../workspace --loglevel=debug --glspDebug"
  },
...
```

## 2. Key Components

### 2.1 GLSPServerContribution Interface

The GLSPServerContribution interface defines the core responsibilities of a GLSP server contribution:

    connect(clientChannel: Channel): Establishes a connection between the client and the GLSP server.

    launch(): (Optional) Launches an embedded GLSP server.

    options: Configuration options for the server contribution.

### 2.2 GLSPServerContributionOptions

This interface defines the configuration options for a server contribution:

- **launchOnDemand:** If true, the server is launched only when needed (e.g., when a widget is opened). If false, it is launched on application start.
- **launchedExternally:** If true, the server is expected to be already running (e.g., for debugging purposes). If false, the server will be launched by the contribution.

### 2.3 BaseGLSPServerContribution Class

This abstract class provides a base implementation for GLSPServerContribution. It includes utility methods for:

- Spawning server processes (spawnProcessAsync).
- Handling process errors (onDidFailSpawnProcess).
- Logging server output (processLogInfo, processLogError).
- Managing disposable resources (dispose).

## 3. Implementing a Custom Server Contribution

### 3.1 Extending GLSPSocketServerContribution

To create a custom server contribution, extend the GLSPSocketServerContribution class (which itself extends BaseGLSPServerContribution). This class is specifically designed for socket-based server connections.
Example: My Server Contribution
typescript
Copy

```javascript
import {
    getPort,
    GLSPSocketServerContribution,
    GLSPSocketServerContributionOptions
} from '@eclipse-glsp/theia-integration/lib/node';
import { injectable } from '@theia/core/shared/inversify';
import { join, resolve } from 'path';
import { MyLanguage } from '../common/My-language';

export const DEFAULT_PORT = 0;
export const PORT_ARG_KEY = 'GLSP_PORT';
export const LOG_DIR = join(__dirname, '..', '..', 'logs');
const JAR_FILE = resolve(
    join(__dirname, '..', '..', '..', '..', 'My.glsp-server', 'target', 'My.server-1.0-glsp.jar')
);

@injectable()
export class MyGLSPSocketServerContribution extends GLSPSocketServerContribution {
    readonly id = MyLanguage.contributionId;

    createContributionOptions(): Partial<GLSPSocketServerContributionOptions> {
        console.log('├── LOG_DIR = ' + LOG_DIR);
        console.log('├── PORT = ' + getPort(PORT_ARG_KEY, DEFAULT_PORT));
        return {
            executable: JAR_FILE,
            additionalArgs: ['--consoleLog', 'false', '--fileLog', 'true', '--logDir', LOG_DIR],
            socketConnectionOptions: {
                port: getPort(PORT_ARG_KEY, DEFAULT_PORT)
            }
        };
    }

    protected override processLogInfo(line: string): void {
        super.processLogInfo(line);
        console.info(`${this.id}: ${line}`);
    }
}
```

### 3.2 Key Implementation Details

**id Property:**

The id property uniquely identifies the server contribution. It should match the contribution ID defined in your language configuration.

**createContributionOptions Method:**

This method returns the configuration options for the server contribution.

- `executable`: Path to the server executable (e.g., a JAR file).
- `additionalArgs`: Additional command-line arguments for the server process.
- `socketConnectionOptions`: Configuration for the socket connection, including the port.

**processLogInfo Method:**

        Override this method to customize how server logs are processed and displayed.

## 4. Understanding launchedExternally

The launchedExternally flag in GLSPServerContributionOptions determines whether the server is launched by the contribution or is expected to be already running:

- If `launchedExternally` is true, the launch() method is not called, and the contribution attempts to connect to an already running server.
- If `launchedExternally` is false, the launch() method is called to start the server.

By default, launchedExternally is set to true unless the --glspDebug flag is passed when starting the application.

## 5. Steps to Build a Launch Configuration

- **Define the Server Contribution:** Extend GLSPSocketServerContribution and implement the required methods (createContributionOptions, processLogInfo, etc.).

- **Configure the Server Options:** Use the createContributionOptions method to specify the server executable, command-line arguments, and socket connection details.

- **Handle Logging:** Override processLogInfo and processLogError to customize how server logs are processed.

- **Set the launchedExternally Flag:** Decide whether the server should be launched by the contribution or connected to an existing server.

- **Register the Contribution:** Use dependency injection (e.g., @injectable) to register the contribution in your application.

## 6. Example Configuration

### 6.1 Server Contribution

```javascript
@injectable()
export class MyGLSPServerContribution extends GLSPSocketServerContribution {
    readonly id = 'my-language-id';

    createContributionOptions(): Partial<GLSPSocketServerContributionOptions> {
        return {
            executable: '/path/to/server/executable',
            additionalArgs: ['--arg1', 'value1', '--arg2', 'value2'],
            socketConnectionOptions: {
                port: 5007
            }
        };
    }

    protected override processLogInfo(line: string): void {
        console.info(`[MyServer] ${line}`);
    }
}
```

### 6.2 Launch Options

- **Launch on Demand:** Set launchOnDemand: true if the server should start only when needed.
- **External Launch:** Set launchedExternally: true if the server is already running.

## 7. Debugging Tips

- Use the --glspDebug flag to enable debugging mode.
- Check the server logs for errors or issues.
- Ensure the server executable is correctly specified and accessible.

By following this guide, you can create a custom GLSP server launch configuration tailored to your specific language and application requirements.
