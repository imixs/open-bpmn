/**
 */
package org.openbpmn.bpmndi;

import org.openbpmn.bpmn2.BaseElement;

import org.openbpmn.di.LabeledShape;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#getLabel <em>Label</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#getChoreographyActivityShape <em>Choreography Activity Shape</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#isIsExpanded <em>Is Expanded</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#isIsHorizontal <em>Is Horizontal</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#isIsMarkerVisible <em>Is Marker Visible</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#isIsMessageVisible <em>Is Message Visible</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.BPMNShape#getParticipantBandKind <em>Participant Band Kind</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape()
 * @model
 * @generated
 */
public interface BPMNShape extends LabeledShape {
    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(BPMNLabel)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_Label()
     * @model containment="true" ordered="false"
     * @generated
     */
    BPMNLabel getLabel();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#getLabel <em>Label</em>}' containment reference.
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
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_BpmnElement()
     * @model ordered="false"
     * @generated
     */
    BaseElement getBpmnElement();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#getBpmnElement <em>Bpmn Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' reference.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(BaseElement value);

    /**
     * Returns the value of the '<em><b>Choreography Activity Shape</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choreography Activity Shape</em>' reference.
     * @see #setChoreographyActivityShape(BPMNShape)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_ChoreographyActivityShape()
     * @model ordered="false"
     * @generated
     */
    BPMNShape getChoreographyActivityShape();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#getChoreographyActivityShape <em>Choreography Activity Shape</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Choreography Activity Shape</em>' reference.
     * @see #getChoreographyActivityShape()
     * @generated
     */
    void setChoreographyActivityShape(BPMNShape value);

    /**
     * Returns the value of the '<em><b>Is Expanded</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Expanded</em>' attribute.
     * @see #setIsExpanded(boolean)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_IsExpanded()
     * @model ordered="false"
     * @generated
     */
    boolean isIsExpanded();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#isIsExpanded <em>Is Expanded</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Expanded</em>' attribute.
     * @see #isIsExpanded()
     * @generated
     */
    void setIsExpanded(boolean value);

    /**
     * Returns the value of the '<em><b>Is Horizontal</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Horizontal</em>' attribute.
     * @see #setIsHorizontal(boolean)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_IsHorizontal()
     * @model ordered="false"
     * @generated
     */
    boolean isIsHorizontal();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#isIsHorizontal <em>Is Horizontal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Horizontal</em>' attribute.
     * @see #isIsHorizontal()
     * @generated
     */
    void setIsHorizontal(boolean value);

    /**
     * Returns the value of the '<em><b>Is Marker Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Marker Visible</em>' attribute.
     * @see #setIsMarkerVisible(boolean)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_IsMarkerVisible()
     * @model ordered="false"
     * @generated
     */
    boolean isIsMarkerVisible();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#isIsMarkerVisible <em>Is Marker Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Marker Visible</em>' attribute.
     * @see #isIsMarkerVisible()
     * @generated
     */
    void setIsMarkerVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Is Message Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Message Visible</em>' attribute.
     * @see #setIsMessageVisible(boolean)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_IsMessageVisible()
     * @model ordered="false"
     * @generated
     */
    boolean isIsMessageVisible();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#isIsMessageVisible <em>Is Message Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Message Visible</em>' attribute.
     * @see #isIsMessageVisible()
     * @generated
     */
    void setIsMessageVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Participant Band Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.openbpmn.bpmndi.ParticipantBandKind}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Band Kind</em>' attribute.
     * @see org.openbpmn.bpmndi.ParticipantBandKind
     * @see #setParticipantBandKind(ParticipantBandKind)
     * @see org.openbpmn.bpmndi.BpmndiPackage#getBPMNShape_ParticipantBandKind()
     * @model ordered="false"
     * @generated
     */
    ParticipantBandKind getParticipantBandKind();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmndi.BPMNShape#getParticipantBandKind <em>Participant Band Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant Band Kind</em>' attribute.
     * @see org.openbpmn.bpmndi.ParticipantBandKind
     * @see #getParticipantBandKind()
     * @generated
     */
    void setParticipantBandKind(ParticipantBandKind value);

} // BPMNShape
