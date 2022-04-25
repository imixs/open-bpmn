/**
 */
package org.openbpmn.bpmn2.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.openbpmn.bpmn2.Bpmn2Package;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2XMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2XMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        Bpmn2Package.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the Bpmn2ResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new Bpmn2ResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new Bpmn2ResourceFactoryImpl());
        }
        return registrations;
    }

} //Bpmn2XMLProcessor
