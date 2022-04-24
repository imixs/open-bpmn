/**
 */
package org.eclipse.dd.di.util;

import java.util.Map;

import org.eclipse.dd.di.DiPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DiXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        DiPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the DiResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new DiResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new DiResourceFactoryImpl());
        }
        return registrations;
    }

} //DiXMLProcessor
