package org.eclipse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BpmnDiFactory;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.junit.Assert;
import org.junit.Test;


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

        org.eclipse.bpmn2.Process process = Bpmn2Factory.eINSTANCE.createProcess();

        BPMNDiagram bpmdiagram = BpmnDiFactory.eINSTANCE.createBPMNDiagram();

        definitions.getRootElements().add(process);
        definitions.getDiagrams().add(bpmdiagram);

        URI uri = URI.createURI("src/test/resources/eclipse-empty.bpmn");
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
                    new OnlyContainmentTypeInfo());

            
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
        uri = URI.createURI("src/test/resources/eclipse-empty.bpmn");
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
