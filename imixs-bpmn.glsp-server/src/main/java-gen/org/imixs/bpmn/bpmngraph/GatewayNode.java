/**
 */
package org.imixs.bpmn.bpmngraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gateway Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.GatewayNode#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getGatewayNode()
 * @model
 * @generated
 */
public interface GatewayNode extends ActivityNode {
   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getGatewayNode_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.GatewayNode#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

} // GatewayNode
