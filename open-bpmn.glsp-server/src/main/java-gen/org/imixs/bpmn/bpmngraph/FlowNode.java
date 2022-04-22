/**
 */
package org.imixs.bpmn.bpmngraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.FlowNode#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.FlowNode#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getFlowNode()
 * @model abstract="true"
 *        extendedMetaData="name='tFlowNode' kind='elementOnly'"
 * @generated
 */
public interface FlowNode extends FlowElement {
    /**
     * Returns the value of the '<em><b>Incoming</b></em>' reference list.
     * The list contents are of type {@link org.imixs.bpmn.bpmngraph.SequenceFlow}.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getTargetRef <em>Target Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming</em>' reference list.
     * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getFlowNode_Incoming()
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getTargetRef
     * @model opposite="targetRef" resolveProxies="false"
     *        extendedMetaData="kind='element' name='incoming' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    EList<SequenceFlow> getIncoming();

    /**
     * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
     * The list contents are of type {@link org.imixs.bpmn.bpmngraph.SequenceFlow}.
     * It is bidirectional and its opposite is '{@link org.imixs.bpmn.bpmngraph.SequenceFlow#getSourceRef <em>Source Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing</em>' reference list.
     * @see org.imixs.bpmn.bpmngraph.BpmngraphPackage#getFlowNode_Outgoing()
     * @see org.imixs.bpmn.bpmngraph.SequenceFlow#getSourceRef
     * @model opposite="sourceRef" resolveProxies="false"
     *        extendedMetaData="kind='element' name='outgoing' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    EList<SequenceFlow> getOutgoing();

} // FlowNode
