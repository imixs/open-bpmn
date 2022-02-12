/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.glsp.graph.GEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Weighted Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.WeightedEdge#getProbability <em>Probability</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getWeightedEdge()
 * @model
 * @generated
 */
public interface WeightedEdge extends GEdge {
   /**
    * Returns the value of the '<em><b>Probability</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Probability</em>' attribute.
    * @see #setProbability(String)
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getWeightedEdge_Probability()
    * @model
    * @generated
    */
   String getProbability();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.WeightedEdge#getProbability <em>Probability</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Probability</em>' attribute.
    * @see #getProbability()
    * @generated
    */
   void setProbability(String value);

} // WeightedEdge
