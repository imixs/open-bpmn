/**
 */
package org.openbpmn.bpmndi.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openbpmn.bpmndi.BPMNDiagram;
import org.openbpmn.bpmndi.BPMNEdge;
import org.openbpmn.bpmndi.BPMNLabel;
import org.openbpmn.bpmndi.BPMNLabelStyle;
import org.openbpmn.bpmndi.BPMNPlane;
import org.openbpmn.bpmndi.BPMNShape;
import org.openbpmn.bpmndi.BpmndiPackage;
import org.openbpmn.bpmndi.DocumentRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNEdge <em>BPMN Edge</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.openbpmn.bpmndi.impl.DocumentRootImpl#getBPMNShape <em>BPMN Shape</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmndiPackage.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, BpmndiPackage.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNDiagram getBPMNDiagram() {
        // TODO: implement this method to return the 'BPMN Diagram' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNDiagram(BPMNDiagram newBPMNDiagram, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Diagram' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNDiagram(BPMNDiagram newBPMNDiagram) {
        // TODO: implement this method to set the 'BPMN Diagram' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNEdge getBPMNEdge() {
        // TODO: implement this method to return the 'BPMN Edge' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNEdge(BPMNEdge newBPMNEdge, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Edge' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNEdge(BPMNEdge newBPMNEdge) {
        // TODO: implement this method to set the 'BPMN Edge' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabel getBPMNLabel() {
        // TODO: implement this method to return the 'BPMN Label' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNLabel(BPMNLabel newBPMNLabel, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Label' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNLabel(BPMNLabel newBPMNLabel) {
        // TODO: implement this method to set the 'BPMN Label' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNLabelStyle getBPMNLabelStyle() {
        // TODO: implement this method to return the 'BPMN Label Style' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNLabelStyle(BPMNLabelStyle newBPMNLabelStyle, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Label Style' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNLabelStyle(BPMNLabelStyle newBPMNLabelStyle) {
        // TODO: implement this method to set the 'BPMN Label Style' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNPlane getBPMNPlane() {
        // TODO: implement this method to return the 'BPMN Plane' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNPlane(BPMNPlane newBPMNPlane, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Plane' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNPlane(BPMNPlane newBPMNPlane) {
        // TODO: implement this method to set the 'BPMN Plane' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BPMNShape getBPMNShape() {
        // TODO: implement this method to return the 'BPMN Shape' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNShape(BPMNShape newBPMNShape, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'BPMN Shape' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBPMNShape(BPMNShape newBPMNShape) {
        // TODO: implement this method to set the 'BPMN Shape' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BpmndiPackage.DOCUMENT_ROOT__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_DIAGRAM:
                return basicSetBPMNDiagram(null, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_EDGE:
                return basicSetBPMNEdge(null, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL:
                return basicSetBPMNLabel(null, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL_STYLE:
                return basicSetBPMNLabelStyle(null, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_PLANE:
                return basicSetBPMNPlane(null, msgs);
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_SHAPE:
                return basicSetBPMNShape(null, msgs);
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
            case BpmndiPackage.DOCUMENT_ROOT__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                if (coreType) return getXMLNSPrefixMap();
                else return getXMLNSPrefixMap().map();
            case BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                if (coreType) return getXSISchemaLocation();
                else return getXSISchemaLocation().map();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_DIAGRAM:
                return getBPMNDiagram();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_EDGE:
                return getBPMNEdge();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL:
                return getBPMNLabel();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL_STYLE:
                return getBPMNLabelStyle();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_PLANE:
                return getBPMNPlane();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_SHAPE:
                return getBPMNShape();
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
            case BpmndiPackage.DOCUMENT_ROOT__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_DIAGRAM:
                setBPMNDiagram((BPMNDiagram)newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_EDGE:
                setBPMNEdge((BPMNEdge)newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL:
                setBPMNLabel((BPMNLabel)newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL_STYLE:
                setBPMNLabelStyle((BPMNLabelStyle)newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_PLANE:
                setBPMNPlane((BPMNPlane)newValue);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_SHAPE:
                setBPMNShape((BPMNShape)newValue);
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
            case BpmndiPackage.DOCUMENT_ROOT__MIXED:
                getMixed().clear();
                return;
            case BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                getXMLNSPrefixMap().clear();
                return;
            case BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                getXSISchemaLocation().clear();
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_DIAGRAM:
                setBPMNDiagram((BPMNDiagram)null);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_EDGE:
                setBPMNEdge((BPMNEdge)null);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL:
                setBPMNLabel((BPMNLabel)null);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL_STYLE:
                setBPMNLabelStyle((BPMNLabelStyle)null);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_PLANE:
                setBPMNPlane((BPMNPlane)null);
                return;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_SHAPE:
                setBPMNShape((BPMNShape)null);
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
            case BpmndiPackage.DOCUMENT_ROOT__MIXED:
                return mixed != null && !mixed.isEmpty();
            case BpmndiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
            case BpmndiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_DIAGRAM:
                return getBPMNDiagram() != null;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_EDGE:
                return getBPMNEdge() != null;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL:
                return getBPMNLabel() != null;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_LABEL_STYLE:
                return getBPMNLabelStyle() != null;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_PLANE:
                return getBPMNPlane() != null;
            case BpmndiPackage.DOCUMENT_ROOT__BPMN_SHAPE:
                return getBPMNShape() != null;
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
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl
