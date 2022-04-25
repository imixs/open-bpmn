/**
 */
package org.openbpmn.bpmndi.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openbpmn.bpmn2.Bpmn2Package;

import org.openbpmn.bpmn2.impl.Bpmn2PackageImpl;

import org.openbpmn.bpmndi.BPMNDiagram;
import org.openbpmn.bpmndi.BPMNEdge;
import org.openbpmn.bpmndi.BPMNLabel;
import org.openbpmn.bpmndi.BPMNLabelStyle;
import org.openbpmn.bpmndi.BPMNPlane;
import org.openbpmn.bpmndi.BPMNShape;
import org.openbpmn.bpmndi.BpmndiFactory;
import org.openbpmn.bpmndi.BpmndiPackage;
import org.openbpmn.bpmndi.DocumentRoot;
import org.openbpmn.bpmndi.MessageVisibleKind;
import org.openbpmn.bpmndi.ParticipantBandKind;

import org.openbpmn.dc.DcPackage;

import org.openbpmn.dc.impl.DcPackageImpl;

import org.openbpmn.di.DiPackage;

import org.openbpmn.di.impl.DiPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmndiPackageImpl extends EPackageImpl implements BpmndiPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnDiagramEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnLabelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnPlaneEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnShapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum messageVisibleKindEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum participantBandKindEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.openbpmn.bpmndi.BpmndiPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private BpmndiPackageImpl() {
        super(eNS_URI, BpmndiFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link BpmndiPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static BpmndiPackage init() {
        if (isInited) return (BpmndiPackage)EPackage.Registry.INSTANCE.getEPackage(BpmndiPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredBpmndiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        BpmndiPackageImpl theBpmndiPackage = registeredBpmndiPackage instanceof BpmndiPackageImpl ? (BpmndiPackageImpl)registeredBpmndiPackage : new BpmndiPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
        Bpmn2PackageImpl theBpmn2Package = (Bpmn2PackageImpl)(registeredPackage instanceof Bpmn2PackageImpl ? registeredPackage : Bpmn2Package.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);
        DiPackageImpl theDiPackage = (DiPackageImpl)(registeredPackage instanceof DiPackageImpl ? registeredPackage : DiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);
        DcPackageImpl theDcPackage = (DcPackageImpl)(registeredPackage instanceof DcPackageImpl ? registeredPackage : DcPackage.eINSTANCE);

        // Create package meta-data objects
        theBpmndiPackage.createPackageContents();
        theBpmn2Package.createPackageContents();
        theDiPackage.createPackageContents();
        theDcPackage.createPackageContents();

        // Initialize created meta-data
        theBpmndiPackage.initializePackageContents();
        theBpmn2Package.initializePackageContents();
        theDiPackage.initializePackageContents();
        theDcPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theBpmndiPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(BpmndiPackage.eNS_URI, theBpmndiPackage);
        return theBpmndiPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNDiagram() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNEdge() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNLabel() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNLabelStyle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNPlane() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_BPMNShape() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNDiagram() {
        return bpmnDiagramEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNDiagram_Plane() {
        return (EReference)bpmnDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNDiagram_LabelStyle() {
        return (EReference)bpmnDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNEdge() {
        return bpmnEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNEdge_Label() {
        return (EReference)bpmnEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNEdge_BpmnElement() {
        return (EReference)bpmnEdgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNEdge_MessageVisibleKind() {
        return (EAttribute)bpmnEdgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNEdge_SourceElement() {
        return (EReference)bpmnEdgeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNEdge_TargetElement() {
        return (EReference)bpmnEdgeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNLabel() {
        return bpmnLabelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNLabel_LabelStyle() {
        return (EReference)bpmnLabelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNLabelStyle() {
        return bpmnLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNLabelStyle_Font() {
        return (EReference)bpmnLabelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNPlane() {
        return bpmnPlaneEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNPlane_BpmnElement() {
        return (EReference)bpmnPlaneEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBPMNShape() {
        return bpmnShapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNShape_Label() {
        return (EReference)bpmnShapeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNShape_BpmnElement() {
        return (EReference)bpmnShapeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBPMNShape_ChoreographyActivityShape() {
        return (EReference)bpmnShapeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNShape_IsExpanded() {
        return (EAttribute)bpmnShapeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNShape_IsHorizontal() {
        return (EAttribute)bpmnShapeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNShape_IsMarkerVisible() {
        return (EAttribute)bpmnShapeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNShape_IsMessageVisible() {
        return (EAttribute)bpmnShapeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBPMNShape_ParticipantBandKind() {
        return (EAttribute)bpmnShapeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getMessageVisibleKind() {
        return messageVisibleKindEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getParticipantBandKind() {
        return participantBandKindEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BpmndiFactory getBpmndiFactory() {
        return (BpmndiFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_DIAGRAM);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_LABEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_LABEL_STYLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_PLANE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_SHAPE);

        bpmnDiagramEClass = createEClass(BPMN_DIAGRAM);
        createEReference(bpmnDiagramEClass, BPMN_DIAGRAM__PLANE);
        createEReference(bpmnDiagramEClass, BPMN_DIAGRAM__LABEL_STYLE);

        bpmnEdgeEClass = createEClass(BPMN_EDGE);
        createEReference(bpmnEdgeEClass, BPMN_EDGE__LABEL);
        createEReference(bpmnEdgeEClass, BPMN_EDGE__BPMN_ELEMENT);
        createEAttribute(bpmnEdgeEClass, BPMN_EDGE__MESSAGE_VISIBLE_KIND);
        createEReference(bpmnEdgeEClass, BPMN_EDGE__SOURCE_ELEMENT);
        createEReference(bpmnEdgeEClass, BPMN_EDGE__TARGET_ELEMENT);

        bpmnLabelEClass = createEClass(BPMN_LABEL);
        createEReference(bpmnLabelEClass, BPMN_LABEL__LABEL_STYLE);

        bpmnLabelStyleEClass = createEClass(BPMN_LABEL_STYLE);
        createEReference(bpmnLabelStyleEClass, BPMN_LABEL_STYLE__FONT);

        bpmnPlaneEClass = createEClass(BPMN_PLANE);
        createEReference(bpmnPlaneEClass, BPMN_PLANE__BPMN_ELEMENT);

        bpmnShapeEClass = createEClass(BPMN_SHAPE);
        createEReference(bpmnShapeEClass, BPMN_SHAPE__LABEL);
        createEReference(bpmnShapeEClass, BPMN_SHAPE__BPMN_ELEMENT);
        createEReference(bpmnShapeEClass, BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_EXPANDED);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_HORIZONTAL);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_MARKER_VISIBLE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_MESSAGE_VISIBLE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__PARTICIPANT_BAND_KIND);

        // Create enums
        messageVisibleKindEEnum = createEEnum(MESSAGE_VISIBLE_KIND);
        participantBandKindEEnum = createEEnum(PARTICIPANT_BAND_KIND);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        DiPackage theDiPackage = (DiPackage)EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);
        Bpmn2Package theBpmn2Package = (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
        DcPackage theDcPackage = (DcPackage)EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        bpmnDiagramEClass.getESuperTypes().add(theDiPackage.getDiagram());
        bpmnEdgeEClass.getESuperTypes().add(theDiPackage.getLabeledEdge());
        bpmnLabelEClass.getESuperTypes().add(theDiPackage.getLabel());
        bpmnLabelStyleEClass.getESuperTypes().add(theDiPackage.getStyle());
        bpmnPlaneEClass.getESuperTypes().add(theDiPackage.getPlane());
        bpmnShapeEClass.getESuperTypes().add(theDiPackage.getLabeledShape());

        // Initialize classes, features, and operations; add parameters
        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, DocumentRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, DocumentRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, DocumentRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNDiagram(), this.getBPMNDiagram(), null, "bPMNDiagram", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNEdge(), this.getBPMNEdge(), null, "bPMNEdge", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNLabel(), this.getBPMNLabel(), null, "bPMNLabel", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNLabelStyle(), this.getBPMNLabelStyle(), null, "bPMNLabelStyle", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNPlane(), this.getBPMNPlane(), null, "bPMNPlane", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNShape(), this.getBPMNShape(), null, "bPMNShape", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(bpmnDiagramEClass, BPMNDiagram.class, "BPMNDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNDiagram_Plane(), this.getBPMNPlane(), null, "plane", null, 1, 1, BPMNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNDiagram_LabelStyle(), this.getBPMNLabelStyle(), null, "labelStyle", null, 0, -1, BPMNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(bpmnEdgeEClass, BPMNEdge.class, "BPMNEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNEdge_Label(), this.getBPMNLabel(), null, "label", null, 0, 1, BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNEdge_BpmnElement(), theBpmn2Package.getBaseElement(), null, "bpmnElement", null, 0, 1, BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNEdge_MessageVisibleKind(), this.getMessageVisibleKind(), "messageVisibleKind", null, 0, 1, BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNEdge_SourceElement(), theDiPackage.getDiagramElement(), null, "sourceElement", null, 0, 1, BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNEdge_TargetElement(), theDiPackage.getDiagramElement(), null, "targetElement", null, 0, 1, BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(bpmnLabelEClass, BPMNLabel.class, "BPMNLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNLabel_LabelStyle(), this.getBPMNLabelStyle(), null, "labelStyle", null, 0, 1, BPMNLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(bpmnLabelStyleEClass, BPMNLabelStyle.class, "BPMNLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNLabelStyle_Font(), theDcPackage.getFont(), null, "font", null, 1, 1, BPMNLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(bpmnPlaneEClass, BPMNPlane.class, "BPMNPlane", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNPlane_BpmnElement(), theBpmn2Package.getBaseElement(), null, "bpmnElement", null, 0, 1, BPMNPlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(bpmnShapeEClass, BPMNShape.class, "BPMNShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNShape_Label(), this.getBPMNLabel(), null, "label", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNShape_BpmnElement(), theBpmn2Package.getBaseElement(), null, "bpmnElement", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getBPMNShape_ChoreographyActivityShape(), this.getBPMNShape(), null, "choreographyActivityShape", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNShape_IsExpanded(), ecorePackage.getEBoolean(), "isExpanded", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNShape_IsHorizontal(), ecorePackage.getEBoolean(), "isHorizontal", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNShape_IsMarkerVisible(), ecorePackage.getEBoolean(), "isMarkerVisible", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNShape_IsMessageVisible(), ecorePackage.getEBoolean(), "isMessageVisible", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBPMNShape_ParticipantBandKind(), this.getParticipantBandKind(), "participantBandKind", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(messageVisibleKindEEnum, MessageVisibleKind.class, "MessageVisibleKind");
        addEEnumLiteral(messageVisibleKindEEnum, MessageVisibleKind.INITIATING);
        addEEnumLiteral(messageVisibleKindEEnum, MessageVisibleKind.NON_INITIATING);

        initEEnum(participantBandKindEEnum, ParticipantBandKind.class, "ParticipantBandKind");
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.TOP_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.MIDDLE_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.BOTTOM_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.TOP_NON_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.MIDDLE_NON_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.BOTTOM_NON_INITIATING);

        // Create resource
        createResource(eNS_URI);
    }

} //BpmndiPackageImpl
