/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.openbpmn.glsp.elements.event.edit;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.openbpmn.glsp.graph.EventNode;
import org.openbpmn.glsp.utils.ModelTypes;

import com.google.inject.Inject;

/**
 * The ApplyEventEditOperationHandler is responsible for processing the
 * ApplyEventEditOperation send by the client and updates the model
 * representation accordingly.
 * <p>
 * Finally the OperationHandler sends out a EventEditOperation
 *
 */
public class ApplyEventUpdateOperationHandler extends AbstractOperationHandler<ApplyEventUpdateOperation> {
    private static Logger logger = Logger.getLogger(ApplyEventUpdateOperationHandler.class.getName());

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected GModelState modelState;

    /**
     *
     */
    @Override
    protected void executeOperation(final ApplyEventUpdateOperation operation) {
        long l = System.currentTimeMillis();
        logger.info("....execute UpdateEvent Operation id: " + operation.getId());
        String jsonData = operation.getJsonData();
        logger.info("....expression= " + jsonData);

        Optional<EventNode> element = modelState.getIndex().findElementByClass(operation.getId(), EventNode.class);
        if (element.isEmpty()) {
            throw new RuntimeException("Cannot find element with id '" + operation.getId() + "'");
        }

        // parse json....
        JsonObject json = null;

        try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
            json = reader.readObject();
        } catch (JsonException e) {
            throw new RuntimeException("Cannot read json data : " + e.getMessage());
        }

        Set<String> features = json.keySet();
        String value = null;
        Method[] methods = EventNode.class.getMethods();
        for (String feature : features) {

            logger.fine("...update feature = " + feature);
            if ("name".equals(feature)) {
                value = json.getString(feature);
                // in case the name was updated, we update the LABEL_HEADING
                // from the element. This will automatically update the name
                // via the EventEditValidator
                // element.get().setName(value);
                logger.fine("....update LABLE_HEANDING..");
                // Update also the text of the GLabel element
                EList<GModelElement> childs = element.get().getChildren();
                for (GModelElement child : childs) {
                    if (ModelTypes.LABEL_HEADING.equals(child.getType())) {
                        GLabel gLabel = (GLabel) child;
                        gLabel.setText(value);
                        break;
                    }
                }
                continue;
            }

            // In the following we use the Java reflection API to check if the given feature
            // has a setter method.
            Method method = ApplyEventUpdateOperationHandler.findSetter(methods, feature);
            if (method != null) {
                // the feature has a corresponding setter method
                try {
                    value = json.getString(feature);
                    // invoke the setter method with the value from the json object
                    method.invoke(element.get(), value);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException("Cannot set feature '" + feature + "' : " + e.getMessage());
                }

            }

        }

        logger.info("....execute UpdateEvent took " + (System.currentTimeMillis() - l) + "ms");

    }

    /**
     * Returns true if the given method is setter method with a String parameter.
     *
     * @param methods
     * @param feature
     * @return true if setter method
     */
    public static Method findSetter(final Method[] methods, final String feature) {
        // iterate over all methods and test if the method is a setter
        for (Method method : methods) {

            if (method.getName().equalsIgnoreCase("set" + feature) && Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 1
                    && method.getParameters()[0].getType().equals(String.class)) {
                return method;
            }

        }
        return null;
    }

}
