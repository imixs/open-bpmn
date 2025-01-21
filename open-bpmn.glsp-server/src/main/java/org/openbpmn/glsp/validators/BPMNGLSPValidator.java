/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
package org.openbpmn.glsp.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.MarkerKind;
import org.eclipse.glsp.server.features.validation.MarkersReason;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.BPMNValidationHandler;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;
import org.openbpmn.bpmn.validation.BPMNValidationMarker.ErrorType;
import org.openbpmn.extensions.BPMNElementExtension;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The BPMNGLSPValidator is used to validate the complete model. It is
 * triggered when the GLSP client starts 'Validate model' action.
 * <p>
 * The Result of a validation can be verified in the 'Problems View' from Theia.
 * <p>
 * The Validation framework form GLSP distinguishes between two modes. LIVE Mode
 * (during modeling) and BATCH mode (only called from the validation icon in
 * the Tool palette)
 * 
 * In Case of LIVE-Validation mode the BPMNModelValidator simply calls the
 * BPMNModel.validate() method which
 * returns a List of all found BPMNValidationError objects from the Open-BPMN
 * Meta model.
 *
 * In Case of BATCH-Validation mode the BPMNModelValidator tests if an extension
 * provides a validation method and calls this one. The Extension validation can
 * be much more complex and does not validate the Meta-Model rules. This is a
 * good point for Adopters to add more complex validation rules into the
 * modelling tool.
 * 
 * This List of validation rules is finally translated into GLSP Marker list.
 * 
 *
 * @see: https://www.eclipse.org/glsp/documentation/validation/
 * @author rsoika
 *
 */
public class BPMNGLSPValidator implements ModelValidator {
    private static Logger logger = Logger.getLogger(BPMNGLSPValidator.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNElementExtension> extensions;

    private List<BPMNValidationMarker> bpmnMarkers = null;;

    /**
     * The method validates a model depending on the validation mode (LIVE|BATCH).
     */
    @Override
    public List<Marker> validate(final List<GModelElement> elements, final String reason) {
        long l = System.currentTimeMillis();
        try {
            beforeValidate(elements, reason);
        } catch (BPMNModelException e) {
            logger.warning("Failed to validate model: " + e.getMessage());
            e.printStackTrace();
        }
        List<Marker> markers = doValidate(elements, reason);
        afterValidate(elements, reason);
        return markers;
    }

    /**
     * This method validates a given BPMNModel. It returns a list of
     * validationErrors that contains a lable and a short description pointing to an
     * element that causes a problem.
     * 
     * This method calls the Meta Model Validation method depending on the
     * validation mode (LIVE|BATCH).
     * 
     * Finally it converts the Meta Model Error markers into GLSP marker objects.
     * 
     * @throws BPMNModelException
     */
    public void beforeValidate(final List<GModelElement> elements, final String reason) throws BPMNModelException {

        if (MarkersReason.LIVE.equals(reason)) {
            // simple case we only call the validate method from the meta model.
            bpmnMarkers = new BPMNValidationHandler().validate(modelState.getBpmnModel(), false);
        }
        if (MarkersReason.BATCH.equals(reason)) {
            // no op in BATCH mode. Elements will be validated in doBatchValidation only
        }
    }

    /**
     * Called after validation.
     * 
     * @param elements
     * @param reason
     */
    public void afterValidate(final List<GModelElement> elements, final String reason) {
        // no op
    }

    /**
     * The method simply converts the BPMNMarkers into GLSP Marker objects.
     * 
     * @param elements
     * @param reason
     * @return
     */
    private List<Marker> doValidate(final List<GModelElement> elements, final String reason) {
        List<Marker> markers = new ArrayList<>();

        for (GModelElement element : elements) {
            if (MarkersReason.LIVE.equals(reason)) {
                markers.addAll(doLiveValidation(element));
            } else if (MarkersReason.BATCH.equals(reason)) {
                markers.addAll(doBatchValidation(element));
            } else {
                markers.addAll(doValidationForCustomReason(element, reason));
            }
            if (!element.getChildren().isEmpty()) {
                markers.addAll(doValidate(element.getChildren(), reason));
            }
        }
        return markers;
    }

    /**
     * Calls for a given Element all available Extension Validation rules. An
     * Extension returns a list of BPMNValidationMarker objects.
     * The method convert the list and returns a list of GLSP Makers.
     */
    @Override
    public List<Marker> doBatchValidation(final GModelElement element) {
        ArrayList<BPMNValidationMarker> bpmnExtensionMarkers = new ArrayList<BPMNValidationMarker>();
        BPMNElementNode bpmnElement = modelState.getBpmnModel().findElementNodeById(element.getId());
        // Apply all registered extensions to this element
        if (bpmnElement != null && extensions != null) {
            for (BPMNElementExtension extension : extensions) {
                // validate if the extension can handle this BPMN element
                if (extension.handlesBPMNElement(bpmnElement)) {
                    List<BPMNValidationMarker> extensionMarkers = extension.validate(bpmnElement);
                    if (extensionMarkers != null) {
                        bpmnExtensionMarkers.addAll(extensionMarkers);
                    }
                }
            }
        }
        // Convert BPMN Markers into GLSP Markers
        List<Marker> result = new ArrayList<>();
        for (BPMNValidationMarker bpmnMarker : bpmnExtensionMarkers) {
            if (bpmnMarker.getElementId().equals(element.getId())) {
                if (bpmnMarker.getErrorType() == ErrorType.ERROR) {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.ERROR));
                } else if (bpmnMarker.getErrorType() == ErrorType.WARNING) {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.WARNING));
                } else {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.INFO));
                }
            }
        }

        return result;
    }

    /**
     * For Live validation we simply look for matching markers in the
     * bpmnMarkersList
     */
    @Override
    public List<Marker> doLiveValidation(GModelElement element) {

        List<Marker> result = new ArrayList<>();
        for (BPMNValidationMarker bpmnMarker : bpmnMarkers) {
            if (bpmnMarker.getElementId().equals(element.getId())) {
                if (bpmnMarker.getErrorType() == ErrorType.ERROR) {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.ERROR));
                } else if (bpmnMarker.getErrorType() == ErrorType.WARNING) {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.WARNING));
                } else {
                    result.add(new Marker(bpmnMarker.getLabel(), bpmnMarker.getDescription(),
                            bpmnMarker.getElementId(),
                            MarkerKind.INFO));
                }

            }
        }

        return result;
    }

}