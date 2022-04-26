/**
 */
package org.openbpmn.bpmn2;

import org.eclipse.emf.common.util.EList;

import org.openbpmn.bpmndi.BPMNDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.Definitions#getProcesses <em>Processes</em>}</li>
 * </ul>
 *
 * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions()
 * @model
 * @generated
 */
public interface Definitions extends BaseElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Namespace</em>' attribute.
     * @see #setTargetNamespace(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_TargetNamespace()
     * @model
     * @generated
     */
    String getTargetNamespace();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Namespace</em>' attribute.
     * @see #getTargetNamespace()
     * @generated
     */
    void setTargetNamespace(String value);

    /**
     * Returns the value of the '<em><b>Expression Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Language</em>' attribute.
     * @see #setExpressionLanguage(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_ExpressionLanguage()
     * @model
     * @generated
     */
    String getExpressionLanguage();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Language</em>' attribute.
     * @see #getExpressionLanguage()
     * @generated
     */
    void setExpressionLanguage(String value);

    /**
     * Returns the value of the '<em><b>Type Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Language</em>' attribute.
     * @see #setTypeLanguage(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_TypeLanguage()
     * @model
     * @generated
     */
    String getTypeLanguage();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Language</em>' attribute.
     * @see #getTypeLanguage()
     * @generated
     */
    void setTypeLanguage(String value);

    /**
     * Returns the value of the '<em><b>Exporter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exporter</em>' attribute.
     * @see #setExporter(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_Exporter()
     * @model
     * @generated
     */
    String getExporter();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getExporter <em>Exporter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exporter</em>' attribute.
     * @see #getExporter()
     * @generated
     */
    void setExporter(String value);

    /**
     * Returns the value of the '<em><b>Exporter Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exporter Version</em>' attribute.
     * @see #setExporterVersion(String)
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_ExporterVersion()
     * @model
     * @generated
     */
    String getExporterVersion();

    /**
     * Sets the value of the '{@link org.openbpmn.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exporter Version</em>' attribute.
     * @see #getExporterVersion()
     * @generated
     */
    void setExporterVersion(String value);

    /**
     * Returns the value of the '<em><b>Diagrams</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.bpmndi.BPMNDiagram}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Diagrams</em>' containment reference list.
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_Diagrams()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='BPMNDiagram' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
     * @generated
     */
    EList<BPMNDiagram> getDiagrams();

    /**
     * Returns the value of the '<em><b>Processes</b></em>' containment reference list.
     * The list contents are of type {@link org.openbpmn.bpmn2.Process}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Processes</em>' containment reference list.
     * @see org.openbpmn.bpmn2.Bpmn2Package#getDefinitions_Processes()
     * @model containment="true" required="true" ordered="false"
     *        extendedMetaData="kind='element' name='process' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    EList<org.openbpmn.bpmn2.Process> getProcesses();

} // Definitions
