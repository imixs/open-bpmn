/**
 */
package org.eclipse.dd.dc.util;

import java.util.Map;

import org.eclipse.dd.dc.DcPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DcXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        DcPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the DcResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new DcResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new DcResourceFactoryImpl());
        }
        return registrations;
    }

} //DcXMLProcessor
