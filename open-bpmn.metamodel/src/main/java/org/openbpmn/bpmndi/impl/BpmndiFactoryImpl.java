/**
 */
package org.openbpmn.bpmndi.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openbpmn.bpmndi.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmndiFactoryImpl extends EFactoryImpl implements BpmndiFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BpmndiFactory init() {
        try {
            BpmndiFactory theBpmndiFactory = (BpmndiFactory)EPackage.Registry.INSTANCE.getEFactory(BpmndiPackage.eNS_URI);
            if (theBpmndiFactory != null) {
                return theBpmndiFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BpmndiFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case BpmndiPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case BpmndiPackage.BPMN_DIAGRAM: return createBPMNDiagram();
            case BpmndiPackage.BPMN_EDGE: return createBPMNEdge();
            case BpmndiPackage.BPMN_LABEL: return createBPMNLabel();
            case BpmndiPackage.BPMN_LABEL_STYLE: return createBPMNLabelStyle();
            case BpmndiPackage.BPMN_PLANE: return createBPMNPlane();
            case BpmndiPackage.BPMN_SHAPE: return createBPMNShape();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case BpmndiPackage.MESSAGE_VISIBLE_KIND:
                return createMessageVisibleKindFromString(eDataType, initialValue);
            case BpmndiPackage.PARTICIPANT_BAND_KIND:
                return createParticipantBandKindFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case BpmndiPackage.MESSAGE_VISIBLE_KIND:
                return convertMessageVisibleKindToString(eDataType, instanceValue);
            case BpmndiPackage.PARTICIPANT_BAND_KIND:
                return convertParticipantBandKindToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNDiagram createBPMNDiagram() {
        BPMNDiagramImpl bpmnDiagram = new BPMNDiagramImpl();
        return bpmnDiagram;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNEdge createBPMNEdge() {
        BPMNEdgeImpl bpmnEdge = new BPMNEdgeImpl();
        return bpmnEdge;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabel createBPMNLabel() {
        BPMNLabelImpl bpmnLabel = new BPMNLabelImpl();
        return bpmnLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabelStyle createBPMNLabelStyle() {
        BPMNLabelStyleImpl bpmnLabelStyle = new BPMNLabelStyleImpl();
        return bpmnLabelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNPlane createBPMNPlane() {
        BPMNPlaneImpl bpmnPlane = new BPMNPlaneImpl();
        return bpmnPlane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNShape createBPMNShape() {
        BPMNShapeImpl bpmnShape = new BPMNShapeImpl();
        return bpmnShape;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageVisibleKind createMessageVisibleKindFromString(EDataType eDataType, String initialValue) {
        MessageVisibleKind result = MessageVisibleKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertMessageVisibleKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParticipantBandKind createParticipantBandKindFromString(EDataType eDataType, String initialValue) {
        ParticipantBandKind result = ParticipantBandKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParticipantBandKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiPackage getBpmndiPackage() {
        return (BpmndiPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BpmndiPackage getPackage() {
        return BpmndiPackage.eINSTANCE;
    }

} //BpmndiFactoryImpl
