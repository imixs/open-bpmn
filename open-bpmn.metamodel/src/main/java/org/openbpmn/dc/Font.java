/**
 */
package org.openbpmn.dc;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Font</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.dc.Font#isIsBold <em>Is Bold</em>}</li>
 *   <li>{@link org.openbpmn.dc.Font#isIsItalic <em>Is Italic</em>}</li>
 *   <li>{@link org.openbpmn.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}</li>
 *   <li>{@link org.openbpmn.dc.Font#isIsUnderline <em>Is Underline</em>}</li>
 *   <li>{@link org.openbpmn.dc.Font#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.dc.Font#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.dc.DcPackage#getFont()
 * @model
 * @generated
 */
public interface Font extends EObject {
    /**
     * Returns the value of the '<em><b>Is Bold</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Bold</em>' attribute.
     * @see #setIsBold(boolean)
     * @see org.openbpmn.dc.DcPackage#getFont_IsBold()
     * @model ordered="false"
     * @generated
     */
    boolean isIsBold();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#isIsBold <em>Is Bold</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Bold</em>' attribute.
     * @see #isIsBold()
     * @generated
     */
    void setIsBold(boolean value);

    /**
     * Returns the value of the '<em><b>Is Italic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Italic</em>' attribute.
     * @see #setIsItalic(boolean)
     * @see org.openbpmn.dc.DcPackage#getFont_IsItalic()
     * @model ordered="false"
     * @generated
     */
    boolean isIsItalic();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#isIsItalic <em>Is Italic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Italic</em>' attribute.
     * @see #isIsItalic()
     * @generated
     */
    void setIsItalic(boolean value);

    /**
     * Returns the value of the '<em><b>Is Strike Through</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Strike Through</em>' attribute.
     * @see #setIsStrikeThrough(boolean)
     * @see org.openbpmn.dc.DcPackage#getFont_IsStrikeThrough()
     * @model ordered="false"
     * @generated
     */
    boolean isIsStrikeThrough();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Strike Through</em>' attribute.
     * @see #isIsStrikeThrough()
     * @generated
     */
    void setIsStrikeThrough(boolean value);

    /**
     * Returns the value of the '<em><b>Is Underline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Underline</em>' attribute.
     * @see #setIsUnderline(boolean)
     * @see org.openbpmn.dc.DcPackage#getFont_IsUnderline()
     * @model ordered="false"
     * @generated
     */
    boolean isIsUnderline();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#isIsUnderline <em>Is Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Underline</em>' attribute.
     * @see #isIsUnderline()
     * @generated
     */
    void setIsUnderline(boolean value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.dc.DcPackage#getFont_Name()
     * @model ordered="false"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(float)
     * @see org.openbpmn.dc.DcPackage#getFont_Size()
     * @model ordered="false"
     * @generated
     */
    float getSize();

    /**
     * Sets the value of the '{@link org.openbpmn.dc.Font#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(float value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean non_negative_size(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Font
