/**
 */
package org.openbpmn.di;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Labeled Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.LabeledShape#getOwnedLabel <em>Owned Label</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.di.DiPackage#getLabeledShape()
 * @model abstract="true"
 * @generated
 */
public interface LabeledShape extends Shape {
    /**
     * Returns the value of the '<em><b>Owned Label</b></em>' reference list.
     * The list contents are of type {@link org.openbpmn.di.Label}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Owned Label</em>' reference list.
     * @see org.openbpmn.di.DiPackage#getLabeledShape_OwnedLabel()
     * @model transient="true" changeable="false" derived="true" ordered="false"
     * @generated
     */
    EList<Label> getOwnedLabel();

} // LabeledShape
