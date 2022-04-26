/**
 */
package org.openbpmn.dc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openbpmn.dc.DcFactory
 * @model kind="package"
 * @generated
 */
public interface DcPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "dc";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.omg.org/spec/DD/20100524/DC";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "dc";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DcPackage eINSTANCE = org.openbpmn.dc.impl.DcPackageImpl.init();

    /**
     * The meta object id for the '{@link org.openbpmn.dc.impl.BoundsImpl <em>Bounds</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.dc.impl.BoundsImpl
     * @see org.openbpmn.dc.impl.DcPackageImpl#getBounds()
     * @generated
     */
    int BOUNDS = 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS__HEIGHT = 0;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS__WIDTH = 1;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS__X = 2;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS__Y = 3;

    /**
     * The number of structural features of the '<em>Bounds</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Bounds</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOUNDS_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.openbpmn.dc.impl.FontImpl <em>Font</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.dc.impl.FontImpl
     * @see org.openbpmn.dc.impl.DcPackageImpl#getFont()
     * @generated
     */
    int FONT = 1;

    /**
     * The feature id for the '<em><b>Is Bold</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__IS_BOLD = 0;

    /**
     * The feature id for the '<em><b>Is Italic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__IS_ITALIC = 1;

    /**
     * The feature id for the '<em><b>Is Strike Through</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__IS_STRIKE_THROUGH = 2;

    /**
     * The feature id for the '<em><b>Is Underline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__IS_UNDERLINE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__NAME = 4;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT__SIZE = 5;

    /**
     * The number of structural features of the '<em>Font</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_FEATURE_COUNT = 6;

    /**
     * The operation id for the '<em>Non negative size</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT___NON_NEGATIVE_SIZE__DIAGNOSTICCHAIN_MAP = 0;

    /**
     * The number of operations of the '<em>Font</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FONT_OPERATION_COUNT = 1;

    /**
     * The meta object id for the '{@link org.openbpmn.dc.impl.PointImpl <em>Point</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openbpmn.dc.impl.PointImpl
     * @see org.openbpmn.dc.impl.DcPackageImpl#getPoint()
     * @generated
     */
    int POINT = 2;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT__Y = 1;

    /**
     * The number of structural features of the '<em>Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POINT_OPERATION_COUNT = 0;


    /**
     * Returns the meta object for class '{@link org.openbpmn.dc.Bounds <em>Bounds</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Bounds</em>'.
     * @see org.openbpmn.dc.Bounds
     * @generated
     */
    EClass getBounds();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Bounds#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.openbpmn.dc.Bounds#getHeight()
     * @see #getBounds()
     * @generated
     */
    EAttribute getBounds_Height();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Bounds#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.openbpmn.dc.Bounds#getWidth()
     * @see #getBounds()
     * @generated
     */
    EAttribute getBounds_Width();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Bounds#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.openbpmn.dc.Bounds#getX()
     * @see #getBounds()
     * @generated
     */
    EAttribute getBounds_X();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Bounds#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.openbpmn.dc.Bounds#getY()
     * @see #getBounds()
     * @generated
     */
    EAttribute getBounds_Y();

    /**
     * Returns the meta object for class '{@link org.openbpmn.dc.Font <em>Font</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Font</em>'.
     * @see org.openbpmn.dc.Font
     * @generated
     */
    EClass getFont();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#isIsBold <em>Is Bold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Bold</em>'.
     * @see org.openbpmn.dc.Font#isIsBold()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_IsBold();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#isIsItalic <em>Is Italic</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Italic</em>'.
     * @see org.openbpmn.dc.Font#isIsItalic()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_IsItalic();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Strike Through</em>'.
     * @see org.openbpmn.dc.Font#isIsStrikeThrough()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_IsStrikeThrough();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#isIsUnderline <em>Is Underline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Underline</em>'.
     * @see org.openbpmn.dc.Font#isIsUnderline()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_IsUnderline();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.openbpmn.dc.Font#getName()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_Name();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Font#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.openbpmn.dc.Font#getSize()
     * @see #getFont()
     * @generated
     */
    EAttribute getFont_Size();

    /**
     * Returns the meta object for the '{@link org.openbpmn.dc.Font#non_negative_size(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Non negative size</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Non negative size</em>' operation.
     * @see org.openbpmn.dc.Font#non_negative_size(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
     * @generated
     */
    EOperation getFont__Non_negative_size__DiagnosticChain_Map();

    /**
     * Returns the meta object for class '{@link org.openbpmn.dc.Point <em>Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Point</em>'.
     * @see org.openbpmn.dc.Point
     * @generated
     */
    EClass getPoint();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Point#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.openbpmn.dc.Point#getX()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_X();

    /**
     * Returns the meta object for the attribute '{@link org.openbpmn.dc.Point#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.openbpmn.dc.Point#getY()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_Y();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DcFactory getDcFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.openbpmn.dc.impl.BoundsImpl <em>Bounds</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.dc.impl.BoundsImpl
         * @see org.openbpmn.dc.impl.DcPackageImpl#getBounds()
         * @generated
         */
        EClass BOUNDS = eINSTANCE.getBounds();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOUNDS__HEIGHT = eINSTANCE.getBounds_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOUNDS__WIDTH = eINSTANCE.getBounds_Width();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOUNDS__X = eINSTANCE.getBounds_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOUNDS__Y = eINSTANCE.getBounds_Y();

        /**
         * The meta object literal for the '{@link org.openbpmn.dc.impl.FontImpl <em>Font</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.dc.impl.FontImpl
         * @see org.openbpmn.dc.impl.DcPackageImpl#getFont()
         * @generated
         */
        EClass FONT = eINSTANCE.getFont();

        /**
         * The meta object literal for the '<em><b>Is Bold</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__IS_BOLD = eINSTANCE.getFont_IsBold();

        /**
         * The meta object literal for the '<em><b>Is Italic</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__IS_ITALIC = eINSTANCE.getFont_IsItalic();

        /**
         * The meta object literal for the '<em><b>Is Strike Through</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__IS_STRIKE_THROUGH = eINSTANCE.getFont_IsStrikeThrough();

        /**
         * The meta object literal for the '<em><b>Is Underline</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__IS_UNDERLINE = eINSTANCE.getFont_IsUnderline();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__NAME = eINSTANCE.getFont_Name();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FONT__SIZE = eINSTANCE.getFont_Size();

        /**
         * The meta object literal for the '<em><b>Non negative size</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation FONT___NON_NEGATIVE_SIZE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getFont__Non_negative_size__DiagnosticChain_Map();

        /**
         * The meta object literal for the '{@link org.openbpmn.dc.impl.PointImpl <em>Point</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.openbpmn.dc.impl.PointImpl
         * @see org.openbpmn.dc.impl.DcPackageImpl#getPoint()
         * @generated
         */
        EClass POINT = eINSTANCE.getPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT__X = eINSTANCE.getPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute POINT__Y = eINSTANCE.getPoint_Y();

    }

} //DcPackage
