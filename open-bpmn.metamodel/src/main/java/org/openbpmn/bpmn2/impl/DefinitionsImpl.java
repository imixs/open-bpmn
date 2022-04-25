/**
 */
package org.openbpmn.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.openbpmn.bpmn2.Bpmn2Package;
import org.openbpmn.bpmn2.Definitions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.openbpmn.bpmn2.impl.DefinitionsImpl#getExporterVersion <em>Exporter Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DefinitionsImpl extends BaseElementImpl implements Definitions {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

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
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DEFINITIONS__NAME, oldName, name));
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
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__NAME:
                return getName();
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                return getTargetNamespace();
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                return getExpressionLanguage();
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                return getTypeLanguage();
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                return getExporter();
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                return getExporterVersion();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Bpmn2Package.DEFINITIONS__NAME:
                setName((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                setTargetNamespace((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                setExpressionLanguage((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                setTypeLanguage((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                setExporter((String)newValue);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                setExporterVersion((String)newValue);
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
            case Bpmn2Package.DEFINITIONS__NAME:
                setName(NAME_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                setExpressionLanguage(EXPRESSION_LANGUAGE_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                setTypeLanguage(TYPE_LANGUAGE_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                setExporter(EXPORTER_EDEFAULT);
                return;
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                setExporterVersion(EXPORTER_VERSION_EDEFAULT);
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
            case Bpmn2Package.DEFINITIONS__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case Bpmn2Package.DEFINITIONS__TARGET_NAMESPACE:
                return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
            case Bpmn2Package.DEFINITIONS__EXPRESSION_LANGUAGE:
                return EXPRESSION_LANGUAGE_EDEFAULT == null ? expressionLanguage != null : !EXPRESSION_LANGUAGE_EDEFAULT.equals(expressionLanguage);
            case Bpmn2Package.DEFINITIONS__TYPE_LANGUAGE:
                return TYPE_LANGUAGE_EDEFAULT == null ? typeLanguage != null : !TYPE_LANGUAGE_EDEFAULT.equals(typeLanguage);
            case Bpmn2Package.DEFINITIONS__EXPORTER:
                return EXPORTER_EDEFAULT == null ? exporter != null : !EXPORTER_EDEFAULT.equals(exporter);
            case Bpmn2Package.DEFINITIONS__EXPORTER_VERSION:
                return EXPORTER_VERSION_EDEFAULT == null ? exporterVersion != null : !EXPORTER_VERSION_EDEFAULT.equals(exporterVersion);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", targetNamespace: ");
        result.append(targetNamespace);
        result.append(", expressionLanguage: ");
        result.append(expressionLanguage);
        result.append(", typeLanguage: ");
        result.append(typeLanguage);
        result.append(", exporter: ");
        result.append(exporter);
        result.append(", exporterVersion: ");
        result.append(exporterVersion);
        result.append(')');
        return result.toString();
    }

} //DefinitionsImpl
