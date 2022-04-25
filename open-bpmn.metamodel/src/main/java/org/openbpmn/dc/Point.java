/**
 */
package org.openbpmn.dc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.dc.Point#getX <em>X</em>}</li>
 *   <li>{@link org.openbpmn.dc.Point#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.dc.DcPackage#getPoint()
 * @model
 * @generated
 */
public interface Point extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(float)
     * @see org.openbpmn.dc.DcPackage#getPoint_X()
     * @model default="0" required="true" ordered="false"
     * @generated
     */
    float getX();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Point#getX <em>X</em>}' attribute.
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
     * @see org.openbpmn.dc.DcPackage#getPoint_Y()
     * @model default="0" required="true" ordered="false"
     * @generated
     */
    float getY();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Point#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(float value);

} // Point
