/**
 */
package org.openbpmn.di;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.DiagramElement#getOwningDiagram <em>Owning Diagram</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getOwningElement <em>Owning Element</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getOwnedElement <em>Owned Element</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getStyle <em>Style</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getId <em>Id</em>}</li>
 *   <li>{@link org.openbpmn.di.DiagramElement#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.di.DiPackage#getDiagramElement()
 * @model
 * @generated
 */
public interface DiagramElement extends EObject {
    /**
     * Returns the value of the '<em><b>Owning Diagram</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.openbpmn.di.Diagram#getRootElement <em>Root Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owning Diagram</em>' reference.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_OwningDiagram()
     * @see org.openbpmn.di.Diagram#getRootElement
     * @model opposite="rootElement" resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    Diagram getOwningDiagram();

    /**
     * Returns the value of the '<em><b>Owning Element</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.openbpmn.di.DiagramElement#getOwnedElement <em>Owned Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owning Element</em>' reference.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_OwningElement()
     * @see org.openbpmn.di.DiagramElement#getOwnedElement
     * @model opposite="ownedElement" resolveProxies="false" transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    DiagramElement getOwningElement();

    /**
     * Returns the value of the '<em><b>Owned Element</b></em>' reference list.
     * The list contents are of type {@link org.openbpmn.di.DiagramElement}.
     * It is bidirectional and its opposite is '{@link org.openbpmn.di.DiagramElement#getOwningElement <em>Owning Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owned Element</em>' reference list.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_OwnedElement()
     * @see org.openbpmn.di.DiagramElement#getOwningElement
     * @model opposite="owningElement" resolveProxies="false" transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    EList<DiagramElement> getOwnedElement();

    /**
     * Returns the value of the '<em><b>Model Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model Element</em>' reference.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_ModelElement()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    EObject getModelElement();

    /**
     * Returns the value of the '<em><b>Style</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' reference.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_Style()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    Style getStyle();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.openbpmn.di.DiPackage#getDiagramElement_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.openbpmn.di.DiagramElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Any Attribute</em>' attribute list.
     * @see org.openbpmn.di.DiPackage#getDiagramElement_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // DiagramElement
