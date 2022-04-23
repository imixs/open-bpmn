/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.Relationship#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Relationship#getSources <em>Sources</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Relationship#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getRelationship()
 * @model
 * @generated
 */
public interface Relationship extends BaseElement {
    /**
     * Returns the value of the '<em><b>Direction</b></em>' attribute.
     * The literals are from the enumeration {@link org.imixs.bpmn2.RelationshipDirection}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Direction</em>' attribute.
     * @see org.imixs.bpmn2.RelationshipDirection
     * @see #setDirection(RelationshipDirection)
     * @see org.imixs.bpmn2.Bpmn2Package#getRelationship_Direction()
     * @model
     * @generated
     */
    RelationshipDirection getDirection();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Relationship#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Direction</em>' attribute.
     * @see org.imixs.bpmn2.RelationshipDirection
     * @see #getDirection()
     * @generated
     */
    void setDirection(RelationshipDirection value);

    /**
     * Returns the value of the '<em><b>Sources</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sources</em>' reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getRelationship_Sources()
     * @model required="true" ordered="false"
     * @generated
     */
    EList<EObject> getSources();

    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getRelationship_Targets()
     * @model required="true" ordered="false"
     * @generated
     */
    EList<EObject> getTargets();

} // Relationship
