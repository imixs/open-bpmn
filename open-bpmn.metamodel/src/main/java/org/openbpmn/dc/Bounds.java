/**
 */
package org.openbpmn.dc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bounds</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.dc.Bounds#getHeight <em>Height</em>}</li>
 *   <li>{@link org.openbpmn.dc.Bounds#getWidth <em>Width</em>}</li>
 *   <li>{@link org.openbpmn.dc.Bounds#getX <em>X</em>}</li>
 *   <li>{@link org.openbpmn.dc.Bounds#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.dc.DcPackage#getBounds()
 * @model
 * @generated
 */
public interface Bounds extends EObject {
    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(float)
     * @see org.openbpmn.dc.DcPackage#getBounds_Height()
     * @model required="true" ordered="false"
     * @generated
     */
    float getHeight();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Bounds#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(float value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(float)
     * @see org.openbpmn.dc.DcPackage#getBounds_Width()
     * @model required="true" ordered="false"
     * @generated
     */
    float getWidth();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Bounds#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(float value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(float)
     * @see org.openbpmn.dc.DcPackage#getBounds_X()
     * @model default="0" required="true" ordered="false"
     * @generated
     */
    float getX();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Bounds#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(float value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(float)
     * @see org.openbpmn.dc.DcPackage#getBounds_Y()
     * @model default="0" required="true" ordered="false"
     * @generated
     */
    float getY();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Bounds#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(float value);

} // Bounds
