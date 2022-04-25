/**
 */
package org.eclipse.bpmn2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Category#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Category#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getCategory()
 * @model extendedMetaData="name='tCategory' kind='elementOnly'"
 * @generated
 */
public interface Category extends RootElement {
    /**
     * Returns the value of the '<em><b>Category Value</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.CategoryValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getCategory_CategoryValue()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='categoryValue' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    EList<CategoryValue> getCategoryValue();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.bpmn2.Bpmn2Package#getCategory_Name()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Category#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Category
