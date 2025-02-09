// @ts-check
require('reflect-metadata');

// Erase the ELECTRON_RUN_AS_NODE variable from the environment, else Electron apps started using Theia will pick it up.
if ('ELECTRON_RUN_AS_NODE' in process.env) {
    delete process.env.ELECTRON_RUN_AS_NODE;
}

const path = require('path');
process.env.THEIA_APP_PROJECT_PATH = path.resolve(__dirname, '..', '..')
const express = require('express');
const { Container } = require('inversify');
const { BackendApplication, BackendApplicationServer, CliManager } = require('@theia/core/lib/node');
const { backendApplicationModule } = require('@theia/core/lib/node/backend-application-module');
const { messagingBackendModule } = require('@theia/core/lib/node/messaging/messaging-backend-module');
const { loggerBackendModule } = require('@theia/core/lib/node/logger-backend-module');

const container = new Container();
container.load(backendApplicationModule);
container.load(messagingBackendModule);
container.load(loggerBackendModule);

function defaultServeStatic(app) {
    app.use(express.static(path.resolve(__dirname, '../../lib/frontend')))
}

function load(raw) {
    return Promise.resolve(raw).then(
        module => container.load(module.default)
    );
}

async function start(port, host, argv = process.argv) {
    if (!container.isBound(BackendApplicationServer)) {
        container.bind(BackendApplicationServer).toConstantValue({ configure: defaultServeStatic });
    }
    let result = undefined;
    await container.get(CliManager).initializeCli(argv.slice(2), 
        () => container.get(BackendApplication).configured,
        async () => {
            result = container.get(BackendApplication).start(port, host);
        });
    if (result) {
        return result;
    } else {
        return Promise.reject(0);
    }
}

module.exports = async (port, host, argv) => {
    try {
        await load(require('@theia/core/lib/node/i18n/i18n-backend-module'));
        await load(require('@theia/core/lib/node/hosting/backend-hosting-module'));
        await load(require('@theia/core/lib/node/request/backend-request-module'));
        await load(require('@theia/filesystem/lib/node/filesystem-backend-module'));
        await load(require('@theia/filesystem/lib/node/download/file-download-backend-module'));
        await load(require('@theia/workspace/lib/node/workspace-backend-module'));
        await load(require('@eclipse-glsp/theia-integration/lib/node/theia-integration-backend-module'));
        await load(require('@open-bpmn/open-bpmn-theia/lib/node/bpmn-backend-module'));
        await load(require('@theia/preferences/lib/node/preference-backend-module'));
        await load(require('@theia/process/lib/common/process-common-module'));
        await load(require('@theia/process/lib/node/process-backend-module'));
        await load(require('@theia/file-search/lib/node/file-search-backend-module'));
        await load(require('@theia/terminal/lib/node/terminal-backend-module'));
        return await start(port, host, argv);
    } catch (error) {
        if (typeof error !== 'number') {
            console.error('Failed to start the backend application:');
            console.error(error); 
            process.exitCode = 1;
        }
        throw error;
    }
}
