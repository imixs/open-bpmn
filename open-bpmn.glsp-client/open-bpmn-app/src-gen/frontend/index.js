// @ts-check
require('es6-promise/auto');
require('reflect-metadata');
require('setimmediate');
const { Container } = require('inversify');
const { FrontendApplicationConfigProvider } = require('@theia/core/lib/browser/frontend-application-config-provider');

FrontendApplicationConfigProvider.set({
    "applicationName": "Eclipse Theia",
    "defaultTheme": {
        "light": "light",
        "dark": "dark"
    },
    "defaultIconTheme": "theia-file-icons",
    "electron": {
        "windowOptions": {},
        "showWindowEarly": true
    },
    "defaultLocale": "",
    "validatePreferencesSchema": true
});


self.MonacoEnvironment = {
    getWorkerUrl: function (moduleId, label) {
        return './editor.worker.js';
    }
}

function load(container, jsModule) {
    return Promise.resolve(jsModule)
        .then(containerModule => container.load(containerModule.default));
}

async function preload(parent) {
    const container = new Container();
    container.parent = parent;
    try {
        await load(container, import('@theia/core/lib/browser/preload/preload-module'));
        const { Preloader } = require('@theia/core/lib/browser/preload/preloader');
        const preloader = container.get(Preloader);
        await preloader.initialize();
    } catch (reason) {
        console.error('Failed to run preload scripts.');
        if (reason) {
            console.error(reason);
        }
    }
}

module.exports = (async () => {
    const { messagingFrontendModule } = require('@theia/core/lib/browser/messaging/messaging-frontend-module');
    const container = new Container();
    container.load(messagingFrontendModule);
    await preload(container);
    const { FrontendApplication } = require('@theia/core/lib/browser');
    const { frontendApplicationModule } = require('@theia/core/lib/browser/frontend-application-module');    
    const { loggerFrontendModule } = require('@theia/core/lib/browser/logger-frontend-module');

    container.load(frontendApplicationModule);
    container.load(loggerFrontendModule);

    try {
        await load(container, import('@theia/core/lib/browser/i18n/i18n-frontend-module'));
        await load(container, import('@theia/core/lib/browser/menu/browser-menu-module'));
        await load(container, import('@theia/core/lib/browser/window/browser-window-module'));
        await load(container, import('@theia/core/lib/browser/keyboard/browser-keyboard-module'));
        await load(container, import('@theia/core/lib/browser/request/browser-request-module'));
        await load(container, import('@theia/filesystem/lib/browser/filesystem-frontend-module'));
        await load(container, import('@theia/filesystem/lib/browser/download/file-download-frontend-module'));
        await load(container, import('@theia/filesystem/lib/browser/file-dialog/file-dialog-module'));
        await load(container, import('@theia/messages/lib/browser/messages-frontend-module'));
        await load(container, import('@theia/variable-resolver/lib/browser/variable-resolver-frontend-module'));
        await load(container, import('@theia/editor/lib/browser/editor-frontend-module'));
        await load(container, import('@theia/workspace/lib/browser/workspace-frontend-module'));
        await load(container, import('@theia/markers/lib/browser/problem/problem-frontend-module'));
        await load(container, import('@theia/outline-view/lib/browser/outline-view-frontend-module'));
        await load(container, import('@theia/monaco/lib/browser/monaco-frontend-module'));
        await load(container, import('@eclipse-glsp/theia-integration/lib/browser/theia-integration-frontend-module'));
        await load(container, import('@open-bpmn/open-bpmn-theia/lib/browser/bpmn-frontend-module'));
        await load(container, import('@theia/navigator/lib/browser/navigator-frontend-module'));
        await load(container, import('@theia/userstorage/lib/browser/user-storage-frontend-module'));
        await load(container, import('@theia/preferences/lib/browser/preference-frontend-module'));
        await load(container, import('@theia/process/lib/common/process-common-module'));
        await load(container, import('@theia/terminal/lib/browser/terminal-frontend-module'));
        await start();
    } catch (reason) {
        console.error('Failed to start the frontend application.');
        if (reason) {
            console.error(reason);
        }
    }

    function start() {
        (window['theia'] = window['theia'] || {}).container = container;
        return container.get(FrontendApplication).start();
    }
})();
