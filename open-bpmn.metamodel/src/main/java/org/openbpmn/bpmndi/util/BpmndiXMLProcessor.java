/**
 */
package org.openbpmn.bpmndi.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.openbpmn.bpmndi.BpmndiPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmndiXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        BpmndiPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the BpmndiResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new BpmndiResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new BpmndiResourceFactoryImpl());
        }
        return registrations;
    }

} //BpmndiXMLProcessor
