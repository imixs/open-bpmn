// @ts-check
require('reflect-metadata');
const { Container } = require('inversify');

module.exports = Promise.resolve().then(() => {
    const { frontendApplicationModule } = require('@theia/core/lib/browser/frontend-application-module');
    const container = new Container();
    container.load(frontendApplicationModule);
    container.load(require('@theia/editor/lib/browser/editor-frontend-module').default);
    container.load(require('@theia/monaco/lib/browser/monaco-frontend-module').default);
    container.load(require('@theia/terminal/lib/browser/terminal-frontend-module').default);
});
