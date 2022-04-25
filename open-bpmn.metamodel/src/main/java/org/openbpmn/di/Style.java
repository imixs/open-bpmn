/**
 */
package org.openbpmn.di;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.Style#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.di.DiPackage#getStyle()
 * @model abstract="true"
 * @generated
 */
public interface Style extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.openbpmn.di.DiPackage#getStyle_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.openbpmn.di.Style#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // Style
