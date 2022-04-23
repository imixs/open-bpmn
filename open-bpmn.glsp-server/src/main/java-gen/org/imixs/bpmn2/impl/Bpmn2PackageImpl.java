/**
 */
package org.imixs.bpmn2.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.glsp.graph.GraphPackage;

import org.eclipse.glsp.graph.impl.GraphPackageImpl;

import org.imixs.bpmn2.Activity;
import org.imixs.bpmn2.BaseElement;
import org.imixs.bpmn2.Bpmn2Factory;
import org.imixs.bpmn2.Bpmn2Package;
import org.imixs.bpmn2.CatchEvent;
import org.imixs.bpmn2.ComplexGateway;
import org.imixs.bpmn2.Definitions;
import org.imixs.bpmn2.EndEvent;
import org.imixs.bpmn2.Event;
import org.imixs.bpmn2.ExclusiveGateway;
import org.imixs.bpmn2.Expression;
import org.imixs.bpmn2.Extension;
import org.imixs.bpmn2.ExtensionAttributeDefinition;
import org.imixs.bpmn2.ExtensionAttributeValue;
import org.imixs.bpmn2.ExtensionDefinition;
import org.imixs.bpmn2.FlowElement;
import org.imixs.bpmn2.FlowNode;
import org.imixs.bpmn2.Gateway;
import org.imixs.bpmn2.Icon;
import org.imixs.bpmn2.Import;
import org.imixs.bpmn2.IncusiveGateway;
import org.imixs.bpmn2.ParallelGateway;
import org.imixs.bpmn2.Pool;
import org.imixs.bpmn2.Relationship;
import org.imixs.bpmn2.RelationshipDirection;
import org.imixs.bpmn2.RootElement;
import org.imixs.bpmn2.SequenceFlow;
import org.imixs.bpmn2.StartEvent;
import org.imixs.bpmn2.Task;
import org.imixs.bpmn2.ThrowEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bpmn2PackageImpl extends EPackageImpl implements Bpmn2Package {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass baseElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass importEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionAttributeDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionAttributeValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relationshipEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass definitionsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass rootElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass flowElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sequenceFlowEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass flowNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass gatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parallelGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass exclusiveGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass incusiveGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass complexGatewayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass eventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass catchEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass throwEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass startEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endEventEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass activityEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass taskEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass poolEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iconEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum relationshipDirectionEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.imixs.bpmn2.Bpmn2Package#eNS_URI
     * @see #init()
     * @generated
     */
    private Bpmn2PackageImpl() {
        super(eNS_URI, Bpmn2Factory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link Bpmn2Package#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static Bpmn2Package init() {
        if (isInited) return (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);

        // Obtain or create and register package
        Object registeredBpmn2Package = EPackage.Registry.INSTANCE.get(eNS_URI);
        Bpmn2PackageImpl theBpmn2Package = registeredBpmn2Package instanceof Bpmn2PackageImpl ? (Bpmn2PackageImpl)registeredBpmn2Package : new Bpmn2PackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(GraphPackage.eNS_URI);
        GraphPackageImpl theGraphPackage = (GraphPackageImpl)(registeredPackage instanceof GraphPackageImpl ? registeredPackage : GraphPackage.eINSTANCE);

        // Create package meta-data objects
        theBpmn2Package.createPackageContents();
        theGraphPackage.createPackageContents();

        // Initialize created meta-data
        theBpmn2Package.initializePackageContents();
        theGraphPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theBpmn2Package.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI, theBpmn2Package);
        return theBpmn2Package;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBaseElement() {
        return baseElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseElement_Name() {
        return (EAttribute)baseElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseElement_Documentation() {
        return (EAttribute)baseElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImport() {
        return importEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getImport_ImportType() {
        return (EAttribute)importEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getImport_Location() {
        return (EAttribute)importEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getImport_Namespace() {
        return (EAttribute)importEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtensionDefinition() {
        return extensionDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionDefinition_Name() {
        return (EAttribute)extensionDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtensionDefinition_ExtensionAttributeDefinitions() {
        return (EReference)extensionDefinitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtensionAttributeDefinition() {
        return extensionAttributeDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionAttributeDefinition_Name() {
        return (EAttribute)extensionAttributeDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionAttributeDefinition_Type() {
        return (EAttribute)extensionAttributeDefinitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionAttributeDefinition_IsReference() {
        return (EAttribute)extensionAttributeDefinitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtensionAttributeDefinition_ExtensionDefinition() {
        return (EReference)extensionAttributeDefinitionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtensionAttributeValue() {
        return extensionAttributeValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtensionAttributeValue_ValueRef() {
        return (EReference)extensionAttributeValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionAttributeValue_Value() {
        return (EAttribute)extensionAttributeValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtensionAttributeValue_ExtensionAttributeDefinition() {
        return (EReference)extensionAttributeValueEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtension() {
        return extensionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtension_MustUnderstand() {
        return (EAttribute)extensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtension_Definition() {
        return (EReference)extensionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRelationship() {
        return relationshipEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRelationship_Direction() {
        return (EAttribute)relationshipEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRelationship_Sources() {
        return (EReference)relationshipEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRelationship_Targets() {
        return (EReference)relationshipEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDefinitions() {
        return definitionsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_RootElements() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_Imports() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_Relationships() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDefinitions_Extensions() {
        return (EReference)definitionsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_Exporter() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_ExporterVersion() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_TargetNamespace() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_TypeLanguage() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinitions_ExpressionLanguage() {
        return (EAttribute)definitionsEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRootElement() {
        return rootElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFlowElement() {
        return flowElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFlowElement_Category() {
        return (EAttribute)flowElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpression() {
        return expressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSequenceFlow() {
        return sequenceFlowEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSequenceFlow_ConditionExpression() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSequenceFlow_SourceRef() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSequenceFlow_TargetRef() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFlowNode() {
        return flowNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFlowNode_Incoming() {
        return (EReference)flowNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFlowNode_Outgoing() {
        return (EReference)flowNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGateway() {
        return gatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParallelGateway() {
        return parallelGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExclusiveGateway() {
        return exclusiveGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExclusiveGateway_DefaultSequenceFlow() {
        return (EReference)exclusiveGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIncusiveGateway() {
        return incusiveGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIncusiveGateway_DefaultSequenceFlow() {
        return (EReference)incusiveGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComplexGateway() {
        return complexGatewayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComplexGateway_DefaultSequenceFlow() {
        return (EReference)complexGatewayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEvent() {
        return eventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCatchEvent() {
        return catchEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getThrowEvent() {
        return throwEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStartEvent() {
        return startEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStartEvent_IsInterrupting() {
        return (EAttribute)startEventEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndEvent() {
        return endEventEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getActivity() {
        return activityEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTask() {
        return taskEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPool() {
        return poolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIcon() {
        return iconEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRelationshipDirection() {
        return relationshipDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bpmn2Factory getBpmn2Factory() {
        return (Bpmn2Factory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        baseElementEClass = createEClass(BASE_ELEMENT);
        createEAttribute(baseElementEClass, BASE_ELEMENT__NAME);
        createEAttribute(baseElementEClass, BASE_ELEMENT__DOCUMENTATION);

        importEClass = createEClass(IMPORT);
        createEAttribute(importEClass, IMPORT__IMPORT_TYPE);
        createEAttribute(importEClass, IMPORT__LOCATION);
        createEAttribute(importEClass, IMPORT__NAMESPACE);

        extensionDefinitionEClass = createEClass(EXTENSION_DEFINITION);
        createEAttribute(extensionDefinitionEClass, EXTENSION_DEFINITION__NAME);
        createEReference(extensionDefinitionEClass, EXTENSION_DEFINITION__EXTENSION_ATTRIBUTE_DEFINITIONS);

        extensionAttributeDefinitionEClass = createEClass(EXTENSION_ATTRIBUTE_DEFINITION);
        createEAttribute(extensionAttributeDefinitionEClass, EXTENSION_ATTRIBUTE_DEFINITION__NAME);
        createEAttribute(extensionAttributeDefinitionEClass, EXTENSION_ATTRIBUTE_DEFINITION__TYPE);
        createEAttribute(extensionAttributeDefinitionEClass, EXTENSION_ATTRIBUTE_DEFINITION__IS_REFERENCE);
        createEReference(extensionAttributeDefinitionEClass, EXTENSION_ATTRIBUTE_DEFINITION__EXTENSION_DEFINITION);

        extensionAttributeValueEClass = createEClass(EXTENSION_ATTRIBUTE_VALUE);
        createEReference(extensionAttributeValueEClass, EXTENSION_ATTRIBUTE_VALUE__VALUE_REF);
        createEAttribute(extensionAttributeValueEClass, EXTENSION_ATTRIBUTE_VALUE__VALUE);
        createEReference(extensionAttributeValueEClass, EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION);

        extensionEClass = createEClass(EXTENSION);
        createEAttribute(extensionEClass, EXTENSION__MUST_UNDERSTAND);
        createEReference(extensionEClass, EXTENSION__DEFINITION);

        relationshipEClass = createEClass(RELATIONSHIP);
        createEAttribute(relationshipEClass, RELATIONSHIP__DIRECTION);
        createEReference(relationshipEClass, RELATIONSHIP__SOURCES);
        createEReference(relationshipEClass, RELATIONSHIP__TARGETS);

        definitionsEClass = createEClass(DEFINITIONS);
        createEReference(definitionsEClass, DEFINITIONS__ROOT_ELEMENTS);
        createEReference(definitionsEClass, DEFINITIONS__IMPORTS);
        createEReference(definitionsEClass, DEFINITIONS__RELATIONSHIPS);
        createEReference(definitionsEClass, DEFINITIONS__EXTENSIONS);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPORTER);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPORTER_VERSION);
        createEAttribute(definitionsEClass, DEFINITIONS__TARGET_NAMESPACE);
        createEAttribute(definitionsEClass, DEFINITIONS__TYPE_LANGUAGE);
        createEAttribute(definitionsEClass, DEFINITIONS__EXPRESSION_LANGUAGE);

        rootElementEClass = createEClass(ROOT_ELEMENT);

        flowElementEClass = createEClass(FLOW_ELEMENT);
        createEAttribute(flowElementEClass, FLOW_ELEMENT__CATEGORY);

        expressionEClass = createEClass(EXPRESSION);

        sequenceFlowEClass = createEClass(SEQUENCE_FLOW);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__CONDITION_EXPRESSION);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__SOURCE_REF);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__TARGET_REF);

        flowNodeEClass = createEClass(FLOW_NODE);
        createEReference(flowNodeEClass, FLOW_NODE__INCOMING);
        createEReference(flowNodeEClass, FLOW_NODE__OUTGOING);

        gatewayEClass = createEClass(GATEWAY);

        parallelGatewayEClass = createEClass(PARALLEL_GATEWAY);

        exclusiveGatewayEClass = createEClass(EXCLUSIVE_GATEWAY);
        createEReference(exclusiveGatewayEClass, EXCLUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        incusiveGatewayEClass = createEClass(INCUSIVE_GATEWAY);
        createEReference(incusiveGatewayEClass, INCUSIVE_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        complexGatewayEClass = createEClass(COMPLEX_GATEWAY);
        createEReference(complexGatewayEClass, COMPLEX_GATEWAY__DEFAULT_SEQUENCE_FLOW);

        eventEClass = createEClass(EVENT);

        catchEventEClass = createEClass(CATCH_EVENT);

        throwEventEClass = createEClass(THROW_EVENT);

        startEventEClass = createEClass(START_EVENT);
        createEAttribute(startEventEClass, START_EVENT__IS_INTERRUPTING);

        endEventEClass = createEClass(END_EVENT);

        activityEClass = createEClass(ACTIVITY);

        taskEClass = createEClass(TASK);

        poolEClass = createEClass(POOL);

        iconEClass = createEClass(ICON);

        // Create enums
        relationshipDirectionEEnum = createEEnum(RELATIONSHIP_DIRECTION);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        GraphPackage theGraphPackage = (GraphPackage)EPackage.Registry.INSTANCE.getEPackage(GraphPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        baseElementEClass.getESuperTypes().add(theGraphPackage.getGNode());
        relationshipEClass.getESuperTypes().add(this.getBaseElement());
        definitionsEClass.getESuperTypes().add(this.getBaseElement());
        rootElementEClass.getESuperTypes().add(this.getBaseElement());
        flowElementEClass.getESuperTypes().add(this.getBaseElement());
        expressionEClass.getESuperTypes().add(this.getBaseElement());
        sequenceFlowEClass.getESuperTypes().add(theGraphPackage.getGEdge());
        sequenceFlowEClass.getESuperTypes().add(this.getFlowElement());
        flowNodeEClass.getESuperTypes().add(this.getFlowElement());
        gatewayEClass.getESuperTypes().add(this.getFlowElement());
        parallelGatewayEClass.getESuperTypes().add(this.getGateway());
        exclusiveGatewayEClass.getESuperTypes().add(this.getGateway());
        incusiveGatewayEClass.getESuperTypes().add(this.getGateway());
        complexGatewayEClass.getESuperTypes().add(this.getGateway());
        eventEClass.getESuperTypes().add(this.getFlowNode());
        catchEventEClass.getESuperTypes().add(this.getEvent());
        throwEventEClass.getESuperTypes().add(this.getEvent());
        startEventEClass.getESuperTypes().add(this.getCatchEvent());
        endEventEClass.getESuperTypes().add(this.getThrowEvent());
        activityEClass.getESuperTypes().add(this.getFlowNode());
        taskEClass.getESuperTypes().add(this.getActivity());
        poolEClass.getESuperTypes().add(this.getBaseElement());
        iconEClass.getESuperTypes().add(theGraphPackage.getGCompartment());

        // Initialize classes, features, and operations; add parameters
        initEClass(baseElementEClass, BaseElement.class, "BaseElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBaseElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBaseElement_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, BaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getImport_ImportType(), ecorePackage.getEString(), "importType", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getImport_Location(), ecorePackage.getEString(), "location", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getImport_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(extensionDefinitionEClass, ExtensionDefinition.class, "ExtensionDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExtensionDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExtensionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExtensionDefinition_ExtensionAttributeDefinitions(), this.getExtensionAttributeDefinition(), this.getExtensionAttributeDefinition_ExtensionDefinition(), "extensionAttributeDefinitions", null, 0, -1, ExtensionDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

        initEClass(extensionAttributeDefinitionEClass, ExtensionAttributeDefinition.class, "ExtensionAttributeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExtensionAttributeDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExtensionAttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExtensionAttributeDefinition_Type(), ecorePackage.getEString(), "type", null, 0, 1, ExtensionAttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExtensionAttributeDefinition_IsReference(), ecorePackage.getEBoolean(), "isReference", null, 0, 1, ExtensionAttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExtensionAttributeDefinition_ExtensionDefinition(), this.getExtensionDefinition(), this.getExtensionDefinition_ExtensionAttributeDefinitions(), "extensionDefinition", null, 1, 1, ExtensionAttributeDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

        initEClass(extensionAttributeValueEClass, ExtensionAttributeValue.class, "ExtensionAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExtensionAttributeValue_ValueRef(), ecorePackage.getEObject(), null, "valueRef", null, 0, 1, ExtensionAttributeValue.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEAttribute(getExtensionAttributeValue_Value(), ecorePackage.getEFeatureMapEntry(), "value", null, 0, -1, ExtensionAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExtensionAttributeValue_ExtensionAttributeDefinition(), this.getExtensionAttributeDefinition(), null, "extensionAttributeDefinition", null, 1, 1, ExtensionAttributeValue.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

        initEClass(extensionEClass, Extension.class, "Extension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExtension_MustUnderstand(), ecorePackage.getEBoolean(), "mustUnderstand", null, 0, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExtension_Definition(), this.getExtensionDefinition(), null, "definition", null, 1, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(relationshipEClass, Relationship.class, "Relationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRelationship_Direction(), this.getRelationshipDirection(), "direction", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRelationship_Sources(), ecorePackage.getEObject(), null, "sources", null, 1, -1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getRelationship_Targets(), ecorePackage.getEObject(), null, "targets", null, 1, -1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(definitionsEClass, Definitions.class, "Definitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDefinitions_RootElements(), this.getRootElement(), null, "rootElements", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDefinitions_Imports(), this.getImport(), null, "imports", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDefinitions_Relationships(), this.getRelationship(), null, "relationships", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDefinitions_Extensions(), this.getExtension(), null, "extensions", null, 0, -1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDefinitions_Exporter(), ecorePackage.getEString(), "exporter", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_ExporterVersion(), ecorePackage.getEString(), "exporterVersion", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_TargetNamespace(), ecorePackage.getEString(), "targetNamespace", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_TypeLanguage(), ecorePackage.getEString(), "typeLanguage", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitions_ExpressionLanguage(), ecorePackage.getEString(), "expressionLanguage", null, 0, 1, Definitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rootElementEClass, RootElement.class, "RootElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(flowElementEClass, FlowElement.class, "FlowElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowElement_Category(), ecorePackage.getEString(), "category", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sequenceFlowEClass, SequenceFlow.class, "SequenceFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSequenceFlow_ConditionExpression(), this.getExpression(), null, "conditionExpression", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSequenceFlow_SourceRef(), this.getFlowNode(), this.getFlowNode_Outgoing(), "sourceRef", null, 1, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSequenceFlow_TargetRef(), this.getFlowNode(), this.getFlowNode_Incoming(), "targetRef", null, 1, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(flowNodeEClass, FlowNode.class, "FlowNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFlowNode_Incoming(), this.getSequenceFlow(), this.getSequenceFlow_TargetRef(), "incoming", null, 0, -1, FlowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlowNode_Outgoing(), this.getSequenceFlow(), this.getSequenceFlow_SourceRef(), "outgoing", null, 0, -1, FlowNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gatewayEClass, Gateway.class, "Gateway", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(parallelGatewayEClass, ParallelGateway.class, "ParallelGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(exclusiveGatewayEClass, ExclusiveGateway.class, "ExclusiveGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExclusiveGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, ExclusiveGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(incusiveGatewayEClass, IncusiveGateway.class, "IncusiveGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIncusiveGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, IncusiveGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(complexGatewayEClass, ComplexGateway.class, "ComplexGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComplexGateway_DefaultSequenceFlow(), this.getSequenceFlow(), null, "defaultSequenceFlow", null, 0, 1, ComplexGateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(eventEClass, Event.class, "Event", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(catchEventEClass, CatchEvent.class, "CatchEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(throwEventEClass, ThrowEvent.class, "ThrowEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(startEventEClass, StartEvent.class, "StartEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStartEvent_IsInterrupting(), ecorePackage.getEBoolean(), "isInterrupting", "true", 0, 1, StartEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(endEventEClass, EndEvent.class, "EndEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(activityEClass, Activity.class, "Activity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iconEClass, Icon.class, "Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(relationshipDirectionEEnum, RelationshipDirection.class, "RelationshipDirection");
        addEEnumLiteral(relationshipDirectionEEnum, RelationshipDirection.NONE);
        addEEnumLiteral(relationshipDirectionEEnum, RelationshipDirection.FORWARD);
        addEEnumLiteral(relationshipDirectionEEnum, RelationshipDirection.BACKWARD);
        addEEnumLiteral(relationshipDirectionEEnum, RelationshipDirection.BOTH);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
        addAnnotation
          (expressionEClass,
           source,
           new String[] {
               "name", "tExpression",
               "kind", "mixed"
           });
        addAnnotation
          (getSequenceFlow_ConditionExpression(),
           source,
           new String[] {
               "kind", "element",
               "name", "conditionExpression"
           });
        addAnnotation
          (getSequenceFlow_SourceRef(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "sourceRef"
           });
        addAnnotation
          (getSequenceFlow_TargetRef(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "targetRef"
           });
        addAnnotation
          (flowNodeEClass,
           source,
           new String[] {
               "name", "tFlowNode",
               "kind", "elementOnly"
           });
        addAnnotation
          (getFlowNode_Incoming(),
           source,
           new String[] {
               "kind", "element",
               "name", "incoming",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/MODEL"
           });
        addAnnotation
          (getFlowNode_Outgoing(),
           source,
           new String[] {
               "kind", "element",
               "name", "outgoing",
               "namespace", "http://www.omg.org/spec/BPMN/20100524/MODEL"
           });
        addAnnotation
          (exclusiveGatewayEClass,
           source,
           new String[] {
               "name", "tExclusiveGateway",
               "kind", "elementOnly"
           });
        addAnnotation
          (getExclusiveGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (getIncusiveGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (getComplexGateway_DefaultSequenceFlow(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "defaultSequenceFlow"
           });
        addAnnotation
          (startEventEClass,
           source,
           new String[] {
               "name", "tStartEvent",
               "kind", "elementOnly"
           });
        addAnnotation
          (getStartEvent_IsInterrupting(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "isInterrupting"
           });
        addAnnotation
          (endEventEClass,
           source,
           new String[] {
               "name", "tEndEvent",
               "kind", "elementOnly"
           });
    }

} //Bpmn2PackageImpl
