/**
 */
package org.eclipse.bpmn2.di.util;

import java.util.Map;

import org.eclipse.bpmn2.di.BpmnDiPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnDiXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmnDiXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        BpmnDiPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the BpmnDiResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new BpmnDiResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new BpmnDiResourceFactoryImpl());
        }
        return registrations;
    }

} //BpmnDiXMLProcessor
