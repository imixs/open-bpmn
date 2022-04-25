/**
 */
package org.openbpmn.di.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.openbpmn.di.DiPackage;
import org.openbpmn.di.Diagram;
import org.openbpmn.di.DiagramElement;
import org.openbpmn.di.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getOwnedStyle <em>Owned Style</em>}</li>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openbpmn.di.impl.DiagramImpl#getResolution <em>Resolution</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DiagramImpl extends MinimalEObjectImpl.Container implements Diagram {
    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = DOCUMENTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected EList<Style> ownedStyle;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

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
     * The default value of the '{@link #getResolution() <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResolution()
     * @generated
     * @ordered
     */
    protected static final float RESOLUTION_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getResolution() <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResolution()
     * @generated
     * @ordered
     */
    protected float resolution = RESOLUTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__DOCUMENTATION, oldDocumentation, documentation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Style> getOwnedStyle() {
        if (ownedStyle == null) {
            ownedStyle = new EObjectResolvingEList<Style>(Style.class, this, DiPackage.DIAGRAM__OWNED_STYLE);
        }
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramElement getRootElement() {
        // TODO: implement this method to return the 'Root Element' reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getResolution() {
        return resolution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResolution(float newResolution) {
        float oldResolution = resolution;
        resolution = newResolution;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.DIAGRAM__RESOLUTION, oldResolution, resolution));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DiPackage.DIAGRAM__DOCUMENTATION:
                return getDocumentation();
            case DiPackage.DIAGRAM__OWNED_STYLE:
                return getOwnedStyle();
            case DiPackage.DIAGRAM__ROOT_ELEMENT:
                return getRootElement();
            case DiPackage.DIAGRAM__ID:
                return getId();
            case DiPackage.DIAGRAM__NAME:
                return getName();
            case DiPackage.DIAGRAM__RESOLUTION:
                return getResolution();
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
            case DiPackage.DIAGRAM__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case DiPackage.DIAGRAM__ID:
                setId((String)newValue);
                return;
            case DiPackage.DIAGRAM__NAME:
                setName((String)newValue);
                return;
            case DiPackage.DIAGRAM__RESOLUTION:
                setResolution((Float)newValue);
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
            case DiPackage.DIAGRAM__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case DiPackage.DIAGRAM__ID:
                setId(ID_EDEFAULT);
                return;
            case DiPackage.DIAGRAM__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DiPackage.DIAGRAM__RESOLUTION:
                setResolution(RESOLUTION_EDEFAULT);
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
            case DiPackage.DIAGRAM__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case DiPackage.DIAGRAM__OWNED_STYLE:
                return ownedStyle != null && !ownedStyle.isEmpty();
            case DiPackage.DIAGRAM__ROOT_ELEMENT:
                return getRootElement() != null;
            case DiPackage.DIAGRAM__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case DiPackage.DIAGRAM__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DiPackage.DIAGRAM__RESOLUTION:
                return resolution != RESOLUTION_EDEFAULT;
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
        result.append(" (documentation: ");
        result.append(documentation);
        result.append(", id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", resolution: ");
        result.append(resolution);
        result.append(')');
        return result.toString();
    }

} //DiagramImpl
