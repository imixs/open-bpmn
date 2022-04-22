/**
 */
package org.imixs.bpmn.bpmngraph.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.glsp.graph.GBoundsAware;
import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GEdgeLayoutable;
import org.eclipse.glsp.graph.GEdgePlacement;
import org.eclipse.glsp.graph.GLayouting;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GShapeElement;
import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GEdgeImpl;
import org.eclipse.glsp.graph.impl.StringToObjectMapEntryImpl;

import org.imixs.bpmn.bpmngraph.BaseElement;
import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.bpmngraph.Expression;
import org.imixs.bpmn.bpmngraph.FlowElement;
import org.imixs.bpmn.bpmngraph.FlowNode;
import org.imixs.bpmn.bpmngraph.SequenceFlow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getEdgePlacement <em>Edge Placement</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getLayoutOptions <em>Layout Options</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.imixs.bpmn.bpmngraph.impl.SequenceFlowImpl#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceFlowImpl extends GEdgeImpl implements SequenceFlow {
    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected GPoint position;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected GDimension size;

    /**
     * The cached value of the '{@link #getEdgePlacement() <em>Edge Placement</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEdgePlacement()
     * @generated
     * @ordered
     */
    protected GEdgePlacement edgePlacement;

    /**
     * The default value of the '{@link #getLayout() <em>Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected static final String LAYOUT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected String layout = LAYOUT_EDEFAULT;

    /**
     * The cached value of the '{@link #getLayoutOptions() <em>Layout Options</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLayoutOptions()
     * @generated
     * @ordered
     */
    protected EMap<String, Object> layoutOptions;

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
     * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategory()
     * @generated
     * @ordered
     */
    protected EList<String> category;

    /**
     * The cached value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConditionExpression()
     * @generated
     * @ordered
     */
    protected Expression conditionExpression;

    /**
     * The cached value of the '{@link #getSourceRef() <em>Source Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceRef()
     * @generated
     * @ordered
     */
    protected FlowNode sourceRef;

    /**
     * The cached value of the '{@link #getTargetRef() <em>Target Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetRef()
     * @generated
     * @ordered
     */
    protected FlowNode targetRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SequenceFlowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BpmngraphPackage.Literals.SEQUENCE_FLOW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GPoint getPosition() {
        return position;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPosition(GPoint newPosition, NotificationChain msgs) {
        GPoint oldPosition = position;
        position = newPosition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__POSITION, oldPosition, newPosition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosition(GPoint newPosition) {
        if (newPosition != position) {
            NotificationChain msgs = null;
            if (position != null)
                msgs = ((InternalEObject)position).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__POSITION, null, msgs);
            if (newPosition != null)
                msgs = ((InternalEObject)newPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__POSITION, null, msgs);
            msgs = basicSetPosition(newPosition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__POSITION, newPosition, newPosition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GDimension getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSize(GDimension newSize, NotificationChain msgs) {
        GDimension oldSize = size;
        size = newSize;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__SIZE, oldSize, newSize);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSize(GDimension newSize) {
        if (newSize != size) {
            NotificationChain msgs = null;
            if (size != null)
                msgs = ((InternalEObject)size).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__SIZE, null, msgs);
            if (newSize != null)
                msgs = ((InternalEObject)newSize).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__SIZE, null, msgs);
            msgs = basicSetSize(newSize, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__SIZE, newSize, newSize));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GEdgePlacement getEdgePlacement() {
        return edgePlacement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEdgePlacement(GEdgePlacement newEdgePlacement, NotificationChain msgs) {
        GEdgePlacement oldEdgePlacement = edgePlacement;
        edgePlacement = newEdgePlacement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT, oldEdgePlacement, newEdgePlacement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdgePlacement(GEdgePlacement newEdgePlacement) {
        if (newEdgePlacement != edgePlacement) {
            NotificationChain msgs = null;
            if (edgePlacement != null)
                msgs = ((InternalEObject)edgePlacement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT, null, msgs);
            if (newEdgePlacement != null)
                msgs = ((InternalEObject)newEdgePlacement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT, null, msgs);
            msgs = basicSetEdgePlacement(newEdgePlacement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT, newEdgePlacement, newEdgePlacement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLayout(String newLayout) {
        String oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__LAYOUT, oldLayout, layout));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, Object> getLayoutOptions() {
        if (layoutOptions == null) {
            layoutOptions = new EcoreEMap<String,Object>(GraphPackage.Literals.STRING_TO_OBJECT_MAP_ENTRY, StringToObjectMapEntryImpl.class, this, BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS);
        }
        return layoutOptions;
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
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION, oldDocumentation, documentation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getCategory() {
        if (category == null) {
            category = new EDataTypeUniqueEList<String>(String.class, this, BpmngraphPackage.SEQUENCE_FLOW__CATEGORY);
        }
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getConditionExpression() {
        return conditionExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConditionExpression(Expression newConditionExpression, NotificationChain msgs) {
        Expression oldConditionExpression = conditionExpression;
        conditionExpression = newConditionExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION, oldConditionExpression, newConditionExpression);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConditionExpression(Expression newConditionExpression) {
        if (newConditionExpression != conditionExpression) {
            NotificationChain msgs = null;
            if (conditionExpression != null)
                msgs = ((InternalEObject)conditionExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION, null, msgs);
            if (newConditionExpression != null)
                msgs = ((InternalEObject)newConditionExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION, null, msgs);
            msgs = basicSetConditionExpression(newConditionExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION, newConditionExpression, newConditionExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowNode getSourceRef() {
        return sourceRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceRef(FlowNode newSourceRef, NotificationChain msgs) {
        FlowNode oldSourceRef = sourceRef;
        sourceRef = newSourceRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF, oldSourceRef, newSourceRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceRef(FlowNode newSourceRef) {
        if (newSourceRef != sourceRef) {
            NotificationChain msgs = null;
            if (sourceRef != null)
                msgs = ((InternalEObject)sourceRef).eInverseRemove(this, BpmngraphPackage.FLOW_NODE__OUTGOING, FlowNode.class, msgs);
            if (newSourceRef != null)
                msgs = ((InternalEObject)newSourceRef).eInverseAdd(this, BpmngraphPackage.FLOW_NODE__OUTGOING, FlowNode.class, msgs);
            msgs = basicSetSourceRef(newSourceRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF, newSourceRef, newSourceRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowNode getTargetRef() {
        return targetRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetRef(FlowNode newTargetRef, NotificationChain msgs) {
        FlowNode oldTargetRef = targetRef;
        targetRef = newTargetRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF, oldTargetRef, newTargetRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetRef(FlowNode newTargetRef) {
        if (newTargetRef != targetRef) {
            NotificationChain msgs = null;
            if (targetRef != null)
                msgs = ((InternalEObject)targetRef).eInverseRemove(this, BpmngraphPackage.FLOW_NODE__INCOMING, FlowNode.class, msgs);
            if (newTargetRef != null)
                msgs = ((InternalEObject)newTargetRef).eInverseAdd(this, BpmngraphPackage.FLOW_NODE__INCOMING, FlowNode.class, msgs);
            msgs = basicSetTargetRef(newTargetRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF, newTargetRef, newTargetRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                if (sourceRef != null)
                    msgs = ((InternalEObject)sourceRef).eInverseRemove(this, BpmngraphPackage.FLOW_NODE__OUTGOING, FlowNode.class, msgs);
                return basicSetSourceRef((FlowNode)otherEnd, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                if (targetRef != null)
                    msgs = ((InternalEObject)targetRef).eInverseRemove(this, BpmngraphPackage.FLOW_NODE__INCOMING, FlowNode.class, msgs);
                return basicSetTargetRef((FlowNode)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BpmngraphPackage.SEQUENCE_FLOW__POSITION:
                return basicSetPosition(null, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__SIZE:
                return basicSetSize(null, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT:
                return basicSetEdgePlacement(null, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS:
                return ((InternalEList<?>)getLayoutOptions()).basicRemove(otherEnd, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION:
                return basicSetConditionExpression(null, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                return basicSetSourceRef(null, msgs);
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                return basicSetTargetRef(null, msgs);
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
            case BpmngraphPackage.SEQUENCE_FLOW__POSITION:
                return getPosition();
            case BpmngraphPackage.SEQUENCE_FLOW__SIZE:
                return getSize();
            case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT:
                return getEdgePlacement();
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT:
                return getLayout();
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS:
                if (coreType) return getLayoutOptions();
                else return getLayoutOptions().map();
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                return getName();
            case BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION:
                return getDocumentation();
            case BpmngraphPackage.SEQUENCE_FLOW__CATEGORY:
                return getCategory();
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION:
                return getConditionExpression();
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                return getSourceRef();
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                return getTargetRef();
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
            case BpmngraphPackage.SEQUENCE_FLOW__POSITION:
                setPosition((GPoint)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__SIZE:
                setSize((GDimension)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT:
                setEdgePlacement((GEdgePlacement)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT:
                setLayout((String)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS:
                ((EStructuralFeature.Setting)getLayoutOptions()).set(newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                setName((String)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CATEGORY:
                getCategory().clear();
                getCategory().addAll((Collection<? extends String>)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION:
                setConditionExpression((Expression)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                setSourceRef((FlowNode)newValue);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                setTargetRef((FlowNode)newValue);
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
            case BpmngraphPackage.SEQUENCE_FLOW__POSITION:
                setPosition((GPoint)null);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__SIZE:
                setSize((GDimension)null);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT:
                setEdgePlacement((GEdgePlacement)null);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT:
                setLayout(LAYOUT_EDEFAULT);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS:
                getLayoutOptions().clear();
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CATEGORY:
                getCategory().clear();
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION:
                setConditionExpression((Expression)null);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                setSourceRef((FlowNode)null);
                return;
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                setTargetRef((FlowNode)null);
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
            case BpmngraphPackage.SEQUENCE_FLOW__POSITION:
                return position != null;
            case BpmngraphPackage.SEQUENCE_FLOW__SIZE:
                return size != null;
            case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT:
                return edgePlacement != null;
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT:
                return LAYOUT_EDEFAULT == null ? layout != null : !LAYOUT_EDEFAULT.equals(layout);
            case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS:
                return layoutOptions != null && !layoutOptions.isEmpty();
            case BpmngraphPackage.SEQUENCE_FLOW__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case BpmngraphPackage.SEQUENCE_FLOW__CATEGORY:
                return category != null && !category.isEmpty();
            case BpmngraphPackage.SEQUENCE_FLOW__CONDITION_EXPRESSION:
                return conditionExpression != null;
            case BpmngraphPackage.SEQUENCE_FLOW__SOURCE_REF:
                return sourceRef != null;
            case BpmngraphPackage.SEQUENCE_FLOW__TARGET_REF:
                return targetRef != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == GBoundsAware.class) {
            switch (derivedFeatureID) {
                case BpmngraphPackage.SEQUENCE_FLOW__POSITION: return GraphPackage.GBOUNDS_AWARE__POSITION;
                case BpmngraphPackage.SEQUENCE_FLOW__SIZE: return GraphPackage.GBOUNDS_AWARE__SIZE;
                default: return -1;
            }
        }
        if (baseClass == GShapeElement.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == GEdgeLayoutable.class) {
            switch (derivedFeatureID) {
                case BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT: return GraphPackage.GEDGE_LAYOUTABLE__EDGE_PLACEMENT;
                default: return -1;
            }
        }
        if (baseClass == GLayouting.class) {
            switch (derivedFeatureID) {
                case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT: return GraphPackage.GLAYOUTING__LAYOUT;
                case BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS: return GraphPackage.GLAYOUTING__LAYOUT_OPTIONS;
                default: return -1;
            }
        }
        if (baseClass == GNode.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == BaseElement.class) {
            switch (derivedFeatureID) {
                case BpmngraphPackage.SEQUENCE_FLOW__NAME: return BpmngraphPackage.BASE_ELEMENT__NAME;
                case BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION: return BpmngraphPackage.BASE_ELEMENT__DOCUMENTATION;
                default: return -1;
            }
        }
        if (baseClass == FlowElement.class) {
            switch (derivedFeatureID) {
                case BpmngraphPackage.SEQUENCE_FLOW__CATEGORY: return BpmngraphPackage.FLOW_ELEMENT__CATEGORY;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == GBoundsAware.class) {
            switch (baseFeatureID) {
                case GraphPackage.GBOUNDS_AWARE__POSITION: return BpmngraphPackage.SEQUENCE_FLOW__POSITION;
                case GraphPackage.GBOUNDS_AWARE__SIZE: return BpmngraphPackage.SEQUENCE_FLOW__SIZE;
                default: return -1;
            }
        }
        if (baseClass == GShapeElement.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == GEdgeLayoutable.class) {
            switch (baseFeatureID) {
                case GraphPackage.GEDGE_LAYOUTABLE__EDGE_PLACEMENT: return BpmngraphPackage.SEQUENCE_FLOW__EDGE_PLACEMENT;
                default: return -1;
            }
        }
        if (baseClass == GLayouting.class) {
            switch (baseFeatureID) {
                case GraphPackage.GLAYOUTING__LAYOUT: return BpmngraphPackage.SEQUENCE_FLOW__LAYOUT;
                case GraphPackage.GLAYOUTING__LAYOUT_OPTIONS: return BpmngraphPackage.SEQUENCE_FLOW__LAYOUT_OPTIONS;
                default: return -1;
            }
        }
        if (baseClass == GNode.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == BaseElement.class) {
            switch (baseFeatureID) {
                case BpmngraphPackage.BASE_ELEMENT__NAME: return BpmngraphPackage.SEQUENCE_FLOW__NAME;
                case BpmngraphPackage.BASE_ELEMENT__DOCUMENTATION: return BpmngraphPackage.SEQUENCE_FLOW__DOCUMENTATION;
                default: return -1;
            }
        }
        if (baseClass == FlowElement.class) {
            switch (baseFeatureID) {
                case BpmngraphPackage.FLOW_ELEMENT__CATEGORY: return BpmngraphPackage.SEQUENCE_FLOW__CATEGORY;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (layout: ");
        result.append(layout);
        result.append(", name: ");
        result.append(name);
        result.append(", documentation: ");
        result.append(documentation);
        result.append(", category: ");
        result.append(category);
        result.append(')');
        return result.toString();
    }

} //SequenceFlowImpl
