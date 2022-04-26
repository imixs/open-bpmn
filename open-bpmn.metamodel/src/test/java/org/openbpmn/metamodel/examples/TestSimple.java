package org.openbpmn.metamodel.examples;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.openbpmn.bpmn2.Bpmn2Factory;
import org.openbpmn.bpmn2.Definitions;
import org.openbpmn.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.openbpmn.bpmndi.BPMNDiagram;
import org.openbpmn.bpmndi.BPMNPlane;
import org.openbpmn.bpmndi.BpmndiFactory;


public class TestSimple {

    private static Logger logger = Logger.getLogger(TestSimple.class.getName());

    /**
     * This test generate an empty BPMN model and stores the model into the file
     * example-empty.bpmn
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test 
    public void testEmptyModel() {

        logger.info("...creating new empty model");
 
        // create some definitions...
        Definitions definitions = Bpmn2Factory.eINSTANCE.createDefinitions();

        definitions.setExporter("demo");
        definitions.setExpressionLanguage("java");

        org.openbpmn.bpmn2.Process process = Bpmn2Factory.eINSTANCE.createProcess();

        BPMNDiagram bpmdiagram = BpmndiFactory.eINSTANCE.createBPMNDiagram();
        bpmdiagram.setId("1-000001");
        bpmdiagram.setDocumentation("...some docu");
        bpmdiagram.setName("my diagram");
        
        BPMNPlane bpmnplane = BpmndiFactory.eINSTANCE.createBPMNPlane();
        // id="BPMNPlane_1" bpmnElement="process_1">
        bpmnplane.setId("BPMNPlane_1");
        bpmnplane.setBpmnElement(process);
        bpmdiagram.setPlane(bpmnplane);
        
        

        process.setId("0000002");
        process.setIsClosed(true);
        definitions.getProcesses().add(process);
        definitions.getDiagrams().add(bpmdiagram);

        URI uri = URI.createURI("src/test/resources/openbpmn-empty.bpmn");
        Bpmn2ResourceFactoryImpl resFactory = new Bpmn2ResourceFactoryImpl();
        Resource resource = resFactory.createResource(uri);
        resource.getContents().add(definitions);
        Definitions def = (Definitions) resource.getContents().get(0);

        Assert.assertNotNull(def);
        try {

            Map options = new HashMap();


            ExtendedMetaData extendedMetadata = new XmlExtendedMetadata();
            options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadata);
           
            options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION,
                   false);

            
            options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
                    Boolean.TRUE);

        
            options.put(XMLResource.OPTION_ELEMENT_HANDLER,
                    new ElementHandlerImpl(true));

            options.put(XMLResource.OPTION_ENCODING, "UTF-8");

            
            resource.save(options);
            // resource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }

        // now read the model again form the file sytem.
        uri = URI.createURI("src/test/resources/openbpmn-empty.bpmn");
        resFactory = new Bpmn2ResourceFactoryImpl();
        resource = resFactory.createResource(uri);
        try {
            logger.info("...reading new empty model");
            HashMap<Object, Object> options = new HashMap<Object, Object>();
            options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);

            resource.load(options);
            definitions = (Definitions) resource.getContents().get(0);
            Assert.assertEquals("demo", definitions.getExporter());
            Assert.assertEquals("java", definitions.getExpressionLanguage());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        logger.info("...model creation sucessful");
    }

}
