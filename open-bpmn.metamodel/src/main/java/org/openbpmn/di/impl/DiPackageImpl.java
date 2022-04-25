/**
 */
package org.openbpmn.di.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.openbpmn.bpmn2.Bpmn2Package;

import org.openbpmn.bpmn2.impl.Bpmn2PackageImpl;

import org.openbpmn.bpmndi.BpmndiPackage;

import org.openbpmn.bpmndi.impl.BpmndiPackageImpl;

import org.openbpmn.dc.DcPackage;

import org.openbpmn.dc.impl.DcPackageImpl;

import org.openbpmn.di.DiFactory;
import org.openbpmn.di.DiPackage;
import org.openbpmn.di.Diagram;
import org.openbpmn.di.DiagramElement;
import org.openbpmn.di.DocumentRoot;
import org.openbpmn.di.Edge;
import org.openbpmn.di.Label;
import org.openbpmn.di.LabeledEdge;
import org.openbpmn.di.LabeledShape;
import org.openbpmn.di.Node;
import org.openbpmn.di.Plane;
import org.openbpmn.di.Shape;
import org.openbpmn.di.Style;

import org.openbpmn.di.util.DiValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiPackageImpl extends EPackageImpl implements DiPackage {
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
    private EClass diagramEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass diagramElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labeledEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labeledShapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass planeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass shapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass styleEClass = null;

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
     * @see org.openbpmn.di.DiPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DiPackageImpl() {
        super(eNS_URI, DiFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link DiPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DiPackage init() {
        if (isInited) return (DiPackage)EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredDiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        DiPackageImpl theDiPackage = registeredDiPackage instanceof DiPackageImpl ? (DiPackageImpl)registeredDiPackage : new DiPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
        Bpmn2PackageImpl theBpmn2Package = (Bpmn2PackageImpl)(registeredPackage instanceof Bpmn2PackageImpl ? registeredPackage : Bpmn2Package.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BpmndiPackage.eNS_URI);
        BpmndiPackageImpl theBpmndiPackage = (BpmndiPackageImpl)(registeredPackage instanceof BpmndiPackageImpl ? registeredPackage : BpmndiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);
        DcPackageImpl theDcPackage = (DcPackageImpl)(registeredPackage instanceof DcPackageImpl ? registeredPackage : DcPackage.eINSTANCE);

        // Create package meta-data objects
        theDiPackage.createPackageContents();
        theBpmn2Package.createPackageContents();
        theBpmndiPackage.createPackageContents();
        theDcPackage.createPackageContents();

        // Initialize created meta-data
        theDiPackage.initializePackageContents();
        theBpmn2Package.initializePackageContents();
        theBpmndiPackage.initializePackageContents();
        theDcPackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put
            (theDiPackage,
             new EValidator.Descriptor() {
                 public EValidator getEValidator() {
                     return DiValidator.INSTANCE;
                 }
             });

        // Mark meta-data to indicate it can't be changed
        theDiPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DiPackage.eNS_URI, theDiPackage);
        return theDiPackage;
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
    public EReference getDocumentRoot_DiagramElement() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Diagram() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Edge() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Label() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LabeledEdge() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LabeledShape() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Node() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Plane() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Shape() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Style() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDiagram() {
        return diagramEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagram_Documentation() {
        return (EAttribute)diagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagram_OwnedStyle() {
        return (EReference)diagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagram_RootElement() {
        return (EReference)diagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagram_Id() {
        return (EAttribute)diagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagram_Name() {
        return (EAttribute)diagramEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagram_Resolution() {
        return (EAttribute)diagramEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDiagramElement() {
        return diagramElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagramElement_OwningDiagram() {
        return (EReference)diagramElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagramElement_OwningElement() {
        return (EReference)diagramElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagramElement_OwnedElement() {
        return (EReference)diagramElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagramElement_ModelElement() {
        return (EReference)diagramElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDiagramElement_Style() {
        return (EReference)diagramElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagramElement_Id() {
        return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagramElement_AnyAttribute() {
        return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdge() {
        return edgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdge_Source() {
        return (EReference)edgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdge_Target() {
        return (EReference)edgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdge_Waypoint() {
        return (EReference)edgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabel() {
        return labelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabel_Bounds() {
        return (EReference)labelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabeledEdge() {
        return labeledEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabeledEdge_OwnedLabel() {
        return (EReference)labeledEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabeledShape() {
        return labeledShapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabeledShape_OwnedLabel() {
        return (EReference)labeledShapeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNode() {
        return nodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPlane() {
        return planeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPlane_PlaneElement() {
        return (EReference)planeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EOperation getPlane__Plane_element_type__DiagnosticChain_Map() {
        return planeEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getShape() {
        return shapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getShape_Bounds() {
        return (EReference)shapeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStyle() {
        return styleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStyle_Id() {
        return (EAttribute)styleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiFactory getDiFactory() {
        return (DiFactory)getEFactoryInstance();
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
        createEReference(documentRootEClass, DOCUMENT_ROOT__DIAGRAM_ELEMENT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DIAGRAM);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABELED_EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABELED_SHAPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PLANE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SHAPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STYLE);

        diagramEClass = createEClass(DIAGRAM);
        createEAttribute(diagramEClass, DIAGRAM__DOCUMENTATION);
        createEReference(diagramEClass, DIAGRAM__OWNED_STYLE);
        createEReference(diagramEClass, DIAGRAM__ROOT_ELEMENT);
        createEAttribute(diagramEClass, DIAGRAM__ID);
        createEAttribute(diagramEClass, DIAGRAM__NAME);
        createEAttribute(diagramEClass, DIAGRAM__RESOLUTION);

        diagramElementEClass = createEClass(DIAGRAM_ELEMENT);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__OWNING_DIAGRAM);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__OWNING_ELEMENT);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__OWNED_ELEMENT);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__MODEL_ELEMENT);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__STYLE);
        createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__ID);
        createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__ANY_ATTRIBUTE);

        edgeEClass = createEClass(EDGE);
        createEReference(edgeEClass, EDGE__SOURCE);
        createEReference(edgeEClass, EDGE__TARGET);
        createEReference(edgeEClass, EDGE__WAYPOINT);

        labelEClass = createEClass(LABEL);
        createEReference(labelEClass, LABEL__BOUNDS);

        labeledEdgeEClass = createEClass(LABELED_EDGE);
        createEReference(labeledEdgeEClass, LABELED_EDGE__OWNED_LABEL);

        labeledShapeEClass = createEClass(LABELED_SHAPE);
        createEReference(labeledShapeEClass, LABELED_SHAPE__OWNED_LABEL);

        nodeEClass = createEClass(NODE);

        planeEClass = createEClass(PLANE);
        createEReference(planeEClass, PLANE__PLANE_ELEMENT);
        createEOperation(planeEClass, PLANE___PLANE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);

        shapeEClass = createEClass(SHAPE);
        createEReference(shapeEClass, SHAPE__BOUNDS);

        styleEClass = createEClass(STYLE);
        createEAttribute(styleEClass, STYLE__ID);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
        DcPackage theDcPackage = (DcPackage)EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        edgeEClass.getESuperTypes().add(this.getDiagramElement());
        labelEClass.getESuperTypes().add(this.getNode());
        labeledEdgeEClass.getESuperTypes().add(this.getEdge());
        labeledShapeEClass.getESuperTypes().add(this.getShape());
        nodeEClass.getESuperTypes().add(this.getDiagramElement());
        planeEClass.getESuperTypes().add(this.getNode());
        shapeEClass.getESuperTypes().add(this.getNode());

        // Initialize classes, features, and operations; add parameters
        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, DocumentRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, DocumentRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, DocumentRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_DiagramElement(), this.getDiagramElement(), null, "diagramElement", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Diagram(), this.getDiagram(), null, "diagram", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Edge(), this.getEdge(), null, "edge", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Label(), this.getLabel(), null, "label", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabeledEdge(), this.getLabeledEdge(), null, "labeledEdge", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabeledShape(), this.getLabeledShape(), null, "labeledShape", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Node(), this.getNode(), null, "node", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Plane(), this.getPlane(), null, "plane", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Shape(), this.getShape(), null, "shape", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Style(), this.getStyle(), null, "style", null, 0, -2, DocumentRoot.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(diagramEClass, Diagram.class, "Diagram", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDiagram_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagram_OwnedStyle(), this.getStyle(), null, "ownedStyle", null, 0, -1, Diagram.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagram_RootElement(), this.getDiagramElement(), this.getDiagramElement_OwningDiagram(), "rootElement", null, 1, 1, Diagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDiagram_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagram_Name(), ecorePackage.getEString(), "name", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDiagram_Resolution(), ecorePackage.getEFloat(), "resolution", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(diagramElementEClass, DiagramElement.class, "DiagramElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramElement_OwningDiagram(), this.getDiagram(), this.getDiagram_RootElement(), "owningDiagram", null, 0, 1, DiagramElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagramElement_OwningElement(), this.getDiagramElement(), this.getDiagramElement_OwnedElement(), "owningElement", null, 0, 1, DiagramElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagramElement_OwnedElement(), this.getDiagramElement(), this.getDiagramElement_OwningElement(), "ownedElement", null, 0, -1, DiagramElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagramElement_ModelElement(), ecorePackage.getEObject(), null, "modelElement", null, 0, 1, DiagramElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDiagramElement_Style(), this.getStyle(), null, "style", null, 0, 1, DiagramElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDiagramElement_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElement_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeEClass, Edge.class, "Edge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdge_Source(), this.getDiagramElement(), null, "source", null, 0, 1, Edge.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getEdge_Target(), this.getDiagramElement(), null, "target", null, 0, 1, Edge.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getEdge_Waypoint(), theDcPackage.getPoint(), null, "waypoint", null, 2, -1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabel_Bounds(), theDcPackage.getBounds(), null, "bounds", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(labeledEdgeEClass, LabeledEdge.class, "LabeledEdge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabeledEdge_OwnedLabel(), this.getLabel(), null, "ownedLabel", null, 0, -1, LabeledEdge.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

        initEClass(labeledShapeEClass, LabeledShape.class, "LabeledShape", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabeledShape_OwnedLabel(), this.getLabel(), null, "ownedLabel", null, 0, -1, LabeledShape.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

        initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(planeEClass, Plane.class, "Plane", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPlane_PlaneElement(), this.getDiagramElement(), null, "planeElement", null, 0, -1, Plane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = initEOperation(getPlane__Plane_element_type__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "plane_element_type", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
        EGenericType g1 = createEGenericType(ecorePackage.getEMap());
        EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(shapeEClass, Shape.class, "Shape", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getShape_Bounds(), theDcPackage.getBounds(), null, "bounds", null, 1, 1, Shape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(styleEClass, Style.class, "Style", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStyle_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //DiPackageImpl
