/**
 */
package org.openbpmn.bpmndi;

import org.openbpmn.bpmn2.BaseElement;

import org.openbpmn.di.DiagramElement;
import org.openbpmn.di.LabeledEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNEdge#getLabel <em>Label</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNEdge#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNEdge#getTargetElement <em>Target Element</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge()
 * @model
 * @generated
 */
public interface BPMNEdge extends LabeledEdge {
    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(BPMNLabel)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge_Label()
     * @model containment="true" ordered="false"
     * @generated
     */
    BPMNLabel getLabel();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNEdge#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>Bpmn Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bpmn Element</em>' reference.
     * @see #setBpmnElement(BaseElement)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge_BpmnElement()
     * @model ordered="false"
     * @generated
     */
    BaseElement getBpmnElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' reference.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(BaseElement value);

    /**
     * Returns the value of the '<em><b>Message Visible Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.openbpmn.bpmndi.MessageVisibleKind}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.openbpmn.bpmndi.MessageVisibleKind
     * @see #setMessageVisibleKind(MessageVisibleKind)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge_MessageVisibleKind()
     * @model ordered="false"
     * @generated
     */
    MessageVisibleKind getMessageVisibleKind();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.openbpmn.bpmndi.MessageVisibleKind
     * @see #getMessageVisibleKind()
     * @generated
     */
    void setMessageVisibleKind(MessageVisibleKind value);

    /**
     * Returns the value of the '<em><b>Source Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Element</em>' reference.
     * @see #setSourceElement(DiagramElement)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge_SourceElement()
     * @model ordered="false"
     * @generated
     */
    DiagramElement getSourceElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNEdge#getSourceElement <em>Source Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Element</em>' reference.
     * @see #getSourceElement()
     * @generated
     */
    void setSourceElement(DiagramElement value);

    /**
     * Returns the value of the '<em><b>Target Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Element</em>' reference.
     * @see #setTargetElement(DiagramElement)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNEdge_TargetElement()
     * @model ordered="false"
     * @generated
     */
    DiagramElement getTargetElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNEdge#getTargetElement <em>Target Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Element</em>' reference.
     * @see #getTargetElement()
     * @generated
     */
    void setTargetElement(DiagramElement value);

} // BPMNEdge
