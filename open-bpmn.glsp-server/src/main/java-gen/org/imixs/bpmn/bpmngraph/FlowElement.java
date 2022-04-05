/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.FlowElement#getCategory <em>Category</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getFlowElement()
 * @model
 * @generated
 */
public interface FlowElement extends BaseElement {
    /**
     * Returns the value of the '<em><b>Category</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category</em>' attribute list.
     * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getFlowElement_Category()
     * @model
     * @generated
     */
    EList<String> getCategory();

} // FlowElement
