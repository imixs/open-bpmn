/**
 */
package org.openbpmn.bpmndi;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot()
 * @model
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>BPMN Diagram</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #setBPMNDiagram(BPMNDiagram)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNDiagram()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNDiagram getBPMNDiagram();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #getBPMNDiagram()
     * @generated
     */
    void setBPMNDiagram(BPMNDiagram value);

    /**
     * Returns the value of the '<em><b>BPMN Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Edge</em>' containment reference.
     * @see #setBPMNEdge(BPMNEdge)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNEdge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNEdge getBPMNEdge();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Edge</em>' containment reference.
     * @see #getBPMNEdge()
     * @generated
     */
    void setBPMNEdge(BPMNEdge value);

    /**
     * Returns the value of the '<em><b>BPMN Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label</em>' containment reference.
     * @see #setBPMNLabel(BPMNLabel)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNLabel()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNLabel getBPMNLabel();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label</em>' containment reference.
     * @see #getBPMNLabel()
     * @generated
     */
    void setBPMNLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>BPMN Label Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #setBPMNLabelStyle(BPMNLabelStyle)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNLabelStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNLabelStyle getBPMNLabelStyle();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #getBPMNLabelStyle()
     * @generated
     */
    void setBPMNLabelStyle(BPMNLabelStyle value);

    /**
     * Returns the value of the '<em><b>BPMN Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Plane</em>' containment reference.
     * @see #setBPMNPlane(BPMNPlane)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNPlane()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNPlane getBPMNPlane();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Plane</em>' containment reference.
     * @see #getBPMNPlane()
     * @generated
     */
    void setBPMNPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>BPMN Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Shape</em>' containment reference.
     * @see #setBPMNShape(BPMNShape)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getDocumentRoot_BPMNShape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     * @generated
     */
    BPMNShape getBPMNShape();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Shape</em>' containment reference.
     * @see #getBPMNShape()
     * @generated
     */
    void setBPMNShape(BPMNShape value);

} // DocumentRoot
