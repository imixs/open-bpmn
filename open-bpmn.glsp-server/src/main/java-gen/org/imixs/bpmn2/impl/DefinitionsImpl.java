/**
 */
package org.imixs.bpmn2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.Definitions;
import org.imixs.bpmn2.Extension;
import org.imixs.bpmn2.Import;
import org.imixs.bpmn2.Relationship;
import org.imixs.bpmn2.RootElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getRootElements <em>Root Elements</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.imixs.bpmn2.impl.DefinitionsImpl#getExpressionLanguage <em>Expression Language</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DefinitionsImpl extends BaseElementImpl implements Definitions {
    /**
     * The cached value of the '{@link #getRootElements() <em>Root Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRootElements()
     * @generated
     * @ordered
     */
    protected EList<RootElement> rootElements;

    /**
     * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImports()
     * @generated
     * @ordered
     */
    protected EList<Import> imports;

    /**
     * The cached value of the '{@link #getRelationships() <em>Relationships</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelationships()
     * @generated
     * @ordered
     */
    protected EList<Relationship> relationships;

    /**
     * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensions()
     * @generated
     * @ordered
     */
    protected EList<Extension> extensions;

    /**
     * The default value of the '{@link #getExporter() <em>Exporter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExporter()
     * @generated
     * @ordered
     */
    protected static final String EXPORTER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExporter() <em>Exporter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExporter()
     * @generated
     * @ordered
     */
    protected String exporter = EXPORTER_EDEFAULT;

    /**
     * The default value of the '{@link #getExporterVersion() <em>Exporter Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExporterVersion()
     * @generated
     * @ordered
     */
    protected static final String EXPORTER_VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExporterVersion() <em>Exporter Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExporterVersion()
     * @generated
     * @ordered
     */
    protected String exporterVersion = EXPORTER_VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetNamespace()
     * @generated
     * @ordered
     */
    protected static final String TARGET_NAMESPACE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetNamespace()
     * @generated
     * @ordered
     */
    protected String targetNamespace = TARGET_NAMESPACE_EDEFAULT;

    /**
     * The default value of the '{@link #getTypeLanguage() <em>Type Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeLanguage()
     * @generated
     * @ordered
     */
    protected static final String TYPE_LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTypeLanguage() <em>Type Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeLanguage()
     * @generated
     * @ordered
     */
    protected String typeLanguage = TYPE_LANGUAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpressionLanguage()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpressionLanguage()
     * @generated
     * @ordered
     */
    protected String expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DefinitionsImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.DEFINITIONS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<RootElement> getRootElements() {
        if (rootElements == null) {
            rootElements = new EObjectContainmentEList<RootElement>(RootElement.class, this, Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS);
        }
        return rootElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Import> getImports() {
        if (imports == null) {
            imports = new EObjectContainmentEList<Import>(Import.class, this, Bpmn2Package.DEFINITIONS__IMPORTS);
        }
        return imports;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Relationship> getRelationships() {
        if (relationships == null) {
            relationships = new EObjectContainmentEList<Relationship>(Relationship.class, this, Bpmn2Package.DEFINITIONS__RELATIONSHIPS);
        }
        return relationships;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Extension> getExtensions() {
        if (extensions == null) {
            extensions = new EObjectContainmentEList<Extension>(Extension.class, this, Bpmn2Package.DEFINITIONS__EXTENSIONS);
        }
        return extensions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExporter() {
        return exporter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExporter(String newExporter) {
        String oldExporter = exporter;
        exporter = newExporter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__EXPORTER, oldExporter, exporter));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExporterVersion() {
        return exporterVersion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExporterVersion(String newExporterVersion) {
        String oldExporterVersion = exporterVersion;
        exporterVersion = newExporterVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__EXPORTER_VERSION, oldExporterVersion, exporterVersion));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetNamespace(String newTargetNamespace) {
        String oldTargetNamespace = targetNamespace;
        targetNamespace = newTargetNamespace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE, oldTargetNamespace, targetNamespace));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTypeLanguage() {
        return typeLanguage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeLanguage(String newTypeLanguage) {
        String oldTypeLanguage = typeLanguage;
        typeLanguage = newTypeLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE, oldTypeLanguage, typeLanguage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExpressionLanguage() {
        return expressionLanguage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpressionLanguage(String newExpressionLanguage) {
        String oldExpressionLanguage = expressionLanguage;
        expressionLanguage = newExpressionLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE, oldExpressionLanguage, expressionLanguage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS:
                return ((InternalEList<?>)getRootElements()).basicRemove(otherEnd, msgs);
            case Bpmn2Package.DEFINITIONS__IMPORTS:
                return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
            case Bpmn2Package.DEFINITIONS__RELATIONSHIPS:
                return ((InternalEList<?>)getRelationships()).basicRemove(otherEnd, msgs);
            case Bpmn2Package.DEFINITIONS__EXTENSIONS:
                return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS:
                return getRootElements();
            case Bpmn2Package.DEFINITIONS__IMPORTS:
                return getImports();
            case Bpmn2Package.DEFINITIONS__RELATIONSHIPS:
                return getRelationships();
            case Bpmn2Package.DEFINITIONS__EXTENSIONS:
                return getExtensions();
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                return getExporter();
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                return getExporterVersion();
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                return getTargetNamespace();
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                return getTypeLanguage();
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                return getExpressionLanguage();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS:
                getRootElements().clear();
                getRootElements().addAll((Collection<? extends RootElement>)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__IMPORTS:
                getImports().clear();
                getImports().addAll((Collection<? extends Import>)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__RELATIONSHIPS:
                getRelationships().clear();
                getRelationships().addAll((Collection<? extends Relationship>)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXTENSIONS:
                getExtensions().clear();
                getExtensions().addAll((Collection<? extends Extension>)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                setExporter((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                setExporterVersion((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                setTargetNamespace((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                setTypeLanguage((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                setExpressionLanguage((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS:
                getRootElements().clear();
                return;
            case Bpmn2Package.DEFINITIONS__IMPORTS:
                getImports().clear();
                return;
            case Bpmn2Package.DEFINITIONS__RELATIONSHIPS:
                getRelationships().clear();
                return;
            case Bpmn2Package.DEFINITIONS__EXTENSIONS:
                getExtensions().clear();
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                setExporter(EXPORTER_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                setExporterVersion(EXPORTER_VERSION_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                setTypeLanguage(TYPE_LANGUAGE_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                setExpressionLanguage(EXPRESSION_LANGUAGE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__ROOT_ELEMENTS:
                return rootElements != null && !rootElements.isEmpty();
            case Bpmn2Package.DEFINITIONS__IMPORTS:
                return imports != null && !imports.isEmpty();
            case Bpmn2Package.DEFINITIONS__RELATIONSHIPS:
                return relationships != null && !relationships.isEmpty();
            case Bpmn2Package.DEFINITIONS__EXTENSIONS:
                return extensions != null && !extensions.isEmpty();
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                return EXPORTER_EDEFAULT == null ? exporter != null : !EXPORTER_EDEFAULT.equals(exporter);
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                return EXPORTER_VERSION_EDEFAULT == null ? exporterVersion != null : !EXPORTER_VERSION_EDEFAULT.equals(exporterVersion);
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                return TYPE_LANGUAGE_EDEFAULT == null ? typeLanguage != null : !TYPE_LANGUAGE_EDEFAULT.equals(typeLanguage);
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                return EXPRESSION_LANGUAGE_EDEFAULT == null ? expressionLanguage != null : !EXPRESSION_LANGUAGE_EDEFAULT.equals(expressionLanguage);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (exporter: ");
        result.append(exporter);
        result.append(", exporterVersion: ");
        result.append(exporterVersion);
        result.append(", targetNamespace: ");
        result.append(targetNamespace);
        result.append(", typeLanguage: ");
        result.append(typeLanguage);
        result.append(", expressionLanguage: ");
        result.append(expressionLanguage);
        result.append(')');
        return result.toString();
    }

} //DefinitionsImpl
