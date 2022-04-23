/**
 */
package org.imixs.bpmn2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.Definitions#getRootElements <em>Root Elements</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getImports <em>Imports</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.imixs.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}</li>
 * </ul>
 *
 * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions()
 * @model
 * @generated
 */
public interface Definitions extends BaseElement {
    /**
     * Returns the value of the '<em><b>Root Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.imixs.bpmn2.RootElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Elements</em>' containment reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_RootElements()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<RootElement> getRootElements();

    /**
     * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
     * The list contents are of type {@link org.imixs.bpmn2.Import}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imports</em>' containment reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_Imports()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Import> getImports();

    /**
     * Returns the value of the '<em><b>Relationships</b></em>' containment reference list.
     * The list contents are of type {@link org.imixs.bpmn2.Relationship}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relationships</em>' containment reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_Relationships()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Relationship> getRelationships();

    /**
     * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
     * The list contents are of type {@link org.imixs.bpmn2.Extension}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extensions</em>' containment reference list.
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_Extensions()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Extension> getExtensions();

    /**
     * Returns the value of the '<em><b>Exporter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exporter</em>' attribute.
     * @see #setExporter(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_Exporter()
     * @model
     * @generated
     */
    String getExporter();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Definitions#getExporter <em>Exporter</em>}' attribute.
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
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_ExporterVersion()
     * @model
     * @generated
     */
    String getExporterVersion();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Definitions#getExporterVersion <em>Exporter Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exporter Version</em>' attribute.
     * @see #getExporterVersion()
     * @generated
     */
    void setExporterVersion(String value);

    /**
     * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Namespace</em>' attribute.
     * @see #setTargetNamespace(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_TargetNamespace()
     * @model
     * @generated
     */
    String getTargetNamespace();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Definitions#getTargetNamespace <em>Target Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Namespace</em>' attribute.
     * @see #getTargetNamespace()
     * @generated
     */
    void setTargetNamespace(String value);

    /**
     * Returns the value of the '<em><b>Type Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Language</em>' attribute.
     * @see #setTypeLanguage(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_TypeLanguage()
     * @model
     * @generated
     */
    String getTypeLanguage();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Definitions#getTypeLanguage <em>Type Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Language</em>' attribute.
     * @see #getTypeLanguage()
     * @generated
     */
    void setTypeLanguage(String value);

    /**
     * Returns the value of the '<em><b>Expression Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Language</em>' attribute.
     * @see #setExpressionLanguage(String)
     * @see org.imixs.bpmn2.Bpmn2Package#getDefinitions_ExpressionLanguage()
     * @model
     * @generated
     */
    String getExpressionLanguage();

    /**
     * Sets the value of the '{@link org.imixs.bpmn2.Definitions#getExpressionLanguage <em>Expression Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Language</em>' attribute.
     * @see #getExpressionLanguage()
     * @generated
     */
    void setExpressionLanguage(String value);

} // Definitions
