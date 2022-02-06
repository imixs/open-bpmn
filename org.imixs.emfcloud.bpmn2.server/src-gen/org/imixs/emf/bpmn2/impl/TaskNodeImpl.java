/**
 */
package org.imixs.emf.bpmn2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.imixs.emf.bpmn2.Bpmn2Package;
import org.imixs.emf.bpmn2.TaskNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl#isExpanded <em>Expanded</em>}</li>
 *   <li>{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl#getTaskType <em>Task Type</em>}</li>
 *   <li>{@link org.imixs.emf.bpmn2.impl.TaskNodeImpl#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskNodeImpl extends ActivityNodeImpl implements TaskNode {
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
	 * The default value of the '{@link #isExpanded() <em>Expanded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpanded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXPANDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExpanded() <em>Expanded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpanded()
	 * @generated
	 * @ordered
	 */
	protected boolean expanded = EXPANDED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTaskType() <em>Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskType()
	 * @generated
	 * @ordered
	 */
	protected static final String TASK_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaskType() <em>Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskType()
	 * @generated
	 * @ordered
	 */
	protected String taskType = TASK_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected String reference = REFERENCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.TASK_NODE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.TASK_NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpanded(boolean newExpanded) {
		boolean oldExpanded = expanded;
		expanded = newExpanded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.TASK_NODE__EXPANDED, oldExpanded, expanded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.TASK_NODE__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaskType() {
		return taskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskType(String newTaskType) {
		String oldTaskType = taskType;
		taskType = newTaskType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.TASK_NODE__TASK_TYPE, oldTaskType, taskType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(String newReference) {
		String oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.TASK_NODE__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Bpmn2Package.TASK_NODE__NAME:
				return getName();
			case Bpmn2Package.TASK_NODE__EXPANDED:
				return isExpanded();
			case Bpmn2Package.TASK_NODE__DURATION:
				return getDuration();
			case Bpmn2Package.TASK_NODE__TASK_TYPE:
				return getTaskType();
			case Bpmn2Package.TASK_NODE__REFERENCE:
				return getReference();
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
			case Bpmn2Package.TASK_NODE__NAME:
				setName((String)newValue);
				return;
			case Bpmn2Package.TASK_NODE__EXPANDED:
				setExpanded((Boolean)newValue);
				return;
			case Bpmn2Package.TASK_NODE__DURATION:
				setDuration((Integer)newValue);
				return;
			case Bpmn2Package.TASK_NODE__TASK_TYPE:
				setTaskType((String)newValue);
				return;
			case Bpmn2Package.TASK_NODE__REFERENCE:
				setReference((String)newValue);
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
			case Bpmn2Package.TASK_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Bpmn2Package.TASK_NODE__EXPANDED:
				setExpanded(EXPANDED_EDEFAULT);
				return;
			case Bpmn2Package.TASK_NODE__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case Bpmn2Package.TASK_NODE__TASK_TYPE:
				setTaskType(TASK_TYPE_EDEFAULT);
				return;
			case Bpmn2Package.TASK_NODE__REFERENCE:
				setReference(REFERENCE_EDEFAULT);
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
			case Bpmn2Package.TASK_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Bpmn2Package.TASK_NODE__EXPANDED:
				return expanded != EXPANDED_EDEFAULT;
			case Bpmn2Package.TASK_NODE__DURATION:
				return duration != DURATION_EDEFAULT;
			case Bpmn2Package.TASK_NODE__TASK_TYPE:
				return TASK_TYPE_EDEFAULT == null ? taskType != null : !TASK_TYPE_EDEFAULT.equals(taskType);
			case Bpmn2Package.TASK_NODE__REFERENCE:
				return REFERENCE_EDEFAULT == null ? reference != null : !REFERENCE_EDEFAULT.equals(reference);
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
		result.append(", expanded: ");
		result.append(expanded);
		result.append(", duration: ");
		result.append(duration);
		result.append(", taskType: ");
		result.append(taskType);
		result.append(", reference: ");
		result.append(reference);
		result.append(')');
		return result.toString();
	}

} //TaskNodeImpl
