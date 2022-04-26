/**
 */
package org.openbpmn.dc.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openbpmn.bpmn2.Bpmn2Package;

import org.openbpmn.bpmn2.impl.Bpmn2PackageImpl;

import org.openbpmn.bpmndi.BpmndiPackage;

import org.openbpmn.bpmndi.impl.BpmndiPackageImpl;

import org.openbpmn.dc.Bounds;
import org.openbpmn.dc.DcFactory;
import org.openbpmn.dc.DcPackage;
import org.openbpmn.dc.Font;
import org.openbpmn.dc.Point;

import org.openbpmn.dc.util.DcValidator;

import org.openbpmn.di.DiPackage;

import org.openbpmn.di.impl.DiPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DcPackageImpl extends EPackageImpl implements DcPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass boundsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fontEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pointEClass = null;

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
     * @see org.openbpmn.dc.DcPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DcPackageImpl() {
        super(eNS_URI, DcFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link DcPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DcPackage init() {
        if (isInited) return (DcPackage)EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredDcPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        DcPackageImpl theDcPackage = registeredDcPackage instanceof DcPackageImpl ? (DcPackageImpl)registeredDcPackage : new DcPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
        Bpmn2PackageImpl theBpmn2Package = (Bpmn2PackageImpl)(registeredPackage instanceof Bpmn2PackageImpl ? registeredPackage : Bpmn2Package.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BpmndiPackage.eNS_URI);
        BpmndiPackageImpl theBpmndiPackage = (BpmndiPackageImpl)(registeredPackage instanceof BpmndiPackageImpl ? registeredPackage : BpmndiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);
        DiPackageImpl theDiPackage = (DiPackageImpl)(registeredPackage instanceof DiPackageImpl ? registeredPackage : DiPackage.eINSTANCE);

        // Create package meta-data objects
        theDcPackage.createPackageContents();
        theBpmn2Package.createPackageContents();
        theBpmndiPackage.createPackageContents();
        theDiPackage.createPackageContents();

        // Initialize created meta-data
        theDcPackage.initializePackageContents();
        theBpmn2Package.initializePackageContents();
        theBpmndiPackage.initializePackageContents();
        theDiPackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put
            (theDcPackage,
             new EValidator.Descriptor() {
                 public EValidator getEValidator() {
                     return DcValidator.INSTANCE;
                 }
             });

        // Mark meta-data to indicate it can't be changed
        theDcPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DcPackage.eNS_URI, theDcPackage);
        return theDcPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBounds() {
        return boundsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBounds_Height() {
        return (EAttribute)boundsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBounds_Width() {
        return (EAttribute)boundsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBounds_X() {
        return (EAttribute)boundsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBounds_Y() {
        return (EAttribute)boundsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFont() {
        return fontEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_IsBold() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_IsItalic() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_IsStrikeThrough() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_IsUnderline() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_Name() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFont_Size() {
        return (EAttribute)fontEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EOperation getFont__Non_negative_size__DiagnosticChain_Map() {
        return fontEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPoint() {
        return pointEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPoint_X() {
        return (EAttribute)pointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPoint_Y() {
        return (EAttribute)pointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcFactory getDcFactory() {
        return (DcFactory)getEFactoryInstance();
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
        boundsEClass = createEClass(BOUNDS);
        createEAttribute(boundsEClass, BOUNDS__HEIGHT);
        createEAttribute(boundsEClass, BOUNDS__WIDTH);
        createEAttribute(boundsEClass, BOUNDS__X);
        createEAttribute(boundsEClass, BOUNDS__Y);

        fontEClass = createEClass(FONT);
        createEAttribute(fontEClass, FONT__IS_BOLD);
        createEAttribute(fontEClass, FONT__IS_ITALIC);
        createEAttribute(fontEClass, FONT__IS_STRIKE_THROUGH);
        createEAttribute(fontEClass, FONT__IS_UNDERLINE);
        createEAttribute(fontEClass, FONT__NAME);
        createEAttribute(fontEClass, FONT__SIZE);
        createEOperation(fontEClass, FONT___NON_NEGATIVE_SIZE__DIAGNOSTICCHAIN_MAP);

        pointEClass = createEClass(POINT);
        createEAttribute(pointEClass, POINT__X);
        createEAttribute(pointEClass, POINT__Y);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(boundsEClass, Bounds.class, "Bounds", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBounds_Height(), ecorePackage.getEFloat(), "height", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBounds_Width(), ecorePackage.getEFloat(), "width", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBounds_X(), ecorePackage.getEFloat(), "x", "0", 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getBounds_Y(), ecorePackage.getEFloat(), "y", "0", 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(fontEClass, Font.class, "Font", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFont_IsBold(), ecorePackage.getEBoolean(), "isBold", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getFont_IsItalic(), ecorePackage.getEBoolean(), "isItalic", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getFont_IsStrikeThrough(), ecorePackage.getEBoolean(), "isStrikeThrough", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getFont_IsUnderline(), ecorePackage.getEBoolean(), "isUnderline", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getFont_Name(), ecorePackage.getEString(), "name", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getFont_Size(), ecorePackage.getEFloat(), "size", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        EOperation op = initEOperation(getFont__Non_negative_size__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "non_negative_size", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
        EGenericType g1 = createEGenericType(ecorePackage.getEMap());
        EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(pointEClass, Point.class, "Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPoint_X(), ecorePackage.getEFloat(), "x", "0", 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getPoint_Y(), ecorePackage.getEFloat(), "y", "0", 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //DcPackageImpl
