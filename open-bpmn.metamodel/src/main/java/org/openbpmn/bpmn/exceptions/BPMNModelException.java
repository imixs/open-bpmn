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
 * BPMNModelException is the abstract super class for all BPMN
 * Exception classes. A BPMNModelException signals an error in the Model
 * logic. BPMNModelException need to be caught.
 * 
 * @author rsoika
 */
public abstract class BPMNModelException extends Exception {

    public static final String INVALID_MODEL = "INVALID_MODEL";

    private static final long serialVersionUID = 1L;

    protected String errorContext = "UNDEFINED";
    protected String errorCode = "UNDEFINED";

    public BPMNModelException(String aErrorCode, String message) {
        super(message);
        errorCode = aErrorCode;

    }

    public BPMNModelException(String aErrorContext, String aErrorCode, String message) {
        super(message);
        errorContext = aErrorContext;
        errorCode = aErrorCode;

    }

    public BPMNModelException(String aErrorContext, String aErrorCode, String message, Exception e) {
        super(message, e);
        errorContext = aErrorContext;
        errorCode = aErrorCode;

    }

    public BPMNModelException(String aErrorCode, String message, Exception e) {
        super(message, e);

        errorCode = aErrorCode;

    }

    public String getErrorContext() {
        return errorContext;
    }

    public void setErrorContext(String errorContext) {
        this.errorContext = errorContext;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
