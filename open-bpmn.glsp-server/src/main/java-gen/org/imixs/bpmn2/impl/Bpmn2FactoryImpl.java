/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.imixs.bpmn2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2FactoryImpl extends EFactoryImpl implements Bpmn2Factory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Bpmn2Factory init() {
        try {
            Bpmn2Factory theBpmn2Factory = (Bpmn2Factory)EPackage.Registry.INSTANCE.getEFactory(Bpmn2Package.eNS_URI);
            if (theBpmn2Factory != null) {
                return theBpmn2Factory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new Bpmn2FactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2FactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case Bpmn2Package.IMPORT: return createImport();
            case Bpmn2Package.EXTENSION_DEFINITION: return createExtensionDefinition();
            case Bpmn2Package.EXTENSION_ATTRIBUTE_DEFINITION: return createExtensionAttributeDefinition();
            case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE: return createExtensionAttributeValue();
            case Bpmn2Package.EXTENSION: return createExtension();
            case Bpmn2Package.RELATIONSHIP: return createRelationship();
            case Bpmn2Package.DEFINITIONS: return createDefinitions();
            case Bpmn2Package.ROOT_ELEMENT: return createRootElement();
            case Bpmn2Package.EXPRESSION: return createExpression();
            case Bpmn2Package.SEQUENCE_FLOW: return createSequenceFlow();
            case Bpmn2Package.PARALLEL_GATEWAY: return createParallelGateway();
            case Bpmn2Package.EXCLUSIVE_GATEWAY: return createExclusiveGateway();
            case Bpmn2Package.INCUSIVE_GATEWAY: return createIncusiveGateway();
            case Bpmn2Package.COMPLEX_GATEWAY: return createComplexGateway();
            case Bpmn2Package.CATCH_EVENT: return createCatchEvent();
            case Bpmn2Package.THROW_EVENT: return createThrowEvent();
            case Bpmn2Package.START_EVENT: return createStartEvent();
            case Bpmn2Package.END_EVENT: return createEndEvent();
            case Bpmn2Package.TASK: return createTask();
            case Bpmn2Package.POOL: return createPool();
            case Bpmn2Package.ICON: return createIcon();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case Bpmn2Package.RELATIONSHIP_DIRECTION:
                return createRelationshipDirectionFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case Bpmn2Package.RELATIONSHIP_DIRECTION:
                return convertRelationshipDirectionToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Import createImport() {
        ImportImpl import_ = new ImportImpl();
        return import_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionDefinition createExtensionDefinition() {
        ExtensionDefinitionImpl extensionDefinition = new ExtensionDefinitionImpl();
        return extensionDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionAttributeDefinition createExtensionAttributeDefinition() {
        ExtensionAttributeDefinitionImpl extensionAttributeDefinition = new ExtensionAttributeDefinitionImpl();
        return extensionAttributeDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionAttributeValue createExtensionAttributeValue() {
        ExtensionAttributeValueImpl extensionAttributeValue = new ExtensionAttributeValueImpl();
        return extensionAttributeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Extension createExtension() {
        ExtensionImpl extension = new ExtensionImpl();
        return extension;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Relationship createRelationship() {
        RelationshipImpl relationship = new RelationshipImpl();
        return relationship;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Definitions createDefinitions() {
        DefinitionsImpl definitions = new DefinitionsImpl();
        return definitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RootElement createRootElement() {
        RootElementImpl rootElement = new RootElementImpl();
        return rootElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression createExpression() {
        ExpressionImpl expression = new ExpressionImpl();
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceFlow createSequenceFlow() {
        SequenceFlowImpl sequenceFlow = new SequenceFlowImpl();
        return sequenceFlow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParallelGateway createParallelGateway() {
        ParallelGatewayImpl parallelGateway = new ParallelGatewayImpl();
        return parallelGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExclusiveGateway createExclusiveGateway() {
        ExclusiveGatewayImpl exclusiveGateway = new ExclusiveGatewayImpl();
        return exclusiveGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IncusiveGateway createIncusiveGateway() {
        IncusiveGatewayImpl incusiveGateway = new IncusiveGatewayImpl();
        return incusiveGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComplexGateway createComplexGateway() {
        ComplexGatewayImpl complexGateway = new ComplexGatewayImpl();
        return complexGateway;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CatchEvent createCatchEvent() {
        CatchEventImpl catchEvent = new CatchEventImpl();
        return catchEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ThrowEvent createThrowEvent() {
        ThrowEventImpl throwEvent = new ThrowEventImpl();
        return throwEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StartEvent createStartEvent() {
        StartEventImpl startEvent = new StartEventImpl();
        return startEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndEvent createEndEvent() {
        EndEventImpl endEvent = new EndEventImpl();
        return endEvent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Task createTask() {
        TaskImpl task = new TaskImpl();
        return task;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pool createPool() {
        PoolImpl pool = new PoolImpl();
        return pool;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Icon createIcon() {
        IconImpl icon = new IconImpl();
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationshipDirection createRelationshipDirectionFromString(EDataType eDataType, String initialValue) {
        RelationshipDirection result = RelationshipDirection.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertRelationshipDirectionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2Package getBpmn2Package() {
        return (Bpmn2Package)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static Bpmn2Package getPackage() {
        return Bpmn2Package.eINSTANCE;
    }

} //Bpmn2FactoryImpl
