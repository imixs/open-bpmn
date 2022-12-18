/*  
 *  Imixs-Workflow 
 *  
 *  Copyright (C) 2001-2020 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *      https://www.imixs.org
 *      https://github.com/imixs/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 */

package org.openbpmn.bpmn.exceptions;

/**
 * The BPMNInvalidTypeException is thrown in case of the usage of an
 *  invalid or unknown BPMN Type.
 * 
 * @author rsoika
 */
public class BPMNInvalidTypeException extends BPMNModelException {

    public static final String INVALID_TYPE = "INVALID_TYPE";
    public static final String UNKNOWN_TYPE = "UNKNOWN_TYPE";

    private static final long serialVersionUID = 1L;

    public BPMNInvalidTypeException(String message) {
        super(INVALID_TYPE, message);
    }

    public BPMNInvalidTypeException(String aErrorCode, String message) {
        super(aErrorCode, message);
    }

    public BPMNInvalidTypeException(String aErrorCode, String message, Exception e) {
        super(aErrorCode, message, e);
    }

}
