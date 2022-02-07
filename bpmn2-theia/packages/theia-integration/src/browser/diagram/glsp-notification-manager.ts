/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
import { Message, MessageOptions } from '@theia/core/lib/common';
import { NotificationManager } from '@theia/messages/lib/browser/notifications-manager';
import { injectable } from 'inversify';
import { Md5 } from 'ts-md5';

/**
 *  A customized notification manager that can properly handle GLSP messages.
 *  For GLSP messages the file uri needs to be considered when generating the message id
 *  to derive the editor widget for which this message is intended.
 */
@injectable()
export class GLSPNotificationManager extends NotificationManager {
    public getMessageId(message: Message): string {
        const options = message.options;
        if (isGLSPMessageOptions(options)) {
            return String(Md5.hashStr(`[${message.type} @ ${options.uri}] ${message.text} : ${(message.actions || []).join(' | ')};`));
        }
        return super.getMessageId(message);
    }
}

export interface GLSPMessageOptions extends MessageOptions {
    uri: string;
}

function isGLSPMessageOptions(options?: MessageOptions): options is GLSPMessageOptions {
    return options !== undefined && 'uri' in options;
}
