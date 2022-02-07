// @ts-check
require('es6-promise/auto');
require('reflect-metadata');
require('setimmediate');
const { Container } = require('inversify');
const { FrontendApplicationConfigProvider } = require('@theia/core/lib/browser/frontend-application-config-provider');

FrontendApplicationConfigProvider.set({
    "applicationName": "Eclipse Theia",
    "defaultTheme": "dark",
    "defaultIconTheme": "none",
    "electron": {
        "disallowReloadKeybinding": false,
        "windowOptions": {}
    }
});

const { ThemeService } = require('@theia/core/lib/browser/theming');
ThemeService.get().loadUserTheme();

const nls = require('@theia/core/lib/browser/nls-loader');

// nls translations MUST be loaded before requiring any code that uses them
module.exports = nls.loadTranslations().then(() => {
    const { FrontendApplication } = require('@theia/core/lib/browser');
    const { frontendApplicationModule } = require('@theia/core/lib/browser/frontend-application-module');
    const { messagingFrontendModule } = require('@theia/core/lib/browser/messaging/messaging-frontend-module');
    const { loggerFrontendModule } = require('@theia/core/lib/browser/logger-frontend-module');

    const container = new Container();
    container.load(frontendApplicationModule);
    container.load(messagingFrontendModule);
    container.load(loggerFrontendModule);

    return Promise.resolve()
    .then(function () { return import('@theia/core/lib/browser/i18n/i18n-frontend-module').then(load) })
    .then(function () { return import('@theia/core/lib/browser/menu/browser-menu-module').then(load) })
    .then(function () { return import('@theia/core/lib/browser/window/browser-window-module').then(load) })
    .then(function () { return import('@theia/core/lib/browser/keyboard/browser-keyboard-module').then(load) })
    .then(function () { return import('@theia/messages/lib/browser/messages-frontend-module').then(load) })
    .then(function () { return import('@theia/variable-resolver/lib/browser/variable-resolver-frontend-module').then(load) })
    .then(function () { return import('@theia/editor/lib/browser/editor-frontend-module').then(load) })
    .then(function () { return import('@theia/filesystem/lib/browser/filesystem-frontend-module').then(load) })
    .then(function () { return import('@theia/filesystem/lib/browser/download/file-download-frontend-module').then(load) })
    .then(function () { return import('@theia/filesystem/lib/browser/file-dialog/file-dialog-module').then(load) })
    .then(function () { return import('@theia/workspace/lib/browser/workspace-frontend-module').then(load) })
    .then(function () { return import('@theia/navigator/lib/browser/navigator-frontend-module').then(load) })
    .then(function () { return import('@theia/markers/lib/browser/problem/problem-frontend-module').then(load) })
    .then(function () { return import('@theia/outline-view/lib/browser/outline-view-frontend-module').then(load) })
    .then(function () { return import('@theia/monaco/lib/browser/monaco-browser-module').then(load) })
    .then(function () { return import('sprotty-theia/lib/theia/diagram-module').then(load) })
    .then(function () { return import('@eclipse-glsp/theia-integration/lib/browser/theia-integration-frontend-module').then(load) })
    .then(function () { return import('@bpmn2-theia/workflow-theia/lib/browser/workflow-frontend-module').then(load) })
    .then(function () { return import('@theia/userstorage/lib/browser/user-storage-frontend-module').then(load) })
    .then(function () { return import('@theia/preferences/lib/browser/preference-frontend-module').then(load) })
    .then(function () { return import('@theia/process/lib/common/process-common-module').then(load) })
    .then(function () { return import('@theia/terminal/lib/browser/terminal-frontend-module').then(load) })
        .then(start).catch(reason => {
            console.error('Failed to start the frontend application.');
            if (reason) {
                console.error(reason);
            }
        });

    function load(jsModule) {
        return Promise.resolve(jsModule.default)
            .then(containerModule => container.load(containerModule));
    }

    function start() {
        (window['theia'] = window['theia'] || {}).container = container;
        return container.get(FrontendApplication).start();
    }
});
