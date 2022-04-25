/**
 */
package org.eclipse.bpmn2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Choreography</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.SubChoreography#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getSubChoreography()
 * @model extendedMetaData="name='tSubChoreography' kind='elementOnly'"
 * @generated
 */
public interface SubChoreography extends ChoreographyActivity, FlowElementsContainer {
    /**
     * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Artifact}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifacts</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getSubChoreography_Artifacts()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='artifact' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#artifact'"
     * @generated
     */
    EList<Artifact> getArtifacts();

} // SubChoreography
