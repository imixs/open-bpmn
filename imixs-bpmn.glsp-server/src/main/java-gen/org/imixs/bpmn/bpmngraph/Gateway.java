/**
 */
package org.imixs.bpmn.bpmngraph;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.imixs.bpmn.bpmngraph.Gateway#getName <em>Name</em>}</li>
 * <li>{@link org.imixs.bpmn.bpmngraph.Gateway#getGatewayType <em>Gateway Type</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getGateway()
 * @model
 * @generated
 */
public interface Gateway extends ActivityNode {
   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    *
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getGateway_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.Gateway#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    *
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Gateway Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    *
    * @return the value of the '<em>Gateway Type</em>' attribute.
    * @see #setGatewayType(String)
    * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getGateway_GatewayType()
    * @model
    * @generated
    */
   String getGatewayType();

   /**
    * Sets the value of the '{@link org.imixs.bpmn.bpmngraph.Gateway#getGatewayType <em>Gateway Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    *
    * @param value the new value of the '<em>Gateway Type</em>' attribute.
    * @see #getGatewayType()
    * @generated
    */
   void setGatewayType(String value);

} // Gateway
