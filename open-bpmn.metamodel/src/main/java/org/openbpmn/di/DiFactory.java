/**
 */
package org.openbpmn.di;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openbpmn.di.DiPackage
 * @generated
 */
public interface DiFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DiFactory eINSTANCE = org.openbpmn.di.impl.DiFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Diagram Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Diagram Element</em>'.
     * @generated
     */
    DiagramElement createDiagramElement();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    DiPackage getDiPackage();

} //DiFactory
